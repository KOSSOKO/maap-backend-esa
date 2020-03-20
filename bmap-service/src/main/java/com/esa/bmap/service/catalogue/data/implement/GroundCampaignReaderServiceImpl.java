package com.esa.bmap.service.catalogue.data.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.AbstractGridFormat;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.coverage.grid.io.GridFormatFinder;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffFormat;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.gce.image.WorldImageFormat;
import org.geotools.geometry.jts.JTS;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.referencing.CRS;
import org.geotools.referencing.ReferencingFactoryFinder;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CRSAuthorityFactory;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.loaders.interfaces.GeotiffLoaderInterface;
import com.esa.bmap.geoserver.loaders.interfaces.ShapeFileLoaderInterface;
import com.esa.bmap.model.Algorithm;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.DataFormat;
import com.esa.bmap.model.DataFormatPrivate;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.Platform;
import com.esa.bmap.model.Polarization;
import com.esa.bmap.model.Privacy;
import com.esa.bmap.model.Quadrangle;
import com.esa.bmap.model.QuadrangleType;
import com.esa.bmap.model.Status;
import com.esa.bmap.model.SubRegion;
import com.esa.bmap.model.util.GranuleHelper;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
import com.esa.bmap.service.catalogue.data.interfaces.GroundCampaignReaderServiceInterface;
import com.esa.bmap.service.dataprocessing.interfaces.RasterStatisticsServiceInterface;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.exception.ObsException;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * @author gscotto the class that indexes all the ground campaigns in the
 *         database
 */
@Service(value = "GroundCampaignReaderServiceInterface")
public class GroundCampaignReaderServiceImpl implements GroundCampaignReaderServiceInterface {

	private final Logger LOG = LoggerFactory.getLogger(GroundCampaignReaderServiceImpl.class);

	// Constante Not Applicable for a plateform
	private static final String N_A_PLATEFORM = "n/a";

	// Constante Not Applicable for a dem
	private static final String N_A_DEM = "n/a";

	// Constante Not Applicable for a master
	private static final String N_A_MASTER = "n/a";

	// Geoserver workspace where the data layers will be published
	@Value("${geoserver.workspace}")
	private String GEOSERVER_WORKSPACE;

	// Geoserver workspace where the data layers will be published
	@Value("${geoserver.url}")
	private String GEOSERVER_URL_CARTO;

	// Bucket S3 url
	@Value("${bucketS3.url}")
	private String urlToData;

	// temporary private user dir for ingestion
	@Value("${datasource.privateUserDataDirTemp}")
	private String privateUserTempDir;
	@Value("${bucketS3.userfolder}")
	private String s3UserFolder;
	// temporary private user dir for ingestion
	@Value("${datasource.privateUserDataDir}")
	private String privateUserFinalDir;

	// directory on python server for user data
	@Value("${pythonserver.userdatadir}")
	private String privateUserPythonDir;
	// directory on python server for campaign data
	@Value("${pythonserver.datadir}")
	private String campaignDataDir;

	@Value("${pythonserver.usercollectiondir}")
	private String s3UserDir;

	// Service handling database ingestion for data model objects
	@Autowired
	private DataCatalogServiceInterface dataService;

	// Service handling geoserver ingestion for shapefile type objects
	@Autowired
	ShapeFileLoaderInterface shploader;

	// Service handling geoserver ingestion for raster type objects
	@Autowired
	GeotiffLoaderInterface geoLS;

	// Service handling raster basic statistics rendering
	@Autowired
	RasterStatisticsServiceInterface rasterStats;

	// Service to manage data catalogue
	@Autowired
	private DataCatalogServiceInterface dataCatalogueService;

	// Service to manage s3Buckets
	@Autowired
	private S3BucketService s3BucketService;

	// s3 endpoint
	@Value("${bucketS3.endpoint}")
	public String s3endpoint;

	// s3 admin access key
	@Value("${bucketS3.access.key}")
	public String s3AdminAccessKey;

	// s3 admin access key
	@Value("${bucketS3.secret.key}")
	public String s3AdminSecretKey;

	@Value("${bucketS3.copa.access.key}")
	public String s3CopaAccessKey;

	// s3 admin access key
	@Value("${bucketS3.copa.secret.key}")
	public String s3CopaSecretKey;

	// s3 bucket copa name
	@Value("${bucketS3.copa.name}")
	public String s3CopaBucketName;

	// s3 connection timeout
	@Value("${bucketS3.connection.timeout}")
	public String s3ConnectionTimeout;
	// s3 connection timeout
	@Value("${bucketS3.socket.timeout}")
	public String s3SocketTimeout;

	/**
	 * for a given tag scene, searches all the tiff files and gives it to
	 * retrieveAirbornData private method
	 * 
	 * @param eElement it's
	 * @param baseDir
	 * @throws FactoryException
	 * @throws BmapException
	 */
	public void searchAirbornFile(Granule granuleScene, String f_AcquisitionDate, String campaignName,
			String folderName, String instrumentName) throws BmapException {

		// If the Granule is null we raise an error
		// The granule must be set before entering in the method
		if (granuleScene == null) {
			throw new BmapException("Null pointer Exception. The Granule Scene is null");
		}

		ArrayList<String> extensionList = new ArrayList<String>();
		extensionList.add(DataFormat.AZ.toString().toLowerCase());
		extensionList.add(DataFormat.RG.toString().toLowerCase());
		extensionList.add(DataFormat.DEM.toString().toLowerCase());
		extensionList.add(DataFormat.KZ.toString().toLowerCase());
		extensionList.add(DataFormat.INC.toString().toLowerCase());
		extensionList.add(DataFormat.SLC_HH.toString());
		extensionList.add(DataFormat.SLC_HV.toString());
		extensionList.add(DataFormat.SLC_VV.toString());
		extensionList.add(DataFormat.SLC_VH.toString());

		// if dem or master values are empty then set the value to non applicable n/a
		if (granuleScene.getDem() == null || granuleScene.getDem().equals("")) {
			granuleScene.setDem(N_A_DEM);
		}
		if (granuleScene.getMaster() == null || granuleScene.getMaster().equals("")) {
			granuleScene.setMaster(N_A_MASTER);
		}

		// Creating Scene Granule and ingest in database
		granuleScene = saveOrUpdateGranuleScene(granuleScene);
		granuleScene.getGranuleList().clear();

		LOG.info("Searching " + campaignName + "_" + granuleScene.getName() + " possible files");

		// Iterate through the extensionList to check the existence of each type of
		// data. retrieve metadata and create Granule Data when found
		for (String currentExtension : extensionList) {

			// Getting other attributes
			String f_dataType = currentExtension;
			String f_name = campaignName + "_" + granuleScene.getName() + "_" + f_dataType;
			String dataName = campaignName + "_" + granuleScene.getName() + "_" + f_dataType + ".tiff";

			// Getting Data path on file System
			Path path_dataType = java.nio.file.Paths.get(folderName, dataName);
			File file = new File(path_dataType.toString());

			LOG.info("checking if " + granuleScene.getName() + " exists at path: " + path_dataType.toString());

			// if the file exists and is not a directory, we create the Airborne Data object
			if (file.exists() && !file.isDirectory()) {
				granuleScene.addToGranuleList(dataName);

				LOG.info(f_name + " exists");

				// if dataType is az or rg or dem, we use Geotiff Reader otherwise we use
				// WorldImage Reader
				if (f_dataType.equals(DataFormat.AZ.toString().toLowerCase())
						|| f_dataType.equals(DataFormat.RG.toString().toLowerCase())
						|| f_dataType.equals(DataFormat.DEM.toString().toLowerCase())) {

					// if the given data is a georeferenced type (az, rg, dem) then its type is
					// GEOTIFF
					ingestGeotiff(file, f_AcquisitionDate, f_name, f_dataType, granuleScene);
				} else {

					// if the given data is a georeferenced type (az, rg, dem) then its type is
					// WorldImage
					ingestWorldImage(file, f_AcquisitionDate, f_name, f_dataType, granuleScene);
				}

			} else {

				LOG.info(f_name + " doesn't exist");
			}
		}

		// Set the Quadrangle from the dem scene and the ref_dem scene itself
		if (granuleScene.getDem() != null && !granuleScene.getDem().equals(N_A_DEM)) {
			File demFile = getDemFileStartingFromAnotherTiffFile(granuleScene.getDem(), granuleScene.getCollection(),
					new File(folderName));
			try {
				granuleScene.setQuadrangle(createQuadrangle(demFile).getQuadrangle());
			} catch (IOException | FactoryException | TransformException e) {
				LOG.error(e.getMessage(), e);
				throw new BmapException("Failed to set granule Scene quadrangle with dem quadrangle ", e);

			}
		}

		// Verifying if granule Scene exists in database. If exists, update it, if not,
		// save it
		granuleScene = saveOrUpdateGranuleScene(granuleScene);

	}

