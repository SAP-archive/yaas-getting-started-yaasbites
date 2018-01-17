package com.hybris.bites;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.hybris.bites.api.generated.Tip;

@Component
public class TipsRepo {
	// Basic repo examples - not thread safe, just for demo purposes.
	Map<Long, Tip> tips = new ConcurrentHashMap<Long, Tip>();
	
	public List<Tip> findAll(){
		return new ArrayList<Tip>(tips.values());
	}
	
	public Tip findOne(long id){
		return tips.get(id);
	}
	
	public boolean exists(long id){
		return tips.containsKey(id);
	}
	
	public boolean save(Tip t){
		tips.put( Long.valueOf( t.getId()), t);
		return true;
	}

	public boolean delete(long id){
		tips.remove(id);
		return true;
	}	
	
}
