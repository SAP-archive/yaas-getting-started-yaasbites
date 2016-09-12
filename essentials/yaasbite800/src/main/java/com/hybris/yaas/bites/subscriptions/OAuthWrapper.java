package com.hybris.yaas.bites.subscriptions;

import java.util.Map;
import java.util.Optional;

import javax.annotation.ManagedBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hybris.yaas.bites.exceptions.CallingYaaSServiceException;

@ManagedBean
@PropertySource("classpath:application.properties")
public class OAuthWrapper { 

	@Value("${oauthURL}")
	private String URI;
	@Value("${projectIDAkaTenant}")
    private String tenant; 
	@Value("${yaaSClientsIdentifier}")
    private String appId;
	@Value("${docuRepoType}")
    private String type;
	@Value("${yaaSClientsIClient_ID}")
	private String clientId;
	@Value("${yaaSClientsClient_Secret}")
	private String clientSecret;
	@Value("${docuRepoScopes}")	
	private String scopes;
	
    private String grantType = "client_credentials";
    
    public OAuthWrapper( ){
    }
    
    public OAuthWrapper grantType( String grantType ){
    	this.grantType = grantType;
    	return this;
    }
    public OAuthWrapper clientId( String clientId ){
    	this.clientId = clientId;
    	return this;
    }
    public OAuthWrapper clientSecret( String clientSecret ){
    	this.clientSecret = clientSecret;
    	return this;
    }
    public OAuthWrapper tenant( String tenant ){
    	this.tenant = tenant;
    	return this;
    }   
    public OAuthWrapper scope( String scope ){
    	this.scopes = scope;
    	return this;
    }   
    public OAuthWrapper build(  ){
    	this.scopes = "hybris.tenant"+"="+tenant+" "+scopes; 	
    	return this;
    }
    
    public Optional<Map<String,String>> getToken(){   	
    	try {
			HttpHeaders headers = new HttpHeaders();   	
			headers.add("content-type", "application/x-www-form-urlencoded" );  	
			RestTemplate restTemplate = new RestTemplate();
			String body = 
					"grant_type="+grantType+
					"&client_id="+clientId+
					"&client_secret="+clientSecret+
					"&scope="+scopes;
			HttpEntity<Object> request = new HttpEntity<>( body, headers );
			Map<String,String> tokenMap = restTemplate.postForObject(URI, request, Map.class );  			
			return Optional.of(tokenMap);
		} catch (RestClientException e) {
			throw new CallingYaaSServiceException();
		}
    } 	
}
