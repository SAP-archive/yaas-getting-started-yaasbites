package controllers

import javax.inject.Inject

import com.google.inject.Singleton
import controllers.ResultConverter._
import exceptions.YaasException
import model.domain.{Tip, TipBodyRequest}
import model.tenant.TenantInfo
import play.api.Logger
import play.api.libs.json.Json
import play.api.mvc._
import services.TipsRepository
import utils.configs.ThreadPools.appExecContext

import scala.concurrent.Future

/**
  * Created by d066419 on 06/07/16.
  */
@Singleton
class TipsController @Inject()(tipsRepository: TipsRepository,
                               controllerHelper: ControllerHelper) extends Controller {

  import controllerHelper._

  private val logger = Logger(classOf[TipsController])

  def ping = asyncAction { tenantInfo: TenantInfo =>
    Future.successful(Ok("pong"))
  }

  def list: Action[AnyContent] = asyncAction { (tenantInfo: TenantInfo) =>
    tipsRepository.list map (x => toJsonResult(x))
  }

  def add: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>

    val action: Action[AnyContent] = asyncFormAction(TipBodyRequest.tipBodyForm) { (tipAddReq, tenantInfo: TenantInfo) =>
      logger.debug(s"request uri ${request.uri} yaasHeaders [$tenantInfo]")

      tipsRepository.create(tipAddRequest = tipAddReq, tenant = tenantInfo.tenant) map { (e: Either[YaasException, Tip]) =>
        toResult(e)(tip => Created(Json.obj("id" -> tip.id)))
      }
    }
    action(request)
  }

  def update(id: String) = asyncFormAction(TipBodyRequest.tipBodyForm) { (tipBodyRequest: TipBodyRequest, tenantInfo: TenantInfo) =>
    actionIfExists(id) { () =>
      val tip: Tip = Tip(id = id, tip = tipBodyRequest.tip, tenant = tenantInfo.tenant)
      tipsRepository.update(tip) map { e =>
        logger.debug(s"updating $tip result is $e")
        toResult(e)(_ => Ok)
      }
    }

  }

  def delete(id: String) = asyncAction { tenantInfo: TenantInfo =>
    actionIfExists(id) { () =>
      tipsRepository.delete(id) map { res =>
        logger.debug(s"deleting tip $id result $res")
        toResult(res)(_ => NoContent)
      }
    }
  }

  def deleteAll() = asyncAction { tenantInfo: TenantInfo =>
    if (tenantInfo.scopes.exists(_.contains("hybris.tips_vip"))) {
      tipsRepository.deleteAll() map (x => toResult(x)(_ => Ok))
    } else Future.successful(BadRequest)
  }

  private def actionIfExists(id: String)(work: () => Future[Result]) = tipsRepository.exists(id) flatMap {
    case Left(yaasException) => Future.successful(InternalServerError)
    case Right(flag) => if (flag) work()
    else {
      logger.debug(s"entity with id [$id] does not exist")
      Future.successful(NotFound)
    }
  }
}
