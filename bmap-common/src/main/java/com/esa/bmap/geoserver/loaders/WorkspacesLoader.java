/*
 * $Id: WorkspacesLoader.java 5301 2017-09-07 08:08:54Z csouriss $
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
package com.esa.bmap.geoserver.loaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.GeoServerStrictApi;
import com.esa.bmap.geoserver.api.exceptions.LoaderException;
import com.esa.bmap.geoserver.api.manager.WorkspacesManager;

/**
 * TODO WorkspacesLoader documentation
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public final class WorkspacesLoader {

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(WorkspacesManager.class);

	/**
	 * Attempt to publish a new workspace for given layer type and version onto the configured GeoServer instance.
	 * <p>
	 * Calling this method when the specified workspace already exists won't have any effect.
	 * </p>
	 *
	 * @param baseName
	 * The base name of the workspace to publish.
	 * @param versionNumber
	 * The version number to set onto the workspace to publish.
	 * @return The name of the workspace published
	 * @throws LoaderException
	 * If the workspace cannot be published onto the GeoServer.
	 */
	public static String publishNewWorkspace(String baseName, String versionNumber) throws BmapException {
		String workspaceName = WorkspacesManager.toVersionedWorkspaceName(baseName, versionNumber);

		boolean done = GeoServerStrictApi.tryPost(mgr -> mgr.getPublisher().createWorkspace(workspaceName));
		if (done) {
			// String msg = Messages.getInstance().get(
			// "maps_loader.workspaces_loader.workspace_created", //$NON-NLS-1$
			// workspaceName);
			// LOG.info(msg);
			// IntegratedLogger.publishInfo(msg);

		} else if (GeoServerStrictApi.get(mgr -> mgr.getReader().existsWorkspace(workspaceName))) {
			// String msg = Messages.getInstance().get(
			// "maps_loader.workspaces_loader.workspace_already_created", //$NON-NLS-1$
			// workspaceName);
			// LOG.info(msg);
			// IntegratedLogger.publishInfo(msg);

		} else {
			LOG.error(Common.getMessageValue("workspacesloader.publishnewworkspace.error"));
			throw new BmapException(Common.getMessageValue("workspacesloader.publishnewworkspace.error"));
			// throw new LoaderException(Messages.getInstance().get(
			// "maps_loader.workspaces_loader.workspace_not_created", //$NON-NLS-1$
			// workspaceName));
		}
		return workspaceName;
	}

}
