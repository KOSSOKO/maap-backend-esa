/*
 * $Id: LoaderException.java 4587 2017-05-29 13:03:47Z acannac $
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
 * Exception thrown when some uncatchable error is encountered when loading some
 * cartographic data.
 *
 * @author $Author: acannac $
 * @version $Revision: 4587 $
 */
public class LoaderException extends Exception {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = -8855149157081711306L;

    /**
     * Constructor with message only.
     * 
     * @param message
     *            The detail message (which is saved for later retrieval by the
     *            {@link #getMessage()} method).
     */
    public LoaderException(String message) {
        super(message);
    }

    /**
     * Constructor with message and cause.
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
    public LoaderException(String message, Throwable cause) {
        super(message, cause);
    }

}
