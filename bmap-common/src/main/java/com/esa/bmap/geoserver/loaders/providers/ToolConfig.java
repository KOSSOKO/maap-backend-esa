/*
 * $Id: ToolConfig.java 5301 2017-09-07 08:08:54Z csouriss $
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

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrSubstitutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.exceptions.BmapException;
//import com.dga.ccs.maps.loader.exceptions.ConfigRuntimeException;
import com.esa.bmap.geoserver.loaders.ReadOnlyProperties;

/**
 * Singleton class providing centralized access to this tool's configuration parameters.
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public class ToolConfig {

	/** Relative path to this tool's main configuration file. */
	private static final String MAIN_CONFIG_FILE_LOC = "./config/maploader.properties"; //$NON-NLS-1$

	/**
	 * Relative path to the file embedding BDTOPO-related "business"-level configuration parameters.
	 */
	private static final String BDTOPO_CONFIG_FILE_LOC = "./config/bdtopo.properties"; //$NON-NLS-1$

	/**
	 * Pattern of the relative path to the file defining which map loading forms should be displayed within the GUI.
	 */
	private static final String FORMS_CONFIG_FILES_LOC_PATTERN = "./config/forms/enabled-forms-${tool.environment}.conf"; //$NON-NLS-1$

	/**
	 * Singleton instance of this provider.
	 */
	private static final ToolConfig INSTANCE = new ToolConfig();

	/**
	 * Set of loaded main configuration parameters.
	 */
	private final Properties mainConfig = new ReadOnlyProperties();

	/**
	 * Set of loaded "business"-level configuration parameters related to the BDTOPO.
	 */
	private final Properties bdTopoConfig = new ReadOnlyProperties();

	/**
	 * Key-list of enabled map loading forms.
	 * <p>
	 * The keys of this list shall match some `forms.&lt;key&gt;`-like properties within the config file depicted by {@link #MAIN_CONFIG_FILE_LOC}.
	 * </p>
	 */
	private final List<String> enabledFormsConfig = new ArrayList<>();

	private static final Logger LOG = LoggerFactory.getLogger(ToolConfig.class);

	/**
	 * Private constructor prohibiting other instantiations of this singleton class.
	 */
	private ToolConfig() {
		super();
	}

	/**
	 * Get this singleton's instance.
	 * 
	 * @return The singleton's instance.
	 */
	public static ToolConfig getInstance() {
		return INSTANCE;
	}

	/**
	 * Initialize this singleton by loading the tool's configuration files content.
	 * 
	 * @throws BmapException
	 */
	public void init() throws BmapException {
		loadContent(MAIN_CONFIG_FILE_LOC, mainConfig);
		loadContent(BDTOPO_CONFIG_FILE_LOC, bdTopoConfig);
		String formsConfigFileLoc = StrSubstitutor.replace(FORMS_CONFIG_FILES_LOC_PATTERN, mainConfig);
		loadContent(formsConfigFileLoc, enabledFormsConfig);
	}

	/**
	 * Load the content of some of this tool's configuration files.
	 * 
	 * @param fileLocation
	 * The relative location of the configuration file to load.
	 * @param target
	 * The properties wrapper that will memorize loaded configuration parameters.
	 * @throws BmapException
	 */
	private void loadContent(String fileLocation, Properties target) throws BmapException {
		try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(fileLocation)) {
			target.load(input);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
			// throw new ConfigRuntimeException(Messages.getInstance().get(
			// "maps_loader.errors.cannot_load_config", //$NON-NLS-1$
			// fileLocation), e);
		}
	}

	/**
	 * Load the content of some of this tool's configuration files.
	 * 
	 * @param fileLocation
	 * The relative location of the configuration file to load.
	 * @param target
	 * The list that will memorize loaded configuration lines.
	 * @throws BmapException
	 */
	private void loadContent(String fileLocation, List<String> target) throws BmapException {
		try {
			Path filePath = Paths.get(this.getClass().getClassLoader().getResource(fileLocation).toURI());
			try (Stream<String> linesStream = Files.lines(filePath)) {
				linesStream.map(String::trim).filter(line -> StringUtils.isNotBlank(line) && !line.startsWith("#")) //$NON-NLS-1$
						.collect(Collectors.toCollection(() -> target));
			}
		} catch (Exception e) {
			// throw new ConfigRuntimeException(Messages.getInstance().get(
			// "maps_loader.errors.cannot_load_config", //$NON-NLS-1$
			// fileLocation), e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

	/**
	 * Get this tool's main configuration parameters.
	 * 
	 * @return The set of loaded configuration parameters.
	 */
	public Properties getMainConfig() {
		return mainConfig;
	}

	/**
	 * Get "business"-level configuration parameters related to the BDTOPO.
	 * 
	 * @return The set of loaded configuration parameters in question.
	 */
	public Properties getBdTopoConfig() {
		return bdTopoConfig;
	}

	/**
	 * Get the list of enabled map loading forms.
	 * 
	 * @return A list of keys indicating which map loading forms shall be displayed within the GUI.
	 * <p>
	 * The keys of this list shall match some `forms.&lt;key&gt;`-like properties within the config file depicted by {@link #MAIN_CONFIG_FILE_LOC}.
	 * </p>
	 */
	public List<String> getEnabledFormsConfig() {
		return enabledFormsConfig;
	}

}
