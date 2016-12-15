package com.hybris.yaas.bites.prettifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hybris.yaas.bites.GreetingController;

import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.javasource.JavaSourceType;
import de.java2html.options.JavaSourceConversionOptions;
import de.java2html.options.JavaSourceStyleEntry;
import de.java2html.util.RGB;

public class CodePrettifierEngine {
	private final Logger LOG = LoggerFactory.getLogger(CodePrettifierEngine.class);
	
	public Map<String, String> getSnippets( File f ) throws Exception{
		String content = Files.readAllLines(Paths.get(f.toURI())).stream().reduce("",
				(x, y) -> x.concat("\n").concat(y));
		Map<String, String> snippets = convertStringOfSnippetsToHTMLBlocks(content);
		return snippets;
	}
	
	public Map<String, String>  getSnippets( URL url ) throws Exception{
		InputStream input = url.openStream();
		String content = (IOUtils.toString( input )) ;
	    IOUtils.closeQuietly(input);
	    Map<String, String> snippets = convertStringOfSnippetsToHTMLBlocks(content);
		return snippets;
	}

	public Map<String, String> convertStringOfSnippetsToHTMLBlocks(String in) throws Exception {
		Pattern pattern = Pattern.compile("YaaSBiteSnippetStart(.*)YaaSBiteSnippetEnd", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(in);
		Map<String, String> prettifiedSnippets = new HashMap<>();
		if (matcher.find()) {
			for (int g = 0; g < matcher.groupCount(); g++) {
				String snippet = matcher.group(0);		
				String snippetName = snippet.substring(snippet.indexOf("YaaSBiteSnippetStart") + 20, snippet.indexOf("\n") + 1).trim();
				snippet = snippet.substring(snippet.indexOf("\n") + 1);
				snippet = snippet.substring(0, snippet.lastIndexOf("\n"));		
				snippet = shiftLeft(snippet);					
				snippet = convertStringToHTML(snippet);
				snippet = snippet.substring(snippet.indexOf("<code>"), snippet.indexOf("</code>") + 7);		
				prettifiedSnippets.put(snippetName, snippet);
			}
		}
		return prettifiedSnippets;
	}

	public void saveSnippets( String targetDir, Map<String, String> snippets ) throws Exception{	
		for (String name : snippets.keySet()){
			LOG.debug("SAVING SNIPPET "+targetDir + "/" + name+".html");
			BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(targetDir + "/" + name+".html")));
			bwr.write(snippets.get(name));
			bwr.flush();
			bwr.close();			
		}
	}
	
	public String shiftLeft(String in){
		int topLeftSpaces = in.indexOf(in.trim());
		String spacesTopLeft = new String(new char[topLeftSpaces]).replace('\0', ' ');
		
		int tabsTopLeft = 0;
		for (char c : in.toCharArray()) {
		    if (c == '\t') {
		    	tabsTopLeft+=1;
		    }
		    else
		    	break;
		}
		if(tabsTopLeft>0)
			spacesTopLeft = new String(new char[topLeftSpaces]).replace('\0', '\t');		
		in = in.trim();
		String out = in.replaceAll("\n"+spacesTopLeft, "\n");
		return out;
	}
	
	private String convertStringToHTML(String in) throws Exception {
		// Create a reader of the raw input text
		StringReader stringReader = new StringReader(in);

		// Parse the raw text to a JavaSource object
		JavaSource source = new JavaSourceParser().parse(stringReader);

		// Create a converter and write the JavaSource object as Html
		JavaSource2HTMLConverter converter = new JavaSource2HTMLConverter();
		StringWriter writer = new StringWriter();

		JavaSourceConversionOptions options =
				  JavaSourceConversionOptions.getDefault();
				options.setShowLineNumbers(false);
			
		options.getStyleTable().put(
				  JavaSourceType.KEYWORD,
				  new JavaSourceStyleEntry(RGB.DARK_GRAY, true, false));
		
		converter.convert(source, options, writer);

		String out = writer.toString();
		out.substring(out.indexOf("<code>"), out.indexOf("</code>") + 7);
		return out;
	}

}
