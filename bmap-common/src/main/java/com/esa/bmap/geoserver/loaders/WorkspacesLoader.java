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

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.GeoServerStrictApi;
import com.esa.bmap.geoserver.api.exceptions.LoaderException;
import com.esa.bmap.geoserver.api.manager.WorkspacesManager;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;

/**
 * TODO WorkspacesLoader documentation
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
@Service(value = "WorkspacesLoader")
public final class WorkspacesLoader {
	@Value("${geoserver.url}")
	private String GEOSERVER_URL;

	@Value("${datasource.dataDirectory}")
	private String dataDirectory;

	@Value("${geoserver.username}")
	private String GEOSERVER_USERNAME;

	@Value("${geoserver.password}")
	private String GEOSERVER_PASSWORD;

	String username = GEOSERVER_USERNAME;
	String password = GEOSERVER_PASSWORD;
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(WorkspacesManager.class);

	/**
	 * Attempt to publish a new workspace for given layer type and version onto the
	 * configured GeoServer instance.
	 * <p>
	 * Calling this method when the specified workspace already exists won't have
	 * any effect.
	 * </p>
	 *
	 * @param baseName The base name of the workspace to publish.
	 * @param versionNumber The version number to set onto the workspace to publish.
	 * @return The name of the workspace published
	 * @throws LoaderException If the workspace cannot be published onto the
	 *             GeoServer.
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

	public void deleteStore(String workspace, String fileName) throws BmapException {
		String fileNameWithOutExt = FilenameUtils.removeExtension(fileName);

		// String store = fileName.substring(0, fileName.lastIndexOf('.')) + "_store";
		String store = fileNameWithOutExt + "_store";
		LOG.info(fileNameWithOutExt);
		LOG.info(store);
		GeoServerRESTPublisher geoServerRESTPublisher = new GeoServerRESTPublisher(GEOSERVER_URL, GEOSERVER_USERNAME,
				GEOSERVER_PASSWORD);

		try {

			if (geoServerRESTPublisher.removeDatastore(workspace, store, true)
					|| geoServerRESTPublisher.removeCoverageStore(workspace, store, true)) {
				LOG.info("    successfully deleted" + store);
			} else {

				LOG.error("    fail to delete : " + store);
				throw new BmapException("    fail to delete : " + store);
			}
		} catch (Exception e) {
			LOG.warn(e.getMessage(), e);
			
		}

	}
}
