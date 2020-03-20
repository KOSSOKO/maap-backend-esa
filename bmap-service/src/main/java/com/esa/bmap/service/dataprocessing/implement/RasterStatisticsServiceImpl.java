package com.esa.bmap.service.dataprocessing.implement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.services.dataprocessing.interfaces.DataProcessingInterface;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Granule;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
import com.esa.bmap.service.dataprocessing.interfaces.RasterStatisticsServiceInterface;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * RasterStatisticsServiceImpl
 * 
 * @author edupin
 *
 */
@Service(value = "RasterStatisticsServiceInterface")
public class RasterStatisticsServiceImpl implements RasterStatisticsServiceInterface {
	private final Logger LOG = LoggerFactory.getLogger(RasterStatisticsServiceImpl.class);

	@Autowired
	DataProcessingInterface dataProcessing;

	@Autowired
	DataCatalogServiceInterface dao;

	/**
	 * Data directory of the campaigns mounted on the python server (not needed)
	 */
	@Value("${pythonserver.datadir}")
	private String pythonDataDir;

	@Value("${pythonserver.userdatadir}")
	private String pythonUserDataDir;

	/*
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, List<Double>> getRasterBasicStats(String dataFilePath) throws BmapException {
		Map<String, List<Double>> mapStats = new HashMap<String, List<Double>>();
		List<Double> listMin = new ArrayList<Double>();
		List<Double> listMax = new ArrayList<Double>();
		List<Double> listAvg = new ArrayList<Double>();
		List<Double> listStdDev = new ArrayList<Double>();

		String json = null;

		json = dataProcessing.getRasterBasicStats(dataFilePath);

		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);

		// parse the given json to get lists of Min Max Avg and Std deviations (index
		// corresponding of band number)
		if (jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement dataNodeEl = jsonObject.get(DATA_NODE);
			if (dataNodeEl.isJsonObject()) {
				JsonElement bandListNode = dataNodeEl.getAsJsonObject().get(BAND_NODE);

				JsonArray bandArray = bandListNode.getAsJsonArray();
				for (JsonElement bandNodeEl : bandArray) {
					JsonObject bandNode = bandNodeEl.getAsJsonObject();

					JsonElement statsNodeEl = bandNode.get(STATS_NODE);
					JsonArray statsArray = statsNodeEl.getAsJsonArray();
					for (JsonElement statEl : statsArray) {
						JsonObject statObject = statEl.getAsJsonObject();
						Set<Entry<String, JsonElement>> entrySet = statObject.entrySet();
						for (Map.Entry<String, JsonElement> entry : entrySet) {

							switch (entry.getKey()) {
							case MINIMUM:
								listMin.add(statObject.get(entry.getKey()).getAsDouble());
								break;
							case MAXIMUM:
								listMax.add(statObject.get(entry.getKey()).getAsDouble());
								break;
							case AVERAGE:
								listAvg.add(statObject.get(entry.getKey()).getAsDouble());
								break;
							case STD_DEVIATION:
								listStdDev.add(statObject.get(entry.getKey()).getAsDouble());
								break;

							default:
								break;
							}

						}
					}

				}

			}

		}
		mapStats.put(LIST_MINIMUM, listMin);
		mapStats.put(LIST_MAXIMUM, listMax);
		mapStats.put(LIST_AVERAGE, listAvg);
		mapStats.put(LIST_STD_DEVIATION, listStdDev);

		return mapStats;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getSubsetStats(String granuleRasterId, String wkt) throws BmapException {
		String json = null;
		// splitting granuleID to get granule Name and Collection (used to retrieve file
		// path)
		String[] sep = granuleRasterId.split(Granule.GRANULE_ID_DELIMITER);

		String baseDataDir = null;

		if (sep[0].equalsIgnoreCase(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {

			baseDataDir = pythonUserDataDir;

		} else {
			baseDataDir = pythonDataDir + "/" + sep[0];
		}

		json = dataProcessing.getSubsetStats(baseDataDir + "/" + sep[1], wkt).replaceAll("'", "\"").replaceAll(NONE,
				"\"" + NONE + "\"");

		return json;
	}

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getRoiSubsetStats(String granuleRasterId, String granuleRoiId) throws BmapException {
		String json = null;
		// splitting granuleID to get granule Name and Collection (used to retrieve file
		// path)
		String[] rasterSep = granuleRasterId.split(Granule.GRANULE_ID_DELIMITER);
		String[] roiSep = granuleRoiId.split(Granule.GRANULE_ID_DELIMITER);

		String baseRasterDataDir = null;

		if (rasterSep[0].equalsIgnoreCase(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {

			baseRasterDataDir = pythonUserDataDir;

		} else {
			baseRasterDataDir = pythonDataDir + "/" + rasterSep[0];
		}

		String baseRoiDataDir = null;

		if (roiSep[0].equalsIgnoreCase(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {

			baseRoiDataDir = pythonUserDataDir;

		} else {
			baseRoiDataDir = pythonDataDir + "/" + roiSep[0];
		}

		json = dataProcessing
				.getRoiSubsetStats(baseRasterDataDir + "/" + rasterSep[1],
						baseRoiDataDir + "/" + roiSep[1] + Granule.EXTENSION_SHP)
				.replaceAll("'", "\"").replaceAll(NONE, "\"" + NONE + "\"");

		return json;
	}

	@Override
	public Map<String, List<Double>> generateDefaultStats() {
		LOG.info("generating default raster statistics");
		// initizalizing map with lists
		Map<String, List<Double>> mapStats = new HashMap<String, List<Double>>();
		List<Double> listMin = new ArrayList<Double>();
		List<Double> listMax = new ArrayList<Double>();
		List<Double> listAvg = new ArrayList<Double>();
		List<Double> listStdDev = new ArrayList<Double>();

		// adding default values to lists
		listMin.add(0.0);
		listMax.add(1.0);
		listAvg.add(0.5);
		listStdDev.add(0.2);

		// adding lists to map
		mapStats.put(LIST_MINIMUM, listMin);
		mapStats.put(LIST_MAXIMUM, listMax);
		mapStats.put(LIST_AVERAGE, listAvg);
		mapStats.put(LIST_STD_DEVIATION, listStdDev);
		return null;
	}
}
