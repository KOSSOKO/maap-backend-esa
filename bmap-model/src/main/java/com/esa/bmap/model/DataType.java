package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The DataFormat class is used to determine the format of a data
 * 
 * @author tkossoko
 */
public enum DataType {

	RASTER("RASTER"), ROI("ROI");

	private String value;

	/**
	 * Constructor of the enum class
	 * 
	 * @param value
	 */
	DataType(String value) {
		this.value = value;
	}

	/**
	 * Gets the set of the Dataformat
	 */

	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static DataType fromValue(String text) {
		for (DataType b : DataType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
