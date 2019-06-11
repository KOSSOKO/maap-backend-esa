package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esa.bmap.model.Data;
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

	// common methods
}
