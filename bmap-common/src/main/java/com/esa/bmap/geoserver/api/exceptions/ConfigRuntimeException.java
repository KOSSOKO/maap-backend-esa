/*
 * $Id: ConfigRuntimeException.java 5301 2017-09-07 08:08:54Z csouriss $
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
 * Exception thrown when facing the impossibility of loading this Java tool's
 * global configuration parameters.
 * <p>
 * The tool should terminate in error (and not even get launched) when such
 * error is thrown.
 * </p>
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public class ConfigRuntimeException extends RuntimeException {

    /**
     * Generated serial version UID.
     */
    private static final long serialVersionUID = 4797561572177630315L;

    /**
     * Constructor with message only.
     * 
     * @param message
     *            The detail message (which is saved for later retrieval by the
     *            {@link #getMessage()} method).
     */
    public ConfigRuntimeException(String message) {
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
    public ConfigRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
