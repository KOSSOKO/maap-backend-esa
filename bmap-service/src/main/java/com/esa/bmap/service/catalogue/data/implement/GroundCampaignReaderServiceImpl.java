package com.esa.bmap.service.catalogue.data.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.factory.Hints;
import org.geotools.gce.geotiff.GeoTiffFormat;
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
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.loaders.interfaces.GeotiffLoaderInterface;
import com.esa.bmap.geoserver.loaders.interfaces.ShapeFileLoaderInterface;
import com.esa.bmap.model.Algorithm;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.Instrument;
import com.esa.bmap.model.Platform;
import com.esa.bmap.model.Polarization;
import com.esa.bmap.model.Quadrangle;
import com.esa.bmap.model.QuadrangleType;
import com.esa.bmap.model.SubRegion;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
import com.esa.bmap.service.catalogue.data.interfaces.GroundCampaignReaderServiceInterface;
import com.esa.bmap.service.dataprocessing.interfaces.RasterStatisticsService;
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

	private static final String EPSG_4326 = "EPSG:4326";

	// Geoserver workspace where the data layers will be published
	@Value("${geoserver.workspace}")
	private String GEOSERVER_WORKSPACE;

	// Bucket S3 url
	@Value("${bucketS3.url}")
	private String urlToData;

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
	RasterStatisticsService rasterStats;

	/**
	 * for a given tag scene, searches all the tiff files and gives it to
	 * retrieveAirbornData private method
	 * 
	 * @param eElement it's
	 * @param baseDir
	 * @throws FactoryException
	 * @throws BmapException
	 */
	public void searchAirbornFile(Element eElement, String campaignName, String folderName, Collection collection,
			String instrumentName) throws BmapException {

		// initialize empty Scene Granule
		Granule granuleScene = null;

		ArrayList<String> extensionList = new ArrayList<String>();
		extensionList.add("az");
		extensionList.add("rg");
		extensionList.add("dem");
		extensionList.add("kz");
		extensionList.add("inc");
		extensionList.add("SLC_HH");
		extensionList.add("SLC_HV");
		extensionList.add("SLC_VV");
		extensionList.add("SLC_VH");

		// Getting attributes from Granule Scene node
		String f_scene_name = eElement.getAttribute("name");
		String f_heading = eElement.getAttribute("heading");
		String f_zflight = eElement.getAttribute("z_flight");
		String f_zTerrain = eElement.getAttribute("z_terrain");
		String f_slrStart = eElement.getAttribute("SLR_start");
		String f_pixelSpacing = eElement.getAttribute("pixel_spacing");
		String f_surfaceResol = eElement.getAttribute("surface_resol");
		String f_GRDResol = eElement.getAttribute("GRD_resol");
		String f_date = eElement.getAttribute("date");
		String f_dem = eElement.getAttribute("dem");
		String f_master = eElement.getAttribute("master");
		String f_subRegion = eElement.getAttribute("subregion");

		// if dem or master values are empty then set the value to non applicable n/a
		if (f_dem == null || f_dem == "") {
			f_dem = N_A_DEM;
		}
		if (f_master == null || f_master == "") {
			f_master = N_A_MASTER;
		}

		// Creating child objects
		SubRegion subRegion = new SubRegion(f_subRegion);

		LocalDateTime updateDate = LocalDateTime.now();

		// Create Platform with instrument Name
		ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
		Instrument instrument = new Instrument(instrumentName, "");
		instrumentList.add(instrument);
		Platform platform = new Platform("GroundCampaign platform", "GroundCampaign platform", instrumentList);

		// Creating Scene Granule and ingest in database
		granuleScene = new Granule(f_scene_name, f_heading, f_zflight, f_zTerrain, f_slrStart, f_pixelSpacing,
				f_surfaceResol, f_GRDResol, updateDate, f_dem, f_master, platform, null, collection, subRegion,
				new ArrayList<String>());

		dataService.addGranule(granuleScene);

		LOG.info("Searching " + campaignName + "_" + eElement.getAttribute("name") + " files");

		// Iterate through the extensionList to check the existence of each type of
		// data. retrieve metadata and create Granule Data when found
		for (String currentExtension : extensionList) {

			// Getting other attributes
			String f_dataType = currentExtension;
			String f_name = campaignName + "_" + eElement.getAttribute("name") + "_" + f_dataType;
			String dataName = campaignName + "_" + eElement.getAttribute("name") + "_" + f_dataType + ".tiff";

			// Getting Data path on file System
			Path path_dataType = java.nio.file.Paths.get(folderName, dataName);
			File file = new File(path_dataType.toString());

			LOG.info("checking if " + eElement.getAttribute("name") + " exists at path: " + path_dataType.toString());

			// if the file exists and is not a directory, we create the Airborne Data object
			if (file.exists() && !file.isDirectory()) {
				granuleScene.addToGranuleList(dataName);

				LOG.info(f_name + " exists");

				// if dataType is az or rg or dem, we use Geotiff Reader otherwise we use
				// WorldImage Reader
				if (f_dataType.equals("az") || f_dataType.equals("rg") || f_dataType.equals("dem")) {

					// if the given data is a georeferenced type (az, rg, dem) then its type is
					// GEOTIFF
					Granule savedGranule = ingestGeotiff(file, f_heading, f_zflight, f_zTerrain, f_slrStart,
							f_pixelSpacing, f_surfaceResol, f_GRDResol, f_date, f_dem, f_master, f_subRegion, f_name,
							collection, f_dataType, instrumentName, granuleScene);
				} else {

					// if the given data is a georeferenced type (az, rg, dem) then its type is
					// WorldImage
					ingestWorldImage(file, f_heading, f_zflight, f_zTerrain, f_slrStart, f_pixelSpacing, f_surfaceResol,
							f_GRDResol, f_date, f_dem, f_master, f_subRegion, f_name, collection, f_dataType,
							instrumentName, granuleScene);
				}

			} else {

				LOG.info(f_name + " doesn't exist");
			}
		}

		// Set the Quadrangle from the dem scene and the ref_dem scene itself
		if (f_dem != null && !f_dem.equals(N_A_DEM)) {
			File demFile = getDemFileStartingFromAnotherTiffFile(f_dem, collection, new File(folderName));
			try {
				granuleScene.setQuadrangle(createQuadrangle(demFile).getQuadrangle());
			} catch (IOException | FactoryException | TransformException e) {

				LOG.error("  Unable to set the Quadrangle for the scene : " + granuleScene.getName());
			}
		}

		dataService.addGranule(granuleScene);
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

		LOG.debug(Float.toString(minX));
		LOG.debug(Float.toString(minY));
		LOG.debug(Float.toString(maxX));
		LOG.debug(Float.toString(maxY));

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
	public Granule retrieveGeotiff(File file, String f_heading, String f_zflight, String f_zTerrain, String f_slrStart,
			String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date, String f_dem,
			String f_master, String f_subRegion, String fileName, Collection collection, String dataFormat,
			String instrumentName, Granule granuleScene) throws BmapException {

		Granule granule = null;

		if (dataFormat.equals("az") || dataFormat.equals("rg") || dataFormat.equals("dem")) {

			LOG.info("Gathering " + fileName + " metadata.");

			// airborne data attributes
			float fileSize = ((float) file.length()) / 1024;
			String filePath = file.getAbsolutePath().toString();
			LocalDateTime acquisitionDate = LocalDateTime
					.from(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm").parse(f_date));
			LocalDateTime uploadDate = LocalDateTime.now();
			LocalDateTime updateDate = LocalDateTime.now();
			Data sourceData = null;
			java.util.Collection<Algorithm> collectionAlgorithm = null;
			String layerName = GEOSERVER_WORKSPACE + ":" + fileName;
			String productType = null;
			Polarization polarization = null;
			String geometryType = Granule.GEOMETRY_TYPE_GEOLOCATED;
			SubRegion subRegion = new SubRegion(f_subRegion);
			String urlDataLink = urlToData + "/" + collection.getShortName() + "/" + fileName + ".tiff";

			// Create Platform with instrument Name
			ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
			Instrument instrument = new Instrument(instrumentName, "");
			instrumentList.add(instrument);
			Platform platform = new Platform("GroundCampaign platform", "GroundCampaign platform", instrumentList);
			Path filePath2 = Paths.get(filePath);
			fileName = filePath2.getFileName().toString();

			Map<String, List<Double>> mapStats = rasterStats
					.getRasterBasicStats(collection.getShortName() + "/" + fileName);

			try {
				// declared CoordinateReferencedSystem used for data search
				QuadrangleHolder geoTemp = createQuadrangle(file);

				switch (dataFormat) {

				case "dem":

					productType = Granule.PRODUCT_TYPE_DEM;
					break;
				}

				granule = new Granule(fileName, updateDate, f_dem, f_master, productType, geoTemp.getEpsgCodeNative(),
						geoTemp.getEpsgCodeDeclared(), null, null, platform, geoTemp.getQuadrangle(), polarization,
						collection, subRegion, fileSize, filePath, acquisitionDate, uploadDate, sourceData,
						collectionAlgorithm, layerName, geometryType, dataFormat, granuleScene, urlDataLink, mapStats);

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
		QuadrangleHolder geoTemp = new QuadrangleHolder();
		// extract metadata from data coverage
		GridCoverage2D coverage = new GeoTiffFormat().getReader(file).read(null);

		// native CoordinateReferenceSystem
		CoordinateReferenceSystem crsOrigin = coverage.getCoordinateReferenceSystem();
		String epsgCodeNative = CRS.lookupIdentifier(crsOrigin, true);
		geoTemp.setEpsgCodeNative(epsgCodeNative);
		// extract boundingbox from referenced envelope in native coordinate system
		// (needed for transforming in standard epsg 4326 coordinate system)
		ReferencedEnvelope bbox = new ReferencedEnvelope(crsOrigin);
		bbox.setBounds(coverage.getEnvelope2D());

		// Transformation from native Coordinate Sytem quadrangle to declared Coordinate
		// Sytem quadrangle
		ReferencedEnvelope env = null;
		LOG.info("epsgCodeNative :" + epsgCodeNative);
		LOG.info("epsgCodeDeclared :" + geoTemp.getEpsgCodeDeclared());
		if (epsgCodeNative.equals(geoTemp.getEpsgCodeDeclared())) {
			env = bbox;
		} else {
			// if epsg code is not the same as declared, a reprojection is needed

			env = transformQuadrangleProjection(geoTemp, crsOrigin, bbox);
		}

		// setting produced quadrangle in declared coordinate referenced system
		quadrangle = setQuadrangleFromEnvelope(env);
		geoTemp.setQuadrangle(quadrangle);
		return geoTemp;
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
		CoordinateReferenceSystem crsTarget = factory.createCoordinateReferenceSystem(EPSG_4326);
		LOG.info(CRS.getAxisOrder(crsTarget).toString());

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
	 * @throws BmapException
	 * @throws FactoryException 
	 * @throws NoSuchAuthorityCodeException 
	 */
	@Override
	public void retrieveROI(String path, String roiFileName, String roiName, String subregionName,
			Collection collection, String baseDir) throws BmapException, NoSuchAuthorityCodeException, FactoryException {

		LOG.info("Gathering " + roiFileName + " metadata.");
		File file = new File(path); // TODO EDU: send instance of file directly?

		// roi attributes
		float fileSize;
		String fileName = roiFileName;
		String filePath;
		LocalDateTime acquisitionDate = LocalDateTime.now(); // TODO EDU: ask ESA to give us the acquisitionDate
		LocalDateTime uploadDate = LocalDateTime.now();
		Data sourceData = null;
		java.util.Collection<Algorithm> collectionAlgorithm = null;
		String layerName = GEOSERVER_WORKSPACE + ":" + roiFileName;
		SubRegion subRegion = new SubRegion(subregionName);
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

			CoordinateReferenceSystem target = CRS.decode(EPSG_4326);
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
			throw new BmapException(e.getMessage(), e);
		}

		// Zipping shapefile
		// Create a zip file named after the shapefile
		// Zip the 4 shapefile files into the zip created
		ArrayList<File> shapefileList = new ArrayList<>();

		File shpFile = new File(baseDir + "/" + roiFileName + ".shp");
		File shxFile = new File(baseDir + "/" + roiFileName + ".shx");
		File dbfFile = new File(baseDir + "/" + roiFileName + ".dbf");
		File prjFile = new File(baseDir + "/" + roiFileName + ".prj");

		shapefileList.add(shpFile);
		shapefileList.add(shxFile);
		shapefileList.add(dbfFile);
		shapefileList.add(prjFile);

		filePath = java.nio.file.Paths.get(baseDir + "/" + fileName + ".zip").toString();

		zipFile(shapefileList, filePath);

		// only the zip file is relevant
		fileSize = ((float) new File(filePath).length()) / 1024;

		// Create Platform with instrument Name

		Platform platform = new Platform(N_A_PLATEFORM, N_A_PLATEFORM, null);

		Granule granule = new Granule(fileName, uploadDate, null, null, Granule.PRODUCT_TYPE_ROI, epsgCodeNative,
				epsgCodeDeclared, null, null, platform, quadrangle, null, collection, subRegion);

		// Creating a Data object for each file extension
		String urlDataLinkShp = urlToData + "/" + collection.getShortName() + "/" + shpFile.getName();
		String urlDataLinkShx = urlToData + "/" + collection.getShortName() + "/" + shxFile.getName();
		String urlDataLinkDbf = urlToData + "/" + collection.getShortName() + "/" + dbfFile.getName();
		String urlDataLinkPrj = urlToData + "/" + collection.getShortName() + "/" + prjFile.getName();

		Data dataShp = new Data((((float) shpFile.length()) / 1024), shpFile.getName(), shpFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType, "shp", null,
				urlDataLinkShp, null);
		Data dataShx = new Data((((float) shxFile.length()) / 1024), shxFile.getName(), shxFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType, "shx", null,
				urlDataLinkShx, null);
		Data dataDbf = new Data((((float) dbfFile.length()) / 1024), dbfFile.getName(), dbfFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType, "dbf", null,
				urlDataLinkDbf, null);
		Data dataPrj = new Data((((float) prjFile.length()) / 1024), prjFile.getName(), prjFile.getAbsolutePath(),
				acquisitionDate, uploadDate, sourceData, collectionAlgorithm, layerName, geometryType, "prj", null,
				urlDataLinkPrj, null);

		granule.getDataList().add(dataPrj);
		granule.getDataList().add(dataDbf);
		granule.getDataList().add(dataShp);
		granule.getDataList().add(dataShx);

		dataService.addGranule(granule);
		// Publishing the shapefile layer on Geoserver
		shploader.publishShapeFile(GEOSERVER_WORKSPACE, fileName + "_store", filePath, fileName, CRS.toSRS(crsOrigin));
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
	public Granule retrieveWorldImage(File file, String f_heading, String f_zflight, String f_zTerrain,
			String f_slrStart, String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date,
			String f_dem, String f_master, String f_subRegion, String fileName, Collection collection,
			String dataFormat, String instrumentName, Granule granuleScene) throws BmapException {

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
		SubRegion subRegion = new SubRegion(f_subRegion);

		Double dataHeight = null;
		Double dataWidth = null;
		String urlDataLink = urlToData + "/" + collection.getShortName() + "/" + fileName + ".tiff";

		// checking dataType. If SLC the data geoserver displays is the SLC_amplitude
		// (geoserver doesn't display complex data)
		if (dataFormat.equals("SLC_HH") || dataFormat.equals("SLC_VV") || dataFormat.equals("SLC_VH")
				|| dataFormat.equals("SLC_HV")) {

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

			LOG.info("getting quadrangle from dem data " + collection.getShortName() + "_" + f_dem + "_dem.tiff");
			File demFile = getDemFileStartingFromAnotherTiffFile(f_dem, collection, tiffFile);

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
		switch (dataFormat) {
		case "inc":

			polarization = null;

			break;

		case "kz":

			polarization = null;

			break;
		case "SLC_HH":

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.HH;

			break;

		case "SLC_HV":

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.HV;

			break;

		case "SLC_VV":

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.VV;

			break;

		case "SLC_VH":

			productType = Granule.PRODUCT_TYPE_SLC;
			polarization = Polarization.VH;

			break;

		default:

			LOG.error(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
			throw new BmapException(Common.getMessageValue("groundcampaignreaderserviceimpl.unexpecteddata.error"));
		}

		// Create Platform with instrument Name
		ArrayList<Instrument> instrumentList = new ArrayList<Instrument>();
		Instrument instrument = new Instrument(instrumentName, "");
		instrumentList.add(instrument);
		Platform platform = new Platform("GroundCampaign platform", "GroundCampaign platform", instrumentList);
		Path filePath2 = Paths.get(filePath);
		fileName = filePath2.getFileName().toString();

		Map<String, List<Double>> mapStats = rasterStats.getRasterBasicStats(collection.getShortName() + "/" + fileName);

		return new Granule(fileName, uploadDate, f_dem, f_master, productType, "EPSG:404000",
				geotiffTemp.getEpsgCodeDeclared(), dataWidth, dataHeight, platform, referencedQuadrangle, polarization,
				collection, subRegion, fileSize, filePath, acquisitionDate, uploadDate, sourceData, collectionAlgorithm,
				layerName, geometryType, dataFormat, granuleScene, urlDataLink, mapStats);
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
	public Granule ingestGeotiff(File file, String f_heading, String f_zflight, String f_zTerrain, String f_slrStart,
			String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date, String f_dem,
			String f_master, String f_subRegion, String fileName, Collection collection, String dataType,
			String instrumentName, Granule granuleScene) throws BmapException {

		Granule granule = retrieveGeotiff(file, f_heading, f_zflight, f_zTerrain, f_slrStart, f_pixelSpacing,
				f_surfaceResol, f_GRDResol, f_date, f_dem, f_master, f_subRegion, fileName, collection, dataType,
				instrumentName, granuleScene);

		// Adding the granule data to the database
		dataService.addGranule(granule);

		// Geoserver publication of the range airborn data
		LOG.info("Publishing " + fileName + " to Geoserver");

		// publishing the granule data to geoserver
		geoLS.publishGeotiff(GEOSERVER_WORKSPACE, fileName + "_store", file.getAbsolutePath(), fileName);
		return granule;
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
	public void ingestWorldImage(File file, String f_heading, String f_zflight, String f_zTerrain, String f_slrStart,
			String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date, String f_dem,
			String f_master, String f_subRegion, String fileName, Collection collection, String dataType,
			String instrumentName, Granule granuleScene) throws BmapException {

		Granule granule = retrieveWorldImage(file, f_heading, f_zflight, f_zTerrain, f_slrStart, f_pixelSpacing,
				f_surfaceResol, f_GRDResol, f_date, f_dem, f_master, f_subRegion, fileName, collection, dataType,
				instrumentName, granuleScene);

		File fileToIngest;

		ArrayList<File> listFileZip = new ArrayList<>();

		if (dataType == "SLC_HH" || dataType == "SLC_VV" || dataType == "SLC_VH" || dataType == "SLC_HV") {

			fileToIngest = new File(new File(file.getAbsolutePath()).getParentFile().getAbsolutePath() + "/" + fileName
					+ "_amplitude.tiff");

		} else {

			fileToIngest = file;
		}

		listFileZip.add(fileToIngest);
		File worldImageDir = new File(
				new File(file.getAbsolutePath()).getParentFile().getAbsolutePath() + "/" + fileName + ".zip");

		zipFile(listFileZip, worldImageDir.getAbsolutePath());

		dataService.addGranule(granule);

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
}
