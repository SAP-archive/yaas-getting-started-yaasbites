package controllers

import javax.inject.Singleton

import model.tenant.TenantInfo
import play.api.data.Form
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by d066419 on 06/07/16.
  */
@Singleton
class ControllerHelper {

  def asyncAction(f: TenantInfo => Future[Result])(implicit executionContext: ExecutionContext): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    TenantInfo.create(request.headers) match {
      case Some(tenantInfo) => f(tenantInfo)
      case _ => Future.successful(BadRequest("no tenant data"))
    }
  }

  def asyncFormAction[T](form: Form[T])(f: (T, TenantInfo) => Future[Result])
                        (implicit executionContext: ExecutionContext): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>

    val action: Action[AnyContent] = asyncAction { tenantInfo =>
      form.bindFromRequest().fold(
        (errors: Form[T]) => Future.successful(handleErrors(errors)),
        (t: T) => f(t, tenantInfo)
      )
    }

    action(request)
  }

  //TODO localization
  private def handleErrors[T](errors: Form[T]): Result = BadRequest
}
