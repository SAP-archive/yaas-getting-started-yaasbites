package com.hybris.yaas.bites;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PrettifierTest {	// Using https://github.com/google/code-prettify
	private static String sourceDirectories[] = {
			"../../essentials/yaasbite100/src/"
	};
	private static String targetFile = "src/main/webapp/demotests/Content.html";
	
	private static List<File> listOfFiles = new ArrayList<File>();
	private static StringBuffer mainHtml = new StringBuffer();
	private static StringBuffer contentHtml = new StringBuffer();
	
	private static String targetDir = "src/main/webapp/demotests";
	private static String pagePrefix = "<html><head><script src='https://cdn.rawgit.com/google/code-prettify/master/loader/run_prettify.js?skin=default'></script><link rel='stylesheet' type='text/css' href='https://www.yaas.io/globalresources/v3/css/global.min.css' media='screen, projection, print'><link rel='stylesheet' href='https://devportal.yaas.io/styles/devportal-yaas.css'></head><body onload='prettyPrint()'><a name='Top'/>";
	private static String codePrefix = "<a name='%s'/><pre class=prettyprint><code class='language-java '>/* %s */ <a href='#Top'>Top</a>\n";
	private static String codePostfix = "</code></pre>";
	private static String pagePostfix = "</body></html>";
	
	@Test
	public void convertJavaToHTML() throws IOException {
		new File(targetDir).mkdir();	
		for (String directory : sourceDirectories){
			collectFileNames( directory );
		}			
		createContentList();
		concatJavaContents();
		assertTrue (new File(targetDir).listFiles().length >0);
	}
	
	private void createContentList(){		
		String contentEntry = "<a href='#%s'>%s</a><br>";
		contentHtml.append("<h2>YaaS Bites Source Code</h2>");
		contentHtml.append("<h3>Content</h3>");
		for(File f : listOfFiles){
			if (f.getName().contains(".java")){
				String fileName = f.toURI().toString().substring( f.toURI().toString().indexOf("../../")+6);
				String entry = String.format( contentEntry, fileName, fileName);
				contentHtml.append( entry );
			}
		}
	}
	
	private void concatJavaContents( ) throws IOException
	{			
		mainHtml.append( pagePrefix );
		mainHtml.append( contentHtml );;
		for(File f : listOfFiles){
			if (f.getName().contains(".java") || f.getName().contains(".xml")  || f.getName().contains(".html")){
				String fileName = f.toURI().toString().substring( f.toURI().toString().indexOf("../../")+6);
				
				String codePrefixWithContent = String.format( codePrefix, fileName, fileName );
				mainHtml.append(codePrefixWithContent);			
				byte[] encoded = Files.readAllBytes(Paths.get(f.toURI()));			  
				mainHtml.append( new String(encoded, StandardCharsets.UTF_8));
				mainHtml.append(codePostfix);
				System.out.println(f.getName());
			}
		}		
		
		mainHtml.append( pagePostfix );		

		  BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(targetFile /*"/Users/d061192/Desktop/demo.html"*/ )));
		  bwr.write(mainHtml.toString());
		  bwr.flush();
		  bwr.close();     
	}

	private void collectFileNames(String directoryName) {
	    File directory = new File(directoryName);
	    File[] fList = directory.listFiles();
	    for (File file : fList) {
	        if (file.isFile()) {
	        	listOfFiles.add(file);
	        } else if (file.isDirectory()) {
	        	collectFileNames(file.getAbsolutePath());
	        }
	    }
	}
	
}
