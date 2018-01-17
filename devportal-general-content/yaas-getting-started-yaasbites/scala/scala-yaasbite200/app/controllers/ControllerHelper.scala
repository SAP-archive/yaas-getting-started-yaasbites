package controllers

import javax.inject.Singleton

import play.api.data.Form
import play.api.mvc.Results._
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by d066419 on 06/07/16.
  */
@Singleton
class ControllerHelper {

  def asyncAction(f: () => Future[Result])(implicit executionContext: ExecutionContext): Action[AnyContent] = Action.async {
    f()
  }

  def asyncFormAction[T](form: Form[T])(f: T => Future[Result])
                        (implicit executionContext: ExecutionContext): Action[AnyContent] = Action.async { implicit request: Request[AnyContent] =>
    form.bindFromRequest().fold(
      (errors: Form[T]) => Future.successful(handleErrors(errors)),
      (t: T) => f(t)
    )
  }

  //TODO localization
  private def handleErrors[T](errors: Form[T]): Result = BadRequest
}
