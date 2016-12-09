package com.hybris.yaas.bites;

import java.io.File;

import org.junit.Test;

import com.hybris.yaasbites.linkchecker.util.CodePrettifierEngine;

public class PrettifierTest {	// Using https://github.com/google/code-prettify
	private static String sourceDirectories[] = {
			"../../essentials"
	};
	
	private static CodePrettifierEngine pe = new CodePrettifierEngine();	
			
	@Test
	public void convertJavaToHTML() throws Exception {	
		pe.convertJavaSnippetsToHTMLFile( new File("../../essentials/yaasbite100/src/main/java/com/hybris/yaas/bites/GreetingController.java"), "src/main/webapp/demotests");
		pe.convertJavaSnippetsToHTMLFile( new File("../../essentials/yaasbite100/src/test/java/com/hybris/yaas/bites/SanityTest.java"), "src/main/webapp/demotests");
	}
	
}
