package com.hybris.yaasbites.linkchecker;
import java.util.Set;


import com.hybris.yaasbites.linkchecker.util.LinkCheckerEngine;

public class LinkChecker {
	
	private static String[] urls = new String[]	 {
		"https://devportal.yaas.io",
		"gettingstarted/yaasbitesoverview/index.html",
		"gettingstarted/yaasbitesessentials/index.html",
		"gettingstarted/yaasbitesmini/index.html", 
		"gettingstarted/yfactorsdna/index.html"
	};

	public static void main(String[] args) throws Exception {
		LinkCheckerEngine engine = new LinkCheckerEngine();
		
		if (args!=null && args.length>0)
			engine.acceptArgs(args);
		else
			engine.acceptArgs( urls );
		
		Set<String> pagesWithErrors = engine.checkLinks();
	
		System.out.println("Unreachable URLs in your site:");		
		pagesWithErrors.forEach(System.out::println);
	}
	
}
