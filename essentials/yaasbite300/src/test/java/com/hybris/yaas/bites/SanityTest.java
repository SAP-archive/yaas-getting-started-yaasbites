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

	private final String TENANT1="Adidas";
	private final String TENANT2="NIke";	
	private final String TENANT1ENDPOINT = "http://localhost:8080/"+TENANT1+"/tips";
	private final String TENANT2ENDPOINT = "http://localhost:8080/"+TENANT2+"/tips";
	
	@Test(timeout = 60000) 
	public void siteIsAwakeAndWorking() throws Exception {		
	
		final TestRestTemplate restTemplate = new TestRestTemplate();
        Tip[] tips  = restTemplate.getForObject(TENANT1ENDPOINT, Tip[].class);
        assertEquals( 0, tips.length);     
        
		Tip newTip1 = new Tip(1, "Hello World!",TENANT1);
	    HttpEntity<Tip> newTipAsEntity = new HttpEntity<Tip>( newTip1 );
        restTemplate.postForEntity( TENANT1ENDPOINT, newTipAsEntity, Tip.class);    
        tips  = restTemplate.getForObject(TENANT1ENDPOINT, Tip[].class);
        
        assertEquals( 1, tips.length);
        assertEquals( newTip1, tips[0]);        

        Tip newTip2 = new Tip(2, "Bonjour!",TENANT1);
	    newTipAsEntity = new HttpEntity<Tip>( newTip2 );
        restTemplate.postForEntity( TENANT1ENDPOINT, newTipAsEntity, Tip.class);    
        tips  = restTemplate.getForObject(TENANT1ENDPOINT, Tip[].class);
        assertEquals( 2, tips.length);
        assertEquals( newTip1, tips[0]);        
        assertEquals( newTip2, tips[1]);        
           
   		Tip newTip3= new Tip(3, "Hello World!",TENANT2);
   	    newTipAsEntity = new HttpEntity<Tip>( newTip3 );
        restTemplate.postForEntity( TENANT2ENDPOINT, newTipAsEntity, Tip.class);    
        tips  = restTemplate.getForObject(TENANT2ENDPOINT, Tip[].class);
        assertEquals( 1, tips.length);
        assertEquals( newTip3, tips[0]);       
         
        Tip updatedTip = new Tip(1, "Hello There World!","Adidas");
        restTemplate.put( TENANT1ENDPOINT+"/1", updatedTip);     
        tips  = restTemplate.getForObject(TENANT1ENDPOINT, Tip[].class);
        assertEquals( updatedTip, tips[0]);        
        
        restTemplate.delete( TENANT1ENDPOINT+"/1" );     
        tips  = restTemplate.getForObject(TENANT1ENDPOINT, Tip[].class);
        assertEquals( 1, tips.length);  

        restTemplate.delete( TENANT1ENDPOINT+"/2" );     
        tips  = restTemplate.getForObject(TENANT1ENDPOINT, Tip[].class);
        assertEquals( 0, tips.length);  
	}

}
