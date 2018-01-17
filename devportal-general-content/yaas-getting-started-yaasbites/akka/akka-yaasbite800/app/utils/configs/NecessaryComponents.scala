package utils.configs

import javax.inject.{Inject, Singleton}

import model.document.DocServiceInvocationInfo
import model.oauth.OauthRequestInfo
import play.api.Configuration
import play.api.libs.ws.WSClient

/**
  * Created by d066419 on 15/07/16.
  */
@Singleton
class NecessaryComponents @Inject()(wSClient: WSClient, configuration: Configuration) {
  val docServiceInvocationInfo = DocServiceInvocationInfo.create(configuration).getOrElse(throw new IllegalStateException("wrong configuration"))
  val maybeOAuthUrl: Option[String] = configuration.getString("oauthURL")
  val maybeOauthRequestInfo: Option[OauthRequestInfo] = OauthRequestInfo.create(configuration)
}
