/*
 * $Id: ShapeFileLoader.java 5301 2017-09-07 08:08:54Z csouriss $
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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.exceptions.LoaderException;
import com.esa.bmap.geoserver.api.exceptions.PublishException;
import com.esa.bmap.geoserver.loaders.interfaces.ShapeFileLoaderInterface;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.GeoServerRESTPublisher.UploadMethod;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy;
import it.geosolutions.geoserver.rest.manager.GeoServerRESTStyleManager;

/**
 * The ShapeFile data loader.
 * <p>
 * A class to load ShapeFile data from SHP files.
 * </p>
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
@Service(value = "ShapeFileLoaderInterface")
public class ShapeFileLoaderService implements ShapeFileLoaderInterface {

	@Value("${geoserver.url}")
	private String GEOSERVER_URL;

	@Value("${datasource.dataDirectory}")
	private String dataDirectory;

	@Value("${geoserver.username}")
	private String GEOSERVER_USERNAME;

	@Value("${geoserver.password}")
	private String GEOSERVER_PASSWORD;

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(ShapeFileLoaderService.class);

	/**
	 * Inherited constructor.
	 * 
	 * @param primaryStage
	 * the stage where to start the new window from
	 * @param layerDesc
	 * The descriptor of the layer to upload (with the name of the layer, the targeted workspace base name, ...).
	 */
	// public ShapeFileLoader(Stage primaryStage, LayerDescriptor layerDesc) {
	// super(primaryStage, layerDesc);
	// }
	//
	//// /**
	//// * {@inheritDoc}
	//// *
	//// * @see
	// com.dga.ccs.maps.loader.loaders.AbstractLayerLoader#loadData(java.io.File,
	//// * java.lang.String, java.util.Map)
	//// */
	//// @Override
	// protected Path loadData(File sourceDir, String versionNumber,
	// Map<EnumLayerLoaderParamKey, Object> parameters)
	// throws LoaderException {
	// // Announce files searching start
	// String searchStartMsg = Messages.getInstance().get(
	// "maps_loader.shapefile_loader.search_files", //$NON-NLS-1$
	// sourceDir.getAbsolutePath());
	// LOG.info(searchStartMsg);
	// IntegratedLogger.publishInfo(searchStartMsg, getRunningTask());
	//
	// // Prepare needed file & directory acceptance functions
	// BiFunction<Path, BasicFileAttributes, Boolean> acceptDir = (path,
	// attrs) -> true;
	// BiFunction<Path, BasicFileAttributes, Boolean> acceptFile = (path,
	// attrs) -> {
	// String fileExtension = FilenameUtils
	// .getExtension(path.getFileName().toString());
	// return Arrays.stream(MapFileExtensions.SHP_RELATED_EXTENSIONS)
	// .anyMatch(ext -> ext.equalsIgnoreCase(fileExtension));
	// };
	//
	//// // Prepare other parameters
	// String baseName = getLayerDesc().getBaseName();
	// String workspaceName = WorkspacesManager
	// .toVersionedWorkspaceName(baseName, versionNumber);
	// List<Path> filesToCopy = new ArrayList<>();
	// HandleFileFunction handleFile = (sourceFile, targetDir) -> filesToCopy
	// .add(sourceFile);
	//
	// // Create storage and list files to copy
	// Path targetDir = LayerFilesStoresManager.getInstance().processData(
	// sourceDir.toPath(), workspaceName, baseName,
	// LayerFilesStoresManager.NOOP_STORE_INIT, acceptFile, acceptDir,
	// handleFile);
	//
	// // Copy files
	// copyShapeFiles(filesToCopy, sourceDir, targetDir, baseName);
	//
	// // Publish the SLD style file associated to the shapefile to load
	//// getRunningTask().resetProgress();
	// publishStyle(workspaceName, baseName);
	//
	// // Publish the shapefile related files
	// publishShapeFile(workspaceName, baseName, targetDir);
	//
	// // Return workspace root
	// return targetDir.getParent();
	// }

	/**
	 * Copy selected shape files to the target storage created.
	 * 
	 * @param filesToCopy
	 * The selected shape files to copy.
	 * @param sourceDir
	 * The source directory containing these shape files (used for log purposes).
	 * @param targetRoot
	 * The root of the target storage (or workspace) created.
	 * @param storageName
	 * The sub-storage to use within the created target storage.
	 * @throws LoaderException
	 * Thrown as soon as some single file copying cannot be performed.
	 */
	// private void copyShapeFiles(List<Path> filesToCopy, File sourceDir,
	// Path targetRoot, String storageName) throws LoaderException {
	// // Log the number of files to copy
	// int nbFilesToCopy = filesToCopy.size();
	// String nbFilesFoundMsg = Messages.getInstance().get(
	// "maps_loader.shapefile_loader.files_found", nbFilesToCopy); //$NON-NLS-1$
	// LOG.info(nbFilesFoundMsg);
	// IntegratedLogger.publishInfo(nbFilesFoundMsg, getRunningTask());
	// getRunningTask().updateProgress(0, nbFilesToCopy);
	//
	// // Log copying start
	// String copyStartMsg = Messages.getInstance().get(
	// "maps_loader.shapefile_loader.copy_files_start", //$NON-NLS-1$
	// targetRoot);
	// LOG.info(copyStartMsg);
	// IntegratedLogger.publishInfo(copyStartMsg, getRunningTask());
	//
	// try {
	// for (Path sourceFile : filesToCopy) {
	// // Log currently copied file
	// String copyFileMsg = Messages.getInstance().get(
	// "maps_loader.shapefile_loader.copy_file", //$NON-NLS-1$
	// sourceDir.toPath().relativize(sourceFile));
	// LOG.debug(copyFileMsg);
	// IntegratedLogger.publishDetail(copyFileMsg, getRunningTask());
	//
	// // Copy the current file and increment the work done
	// String sourceExt = FilenameUtils
	// .getExtension(sourceFile.getFileName().toString());
	// Path targetFilePath = targetRoot.resolve(
	// MapFileExtensions.concat(storageName, sourceExt));
	// LayerFilesStoresManager.getInstance().copyFile(sourceFile,
	// targetFilePath);
	// getRunningTask().incrementWorkDone();
	// }
	// } catch (IOException e) {
	// String errorMsg = Messages.getInstance().get(
	// "maps_loader.shapefile_loader.copy_files_error", //$NON-NLS-1$
	// targetRoot);
	// throw new LoaderException(errorMsg, e);
	// }
	//
	// // Log copying end
	// String copyEndMsg = Messages.getInstance().get(
	// "maps_loader.shapefile_loader.copy_files_end", targetRoot); //$NON-NLS-1$
	// LOG.info(copyEndMsg);
	//
	// }

	/**
	 * Load and publish the style file associated to some shapefile kind onto some GeoServer workspace.
	 * 
	 * @param workspaceName
	 * The name of the versioned workspace to which the shapefile style shall be loaded.
	 * @param baseName
	 * The shapefile store base name, which will indicate the name of the shapefile style to load.
	 * @throws MalformedURLException
	 * @throws LoaderException
	 * If anything wrong happens when loading and/or publishing the shapefile style.
	 */
	@Override
	public void publishStyle(String workspaceName, String baseName) throws BmapException, MalformedURLException {

		URL restURL = new URL(GEOSERVER_URL);

		String username = GEOSERVER_USERNAME;
		String password = GEOSERVER_PASSWORD;

		GeoServerRESTStyleManager geoSttleMg = new GeoServerRESTStyleManager(restURL, username, password);
		// TO COMPLETE

		// Copy style file on disk
		// String copyStyleMsg = Messages.getInstance()
		// .get("maps_loader.shapefile_loader.copy_sld", baseName); //$NON-NLS-1$

		// Path sldTargetPath =
		// LayerFilesStoresManager.getInstance().addStyleToWorkspace(baseName,
		// workspaceName);

		// Publish it onto the GeoServer
		// String publishStyleMsg = Messages.getInstance()
		// .get("maps_loader.shapefile_loader.publish_sld", baseName); //$NON-NLS-1$

		// GeoServerStrictApi.post(
		// mgr -> mgr.getStyleManager().publishStyleInWorkspace(workspaceName,
		// sldTargetPath.toFile(), baseName));

	}

	/**
	 * /** Publish the shapefile and relative files onto some GeoServer workspace.
	 * 
	 * @param workspace
	 * The name of the versioned workspace to which the shapefile shall be loaded.
	 * @param storeName
	 * The shapefile store base name.
	 * @param shpFilePath
	 * The shapefile files directory, containing the shapefile to load alongside some related files (e.g. projection file).
	 * @param layerName
	 * The layer name as it will be shown on geoserver.
	 * @throws PublishException
	 * If anything wrong happens when publishing the shapefile.
	 */
	@Override
	public void publishShapeFile(String workspace, String storeName, String shpFilePath, String layerName, String crsOrigin) throws BmapException {

		File shapeFile = new File(shpFilePath);

		String style = "default_polygon";

		GeoServerRESTPublisher geoP = new GeoServerRESTPublisher(GEOSERVER_URL, GEOSERVER_USERNAME, GEOSERVER_PASSWORD);

		try {

			geoP.publishShp(workspace, storeName, null, layerName, UploadMethod.FILE, shapeFile.toURI(), "EPSG:4326", crsOrigin, ProjectionPolicy.REPROJECT_TO_DECLARED, style);
			LOG.info(layerName + " was successfully published to Geoserver");

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}
}