	/**
	 * @param granuleScene
	 * @throws BmapException
	 */
	private Granule saveOrUpdateGranuleScene(Granule granuleScene) throws BmapException {
		Granule granuleDB = dataService.getGranuleByGranuleNameDataBase(
				granuleScene.getCollection().getShortName() + Granule.GRANULE_ID_DELIMITER + granuleScene.getName());
		if (granuleDB != null) {
			LOG.info("The granule scene is already ingested in database");
			LOG.info("Updating Granule Scene ...");
			granuleDB.setHeading(granuleScene.getHeading());
			granuleDB.setzFlight(granuleScene.getzFlight());
			granuleDB.setzTerrain(granuleScene.getzTerrain());
			granuleDB.setSlrStart(granuleScene.getSlrStart());
			granuleDB.setPixelSpacing(granuleScene.getPixelSpacing());
			granuleDB.setSurfaceResol(granuleScene.getSurfaceResol());
			granuleDB.setGrdResol(granuleScene.getGrdResol());
			granuleDB.setUpdateDate(granuleScene.getUpdateDate());
			granuleDB.setDem(granuleScene.getDem());
			granuleDB.setMaster(granuleScene.getMaster());
			granuleDB.setPlatform(granuleScene.getPlatform());
			granuleDB.setQuadrangle(granuleScene.getQuadrangle());
			granuleDB.setSubRegion(granuleScene.getSubRegion());
			granuleDB = dataService.addGranule(granuleDB);
			LOG.info(Common.getMessageValue("hibernate.update.successfull"), granuleScene.getName());

		} else {
			LOG.info("New Granule Scene detected");
			LOG.info("Ingesting new Granule Scene in Database");
			granuleDB = dataService.addGranule(granuleScene);
			LOG.info(Common.getMessageValue("hibernate.save.successfull"), granuleScene.getName());

		}
		return granuleDB;
	}

	/**
	 * Returns an instance of quadrangle constructed from the specified general
	 * envelope or referenced envelope.
	 * 
	 * @param env A general envelope or referenced envelope containing the 4 corners
	 *            of the data.
	 * @return An instance of quadrangle.
	 * @throws BmapException When the specified envelope is of an incorrect type.
	 */
	public Quadrangle setQuadrangleFromEnvelope(Envelope env) throws BmapException {

		float minX;
		float minY;
		float maxX;
		float maxY;
		QuadrangleType quadrangleType;

		switch (env.getClass().getSimpleName()) {

		case "GeneralEnvelope":

			minX = (float) env.getLowerCorner().getOrdinate(0);
			minY = (float) env.getLowerCorner().getOrdinate(1);
			maxX = (float) env.getUpperCorner().getOrdinate(0);
			maxY = (float) env.getUpperCorner().getOrdinate(1);
			quadrangleType = QuadrangleType.XY;
			break;

		case "ReferencedEnvelope":
			// min Lat
			minX = (float) ((ReferencedEnvelope) env).getMinX();
			minY = (float) ((ReferencedEnvelope) env).getMinY();
			maxX = (float) ((ReferencedEnvelope) env).getMaxX();
			maxY = (float) ((ReferencedEnvelope) env).getMaxY();
			quadrangleType = QuadrangleType.LATLONG;
			break;

		default:

			LOG.error(Common.getMessageValue("groundcampaignreaderserviceimpl.setquadranglefromenvelope.error"));
			throw new BmapException(
					Common.getMessageValue("groundcampaignreaderserviceimpl.setquadranglefromenvelope.error"));
		}

		LOG.debug("Quadrangle minimum X", Float.toString(minX));
		LOG.debug("Quadrangle minimum Y", Float.toString(minY));
		LOG.debug("Quadrangle maximum X", Float.toString(maxX));
		LOG.debug("Quadrangle maximum Y", Float.toString(maxY));

		long width = (long) maxX;
		long height = (long) maxY;

		Coordinate coord1 = new Coordinate(maxX, maxY);
		Coordinate coord4 = new Coordinate(maxX, minY);
		Coordinate coord3 = new Coordinate(minX, minY);
		Coordinate coord2 = new Coordinate(minX, maxY);

		return new Quadrangle(quadrangleType, new Coordinate[] { coord1, coord4, coord3, coord2 }, width, height);
	}

