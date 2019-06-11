package com.esa.bmap.service.dataprocessing.interfaces;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * RasterPlotComparisonService
 * 
 * @author edupin
 *
 */
public interface RasterPlotComparisonServiceInterface {
	
	/**
	 * Get plot comparison between two rasters 
	 * @param xGranuleId raster granuleId that will be placed in x axis
	 * @param yGranuleId raster granuleId that will be placed in y axis
	 * @param wkt geometry used to cop the given rasters to resample
	 * @return json String with the raster's axes that will be used for scatter plotting
	 * @throws BmapException
	 */
	public abstract String getRasterPlotComparison(String xGranuleId,String yGranuleId, String wkt) throws BmapException;


}
