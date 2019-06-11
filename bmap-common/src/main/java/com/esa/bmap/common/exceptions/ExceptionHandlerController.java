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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Capgemini
 * @version 0.0.1
 * 
 */
@ControllerAdvice
public class ExceptionHandlerController {
	
	@Autowired
	ObjectMapper objectMapper;
	
	Logger log = LoggerFactory.getLogger(ExceptionHandlerController.class);
	
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception e) {
        // log exception
    	
    	log.error("an exception has been caught",e);
    	
    	ExceptionResponse response = new ExceptionResponse();
    	response.setStatus("ERROR");
    	
    	//By default, it's an internal servor error
    	int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    	HttpStatus statusHttp = HttpStatus.INTERNAL_SERVER_ERROR;
    	
        //But we can caught specific exception
    	if(e instanceof BmapException) {
    		
    		if(((BmapException)e).getStatusCode() != null) {
    			statusCode=((BmapException)e).getStatusCode().value();
    		}
    		
    		statusHttp=HttpStatus.resolve(statusCode);
    	}
    	
    	response.setMessage(e.getMessage());
    	response.setStackTrace(ExceptionUtils.getStackTrace(e));
    	response.setHttpCode(statusCode);
    	
    	/**If after all verification we have null in the status, we return 
    	 * an internal server error
    	 * 
    	 */
    	if(statusHttp == null) {
    		statusHttp = HttpStatus.INTERNAL_SERVER_ERROR;
    	}
    	
        try {
			return ResponseEntity
			        .status(statusHttp)
			        .body(objectMapper.writeValueAsString(response));
			
		} catch (JsonProcessingException e1) {
			return ResponseEntity
			        .status(HttpStatus.INTERNAL_SERVER_ERROR)
			        .body(ExceptionUtils.getStackTrace(e1));
		}
    } 
}
