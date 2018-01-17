package controllers

import org.scalatest.concurrent.PatienceConfiguration.Timeout
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Minutes, Span}
import org.scalatest.{MustMatchers, ParallelTestExecution, WordSpec}

/**
  * Created by d066419 on 06/07/16.
  */
trait BaseSpec extends WordSpec with ScalaFutures with MustMatchers with ParallelTestExecution {
  val defaultTimeOut = Timeout(Span(1, Minutes))
}
