import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
val f = Future.successful(2)

val y: Future[Int] = for {
  x <- f
  if x % 2 != 0
} yield x

Await.result(y,Duration.Inf)

