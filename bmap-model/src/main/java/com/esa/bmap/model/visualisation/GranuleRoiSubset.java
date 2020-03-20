package com.esa.bmap.model.visualisation;

import com.esa.bmap.model.Utils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL) // ignore nulls during serialization
public class GranuleRoiSubset {

	/**
	 * Id of Granule raster to be subsetted
	 */
	private String granuleRaster;

	/**
	 * granuleRoi used to subset granuleRaster with
	 */
	private String granuleRoi;

	/**
	 * Creates an empty GranuleRoiSubset
	 */
	public GranuleRoiSubset() {
		super();
	}

	/**
	 * Creates a GranuleRoiSubset with the specified parameters
	 * @param granuleRaster granule raster to subset the roi with
	 * @param granuleRoi granule Roi used to subset the granule raster with
	 */
	public GranuleRoiSubset(String granuleRaster, String granuleRoi) {
		super();
		this.granuleRaster = granuleRaster;
		this.granuleRoi = granuleRoi;
	}

	/**
	 * @return the granuleRaster
	 */
	public String getGranuleRaster() {
		return granuleRaster;
	}

	/**
	 * @param granuleRaster the granuleRaster to set
	 */
	public void setGranuleRaster(String granuleRaster) {
		this.granuleRaster = granuleRaster;
	}

	/**
	 * @return the granuleRoi
	 */
	public String getGranuleRoi() {
		return granuleRoi;
	}

	/**
	 * @param granuleRoi the granuleRoi to set
	 */
	public void setGranuleRoi(String granuleRoi) {
		this.granuleRoi = granuleRoi;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GranuleSubset {\n");
		sb.append("    granuleRaster: ").append(Utils.toIndentedString(this.granuleRaster)).append("\n");
		sb.append("    granuleRoi: ").append(Utils.toIndentedString(this.granuleRoi)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	
	
	

}
