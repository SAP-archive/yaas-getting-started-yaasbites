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
import de.java2html.options.JavaSourceConversionOptions;

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
				String prettified = convertStringToHTML(snippet);
				String prefix = "<b>"+f.getAbsolutePath().substring( f.getAbsolutePath().indexOf("/essentials/"))+"</b><br>";
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
		String prettified = convertStringToHTML(content);
		String trimmed = prettified.substring(prettified.indexOf("<code>"), prettified.indexOf("</code>") + 7);
		String shiftedLeft = shiftLeft(trimmed);
		
		BufferedWriter bwr = new BufferedWriter(new FileWriter(new File(relativeTargetDir + "/" + f.getName())));
		bwr.write(shiftedLeft);
		bwr.flush();
		bwr.close();

		return prettified;
	}

	private String shiftLeft(String in){
		int spacesInTopLeft = 0;		
		for (char c : in.toCharArray()) {
		    if (c == ' ') {
		    	spacesInTopLeft++;
		    }
		}
		String out = in.replaceAll("\n  ", "\nXXX");
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
		converter.convert(source, JavaSourceConversionOptions.getDefault(), writer);

		String out = writer.toString();
		out.substring(out.indexOf("<code>"), out.indexOf("</code>") + 7);
		return out;
	}

}
