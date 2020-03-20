package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The DataFormat class is used to determine the format of a data
 * 
 * @author tkossoko
 */
public enum DataFormat {

	AZ("AZ"), RG("RG"), DEM("DEM"), ROI("ROI"), SLC_HH("SLC_HH"), SLC_VV("SLC_VV"), SLC_VH("SLC_VH"), SLC_HV(
			"SLC_HV"), INC("INC"), KZ("KZ"), GRD("GRD"), L2("L2"), OTHER("OTHER");

	private String value;

	/**
	 * Constructor of the enum class
	 * 
	 * @param value
	 */
	DataFormat(String value) {
		this.value = value;
	}

	/**
	 * Gets the set of the Dataformat
	 */

	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static DataFormat fromValue(String text) {
		for (DataFormat b : DataFormat.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
