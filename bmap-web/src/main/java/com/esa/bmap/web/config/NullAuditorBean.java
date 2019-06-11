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
package com.esa.bmap.web.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.User;

/**
 * @author Capgemini
 * @version 0.0.1
 */

public class NullAuditorBean implements AuditorAware<User> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> getCurrentAuditor() {
    	Optional<User> empty = Optional.empty();
    	return empty;
    }
}

