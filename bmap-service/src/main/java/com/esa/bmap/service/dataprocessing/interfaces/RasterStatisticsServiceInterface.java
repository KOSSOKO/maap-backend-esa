package com.esa.bmap.service.dataprocessing.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * RasterStatisticsService
 * 
 * @author edupin
 *
 */
public interface RasterStatisticsServiceInterface {

	/**
	 * None String
	 */
	public static final String NONE = "NONE";

	/**
	 * Raster Minimum key String
	 */
	public static final String MINIMUM = "minimum";
	/**
	 * Raster Maximum key String
	 */
	public static final String MAXIMUM = "maximum";
	/**
	 * Raster Average key String
	 */
	public static final String AVERAGE = "average";

	/**
	 * Raster json band node key String
	 */
	public static final String BAND_NODE = "band";
	/**
	 * Raster json data node key String
	 */
	public static final String DATA_NODE = "data";
	/**
	 * Raster json stats node key String
	 */
	public static final String STATS_NODE = "stats";
	/**
	 * Raster Standard Deviation key String
	 */
	public static final String STD_DEVIATION = "standard_deviation";
	/**
	 * Raster Standard Deviation Map key String
	 */
	public static final String LIST_MINIMUM = "listMin";
	/**
	 * Raster Standard Deviation Map key String
	 */
	public static final String LIST_MAXIMUM = "listMax";
	/**
	 * Raster Standard Deviation Map key String
	 */
	public static final String LIST_AVERAGE = "listAvg";
	/**
	 * Raster Standard Deviation Map key String
	 */
	public static final String LIST_STD_DEVIATION = "listStdDev";

	// pythonserver.datastats.mock={"data":{"band":[{"bandName":"1","stats":[{"minimum":"0.0"},{"maximum":"1.0"},{"average":"0.5"},{"standard_deviation":"0.2"}]}]}}

	/**
	 * get Raster (.tiff) Basic statistics (min, max, average and standard
	 * deviation)
	 * 
	 * @param String dataFilePath concatenation of granule collection and granule ur
	 *            (pattern: collection/granuleur)
	 * @return Map with min max average and standard deviation of each band (lists)
	 * @throws BmapException
	 */
	public abstract Map<String, List<Double>> getRasterBasicStats(String dataFilePath) throws BmapException;

	/**
	 * get Raster (.tiff) subset Basic statistics
	 * 
	 * @param String granuleRasterId concatenation of granule collection and granule
	 *            ur (pattern: collection/granuleur)
	 * @param String wkt used to subset the granule raster with
	 * @return json String with min max average and standard deviation of each band
	 * @throws BmapException
	 */
	public abstract String getSubsetStats(String granuleRasterId, String wkt) throws BmapException;

	/**
	 * get Raster (.tiff) subset Basic statistics
	 * 
	 * @param String granuleRaster concatenation of granule collection and granule
	 *            ur (pattern: collection/granuleur)
	 * @param String granuleRoiId used to subset the granule raster with
	 * @return json String with min max average and standard deviation of each band
	 * @throws BmapException
	 */
	public abstract String getRoiSubsetStats(String granuleRasterId, String granuleRoiId) throws BmapException;

	/**
	 * generates default statisitcs for a raster
	 * 
	 * @return Map with min max average and standard deviation of each band (lists)
	 */
	public abstract Map<String, List<Double>> generateDefaultStats();

}
