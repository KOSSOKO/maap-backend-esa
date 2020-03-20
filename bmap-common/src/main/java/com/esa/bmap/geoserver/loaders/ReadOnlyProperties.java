/*
 * $Id: ReadOnlyProperties.java 5301 2017-09-07 08:08:54Z csouriss $
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
package com.esa.bmap.geoserver.loaders;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.geoserver.api.exceptions.ConfigRuntimeException;
//import com.dga.ccs.maps.loader.Messages;
//import com.dga.ccs.maps.loader.exceptions.ConfigRuntimeException;

/**
 * Standard {@link Properties} specialization implying:
 * <ul>
 * <li>a read-only properties set (meaning that the {@link #setProperty(String, String)} method cannot be used),</li>
 * <li>the throw of a {@link ConfigRuntimeException} exception when some requested property doesn't exist.</li>
 * </ul>
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public class ReadOnlyProperties extends Properties {

	/**
	 * Generated serial version UID.
	 */
	private static final long serialVersionUID = 8002091845206453981L;

	private static final Logger LOG = LoggerFactory.getLogger(ReadOnlyProperties.class);

	/**
	 * Sole inherited public constructor.
	 */
	public ReadOnlyProperties() {
		super();
	}

	/**
	 * Searches for the property with the specified key in this property list. If the key is not found in this property list, the default property list, and its defaults, recursively, are then checked.
	 * <p>
	 * The method throws a {@link ConfigRuntimeException} if the property is not found.
	 * </p>
	 * 
	 * @see java.util.Properties#getProperty(java.lang.String)
	 */
	@Override
	public String getProperty(String key) {
		// throws ConfigRuntimeException {
		String result = super.getProperty(key);
		if (result == null) {
			// throw new ConfigRuntimeException(Messages.getInstance()
			// .get("maps_loader.errors.cannot_find_property", key)); //$NON-NLS-1$
		} else {
			return result;
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * If the case of some "read-only" properties instance, this method cannot be called (some runtime exception would be thrown).
	 * </p>
	 * 
	 * @see java.util.Properties#setProperty(java.lang.String, java.lang.String)
	 */
	@Override
	public synchronized Object setProperty(String key, String value) {

		LOG.error(Common.getMessageValue("readonlyproperties.setproperty.error"));
		throw new UnsupportedOperationException(Common.getMessageValue("readonlyproperties.setproperty.error")); //$NON-NLS-1$
	}
}
