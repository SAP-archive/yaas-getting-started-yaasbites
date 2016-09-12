package actors

import actors.OAuthActor.{CreateToken, TokenCreated, TokenCreationError, TokenCreationFailed}
import actors.TipsDocumentActor._
import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.pattern._
import exceptions.{CallingYaaSServiceException, OperationFailed, YaasException}
import model.domain.{CreateResponse, Tip}
import model.messages.BaseMessage
import play.api.http.Status
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest}
import utils.configs.NecessaryComponents

import scala.concurrent.Future
import scala.util.control.NonFatal

/**
  * Created by d066419 on 15/07/16.
  */
class TipsDocumentActor(wSClient: WSClient, necessaryComponents: NecessaryComponents) extends Actor with ActorLogging {

  import necessaryComponents._

  private val oAuthActor: ActorRef = context.actorOf(OAuthActor.props[DocumentOps](wSClient, necessaryComponents))

  override def receive: Receive = fromRepo orElse fromOAuth

  private implicit val ec = context.dispatcher

  private def fromRepo: Receive = {
    case documentOps: DocumentOps =>
      log.debug(s"got message $documentOps")
      oAuthActor ! CreateToken(documentOps)
  }

  private def fromOAuth: Receive = {
    case TokenCreated(ListDocs(controllerActor), token) =>
      val listRes = annotateTokenHeader(wSClient.url(docServiceInvocationInfo.opsUrl), token)
        .execute() map { wsResponse =>
        Right.apply(wsResponse.json.asOpt[List[Tip]].getOrElse(Nil))
      }
      doPipe(listRes, controllerActor)

    case TokenCreated(Add(controllerActor, tip), token) =>
      val addRes: Future[Either[OperationFailed, CreateResponse]] = annotateHeaders4Body(wSClient.url(docServiceInvocationInfo.opsUrl), token)
        .post(Json.toJson(tip)) map { response =>
        response.json.asOpt[CreateResponse] map Right.apply getOrElse Left(OperationFailed(response.body))
      }
      doPipe(addRes, controllerActor)

    case TokenCreated(Update(controllerActor, tip), token) =>
      val url: String = docServiceInvocationInfo.getEntityOpsUrl(tip.id)
      val updateRes: Future[Either[OperationFailed, String] with Product with Serializable] = annotateHeaders4Body(wSClient.url(url), token).put(Json.toJson(tip)) map { response =>
        if (response.status == Status.OK) Right(url) else Left(OperationFailed(response.status.toString))
      }
      doPipe(updateRes, controllerActor)

    case TokenCreated(Delete(controllerActor, id), token) =>
      doPipe(doDelete(docServiceInvocationInfo.getEntityOpsUrl(id), token), controllerActor)

    case TokenCreated(DeleteAll(controllerActor), token) =>
      doPipe(doDelete(docServiceInvocationInfo.deleteAllUrl, token), controllerActor)

    case TokenCreated(Get(controllerActor, id), token) =>
      val url = docServiceInvocationInfo.getEntityOpsUrl(id)
      val x: Future[Either[YaasException, Option[Tip]]] = for {
        response <- annotateTokenHeader(wSClient.url(url), token).get()
      } yield {
        if (response.status == Status.NOT_FOUND) Right(None)
        else response.json.asOpt[Tip] match {
          case t@Some(_) => Right(t)
          case _ => Left(CallingYaaSServiceException)
        }
      }
      doPipe(x, controllerActor)

    case TokenCreationFailed(documentOps, error: TokenCreationError) => documentOps.controllerActor ! error
  }

  private def doPipe[T](result: Future[Either[YaasException, T]], controllerActor: ActorRef): Unit = {
    pipe(result.recover {
      case NonFatal(e) => Left(OperationFailed(e))
    }) to controllerActor
  }

  private def doDelete(url: String, token: YaasToken): Future[Either[YaasException, String]] = {
    log.debug(s"deleting for url[$url]")
    annotateTokenHeader(wSClient.url(url), token).delete() map { response =>
      if (response.status == Status.NO_CONTENT) Right(url) else Left(OperationFailed(response.status.toString))
    }
  }
}

object TipsDocumentActor {
  private type YaasToken = String

  def props(wSClient: WSClient, necessaryComponents: NecessaryComponents) = Props(new TipsDocumentActor(wSClient = wSClient, necessaryComponents))

  sealed trait DocumentOps extends BaseMessage

  case class ListDocs(controllerActor: ActorRef) extends DocumentOps

  case class Add(controllerActor: ActorRef, tip: Tip) extends DocumentOps

  case class Update(controllerActor: ActorRef, tip: Tip) extends DocumentOps

  case class Delete(controllerActor: ActorRef, id: String) extends DocumentOps

  case class DeleteAll(controllerActor: ActorRef) extends DocumentOps

  case class Get(controllerActor: ActorRef, id: String) extends DocumentOps

  private def annotateHeaders4Body(wSRequest: WSRequest, token: YaasToken) = {
    annotateTokenHeader(wSRequest, token).withHeaders(("Content-type", "application/json"))
  }

  private def annotateTokenHeader(wSRequest: WSRequest, token: YaasToken): WSRequest = {
    wSRequest.withHeaders(("Authorization", s"Bearer $token"))
  }
}
