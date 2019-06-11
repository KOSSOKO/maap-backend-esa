package com.esa.bmap.external.services.CMR;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.model.Collections;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.Quadrangle;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * 
 * @author THBLED
 *
 */
@Service(value = "DataRepositoryCMR")
public class DataRepositoryCMR implements DataRepositoryCMRInterface {

	/**
	 * Constante to search a boolean false
	 */
	private static final String FALSE = "false";

	/**
	 * Constante to search a boolean true
	 */
	private static final String TRUE = "true";

	private static final String REQUEST_HEADERS_TOKEN = "Request.Headers.Token";

	private static final String ECHO_TOKEN = "Echo-Token";

	/**
	 * Set the properties to true to enable the log of the xml before the call to
	 * CMR
	 */
	@Value("${CMR.ingestion.log}")
	private boolean enableCMRIngestionLog;

	/**
	 * Set the properties to true to enable the ingestion to CMR
	 */
	@Value("${CMR.ingestion.enable}")
	private boolean enableCMRIngestion;

	@Autowired
	MapperCMRToESA mapperCMRToESA;
	// the downloaded granules
	private static final Logger LOG = LoggerFactory.getLogger(DataRepositoryCMR.class);
	XMLToolBox xmlToolBox = new XMLToolBox();

	JaxBXMLService jaxBXMLService = new JaxBXMLService();

	/**
	 * Default Constructor
	 */
	public DataRepositoryCMR() {
		super();

	}

