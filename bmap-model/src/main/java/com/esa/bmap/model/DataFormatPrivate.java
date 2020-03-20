package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The DataFormat class is used to determine the format of a data
 * 
 * @author tkossoko
 */
public enum DataFormatPrivate {

	AZ("AZ"), RG("RG"), DEM("DEM"), ROI("ROI"), SLC("SLC"), INC("INC"), KZ("KZ"), GRD("GRD");

	private String value;

	/**
	 * Constructor of the enum class
	 * 
	 * @param value
	 */
	DataFormatPrivate(String value) {
		this.value = value;
	}

	/**
	 * Gets the set of the Dataformat
	 */

	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static DataFormatPrivate fromValue(String text) {
		for (DataFormatPrivate b : DataFormatPrivate.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
