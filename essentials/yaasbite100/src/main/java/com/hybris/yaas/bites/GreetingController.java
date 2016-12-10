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
    @RequestMapping( "/greeting" )
    public ResponseEntity<Greeting> greetings( @RequestParam( value="name", defaultValue="User") String name) {
    	LOG.debug("======= In /greetings with the name : "+ name );    	
    	return new ResponseEntity<Greeting>(new Greeting( String.format(TEMPLATE, name) ), HttpStatus.OK); 
    }
}
//YaaSBiteSnippetEnd

