package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.esa.bmap.model.Sensor;

/**
 * SubRegionRepository
 * 
 * @author QFAURE
 *
 */
public interface SensorModeRepository extends CrudRepository<Sensor, Long> {

	Sensor findByName(String name);
}
