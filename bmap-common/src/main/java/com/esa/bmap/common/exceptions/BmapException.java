/*
 * $Id$
 *
 * ======================================================
 *
 * Project : Biomass
 * Produit par Capgemini.
 *
 * ======================================================
 * HISTORIQUE
 * FIN-HISTORIQUE
 * ======================================================
 */
package com.esa.bmap.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * @author Capgemini
 * @version 0.0.1
 * 
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class BmapException extends Exception {

	/**
	 * Unique identifier for Serializable classes
	 */
	private static final long serialVersionUID = -5388107316843808444L;

	/**
	 * Status
	 */
	private HttpStatus statusCode;
	
	/**
	 * Default constructor
	 */
	public BmapException() {
		super();
	}
	
	/**
	 * 
	 * @param messageKey
	 * @param cause
	 */
	public BmapException(String messageKey, Throwable cause) {
		super(messageKey, cause);
	}
	
	/**
	 * 
	 * @param messageKey
	 * @param codeError
	 * @param cause
	 */
	public BmapException(String messageKey, HttpStatus codeError, Throwable cause) {
		super(messageKey, cause);
		this.setStatusCode(codeError);
	}
	
	/**
	 * 
	 * @param messageKey
	 * @param codeError
	 * @param cause
	 */
	public BmapException(String messageKey, HttpStatus codeError) {
		super(messageKey);
		this.setStatusCode(codeError);
	}
	
	/**
	 * 
	 * @param messageKey
	 */
	public BmapException(String messageKey) {
		super(messageKey);
	}
	
	/**
	 * Get the status code of error
	 * @param statusCode
	 */
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	
	/**
	 * Return the status code
	 * @return int the statusCode
	 */
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	
}

