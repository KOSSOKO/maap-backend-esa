package com.esa.bmap.service.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.assertj.core.util.Lists;
import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.coverage.grid.io.GridCoverage2DReader;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengis.coverage.grid.GridCoordinates;
import org.opengis.coverage.grid.GridEnvelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.BmapServiceTestContext;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.dao.common.BmapUserRepositoryInterface;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.DataFormat;
import com.esa.bmap.model.DataFormatPrivate;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.Polarization;
import com.esa.bmap.model.Privacy;
import com.esa.bmap.model.Quadrangle;
import com.esa.bmap.model.QuadrangleType;
import com.esa.bmap.model.Status;
import com.esa.bmap.model.SubRegion;
import com.esa.bmap.service.catalogue.data.implement.S3BucketService;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
import com.esa.bmap.service.catalogue.data.interfaces.GroundCampaignReaderServiceInterface;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;
import com.obs.services.model.GetObjectRequest;
import com.obs.services.model.ObsObject;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * DataCatalogueServiceTest
 * 
 * @author QFAURE
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { BmapServiceTestContext.class })
public class DataCatalogServiceIT {

	@Autowired
	private DataCatalogServiceInterface dataCatalogueService;
	@Autowired
	private GroundCampaignReaderServiceInterface campaignReaderServiceInterface;
	@Autowired
	BmapUserRepositoryInterface bmapuserService;
	@Autowired
	S3BucketService s3BucketService;

	private static Logger LOG = LoggerFactory.getLogger(DataCatalogServiceIT.class);

	@Value("${geoserver.url}")
	String geoserverUrl;

	@Value("${bucketS3.userfolder}")
	private String s3UserFolder;

	@Value("${datasource.bucketS3FileURL}")
	String bucketS3FileURL;

	@Value("${datasource.dataToUploadS3}")
	String dataToUploadS3;

	@Value("${dataSFSTestPath}")
	String dataSFSTestPath3bands;

	@Value("${dataSFSTestPath1Band}")
	String dataSFSTestPath1Band;

	@Value("${dataSFSTestPath3Bands}")
	String dataSFSTestPath3Bands;

	@Value("${datasource.bucketS3FileURL1band}")
	String dataSFSTestPath1band;

	// s3 endpoint
	@Value("${bucketS3.endpoint}")
	public String s3endpoint;

	// s3 admin access key
	@Value("${bucketS3.access.key}")
	public String s3AdminAccessKey;

	// s3 admin access key
	@Value("${bucketS3.secret.key}")
	public String s3AdminSecretKey;

	// s3 bucket copa name
	@Value("${bucketS3.copa.name}")
	public String s3CopaBucketName;

	// s3 bucket bmap name
	@Value("${bucketS3.bmap.name}")
	public String s3BmapBucketName;

	// s3 connection timeout
	@Value("${bucketS3.connection.timeout}")
	public String s3ConnectionTimeout;
	// s3 connection timeout
	@Value("${bucketS3.socket.timeout}")
	public String s3SocketTimeout;

	private Collection collection = null;

	private Granule granuleRoi = null;

	@Before
	public void init() throws BmapException {
		LOG.info("Initiating DataCatalogService Test");

		// instantiation of collection
		collection = new com.esa.bmap.model.Collection();
		collection.setId(1L);
		collection.setShortName("GroundCampaignCollection");
		collection.setCategoryKeyWords("Ground Campaign");
		collection.setProcessingLevel("L1");
		collection.setVersionId("2");
		collection.setCollectionType(Collection.COLLECTION_TYPE_GROUND_CAMPAIGN);
		collection.setUrlCartoServer("http://cartoserver.com");
		dataCatalogueService.addCollection(collection);

		// instantiation of granule ROI
		Quadrangle quadrangle = new Quadrangle(QuadrangleType.LATLONG, new Coordinate[] { new Coordinate(1.0, 1.0),
				new Coordinate(3.0, 1.0), new Coordinate(1.0, 3.0), new Coordinate(3.0, 3.0) }, 3, 3);
		Data dataTest = new Data(new Float(100.0), "fileName", "http://s3server/myurl.tiff", LocalDateTime.now(),
				LocalDateTime.now(), null, null, "testlayer", Granule.GEOMETRY_TYPE_GEOLOCATED,
				DataFormat.ROI.toString(), "", "", null);
		granuleRoi = new Granule("granuleRoi", LocalDateTime.now(), null, null, Granule.PRODUCT_TYPE_ROI,
				Granule.EPSG_CODE_4326, Granule.EPSG_CODE_4326, null, null, null, null, null, collection, null,
				Status.SHARED, Privacy.PUBLIC);
		granuleRoi.getDataList().add(dataTest);
		LOG.info("first persistence of the granule");
		dataCatalogueService.addGranule(granuleRoi);
	}

