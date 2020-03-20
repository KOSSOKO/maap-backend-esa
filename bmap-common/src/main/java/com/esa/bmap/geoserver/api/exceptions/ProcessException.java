/*
 * $Id: ProcessException.java 5301 2017-09-07 08:08:54Z csouriss $
 *
 * ======================================================
 *
 * Project : CCS
 * Produit par Capgemini.
 *
 * ======================================================
 * HISTORIQUE
 * FIN-HISTORIQUE
 * ======================================================
 */
package com.esa.bmap.geoserver.api.exceptions;

/**
 * Exception thrown to indicate an error occurrence related to some external
 * process execution.
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public class ProcessException extends Exception {

    /** Serialization UID. */
    private static final long serialVersionUID = 4265755848590035193L;

    /**
     * Constructor with message parameter only.
     * 
     * @param message
     *            The detail message (which is saved for later retrieval by the
     *            {@link #getMessage()} method).
     */
    public ProcessException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause parameters.
     * 
     * @param message
     *            The detail message (which is saved for later retrieval by the
     *            {@link #getMessage()} method).
     * @param cause
     *            The cause (which is saved for later retrieval by the
     *            {@link #getCause()} method). A {@code null} value is
     *            permitted, and indicates that the cause is nonexistent or
     *            unknown.
     */
    public ProcessException(String message, Throwable cause) {
        super(message, cause);
    }

}
