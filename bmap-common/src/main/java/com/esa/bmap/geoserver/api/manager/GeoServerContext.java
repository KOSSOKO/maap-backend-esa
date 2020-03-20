/*
 * $Id: GeoServerContext.java 4629 2017-05-31 14:40:50Z acannac $
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
package com.esa.bmap.geoserver.api.manager;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.exceptions.BmapException;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;

/**
 * Singleton class providing contextual data and services related to the
 * configured GeoServer instance.
 *
 * @author $Author: acannac $
 * @version $Revision: 4629 $
 */
public class GeoServerContext {

    /** Logger. */
    private static final Logger LOG = LoggerFactory.getLogger(GeoServerContext.class);

    /**
     * Singleton instance of this context provider.
     */
    private static final GeoServerContext INSTANCE = new GeoServerContext();

    /**
     * Base URL of the GeoServer instance targeted by this tool.
     */
    private URL gsBaseURL;

    /**
     * Service used as a facade for any read/write access needed to the
     * GeoServer instance targeted by this tool.
     */
    private GeoServerRESTManager gsManager;

    /**
     * Private constructor prohibiting other instantiations of this singleton
     * class.
     */
    private GeoServerContext() {
        super();
    }

    /**
     * Get this singleton's context instance.
     * 
     * @return The singleton's context instance.
     */
    public static GeoServerContext getInstance() {
        return INSTANCE;
    }

    /**
     * Initialize this GeoServer context instance.
     * 
     * @param gsBase
     *            Base URL for geoserver
     * @throws BmapException Malformed URL
     * 			  
     */
    public void init(String gsBase) throws BmapException {
        // Build GeoServer base URL
        try {
            gsBaseURL = new URL(gsBase);
//        } catch (MalformedURLException e) {
        } catch (Exception e) {
//            String errorMsg = Messages.getInstance().get(
//                    "ccs_commons_geoserver.errors.bad_geoserver_url", gsBase); //$NON-NLS-1$
        	LOG.error(e.getMessage(), e);
            throw new BmapException(e.getMessage(), e);
        }
    }

    /**
     * Update the GeoServer manager service managed by this instance with
     * provided credentials.
     * 
     * @param user
     *            The user name to use for later GeoServer requesting.
     * @param password
     *            The password to use for later GeoServer requesting.
     */
    public void updateCredentials(String user, String password) {
        // Build GeoServer REST manager
        gsManager = new GeoServerRESTManager(gsBaseURL, user, password);
    }

    /**
     * Get the base URL of the GeoServer instance targeted by this tool.
     * 
     * @return GeoServer's base URL.
     */
    public URL getGsBaseURL() {
        return gsBaseURL;
    }

    /**
     * Get the facade service managing read/write accesses to the GeoServer
     * instance targeted by this tool.
     * 
     * @return The facade service requested.
     */
    public GeoServerRESTManager getGsManager() {
        return gsManager;
    }

}
