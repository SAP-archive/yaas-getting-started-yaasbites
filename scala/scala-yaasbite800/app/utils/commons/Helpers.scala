package utils.commons

import java.util.UUID

/**
  * Created by d066419 on 10/07/16.
  */
object Helpers {
  def toEither[R, L](option: Option[R])(l: => L): Either[L, R] = {
    (option map Right.apply).getOrElse(Left(l))
  }

  def getUniqueId: String = UUID.randomUUID().toString
}
