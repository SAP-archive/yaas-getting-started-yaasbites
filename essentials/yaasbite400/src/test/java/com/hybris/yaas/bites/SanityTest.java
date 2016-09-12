package com.hybris.yaas.bites;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port=8080")
public class SanityTest {

	private final String TENANT="ADIDAS";
	private final String HOMEPAGE = "http://localhost:8080/";
	private final String TENANTENDPOINT = HOMEPAGE+TENANT+"/tips";
	private HttpHeaders headers;
	
	@Before
	public void setUpTenantInHeadaer(){
		headers = new HttpHeaders();
		headers.add("hybris-tenant", TENANT);
	}

	@Test(timeout = 60000) 
	public void siteIsAwakeAndWorking() throws Exception {		
		
		final TestRestTemplate restTemplate = new TestRestTemplate();
		HttpEntity<Tip> emptyEntityWithTenantHeader = new HttpEntity<Tip>( headers );
        Object o = restTemplate.exchange(TENANTENDPOINT, HttpMethod.GET, emptyEntityWithTenantHeader,Object.class).getBody();
        Tip[] tips = restTemplate.exchange(TENANTENDPOINT, HttpMethod.GET, emptyEntityWithTenantHeader, Tip[].class).getBody();
        assertTrue( "Should have 0 tips", Arrays.asList(tips).size() == 0);
        
		Tip newTip = new Tip(1, "Hello World!",TENANT);
        
		HttpEntity<Tip> newTipAsEntity = new HttpEntity<Tip>( newTip, headers );
        restTemplate.postForEntity(TENANTENDPOINT, newTipAsEntity, Object.class);
//		restTemplate.put(TENANTENDPOINT, newTipAsEntity);
		
		tips = restTemplate.exchange(TENANTENDPOINT, HttpMethod.GET, emptyEntityWithTenantHeader, Tip[].class).getBody();       
        assertTrue( "Should have 1 tips", Arrays.asList(tips).size() == 1);        
        assertTrue( "Tip should be what we just posted", newTip.equals( tips[0]) );    

        Tip updatedTip = new Tip(1, "Updated tip",TENANT);    
        final HttpEntity<Tip> updatedTipAsEntity = new HttpEntity<Tip>( updatedTip, headers );  
        restTemplate.put(TENANTENDPOINT, updatedTipAsEntity );
        
        tips = restTemplate.exchange(TENANTENDPOINT, HttpMethod.GET, emptyEntityWithTenantHeader, Tip[].class).getBody();       
        assertTrue( "Should have 1 tips", Arrays.asList(tips).size() == 1);        
     //   assertTrue( "Tip should be what we just updated", updatedTip.equals( tips[0]) );    
        
        restTemplate.exchange(TENANTENDPOINT+"/1", HttpMethod.DELETE, emptyEntityWithTenantHeader, String.class);
        tips = restTemplate.exchange(TENANTENDPOINT, HttpMethod.GET, emptyEntityWithTenantHeader, Tip[].class).getBody();       
        assertTrue( "Should have 0 tips", Arrays.asList(tips).size() == 0);        
        
    }

}
