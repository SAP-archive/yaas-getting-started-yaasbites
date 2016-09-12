package com.hybris.yaas.bites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hybris.yaas.bites.TipsRepo;		

@RestController
public class TipController {
    
	private final Logger LOG = LoggerFactory.getLogger(TipController.class);

    @Autowired
    private TipsRepo repo;

    @CrossOrigin() 
    @RequestMapping( value="{tenantFromURL}/ping", method=RequestMethod.GET )
    public ResponseEntity<String> ping( 
    		@RequestHeader(value="hybris-tenant", defaultValue="") String tenantFromHeader, 
    		@RequestHeader(value="hybris-scopes", defaultValue="") String scopesFromHeader,
    		@PathVariable String tenantFromURL
    		) {
    	 
    	String s= "-> In GET "+tenantFromURL+"/ping: "+
 			   "  tenantFromURLPath: '"+tenantFromURL+
 			"',   hybris-tenant from token: '"+tenantFromHeader+
 			"',   hybris-scopes from token: '"+scopesFromHeader+"'";
    	LOG.debug(s);
    	
    	return new ResponseEntity<String>(s, HttpStatus.OK); 
    }
    
    @CrossOrigin()
    @RequestMapping( value="/tips", method=RequestMethod.GET )
    public ResponseEntity<List<Tip>> getAllTipsForAllTenants( ) {
    	LOG.debug("-> In GET /tips ");
    	return new ResponseEntity<List<Tip>>( iterableToList( repo.findAll() ), HttpStatus.OK); 
    } 
    

    @CrossOrigin()
    @RequestMapping( value="/{tenant}/tips", method=RequestMethod.GET )
    public ResponseEntity<List<Tip>> getAllTips(@RequestHeader(value="hybris-tenant", defaultValue="") String tenantFromHeader, @PathVariable String tenant ) {
    	LOG.debug("-> In GET /tips Tenant: "+tenant);
		if (tenant==null )
			throw new NoTenantException();
    	return new ResponseEntity<List<Tip>>( iterableToList(repo.findByTenant( tenant )), HttpStatus.OK); 
    }
    
    @CrossOrigin()
    @RequestMapping( value="/{tenant}/tips", method=RequestMethod.POST )
    public ResponseEntity<Void> postTip(  @RequestHeader(value="hybris-tenant", defaultValue="") String tenantFromHeader, @PathVariable String tenant, @RequestBody Tip t, UriComponentsBuilder ucBuilder ) {  	
    	LOG.debug("-> In POST /tips Tenant: "+tenant);
		if (tenant==null)
			throw new NoTenantException();
    	t.setTenant(tenant);    
    	repo.save( t );
    	
    	HttpHeaders newHeaders = new HttpHeaders();
    	newHeaders.setLocation(ucBuilder.path("/tips/{id}").buildAndExpand(t.getId()).toUri());
		return new ResponseEntity<Void>(newHeaders, HttpStatus.CREATED);         
    }
    
    @CrossOrigin()
    @RequestMapping( value="/{tenant}/tips/{id}", method=RequestMethod.PUT )
    public ResponseEntity<String> putTip( @RequestHeader(value="hybris-tenant", defaultValue="") String tenantFromHeader, 
    		@PathVariable String tenant, 
    		@PathVariable Long id, @RequestBody Tip t ) {
    	LOG.debug("-> In PUT /tips  Tenant: "+tenant);
		if (tenant==null)
			throw new NoTenantException();
    	t.setTenant(tenant);
    
    	synchronized(repo){
    		if (repo.exists(id)){
    			Tip oldTip = repo.findOne(id);
    			oldTip.setTip(t.getTip());
    			repo.save(oldTip);
    			return new ResponseEntity<String>( HttpStatus.OK); 
    		}
    	}  	
    	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"message\":\"Tip "+id+" not found\"}");
    }
    
    @CrossOrigin()
    @RequestMapping( value="/{tenant}/tips/{id}", method=RequestMethod.DELETE )
    public ResponseEntity<String> deleteTip(  @RequestHeader(value="hybris-tenant", defaultValue="") String tenantFromHeader, @PathVariable String tenant, @PathVariable Long id ) {
    	LOG.debug("-> In DELETE /tips  Tenant: "+tenant);
		if (tenant==null)
			throw new NoTenantException();

    	synchronized(repo){
    		if (repo.exists(id) && repo.findOne(id).getTenant().equals(tenant)){
    			repo.delete(id);
    			return new ResponseEntity<String>(HttpStatus.NO_CONTENT); // If not found, could return HttpStatus.NOT_FOUND
    		}
    	}  	
       	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"message\":\"Tip "+id+" not found\"}");
    }
   
    @CrossOrigin()
    @RequestMapping( value="/tips", method=RequestMethod.DELETE )
    public ResponseEntity<String> sensitiveMethodForVIPsOnly(  
    		@RequestHeader(value="hybris-tenant", defaultValue="") String tenantFromHeader,
    		@RequestHeader(value="hybris-scopes", defaultValue="") String scopesFromHeader
    		) {
    	LOG.debug("-> In sensitiveMethodForVIPsOnly /tips  tenantFromHeader: "+tenantFromHeader +" scopesFromHeader:"+scopesFromHeader);
    	if (!scopesFromHeader.contains("hybris.tips_vip"))
    		throw new InsufficientScopeException();
		if (tenantFromHeader==null)
			throw new NoTenantException();

		repo.deleteAll();
		
    	return new ResponseEntity<String>(HttpStatus.OK); 
    }
    
	private static <T> List<T> iterableToList(Iterable<T> iterable) {
	  List<T> list = new ArrayList<>();
	  iterable.forEach(list::add);
	  return list;
	}
	
	// Convert a predefined exception to an HTTP Status code
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="No Tenant Defined")  // 400
	@ExceptionHandler(NoTenantException.class)
	public void anyName() {
	   // Spring transforms the exception to an Http Response
	}
	
	
	// Convert a predefined exception to an HTTP Status code
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Missing appropriate scope")  // 400
	@ExceptionHandler(InsufficientScopeException.class)
	public void anyOtherName() {
	   // Spring transforms the exception to an Http Response
	}
	
}

