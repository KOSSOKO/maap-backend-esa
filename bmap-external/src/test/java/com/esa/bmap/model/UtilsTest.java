package com.esa.bmap.model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.vividsolutions.jts.geom.Coordinate;

public class UtilsTest {
	private static final Logger LOG = LoggerFactory.getLogger(UtilsTest.class);

	public static Algorithm initAlgorithm() throws IOException {
		File gitUrl;
		gitUrl = File.createTempFile("temp-file-name", ".tmp");
		String path = gitUrl.getAbsolutePath();

		return new Algorithm("algo1", new BmaapUser(null, null), "zone1", "1h30", path, path, "1.0.0", "doc1",
				Privacy.PUBLIC, Status.SHARED, "RADAR", "Geometry", "radar lidar teddy", null);
	}

	public static Algorithm initAlgorithm2() {
		return new Algorithm("algo1", new BmaapUser(null, null), "zone2", "time2", "urlconfigFile2", "urlconfigFile2",
				"1.0.0", "doc2", Privacy.PUBLIC, Status.SHARED, "RADAR", "Geometry", "radar lidar coorection", null);
	}

	/*
	 * Data
	 */

	public final static int AIRBORNEDATA = 0;
	public final static int BIOMASSOFFICIALPRODUCT = 1;
	public final static int REGIONOFINTEREST = 2;
	public final static int SARRAWDATA = 3;
	
	public final static int GEOTIFFDATA = 4;
	public final static int WORLDIMAGEDATA = 5;
	public final static int ROIDATA = 6;
	
