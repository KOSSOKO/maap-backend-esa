package com.esa.bmap.geoserver.loaders.interfaces;

import java.net.MalformedURLException;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.exceptions.LoaderException;
import com.esa.bmap.geoserver.api.exceptions.PublishException;

public interface ShapeFileLoaderInterface {

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
	void publishStyle(String workspaceName, String baseName) throws BmapException, MalformedURLException;

	/**
	 * Publish the shapefile and relative files onto some GeoServer workspace.
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
	public void publishShapeFile(String workspace, String storeName, String shpFilePath, String layerName, String crsOrigin) throws BmapException;

	/**
	 * Create the shapefile Data Store into a GeoServer workspace.
	 * 
	 * @param workspace
	 * The name of the versioned workspace to which the shapefile shall be loaded.
	 * @param storeName
	 * The shapefile store base name.
	 * @param shpFilePath
	 * The layer files directory, relative to GEOSERVER_DATA_DIR environment variable, containing the shapefile to load.
	 * @throws BmapException
	 * If anything wrong happens when publishing the shapefile.
	 */

}
