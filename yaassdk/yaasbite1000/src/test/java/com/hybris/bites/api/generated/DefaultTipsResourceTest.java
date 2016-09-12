package com.hybris.bites.api.generated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Arrays;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;
public final class DefaultTipsResourceTest extends com.hybris.bites.api.generated.AbstractResourceTest
{

	@Test
	public void testSanityPath(){      
		WebTarget target = endpoint("/tips");
		Response response = target.request().get();
		Tip[] tips = response.readEntity(Tip[].class);
		Assert.assertEquals("Should have 0 Tips", Arrays.asList(tips).size(), 0);
		
		Tip entityBody = newTip( 1, "Eat fireworks");
		Entity<Tip> entity = Entity.entity(entityBody,"application/json");
		response = target.request().post(entity);
		int index = getIndexFromLocationHeader(response);
		Assert.assertEquals("Should have index 1", index,1);
		
		target = endpoint("/tips");
		response = target.request().get();
		tips = response.readEntity(Tip[].class);
		Assert.assertEquals("Should have 1 Tip", Arrays.asList(tips).size(), 1);
		Assert.assertEquals("Tip should be as posted",tips[0].getTip(), "Eat fireworks");
		
		target = endpoint("/tips","/1");
		entityBody = newTip( 1, "Don't Eat fireworks");
		entity = Entity.entity(entityBody,"application/json");
		response = target.request().put(entity);
		Assert.assertEquals("Response should be OK", Status.OK.getStatusCode(), response.getStatus());	
	
		target = endpoint("/tips");
		response = target.request().get();
		tips = response.readEntity(Tip[].class);
		Assert.assertEquals("Should have 1 Tip", Arrays.asList(tips).size(), 1);
		Assert.assertEquals("Tip should be as posted",tips[0].getTip(), "Don't Eat fireworks");
		
		target = endpoint("/tips","/1");
		response = target.request().delete();
		Assert.assertEquals("Response does not have expected response code", Status.NO_CONTENT.getStatusCode(), response.getStatus());	
	
		target = endpoint("/tips");
		response = target.request().get();
		tips = response.readEntity(Tip[].class);
		Assert.assertEquals("Should have 0 Tips", Arrays.asList(tips).size(), 0);

	}

	@Override
	protected ResourceConfig configureApplication()
	{
		final ResourceConfig application = new ResourceConfig();
		application.register(DefaultTipsResource.class);
		return application;
	}
	
	private WebTarget endpoint( String root, String ... path){
		 return getRootTarget(root).path( path.length>0 ? path[0]: "");		 
	}
	
	private Tip newTip( Integer id, String advice ){
		Tip t = new Tip();
		t.setId(id);
		t.setTip(advice);
		return t;
	}


	private StringBuilder getContent(HttpURLConnection connection) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line+"\n");
        }       
        br.close();
		return sb;
	}
	private int getIndexFromURL( String url ){
		try {
			String afterLastSlash = url.substring(url.lastIndexOf("/")+1 );	
			String afterLastSlashTrimmed = afterLastSlash.replace(']', ' ' ).trim();
			int i = Integer.valueOf(afterLastSlashTrimmed);
			return i;
		} catch (Exception e) {
			return -1;
		}
	}
	private int getIndexFromLocationHeader( Response response ){
		String locationURL = response.getHeaders().get("Location").toString();
		int id = getIndexFromURL(locationURL);
		return id;
	}
	
}
