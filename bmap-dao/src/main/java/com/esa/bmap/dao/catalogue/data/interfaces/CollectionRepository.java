package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esa.bmap.model.Collection;
import com.esa.bmap.model.SubRegion;

/**
 * @author edupin
 *
 */
@Repository
public interface CollectionRepository extends CrudRepository<Collection, Long> {
	
	Collection findByShortName(String shortName);
	Collection findByShortNameIgnoreCase(String shortName);
	
	
}
