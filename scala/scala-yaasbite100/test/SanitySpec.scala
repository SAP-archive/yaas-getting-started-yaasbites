import model.Greetings
import org.scalatestplus.play.OneServerPerSuite
import play.api.libs.ws.{WSClient, WSRequest}

/**
  * Created by d066419 on 06/07/16.
  */
class SanitySpec extends BaseSpec with OneServerPerSuite {
  val wsClient = app.injector.instanceOf[WSClient]
  val baseUrl = s"http://localhost:$port"

  "Application" must {
    "get correct response for no parameters" in {
      callNVerify(Greetings(1, "Hello World!"))
      callNVerify(Greetings(2, "Hello Gaurav"), Some("Gaurav"))
    }
  }

  private def callNVerify(expected: Greetings, name: Option[String] = None): Unit = {
    val request: WSRequest = name.foldLeft(wsClient.url(s"$baseUrl/greeting")) { case (acc, x) => acc.withQueryString(("name", x)) }
    whenReady(request.execute(), defaultTimeOut) { response =>
      response.json.as[Greetings] mustEqual expected
      response.status mustEqual 200
    }
  }
}
