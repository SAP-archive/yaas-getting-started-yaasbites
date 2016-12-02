package com.hybris.yaas.bites;

import java.util.HashMap;
import java.util.List;

/**
 * A Java Class to match the JSON response returned by the PubSub Service's endpoint: /topics /{topicOwnerClient}/{eventType} /read POST, as described in its raml file
 * https://devportal.yaas.io/services/pubsub/v1/api.raml 
 * and in a more readable format at https://devportal.yaas.io/services/pubsub/latest/apiconsole.html
 * 
 * The JSON example mentioned in the links above is the following:
 * {
    "events": [
        {
            "id": "067e6162-3b6f-4ae2-a171-2470b63dff00",
            "createdAt": 1078884319047,
            "tenant": "myExampleShop",
            "sourceClient": "order",
            "eventType": "order-created",
            "payload": "{ \"orderId\": \"123\" }"
        }
    ],
    "token": "someTokenValue"
}
 * Providing a matching Java class, PubSubJsonOutput, enables Jersey to automatically populate this class from JSON
 */

public class PubSubJsonOutput {
	private String token;
	private List<HashMap<String, String>> events;
	public List<HashMap<String, String>> getEvents() {
		return events;
	}
	public String getToken() {
		return token;
	}
}