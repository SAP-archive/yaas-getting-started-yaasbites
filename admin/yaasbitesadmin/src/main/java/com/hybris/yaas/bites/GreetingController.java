package com.hybris.yaas.bites;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	public static final String TEMPLATE = "Greetings from YaaS! I am a RESTful web service registered in YaaS.";
	public static final String SECUREDTEMPLATE = "Greetings from YaaS! I am a secured endpoint in a  RESTful web service registered in YaaS.";
	public static final String SECUREDBONJOUR = "Bonjour from YaaS! I am a secured endpoint in a  RESTful web service registered in YaaS.";
    private final Logger LOG = LoggerFactory.getLogger(GreetingController.class);
    
    @CrossOrigin()
    @RequestMapping( "/greetings" )
    public ResponseEntity<Greeting> greetings() {
    	LOG.debug("======= In /greetings" );    	
    	return new ResponseEntity<Greeting>(new Greeting(TEMPLATE), HttpStatus.OK); 
    }
    
    @CrossOrigin()
    @RequestMapping( "/securedgreetings" )
    public ResponseEntity<Greeting> securedgreetings( ) {
    	LOG.debug("======= In /securedgreetings" );    	
    	return new ResponseEntity<Greeting>(new Greeting( SECUREDTEMPLATE), HttpStatus.OK); 
    }
    
    @CrossOrigin()
    @RequestMapping( value="/securedbonjour" )
    public ResponseEntity<Greeting> securedbonjour(  
    		@RequestHeader(value="hybris-tenant", defaultValue="") String tenantFromHeader,
    		@RequestHeader(value="hybris-scopes", defaultValue="") String scopesFromHeader
    		) {
    	LOG.debug("-> In securedbonjour  tenantFromHeader: "+tenantFromHeader +" scopesFromHeader:"+scopesFromHeader);
    	if (!scopesFromHeader.contains("hybris.yaasbites_accesstobonjouryaas"))
    		throw new InsufficientScopeException();
		if (tenantFromHeader==null)
			throw new NoTenantException();

    	return new ResponseEntity<Greeting>(new Greeting( SECUREDBONJOUR), HttpStatus.OK); 
    }
    
}
