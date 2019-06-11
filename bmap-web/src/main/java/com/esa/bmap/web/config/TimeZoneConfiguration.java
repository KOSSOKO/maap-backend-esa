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

import java.util.TimeZone;
import org.springframework.stereotype.Component;

/**
 * @author Capgemini
 * @version 0.0.1
 */
@Component
public class TimeZoneConfiguration {
    static {
        // we force the service to use dates in UTC (else daylight savings rules
        // will be applied)
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
