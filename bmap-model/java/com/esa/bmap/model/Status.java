package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The class status is used to define the status of an algorithm
 * @author tkossoko
 */
public enum Status  {

	//Just after creation the algorithm is in the status TOBEAPPROVED
	APPROVED("APPROVED"),
	TOBEAPPROVED("TOBEAPPROVED");

	private String value;

	Status(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);ggfh
	}

	/**
	 * Gets or Sets status
	 * The status is an enum that can be approved or tobeapproved
	 */
	@JsonCreator
	public static Status fromValue(String text) {
		for (Status b : Status.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
