package com.hybris.yaas.bites;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.hybris.yaas.bites.prettifier.CodePrettifierEngine;


public class PrettifierTest {	// Using https://github.com/google/code-prettify	
	private static CodePrettifierEngine pe = new CodePrettifierEngine();	
	
	
	@Test
	public void convertJavaToHTML() throws Exception {	
		pe.convertJavaSnippetsToHTMLFile( new File("../../essentials/yaasbite100/src/main/java/com/hybris/yaas/bites/GreetingController.java"), "src/main/webapp/demotests");
		assertTrue(new File("src/main/webapp/demotests/greetingEndpoint.html").exists());
		pe.convertJavaSnippetsToHTMLFile( new File("../../essentials/yaasbite100/src/test/java/com/hybris/yaas/bites/SanityTest.java"), "src/main/webapp/demotests");
		assertTrue(new File("src/main/webapp/demotests/greetingTest.html").exists());
	}

}
