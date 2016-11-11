package com.hybris.yaasbites.linkchecker.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

import javax.net.ssl.SSLHandshakeException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * Locate broken links and broken images in the web pages you specify
 * On Stash @ https://stash.hybris.com/projects/YBIT/repos/linkchecker/browse
 * @author Ken Lomax
 *
 */

public class LinkCheckerEngine {

	private List<String> websiteRootURLs;
	private List<String> relativeChildURLS;
	
	private Set<String> hrefsToBePinged = new ConcurrentSkipListSet<>();
	private Set<String> pagesWithErrors = new ConcurrentSkipListSet<>();
	
	private final List<Integer> acceptableHTTPResponses = new ArrayList<>(Arrays.asList(
		HttpURLConnection.HTTP_OK,
		HttpURLConnection.HTTP_FORBIDDEN, 
		HttpURLConnection.HTTP_SEE_OTHER, 
		HttpURLConnection.HTTP_MOVED_PERM
	));

	private final List<String> whiteListedAbsoluteURLs = new ArrayList<>(Arrays.asList(
		"https://market.stage.yaas.io",
		"https://community.stage.yaas.io", 
		"https://knowledge.stage.yaas.io", 
		"http://www.hybris.com",
		"http://www.sap.com/about/legal/copyright.html", 
		"http://www.sap.com/about/legal/impressum.html",
		"http://www.sap.com/about/legal/privacy.html", 
		"https://experts.hybris.com/spaces/151/index.html",
		"https://www.yaas.io/legal/#copyright",
		"https://www.yaas.io/legal/#impressum",
		"https://www.yaas.io/legal/#privacy",
		"https://www.yaas.io/legal/#terms",
		"https://builder.yaas.io//#/?selectedPath=%2FMy%20Profile%2FProfile",
		"https://api.eu.yaas.io/yaasgulps/sdf/v1", // Is just an example string in the bites
		"https://api.yaas.io/yaasgulps/sdf/v1/tips", // Is just an example string in the bites,
		"https://yaasbite6.cfapps.us10.hana.ondemand.com/index.html",  // Is just an example string in the bites,
		"https://github.com/mgonto/Restangular#starter-guide" // I expect they will correct and rename to starter-guide	
	));

	public void acceptArgs(String[] args) {
		websiteRootURLs = Arrays.asList(args).stream().filter(s -> s.startsWith("http")).collect(Collectors.toList());
		relativeChildURLS = Arrays.asList(args).stream().filter(s -> !s.startsWith("http"))
				.collect(Collectors.toList());
	}
	
	public Set<String> checkLinks() throws Exception {		
		websiteRootURLs = websiteRootURLs.stream().map(
				s -> s.endsWith("/") ? s : s + "/").collect(Collectors.toList());
		relativeChildURLS = relativeChildURLS.stream().map(
				s -> s.indexOf("/") == 0 ? s.substring(1) : s).collect(Collectors.toList());

		for (String website : websiteRootURLs) {
			for (String page : relativeChildURLS) {
				String fullPageURL = website + page;
				Document doc = Jsoup.connect(fullPageURL).get();
				Elements links = doc.select("a[href]");
				Elements pngs = doc.select("img[src]");
				extractLinks(website, page, links, pngs);
			}
		}

		hrefsToBePinged.parallelStream().forEach(s -> pingHost(s, pagesWithErrors));

		return pagesWithErrors;
	}

	private boolean extractLinks(String root, String page, Elements links, Elements pngs) throws MalformedURLException {
		for (Element link : links) {
			String href = link.attr("href");
			href = constructAbsoluteHRef(root, page, href);
			if (href.endsWith("#"))
				href=href.substring(0, href.length()-1);
			hrefsToBePinged.add(  root+page+"|" + href);
		}

		for (Element image : pngs) {
			String href = image.attr("src");
			href = constructAbsoluteImageLocation(root, page, href);
			hrefsToBePinged.add( root+page+"|" +href);
		}

		return true;
	}

	private boolean pingHost(String sourceAndUrl, Set<String> pagesWithErrors) {
		
		String source = sourceAndUrl.split("\\|")[0];
		String url = sourceAndUrl.split("\\|")[1];
		
		//System.out.println("Checking " + url);
		if (whiteListedAbsoluteURLs.contains(url))
			return true;

		HttpURLConnection.setFollowRedirects(false);
		HttpURLConnection con;
		String response = null;
		try {
			con = (HttpURLConnection) new URL(url).openConnection();
			con.setConnectTimeout(10000);
			con.setReadTimeout(10000);
			
			response = "" + con.getResponseCode();
			if (acceptableHTTPResponses.contains(con.getResponseCode())){
				if ( url.contains("#")){
					String anchorName = url.split("#")[1];
					String s = getUrlContents(con);
					if (s.contains("<a name=\""+anchorName+"\"") || s.contains("<a name=\'"+anchorName+"\'") 
							||  s.contains(" id=\""+anchorName+"\"") ||  s.contains(" id=\'"+anchorName+"\'") )
						return true;
					throw new Exception("Anchor with name "+anchorName+ " not found");
				}				
				return true;
			}
		} catch (SSLHandshakeException e) {
			return true;
		} catch (Exception e) {
			response = e.getMessage();
		}
		System.out.println("Unreachable from " +source + " : " + url + " "+response);
		
		pagesWithErrors.add( "Unreachable: "+url + ", reponse=" + response);
		return false;
	}
	
	private String constructAbsoluteImageLocation(String root, String page, String href) throws MalformedURLException {
		if (!href.startsWith("http")) {
			if (href.startsWith("/"))
				href = root + href.substring(1);
			if (!href.startsWith("http"))
				href = root + page.replace("index.html", "") + href;
			if (href.contains("..")) {
				int indexOfFirstDotDot = href.indexOf("..");
				href = new URL(new URL(href.substring(0, indexOfFirstDotDot)), href.substring(indexOfFirstDotDot)).toString();
			}
		}
		return href;
	}

	private String constructAbsoluteHRef(String root, String page, String href) throws MalformedURLException {

		if (href.startsWith("/"))
			href = root + href.substring(1);
		if (!href.startsWith("http"))
			href = root + page + href;
		if (href.contains("..")) {
			int indexOfFirstDotDot = href.indexOf("..");
			href = new URL(new URL(href.substring(0, indexOfFirstDotDot)), href.substring(indexOfFirstDotDot))
					.toString();
		}
		return href;
	}

	
	private String getUrlContents(HttpURLConnection conn)  {
	    StringBuilder content = new StringBuilder();
	    try   {
	      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = bufferedReader.readLine()) != null) {
	        content.append(line + "\n");
	      }
	      bufferedReader.close();
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	    return content.toString();
	  }

}
