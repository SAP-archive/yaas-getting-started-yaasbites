package com.hybris.yaas.bites;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.junit.Test;

public class PrettifierTest {	// Using https://code.google.com/p/google-code-prettify/wiki/GettingStarted
	private static String sourceFiles[] = {
		"../../essentials/yaasbite100/src/main/java/com/hybris/yaas/bites/GreetingController.java"
	};

	private static String targetDir = "src/main/webapp/demotests";
	private static String prefix = "<html><head><script src='https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js?skin=desert'></script></head><body onload='prettyPrint()'><pre class=prettyprint><code class='language-java '>";
	private static String postfix = "</code></pre></body></html>";
	
	@Test
	public void convertJavaToHTML() throws IOException {
		/*
		Not running for now - will update next in the CI pipeline
		new File(targetDir).mkdir();
		for (String file : sourceFiles){
			javaToPrettyHtml(file, targetDir);
		}
		assertTrue (new File(targetDir).listFiles().length >0);
		 */
	}
	
	private void javaToPrettyHtml( String srcFile, String targetDir) throws IOException
	{			
		File srcFileRef = new File(srcFile);
		FileInputStream input = new FileInputStream(srcFileRef);
		String newName = srcFileRef.getName().replace(".java", ".html");
		FileOutputStream output = new FileOutputStream(new File(targetDir+"/"+newName));
		
		byte[] buf = new byte[1024];
		int bytesRead;
		
		Writer w = new OutputStreamWriter(output, "UTF-8");
		w.write(prefix);
		w.flush();
		
		while ((bytesRead = input.read(buf)) > 0) {
			output.write(buf, 0, bytesRead);
		}
		
		w.write(postfix);
		w.flush();
		
		input.close();
		output.close();
	}

}
