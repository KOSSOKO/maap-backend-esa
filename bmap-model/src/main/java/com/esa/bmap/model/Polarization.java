package com.esa.bmap.model;

/**
 * This enumeration contains the polarizations.
 * 
 * @author QFAURE
 *
 */
public enum Polarization {

	/**
	 * Available polarizations.
	 */
	HH("HH"), HV("HV"), VH("VH"), VV("VV");

	/**
	 * Stored polarization.
	 */
	private String value = null;

	/**
	 * Creates a polarization with the specified parameter.
	 * 
	 * @param value
	 */
	Polarization(String value) {
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
	public static Polarization fromValue(String text) {
		for (Polarization b : Polarization.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
