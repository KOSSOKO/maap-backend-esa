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
package com.esa.bmap.common;

import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Capgemini
 * @version 0.0.1
 */
public class Common {

	private static final Logger LOG = LoggerFactory.getLogger(Common.class);

	private static final String MESSAGESFILE = "messages.properties";

	private static final String APPLICATIONFILE = "application-ext.properties";

	/**
	 * Return the value of a key in the properties file.
	 * 
	 * @param key
	 * The key to read.
	 * @return The value of the key.
	 */
	public static String getPropertiesValue(String key) {

		String value = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();

		try (InputStream resourceStream = loader.getResourceAsStream(APPLICATIONFILE)) {

			// load a properties file
			properties.load(resourceStream);
			value = properties.getProperty(key);

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
		}

		return value;
	}

	/**
	 * Return the value of a key in the properties file.
	 * 
	 * @param key
	 * The key to read.
	 * @return The value of the key.
	 */
	public static String getMessageValue(String key) {

		String value = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties();

		try (InputStream resourceStream = loader.getResourceAsStream(MESSAGESFILE)) {

			// load a properties file
			properties.load(resourceStream);
			value = properties.getProperty(key);

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
		}

		return value;
	}
}
