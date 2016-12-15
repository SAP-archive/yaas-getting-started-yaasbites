package com.hybris.yaas.bites;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URL;
import java.util.Map;

import org.junit.Test;

import com.hybris.yaas.bites.prettifier.CodePrettifierEngine;

public class PrettifierTest {	// Using https://github.com/google/code-prettify	
	private static CodePrettifierEngine pe = new CodePrettifierEngine();	
	
	@Test
	public void convertGithubSnippetsToHTML() throws Exception {	
		Map<String, String> snippets = pe.getSnippets( 
			new URL("https://raw.githubusercontent.com/SAP/yaas-getting-started-yaasbites/master/admin/yaasbitesadmin/src/test/java/com/hybris/yaas/bites/SanityTest.java?nocache="+System.currentTimeMillis()));
		pe.saveSnippets( "src/main/webapp/demotests", snippets);
		assertTrue(new File("src/main/webapp/demotests/greetingTest.html").exists());
		
		snippets = pe.getSnippets( 
			new File("../../essentials/yaasbite100/src/main/java/com/hybris/yaas/bites/GreetingController.java?nocache="+System.currentTimeMillis()));
		pe.saveSnippets( "src/main/webapp/demotests", snippets);
		assertTrue(new File("src/main/webapp/demotests/greetingEndpoint.html").exists());
	}

}
