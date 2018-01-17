package com.hybris.yaas.bites;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
		
@RestController
public class TipController {
    
	private final Logger LOG = LoggerFactory.getLogger(TipController.class);

    @Autowired
    private TipsRepo repo;
    
    @RequestMapping( value="/ping", method=RequestMethod.GET )
    public ResponseEntity<String> ping(  ) {
    	LOG.debug("-> In GET /ping");
    	return new ResponseEntity<String>( "Pong", HttpStatus.OK); 
    }
     
    @CrossOrigin() 
    @RequestMapping( value="/tips", method=RequestMethod.GET )
    public ResponseEntity<List<Tip>> getAllTips( ) {
    	LOG.debug("-> In GET /tips");
    	return new ResponseEntity<List<Tip>>( iterableToList(repo.findAll()), HttpStatus.OK); 
    }
    
    @RequestMapping( value="/tips", method=RequestMethod.POST )
    public ResponseEntity<Void> postTip(  @RequestBody Tip t, UriComponentsBuilder ucBuilder ) {  	
    	LOG.debug("-> In POST /tips");
    	repo.save( t );
    	HttpHeaders headers = new HttpHeaders();
    	headers.setLocation(ucBuilder.path("/tips/{id}").buildAndExpand(t.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);         
    }
    
    @RequestMapping( value="/tips/{id}", method=RequestMethod.PUT )
    public ResponseEntity<String> putTip(  @PathVariable Long id, @RequestBody Tip t ) {
    	LOG.debug("-> In PUT /tips ");

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
    
    @RequestMapping( value="/tips/{id}", method=RequestMethod.DELETE )
    public ResponseEntity<String> deleteTip( @PathVariable Long id ) {
    	LOG.debug("-> In DELETE /tips");
    	synchronized(repo){
    		if (repo.exists(id)){
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
