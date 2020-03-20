package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.esa.bmap.model.Instrument;

/**
 * SubRegionRepository
 * 
 * @author QFAURE
 *
 */
public interface InstrumentRepository extends CrudRepository<Instrument, Long> {

	Instrument findByName(String name);
}
