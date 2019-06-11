package com.esa.bmap.geoserver.loaders;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.loaders.interfaces.GeotiffLoaderInterface;

import it.geosolutions.geoserver.rest.GeoServerRESTPublisher;
import it.geosolutions.geoserver.rest.encoder.GSResourceEncoder.ProjectionPolicy;

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
	 * @param workspace
	 * The name of the versioned workspace to which the shapefile shall be loaded.
	 * @param storeName
	 * The shapefile store base name.
	 * @param geotiffFilePath
	 * The geotiff file path.
	 * @param layername
	 * The given layer name that will be visible on geoserver.
	 * @throws BmapException
	 * If anything wrong happens when publishing the shapefile.
	 */
	@Override
	public void publishGeotiff(String workspace, String storeName, String geotiffFilePath, String layername) throws BmapException {

		File geotiffFile = new File(geotiffFilePath);

		GeoServerRESTPublisher geoServerRESTPublisher = new GeoServerRESTPublisher(geoserverUrl, geoserverUsername, geoserverPassword);

		try {
			geoServerRESTPublisher.publishExternalGeoTIFF(workspace, storeName, geotiffFile, layername, "EPSG:4326", ProjectionPolicy.REPROJECT_TO_DECLARED, "raster");
			LOG.info(layername + " was successfully published to Geoserver");

		} catch (Exception e) {
			LOG.error(e.getLocalizedMessage(), e);
			throw new BmapException(e.getLocalizedMessage(), e);
		}

	}

	@Override
	public void publishWorldImage(String workspace, String coveragestore, File zipFile) throws BmapException {

		GeoServerRESTPublisher geoServerRESTPublisher = new GeoServerRESTPublisher(geoserverUrl, geoserverUsername, geoserverPassword);

		try {

			geoServerRESTPublisher.publishWorldImage(workspace, coveragestore, zipFile);
			LOG.info(zipFile.getName() + " was successfully published to Geoserver");

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}

	}

}
