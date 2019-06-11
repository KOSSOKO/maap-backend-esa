package com.esa.bmap.model;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * The class status is used to define the status of an algorithm
 * @author tkossoko
 */
public enum Status  {

	//CALLABORATE is when the algorithm is still in a private repo
	//SHARE is when the algorithm is moved in a public repo accessible by eveybody 
	//PUBLISHED is when the algorithm is stamp by ESA
	COLLABORATE("COLLABORATE"),
	SHARE("SHARE"),
	PUBLISHED("PUBLISHED");

	private String value;

	Status(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
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
