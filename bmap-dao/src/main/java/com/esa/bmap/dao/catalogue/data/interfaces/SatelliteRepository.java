package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.esa.bmap.model.Satellite;

/**
 * SubRegionRepository
 * 
 * @author QFAURE
 *
 */
public interface SatelliteRepository extends CrudRepository<Satellite, Long> {

	Satellite findByName(String name);
}
