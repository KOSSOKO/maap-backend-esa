package com.esa.bmap.service.dataprocessing.interfaces;

import java.util.List;
import java.util.Map;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * RasterStatisticsService
 * 
 * @author edupin
 *
 */
public interface RasterStatisticsService {
	
	/**
	 * get  Raster (.tiff)  Basic statistics (min, max, average and standard deviation)
	 * 
	 * @param String dataFilePath concatenation of granule collection and granule ur (pattern: collection/granuleur)
	 * @return Map with min max average and standard deviation of each band (lists)
	 * @throws BmapException
	 */
	public abstract Map<String, List<Double>> getRasterBasicStats(String dataFilePath) throws BmapException;
	
	/**
	 * get  Raster (.tiff) subset Basic statistics
	 * 
	 * @param String granuleRasterId concatenation of granule collection and granule ur (pattern: collection/granuleur)
	 * @param String wkt used to subset the granule raster with
	 * @return json String with min max average and standard deviation of each band
	 * @throws BmapException
	 */
	public abstract String getSubsetStats(String granuleRasterId, String wkt) throws BmapException;
	
	/**
	 * get  Raster (.tiff) subset Basic statistics
	 * 
	 * @param String granuleRaster concatenation of granule collection and granule ur (pattern: collection/granuleur)
	 * @param String granuleRoiId used to subset the granule raster with
	 * @return json String with min max average and standard deviation of each band
	 * @throws BmapException
	 */
	public abstract String getRoiSubsetStats(String granuleRasterId, String granuleRoiId) throws BmapException;

}