	/**
	 * testing get All Collections Service
	 * 
	 * @throws BmapException
	 */
	@Test
	public void getAllCollections() throws BmapException {
		LOG.info("getAllCollections() Test");

		Iterable<Collection> collectionList = dataCatalogueService.getAllCollection();

		for (Collection collection : collectionList) {
			LOG.info(collection.getShortName());
		}

		LOG.info("Size of All Collection List: " + String.valueOf(Lists.newArrayList(collectionList).size()));
		assertFalse(Lists.newArrayList(collectionList).isEmpty());

	}

	/**
	 * testing getGranuleByGranuleUR test
	 * 
	 * @throws BmapException
	 */
	@Test
	public void getGranuleByGranuleUR() throws BmapException {
		LOG.info("getGranuleByGranuleUR() test");
		LOG.info(dataCatalogueService.getGranuleByGranuleNameDataBase("GroundCampaignCollection:@granuleRoi")
				.toString());
		assertNotNull(dataCatalogueService.getGranuleByGranuleNameDataBase("GroundCampaignCollection:@granuleRoi"));

	}

	/**
	 * Tests save + get by id ground campaign.
	 */
	@Deprecated
	@Test
	@Ignore
	public void testAddGroundCampaign() {

		try {

			// instantiation of ground campaign
			com.esa.bmap.model.Collection collection1 = new com.esa.bmap.model.Collection("ground campaign name");

			// test: save ground campaign to database
			com.esa.bmap.model.Collection collection2 = this.dataCatalogueService.addCollection(collection1);

			// test: get ground campaign id from object and get saved ground campaign from
			// database with id
			Assert.assertNotNull(this.dataCatalogueService.getCollectionById(collection2.getId()));

			LOG.info(collection1.toString());

		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Tests save + get by id for sub-region.
	 */
	@Deprecated
	@Test
	@Ignore
	public void testAddSubRegion() {

		try {

			// instantiation of sub-region
			SubRegion subRegion1 = new SubRegion("sub-region name");

			// test: save sub-region to database
			SubRegion subRegion2 = this.dataCatalogueService.addSubRegion(subRegion1);

			// test: get sub-region id from object and get saved sub-region from database
			// with id
			Assert.assertNotNull(this.dataCatalogueService.getSubRegionById(subRegion2.getId()));

			LOG.info(subRegion1.toString());

		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	/**
	 * Test if two granules are duplicated (save if not, update if it is)
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testGranuleDuplication() throws BmapException {
		SubRegion subRegionRabi = new SubRegion("Rabi");
		dataCatalogueService.addSubRegion(subRegionRabi);

		LOG.info("Creation of same granule with two different parameters ");
		// update of an existing granule
		Granule granule2 = new Granule("GranuleTest", LocalDateTime.now(), null, null, Granule.PRODUCT_TYPE_ROI,
				Granule.EPSG_CODE_4326, Granule.EPSG_CODE_4326, null, null, null, null, null, collection, subRegionRabi,
				Status.COLLABORATE, Privacy.PUBLIC);

		campaignReaderServiceInterface.saveOrUpdateGranuleData(granule2);

		Granule foundGranule = dataCatalogueService.getGranuleByGranuleNameDataBase(
				granule2.getCollection().getShortName() + Granule.GRANULE_ID_DELIMITER + granule2.getName());

		Assert.assertTrue(foundGranule.getSubRegion().getName().equals(subRegionRabi.getName()));

	}

	/**
	 * Method to test private data ingestion coming from COPA application
	 * 
	 * @throws BmapException
	 */
	@Test
	@Ignore
	public void testIngestS3Data() {

		BmaapUser bmaapUser = new BmaapUser();
		bmaapUser.setId(35601L);

		bmaapUser.setName("test");

		bmapuserService.save(bmaapUser);

		try {
			campaignReaderServiceInterface.ingestPrivateData(bucketS3FileURL, "la lope", Polarization.HH,
					DataFormatPrivate.GRD, "", bmaapUser);
		} catch (BmapException e) {
			LOG.error(e.getMessage(), e);
			fail();
		}

	}

	@Test
	public void testGetQueryParameters() throws UnsupportedEncodingException {
		Map<String, String> map = new HashMap<>();

		String url = "https://api.maap.xyz/api/wms/GetMap?SERVICE=WMS&amp;VERSION=1.1.0&amp;REQUEST=GetMap&amp;LAYERS=SC:AFLVIS2.001:138348879";
		url = URLDecoder.decode(url, "UTF-8").replace("&amp;", "&");

		Pattern pattern = Pattern.compile("(\\w+)=?([^&]+)?");
		Matcher matcher = pattern.matcher(url);
		while (matcher.find()) {
			LOG.info(" - Key   : " + matcher.group(1));
			LOG.info("   Value : " + matcher.group(2));
			map.put(matcher.group(1), matcher.group(2));
		}

		// asserting that the layers parameter can be retrieved
		assertNotNull(map.get("LAYERS"));

	}

	@Test
	@Ignore
	public void testIngestTiffData() {

		String dataPath = dataSFSTestPath3bands;
		BmaapUser bmaapUser = new BmaapUser();
		bmaapUser.setId(35601L);

		bmaapUser.setName("test");

		bmapuserService.save(bmaapUser);

		try {
			campaignReaderServiceInterface.ingestPrivateData(dataPath, "la lope", Polarization.HH,
					DataFormatPrivate.GRD, "", bmaapUser);
		} catch (BmapException e) {
			LOG.error(e.getMessage(), e);
			fail();
		}

	}

	@Test
	@Ignore
	public void testUploadDataToS3() throws BmapException {
		ObsConfiguration obsConfiguration = new ObsConfiguration();
		obsConfiguration.setEndPoint(s3endpoint);
		obsConfiguration.setSocketTimeout(Integer.valueOf(s3SocketTimeout));
		obsConfiguration.setConnectionTimeout(Integer.valueOf(s3ConnectionTimeout));

		ObsClient obsClient = new ObsClient(s3AdminAccessKey, s3AdminSecretKey, obsConfiguration);

		String dataPath = dataToUploadS3;
		File fileToUpload = new File(dataPath);

		String objectKey = s3UserFolder + String.valueOf(35601) + "-" + fileToUpload.getName();
		File file = new File(dataPath);
		S3BucketService.uploadFileToS3Bucket(obsClient, file, s3BmapBucketName, objectKey);

		ObsObject obsObject = obsClient.getObject(new GetObjectRequest(s3BmapBucketName, objectKey));
		LOG.info(obsObject.getObjectKey());

		// LOG.info(obsObject.getMetadata().toString());
		assertNotNull(obsObject);
	}

	@Test
	@Ignore
	public void testNumberOfBands() throws BmapException, IOException {

		// file 1, 1 band file
		File file = new File(dataSFSTestPath1Band);
		GridCoverage2DReader reader = new GeoTiffReader(file);
		GridEnvelope dimensions = reader.getOriginalGridRange();
		GridCoordinates maxDimensions = dimensions.getHigh();
		int w = maxDimensions.getCoordinateValue(0) + 1;
		int h = maxDimensions.getCoordinateValue(1) + 1;
		// int numBands = reader.getGridCoverageCount();
		GridCoverage2D coverage = reader.read(null);
		LOG.info(String.valueOf(coverage.getNumSampleDimensions()));
		// LOG.info(String.valueOf(w));
		// LOG.info(String.valueOf(h));
		// LOG.info(String.valueOf(numBands));
		String numBands = String.valueOf(coverage.getNumSampleDimensions());
		LOG.info(String.valueOf(coverage.getNumSampleDimensions()));
		assertEquals(numBands, "1");

		// file 2 , 3 band file
		File file2 = new File(dataSFSTestPath3bands);

		GridCoverage2DReader reader2 = new GeoTiffReader(file2);
		GridEnvelope dimensions2 = reader2.getOriginalGridRange();

		GridCoordinates maxDimensions2 = dimensions2.getHigh();
		int w2 = maxDimensions2.getCoordinateValue(0) + 1;
		int h2 = maxDimensions2.getCoordinateValue(1) + 1;

		GridCoverage2D coverage2 = reader2.read(null);
		String numBands2 = String.valueOf(coverage2.getNumSampleDimensions());
		LOG.info(String.valueOf(coverage2.getNumSampleDimensions()));
		assertEquals(numBands2, "3");

	}

	/**
	 * Tests get data by criteria for all data types.
	 */
	@Test
	@Ignore
	public void testGetDataByCriteria() {

		try {

			// instantiation of data criteria with attributes
			GranuleCriteria granuleCriteria = new GranuleCriteria();
			granuleCriteria.getCollectionNames().add("GroundCampaignCollection");

			java.util.Collection<Granule> granules = dataCatalogueService.getGranuleByCriteria(granuleCriteria);
			
			Iterable<Granule> granuleIterable=dataCatalogueService.getAllGranules();
			ArrayList<Granule> granulesList = Lists.newArrayList(granuleIterable);
			LOG.info(granulesList.toString());
			
			
			assertNotNull(granules);
			assertFalse(granules.isEmpty());

		} catch (Exception e) {

			fail(e.getMessage());
		}
	}

}
