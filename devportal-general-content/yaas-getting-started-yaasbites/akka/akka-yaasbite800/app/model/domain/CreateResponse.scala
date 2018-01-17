package model.domain

import play.api.libs.json.Json

/**
  * Created by d066419 on 11/07/16.
  */
case class CreateResponse(link: String, id: String)

object CreateResponse {
  implicit val createResponseFormat = Json.format[CreateResponse]
}
