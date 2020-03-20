package com.esa.bmap.model;

/**
 * This enumeration contains the quadrangle types.
 * 
 * @author QFAURE
 *
 */
public enum QuadrangleType {

	/**
	 * Available point types.
	 */
	XY("XY"), LATLONG("LATLONG");

	/**
	 * Stored point type.
	 */
	private String value = null;

	/**
	 * Creates a quadrangle type with the specified parameter.
	 * 
	 * @param value
	 */
	QuadrangleType(String value) {
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
	public static QuadrangleType fromValue(String text) {
		for (QuadrangleType b : QuadrangleType.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
