/*
 * $Id: WorkspacesLoader.java 4347 2017-04-14 17:32:09Z csouriss $
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.util.StringUtils;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.GeoServerStrictApi;
//import com.esa.bmap.geoserver.api.exceptions.RetrieveException;
import com.esa.bmap.geoserver.api.exceptions.RetrieveException;

import it.geosolutions.geoserver.rest.decoder.RESTWorkspaceList;
import it.geosolutions.geoserver.rest.decoder.RESTWorkspaceList.RESTShortWorkspace;

/**
 * The Workspaces loader.
 * <p>
 * A class to create default Workspaces if they don't exist
 * </p>
 * 
 * @author $Author: csouriss $
 * @version $Revision: 4347 $
 */
public final class WorkspacesManager {

    /**
     * Connector used to append a unique version number to some base workspace
     * name.
     */
    private static final String VERSIONED_WKS_NAME_CONNECTOR = "_v"; //$NON-NLS-1$

    /**
     * Private default constructor, prohibiting this class' instantiation.
     */
    private WorkspacesManager() {
        super();
    }

    /**
     * Build a new layer version number.
     * 
     * @return A new layer version number.
     */
    public static String buildNewVersionNumber() {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", //$NON-NLS-1$
                Locale.getDefault(Locale.Category.FORMAT));
        return dateFormat.format(new Date());
    }

    /**
     * Retrieve existing CCS workspace names associated to some given layer from
     * the configured GeoServer instance.
     * 
     * @param baseName
     *            The base name of the layer for which versions are searched.
     * @return The list of version numbers of matching workspaces found.
     * @throws RetrieveException
     *             If workspace names cannot be retrieved from the GeoServer.
     */
    public static List<String> getExistingWksVersions(String baseName)
//            throws RetrieveException {
    	throws BmapException {
        // Get all registered workspaces
        RESTWorkspaceList workspaces = GeoServerStrictApi
                .get(mgr -> mgr.getReader().getWorkspaces());
        // Compute the result
        return StreamSupport.stream(workspaces.spliterator(), false)
                // Keep workspace names only
                .map(RESTShortWorkspace::getName)
                // Make sure to filter only CCS ones
                .filter(name -> name.contains(VERSIONED_WKS_NAME_CONNECTOR))
                // Split names
                .map(name -> name.split(VERSIONED_WKS_NAME_CONNECTOR))
                // Keep only workspaces related to the provided baseName
                .filter(nameParts -> nameParts[0].equals(baseName))
                // Retrieve the version ID
                .map(nameParts -> nameParts[1])
                // Keep distinct ones and sort them
                .distinct().sorted().collect(Collectors.toList());
    }

    /**
     * Build a full workspace name (as registered into the GeoServer) from a
     * base workspace name and a version number.
     * 
     * @param workspaceBase
     *            The base workspace name (e.g. ASRP).
     * @param versionNumber
     *            The version number (e.g. 20170101[...]).
     * @return The full workspace name to use, including the version number
     *         given.
     */
    public static String toVersionedWorkspaceName(String workspaceBase,
            String versionNumber) {
        return workspaceBase + VERSIONED_WKS_NAME_CONNECTOR + versionNumber;
    }

    /**
     * Retrieve a base workspace name from a full one (as registered into the
     * GeoServer).
     * 
     * @param versionedWorkspace
     *            The full workspace name with version number (e.g.
     *            ASRP_v20170102).
     * @return The associated base workspace name.
     */
    public static String toBaseWorkspaceName(String versionedWorkspace) {
        return StringUtils.split(versionedWorkspace,
                VERSIONED_WKS_NAME_CONNECTOR)[0];
    }

}
