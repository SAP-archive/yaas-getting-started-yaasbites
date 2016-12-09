package com.hybris.yaas.bites;


import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SnippetController {

    private final Logger LOG = LoggerFactory.getLogger(SnippetController.class);
    
    @CrossOrigin()
    @RequestMapping( "/snippet" )
    public ResponseEntity<String> snippet( @RequestParam( value="pathToFile", defaultValue="") String pathToFile) {
    	LOG.debug("======= In /snippet" );    	
    	try {
            
			String content = Files.readAllLines(Paths.get(pathToFile)).stream().reduce("",
					(x, y) -> x.concat("\n").concat(y));
	    	return new ResponseEntity<String>(content, HttpStatus.OK);

		} catch (Exception e) {
			LOG.debug("======= In /snippet"+e );    		    	
		}
    	return new ResponseEntity<String>("", HttpStatus.OK);
    }
    
}
