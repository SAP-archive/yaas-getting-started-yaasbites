package controllers

import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

import com.google.inject.Singleton
import model.Greetings
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, Controller}

import scala.concurrent.Future

/**
  * Created by d066419 on 06/07/16.
  */
@Singleton
class GreetingsController @Inject() extends Controller {
  private val atomicLong = new AtomicLong()

  def greet(name: String): Action[AnyContent] = Action.async {
    Future.successful(
      Ok(Json.toJson(Greetings(id = atomicLong.incrementAndGet(), content = s"Hello $name")))
    )
  }
}
