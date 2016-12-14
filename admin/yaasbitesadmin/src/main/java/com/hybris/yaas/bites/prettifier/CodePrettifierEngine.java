package com.hybris.yaas.bites.prettifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.java2html.converter.JavaSource2HTMLConverter;
import de.java2html.javasource.JavaSource;
import de.java2html.javasource.JavaSourceParser;
import de.java2html.javasource.JavaSourceType;
import de.java2html.options.JavaSourceConversionOptions;
import de.java2html.options.JavaSourceStyleEntry;
import de.java2html.util.RGB;

/**
 * 
 * Locate broken links and broken images in the web pages you specify On Stash @
 * https://stash.hybris.com/projects/YBIT/repos/linkchecker/browse
 * 
 * @author Ken Lomax
 *
 */

public class CodePrettifierEngine {

	public Map<File, String> convertJavaSnippetsToHTMLFile(File f, String relativeTargetDir) throws Exception {
		Map<File, String> filesAndHTML = new HashMap<File, String>();
		String content = Files.readAllLines(Paths.get(f.toURI())).stream().reduce("",
				(x, y) -> x.concat("\n").concat(y));
		Pattern pattern = Pattern.compile("YaaSBiteSnippetStart(.*)YaaSBiteSnippetEnd", Pattern.DOTALL);
		Matcher matcher = pattern.matcher(content);

		if (matcher.find()) {
			for (int g = 0; g < matcher.groupCount(); g++) {
				String snippet = matcher.group(0);		
				String snippetName = snippet.substring(snippet.indexOf("YaaSBiteSnippetStart") + 20,
						snippet.indexOf("\n") + 1);
				snippet = snippet.substring(snippet.indexOf("\n") + 1);
				snippet = snippet.substring(0, snippet.lastIndexOf("\n"));		
				String shiftedLeft = shiftLeft(snippet);					
				String prettified = convertStringToHTML(shiftedLeft);
				String prefix = "File: "+f.getAbsolutePath().substring( f.getAbsolutePath().indexOf("/essentials/"))+"<br>";
				String trimmed = prefix.concat( prettified.substring(prettified.indexOf("<code>"), prettified.indexOf("</code>") + 7) );			
				filesAndHTML.put(f, trimmed);
				String name = snippetName.trim() + ".html";
				BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(relativeTargetDir + "/" + name)));
				bwr.write(trimmed);
				bwr.flush();
				bwr.close();
			}
		}
		return filesAndHTML;
	}

	public String convertJavaFileToHTMLFile(File f, String relativeTargetDir) throws Exception {
		String content = Files.readAllLines(Paths.get(f.toURI())).stream().reduce("",
				(x, y) -> x.concat("\n").concat(y));
		String shiftedLeft = shiftLeft(content);
		String prettified = convertStringToHTML(shiftedLeft);
		String trimmed = prettified.substring(prettified.indexOf("<code>"), prettified.indexOf("</code>") + 7);	
		
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(relativeTargetDir + "/" + f.getName())));
		bwr.write(trimmed);
		bwr.flush();
		bwr.close();

		return prettified;
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
				  new JavaSourceStyleEntry(RGB.MAGENTA, false, false));
		
		converter.convert(source, /*JavaSourceConversionOptions.getDefault()*/options, writer);

		String out = writer.toString();
		out.substring(out.indexOf("<code>"), out.indexOf("</code>") + 7);
		return out;
	}

}
