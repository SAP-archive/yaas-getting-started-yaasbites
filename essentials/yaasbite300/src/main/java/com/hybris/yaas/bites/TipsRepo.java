package com.hybris.yaas.bites;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TipsRepo extends CrudRepository<Tip, Long> {
	List<Tip> findByTenant(String tenant);
}
