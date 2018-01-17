package model.oauth

import play.api.Configuration

/**
  * Created by d066419 on 10/07/16.
  */
case class OauthRequestInfo(clientId: String, clientSecret: String, scopes: String, grantType: String) {
  def formBody = Map(
    "grant_type" -> Seq(grantType),
    "client_id" -> Seq(clientId),
    "client_secret" -> Seq(clientSecret),
    "scope" -> Seq(scopes)
  )
}

object OauthRequestInfo {
  def create(configuration: Configuration) = for {
    confClientId <- configuration.getString("yaaSClientsIClient_ID")
    confClientSecret <- configuration.getString("yaaSClientsClient_Secret")
    scopes <- configuration.getString("docuRepoScopes")
  } yield {
    OauthRequestInfo(clientId = confClientId, clientSecret = confClientSecret, scopes = scopes, grantType = "client_credentials")
  }
}
