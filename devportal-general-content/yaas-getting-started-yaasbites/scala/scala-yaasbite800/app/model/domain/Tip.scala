package model.domain

import play.api.libs.json.Json
import utils.commons.Helpers

/**
  * Created by d066419 on 06/07/16.
  */
case class Tip(id: String, tip: String, tenant: String)

object Tip {
  implicit val tipFormat = Json.format[Tip]

  def apply(tipBodyRequest: TipBodyRequest, tenant: String): Tip = Tip(id = Helpers.getUniqueId, tip = tipBodyRequest.tip, tenant = tenant)
}



