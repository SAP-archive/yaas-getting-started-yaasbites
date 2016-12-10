package com.hybris.yaas.bites;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port=8080")
public class SanityTest {
	//YaaSBiteSnippetStart greetingTest
	private final String HOMEPAGE = "http://localhost:8080/greeting";	
	@Test(timeout = 120000) 
	public void siteIsAwakeAndWorking() throws Exception {	
		final TestRestTemplate restTemplate = new TestRestTemplate();
        Greeting greeting1 = restTemplate.getForObject(HOMEPAGE, Greeting.class);
        assertEquals(greeting1, new Greeting( "Greetings from Bayern, Most Honorable User!"));                
        Greeting greeting2  = restTemplate.getForObject(HOMEPAGE+"?name=Bod", Greeting.class);
        assertEquals(greeting2, new Greeting(  "Greetings from Bayern, Most Honorable Bod!"));   
	}
	//YaaSBiteSnippetEnd 
}
