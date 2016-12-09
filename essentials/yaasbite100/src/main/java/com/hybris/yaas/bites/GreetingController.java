package com.hybris.yaas.bites;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//YaaSBiteSnippetStart greetingEndpoint
@RestController
public class GreetingController {

    private final String TEMPLATE = "Greetings from Bayern, Most Honorable %s!";
    private final Logger LOG = LoggerFactory.getLogger(GreetingController.class);
    private final AtomicLong counter = new AtomicLong();
    
    @RequestMapping( "/greeting" )
    public ResponseEntity<Greeting> greetings( @RequestParam( value="name", defaultValue="User") String name) {
    	LOG.debug("======= In /greetings with name parameter: "+ name );    	
    	return new ResponseEntity<Greeting>(new Greeting(counter.incrementAndGet(), 
    			String.format(TEMPLATE, name)), HttpStatus.OK); 
    }
}
//YaaSBiteSnippetEnd

