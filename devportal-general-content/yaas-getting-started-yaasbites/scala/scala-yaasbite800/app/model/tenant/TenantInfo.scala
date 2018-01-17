package model.tenant

import play.api.mvc.Headers
import utils.configs.YaasConstants._

/**
  * Created by d066419 on 08/07/16.
  */
case class TenantInfo(tenant: String, scopes: Option[String])

object TenantInfo {
  def create(headers: Headers): Option[TenantInfo] = for {
    tenant <- headers.get(tenantHeaderName)
  } yield {
    TenantInfo(tenant = tenant, scopes = headers.get(scopesHeaderName))
  }
}
