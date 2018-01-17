package controllers

import exceptions.YaasException
import play.api.libs.json.{Json, Writes}
import play.api.mvc.Results._
import play.api.mvc.{Result, Results}

/**
  * Created by d066419 on 12/07/16.
  */
object ResultConverter {
  def toResult[I](either: Either[YaasException, I])(rightResult: I => Result): Result = either match {
    case Left(yaasException) => Results.InternalServerError
    case Right(x) => rightResult(x)
  }

  def toJsonResult[T](either: Either[YaasException, T])(implicit writes: Writes[T]) = toResult(either)(x => Ok(Json.toJson(x)))

}
