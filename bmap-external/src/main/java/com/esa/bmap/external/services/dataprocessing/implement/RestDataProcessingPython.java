package com.esa.bmap.external.services.dataprocessing.implement;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.ConnectException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.services.dataprocessing.interfaces.DataProcessingInterface;
import com.esa.bmap.model.Granule;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * RestDataProcessingPython
 * 
 * Does basic computing on bmaap data on python server requests
 * 
 * @author edupin
 *
 */
@Service(value = "DataProcessingInterface")
public class RestDataProcessingPython implements DataProcessingInterface {

	HttpClient client;

	private static final Logger LOG = LoggerFactory.getLogger(RestDataProcessingPython.class);

	@Value("${pythonserver.url}")
	private String pythonserverurl;

	// private String pythonserverurl = "http://frtlsvm09422203:8888/";

	@Value("${pythonserver.enable:true}")
	private Boolean pythonserverEnable;

	/**
	 * Mock string to use in dev when the server is not available
	 */
	@Value("${pythonserver.datastats.mock:json data}")
	private String pythonserverMock;

	/**
	 * Root method for basic statistics service
	 */
	private static final String METHOD_BASIC_STATS = "basicStats";
	/**
	 * Root method for basic statistics service from subset
	 */
	private static final String METHOD_BASIC_STATS_SUBSET = "basicStatsSubset";

	/**
	 * Root method for basic statistics service from roi
	 */
	private static final String METHOD_BASIC_STATS_ROI = "basicStatsRoiSubset";

	/**
	 * Root method for raster histogram service
	 */
	private static final String METHOD_RASTER_HIST = "rasterHist";

	/**
	 * Root method for custom profile service
	 */
	private static final String METHOD_CUSTOM_PROFILE = "customProfile";

	/**
	 * Root method for rasterCompare service
	 */
	private static final String METHOD_RASTER_COMPARE = "rasterCompare";

	/**
	 * dataPath parameter used for python rest methods
	 */
	private static final String PARAMETER_DATAPATH = "datapath";

	/**
	 * wkt parameter used for python rest methods
	 */
	private static final String PARAMETER_WKT = "wkt";

	/**
	 * raster_path parameter used for python rest methods
	 */
	private static final String PARAMETER_RASTER_PATH = "raster_path";

	/**
	 * raster_path parameter used for python rest methods
	 */
	private static final String PARAMETER_SHAPEFILE_PATH = "shapefile_path";

	/**
	 * xAxe_raster_path parameter used for python rest methods
	 */
	private static final String PARAMETER_XAXE_RASTER_PATH = "xAxe_raster_path";

	/**
	 * yAxe_raster_path parameter used for python rest methods
	 */
	private static final String PARAMETER_YAXE_RASTER_PATH = "yAxe_raster_path";

	/**
	 * application/json mime type
	 */
	private static final String MIME_TYPE_APPLICATION_JSON = "application/json";

	/**
	 * UTF-8 encoding
	 */
	private static final String UTF8_ENCODING = "UTF-8";

	/**
	 * RestDataProcessingPython constructor
	 */
	public RestDataProcessingPython() {
		// We initialize the httpclient
		MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
		client = new HttpClient(connectionManager);
	}

