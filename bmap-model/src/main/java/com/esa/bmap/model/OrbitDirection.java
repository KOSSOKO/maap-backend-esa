package com.esa.bmap.model;

/**
 * This enumeration contains the orbit directions.
 * 
 * @author QFAURE
 *
 */
public enum OrbitDirection {

	/**
	 * Available orbit directions.
	 */
	NONE("NONE"), DESENDING("DESENDING"), ASCENDING("ASCENDING");

	/**
	 * Stored orbit direction.
	 */
	private String value = null;

	/**
	 * Creates an orbit direction with the specified parameter.
	 * 
	 * @param value
	 */
	OrbitDirection(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	/**
	 * Checks if the specified text matches a constant of the enumeration.
	 * 
	 * @param text
	 * @return
	 */
	public static OrbitDirection fromValue(String text) {
		for (OrbitDirection b : OrbitDirection.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
