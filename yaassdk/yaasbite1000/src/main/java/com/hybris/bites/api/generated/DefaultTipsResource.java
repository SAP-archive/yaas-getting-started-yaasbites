package com.hybris.bites.api.generated;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hybris.bites.TipsRepo;

/**
* Resource class containing the custom logic. Please put your logic here!
*/
@Component("apiTipsResource")
@Singleton
public class DefaultTipsResource implements com.hybris.bites.api.generated.TipsResource
{
	private final Logger LOG = LoggerFactory.getLogger(DefaultTipsResource.class);

	@javax.ws.rs.core.Context
	private javax.ws.rs.core.UriInfo uriInfo;

    @Autowired
    private TipsRepo repo;

	/* GET / */
	@Override
	public Response get()
	{
	   	LOG.debug("-> In GET /tips ");   	
	   	return Response.ok().entity(iterableToList( repo.findAll() )).build();	
	}

	/* POST / */
	@Override
	public Response post(Tip tip) {
		LOG.debug("-> In POST /tips");
    	repo.save( tip );   	
    	
    	return created(tip.getId());
	}
	
	/* PUT /{id} */
	@Override
	public Response putById(Integer id, Tip tip) {
		LOG.debug("-> In PUT /tips ");
		synchronized(repo){
    		if (repo.exists(Long.valueOf(id))){
    			Tip oldTip = repo.findOne(Long.valueOf(id));
    			oldTip.setTip(tip.getTip());
    			repo.save(oldTip);
    	    	return Response.ok().build();

    		}
    	}  	
    	return Response.status(Status.NOT_FOUND).build();
	}

	/* DELETE /{id} */
	@Override
	public Response deleteById(final java.lang.Integer id)
	{
		LOG.debug("-> In DELETE /tips");
    	synchronized(repo){
    		if (repo.exists(Long.valueOf(id))){
    			repo.delete(Long.valueOf(id));
    			return Response.noContent().build();
    		}
    	}  	
    	return Response.status(Status.NOT_FOUND).build();
	}

	private static <T> List<T> iterableToList(Iterable<T> iterable) {
	  List<T> list = new ArrayList<>();
	  iterable.forEach(list::add);
	  return list;
	}
	
	private Response created(final Object o){		
		final URI uri = uriInfo.getRequestUriBuilder().path( o.toString() ).build();
		return Response.created( uri ).build();
	}
	private Response createdWithEntity(final Object o, final Object entity){	
		final URI uri = uriInfo.getRequestUriBuilder().path( o.toString() ).build();
		return Response.created( uri ).entity( entity ).build();
	}
}
