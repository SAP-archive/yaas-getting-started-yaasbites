package com.hybris.yaas.bites;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
		
@Service
public class TipService {
    
	private final Logger LOG = LoggerFactory.getLogger(TipService.class);
   
    private static boolean problem = false;   
    public static final int GOOD_USER_EXPERIENCE_SLEEP_MS = 1000;
    public static final int BAD_USER_EXPERIENCE_SLEEP_MS = 15000;
	private static final int HYSTRIX_TRIGGER_MS =2000 ;   
    
	
	// Supply Hystrix with a fallback method to invoke, should this method be taking too long
	@HystrixCommand(fallbackMethod = "fallBackMethod",
    		commandProperties = {
    			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = ""+HYSTRIX_TRIGGER_MS)
    })
	public String riskyMethod(  ) throws InterruptedException{   	
		// If there is a problem, this method will take too long, but is protected by Hystrix
    	if (problem){
        	LOG.debug("RiskyMethod is not responding promptly, about to have a BAD pause");
    		Thread.sleep(BAD_USER_EXPERIENCE_SLEEP_MS);
    	}
    	LOG.debug("In riskyMethod");
		return "Returning from riskyMethod";
    }
    
	// If there is a problem, this method will take too long
    public String veryRiskyMethod(  ) throws InterruptedException{   	
    	if (problem){
        	LOG.debug("RiskyMethod is not responding promptly, about to have a BAD pause");
    		Thread.sleep(BAD_USER_EXPERIENCE_SLEEP_MS);
    	}
    	LOG.debug("In veryRiskyMethod");
		return "Returning from veryRiskyMethod";
    }
    
    public String fallBackMethod(  ) {   
    	LOG.info("In fallBackMethod");
		return "Returning from fallBackMethod";
    }
    
    public void toggleProblem(  ) {   	
    	problem=!problem;
    }

    public boolean hasProblem(){
    	return problem;
    }
}
