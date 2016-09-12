package actors

import akka.actor.{Actor, ActorLogging, Props}
import akka.pattern._
import model.messages.BaseMessage
import model.oauth.OauthRequestInfo
import play.api.libs.ws.{WSClient, WSResponse}
import utils.configs.NecessaryComponents

import scala.concurrent.{ExecutionContext, Future}
import scala.util.control.NonFatal

/**
  * Created by d066419 on 15/07/16.
  */
class OAuthActor[T <: BaseMessage](wSClient: WSClient, necessaryComponents: NecessaryComponents) extends Actor with ActorLogging {

  import OAuthActor._
  import necessaryComponents._

  private implicit val executionContext: ExecutionContext = context.dispatcher

  private def getToken(url: String, oauthInfo: OauthRequestInfo, documentOps: T): Future[TokenResponse[T]] = {
    def httpCall: Future[WSResponse] = {
      wSClient.url(url = url)
        .withHeaders(("content-type", "application/x-www-form-urlencoded"))
        .post(oauthInfo.formBody)
    }

    for {
      response <- httpCall
    } yield {
      extractToken(response) map (token => TokenCreated(documentOps, token)) getOrElse TokenCreationFailed(documentOps, TokenCreationError("no token in request"))
    }
  }

  override def receive: Receive = {
    case CreateToken(documentOps: T) =>
      val eventualTokenResponse: Future[TokenResponse[T]] = (for {
        url <- maybeOAuthUrl
        oauthInfo <- maybeOauthRequestInfo
      } yield getToken(url, oauthInfo, documentOps)).getOrElse(Future.successful(TokenCreationFailed(documentOps, TokenCreationError("invalid configuration"))))

      eventualTokenResponse.recover {
        case NonFatal(e) =>
          log.error(e, "error in token creation")
          TokenCreationFailed(documentOps, error = TokenCreationError(Option(e.getLocalizedMessage).getOrElse("unknown error")))
      } pipeTo sender()
  }
}

object OAuthActor {
  def props[T <: BaseMessage](wSClient: WSClient, necessaryComponents: NecessaryComponents): Props = Props(new OAuthActor[T](wSClient = wSClient, necessaryComponents))

  sealed trait TokenExchange[T <: BaseMessage] {
    def documentOps: T
  }

  case class CreateToken[T <: BaseMessage](documentOps: T) extends TokenExchange[T]

  sealed trait TokenResponse[T <: BaseMessage] extends TokenExchange[T]

  case class TokenCreated[T <: BaseMessage](documentOps: T, token: String) extends TokenResponse[T]

  case class TokenCreationError(error: String)

  case class TokenCreationFailed[T <: BaseMessage](documentOps: T, error: TokenCreationError) extends TokenResponse[T]

  private def extractToken(wSResponse: WSResponse): Option[String] = {
    (wSResponse.json \ "access_token").asOpt[String]
  }


}