	public static Granule initData(int type) throws BmapException {

		switch (type) {

		case AIRBORNEDATA:

			try {

				// attributes
				float fileSize1 = (float) 100.0;
				String fileName1 = "airborne data file.ext";
				String filePath1 = "C:\\airborne data path";
				LocalDateTime updateDate1 = LocalDateTime.now();
				LocalDateTime acquisitionDate1 = LocalDateTime.of(2018, 11, 12, 12, 00);
				LocalDateTime uploadDate1 = LocalDateTime.of(2018, 11, 12, 12, 00);
				Data sourceData1 = null;
				java.util.Collection<Algorithm> collectionAlgorithm1 = null;
				String layerName1 = "airborne data layer name";
				String processingLevel1 = "L0";
				String productType1 = Granule.PRODUCT_TYPE_SLC;
				String epsgCodeNative = "EPSG:4326";
				String epsgCodeDeclared = "EPSG:4326";
				// String orbitDirection = "ASCENDING";
				// String orbitNumber = "13";
				Double width = new Double("1230.12");
				Double height = new Double("1230.12");
				Instrument instrument = new Instrument("SETHI", "My description");
				List<Instrument> listInstrument = new ArrayList();
				listInstrument.add(instrument);
				Platform platform = new Platform("my description", "Dornier DO228 ", listInstrument);
				Quadrangle quadrangle1 = new Quadrangle(QuadrangleType.LATLONG,
						new Coordinate[] { new Coordinate(1.0, 1.0), new Coordinate(3.0, 1.0), new Coordinate(1.0, 3.0),
								new Coordinate(3.0, 3.0) },
						3, 3);
				Polarization polarization1 = Polarization.HH;
				String geometryType1 = "GRD";
				SubRegion subRegion1 = new SubRegion("sub-region name 1");
				Collection collection = new Collection("ground campaign name 1");
				collection.setCategoryKeyWords(Collection.COLLECTION_TYPE_GROUND_CAMPAIGN);
				String dataFormat = "tiff";

				Granule granuleScene = new Granule();
				granuleScene.setName("paracou");
				granuleScene.setStatus(Status.SHARED);
				granuleScene.setPrivacy(Privacy.PRIVATE);
				String urlData = "http://s3server/myurl.tiff";
				return new Granule(fileName1, updateDate1, null, null, productType1, epsgCodeNative, epsgCodeNative,
						width, height, platform, quadrangle1, polarization1, collection, subRegion1, fileSize1,
						filePath1, acquisitionDate1, uploadDate1, sourceData1, collectionAlgorithm1, layerName1,
						geometryType1, dataFormat, granuleScene, urlData, null);

			} catch (BmapException e) {

				LOG.info(e.getMessage(), e);
				throw e;
			}
		case BIOMASSOFFICIALPRODUCT:
		case REGIONOFINTEREST:
		case SARRAWDATA:
			try {

				// attributes
				float fileSize1 = (float) 100.0;
				String fileName1 = "sarr_data_file.ext";
				String filePath1 = "C:\\dummy\\sar_data_path";
				LocalDateTime updateDate1 = LocalDateTime.now();
				LocalDateTime acquisitionDate1 = LocalDateTime.of(2018, 11, 12, 12, 00);
				LocalDateTime uploadDate1 = LocalDateTime.of(2018, 11, 12, 12, 00);
				Data sourceData1 = null;
				java.util.Collection<Algorithm> collectionAlgorithm1 = null;
				String layerName1 = "sar data layer name";
				String processingLevel1 = "L0";
				String productType1 = Granule.PRODUCT_TYPE_SLC;
				String epsgCodeNative = "EPSG:4326";
				String epsgCodeDeclared = "EPSG:4326";
				String orbitDirection = "ASCENDING";
				String orbitNumber = "13";
				Double width = new Double("1230.12");
				Double height = new Double("1230.12");
				Instrument instrument = new Instrument("E-SAR", "My description sar");
				List<Instrument> listInstrument = new ArrayList();
				listInstrument.add(instrument);
				Platform platform = new Platform("my description", "Dornier DO228 ", listInstrument);
				Quadrangle quadrangle1 = new Quadrangle(QuadrangleType.LATLONG,
						new Coordinate[] { new Coordinate(1.0, 1.0), new Coordinate(3.0, 1.0), new Coordinate(1.0, 3.0),
								new Coordinate(3.0, 3.0) },
						3, 3);
				Polarization polarization1 = Polarization.HH;
				String geometryType1 = "GRD";
				SubRegion subRegion1 = new SubRegion("sub-region name 1");
				Collection collection = new Collection("ground campaign name 1");
				collection.setCategoryKeyWords(Collection.COLLECTION_TYPE_SATELLITE);
				String dataFormat = "tiff";

				Granule granuleScene = new Granule();
				granuleScene.setName("paracou");
				granuleScene.setStatus(Status.SHARED);
				granuleScene.setPrivacy(Privacy.PRIVATE);

				String urlData = "http://s3server/sarrwurl.tiff";
				return new Granule(fileName1, updateDate1, null, null, productType1, epsgCodeNative, epsgCodeNative,
						width, height, platform, quadrangle1, polarization1, collection, subRegion1, fileSize1,
						filePath1, acquisitionDate1, uploadDate1, sourceData1, collectionAlgorithm1, layerName1,
						geometryType1, dataFormat, granuleScene, urlData, null);

			} catch (BmapException e) {

				LOG.info(e.getMessage(), e);
				throw e;
			}
		case GEOTIFFDATA:
			try {

				// attributes
				float fileSize1 = (float) 100.0;
				String fileName1 = "sarr_data_file.ext";
				String filePath1 = "C:\\dummy\\sar_data_path";
				LocalDateTime updateDate1 = LocalDateTime.now();
				LocalDateTime acquisitionDate1 = LocalDateTime.of(2018, 11, 12, 12, 00);
				LocalDateTime uploadDate1 = LocalDateTime.of(2018, 11, 12, 12, 00);
				Data sourceData1 = null;
				java.util.Collection<Algorithm> collectionAlgorithm1 = null;
				String layerName1 = "sar data layer name";
				String processingLevel1 = "L0";
				String productType1 = Granule.PRODUCT_TYPE_SLC;
				String epsgCodeNative = "EPSG:4326";
				String epsgCodeDeclared = "EPSG:4326";
				String orbitDirection = "ASCENDING";
				String orbitNumber = "13";
				Double width = new Double("1230.12");
				Double height = new Double("1230.12");
				Instrument instrument = new Instrument("E-SAR", "My description sar");
				List<Instrument> listInstrument = new ArrayList();
				listInstrument.add(instrument);
				Platform platform = new Platform("my description", "Dornier DO228 ", listInstrument);
				Quadrangle quadrangle1 = new Quadrangle(QuadrangleType.LATLONG,
						new Coordinate[] { new Coordinate(1.0, 1.0), new Coordinate(3.0, 1.0), new Coordinate(1.0, 3.0),
								new Coordinate(3.0, 3.0) },
						3, 3);
				Polarization polarization1 = Polarization.HH;
				String geometryType1 = "GRD";
				SubRegion subRegion1 = new SubRegion("sub-region name 1");
				Collection collection = new Collection("ground campaign name 1");
				collection.setCategoryKeyWords(Collection.COLLECTION_TYPE_SATELLITE);
				String dataFormat = "tiff";

				Granule granuleScene = new Granule();
				granuleScene.setName("paracou");
				granuleScene.setStatus(Status.SHARED);
				granuleScene.setPrivacy(Privacy.PRIVATE);

				String urlData = "http://s3server/sarrwurl.tiff";
				return new Granule(fileName1, updateDate1, null, null, productType1, epsgCodeNative, epsgCodeDeclared,
						width, height, platform, quadrangle1, polarization1, collection, subRegion1, fileSize1,
						filePath1, acquisitionDate1, uploadDate1, sourceData1, collectionAlgorithm1, layerName1,
						geometryType1, dataFormat, granuleScene, urlData, null);

			} catch (BmapException e) {

				LOG.info(e.getMessage(), e);
				throw e;
			}
		default:
			LOG.error(Common.getMessageValue("utilstest.initdata.error"));
			throw new BmapException(Common.getMessageValue("utilstest.initdata.error"));
		}
	}

