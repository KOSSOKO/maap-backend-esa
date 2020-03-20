package com.esa.bmap.service.dataprocessing.interfaces;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * @author edupin
 *
 */
public interface RasterHistogramServiceInterface {
	
	/**
	 * get Raster (.tiff) frequency Histogram
	 * 
	 * @param granuleId
	 *            granuleId
	 * @return String json response of the get request corresponding to list of
	 *         frequency/value of each data band
	 * @throws BmapException
	 */
	public abstract String getRasterHistogram(String granuleId) throws BmapException;

}
