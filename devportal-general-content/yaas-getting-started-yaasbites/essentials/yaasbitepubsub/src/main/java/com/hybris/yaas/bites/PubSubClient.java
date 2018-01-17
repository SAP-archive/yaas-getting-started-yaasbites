package com.hybris.yaas.bites;

import java.util.Map;
import java.util.Optional;

import javax.annotation.ManagedBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

/**
 * A class to provide simple access to the PubSub YaaS Service, via Java.
 * https://devportal.yaas.io/services/pubsub/latest/
 */
@ManagedBean
@PropertySource("classpath:application.properties")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PubSubClient {
	
	@Value("${pubsubURL}")
	private String pubsubURI;
	@Value("${oauthURL}")
	private String oauthURI;
	@Value("${yaaSClientsIdentifier}")
    private String yclientIdentifier;
	@Value("${yaaSClientsClient_ID}")
	private String clientId;
	@Value("${yaaSClientsClient_Secret}")
	private String clientSecret;
			
	private String token;
	private String endpoint;
	private String body;
	private Class expectedClass;

	public HttpStatus createTopic(String topic) {
		ResponseEntity<Map> response = 
			clean().
			endpoint(pubsubURI + "/topics").
			token(getOAuthToken()).
			body("{\"eventType\": \"" + topic + "\" }").
			expects(Map.class).
			post();
		return response.getStatusCode();
	}

	public HttpStatus deleteTopic(String topic) {
		ResponseEntity<String> response =
			clean().
			endpoint(pubsubURI + "/topics/" + yclientIdentifier + "/" + topic).
			token(getOAuthToken()).
			expects(String.class).
			delete();
		return response.getStatusCode();
	}

	public HttpStatus postMessage( String topic, String message) {
		ResponseEntity<String> response = 
			clean().
			endpoint(pubsubURI + "/topics/" + yclientIdentifier + "/" + topic + "/publish").
			body("{\"payload\": \"" + message + "\" }").
			token(getOAuthToken()).
			expects(String.class).
			post();
		return response.getStatusCode();
	}

	public Optional<PubSubJsonOutput> readMessage( String topic) {
		ResponseEntity<String> response = 
			clean().
			endpoint(pubsubURI + "/topics/" + yclientIdentifier + "/" + topic + "/read").
			token(getOAuthToken()).
			expects(String.class).
			post();

		if (response.getStatusCode() == HttpStatus.NO_CONTENT)
			return Optional.empty();
		Gson g = new Gson();
		PubSubJsonOutput o = g.fromJson(response.getBody(), PubSubJsonOutput.class);
		return Optional.of(o);
	}

	public HttpStatus commitMessage( String topic, String pubsubMessageId) {
		ResponseEntity<String> response = 
			clean().
			endpoint(pubsubURI + "/topics/" + yclientIdentifier + "/" + topic + "/commit").
			body("{\"token\": \"" + pubsubMessageId + "\" }").
			token(getOAuthToken()).
			expects(String.class).
			post();
		return response.getStatusCode();
	}

	private ResponseEntity post() {
		HttpEntity<Object> request = new HttpEntity<>(body, headerWithToken(token));
		ResponseEntity<Map<String,String>> response = new RestTemplate().exchange(endpoint, HttpMethod.POST, request, expectedClass);
		return response;
	}

	private ResponseEntity delete() {
		HttpEntity<Object> request = new HttpEntity<>(headerWithToken(token));
		ResponseEntity<String> response = new RestTemplate().exchange(endpoint, HttpMethod.DELETE, request, expectedClass);
		return response;
	}
	
	private PubSubClient token(String token) {
		this.token = token;
		return this;
	}

	private PubSubClient endpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	private PubSubClient body(String body) {
		this.body = body;
		return this;
	}

	private PubSubClient expects(Class c) {
		expectedClass = c;
		return this;
	}

	private PubSubClient clean() {
		token = "";
		endpoint = "";
		body = "";
		return this;
	}

	private HttpHeaders headerWithToken(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "application/json");
		headers.add("Authorization", "Bearer " + token);
		return headers;
	}
   
	private String getOAuthToken(){   	
		HttpHeaders headers = new HttpHeaders();   	
		headers.add("content-type", "application/x-www-form-urlencoded" );  	
		String body = 
			"grant_type=client_credentials"+
			"&client_id="+clientId+
			"&client_secret="+clientSecret;
		HttpEntity<Object> request = new HttpEntity<>( body, headers );
		Map<String,String> tokenMap = new RestTemplate().postForObject(oauthURI, request, Map.class );  			
		return tokenMap.get("access_token");
    }	
}
