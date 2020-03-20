package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;
import com.esa.bmap.model.SubRegion;

/**
 * SubRegionRepository
 * 
 * @author QFAURE
 *
 */
public interface SubRegionRepository extends CrudRepository<SubRegion, Long> {

	SubRegion findByName(String name);
}
