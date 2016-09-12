package controllers

import model.{Tip, TipBodyRequest}
import org.scalatestplus.play.OneServerPerSuite
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.WSClient

/**
  * Created by d066419 on 06/07/16.
  */
class SanitySpec extends BaseSpec with OneServerPerSuite {
  val wsClient = app.injector.instanceOf[WSClient]
  val baseUrl = s"http://localhost:$port/tips"
  //TODO find out a way to run functional tests with slick with minimal configuration
  "Application" ignore {
    "create update list delete tips" in {
      val tipString = "use scala"
      addTip(TipBodyRequest(tipString))
      verifyGetTips(Tip(1, tipString))
    }
  }

  private def addTip(tipAddRequest: TipBodyRequest): Unit = {
    whenReady(wsClient.url(baseUrl).withBody(Json.toJson(tipAddRequest)).execute(), defaultTimeOut) { response =>
      response.status mustEqual CREATED
    }
  }

  private def verifyGetTips(expectedTips: Tip*): Unit = {
    whenReady(wsClient.url(baseUrl).execute(), defaultTimeOut) { response =>
      response.status mustEqual OK
      response.json.as[List[Tip]] mustEqual expectedTips
    }
  }
}
