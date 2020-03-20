package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esa.bmap.model.Platform;

/**
 * PlatformRepository
 * 
 * @author QFAURE
 *
 */
@Repository
public interface PlatformRepository extends CrudRepository<Platform, Long> {

	Platform findByName(String name);
}
