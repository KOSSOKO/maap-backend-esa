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

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.userdetails.User;

/**
 * @author Capgemini
 * @version 0.0.1
 */
@Configuration
public class AuditingConfiguration {

    /**
     * Auditor provider.
     *
     * @return the auditor aware
     */
    @Bean
    public AuditorAware<User> auditorProvider() {
        return new NullAuditorBean();
    }
}
