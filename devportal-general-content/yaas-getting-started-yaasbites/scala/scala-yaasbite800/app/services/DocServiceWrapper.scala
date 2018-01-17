package services

import javax.inject.{Inject, Singleton}

import exceptions.{CallingYaaSServiceException, OperationFailed, YaasException}
import model.document.DocServiceInvocationInfo
import model.domain.{CreateResponse, Tip}
import play.api.http.Status
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest}
import play.api.{Configuration, Logger}
import utils.commons.EitherOps._

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

/**
  * Created by d066419 on 11/07/16.
  */
@Singleton
class DocServiceWrapper @Inject()(configuration: Configuration, oauthService: OauthService, wSClient: WSClient) {
  private val logger = Logger(classOf[DocServiceWrapper])
  private type YaasToken = String

  private val docServiceInvocationInfo: DocServiceInvocationInfo = DocServiceInvocationInfo.create(configuration)
    .getOrElse(throw new IllegalStateException("wrong configuration"))

  def add(tip: Tip)(implicit executionContext: ExecutionContext): Future[Either[YaasException, Tip]] = {
    def postRequest(token: String): Future[Either[YaasException, CreateResponse]] = {
      annotateHeaders4Body(wSClient.url(docServiceInvocationInfo.opsUrl), token)
        .post(Json.toJson(tip)) map { response =>
        response.json.asOpt[CreateResponse] map Right.apply getOrElse Left(OperationFailed(response.body))
      }
    }

    genericWork(postRequest) map (_.map(_ => tip))
  }

  def update(tip: Tip)(implicit executionContext: ExecutionContext): Future[Either[YaasException, String]] = {
    def putRequest(token: YaasToken) = {
      val url: String = docServiceInvocationInfo.getEntityOpsUrl(tip.id)
      annotateHeaders4Body(wSClient.url(url), token).put(Json.toJson(tip)) map { response =>
        if (response.status == Status.OK) Right(url) else Left(OperationFailed(response.status.toString))
      }
    }

    genericWork(putRequest)

  }

  def delete(id: String)(implicit executionContext: ExecutionContext): Future[Either[YaasException, String]] = doDelete(docServiceInvocationInfo.getEntityOpsUrl(id))

  def deleteAll(implicit executionContext: ExecutionContext): Future[Either[YaasException, String]] = doDelete(docServiceInvocationInfo.deleteAllUrl)

  def get(id: String)(implicit executionContext: ExecutionContext): Future[Either[YaasException, Option[Tip]]] = genericWork { token =>
    val url: String = docServiceInvocationInfo.getEntityOpsUrl(id)
    logger.debug(s"url to get single [$url]")
    annotateTokenHeader(wSClient.url(url), token).get() map { response =>
      if (response.status == Status.NOT_FOUND) Right(None)
      else response.json.asOpt[Tip] match {
        case s@Some(_) => Right(s)
        case _ => Left(CallingYaaSServiceException)
      }
    }
  }

  private def doDelete(url: String)(implicit executionContext: ExecutionContext): Future[Either[YaasException, String]] = genericWork { token =>
    logger.debug(s"deleting for url[$url]")
    annotateTokenHeader(wSClient.url(url), token).delete() map { response =>
      if (response.status == Status.NO_CONTENT) Right(url) else Left(OperationFailed(response.status.toString))
    }
  }


  def getDocuments(implicit executionContext: ExecutionContext): Future[Either[YaasException, List[Tip]]] = genericWork { token: YaasToken =>
    annotateTokenHeader(wSClient.url(docServiceInvocationInfo.opsUrl), token)
      .execute() map { wsResponse =>
      Right.apply(wsResponse.json.asOpt[List[Tip]].getOrElse(Nil))
    }
  }


  private def genericWork[T](f: YaasToken => Future[Either[YaasException, T]])(implicit executionContext: ExecutionContext) = (for {
    token: Either[YaasException, String] <- oauthService.getToken
    _ = logger.debug(s"token is $token")
    serviceRes <- token.asyncFlatMap(f)
  } yield serviceRes).recover {
    case NonFatal(e) => Left(OperationFailed(e))
  }


  private def annotateHeaders4Body(wSRequest: WSRequest, token: YaasToken) = {
    annotateTokenHeader(wSRequest, token).withHeaders(("Content-type", "application/json"))
  }

  private def annotateTokenHeader(wSRequest: WSRequest, token: YaasToken): WSRequest = {
    wSRequest.withHeaders(("Authorization", s"Bearer $token"))
  }

}
