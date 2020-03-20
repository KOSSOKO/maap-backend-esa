package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The DataFormat class is used to determine the format of a data
 * 
 * @author tkossoko
 */
public enum GeometryTypes {

	GEOLOCATED("geolocated"), NON_GEOLOCATED("non-geolocated");

	private String value;

	/**
	 * Constructor of the enum class
	 * 
	 * @param value
	 */
	GeometryTypes(String value) {
		this.value = value;
	}

	/**
	 * Gets the set of the Dataformat
	 */

	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static GeometryTypes fromValue(String text) {
		for (GeometryTypes b : GeometryTypes.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
