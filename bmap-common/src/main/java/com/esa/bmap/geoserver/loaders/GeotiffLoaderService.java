package com.esa.bmap.geoserver.loaders;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.loaders.interfaces.GeotiffLoaderInterface;

@Service(value = "GeotiffLoaderInterface")
public class GeotiffLoaderService implements GeotiffLoaderInterface {
	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(GeotiffLoaderService.class);

	@Value("${geoserver.url}")
	private String geoserverUrl;

	@Value("${datasource.dataDirectory}")
	private String dataDirectory;

	@Value("${geoserver.username}")
	private String geoserverUsername;

	@Value("${geoserver.password}")
	private String geoserverPassword;

	/**
	 * Publish the geotiff layer on geoserver.
	 * 
	 * @param workspace The name of the versioned workspace to which the shapefile
	 *            shall be loaded.
	 * @param storeName The shapefile store base name.
	 * @param geotiffFilePath The geotiff file path.
	 * @param layername The given layer name that will be visible on geoserver.
	 * @throws BmapException If anything wrong happens when publishing the
	 *             shapefile.
	 */
	@Override
	public void publishGeotiff(String workspace, String storeName, String geotiffFilePath, String layername,
			String style) throws BmapException {
		// defining style if null
		if (style == null) {
			style = DEFAULT_STYLE;
		}

		File geotiffFile = new File(geotiffFilePath);

		GeoServerRESTPublisher geoServerRESTPublisher = new GeoServerRESTPublisher(geoserverUrl, geoserverUsername,
				geoserverPassword);

		try {
			// publishing layer to geoserver
			geoServerRESTPublisher.publishExternalGeoTIFF(workspace, storeName, geotiffFile, layername, EPSG_4326,
					ProjectionPolicy.REPROJECT_TO_DECLARED, style);
			LOG.info(layername + " was successfully published to Geoserver with style: " + style);

		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			throw new BmapException(e.getLocalizedMessage(), e);
		}

	}

	@Override
	public void publishWorldImage(String workspace, String coveragestore, File zipFile) throws BmapException {

		GeoServerRESTPublisher geoServerRESTPublisher = new GeoServerRESTPublisher(geoserverUrl, geoserverUsername,
				geoserverPassword);

		try {

			if (geoServerRESTPublisher.publishWorldImage(workspace, coveragestore, zipFile)) {
				LOG.info("    successfully published to Geoserver : " + zipFile.getName());
			} else {
				LOG.error("    fail to published to Geoserver : " + zipFile.getName());
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}

	}

}
