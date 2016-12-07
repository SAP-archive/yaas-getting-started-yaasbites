package com.hybris.yaas.bites;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port=8080")
@PropertySource("classpath:application.properties")

public class SanityTest {
	@Autowired
    private PubSubClient psc;
	
	@Value("${yaaSClientsIdentifier}")
	private String clientIdentifier;
	
	private String aUniqueTopicName= UUID.randomUUID().toString();
	
	private String businessEvent1 = "{'Tip':'Do not eat fireworks', 'AuthorsEmail': 'bob.builder@sap.com'}";
	private String businessEvent2 = "{'Tip':'Do not eat haggis', 'AuthorsEmail': 'bob.builder@sap.com'}";
	int msSleepTime = 500;

	@Test
    public void pubsubHappyPath() throws Exception {		
		
		HttpStatus httpStatusForCreateTopic = psc.createTopic( aUniqueTopicName );
		assertEquals( HttpStatus.CREATED, httpStatusForCreateTopic );
		Thread.sleep(msSleepTime);

		HttpStatus httpStatusForPostMessage1 = psc.postMessage(  aUniqueTopicName, businessEvent1);
		assertEquals( HttpStatus.CREATED,  httpStatusForPostMessage1 );
		Thread.sleep(msSleepTime);
		
		HttpStatus httpStatusForPostMessage2 = psc.postMessage(  aUniqueTopicName, businessEvent2);
		assertEquals( HttpStatus.CREATED,  httpStatusForPostMessage2 );
		Thread.sleep(msSleepTime);
		
		Optional<PubSubJsonOutput> pubsubJsonOutput1 = psc.readMessage(  aUniqueTopicName);			
		assertEquals( pubsubJsonOutput1.get().getEvents().get(0).get("payload"), businessEvent1);
		Thread.sleep(msSleepTime);
		
		HttpStatus httpStatusForCommitMessage1 = psc.commitMessage(  aUniqueTopicName, pubsubJsonOutput1.get().getToken() );
		assertEquals( HttpStatus.OK, httpStatusForCommitMessage1 );
		Thread.sleep(msSleepTime);

		Optional<PubSubJsonOutput> pubsubJsonOutput2 = psc.readMessage(  aUniqueTopicName);			
		assertEquals( pubsubJsonOutput2.get().getEvents().get(0).get("payload"), businessEvent2);
		Thread.sleep(msSleepTime);
		
		HttpStatus httpStatusForCommitMessage2 = psc.commitMessage(  aUniqueTopicName, pubsubJsonOutput2.get().getToken() );
		assertEquals( HttpStatus.OK, httpStatusForCommitMessage2 );
		Thread.sleep(msSleepTime);

		HttpStatus httpStatusForDeleteMessage = psc.deleteTopic(  aUniqueTopicName );
		assertEquals( HttpStatus.ACCEPTED, httpStatusForDeleteMessage );
    }
}