	/**
	 * get request method
	 * 
	 * @param String url get request url
	 * @return String json response of the get request
	 * @throws ConnectException
	 */
	protected String get(String url) throws ConnectException {
		LOG.info(url);
		String json = null;

		GetMethod method = new GetMethod(pythonserverurl + url);
		LOG.info(pythonserverurl + url);
		method.setRequestHeader("Accept", MIME_TYPE_APPLICATION_JSON);

		try {
			// Execute the method.
			LOG.info("method execution : " + method.toString());
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			InputStream responseBody = method.getResponseBodyAsStream();
			LOG.info("recieved response");
			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data

			StringWriter writer = new StringWriter();
			IOUtils.copy(responseBody, writer, StandardCharsets.UTF_8.name());

			json = new String(writer.toString());

		} catch (HttpException e) {
			throw new ConnectException(e.getMessage());
		} catch (IOException e) {
			throw new ConnectException(e.getMessage());
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return json;
	}

	/**
	 * post request method
	 * 
	 * @param url
	 * @param body
	 * @return
	 * @throws ConnectException
	 */
	protected String post(String url, String body) throws ConnectException {
		LOG.info(url);
		String result = null;

		// Create a method instance.
		PostMethod method = new PostMethod(pythonserverurl + url);
		method.setRequestHeader("Accept", MIME_TYPE_APPLICATION_JSON);

		try {

			StringRequestEntity requestEntity = new StringRequestEntity(body, MIME_TYPE_APPLICATION_JSON,
					UTF8_ENCODING);
			LOG.debug("Response:  " + body.toString());
			// Execute the method.
			method.setRequestEntity(requestEntity);
			int statusCode = client.executeMethod(method);

			// We get the status code
			if (statusCode != HttpStatus.SC_OK) {
				LOG.error("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			InputStream responseBody = method.getResponseBodyAsStream();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary data

			StringWriter writer = new StringWriter();
			IOUtils.copy(responseBody, writer, StandardCharsets.UTF_8.name());

			result = new String(writer.toString());

		} catch (HttpException e) {
			throw new ConnectException(e.getMessage());
		} catch (IOException e) {
			throw new ConnectException(e.getMessage());
		} finally {
			// Release the connection.
			method.releaseConnection();
		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRasterBasicStats(String dataFilePath) throws BmapException {

		String json = null;

		try {

			if (pythonserverurl != null && !pythonserverEnable) {
				// return the modck
				json = pythonserverMock;
			} else {// use the python server
				LOG.info(METHOD_BASIC_STATS + "?" + PARAMETER_DATAPATH + "=" + dataFilePath);
				json = get(METHOD_BASIC_STATS + "?" + PARAMETER_DATAPATH + "=" + dataFilePath);
			}

		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
			// throw new BmapException(e.getMessage(), e);

		}

		return json;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getSubsetStats(String dataFilePath, String wkt) throws BmapException {

		String json = null;

		try {

			Map<String, String> map = new HashMap<String, String>();
			map.put(PARAMETER_DATAPATH, dataFilePath);
			map.put(PARAMETER_WKT, wkt);
			ObjectMapper mapper = new ObjectMapper();
			String jsonFromMap = mapper.writeValueAsString(map);
			LOG.info(PARAMETER_DATAPATH);
			json = post(METHOD_BASIC_STATS_SUBSET, jsonFromMap);

		} catch (IOException e) {

			throw new BmapException(e.getMessage(), e);

		}

		return json;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRoiSubsetStats(String granuleRasterFilePath, String granuleRoiFilePath) throws BmapException {

		String json = null;

		try {

			Map<String, String> map = new HashMap<String, String>();
			map.put(PARAMETER_RASTER_PATH, granuleRasterFilePath);
			map.put(PARAMETER_SHAPEFILE_PATH, granuleRoiFilePath);
			ObjectMapper mapper = new ObjectMapper();
			String jsonFromMap = mapper.writeValueAsString(map);
			LOG.info(PARAMETER_RASTER_PATH);
			LOG.info(PARAMETER_SHAPEFILE_PATH);
			json = post(METHOD_BASIC_STATS_ROI, jsonFromMap);

		} catch (IOException e) {

			throw new BmapException(e.getMessage(), e);

		}

		return json;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRasterHistogram(String dataFilePath) throws BmapException {

		String json = null;
		LOG.debug("Attempt to get  " + dataFilePath + " raster histogram");
		try {
			LOG.info(METHOD_RASTER_HIST + "?" + PARAMETER_DATAPATH + "=" + dataFilePath);
			json = get(METHOD_RASTER_HIST + "?" + PARAMETER_DATAPATH + "=" + dataFilePath);

		} catch (IOException e) {

			throw new BmapException(e.getMessage(), e);

		}

		return json;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCustomProfile(String dataFilePath, String wkt) throws BmapException {

		String json = null;

		try {

			Map<String, String> map = new HashMap<String, String>();
			map.put(PARAMETER_DATAPATH, dataFilePath);
			map.put(PARAMETER_WKT, wkt);
			ObjectMapper mapper = new ObjectMapper();
			String jsonFromMap = mapper.writeValueAsString(map);

			json = post(METHOD_CUSTOM_PROFILE, jsonFromMap);

		} catch (IOException e) {

			throw new BmapException(e.getMessage(), e);

		}
		return json;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRasterPlotComparison(String xRasterFilePath, String yRasterFilePath, String wkt)
			throws BmapException {

		String json = null;

		try {

			Map<String, String> map = new HashMap<String, String>();
			map.put(PARAMETER_XAXE_RASTER_PATH, xRasterFilePath);
			map.put(PARAMETER_YAXE_RASTER_PATH, yRasterFilePath);
			map.put(PARAMETER_WKT, wkt);
			ObjectMapper mapper = new ObjectMapper();
			String jsonFromMap = mapper.writeValueAsString(map);
			LOG.info(PARAMETER_XAXE_RASTER_PATH);
			LOG.info(PARAMETER_YAXE_RASTER_PATH);
			json = post(METHOD_RASTER_COMPARE, jsonFromMap);

		} catch (IOException e) {

			throw new BmapException(e.getMessage(), e);

		}
		return json;

	}
}
