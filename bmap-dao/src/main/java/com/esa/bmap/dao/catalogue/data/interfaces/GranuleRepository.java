package com.esa.bmap.dao.catalogue.data.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.esa.bmap.model.Granule;

/**
 * DataRepository
 * 
 * @author QFAURE
 *
 * @param <T>
 */
@Repository
public interface GranuleRepository<T extends Granule> extends CrudRepository<T, Long> {

	//	@Query("SELECT g FROM Granule g WHERE UPPER(g.lastName) = UPPER(:lastName)")
	@Query("SELECT gr FROM Granule gr, Collection col WHERE gr.collection = col AND UPPER(gr.name) = UPPER(:granuleName) AND UPPER(col.shortName) = UPPER(:collectionName) ")
	public List<Granule> findByGranuleUR(@Param("collectionName") String collectionName, @Param("granuleName") String granuleName);
}
