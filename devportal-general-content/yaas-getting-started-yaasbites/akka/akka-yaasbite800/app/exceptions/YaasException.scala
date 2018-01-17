package exceptions

/**
  * Created by d066419 on 10/07/16.
  */
sealed trait YaasException extends Exception

case object NoTenantException extends YaasException

case object InsufficientScopesException extends YaasException

case object CallingYaaSServiceException extends YaasException

case class OperationFailed(cause: Throwable) extends YaasException

object OperationFailed {
  def apply(reason: String): OperationFailed = OperationFailed.apply(new RuntimeException(reason))
}
