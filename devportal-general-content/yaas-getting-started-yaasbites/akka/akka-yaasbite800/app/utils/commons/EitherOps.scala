package utils.commons

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by d066419 on 11/07/16.
  */
object EitherOps {

  class EitherWrapper[L, R](either: Either[L, R]) {

    def map[O](f: R => O): Either[L, O] = either match {
      case Left(l) => Left(l)
      case Right(r) => Right(f(r))
    }

    def asyncMap[O](f: R => Future[O])(implicit executionContext: ExecutionContext): Future[Either[L, O]] = either match {
      case Left(l) => toLeft(l)
      case Right(r) => f(r) map Right.apply
    }

    def asyncFlatMap[O](f: R => Future[Either[L, O]])(implicit executionContext: ExecutionContext): Future[Either[L, O]] = either match {
      case Left(l) => toLeft(l)
      case Right(r) => f(r)
    }
  }

  private def toLeft[L, O](l: L): Future[Left[L, O]] = {
    Future.successful(Left(l))
  }

  implicit def wrap[L, R](either: Either[L, R]): EitherWrapper[L, R] = new EitherWrapper[L, R](either)
}
