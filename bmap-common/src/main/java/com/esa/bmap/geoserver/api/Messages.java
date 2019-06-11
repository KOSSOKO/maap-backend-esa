/*
 * $Id$
 *
 * ======================================================
 *
 * Project : CCS
 * Produit par Capgemini.
 *
 * ======================================================
 * HISTORIQUE
 * FIN-HISTORIQUE
 * ======================================================
 */
package com.esa.bmap.geoserver.api;

import java.lang.invoke.MethodHandles;

//import com.dga.ccs.commons.system.util.log.slf4j.AbstractSlf4JMessageBundle;

/**
 * Messages externalization for commons library handling access to the
 * GeoServer.
 *
 * @author $Author$
 * @version $Revision$
 */
public final class Messages {

    /**
     * Bundle name
     */
    private static final String BUNDLE_NAME = MethodHandles.lookup()
            .lookupClass().getPackage().getName() + ".messages"; //$NON-NLS-1$

    /**
     * Resource bundle
     */
    private static final Messages MESSAGE_BUNDLE = new Messages();

    /**
     * Sole constructor.
     */
    private Messages() {
        //super(BUNDLE_NAME, MethodHandles.lookup().lookupClass());
    }

    /**
     * Get the messages catalog instance to use.
     * 
     * @return The singleton messages catalog instance.
     */
    public static Messages getInstance() {
        return MESSAGE_BUNDLE;
    }
}
