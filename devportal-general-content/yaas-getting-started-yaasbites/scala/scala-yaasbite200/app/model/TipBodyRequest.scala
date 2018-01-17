package model

import play.api.data.Form
import play.api.data.Forms._
import play.api.libs.json.Json

/**
  * Created by d066419 on 06/07/16.
  */
case class TipBodyRequest(tip: String)

object TipBodyRequest {
  val tipBodyForm = Form(mapping = mapping(
    "tip" -> nonEmptyText.transform[String](_.toLowerCase, _.toLowerCase)
  )(TipBodyRequest.apply)(TipBodyRequest.unapply)
  )

  implicit val tipAddFormat = Json.format[TipBodyRequest]
}