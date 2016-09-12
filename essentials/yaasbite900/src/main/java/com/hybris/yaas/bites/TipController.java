package com.hybris.yaas.bites;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

// Example of Hystrix in action - the  @EnableCircuitBreaker annotation enables hystrix protection.  
// See https://spring.io/guides/gs/circuit-breaker/ and https://www.javacodegeeks.com/2014/09/defend-your-application-with-hystrix.html for examples.
@EnableCircuitBreaker
@RestController
public class TipController {
    
	private final Logger LOG = LoggerFactory.getLogger(TipController.class);

	@Autowired
	private TipService tipService;
	 
	// Nothing special - standard Ping for sanity tests
	@CrossOrigin() 
    @RequestMapping( value="/ping", method=RequestMethod.GET )
    public ResponseEntity<String> ping( ) {   	 
    	String s= "-> In GET /ping";
    	LOG.debug(s);
    	return new ResponseEntity<String>(s, HttpStatus.OK); 
    }
	
	// This calls tipService.riskyMethod which *may* take unacceptably long to respond,  if TipService has a problem
    @CrossOrigin() 
    @RequestMapping( value="/riskyConnection", method=RequestMethod.GET )
    public ResponseEntity<String> riskyConnection( ) throws InterruptedException{
    	String s= "-> In GET /riskyConnection ";
    	LOG.debug(s);
    	
    	Instant t1 = Instant.now();
    	s += tipService.riskyMethod( );
    	Instant t2 = Instant.now();
    	s +=" Time taken: "+Duration.between(t1, t2).toMillis() +"ms";
    	
    	return new ResponseEntity<String>(s, HttpStatus.OK); 
    }
    
	// This calls tipService.riskyMethod which *may* take unacceptably long to respond, if TipService has a problem
    @CrossOrigin() 
    @RequestMapping( value="/veryRiskyConnection", method=RequestMethod.GET )
    public ResponseEntity<String> veryRiskyConnection( ) throws InterruptedException{
    	String s= "-> In GET /veryRiskyConnection ";
    	LOG.debug(s);
    	
    	Instant t1 = Instant.now();
    	s += tipService.veryRiskyMethod( );
    	Instant t2 = Instant.now();
    	s +=" Time taken: "+Duration.between(t1, t2).toMillis() +"ms";
    	
    	return new ResponseEntity<String>(s, HttpStatus.OK); 
    }
    
    // Toggle the problem in TipService
    @CrossOrigin() 
    @RequestMapping( value="/toggleProblem", method=RequestMethod.GET )
    public ResponseEntity<String> toggleProblem( ) throws InterruptedException{
    	String s = "-> In GET /toggleProblem ";
    	LOG.debug(s);
    	tipService.toggleProblem( );
    	s+= "Problems="+ tipService.hasProblem();    	
    	return new ResponseEntity<String>(s, HttpStatus.OK); 
    }  
	
}