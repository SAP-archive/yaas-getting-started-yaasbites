package model.document

import play.api.Configuration

/**
  * Created by d066419 on 11/07/16.
  */
case class DocServiceInvocationInfo(baseUri: String, tenantId: String, appId: String, docuRepoType: String) {
  val opsUrl = s"$baseUri/$tenantId/$appId/data/$docuRepoType"

  val deleteAllUrl = s"$baseUri/$tenantId/$appId"

  def getEntityOpsUrl(id: String) = s"$opsUrl/$id"
}

object DocServiceInvocationInfo {

  def create(configuration: Configuration): Option[DocServiceInvocationInfo] = for {
    baseUri <- configuration.getString("docuRepoURL")
    tenantId <- configuration.getString("projectIDAkaTenant")
    appId <- configuration.getString("yaaSClientsIdentifier")
    docuRepoType <- configuration.getString("docuRepoType")
  } yield DocServiceInvocationInfo(baseUri = baseUri, tenantId = tenantId, appId = appId, docuRepoType = docuRepoType)
}
