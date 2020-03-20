package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The DataFormat class is used to determine the format of a data
 * 
 * @author tkossoko
 */
public enum SLCTypes {

	SLC_HH("SLC_HH"), SLC_VV("SLC_VV"), SLC_VH("SLC_VH"), SLC_HV("SLC_HV");

	private String value;

	/**
	 * Constructor of the enum class
	 * 
	 * @param value
	 */
	SLCTypes(String value) {
		this.value = value;
	}

	/**
	 * Gets the set of the Dataformat
	 */

	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static SLCTypes fromValue(String text) {
		for (SLCTypes b : SLCTypes.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
