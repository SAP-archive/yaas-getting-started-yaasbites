package com.hybris.yaas.bites;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.Instant;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port=8080")

public class SanityTest {

	private final String HOMEPAGE = "http://localhost:8080/";
	
	@Test(timeout = 10000) 
	public void siteIsAwakeAndWorking() throws Exception {				
		final TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>( "{}" );
		
		String o = restTemplate.exchange(HOMEPAGE+"/ping", HttpMethod.GET, entity, String.class).getBody();
        assertTrue( o.equals("-> In GET /ping") );
    }

	@Test(timeout = 10000) 
	public void hystrixIsPreventingLongDelays() throws Exception {				
		final TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<String> entity = new HttpEntity<String>( "{}" );
		
		Instant t1 = Instant.now();
		restTemplate.exchange(HOMEPAGE+"/riskyConnection", HttpMethod.GET, entity, String.class).getBody();
		Instant t2 = Instant.now();
		long durationMS = Duration.between(t1, t2).toMillis(); 	
    	assertTrue( durationMS < TipService.GOOD_USER_EXPERIENCE_SLEEP_MS);
    	 	
		restTemplate.exchange(HOMEPAGE+"/toggleProblem", HttpMethod.GET, entity, String.class).getBody();
		
		t1 = Instant.now();
		restTemplate.exchange(HOMEPAGE+"/riskyConnection", HttpMethod.GET, entity, String.class).getBody();
        t2 = Instant.now();
    	durationMS = Duration.between(t1, t2).toMillis(); 	
    	assertTrue( durationMS < TipService.BAD_USER_EXPERIENCE_SLEEP_MS);
    }

}
