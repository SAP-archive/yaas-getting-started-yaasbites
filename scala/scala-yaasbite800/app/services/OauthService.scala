package services

import javax.inject.{Inject, Singleton}

import exceptions.{CallingYaaSServiceException, YaasException}
import model.oauth.OauthRequestInfo
import play.api.Configuration
import play.api.libs.ws.{WSClient, WSResponse}
import utils.commons.Helpers._

import scala.concurrent.{ExecutionContext, Future}

/**
  * Created by d066419 on 10/07/16.
  */
@Singleton
class OauthService @Inject()(configuration: Configuration, wSClient: WSClient) {
  val maybeOAuthUrl: Option[String] = configuration.getString("oauthURL")
  val maybeOauthRequestInfo: Option[OauthRequestInfo] = OauthRequestInfo.create(configuration)


  def getToken(implicit executionContext: ExecutionContext): Future[Either[YaasException, String]] = (for {
    url: String <- maybeOAuthUrl
    oauthInfo: OauthRequestInfo <- maybeOauthRequestInfo
  } yield {
    httpCall(url, oauthInfo) map extractToken
  }).getOrElse(Future.successful(Left(CallingYaaSServiceException)))

  private def httpCall(url: String, oauthInfo: OauthRequestInfo)(implicit executionContext: ExecutionContext): Future[WSResponse] = {
    wSClient.url(url = url)
      .withHeaders(("content-type", "application/x-www-form-urlencoded"))
      .post(oauthInfo.formBody)
  }

  private def extractToken(wSResponse: WSResponse): Either[YaasException, String] = {
    toEither((wSResponse.json \ "access_token").asOpt[String])(CallingYaaSServiceException)
  }
}