	/**
	 * Find granules or collection with granulecriteria parameters
	 * 
	 * @param granuleCriteria
	 * @return Collection<Granule> return all granules found
	 * @throws BmapException
	 */
	@Override
	public Collection<com.esa.bmap.model.Granule> findByCriteria(GranuleCriteria granuleCriteria) throws BmapException {
		LOG.debug("start of findTypeData Class");
		// create a new collection of data object

		Collection<com.esa.bmap.model.Granule> dataCollection = new ArrayList<>();
		ArrayList<String> customQueriesList = new ArrayList<>();
		String targetRestSearch = Common.getPropertiesValue("Request.Adresses.Base");

		// ======================================================================================
		// ======================================================================================
		// ================================ Search GRANULES
		// ===============================
		// ======================================================================================
		// ======================================================================================
		LOG.debug("Collection name founded, searching all granules with datarcriteria ... ");
		// Initialize the granule search request :
		targetRestSearch += "search/granules.echo10?";

		if (granuleCriteria.getCollectionNames() != null) {
			customQueriesList.add(
					"short_name=" + granuleCriteria.getCollectionNames().get(0) + "&options[short_name][pattern]=true");
		}
		// Search by granule Name
		if (granuleCriteria.getGranuleName() != null) {
			customQueriesList.add("granule_ur=" + granuleCriteria.getGranuleName());
		}

		// Search by Date :
		// CMR FORMAT : yyyy-MM-ddTHH:mm:ssZ
		if (granuleCriteria.getStartDate() != null) {

			if (granuleCriteria.getEndDate() != null) {
				// Search between two date :
				customQueriesList.add("created_at[]="
						+ xmlToolBox.convertLocalDateToSimpleDate(granuleCriteria.getStartDate()) + "&created_at[]="
						+ xmlToolBox.convertLocalDateToSimpleDate(granuleCriteria.getEndDate()));
			} else {
				// Search only on a date
				customQueriesList
						.add("created_at[]=" + xmlToolBox.convertLocalDateToSimpleDate(granuleCriteria.getStartDate()));
			}
		}

		// Find granules by additionals attributes
		// Product Type
		if (granuleCriteria.getProductTypes() != null && granuleCriteria.getProductTypes().size() > 0) {
			customQueriesList.add("attribute[]=string,Product%20Type," + granuleCriteria.getProductTypes().get(0));
			for (int i = 1; i < granuleCriteria.getProductTypes().size(); i++) {
				customQueriesList.add("," + granuleCriteria.getProductTypes().get(i));
			}
		}

		// Polarization
		if (granuleCriteria.getPolarizations() != null && granuleCriteria.getPolarizations().size() > 0) {
			customQueriesList.add("attribute[]=string,Polarization," + granuleCriteria.getPolarizations().get(0));
			for (int i = 1; i < granuleCriteria.getPolarizations().size(); i++) {
				customQueriesList.add("," + granuleCriteria.getProductTypes().get(i));
			}
		}

		// Geometry
		if (granuleCriteria.getGeometryTypes() != null && granuleCriteria.getGeometryTypes().size() > 0) {
			String booleanString = null;
			if (com.esa.bmap.model.Granule.GEOMETRY_TYPE_GEOLOCATED.equals(granuleCriteria.getGeometryTypes().get(0))) {
				booleanString = TRUE;
			} else {
				booleanString = FALSE;
			}
			customQueriesList.add("attribute[]=string,Geolocated," + booleanString);
		}

		// SubRegion :
		if (granuleCriteria.getSubRegionNames() != null && granuleCriteria.getSubRegionNames().size() > 0) {
			try {
				customQueriesList.add("attribute[]=string,Site%20Name,"
						+ URLEncoder.encode(granuleCriteria.getSubRegionNames().get(0), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				LOG.error("couldn't encode granule Criteria subregion input");
				throw new BmapException("couldn't encode granule Criteria subregion input" + e.getMessage());
			}
			for (int i = 1; i < granuleCriteria.getSubRegionNames().size(); i++) {
				customQueriesList.add("," + granuleCriteria.getSubRegionNames().get(i));
			}
		}

		// Heading :
		if (granuleCriteria.getHeadingValues() != null && granuleCriteria.getHeadingValues().size() > 0) {
			customQueriesList.add("attribute[]=string,Heading," + granuleCriteria.getHeadingValues().get(0));
			for (int i = 1; i < granuleCriteria.getHeadingValues().size(); i++) {
				customQueriesList.add("," + granuleCriteria.getHeadingValues().get(i));
			}
		}

		// Search granule by quadrangle
		if (granuleCriteria.getLocation() != null) {
			customQueriesList.add("polygon=" + createGpolygonString(granuleCriteria.getLocation()));
		}
		// Search granule by instruments name
		if (granuleCriteria.getInstrumentNames() != null) {
			for (int i = 0; i < granuleCriteria.getInstrumentNames().size(); i++) {
				customQueriesList.add("instrument=" + granuleCriteria.getInstrumentNames().get(i));
			}
		}

		// Search granule by processingLevel
		if (granuleCriteria.getProcessingLevels() != null) {
			for (int i = 0; i < granuleCriteria.getInstrumentNames().size(); i++) {
				customQueriesList.add("processing_level_id=" + granuleCriteria.getProcessingLevels().get(i));
			}
		}
		// Create the REST url with all find parameters :
		String restUrl = "";
		for (int i = 0; i < customQueriesList.size(); i++) {
			restUrl += "&" + customQueriesList.get(i);
		}

		targetRestSearch += restUrl;
		Document doc = getXMLfromCMR(targetRestSearch + Common.getPropertiesValue("Request.MaxNumberOfResult"));
		xmlToolBox.createXmlFileFromDoc(doc,
				Common.getPropertiesValue("Download.FilePath") + "GranuleFoundForThisCriteria");

		// return all granules.xml link :
		NodeList granuleNodeList = xmlToolBox.getTagNameNodeListFromXML(doc, "Granule");

		// com.esa.bmap.external.model.cmr.collections.Collection collectionCMR
		// = new
		// com.esa.bmap.external.model.cmr.collections.Collection();
		// doc = getXMLfromCMR(targetRestSearch);
		// return all granules.xml link :

		// for each link to a granule :
		// 1 - get the granule.xml
		// 2 - convert it to a granule java Object
		// 3 - map the granule to a Data object
		// 4 - add the data object to a collection of data
		Node granuleUrl = null;

		// Initialize hasmpap of collection which allow to check if a collection exists
		// when we loop in granuleList
		Map<String, com.esa.bmap.external.model.cmr.collections.Collection> collectionMap = new HashMap<String, com.esa.bmap.external.model.cmr.collections.Collection>();
		Map<String, com.esa.bmap.model.Granule> collectionScenesMap = new HashMap<String, com.esa.bmap.model.Granule>();

		// TODO remove the workaround
		// KO http://localhost:3003/concepts/G1200109231-ESA_MAAP/4
		// OK https://maap-project.org/search/concepts/G1200109231-ESA_MAAP/4
		Granule granule = new Granule();
		com.esa.bmap.model.Granule bmaapGranule = null;
		for (int i = 0; i < granuleNodeList.getLength(); i++) {

			granuleUrl = granuleNodeList.item(i);

			try {
				granule = jaxBXMLService.createGranuleFromNode(granuleUrl);
			} catch (JAXBException e1) {
				// TODO Auto-generated catch block
				LOG.error("couldn't find granule in CMR " + e1.getMessage());
			}
			xmlToolBox.createXmlFileFromDoc(doc,
					Common.getPropertiesValue("Download.FilePath") + granule.getGranuleUR());

			com.esa.bmap.external.model.cmr.collections.Collection collectionCMR = null;
			if (!collectionMap.containsKey(granule.getCollection().getShortName())) {
				LOG.info("first occurence of collection, getting the collection metadata from cmr");
				collectionCMR = new com.esa.bmap.external.model.cmr.collections.Collection();
				collectionCMR = getCollectionCMR(granule.getCollection().getShortName());
				collectionMap.put(granule.getCollection().getShortName(), collectionCMR);

			} else {
				collectionCMR = collectionMap.get(granule.getCollection().getShortName());
			}

			// mapping granule from cmr
			bmaapGranule = mapperCMRToESA.mapCMRToESA(granule, collectionCMR);

			// updating requested granule with granuleScene information
			com.esa.bmap.model.Granule granuleScene = null;
			if (bmaapGranule.getGranuleScene() != null) {

				if (!collectionScenesMap.containsKey(bmaapGranule.getGranuleScene().getName())) {
					LOG.info("First occurence of scene, getting granule scene by name "
							+ bmaapGranule.getGranuleScene().getName());
					try {
						granuleScene = findGranuleScene(collectionCMR, bmaapGranule.getGranuleScene().getName());
						if (granuleScene != null) {
							collectionScenesMap.put(granuleScene.getName(), granuleScene);
						}
					} catch (JAXBException e) {
						LOG.error("couldn't find granuleScene in CMR ", e.getMessage());
					}

				} else {
					granuleScene = collectionScenesMap.get(bmaapGranule.getGranuleScene().getName());

				}
				if (granuleScene != null) {
					bmaapGranule.setGranuleScene(granuleScene);
				}

			}

			dataCollection.add(bmaapGranule);

		}

		LOG.debug("End of findTypeData Class");
		return dataCollection;
	}

	private com.esa.bmap.model.Granule findGranuleScene(
			com.esa.bmap.external.model.cmr.collections.Collection collectionCMR, String granuleSceneName)
			throws BmapException, JAXBException {

		com.esa.bmap.model.Granule granuleScene = null;
		LOG.debug("Searching granuleScene by Collection Name and granule name");
		ArrayList<String> customQueriesList = new ArrayList<>();
		String targetRestSearch = Common.getPropertiesValue("Request.Adresses.Base");
		targetRestSearch += "search/granules.echo10?";

		customQueriesList.add("short_name=" + collectionCMR.getShortName());
		customQueriesList.add("granule_ur=" + granuleSceneName);
		// Create the REST url with all find parameters :
		String restUrl = "";
		for (int i = 0; i < customQueriesList.size(); i++) {
			restUrl += "&" + customQueriesList.get(i);
		}

		targetRestSearch += restUrl;
		Document doc = getXMLfromCMR(targetRestSearch + Common.getPropertiesValue("Request.MaxNumberOfResult"));
		xmlToolBox.createXmlFileFromDoc(doc,
				Common.getPropertiesValue("Download.FilePath") + "GranuleFoundForThisCriteria");

		// return all granules.xml link :
		NodeList granuleNodeList = xmlToolBox.getTagNameNodeListFromXML(doc, "Granule");
		Granule granule = new Granule();
		if (granuleNodeList.getLength() > 0) {
			LOG.debug(String.valueOf(granuleNodeList.getLength()));
			Node granuleUrl = granuleNodeList.item(0);
			granule = jaxBXMLService.createGranuleFromNode(granuleUrl);
			xmlToolBox.createXmlFileFromDoc(doc,
					Common.getPropertiesValue("Download.FilePath") + granule.getGranuleUR());
			granuleScene = mapperCMRToESA.mapCMRToESA(granule, collectionCMR);
		}
		return granuleScene;
	}

	private void findCollectionByCriteria(GranuleCriteria granuleCriteria, ArrayList<String> customQueriesList,
			String targetRestSearch) {
		// ======================================================================================
		// ======================================================================================
		// ================================ Search COLLECTIONS
		// ======================================================================================
		// ======================================================================================

		LOG.debug("No collection name founded, searching all collections with datarcriteria... ");
		targetRestSearch += "search/collections?";

		// search by quadrangle
		if (granuleCriteria.getLocation() != null) {
			customQueriesList.add("polygon=" + createGpolygonString(granuleCriteria.getLocation()));
		}
		// search collection by instrumentName
		if (granuleCriteria.getInstrumentNames() != null) {
			for (int i = 0; i < granuleCriteria.getInstrumentNames().size(); i++) {
				customQueriesList.add("instrument=" + granuleCriteria.getInstrumentNames().get(i));
			}
		}
		// search collection by processingLevel
		if (granuleCriteria.getProcessingLevels() != null) {
			for (int i = 0; i < granuleCriteria.getInstrumentNames().size(); i++) {
				customQueriesList.add("processing_level_id=" + granuleCriteria.getProcessingLevels().get(i));
			}
		}
		if (granuleCriteria.getStartDate() != null & granuleCriteria.getEndDate() != null) {
			// convert start and end date to a yyyy-MM-ddTHH:mm:ssZ SimpleDate format for
			// cmr
			String startDate = xmlToolBox.convertLocalDateToSimpleDate(granuleCriteria.getStartDate());
			String endDate = xmlToolBox.convertLocalDateToSimpleDate(granuleCriteria.getEndDate());
			customQueriesList.add("temporal=" + startDate + "," + endDate);
		}
		// find by instruments name
		if (granuleCriteria.getInstrumentNames() != null) {
			for (int i = 0; i < granuleCriteria.getInstrumentNames().size(); i++) {
				customQueriesList.add("instrument=" + granuleCriteria.getInstrumentNames().get(i));
			}
		}
		// Create the REST url with all find parameters :
		String restUrl = "";
		for (int i = 0; i < customQueriesList.size(); i++) {
			restUrl += "&" + customQueriesList.get(i);
		}
		targetRestSearch += restUrl;

		Document doc = getXMLfromCMR(targetRestSearch + Common.getPropertiesValue("Request.MaxNumberOfResult"));
		xmlToolBox.createXmlFileFromDoc(doc, Common.getPropertiesValue("Download.FilePath") + "collection");
		ArrayList<String> locationList = xmlToolBox.getTagNameTextContentFromXML(doc, "location");
		ArrayList<String> nameList = xmlToolBox.getTagNameTextContentFromXML(doc, "name");
		ArrayList<String> idList = xmlToolBox.getTagNameTextContentFromXML(doc, "id");
		// ArrayList<String> versionList = xmlToolBox.getTagNameTextContentFromXML(doc,
		// "revision-id");
		// get all url and name
		for (int i = 0; i < nameList.size(); i++) {
			Collections collection = new Collections();
			collection.setShortName(nameList.get(i));
			collection.setCollectionURL(locationList.get(i));
			collection.setDataSetId(idList.get(i));

			// collection.setVersionId(versionList.get(i));
			// TODO return a data collection object :
			// data2.setCollection(collection);
			// add data object to data collection
			// dataCollection.add(data2);
		}
	}

	/**
	 * Create a Collection object from the collection shortname
	 * 
	 * @param collectionShortName
	 * @return full collection java object
	 */
	@Override
	public com.esa.bmap.external.model.cmr.collections.Collection getCollectionCMR(String collectionShortName)
			throws BmapException {
		LOG.debug("Start of getCollectionCMR");
		/*
		 * * 1 create target url 2 get the collection xml result 3 extract the location
		 * url of the collection.xml 4 get the collection.xml 5 transform collection.xml
		 * to a collection java object 6 return the collection object
		 */

		String targetCollection = Common.getPropertiesValue("Request.Adresses.Base")
				+ Common.getPropertiesValue("Request.Search.Collection.by.ShortName");
		targetCollection += collectionShortName;
		Document doc = getXMLfromCMR(targetCollection);
		// extract all url links to a collections :
		ArrayList<String> locationListCollection = xmlToolBox.getTagNameTextContentFromXML(doc, "location");

		xmlToolBox.createXmlFileFromDoc(doc,
				Common.getPropertiesValue("Download.FilePath") + "CollectionFoundForThisCriteria");
		// check if their is more than one collection
		// create a new collection :
		com.esa.bmap.external.model.cmr.collections.Collection collection = new com.esa.bmap.external.model.cmr.collections.Collection();
		if (locationListCollection.size() > 1) {
			doc = getXMLfromCMR(locationListCollection.get(0));
			collection = jaxBXMLService.createCollectionFromXML(doc);
		} else {
			// function to keep just the Collection node
			doc = xmlToolBox.getXMLNodeContentFromTag(doc, "Collection");
			xmlToolBox.toString(doc);
			xmlToolBox.createXmlFileFromDoc(doc,
					Common.getPropertiesValue("Download.FilePath") + "CleanCollectionFoundForThisCriteria");
			collection = jaxBXMLService.createCollectionFromXML(doc);
		}

		LOG.debug("End of getCollectionCMR");
		return collection;
	}

	/**
	 * Ingest the in CMR collections 1 - get the collection shortname targeted 2 -
	 * find the type of the data (airborne, SARRawData, ROI) 3 - create the
	 * granule.xml file associated in debug only 4 - create a request and send the
	 * associated granule.xml to the CMR ingestion process
	 * 
	 * @param bmaapGranule a full data object who contain the future granule to
	 *            ingest in CMR
	 * @throws BmapException
	 */
	@Override
	public void save(com.esa.bmap.model.Granule bmaapGranule) throws BmapException {
		LOG.info("start of save");
		// intialize all dependencies :
		MapperESAToCMR mapper = new MapperESAToCMR();
		JaxBXMLService jaxbService = new JaxBXMLService();
		Granule granule = new Granule();

		String collection = bmaapGranule.getCollection().getShortName();
		// map data to granule :
		granule = mapper.findTypeData(bmaapGranule, collection);

		if (enableCMRIngestionLog) {
			// generate a granule.xml file
			String fileName = Common.getPropertiesValue("Download.Temp.Folder") + bmaapGranule.getName();
			jaxbService.createXmlFileFromGranule(granule, fileName);
		}
		// Convert the granule object to an xml string
		String requestXml = jaxbService.marshalGranuleToXMLString(granule);

		if (enableCMRIngestion) {
			String targetUrl = Common.getPropertiesValue("Request.Adresses.Base") + "ingest/providers/"
					+ Common.getPropertiesValue("ProviderId") + "/granules/" + granule.getGranuleUR();
			LOG.info("  " + targetUrl);
			try {
				// initialize the connection header to CMR :
				CloseableHttpClient client = HttpClientBuilder.create().build();
				// HttpPost post = new HttpPost(targetUrl);
				HttpPut post = new HttpPut(targetUrl);
				// add header

				post.setHeader("Content-Type", "application/echo10+xml");
				post.setHeader("ClientId", Common.getPropertiesValue("ClientId"));
				post.setHeader(ECHO_TOKEN, Common.getPropertiesValue(REQUEST_HEADERS_TOKEN));
				post.setHeader("Authorization",
						"Basic " + Base64.getEncoder()
								.encodeToString((Common.getPropertiesValue("Request.Headers.Password") + ":"
										+ Common.getPropertiesValue("Request.Headers.Password")).getBytes()));
				post.setHeader("Connection", "Keep-Alive");

				// add the granule.xml to the request :
				// List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				// urlParameters.add(new BasicNameValuePair("xml", requestXml));
				// post.setEntity(new UrlEncodedFormEntity(urlParameters));
				StringEntity stringEntity = new StringEntity(requestXml);
				post.setEntity(stringEntity);

				HttpResponse response = client.execute(post);
				int result = response.getStatusLine().getStatusCode();
				LOG.info("End of save result code : " + result);
				if (result != 200) {
					String text = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8.name());
					LOG.error(text);
				}

			} catch (IOException e) {

				e.printStackTrace();
			}
		} else {
			LOG.debug(" CMR ingestion is disabled");
		}
	}

	/**
	 * Delete a Collection and all the granule inside
	 * 
	 * @param collectionShortName
	 * @return true if the delete was successfull
	 */
	@Override
	public boolean deleteAllGranuleInCollection(String CollectiondataSetId) throws BmapException {

		LOG.debug("Start of deleteAllGranuleInCollection ");

		// create the delete string request :
		// target example :
		// https://cmr.earthdata.nasa.gov/ingest/providers/PROV1/collections/sampleNativeId15
		String targetURL = Common.getPropertiesValue("Request.Adresses.Base") + "ingest/providers/"
				+ Common.getPropertiesValue("ProviderId") + "/collections/" + CollectiondataSetId;
		boolean result = false;
		// Send the request
		Document doc = null;
		URL url;
		try {
			url = new URL(targetURL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setInstanceFollowRedirects(false); // follow redirection link
			connection.setUseCaches(false);
			connection.setDoInput(true); // can set a request
			connection.setDoOutput(true); // get the response
			connection
					.setRequestProperty(
							"Authorization", "Basic "
									+ Base64.getEncoder()
											.encodeToString((Common.getPropertiesValue("Request.Headers.Password") + ":"
													+ Common.getPropertiesValue("Request.Headers.Password"))
															.getBytes()));

			connection.getInputStream();
			// if collection has been delete , response code will be 201
			if (connection.getResponseCode() == 201) {
				result = true;
			}

		} catch (Exception e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}

		LOG.debug("End of deleteAllGranuleInCollection ");
		return result;
	}

	/**
	 * Download the data file from a url target
	 * 
	 * @param TargetUrl the url where is located the file to download
	 * @param Filename the name of the future file with the extension , granule.tiff
	 *            , granule.xml ...
	 * @throws BmapException
	 */
	@Override
	public void downloadTheData(String TargetUrl, String Filename) throws BmapException {
		// have to implement the true methode for cmr ( download from s3 )
		OutputStream outputStream = null;
		// Authorize cookies to keep connection auth.
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		try {
			URL url = new URL(TargetUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false); // follow redirection link
			connection.setUseCaches(false);
			connection.setDoInput(true); // can set a request
			connection.setDoOutput(true); // get the response
			connection
					.setRequestProperty(
							"Authorization", "Basic "
									+ Base64.getEncoder()
											.encodeToString((Common.getPropertiesValue("Request.Headers.Password") + ":"
													+ Common.getPropertiesValue("Request.Headers.Password"))
															.getBytes()));

			connection.getInputStream();

			// if we get a 301 response code (redirection) we initialize a new connection :
			// to follow the redirection link
			if (connection.getResponseCode() == HttpURLConnection.HTTP_MOVED_TEMP) {
				url = new URL(connection.getHeaderField("Location"));
				connection = (HttpURLConnection) url.openConnection();
				connection
						.setRequestProperty(
								"Authorization", "Basic "
										+ Base64.getEncoder()
												.encodeToString((Common.getPropertiesValue("Request.Headers.Password")
														+ ":" + Common.getPropertiesValue("Request.Headers.Password"))
																.getBytes()));
			}

			InputStream inputStream = connection.getInputStream();
			String FilePath = Common.getPropertiesValue("Download.Data.FilePath");
			outputStream = new FileOutputStream(new File(FilePath + Filename));
			// open and create a output datafile :
			int bytesRead = -1;
			byte[] buffer = new byte[(4096) * 100000];

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}

	}

	/**
	 * Download the data file from a S3 url target
	 * 
	 * @param TargetUrl the url where is located the file to download
	 * @param Filename the name of the future file with the extension , granule.tiff
	 *            , granule.xml ...
	 * @throws BmapException
	 */
	@Override
	public void downloadTheDataFromS3(String TargetUrl, String Filename) throws BmapException {
		// have to implement the true methode for cmr ( download from s3 )
		OutputStream outputStream = null;
		// Authorize cookies to keep connection auth.
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
		try {
			URL url = new URL(TargetUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false); // follow redirection link
			connection.setUseCaches(false);
			connection.setDoInput(true); // can set a request
			connection.setDoOutput(true); // get the response
			connection.getInputStream();
			InputStream inputStream = connection.getInputStream();
			String FilePath = Common.getPropertiesValue("Download.Data.FilePath");
			outputStream = new FileOutputStream(new File(FilePath + Filename));
			// open and create a output datafile :
			int bytesRead = -1;
			byte[] buffer = new byte[(4096) * 100000];

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}

	}

	// =====================================================================================
	// =====================================================================================
	// ============================= Generals :
	// ======================================
	// =====================================================================================
	// =====================================================================================

	/**
	 * Send the request to get xml from CMR and return the Document xml object
	 * 
	 * @param customRequest custom curl request
	 * @return Document object who contains the request response
	 */
	private Document getXMLfromCMR(String targetRestSearch) {

		// Send the request
		Document doc = null;
		URL url;
		try {
			url = new URL(targetRestSearch);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setInstanceFollowRedirects(false); // follow redirection link
			connection.setUseCaches(false);

			connection.setDoInput(true); // can set a request
			connection.setDoOutput(true); // get the response
			connection.setRequestProperty(ECHO_TOKEN, Common.getPropertiesValue(REQUEST_HEADERS_TOKEN));
			connection
					.setRequestProperty(
							"Authorization", "Basic "
									+ Base64.getEncoder()
											.encodeToString((Common.getPropertiesValue("Request.Headers.Password") + ":"
													+ Common.getPropertiesValue("Request.Headers.Password"))
															.getBytes()));

			connection.getInputStream();
			// if Custom search return a valid response code :
			// valid ESA collection objects :
			InputStream inputStream = connection.getInputStream();
			// parse the xml response from the server
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(inputStream);
			inputStream.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return doc;
	}

	/**
	 * Create a string search polygon from a quadrangle datacriteria
	 * 
	 * @param quadrangle ESA quadrangle
	 * @return String contains a Gpolygon formated for CMR
	 */
	private String createGpolygonString(Quadrangle quadrangle) {
		LOG.debug("Start of  createGpolygonString ");

		Coordinate[] coordinates = quadrangle.getGeometry().getCoordinates();
		String polygon = "";
		// For all coordinates points :
		for (int i = coordinates.length - 1; i >= 0; i--) {

			Coordinate coordinateP1 = coordinates[i];
			LOG.info(coordinateP1.toString());
			// create the request link
			polygon += BigDecimal.valueOf(coordinateP1.x).toPlainString() + ","
					+ BigDecimal.valueOf(coordinateP1.y).toPlainString() + ",";
		}
		// delete the last "," generated :
		polygon = polygon.substring(0, polygon.length() - 1);
		LOG.debug("End of  createGpolygonString ");
		return polygon;
	}

}
