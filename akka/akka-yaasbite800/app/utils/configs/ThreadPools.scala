package utils.configs

import scala.concurrent.ExecutionContext

/**
  * Created by d066419 on 06/07/16.
  */
object ThreadPools {
  implicit val appExecContext: ExecutionContext = play.api.libs.concurrent.Execution.Implicits.defaultContext
}
