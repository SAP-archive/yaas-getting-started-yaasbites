package model

import play.api.libs.json.Json

/**
  * Created by d066419 on 06/07/16.
  */
case class Tip(id: Long, tip: String)

object Tip {
  implicit val tipFormat = Json.format[Tip]
}



