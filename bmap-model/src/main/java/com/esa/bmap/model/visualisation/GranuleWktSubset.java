package com.esa.bmap.model.visualisation;

import com.esa.bmap.model.Utils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL) // ignore nulls during serialization
public class GranuleWktSubset {

	/**
	 * Id of Granule data to be subsetted
	 */
	private String granuleID;

	/**
	 * Wkt representing the geometry used to subset the granule data
	 */
	private String wkt;

	public GranuleWktSubset() {
		super();
	}

	public GranuleWktSubset(String granuleID, String wkt) {
		this.granuleID = granuleID;
		this.wkt = wkt;
	}

	/**
	 * @return the granuleID
	 */
	public String getGranuleID() {
		return granuleID;
	}

	/**
	 * @param granuleID the granuleID to set
	 */
	public void setGranuleID(String granuleID) {
		this.granuleID = granuleID;
	}

	/**
	 * @return the wkt
	 */
	public String getWkt() {
		return wkt;
	}

	/**
	 * @param wkt the wkt to set
	 */
	public void setWkt(String wkt) {
		this.wkt = wkt;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GranuleSubset {\n");
		sb.append("    granuleID: ").append(Utils.toIndentedString(this.granuleID)).append("\n");
		sb.append("    wkt: ").append(Utils.toIndentedString(this.wkt)).append("\n");
		sb.append("}");
		return sb.toString();
	}
	
	

}
