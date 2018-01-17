package controllers

import model.domain.{Tip, TipBodyRequest}
import org.scalatestplus.play.OneServerPerSuite
import play.api.http.Status._
import play.api.libs.json.Json
import play.api.libs.ws.{WSClient, WSRequest}

import scala.util.Random

/**
  * Created by d066419 on 06/07/16.
  */
class SanitySpec extends BaseSpec with OneServerPerSuite {

  import SanitySpec._

  private val wsClient = app.injector.instanceOf[WSClient]
  private val baseUrl = s"http://localhost:$port/tips"

  "Application" must {
    "create update list delete tips" in {
      val tipString = "use scala"
      val id: String = addTip(TipBodyRequest(tipString))
      val newTip: Tip = Tip(id, tipString, tenant)
      verifyGetTips(newTip)
      val updatedTip: Tip = newTip.copy(tip = "update tip")
      updateTip(updatedTip)
      verifyGetTips(updatedTip)
      delete(updatedTip.id)
      verifyTipDeletion(updatedTip)
    }
  }

  private def delete(id: String) = {
    whenReady(annotateHeaders(wsClient.url(s"$baseUrl/$id")).delete(), defaultTimeOut)(_.status mustEqual NO_CONTENT)
  }

  private def updateTip(updatedTip: Tip) = {
    whenReady(annotateHeaders(wsClient.url(s"$baseUrl/${updatedTip.id}")).put(Json.toJson(updatedTip)), defaultTimeOut) { res =>
      res.status mustEqual OK
    }
  }

  private def addTip(tipAddRequest: TipBodyRequest): String = {
    whenReady(annotateHeaders(wsClient.url(baseUrl)).post(Json.toJson(tipAddRequest)), defaultTimeOut) { response =>
      response.status mustEqual CREATED
      val maybeId: Option[String] = (response.json \ "id").asOpt[String]
      maybeId mustBe a[Some[_]]
      maybeId.get
    }
  }

  private def verifyGetTips(expectedTips: Tip*): Unit = assertOnList(tips => expectedTips.foreach(tips must contain(_)))

  private def verifyTipDeletion(tip: Tip) = assertOnList(dbTips => dbTips.find(_.id == tip.id) mustEqual None)

  private def assertOnList(assertion: List[Tip] => Unit) = whenReady(annotateHeaders(wsClient.url(baseUrl)).execute(), defaultTimeOut) { response =>
    response.status mustEqual OK
    assertion(response.json.as[List[Tip]])
  }


  private def annotateHeaders(request: WSRequest): WSRequest = {
    request.withHeaders(("hybris-tenant", tenant), ("hybris-scopes", "dummyScopes"))
  }
}

object SanitySpec {

  private val tenant: String = s"nike${Random.alphanumeric.take(5).toList.mkString}"
}
