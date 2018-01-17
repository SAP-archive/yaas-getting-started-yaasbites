package controllers

import javax.inject.Inject

import com.google.inject.Singleton
import model.{Tip, TipBodyRequest}
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
class TipsController @Inject()(tipsRepository: TipsRepository, controllerHelper: ControllerHelper) extends Controller {

  import controllerHelper._

  private val logger = Logger(classOf[TipsController])

  def ping = asyncAction(() => Future.successful(Ok("pong")))

  def list: Action[AnyContent] = asyncAction(() => tipsRepository.list() map (x => Ok(Json.toJson(x))))

  def add: Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    logger.debug(s"${request.uri}")

    val action: Action[AnyContent] = asyncFormAction(TipBodyRequest.tipBodyForm) { tipAddReq =>
      tipsRepository.create(tipAddReq) map { _ =>
        Created("created")
      }
    }
    action(request)
  }

  def update(id: Long) = asyncFormAction(TipBodyRequest.tipBodyForm) { tipBodyRequest: TipBodyRequest =>
    actionIfExists(id) { () =>
      val tip: Tip = Tip(id = id, tip = tipBodyRequest.tip)
      tipsRepository.update(tip) map { res =>
        logger.debug(s"updating $tip result is $res")
        Ok
      }
    }

  }

  def delete(id: Long) = asyncAction { () =>
    actionIfExists(id) { () =>
      tipsRepository.delete(id) map { res =>
        logger.debug(s"deleting tip $id result $res")
        Ok
      }
    }
  }

  private def actionIfExists(id: Long)(work: () => Future[Result]) = tipsRepository.exists(id) flatMap {
    case false => Future.successful(NotFound)
    case true => work()
  }
}
