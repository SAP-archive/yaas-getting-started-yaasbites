package controllers

import javax.inject.{Inject, Singleton}

import actors.TipsRepoActor
import actors.TipsRepoActor.ListTips
import akka.actor.{ActorRef, ActorSystem}
import akka.pattern._
import akka.util.Timeout
import akka.util.Timeout._
import controllers.ResultConverter._
import exceptions.YaasException
import model.domain.{CreateResponse, Tip, TipBodyRequest}
import model.tenant.TenantInfo
import play.api.Logger
import play.api.libs.json.Json
import play.api.libs.ws.WSClient
import play.api.mvc._
import utils.configs.NecessaryComponents
import utils.configs.ThreadPools.appExecContext

import scala.concurrent.Future
import scala.concurrent.duration._

/**
  * Created by d066419 on 06/07/16.
  */
@Singleton
class TipsActorController @Inject()(controllerHelper: ControllerHelper,
                                    necessaryComponents: NecessaryComponents,
                                    wSClient: WSClient,
                                    actorSystem: ActorSystem) extends Controller {

  import controllerHelper._

  private val logger = Logger(classOf[TipsActorController])

  private implicit val timeout: Timeout = 60 seconds
  private val tipsActor: ActorRef = actorSystem.actorOf(TipsRepoActor.props(wSClient, necessaryComponents))

  logger.debug(s"tips actors is [$tipsActor]")

  def ping = asyncAction { tenantInfo: TenantInfo =>
    (tipsActor ? "ping").mapTo[String] map ((x: String) => Ok(x))
  }

  def list: Action[AnyContent] = asyncAction { (tenantInfo: TenantInfo) =>
    workWithActorResponse(tipsActor ? ListTips) { y =>
      toJsonResult(y.asInstanceOf[Either[YaasException, List[Tip]]])
    }
  }

  def add: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>

    val action: Action[AnyContent] = asyncFormAction(TipBodyRequest.tipBodyForm) { (tipAddReq, tenantInfo: TenantInfo) =>
      logger.debug(s"request uri ${request.uri} yaasHeaders [$tenantInfo]")
      workWithActorResponse(tipsActor ? TipsRepoActor.Add(Tip(tipAddReq, tenantInfo.tenant))) { any =>
        val e = any.asInstanceOf[Either[YaasException, CreateResponse]]
        toResult(e)(createResponse => Created(Json.obj("id" -> createResponse.id)))
      }
    }
    action(request)
  }

  def update(id: String) = asyncFormAction(TipBodyRequest.tipBodyForm) { (tipBodyRequest: TipBodyRequest, tenantInfo: TenantInfo) =>
    actionIfExists(id) { () =>
      val tip: Tip = Tip(id = id, tip = tipBodyRequest.tip, tenant = tenantInfo.tenant)
      workWithActorResponse(tipsActor ? TipsRepoActor.Update(tip)) { any =>
        val e = any.asInstanceOf[Either[YaasException, String]]
        logger.debug(s"updating $tip result is $e")
        toResult(e)(_ => Ok)
      }
    }

  }

  def delete(id: String) = asyncAction { tenantInfo: TenantInfo =>
    actionIfExists(id) { () =>
      workWithActorResponse(tipsActor ? TipsRepoActor.Delete(id)) { any =>
        val res = any.asInstanceOf[Either[YaasException, String]]
        logger.debug(s"deleting tip $id result $res")
        toResult(res)(_ => NoContent)
      }
    }
  }

  def deleteAll() = asyncAction { tenantInfo: TenantInfo =>
    if (tenantInfo.scopes.exists(_.contains("hybris.tips_vip"))) {
      workWithActorResponse(tipsActor ? TipsRepoActor.DeleteAll) { any =>
        val x = any.asInstanceOf[Either[YaasException, String]]
        toResult(x)(_ => Ok)
      }
    } else Future.successful(BadRequest)
  }

  private def actionIfExists(id: String)(work: () => Future[Result]) = {
    import utils.commons.EitherOps._
    workWithActorAsyncResponse(tipsActor ? TipsRepoActor.Get(id)) { any =>
      val exists: Either[YaasException, Boolean] = any.asInstanceOf[Either[YaasException, Option[Tip]]].map(_.isDefined)
      exists match {
        case Left(yaasException) => Future.successful(InternalServerError)
        case Right(flag) => if (flag) work()
        else {
          logger.debug(s"entity with id [$id] does not exist")
          Future.successful(NotFound)
        }
      }
    }
  }

  private def workWithActorResponse(future: Future[Any])(f: Any => Result): Future[Result] = for {
    any <- future
  } yield any match {
    case exception: YaasException => InternalServerError(exception.getLocalizedMessage)
    case _ => f(any)
  }

  private def workWithActorAsyncResponse(future: Future[Any])(f: Any => Future[Result]): Future[Result] = for {
    any <- future
    res <- any match {
      case exception: YaasException => Future.successful(InternalServerError(exception.getLocalizedMessage))
      case _ => f(any)
    }
  } yield res
}
