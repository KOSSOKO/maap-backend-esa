package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esa.bmap.model.Data;

/**
 * DataRepository
 * 
 * @author QFAURE
 *
 * @param <T>
 */
@Repository
public interface DataRepository<T extends Data> extends CrudRepository<T, Long> {

	// common methods
}
