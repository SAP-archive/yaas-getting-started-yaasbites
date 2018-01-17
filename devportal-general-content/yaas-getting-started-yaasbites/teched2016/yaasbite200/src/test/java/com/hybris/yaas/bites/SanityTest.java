package com.hybris.yaas.bites;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port=8080")
public class SanityTest {

	private final String HOMEPAGE = "http://localhost:8080/tips";
	
	@Test(timeout = 60000) 
	public void siteIsAwakeAndWorking() throws Exception {		
	
		final TestRestTemplate restTemplate = new TestRestTemplate();
        Tip[] tips  = restTemplate.getForObject(HOMEPAGE, Tip[].class);
        assertEquals( 0, tips.length);      
		
		Tip newTip = new Tip(1, "Hello World!");
	    HttpEntity<Tip> newTipAsEntity = new HttpEntity<Tip>( newTip );
        restTemplate.postForEntity( HOMEPAGE, newTipAsEntity, Tip.class);    
        tips  = restTemplate.getForObject(HOMEPAGE, Tip[].class);
        assertEquals( newTip, tips[0]);        

        Tip newTip2 = new Tip(2, "Bonjour!");
	    newTipAsEntity = new HttpEntity<Tip>( newTip2 );
        restTemplate.postForEntity( HOMEPAGE, newTipAsEntity, Tip.class);    
        tips  = restTemplate.getForObject(HOMEPAGE, Tip[].class);
        assertEquals( newTip, tips[0]);        
        assertEquals( newTip2, tips[1]);        
        
        Tip updatedTip = new Tip(1, "Hello There World!");
        restTemplate.put( HOMEPAGE+"/1", updatedTip);     
        tips  = restTemplate.getForObject(HOMEPAGE, Tip[].class);
        assertEquals( updatedTip, tips[0]);        
        
        restTemplate.delete( HOMEPAGE+"/1" );     
        tips  = restTemplate.getForObject(HOMEPAGE, Tip[].class);
        assertEquals( 1, tips.length);  

        restTemplate.delete( HOMEPAGE+"/2" );     
        tips  = restTemplate.getForObject(HOMEPAGE, Tip[].class);
        assertEquals( 0, tips.length);  
}

}
