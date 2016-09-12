package model

import play.api.libs.json.Json

/**
  * Created by d066419 on 06/07/16.
  */
case class Greetings(id: Long, content: String)

object Greetings {
  implicit val greetingsFormat = Json.format[Greetings]
}
