package com.hybris.yaasbites.linkchecker;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Set;

import com.hybris.yaasbites.linkchecker.util.LinkCheckerEngine;

import org.junit.Test;

public class LinkCheckerTest {
	@Test
    public void testOnAKnownTestPage() throws Exception
    {
    	LinkCheckerEngine engine = new LinkCheckerEngine();
		engine.acceptArgs(new String[]	 {
			"https://yaasbiteindex.cfapps.us10.hana.ondemand.com",
			"forTestingLinkChecker/badLinks.html"
		});

		Set<String> pagesWithErrors = engine.checkLinks();
		assertTrue (pagesWithErrors.size()==7);
		pagesWithErrors.containsAll( Arrays.asList( new String[]{
			"http://www.cnn, reponse=www.cnn", 
			"http://www.cnn.com, reponse=302", 
			"http://wwwwww.cnn.com, reponse=wwwwww.cnn.com", 
			"https://yaasbiteindex.cfapps.us10.hana.ondemand.com/forTestingLinkChecker/badLinks.html#chapter2, reponse=Anchor with name chapter2 not found", 
			"https://yaasbiteindex.cfapps.us10.hana.ondemand.com/forTestingLinkChecker/badLinks.htmlwww.cnn.com, reponse=404", 
			"https://yaasbiteindex.cfapps.us10.hana.ondemand.com/images/bite99.png, reponse=404", 
			"https://yaasbiteindex.cfapps.us10.hana.ondemand.com/index.html#BADLINK, reponse=Anchor with name BADLINK not found"})
		);
    }
	
	@Test
    public void testYaaSBitesOnDevPortal() throws Exception
    {
    	LinkCheckerEngine engine = new LinkCheckerEngine();
		engine.acceptArgs(new String[]	 {
				"https://devportal.yaas.io",
				"gettingstarted/yaasbitesoverview/index.html",
				"gettingstarted/yaasbitesessentials/index.html",
				"gettingstarted/yaasbitesmini/index.html",
				"gettingstarted/yfactorsdna/index.html"
		});

		Set<String> pagesWithErrors = engine.checkLinks();
		assertTrue (pagesWithErrors.size()==0);
		
    }
    
}

