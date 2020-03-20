package com.esa.bmap.external.services.dataprocessing.interfaces;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * DataProcessingInterface
 * 
 * Does basic computing on bmaap datas
 * 
 * @author edupin
 *
 */
public interface DataProcessingInterface {

	/**
	 * get Raster (.tiff) Basic statistics (min, max, average and standard
	 * deviation)
	 * 
	 * @param String dataFilePath path of the request Data to load
	 * @return String json response of the get request corresponding to min, max,
	 *         avg and standard deviation of each data band
	 * @throws BmapException
	 */
	public abstract String getRasterBasicStats(String dataFilePath) throws BmapException;
	
	/**
	 * get Raster (.tiff) subset statistics
	 * 
	 * @param String granuleRasterId concatenation of granule collection and granule ur (pattern: collection/granuleur)
	 * @return String json response of the get request corresponding to min, max,
	 *         avg and standard deviation of each data band
	 * @throws BmapException
	 */
	public abstract String getSubsetStats(String granuleRasterId, String wkt) throws BmapException;
	
	/**
	 * get  Raster (.tiff) subset Basic statistics
	 * 
	 * @param String granuleRaster concatenation of granule collection and granule ur (pattern: collection/granuleur)
	 * @param String granuleRoiId used to subset the granule raster with
	 * @return String json response of the get request corresponding to min, max,
	 *         avg and standard deviation of each data band
	 * @throws BmapException
	 */
	public abstract String getRoiSubsetStats(String granuleRasterId, String granuleRoiId) throws BmapException;

	/**
	 * get Raster (.tiff) frequency Histogram
	 * 
	 * @param dataFilePath path of the request Data to load
	 * @return String json response of the get request corresponding to list of
	 *         frequency/value of each data band
	 * @throws BmapException
	 */
	public abstract String getRasterHistogram(String dataFilePath) throws BmapException;

	/**
	 * get Raster (.tiff) custom Profile from given coordinates
	 * 
	 * @param dataFilePath path of the request Data to load
	 * @return String json response of the get request corresponding to the profile
	 *         given to the python service
	 * @throws BmapException
	 */
	public abstract String getCustomProfile(String dataFilePath, String wkt) throws BmapException;
	
	/**
	 * Get plot comparison between two rasters 
	 * @param xRasterFilePath raster file path that will be placed in x axis
	 * @param yRasterFilePath raster file path that will be placed in y axis
	 * @param wkt geometry used to cop the given rasters to resample
	 * @return json String with the raster's axes that will be used for scatter plotting
	 * @throws BmapException
	 */
	public String getRasterPlotComparison(String xRasterFilePath,String yRasterFilePath, String wkt) throws BmapException;
}
