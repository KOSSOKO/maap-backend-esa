package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The algorithm class is used to determine the privacy of an algorithm. It can be private or public
 * @author tkossoko
 */
public enum Privacy {
	
	//An algorithm can be private or public
	PRIVATE("PRIVATE"),
	PUBLIC("PUBLIC");

	private String value;

	/**
	 * Constructor of the enum class
	 * @param value
	 */
	Privacy(String value) {
		this.value = value;
	}

	
	/**
	 * Gets or Sets privacy
	 * The privacy is an enum that can be private or public
	 */

	public String toString() {
		return String.valueOf(value);
	}
	
	@JsonCreator
	public static Privacy fromValue(String text) {
		for (Privacy b : Privacy.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
