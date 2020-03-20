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

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Capgemini
 * @version 0.0.1
 * 
 */
public class ExceptionUtils {
	
	/**
	 * 
	 * @param throwable
	 * @return String StackTrace
	 */
	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}

}