	/**
	 * Creates AirbornData object from the path to one tiff file
	 * 
	 * @param file the path to the .tiff
	 * @param fileName tiff's name
	 * @param date Date and time of acquisition
	 * @param region SubRegion
	 * @param GroundCampaign groundCampaign of the data
	 * @param dataFormat data Type of the data (i.e: rg, az, kz)
	 * @throws FactoryException
	 * @return the instance of Airborn Data created
	 * @throws BmapException
	 */
	public Granule retrieveGeotiff(File file, String f_AcquisitionDate, String fileName, String dataFormat,
			Granule granuleScene) throws BmapException {

		Granule granule = null;

		if (dataFormat.equalsIgnoreCase(DataFormat.AZ.toString())
				|| dataFormat.equalsIgnoreCase(DataFormat.RG.toString())
				|| dataFormat.equalsIgnoreCase(DataFormat.DEM.toString())) {

			LOG.info("Gathering " + fileName + " metadata.");

			// airborne data attributes
			float fileSize = ((float) file.length()) / 1024;
			String filePath = file.getAbsolutePath().toString();
			LocalDateTime acquisitionDate = LocalDateTime
					.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(f_AcquisitionDate));
			LocalDateTime uploadDate = LocalDateTime.now();
			LocalDateTime updateDate = LocalDateTime.now();
			Data sourceData = null;
			java.util.Collection<Algorithm> collectionAlgorithm = null;
			String layerName = GEOSERVER_WORKSPACE + ":" + fileName;
			String productType = null;
			Polarization polarization = null;
			String geometryType = Granule.GEOMETRY_TYPE_GEOLOCATED;
			String urlDataLink = urlToData + "/" + granuleScene.getCollection().getShortName() + "/" + fileName + "."
					+ Granule.RASTER_EXTENSION_TIFF;

			Path filePath2 = Paths.get(filePath);
			fileName = filePath2.getFileName().toString();

			Map<String, List<Double>> mapStats = rasterStats.getRasterBasicStats(
					campaignDataDir + "/" + granuleScene.getCollection().getShortName() + "/" + fileName);

			try {
				// declared CoordinateReferencedSystem used for data search
				QuadrangleHolder geoTemp = createQuadrangle(file);
				LOG.info(dataFormat);
				switch (DataFormat.valueOf(dataFormat.toUpperCase())) {

				case DEM:

					productType = Granule.PRODUCT_TYPE_DEM;
					break;
				default:
					break;

				}

				granule = new Granule(fileName, updateDate, granuleScene.getDem(), granuleScene.getMaster(),
						productType, geoTemp.getEpsgCodeNative(), geoTemp.getEpsgCodeDeclared(), null, null,
						granuleScene.getPlatform(), geoTemp.getQuadrangle(), polarization, granuleScene.getCollection(),
						granuleScene.getSubRegion(), fileSize, filePath, acquisitionDate, uploadDate, sourceData,
						collectionAlgorithm, layerName, geometryType, dataFormat, granuleScene, urlDataLink, mapStats);
				// updating data with wms url
				String wmsUrl = GEOSERVER_URL_CARTO + GEOSERVER_WORKSPACE
						+ Common.getPropertiesValue("Esa.VisualizationURL.wms.endpoint")
						+ Common.getPropertiesValue("Esa.VisualizationURL.wms.parameters") + layerName;

				granule.getDataList().get(0).setWmsCartoServerUrl(wmsUrl);
			} catch (Exception e) {

				LOG.error(e.getMessage(), e);
				throw new BmapException(e.getMessage(), e);
			}

		} else {
			LOG.error(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
			throw new BmapException(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
		}

		return granule;
	}

	/**
	 * Create the quadrangle
	 * 
	 * @param file the file to extract the quadrangle
	 * @return the holde of the Quadrangle
	 * @throws IOException
	 * @throws FactoryException
	 * @throws TransformException
	 * @throws BmapException
	 */
	private QuadrangleHolder createQuadrangle(File file)
			throws IOException, FactoryException, TransformException, BmapException {
		Quadrangle quadrangle;
		QuadrangleHolder quadrangleHolder = new QuadrangleHolder();
		// extract metadata from data coverage
		GridCoverage2D coverage = new GeoTiffFormat().getReader(file).read(null);

		// native CoordinateReferenceSystem
		CoordinateReferenceSystem crsOrigin = coverage.getCoordinateReferenceSystem();
		String epsgCodeNative = CRS.lookupIdentifier(crsOrigin, true);
		quadrangleHolder.setEpsgCodeNative(epsgCodeNative);
		// extract boundingbox from referenced envelope in native coordinate system
		// (needed for transforming in standard epsg 4326 coordinate system)
		ReferencedEnvelope bbox = new ReferencedEnvelope(crsOrigin);
		bbox.setBounds(coverage.getEnvelope2D());

		// Transformation from native Coordinate Sytem quadrangle to declared Coordinate
		// Sytem quadrangle
		ReferencedEnvelope env = null;
		LOG.info("epsgCodeNative :" + epsgCodeNative);
		LOG.info("epsgCodeDeclared :" + quadrangleHolder.getEpsgCodeDeclared());
		if (epsgCodeNative.equals(quadrangleHolder.getEpsgCodeDeclared())) {
			env = bbox;
		} else {
			// if epsg code is not the same as declared, a reprojection is needed

			env = transformQuadrangleProjection(quadrangleHolder, crsOrigin, bbox);
		}

		// setting produced quadrangle in declared coordinate referenced system
		quadrangle = setQuadrangleFromEnvelope(env);
		quadrangleHolder.setQuadrangle(quadrangle);
		return quadrangleHolder;
	}

	/**
	 * Transform a given quadrangle from a native projection to a EPSG 4326
	 * projection
	 * 
	 * @param geoTemp
	 * @param crsOrigin
	 * @param bbox
	 * @return
	 * @throws NoSuchAuthorityCodeException
	 * @throws FactoryException
	 * @throws TransformException
	 */
	private ReferencedEnvelope transformQuadrangleProjection(QuadrangleHolder geoTemp,
			CoordinateReferenceSystem crsOrigin, ReferencedEnvelope bbox)
			throws NoSuchAuthorityCodeException, FactoryException, TransformException {
		ReferencedEnvelope env;
		Hints hints = new Hints(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
		CRSAuthorityFactory factory = ReferencingFactoryFinder.getCRSAuthorityFactory("EPSG", hints);
		CoordinateReferenceSystem crsTarget = factory.createCoordinateReferenceSystem(Granule.EPSG_CODE_4326);
		LOG.debug(CRS.getAxisOrder(crsTarget).toString());

		MathTransform transform = CRS.findMathTransform(crsOrigin, crsTarget, false);

		env = new ReferencedEnvelope(JTS.transform(bbox, transform), geoTemp.getTarget());
		return env;
	}

	/**
	 * Extract shapefile shp File metadata and creates an ROI object
	 * 
	 * @param path path to the shapefile .shp File from which the metadata are
	 *            extracted
	 * @param roiFileName name of the file
	 * @param roiName name of the Region of Interest
	 * @param subregion name of the sub-region
	 * @param GroundCampaign GroundCampaign of the ROI
	 * @param baseDir Parent dir of the ROI file
	 * @param BmaapUser the creator of the granule (can be null)
	 * @throws BmapException
	 * @throws FactoryException
	 * @throws NoSuchAuthorityCodeException
	 */
	@Override
	public Granule retrieveROI(String path, String roiFileName, String subregionName, Collection collection,
			String baseDir, BmaapUser author) throws BmapException, NoSuchAuthorityCodeException, FactoryException {

		LOG.info("Gathering " + roiFileName + " metadata.");
		File file = new File(path); // TODO EDU: send instance of file directly?

		// roi attributes
		float fileSize;
		String fileName = file.getName().substring(0, file.getName().lastIndexOf('.'));
		String filePath;
		LocalDateTime acquisitionDate = LocalDateTime.now(); // TODO EDU: ask ESA to give us the acquisitionDate
		LocalDateTime uploadDate = LocalDateTime.now();
		Data sourceData = null;
		java.util.Collection<Algorithm> collectionAlgorithm = null;
		String layerName = GEOSERVER_WORKSPACE + ":" + fileName;
		SubRegion subRegion = null;
		if (subregionName != null && !subregionName.isEmpty()) {
			subRegion = new SubRegion(subregionName);
		}

		Quadrangle quadrangle;

		String geometryType = Granule.GEOMETRY_TYPE_GEOLOCATED;
		String epsgCodeDeclared = null;
		String epsgCodeNative = null;

		CoordinateReferenceSystem crsOrigin = null;

		QuadrangleHolder geoTemp = new QuadrangleHolder();
		geoTemp.setEpsgCodeNative(epsgCodeNative);
		// getting feature sources metadata to extract ROI bounding box
		LOG.debug("Gathering ROI feature sources");

		try {

			Map<String, URL> map = new HashMap<String, URL>();
			map.put("url", file.toURI().toURL());

			// getting correct DataStore for Metadata Extraction
			DataStore dataStore = DataStoreFinder.getDataStore(map);

			SimpleFeatureSource featureSource = dataStore.getFeatureSource(dataStore.getTypeNames()[0]);
			SimpleFeatureCollection featureCollection = featureSource.getFeatures();

			ReferencedEnvelope bbox = featureCollection.getBounds();
			crsOrigin = bbox.getCoordinateReferenceSystem();
			epsgCodeNative = CRS.lookupIdentifier(crsOrigin, true);

			crsOrigin = bbox.getCoordinateReferenceSystem();

			CoordinateReferenceSystem target = CRS.decode(Granule.EPSG_CODE_4326);
			epsgCodeDeclared = CRS.lookupIdentifier(target, true);
			ReferencedEnvelope env = null;
			LOG.info("epsgCodeNative :" + epsgCodeNative);
			LOG.info("epsgCodeDeclared :" + epsgCodeDeclared);
			if (epsgCodeNative.equals(geoTemp.getEpsgCodeDeclared())) {

				env = bbox;

			} else {
				// if epsg code is not the same as declared, a reprojection is needed

				env = transformQuadrangleProjection(geoTemp, crsOrigin, bbox);
			}

			// getting ROI bounding box and using it to make a quadrangle
			quadrangle = setQuadrangleFromEnvelope(env);

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
			throw new BmapException("Failed to retrieve metadata from Shapefile", e);
		}

		// Zipping shapefile
		// Create a zip file named after the shapefile
		// Zip the 4 shapefile files into the zip created
		ArrayList<File> shapefileList = new ArrayList<>();

		File shpFile = new File(baseDir + File.separator + fileName + Granule.EXTENSION_SHP);
		File shxFile = new File(baseDir + File.separator + fileName + Granule.EXTENSION_SHX);
		File dbfFile = new File(baseDir + File.separator + fileName + Granule.EXTENSION_DBF);
		File prjFile = new File(baseDir + File.separator + fileName + Granule.EXTENSION_PRJ);

		shapefileList.add(shpFile);
		shapefileList.add(shxFile);
		shapefileList.add(dbfFile);
		shapefileList.add(prjFile);

		filePath = java.nio.file.Paths.get(baseDir + File.separator + fileName + Granule.EXTENSION_ZIP).toString();

		zipFile(shapefileList, filePath);

		// only the zip file is relevant
		fileSize = ((float) new File(filePath).length()) / 1024;

		// Create Platform with instrument Name

		Platform platform = new Platform(N_A_PLATEFORM, N_A_PLATEFORM, null);

		Granule granule = new Granule(fileName, uploadDate, null, null, Granule.PRODUCT_TYPE_ROI, epsgCodeNative,
				epsgCodeDeclared, null, null, platform, quadrangle, null, collection, subRegion, Status.SHARED,
				Privacy.PUBLIC);

		// We have a private ingestion, we set the ingestion
		if (author != null) {
			granule.setAuthor(author);
			granule.setPrivacy(Privacy.PRIVATE);
			granule.setStatus(Status.COLLABORATE);
		}

		// Creating a Data object for each file extension
		String urlDataLinkShp = urlToData + File.separator + collection.getShortName() + File.separator
				+ shpFile.getName();
		String urlDataLinkShx = urlToData + File.separator + collection.getShortName() + File.separator
				+ shxFile.getName();
		String urlDataLinkDbf = urlToData + File.separator + collection.getShortName() + File.separator
				+ dbfFile.getName();
		String urlDataLinkPrj = urlToData + File.separator + collection.getShortName() + File.separator
				+ prjFile.getName();

		Data dataShp = new Data((((float) shpFile.length()) / 1024), shpFile.getName(), shpFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType,
				Granule.PRODUCT_TYPE_SHP, null, urlDataLinkShp, null);
		Data dataShx = new Data((((float) shxFile.length()) / 1024), shxFile.getName(), shxFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType,
				Granule.PRODUCT_TYPE_SHX, null, urlDataLinkShx, null);
		Data dataDbf = new Data((((float) dbfFile.length()) / 1024), dbfFile.getName(), dbfFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType,
				Granule.PRODUCT_TYPE_DBF, null, urlDataLinkDbf, null);
		Data dataPrj = new Data((((float) prjFile.length()) / 1024), prjFile.getName(), prjFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType,
				Granule.PRODUCT_TYPE_PRJ, null, urlDataLinkPrj, null);

		// adding cartoserverUrl to each Data
		String wmsUrl = GEOSERVER_URL_CARTO + GEOSERVER_WORKSPACE
				+ Common.getPropertiesValue("Esa.VisualizationURL.wms.endpoint")
				+ Common.getPropertiesValue("Esa.VisualizationURL.wms.parameters") + layerName;

		dataShp.setWmsCartoServerUrl(wmsUrl);
		dataShx.setWmsCartoServerUrl(wmsUrl);
		dataDbf.setWmsCartoServerUrl(wmsUrl);
		dataPrj.setWmsCartoServerUrl(wmsUrl);

		// adding Data to granule's datalist
		granule.getDataList().add(dataPrj);
		granule.getDataList().add(dataDbf);
		granule.getDataList().add(dataShp);
		granule.getDataList().add(dataShx);

		// Publishing the shapefile layer on Geoserver
		shploader.publishShapeFile(GEOSERVER_WORKSPACE, fileName + "_store", filePath, fileName, CRS.toSRS(crsOrigin));
		return granule;

	}

	/**
	 * Creates AirbornData object from the path to one tiff file
	 * 
	 * @param path the path to the .tiff
	 * @param fileName tiff's name
	 * @param date Date and time of acquisition
	 * @param region SubRegion
	 * @param groundCampaign GroundCampaign of the data
	 * @param dataFormat data Type of the data (i.e: rg, az, kz)
	 * @throws FactoryException
	 * @return the instance of Airborn Data created
	 * @throws BmapException
	 */
	public Granule retrieveWorldImage(File file, String f_date, String fileName, String dataFormat,
			Granule granuleScene) throws BmapException {

		LOG.info("Gathering " + fileName + " metadata.");
		File tiffFile = file;

		// getting data attributes
		float fileSize = ((float) tiffFile.length()) / 1024;
		String filePath = file.getAbsolutePath().toString();
		LocalDateTime acquisitionDate = LocalDateTime
				.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(f_date));
		LocalDateTime uploadDate = LocalDateTime.now();
		Data sourceData = null;
		java.util.Collection<Algorithm> collectionAlgorithm = null;
		String layerName;
		String productType = null;
		QuadrangleHolder geotiffTemp;
		Quadrangle quadrangle;
		Quadrangle referencedQuadrangle;
		Polarization polarization;
		String geometryType = Granule.GEOMETRY_TYPE_NON_GEOLOCATED;
		Double dataHeight = null;
		Double dataWidth = null;
		String urlDataLink = urlToData + "/" + granuleScene.getCollection().getShortName() + "/" + fileName + ".tiff";

		// checking dataType. If SLC the data geoserver displays is the SLC_amplitude
		// (geoserver doesn't display complex data)
		if (dataFormat.equalsIgnoreCase(DataFormat.SLC_HH.toString())
				|| dataFormat.equalsIgnoreCase(DataFormat.SLC_VV.toString())
				|| dataFormat.equalsIgnoreCase(DataFormat.SLC_VH.toString())
				|| dataFormat.equalsIgnoreCase(DataFormat.SLC_HV.toString())) {

			tiffFile = new File(FilenameUtils.removeExtension(file.getAbsolutePath()) + "_amplitude.tiff");

			layerName = GEOSERVER_WORKSPACE + ":" + fileName + "_amplitude";

		} else {

			layerName = GEOSERVER_WORKSPACE + ":" + fileName;
		}

		try {

			// Getting matrix envelope to get data width and height
			Envelope env = new WorldImageFormat().getReader(tiffFile).read(null).getEnvelope();
			quadrangle = setQuadrangleFromEnvelope(env);

			dataHeight = (double) quadrangle.getHeight();
			dataWidth = (double) quadrangle.getWidth();

			// Getting dem referenced envelope to get data quadrangle

			LOG.info("getting quadrangle from dem data " + granuleScene.getCollection().getShortName() + "_"
					+ granuleScene.getDem() + "_dem.tiff");
			File demFile = getDemFileStartingFromAnotherTiffFile(granuleScene.getDem(), granuleScene.getCollection(),
					tiffFile);

			geotiffTemp = createQuadrangle(demFile);
			referencedQuadrangle = geotiffTemp.getQuadrangle();

		} catch (Exception e) {

			if (tiffFile.exists()) {
				LOG.error(e.getMessage(), e);
				throw new BmapException(e.getMessage(), e);
			} else {
				LOG.error("The file " + tiffFile + " does not exist.", e);
				throw new BmapException("The file " + tiffFile + " does not exist.", e);
			}
		}

		// Set polarization and productType depending on dataFormat
		switch (DataFormat.valueOf(dataFormat.toUpperCase())) {
		case INC:

			polarization = null;

			break;

		case KZ:

			polarization = null;

			break;
		case SLC_HH:

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.HH;

			break;

		case SLC_HV:

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.HV;

			break;

		case SLC_VV:

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.VV;

			break;

		case SLC_VH:

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.VH;

			break;

		default:

			LOG.error(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
			throw new BmapException(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
		}

		Path filePath2 = Paths.get(filePath);
		fileName = filePath2.getFileName().toString();

		Map<String, List<Double>> mapStats = rasterStats.getRasterBasicStats(
				campaignDataDir + "/" + granuleScene.getCollection().getShortName() + "/" + fileName);

		Granule granule = new Granule(fileName, uploadDate, granuleScene.getDem(), granuleScene.getMaster(),
				productType, Granule.EPSG_CODE_404000, geotiffTemp.getEpsgCodeDeclared(), dataWidth, dataHeight,
				granuleScene.getPlatform(), referencedQuadrangle, polarization, granuleScene.getCollection(),
				granuleScene.getSubRegion(), fileSize, filePath, acquisitionDate, uploadDate, sourceData,
				collectionAlgorithm, layerName, geometryType, dataFormat, granuleScene, urlDataLink, mapStats);

		// updating data with wms url
		String wmsUrl = GEOSERVER_URL_CARTO + GEOSERVER_WORKSPACE
				+ Common.getPropertiesValue("Esa.VisualizationURL.wms.endpoint")
				+ Common.getPropertiesValue("Esa.VisualizationURL.wms.parameters") + layerName;

		granule.getDataList().get(0).setWmsCartoServerUrl(wmsUrl);
		return granule;
	}

	private File getDemFileStartingFromAnotherTiffFile(String f_dem, Collection collection, File inputFile) {
		LOG.info("getting quadrangle from dem data " + collection.getShortName() + "_" + f_dem + "_dem.tiff");
		String directory = null;
		if (!inputFile.isDirectory()) {
			Path path = Paths.get(inputFile.getAbsolutePath());
			directory = path.getParent().toString();
		} else {
			directory = inputFile.getAbsolutePath();
		}

		Path path_dem = java.nio.file.Paths.get(directory, collection.getShortName() + "_" + f_dem + "_dem.tiff");

		File demFile = new File(path_dem.toString());
		return demFile;
	}

	/**
	 * Ingest az, rg, dem .tiff files to database and geoserver
	 * 
	 * @param file
	 * @param fileName
	 * @param date
	 * @param subregion
	 * @param collection
	 * @param campaignName
	 * @throws BmapException
	 */
	public Granule ingestGeotiff(File file, String f_AcquisitionDate, String fileName, String dataType,
			Granule granuleScene) throws BmapException {

		Granule granule = retrieveGeotiff(file, f_AcquisitionDate, fileName, dataType, granuleScene);
		// Verifying if granule Data exists in database. If exists, update it, if not,
		// save it
		granule = saveOrUpdateGranuleData(granule);

		// Geoserver publication of the range airborn data
		LOG.info("Publishing " + fileName + " to Geoserver");

		// publishing the granule data to geoserver
		geoLS.publishGeotiff(GEOSERVER_WORKSPACE, fileName + "_store", file.getAbsolutePath(), fileName, null);
		return granule;
	}

	/**
	 * {@inheritDoc}
	 */
	public Granule saveOrUpdateGranuleData(Granule granule) throws BmapException {
		// Verifying if granule Data exists in database. If exists, update it, if not,
		Granule granuleDB = dataService.getGranuleByGranuleNameDataBase(
				granule.getCollection().getShortName() + Granule.GRANULE_ID_DELIMITER + granule.getName());
		if (granuleDB != null) {
			LOG.info("The granule Data is already ingested in database");
			LOG.info("Updating Granule Data ...");
			granuleDB.setUpdateDate(granule.getUpdateDate());
			granuleDB.setDem(granule.getDem());
			granuleDB.setMaster(granule.getMaster());
			granuleDB.setProductType(granule.getProductType());
			granuleDB.setEpsgCodeNative(granule.getEpsgCodeNative());
			granuleDB.setEpsgCodeDeclared(granule.getEpsgCodeDeclared());
			granuleDB.setWidth(granule.getWidth());
			granuleDB.setHeight(granule.getHeight());
			granuleDB.setPlatform(granule.getPlatform());
			granuleDB.setQuadrangle(granule.getQuadrangle());
			granuleDB.setPolarization(granule.getPolarization());
			granuleDB.setSubRegion(granule.getSubRegion());
			granuleDB.setStatus(granule.getStatus());
			granuleDB.setPrivacy(granule.getPrivacy());
			granuleDB.getDataList().clear();
			granuleDB.getDataList().addAll(granule.getDataList());
			granuleDB.setAuthor(granule.getAuthor());

			// This not necessary to update the granule scene because the reference must
			// be always the same.
			// The method add Granule will not recreate a scene granule starting from the
			// data granule
			// granuleDB.setGranuleScene(granule.getGranuleScene());
			granuleDB = dataService.addGranule(granuleDB);
			LOG.info(Common.getMessageValue("hibernate.update.successfull"), granule.getName());

		} else {
			LOG.info("New Granule Data detected");
			LOG.info("Ingesting new Granule Data in Database");
			granuleDB = dataService.addGranule(granule);
			LOG.info(Common.getMessageValue("hibernate.save.successfull"), granule.getName());
		}
		return granuleDB;
	}

	/**
	 * Ingest SLC, kz inc tiff files to database and geoserver
	 * 
	 * @param path
	 * @param fileName
	 * @param date
	 * @param subregion
	 * @param collection
	 * @param campaignName
	 * @throws BmapException
	 */
	public void ingestWorldImage(File file, String f_AcquisitionDate, String fileName, String dataType,
			Granule granuleScene) throws BmapException {

		Granule granule = retrieveWorldImage(file, f_AcquisitionDate, fileName, dataType, granuleScene);

		File fileToIngest;

		ArrayList<File> listFileZip = new ArrayList<>();

		if (dataType == DataFormat.SLC_HH.toString() || dataType == DataFormat.SLC_VV.toString()
				|| dataType == DataFormat.SLC_VH.toString() || dataType == DataFormat.SLC_HV.toString()) {

			fileToIngest = new File(new File(file.getAbsolutePath()).getParentFile().getAbsolutePath() + "/" + fileName
					+ "_amplitude.tiff");

		} else {

			fileToIngest = file;
		}

		listFileZip.add(fileToIngest);
		File worldImageDir = new File(new File(file.getAbsolutePath()).getParentFile().getAbsolutePath() + "/"
				+ fileName + Granule.EXTENSION_ZIP);

		zipFile(listFileZip, worldImageDir.getAbsolutePath());

		// Verifying if granule Data exists in database. If exists, update it, if not,
		// save it
		granule = saveOrUpdateGranuleData(granule);

		// Geoserver publication of the range airborne data
		geoLS.publishWorldImage(GEOSERVER_WORKSPACE, fileName + "_store", worldImageDir);
	}

	/**
	 * Zips a list of files
	 * 
	 * @param listFiles arraylist of files to be zipped
	 * @param zipFilePath path to the zip generated
	 * @throws BmapException
	 * 
	 */
	private void zipFile(ArrayList<File> listFiles, String zipFilePath) throws BmapException {
		try (FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
				ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {
			for (File file : listFiles) {

				// a ZipEntry represents a file entry in the zip archive
				// We name the ZipEntry after the original file's name
				ZipEntry zipEntry = new ZipEntry(file.getName());
				zipOutputStream.putNextEntry(zipEntry);

				try (FileInputStream fileInputStream = new FileInputStream(file)) {
					byte[] buf = new byte[1024];
					int bytesRead;

					// Read the input file by chucks of 1024 bytes
					// and write the read bytes to the zip stream
					while ((bytesRead = fileInputStream.read(buf)) > 0) {
						zipOutputStream.write(buf, 0, bytesRead);
					}
					// We close the stream
					fileInputStream.close();
				}

			}

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}

	}

	/**
	 * We ingest a data knowing its' path and userId
	 * 
	 * @param dataPath
	 * @param subregionName
	 * @param polarization
	 * @param dataFormat
	 * @param collectionName
	 * @param BmaapUser
	 * @throws BmapException
	 */
	public void ingestPrivateData(String dataPath, String subregionName, Polarization polarization,
			DataFormatPrivate dataFormat, String collectionName, BmaapUser author) throws BmapException {
		LOG.info(Common.getMessageValue(Common.INGEST_PRIVATE_DATA_PARAMETERS), dataPath, subregionName, polarization,
				dataFormat, collectionName, author.getId());
		// We convert the datapath to file and get the name
		// COMMON METADATA
		// split the file path to check if it is a s3 url
		LOG.info("Checking dataPath format");
		String s3key = dataPath.substring(0, 2);
		File file = null;
		// if it is a s3 url
		if (s3key.equals(S3BucketService.S3_PROTOCOL)) {
			LOG.info("Datapath is a S3 url");

			/*
			 * Constructs a obs client instance with your account for accessing OBS
			 */
			ObsConfiguration obsConfiguration = new ObsConfiguration();
			obsConfiguration.setEndPoint(s3endpoint);
			obsConfiguration.setSocketTimeout(Integer.valueOf(s3SocketTimeout));
			obsConfiguration.setConnectionTimeout(Integer.valueOf(s3ConnectionTimeout));

			ObsClient obsClient = new ObsClient(s3CopaAccessKey, s3CopaSecretKey, obsConfiguration);
			String objectKey = dataPath.substring(20);
			try {

				file = S3BucketService.downloadS3ObjectToLocalTarget(obsClient, s3CopaBucketName, objectKey,
						privateUserFinalDir, author.getId().toString());

				LOG.info("File downloaded to following path : " + file.getAbsolutePath());
			} catch (ObsException | IOException e) {
				throw new BmapException(
						"Failed to retrieve file from S3 Bucket. The Service doesn't support this product type or the file is above 5GB",
						e);
			}

		} else {

			LOG.info("Datapath is a file system path");

			file = new File(dataPath);

		}

		LocalDateTime updateDate = LocalDateTime.now();
		String polarizationParam = null;

		// First we check if the file exist. If not we raise an execption
		if (!file.exists()) {
			LOG.error(Common.getMessageValue(Common.FILE_NOT_FOUND_ERROR), file.getAbsolutePath());
			throw new BmapException("The file does not exist", HttpStatus.NOT_FOUND);
		}

		// We prepare the collection
		Collection collection = new Collection(collectionName);
		// We set the category key words by default with ground campaign instead of
		// satellite
		collection.setShortName(Collection.COLLECTION_TYPE_L2_USER_DATAS);
		collection.setCategoryKeyWords(Collection.COLLECTION_TYPE_USER_DATA);
		collection.setCollectionType(Collection.COLLECTION_TYPE_USER_DATA);
		collection.setProcessingLevel(Collection.COLLECTION_TYPE_L2_USER_DATAS);
		collection.setUrlCartoServer(GEOSERVER_URL_CARTO);
		collection.setVersionId(Collection.VERSION_ID_1);
		LOG.debug(collection.toString());
		String fileName = file.getName();
		// We have first to detect the format of the data
		// If is ROI
		Boolean isRoi = GranuleHelper.isROIFile(fileName);
		LOG.info("Is the file to ingest of ROI Datatype ? : " + isRoi);
		// If the file is ROI, we know that we have 3 others extensions to load in the
		// folder
		if (isRoi) {
			// Ingest a private ROI

			this.ingestPrivateROI(file.getName(), file.getPath(), subregionName, collection, file, author);
		} else {
			fileName = file.getName();
			// We move the data
			String newDataPath = dataCatalogueService.movePrivateDataBeforeIngestion(file.getPath(), author);

			file = new File(newDataPath);

			// The file is not ROI, it's a raster
			// We check if it is world image or georeferenced
			AbstractGridFormat format = GridFormatFinder.findFormat(file);

			// SLC Data are not manage for the moment
			// So we don't have to convert them to amplitude ?

			try {
				Envelope env = null;
				if (format.getReader(file) != null) {
					env = format.getReader(file).read(null).getEnvelope();
				}
				// We get the envelop from the image

				// we create a new granule to ingest
				Granule granule = new Granule();
				// The data is private and in the state collaborate
				granule.setPrivacy(Privacy.PRIVATE);
				granule.setStatus(Status.COLLABORATE);

				// We set the meta data
				granule.setCollection(collection);
				SubRegion subRegion = null;
				if (subregionName != null && !subregionName.isEmpty()) {
					subRegion = new SubRegion(subregionName);
				}

				if (polarization != null) {
					polarizationParam = polarization.toString();
				}

				granule.setSubRegion(subRegion);

				granule.setName(file.getName());
				granule.setUpdateDate(updateDate);

				// We set the user
				granule.setAuthor(author);

				List<Data> dataList = new ArrayList<Data>();
				// We create a data
				// airborne data attributes
				float fileSize = ((float) file.length()) / 1024;
				String filePath = file.getAbsolutePath();
				Data sourceData = null;
				List<Algorithm> collectionAlgorithm = null;

				int indexOfLast = file.getName().lastIndexOf(".");
				String fileWithoutExtension = file.getName();
				if (indexOfLast >= 0)
					fileWithoutExtension = file.getName().substring(0, indexOfLast);

				if (dataFormat.toString() != null
						&& dataFormat.toString().equalsIgnoreCase(com.esa.bmap.model.Granule.PRODUCT_TYPE_SLC)) {
					fileWithoutExtension = fileWithoutExtension + "_amplitude";
				}

				String layerName = GEOSERVER_WORKSPACE + ":" + fileWithoutExtension;
				String geometryType = null;
				// We get all of the mapstats using the python server
				Map<String, List<Double>> mapStats = null;

				mapStats = rasterStats.getRasterBasicStats(privateUserPythonDir + "/" + file.getName());

				if (mapStats == null) {
					LOG.error(
							"Python server returns an error while getting raster statistics. Setting default statisitcs values");
					mapStats = rasterStats.generateDefaultStats();
				}

				// We create the data
				// separator is a URL separator
				Data data = new Data(fileSize, file.getName(), filePath, updateDate, updateDate, sourceData,
						collectionAlgorithm, layerName, geometryType, dataFormat.toString(), Data.USER_DATA_SET,
						urlToData + "/" + s3UserDir + "/" + String.valueOf(granule.getAuthor().getId()) + "/"
								+ file.getName(),
						mapStats);

				// We check the format of the image we have :
				if (format instanceof GeoTiffFormat) {

					// We have the DEM images
					LOG.info("The file is geotiff");

					// Quadrangle quadrangle = setQuadrangleFromEnvelope(env);
					Quadrangle quadrangle = createQuadrangle(file).getQuadrangle();
					String epsgCodeNative = null;
					if (env != null) {
						CoordinateReferenceSystem crsOrigin = env.getCoordinateReferenceSystem();
						epsgCodeNative = CRS.lookupIdentifier(crsOrigin, true);
					}

					CoordinateReferenceSystem crsTarget = CRS.decode(Granule.EPSG_CODE_4326);
					String epsgCodeDeclared = CRS.lookupIdentifier(crsTarget, true);
					granule.setQuadrangle(quadrangle);
					granule.setEpsgCodeNative(epsgCodeNative);
					granule.setEpsgCodeDeclared(epsgCodeDeclared);

					// We set the geometry type if Geottif format
					data.setGeometryType(Granule.GEOMETRY_TYPE_GEOLOCATED);

					// We check the data format and set the product type if DEM
					LOG.info(dataFormat.name() + " " + DataFormat.DEM);
					if (dataFormat == DataFormatPrivate.DEM) {
						granule.setProductType(Granule.PRODUCT_TYPE_DEM);
					} else if (dataFormat == DataFormatPrivate.AZ) {
						granule.setProductType(Granule.PRODUCT_TYPE_AZ);
					} else if (dataFormat == DataFormatPrivate.RG) {
						granule.setProductType(Granule.PRODUCT_TYPE_RG);
					} else {
						granule.setProductType(Granule.PRODUCT_TYPE_GRD);

					}

					// AZ("AZ"), RG("RG"), DEM("DEM"), ROI("ROI"), SLC("SLC"), INC("INC"), KZ("KZ"),
					// GRD("GRD");

					// We add the data to the datalist
					dataList.add(data);
					granule.setData(dataList);
					// Geoserver publication of the range airborn data
					LOG.info("Publishing " + fileName + " to Geoserver");

					// Defining geoserver style that will be used by raster's number of bands
					String style = null;

					GridCoverage2DReader reader = new GeoTiffReader(file);
					GridCoverage2D coverage = reader.read(null);
					int numBands = coverage.getNumSampleDimensions();
					LOG.info("##########################");

					LOG.info(coverage.getSampleDimension(0).toString());
					LOG.info(String.valueOf(numBands));
					LOG.info("##########################");

					LOG.info("The file has " + String.valueOf(numBands) + " bands");
					if (numBands > 0) {
						style = GeotiffLoaderInterface.RASTER_MULTIBAND_STYLE;
					}

					// publishing the granule data to geoserver
					geoLS.publishGeotiff(GEOSERVER_WORKSPACE, fileWithoutExtension + "_store", file.getAbsolutePath(),
							fileWithoutExtension, style);
					// We save the granule if everything is ok
					// Adding the granule data to the database
					granule = dataService.addPrivateGranule(granule);
					LOG.debug(granule.toString());

				} else if (format instanceof WorldImageFormat) {
					// SLC images are not ingest for the moment
					// We can ingest KZ and INC
					LOG.info("The file is worldimage");
					// We set the geometry type if Geottif format
					data.setGeometryType(Granule.GEOMETRY_TYPE_NON_GEOLOCATED);
					Quadrangle quadrangle = setQuadrangleFromEnvelope(env);
					granule.setQuadrangle(quadrangle);
					granule.setWidth((double) quadrangle.getWidth());
					granule.setHeight((double) quadrangle.getHeight());

					// We set some properties of the granule
					granule = retrievePrivateWorldImage(granule, dataFormat, polarization);

					dataList.add(data);
					granule.setData(dataList);
					granule.setEpsgCodeNative(Granule.EPSG_CODE_404000);
					granule.setEpsgCodeDeclared(Granule.EPSG_CODE_404000);

					// We check the data format and set the product type if DEM

					if (dataFormat == DataFormatPrivate.SLC) {
						granule.setProductType(Granule.PRODUCT_TYPE_SLC);
					} else if (dataFormat == DataFormatPrivate.KZ) {
						granule.setProductType(Granule.PRODUCT_TYPE_KZ);
					} else if (dataFormat == DataFormatPrivate.INC) {
						granule.setProductType(Granule.PRODUCT_TYPE_INC);
					}

					// We have to zip the file to b eingested
					ArrayList<File> listFileZip = new ArrayList<>();
					listFileZip.add(file);
					File worldImageDir = new File(new File(file.getAbsolutePath()).getParentFile().getAbsolutePath()
							+ File.separator + file.getName() + Granule.EXTENSION_ZIP);
					zipFile(listFileZip, worldImageDir.getAbsolutePath());
					dataService.addPrivateGranule(granule);
					geoLS.publishWorldImage(GEOSERVER_WORKSPACE, file.getName() + "_store", worldImageDir);
					LOG.debug(granule.toString());
					// Geoserver publication of the range airborne data

				} else {
					// We throw an exception
					throw new BmapException(
							Common.getMessageValue("GroundCampaignReaderServiceImpl.formatNotSupported"),
							HttpStatus.NOT_ACCEPTABLE);
				}
				LOG.info("End of Data ingestion");
			} catch (IllegalArgumentException | IOException | FactoryException | TransformException e) {
				throw new BmapException(
						"Failed to ingest user data. Please check if the data you want to ingest is not of complex type as it is not supported yet",
						HttpStatus.NOT_ACCEPTABLE, e);
			}

		}
	}

	/**
	 * Ingestion of a private ROI
	 * 
	 * @param fileName
	 * @param dataPath
	 * @param subregionName
	 * @param collection
	 * @param file
	 * @throws BmapException
	 */
	private void ingestPrivateROI(String fileName, String dataPath, String subregionName, Collection collection,
			File file, BmaapUser author) throws BmapException {
		LOG.info("ROI treatment");
		String fileNameWithoutExtension = fileName.replaceFirst("[.][^.]+$", "");
		// we get the file name without extension
		// We have 4 extensions shp, prj, dbx et shx
		// The method retrieveROI doesn't check if there are 4 ROI, so we made the
		// verification before
		ArrayList<File> shapefileList = GranuleHelper.isAllRoiFileExist(file.getParent(), fileNameWithoutExtension);

		// we call the method to move the roi in the dedicated folder
		// We move the data
		ArrayList<String> newShapefileList = new ArrayList<String>();
		for (File shapeFile : shapefileList) {
			String finalDataPath = dataCatalogueService.movePrivateDataBeforeIngestion(shapeFile.getAbsolutePath(),
					author);
			newShapefileList.add(finalDataPath);
			LOG.info("We have ROI in the folder " + finalDataPath);
		}
		// We get the absolute path of the first element
		LOG.info("The first ROI is " + newShapefileList.get(0));
		file = new File(newShapefileList.get(0));
		try {
			// We ingest the data
			// This method create ROI data and save it in the data base
			Granule granule = retrieveROI(file.getAbsolutePath(), fileNameWithoutExtension, subregionName, collection,
					file.getParent(), author);
			granule.setName(file.getName().substring(0, file.getName().lastIndexOf('.')));
			granule.setProductType(Granule.PRODUCT_TYPE_ROI);

			// updating url of roi data
			List<Data> dataList = granule.getDataList();
			for (Iterator iterator = dataList.iterator(); iterator.hasNext();) {
				Data data = (Data) iterator.next();
				data.setUrlToData(urlToData + "/" + s3UserDir + "/" + String.valueOf(granule.getAuthor().getId()) + "/"
						+ data.getFileName());
				LOG.info(data.getUrlToData());
			}
			// saving roi in database
			dataService.addPrivateGranule(granule);

		} catch (FactoryException e) {
			throw new BmapException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, e);
		}
	}

	/**
	 * 
	 * @param granule
	 * @param dataFormat
	 * @return
	 * @throws BmapException
	 */
	private Granule retrievePrivateWorldImage(Granule granule, DataFormatPrivate dataFormat, Polarization polarization)
			throws BmapException {

		LOG.info("Gathering " + granule.getName() + " metadata.");

		// getting data attributes
		String productType = null;

		// checking dataType. If SLC the data geoserver displays is the SLC_amplitude
		// (geoserver doesn't display complex data)
		if (dataFormat == DataFormatPrivate.SLC) {

			throw new BmapException(Common.getMessageValue("GroundCampaignReaderServiceImpl.complexDataNotSupported"),
					HttpStatus.NOT_IMPLEMENTED);

		}

		// Set polarization and productType depending on dataFormat
		switch (dataFormat) {
		case INC:

			polarization = null;

			break;

		case KZ:

			polarization = null;

			break;
		case SLC:

			productType = Granule.PRODUCT_TYPE_SLC;

			break;

		default:

			LOG.error(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
			throw new BmapException(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
		}

		granule.setPolarization(polarization);
		granule.setProductType(productType);

		return granule;

	}

}
