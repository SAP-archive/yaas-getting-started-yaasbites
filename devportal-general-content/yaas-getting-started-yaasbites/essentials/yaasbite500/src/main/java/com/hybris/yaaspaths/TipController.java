package com.hybris.yaaspaths;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
	
@RestController
public class TipController {
    
	private final Logger LOG = LoggerFactory.getLogger(TipController.class);

    @Autowired
    private TipsRepo repo;

    @RequestMapping( value="/tips", method=RequestMethod.GET )
    public ResponseEntity<List<Tip>> getAllTips(@RequestHeader Map<String,String> headers ) {   

    	YaasAwareParameters yap = new YaasAwareParameters(headers);
    	LOG.debug("-> In GET /tips "+yap.toString());
    	
    	return new ResponseEntity<List<Tip>>( iterableToList(repo.findAll()), HttpStatus.OK); 
    }
    
    @RequestMapping( value="/tips", method=RequestMethod.POST )
    public ResponseEntity<Void> postTip( @RequestHeader Map<String,String> headers,  @RequestBody Tip t, UriComponentsBuilder ucBuilder ) {  	
    	YaasAwareParameters yap = new YaasAwareParameters(headers);
    	LOG.debug("-> In POST /tips "+yap); 	
    	repo.save( t );
    	
    	HttpHeaders newHeaders = new HttpHeaders();
    	newHeaders.setLocation(ucBuilder.path("/tips/{id}").buildAndExpand(t.getId()).toUri());
		return new ResponseEntity<Void>(newHeaders, HttpStatus.CREATED);  
    }
    
    @RequestMapping( value="/tips/{id}", method=RequestMethod.PUT )
    public ResponseEntity<Tip> putTip( @RequestHeader Map<String,String> headers, @PathVariable Long id, @RequestBody Tip t ) {
    	YaasAwareParameters yap = new YaasAwareParameters(headers);
    	LOG.debug("-> In PUT /tips "+yap);
    	
    	synchronized(repo){
    		if (repo.exists(id)){
    			Tip oldTip = repo.findOne(id);
    			oldTip.setTip(t.getTip());
    			repo.save(oldTip);
    			return new ResponseEntity<Tip>(t, HttpStatus.OK); 
    		}
    	}  	
    	return new ResponseEntity<Tip>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping( value="/tips/{id}", method=RequestMethod.DELETE )
    public ResponseEntity<Tip> deleteTip( @RequestHeader Map<String,String> headers,  @PathVariable Long id ) {
    	YaasAwareParameters yap = new YaasAwareParameters(headers);
    	LOG.debug("-> In DELETE /tips "+yap);
    	
    	synchronized(repo){
    		if (repo.exists(id)){
    			repo.delete(id);
    			return new ResponseEntity<Tip>(HttpStatus.NO_CONTENT); // If not found, could return HttpStatus.NOT_FOUND
    		}
    	}  	
    	return new ResponseEntity<Tip>(HttpStatus.NOT_FOUND);
    }
   
	private static <T> List<T> iterableToList(Iterable<T> iterable) {
	  List<T> list = new ArrayList<>();
	  iterable.forEach(list::add);
	  return list;
	}
	
}
