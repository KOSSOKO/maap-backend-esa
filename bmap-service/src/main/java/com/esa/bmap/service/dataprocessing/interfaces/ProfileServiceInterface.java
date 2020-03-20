package com.esa.bmap.service.dataprocessing.interfaces;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * @author edupin
 *
 */
public interface ProfileServiceInterface {

	/**
	 * Method to get a Custom Profile from a raster Granule, given a wkt (line
	 * geometry)
	 * 
	 * @param granuleId granuleId of the raster granule to generate the custom
	 *            profile from
	 * @param wkt line geometry used to generate custom profile from the pixel
	 *            values underneath, when overlaping the given raster granule
	 * @return json String containing custom profile values
	 * @throws BmapException
	 */
	public abstract String getCustomProfile(String granuleId, String wkt) throws BmapException;
}
