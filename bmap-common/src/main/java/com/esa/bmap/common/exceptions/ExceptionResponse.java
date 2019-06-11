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

/**
 * @author Capgemini
 * @version 0.0.1
 * 
 */
public class ExceptionResponse {
	
	private String status;	
	
	private int httpCode;
	
	private String message;
	
	private String stackTrace;

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
