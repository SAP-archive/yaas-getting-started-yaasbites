package com.hybris.yaas.bites;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
		
@RestController
public class TipController {
    
	private final Logger LOG = LoggerFactory.getLogger(TipController.class);

    @Autowired
    private TipsRepo repo;

    @CrossOrigin()
    @RequestMapping( value="/ping", method=RequestMethod.GET )
    public ResponseEntity<String> ping(  ) {
    	LOG.debug("-> In GET /ping");
    	return new ResponseEntity<String>( "Pong", HttpStatus.OK); 
    }
    
    @RequestMapping( value="/tips", method=RequestMethod.GET )
    public ResponseEntity<List<Tip>> getAllTipsForAllTenants( ) {
    	LOG.debug("-> In GET /tips ");
    	return new ResponseEntity<List<Tip>>( iterableToList( repo.findAll() ), HttpStatus.OK); 
    } 
    
    @RequestMapping( value="/{tenant}/tips", method=RequestMethod.GET )
    public ResponseEntity<List<Tip>> getAllTipsForATenant( @PathVariable String tenant ) {
    	LOG.debug("-> In GET tenant/tips "+tenant);
    	return new ResponseEntity<List<Tip>>( repo.findByTenant( tenant ), HttpStatus.OK); 
    }
    
    @RequestMapping( value="/{tenant}/tips", method=RequestMethod.POST )
    public ResponseEntity<Void> postTip( @PathVariable String tenant, @RequestBody Tip t, UriComponentsBuilder ucBuilder ) {  	
    	LOG.debug("-> In POST tenant/tips "+tenant);
    	t.setTenant(tenant);
    	repo.save( t );
    	HttpHeaders newHeaders = new HttpHeaders();
    	newHeaders.setLocation(ucBuilder.path("/tips/{id}").buildAndExpand(t.getId()).toUri());
		return new ResponseEntity<Void>(newHeaders, HttpStatus.CREATED);         
    }
    
    @RequestMapping( value="/{tenant}/tips/{id}", method=RequestMethod.PUT )
    public ResponseEntity<String> putTip( @PathVariable String tenant, @PathVariable Long id, @RequestBody Tip t ) {
    	LOG.debug("-> In PUT tenant/tips "+tenant);
    	synchronized(repo){
    		if (repo.exists(id)){
    			Tip oldTip = repo.findOne(id);
				if (oldTip.getTenant().equals(tenant)){
					oldTip.setTip(t.getTip());
    				repo.save(oldTip);
    				return new ResponseEntity<String>( HttpStatus.OK); 
				}				
    		}
    	}  	
    	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"message\":\"Tip "+id+" not found\"}");
    }
    
    @RequestMapping( value="/{tenant}/tips/{id}", method=RequestMethod.DELETE )
    public ResponseEntity<String> deleteTip( @PathVariable String tenant, @PathVariable Long id ) {
    	LOG.debug("-> In DELETE tenant/tips "+tenant);
    	
    	synchronized(repo){
			Tip tip = repo.findOne(id);
			if (tip!=null){
				if (tip.getTenant().equals(tenant))
					repo.delete(id);
				return new ResponseEntity<String>(HttpStatus.NO_CONTENT); // If not found, could return HttpStatus.NOT_FOUND
			}
		}  	
    	return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("{\"message\":\"Tip "+id+" not found\"}");
    }
   
	private static <T> List<T> iterableToList(Iterable<T> iterable) {
	  List<T> list = new ArrayList<>();
	  iterable.forEach(list::add);
	  return list;
	}
	
}