	public static GranuleCriteria initGranuleCriteria() throws BmapException {

		try {
			String granuleName = "granule Name";
			// data criteria attributes
			LocalDate startDate = LocalDate.of(2018, 01, 01);
			LocalDate endDate = LocalDate.of(2999, 01, 01);
			// product types list
			List<String> productTypes = new ArrayList<>();
			productTypes.add("desert");
			productTypes.add("tropical");
			// instruments list
			List<String> instrumentNames = new ArrayList<>();
			instrumentNames.add("instrument name 1");
			instrumentNames.add("instrument name 2");
			// location
			Quadrangle location = new Quadrangle(QuadrangleType.LATLONG, new Coordinate[] { new Coordinate(1.0, 1.0),
					new Coordinate(6.0, 1.0), new Coordinate(1.0, 6.0), new Coordinate(6.0, 6.0) }, 6, 6);
			// polarizations list
			List<Polarization> polarizations = new ArrayList<>();
			polarizations.add(Polarization.HH);
			polarizations.add(Polarization.HV);
			// geometry types list
			List<String> geometryTypes = new ArrayList<>();
			geometryTypes.add("GRD");
			geometryTypes.add("SLC");
			// processing levels list
			List<String> processingLevels = new ArrayList<>();
			processingLevels.add("L0");
			processingLevels.add("L1");
			// ground campaign names list
			List<String> collectionNames = new ArrayList<>();
			collectionNames.add("ground campaign name 1");
			collectionNames.add("ground campaign name 2");
			// sub-region names list
			List<String> subRegionNames = new ArrayList<>();
			subRegionNames.add("sub-region name 1");
			subRegionNames.add("sub-region name 2");

			GranuleCriteria granuleCriteria = new GranuleCriteria();
			granuleCriteria.setGranuleName(granuleName);
			granuleCriteria.setStartDate(startDate);
			granuleCriteria.setEndDate(endDate);
			granuleCriteria.setProductTypes(productTypes);
			granuleCriteria.setGeometryTypes(geometryTypes);
			granuleCriteria.setInstrumentNames(instrumentNames);
			granuleCriteria.setLocation(location);
			granuleCriteria.setPolarizations(polarizations);
			granuleCriteria.setProcessingLevels(processingLevels);
			granuleCriteria.setCollectionNames(collectionNames);
			granuleCriteria.setSubRegionNames(subRegionNames);
			return granuleCriteria;
			// return an instance of data criteria with attributes
			// return new GranuleCriteria(granuleName, startDate, endDate, productTypes,
			// instrumentNames, location,
			// polarizations, geometryTypes, processingLevels, collectionNames,
			// subRegionNames, null, null, null);

		} catch (BmapException e) {

			LOG.info(e.getMessage(), e);
			throw e;
		}
	}
}
