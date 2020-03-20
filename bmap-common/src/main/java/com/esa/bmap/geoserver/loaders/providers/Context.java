/*
 * $Id: Context.java 5301 2017-09-07 08:08:54Z csouriss $
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
package com.esa.bmap.geoserver.loaders.providers;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.loaders.constants.MapFileExtensions;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

/**
 * Singleton class providing contextual data and services to any component of this SAR maps loader.
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public class Context {

	/**
	 * Sub-directory of this tool's directory containing reference SLD style files.
	 */
	private static final String BASE_STYLES_ROOT = "./sld/"; //$NON-NLS-1$

	/**
	 * Singleton instance of this context provider.
	 */
	private static final Context INSTANCE = new Context();

	/**
	 * Console always visible within this UI, within which any SAR maps loader component can display information.
	 */
	private TextArea sharedConsole;

	private static final Logger LOG = LoggerFactory.getLogger(Context.class);

	/**
	 * Private constructor prohibiting other instantiations of this singleton class.
	 */
	private Context() {
		super();
	}

	/**
	 * Get this singleton's context instance.
	 * 
	 * @return The singleton's context instance.
	 */
	public static Context getInstance() {
		return INSTANCE;
	}

	/**
	 * Initialize this singleton provider's data.
	 */
	public void init() {
		sharedConsole = new TextArea();
	}

	/**
	 * Append text to the shared informative console.
	 * 
	 * @param text
	 * The text to add.
	 */
	public void addTextToConsole(String text) {
		Platform.runLater(() -> sharedConsole.appendText(text + '\n'));
	}

	/**
	 * Get the shared informative console (within which data can displayed by any SAR maps loader component).
	 * 
	 * @return The shared console.
	 */
	public TextArea getSharedConsole() {
		return sharedConsole;
	}

	/**
	 * Get some reference SLD style file from this tool's configuration directory.
	 * 
	 * @param styleName
	 * The name (without extension) of the requested style file.
	 * @return The requested style file's path, or {@code null} if it wasn't found.
	 * @throws BmapException
	 */
	public Path getStyleFile(String styleName) throws BmapException {
		String sldFileName = MapFileExtensions.concat(styleName, MapFileExtensions.SLD);
		URL sldFileUrl = getClass().getClassLoader().getResource(BASE_STYLES_ROOT + sldFileName);
		try {
			return sldFileUrl == null ? null : Paths.get(sldFileUrl.toURI());
		} catch (Exception e) {
			// throw new ConfigRuntimeException(Messages.getInstance().get(
			// "maps_loader.errors.cannot_load_configured_sld", //$NON-NLS-1$
			// sldFileUrl), e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}
}
