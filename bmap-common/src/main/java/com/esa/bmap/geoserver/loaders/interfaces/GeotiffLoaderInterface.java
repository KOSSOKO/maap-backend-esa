package com.esa.bmap.geoserver.loaders.interfaces;
import java.io.File;

import com.esa.bmap.common.exceptions.BmapException;

public interface GeotiffLoaderInterface {
	
	public static final String DEFAULT_STYLE = "raster";
	public static final String RASTER_MULTIBAND_STYLE = "rastermultiband";
	public static final String EPSG_4326 = "EPSG:4326";
	
	/**
	 * Publish the geotiff layer on geoserver.
	 * 
	 * @param workspace
	 *            The name of the versioned workspace to which the shapefile shall
	 *            be loaded.
	 * @param storeName
	 *            The shapefile store base name.
	 * @param geotiffFilePath
	 *            The geotiff file path.
	 * @param layername
	 *            The given layer name that will be visible on geoserver.
	 * @throws BmapException
	 *             If anything wrong happens when publishing the shapefile.
	 */
    public void publishGeotiff(String workspace, String storeName, String geotiffFilePath, String layername, String style)
			throws BmapException ;
    
    
    /**
     * 
     * @param workspace
     * @param coveragestore
     * @param zipFile
     * @throws BmapException
     */
    public void publishWorldImage(String workspace, String coveragestore, File zipFile) throws BmapException;
}
