/*
 * $Id$
 *
 * ======================================================
 *
 * Project : Biomass
 * Produit par Capgemini.
 *
 * ======================================================
 * HISTORIQUE
 * FIN-HISTORIQUE
 * ======================================================
 */
package com.esa.bmap.dao.common;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.esa.bmap.model.BmaapUser;

/**
 * @author Capgemini
 * @version 0.0.1
 * 
 */
@Repository
public interface BmapUserRepositoryInterface extends CrudRepository<BmaapUser, Long> {

	
}