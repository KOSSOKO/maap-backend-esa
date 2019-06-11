package com.esa.bmap.external.services.CMR;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
//CMR Model :
import com.esa.bmap.external.model.cmr.collections.Collection;
import com.esa.bmap.external.model.cmr.granules.AdditionalAttributeRef;
import com.esa.bmap.external.model.cmr.granules.BoundingRectangle;
import com.esa.bmap.external.model.cmr.granules.CollectionRef;
import com.esa.bmap.external.model.cmr.granules.GPolygon;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.model.cmr.granules.InstrumentRef;
import com.esa.bmap.external.model.cmr.granules.ListOfAdditionalAttributeValues;
import com.esa.bmap.external.model.cmr.granules.PlatformRef;
import com.esa.bmap.external.model.cmr.granules.SensorRef;
// ESA Model : 
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Instrument;
import com.esa.bmap.model.Platform;
import com.esa.bmap.model.Polarization;
import com.esa.bmap.model.Quadrangle;
import com.esa.bmap.model.QuadrangleType;
import com.esa.bmap.model.Sensor;
import com.esa.bmap.model.SubRegion;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * 
 * @author THBLED
 *
 */
/**
 * @author edupin
 *
 */
@Component
// proxyMode necessary to avoid conflict in multiuser mode
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MapperCMRToESA implements CMRAdditionAttributes {

	private static final String TRUE = "true";

	private static final Logger LOG = LoggerFactory.getLogger(MapperCMRToESA.class);

	@Value("${datasource.dataDirectory}")
	private String dataDirectory;

	@Value("${geoserver.workspace}")
	private String geoserverWorkspace;

	/**
	 * Default Constructor
	 */
	public MapperCMRToESA() {
		super();

	}

	/**
	 * Instantiate thanks to the proxyMode a new instance for each request shared by
	 * the private methods of the class
	 */
	Map<String, List<String>> additionalAttributesTypeList;

	/**
	 * Map of the additional attributes of a collection
	 */
	Map<String, String> collectionAdditionalAttributesTypeList;

	/**
	 * Return an hash map of the additional attributes of the specified granule
	 * 
	 * @param granule
	 * @return the map of the additional attributes
	 */
	private Map<String, List<String>> getMapOfAdditionalAttributes(Granule granule) {
		additionalAttributesTypeList = new HashMap<String, List<String>>();
		ListOfAdditionalAttributeValues listOfValues = null;
		if (granule.getAdditionalAttributes() != null
				&& granule.getAdditionalAttributes().getAdditionalAttribute() != null) {
			List<AdditionalAttributeRef> list = granule.getAdditionalAttributes().getAdditionalAttribute();
			AdditionalAttributeRef additionalAttributeRef;
			for (int y = 0; y < list.size(); y++) {
				additionalAttributeRef = granule.getAdditionalAttributes().getAdditionalAttribute().get(y);
				listOfValues = additionalAttributeRef.getValues();

				additionalAttributesTypeList.put(additionalAttributeRef.getName(), listOfValues.getValue());
			}

		}
		return additionalAttributesTypeList;
	}

	/**
	 * Return an hash map of the additional attributes of the specified collection
	 * 
	 * @param collection
	 * @return the map of the additional attributes
	 */
	private Map<String, String> getMapOfAdditionalAttributes(Collection collection) {
		collectionAdditionalAttributesTypeList = new HashMap<String, String>();

		if (collection.getAdditionalAttributes() != null
				&& collection.getAdditionalAttributes().getAdditionalAttribute() != null) {

			for (int y = 0; y < collection.getAdditionalAttributes().getAdditionalAttribute().size(); y++) {

				;

				collectionAdditionalAttributesTypeList.put(
						collection.getAdditionalAttributes().getAdditionalAttribute().get(y).getName(),
						collection.getAdditionalAttributes().getAdditionalAttribute().get(y).getValue());
			}

		}
		return collectionAdditionalAttributesTypeList;
	}

	/**
	 * Convert a Granule object to a Data ESA java class
	 * 
	 * @param granule The full Granule cmr Object
	 * @return The full Esa Data java class
	 * @throws BmapException
	 */
	public com.esa.bmap.model.Granule mapCMRToESA(Granule granule, Collection collection) throws BmapException {
		LOG.debug("Start of  mapCMRToESA Class");
		com.esa.bmap.model.Granule bmaapGranule = new com.esa.bmap.model.Granule();

		additionalAttributesTypeList = getMapOfAdditionalAttributes(granule);

		collectionAdditionalAttributesTypeList = getMapOfAdditionalAttributes(collection);

		bmaapGranule = mapAllFieldsAndAdditionalAttributes(granule, collection, bmaapGranule);

		bmaapGranule = mapToBMAAGranule(granule, collection, bmaapGranule);

		int indexOfLast = bmaapGranule.getName().lastIndexOf(".");
		String fileWithoutExtension = bmaapGranule.getName();
		if (indexOfLast >= 0)
			fileWithoutExtension = bmaapGranule.getName().substring(0, indexOfLast);

		if (bmaapGranule.getProductType() != null && bmaapGranule.getProductType().equals("SLC")) {
			fileWithoutExtension = fileWithoutExtension + "_amplitude";
		}
		for (int i = 0; i < bmaapGranule.getDataList().size(); i++) {

			bmaapGranule.getDataList().get(i).setLayerName(geoserverWorkspace + ":" + fileWithoutExtension);

		}

		LOG.debug("End of mapCMRToESA Class");
		return bmaapGranule;
	}

	// ==========================================================================
	// =================== AirbornData :
	// ========================================
	// ==========================================================================
	/**
	 * Construct an AirbornData Object from a Cmr Granule
	 * 
	 * @param granule A full Cmr granule Object
	 * @return A full ESA AirbornData Object
	 * @throws BmapException
	 */
	@Deprecated
	public com.esa.bmap.model.Granule mapToAirbornData(Granule granule, Collection collection,
			com.esa.bmap.model.Granule bmaapData) throws BmapException {
		LOG.debug("Start of mappingToAirbornData ");
		LOG.debug("Create an AirbornData Object (SCENE, ROI, DATA");

		// ----------------------------QUADRANGLE
		LOG.debug(" Quadrangle mapping");
		if (granule.getSpatial() != null) {
			addSpacialAttributes(granule, bmaapData);
		} else {
			LOG.info("  No Quadrangle specified in the granule : " + granule.getGranuleUR());
		}
		// ----------------------------ADDITIONALS ATTRIBUTES :
		// The variable additionalAttributes is used to store the additional
		// attributes as key,value
		Map additionalAttributes = new HashMap<String, Object>();
		if (granule.getAdditionalAttributes() != null
				&& granule.getAdditionalAttributes().getAdditionalAttribute() != null) {
			// ----------------------------Polarisation
			LOG.debug(" Polarization mapping.");

			List<AdditionalAttributeRef> list = granule.getAdditionalAttributes().getAdditionalAttribute();
			AdditionalAttributeRef additionalAttributeRef;
			for (int y = 0; y < list.size(); y++) {
				additionalAttributeRef = granule.getAdditionalAttributes().getAdditionalAttribute().get(y);
				additionalAttributes.put(additionalAttributeRef.getName(),
						additionalAttributeRef.getValues().getValue());
				// Search Polarisation attributes in all additionalAttribute
				// field :
				List<Polarization> polarizations = new ArrayList<>();
				Polarization polarization1 = null;

				if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getName()
						.equals("Polarization")) {
					// granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue().get(y);
					int sizePolarizationList = granule.getAdditionalAttributes().getAdditionalAttribute().get(y)
							.getValues().getValue().size();
					for (int i = 0; i < sizePolarizationList; i++) {
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("HH")) {
							polarization1 = Polarization.HH;
							polarizations.add(Polarization.HH);

						}
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("HV")) {
							polarization1 = Polarization.HV;
							polarizations.add(Polarization.HV);
						}
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("VH")) {
							polarization1 = Polarization.VH;
							polarizations.add(Polarization.VH);
						}
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("VV")) {
							polarization1 = Polarization.VV;
							polarizations.add(Polarization.VV);
						}
					}

					bmaapData.setPolarization(polarization1);
				}
				// ---------------------------- SubRegion :
				LOG.debug("SubRegion mapping.");
				for (y = 0; y < list.size(); y++) {
					if (list.get(y).getName().equals("Site Name")) {
						SubRegion subRegion = new SubRegion();
						// Create a subRegion in ESA dataModels :
						subRegion.setName(list.get(y).getValues().getValue().toString());
						bmaapData.setSubRegion(subRegion);

					}
				}
			}
		}
		LOG.debug("End of mappingToAirbornData Class");
		return bmaapData;
	}

	private void addSpacialAttributes(Granule granule, com.esa.bmap.model.Granule bmaapData) {
		List<Object> listCoordinate = granule.getSpatial().getHorizontalSpatialDomain().getGeometry()
				.getPointOrBoundingRectangleOrGPolygon();
		Object o = listCoordinate.get(0);
		// IF the coordinate system of the granule is a BoundingRectanle
		if (o instanceof BoundingRectangle) {
			double W = Double.parseDouble(((BoundingRectangle) o).getWestBoundingCoordinate().toString());
			double N = Double.parseDouble(((BoundingRectangle) o).getNorthBoundingCoordinate().toString());
			// -------------
			double E = Double.parseDouble(((BoundingRectangle) o).getEastBoundingCoordinate().toString());
			double S = Double.parseDouble(((BoundingRectangle) o).getSouthBoundingCoordinate().toString());

			// FORMAT : (WN) (ES)(-W-N) (-E-S)
			// TODO test if its works ( reverse coordinate and height and
			// width
			// )
			Quadrangle quadrangle = new Quadrangle(QuadrangleType.LATLONG, new Coordinate[] { new Coordinate(W, N),
					new Coordinate(E, S), new Coordinate((W * -1), (N * -1)), new Coordinate((E * -1), (S * -1)) }, 3,
					3);

			bmaapData.setQuadrangle(quadrangle);
		} else if (o instanceof GPolygon) {
			// TODO have to implement multi points quadrangle Gpolygon
			// 5points ,
			// 3 points ,
			// ....
			// if the coordinate is a Gpolygon
			double Lat1 = ((GPolygon) o).getBoundary().getPoint().get(0).getPointLatitude().doubleValue();
			double Long1 = ((GPolygon) o).getBoundary().getPoint().get(0).getPointLongitude().doubleValue();

			double Lat2 = ((GPolygon) o).getBoundary().getPoint().get(1).getPointLatitude().doubleValue();
			double Long2 = ((GPolygon) o).getBoundary().getPoint().get(1).getPointLongitude().doubleValue();

			double Lat3 = ((GPolygon) o).getBoundary().getPoint().get(2).getPointLatitude().doubleValue();
			double Long3 = ((GPolygon) o).getBoundary().getPoint().get(2).getPointLongitude().doubleValue();

			double Lat4 = ((GPolygon) o).getBoundary().getPoint().get(3).getPointLatitude().doubleValue();
			double Long4 = ((GPolygon) o).getBoundary().getPoint().get(3).getPointLongitude().doubleValue();

			Quadrangle quadrangle = new Quadrangle(QuadrangleType.LATLONG,
					new Coordinate[] { new Coordinate(Long1, Lat1), new Coordinate(Long2, Lat2),
							new Coordinate(Long3, Lat3), new Coordinate(Long4, Lat4) },
					3, 3);
			bmaapData.setQuadrangle(quadrangle);
		} else {
			LOG.debug("PROBLEM : Check the coordinate system for the quadrangle Mapping");
		}
	}

	/**
	 * Create data for the bmaap granule
	 * 
	 * @param granule the CMR object
	 * @param bmaapData the bmaap object
	 * @return the bmaap object
	 */
	private com.esa.bmap.model.Granule createData(Granule granule, com.esa.bmap.model.Granule bmaapData) {
		if (granule.getOnlineAccessURLs() != null && granule.getOnlineAccessURLs().getOnlineAccessURL() != null
				&& granule.getOnlineAccessURLs().getOnlineAccessURL().size() > 0) {
			LOG.info("createData for = " + granule.getGranuleUR());

			List<String> geometryTypeValues = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBTE_GEOMETRY_TYPE);
			for (int i = 0; i < granule.getOnlineAccessURLs().getOnlineAccessURL().size(); i++) {
				String urlOnLine = granule.getOnlineAccessURLs().getOnlineAccessURL().get(i).getURL();
				URL aURL = null;
				try {
					aURL = new URL(urlOnLine);
					LOG.debug("  url filename = " + aURL.getFile());

				} catch (MalformedURLException e) {
					LOG.error(" bad CMR online url ", e.getMessage());
					// can occured with url online S3 in this case the localFileName is null
				}

				String localFilePath = null;
				String localFileName = null;
				if (aURL != null) {
					Path pathUri = null;
					pathUri = Paths.get(aURL.getPath());

					Path filePath = Paths.get(dataDirectory, pathUri.getParent().getFileName().toString(),
							pathUri.getFileName().toString());
					localFilePath = filePath.toString();
					localFileName = pathUri.getFileName().toString();
					LOG.debug("  data file path :  " + localFilePath);
				} else {
					// no local file for bad url
					localFilePath = null;
					localFileName = null;
				}

				Data data = new Data();

				if (geometryTypeValues != null && geometryTypeValues.size() > 0) {
					if (geometryTypeValues.get(0).equals(TRUE)) {
						data.setGeometryType(com.esa.bmap.model.Granule.GEOMETRY_TYPE_GEOLOCATED);
					} else {
						data.setGeometryType(com.esa.bmap.model.Granule.GEOMETRY_TYPE_NON_GEOLOCATED);
					}
				}
				data.setFileName(localFileName);
				data.setUploadDate(convertCMRDateToESADate(granule.getLastUpdate()));
				data.setFilePath(localFilePath);
				data.setUrlToData(granule.getOnlineAccessURLs().getOnlineAccessURL().get(i).getURL());

				// parse Double to float and set size

				addLayerName(granule, bmaapData, data);

				bmaapData.getDataList().add(data);

			}

		}
		return bmaapData;
	}

	/**
	 * Add a layerName from granule
	 * 
	 * @param granule
	 * @param bmaapData
	 * @param data
	 */
	private void addLayerName(Granule granule, com.esa.bmap.model.Granule bmaapData, Data data) {
		if (granule.getOnlineAccessURLs().getOnlineAccessURL().size() == 1) {
			if (granule.getDataGranule() != null) {
				data.setFileSize((float) (granule.getDataGranule().getSizeMBDataGranule() * SIZE_FACTOR_1000));
			}
		} // else we can't as sign file size on many online access url
		data.setAcquisitionDate(convertCMRDateToESADate(granule.getLastUpdate()));

		int indexOfLast = bmaapData.getName().lastIndexOf(".");
		String fileWithoutExtension = bmaapData.getName();
		if (indexOfLast >= 0)
			fileWithoutExtension = bmaapData.getName().substring(0, indexOfLast);

		if (bmaapData.getProductType() != null && bmaapData.getProductType().equals("SLC")) {
			fileWithoutExtension = fileWithoutExtension + "_amplitude";
		}
		data.setLayerName(geoserverWorkspace + ":" + fileWithoutExtension);
	}


	/**
	 * Map all the fields and the additional attributes when there are available
	 * 
	 * 
	 * @param granule the CMR object
	 * @param collection the CMR collection
	 * @param bmaapGranule the bmaap object
	 * @return the bmaap Granule
	 */
	private com.esa.bmap.model.Granule mapAllFieldsAndAdditionalAttributes(Granule granule, Collection collection,
			com.esa.bmap.model.Granule bmaapGranule) {
		// ==================================================
		// -------Collection REF :
		CollectionRef collectionRef = granule.getCollection();

		com.esa.bmap.model.Collection bmaapCollection = bmaapGranule.getCollection();
		if (bmaapCollection == null) {
			String urlCarto = null;
			if (collectionAdditionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_URL_CARTO) != null) {
				urlCarto = collectionAdditionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_URL_CARTO).toString();
			}
			String categoryKeywords = null;
			if (collectionAdditionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_CATEGORY_KEY_WORDS) != null) {
				categoryKeywords = collectionAdditionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_CATEGORY_KEY_WORDS)
						.toString();
			}
			bmaapCollection = new com.esa.bmap.model.Collection(collection.getShortName(), collection.getDataSetId(),
					collection.getVersionId(), urlCarto, categoryKeywords, collection.getProcessingLevelId());
			// -----------------Processing Level based at collection level :
			// if the granule is US : processing level have to be redefined
			String processingLevel = collection.getProcessingLevelId();
			LOG.debug(Common.getMessageValue(COLLECTION_PROCESSING_LEVEL_MESSAGE), processingLevel);

			bmaapGranule.setCollection(bmaapCollection);
		}

		bmaapGranule.setUpdateDate(convertCMRDateToESADate(granule.getLastUpdate()));

		// ==================================================

		// ================================================
		// --------- Additional Attributes :
		// ==================================================

		// -------Granule :
		bmaapGranule.setName(granule.getGranuleUR());

		// ==================================================

		bmaapGranule = createData(granule, bmaapGranule);

		// Initialize the additional attributes in a map
		// Map<String, List<String>> additionalAttributesTypeList =
		// getMapOfAdditionalAttributes(granule);
		// Set Scene Name if specified

		List<String> values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SUB_REGION);
		if (values != null && values.size() > 0) {
			SubRegion subRegion = new SubRegion();
			// Create a subRegion in ESA dataModels :
			subRegion.setName(values.get(0));
			bmaapGranule.setSubRegion(subRegion);
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_NAME);
		if (values != null && values.size() > 0) {
			com.esa.bmap.model.Granule bmaapScene = new com.esa.bmap.model.Granule();
			bmaapScene.setName(values.get(0));
			bmaapGranule.setGranuleScene(bmaapScene);
		}

		// Set Product Type if specified
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_PRODUCT_TYPE);
		if (values != null && values.size() > 0) {
			bmaapGranule.setProductType(values.get(0));
		}

		// Min Max Avg StdDeviation
		// Min
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_BANDS_MIN);
		if (values != null && values.size() > 0 && bmaapGranule.getDataList().size() > 0) {
			List<Double> listOfDouble = values.stream().map(Double::parseDouble).collect(Collectors.toList());
			bmaapGranule.getDataList().get(0).setMins(listOfDouble);
		}

		// Max Avg StdDeviation
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_BANDS_MAX);
		if (values != null && values.size() > 0 && bmaapGranule.getDataList().size() > 0) {
			List<Double> listOfDouble = values.stream().map(Double::parseDouble).collect(Collectors.toList());
			bmaapGranule.getDataList().get(0).setMaxs(listOfDouble);
		}
		// Avg
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_BANDS_AVERAGE);
		if (values != null && values.size() > 0 && bmaapGranule.getDataList().size() > 0) {
			List<Double> listOfDouble = values.stream().map(Double::parseDouble).collect(Collectors.toList());
			bmaapGranule.getDataList().get(0).setAvgs(listOfDouble);
		}
		// StdDeviation
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_BANDS_STANDARD_DEVIATION);
		if (values != null && values.size() > 0 && bmaapGranule.getDataList().size() > 0) {
			List<Double> listOfDouble = values.stream().map(Double::parseDouble).collect(Collectors.toList());
			bmaapGranule.getDataList().get(0).setStdDeviations(listOfDouble);
		}

		// SCENE Attributes
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_GRD_RESOL);
		if (values != null && values.size() > 0) {
			bmaapGranule.setGrdResol(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_PIXEL_SPACING);
		if (values != null && values.size() > 0) {
			bmaapGranule.setPixelSpacing(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_SLR_START);
		if (values != null && values.size() > 0) {
			bmaapGranule.setSlrStart(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_SURFACE_RESOL);
		if (values != null && values.size() > 0) {
			bmaapGranule.setSurfaceResol(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_Z_FIELD);
		if (values != null && values.size() > 0) {
			bmaapGranule.setzTerrain(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_Z_FLIGHT);
		if (values != null && values.size() > 0) {
			bmaapGranule.setzFlight(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SCENE_HEADING);
		if (values != null && values.size() > 0) {
			bmaapGranule.setHeading(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_HEIGHT);
		if (values != null && values.size() > 0) {
			bmaapGranule.setHeight(new Double(values.get(0)));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_WIDTH);
		if (values != null && values.size() > 0) {
			bmaapGranule.setWidth(new Double(values.get(0)));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_EPSG_CODE_NATIVE);
		if (values != null && values.size() > 0) {
			bmaapGranule.setEpsgCodeNative(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_EPSG_CODE_DECLARED);
		if (values != null && values.size() > 0) {
			bmaapGranule.setEpsgCodeDeclared(values.get(0));
		}

		// SCENE reference to granule
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}
		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.getGranuleList().add(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_MASTER_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.setMaster(values.get(0));
		}

		values = additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_DEM_GRANULE_REF);
		if (values != null && values.size() > 0) {
			bmaapGranule.setDem(values.get(0));
		}

		return bmaapGranule;
	}

	// ==========================================================================
	// =================== SARRawData :
	// =========================================
	// ==========================================================================
	/**
	 * Construct a SARRawData Object from a Cmr Granule
	 * 
	 * @param granule A full Cmr granule Object
	 * @return A full ESA SARRawData Object
	 * @throws BmapException
	 */
	public com.esa.bmap.model.Granule mapToBMAAGranule(Granule granule, Collection collection,
			com.esa.bmap.model.Granule bmaapGranule) throws BmapException {
		LOG.debug("Start of mappingToSARRawData Class");
		LOG.debug("Create an SARRawData Object");

		if (granule.getPlatforms() != null) {
			List<PlatformRef> platfomRef = granule.getPlatforms().getPlatform();
			Platform bmaapPlatForm = new Platform();
			bmaapPlatForm.setName(platfomRef.get(0).getShortName());
			bmaapGranule.setPlatform(bmaapPlatForm);
			// -----------INSTRUMENTS : *
			LOG.debug(" Instruments mapping");
			// get all platforms in granule :
			for (int i = 0; i < platfomRef.size(); i++) {
				List<InstrumentRef> Ginstruments = platfomRef.get(i).getInstruments().getInstrument();
				// in all platfomrs search all instruments
				for (int y = 0; y < Ginstruments.size(); y++) {
					InstrumentRef instrumentRef = Ginstruments.get(y);
					Instrument instrument = createBMAAPInstrument(instrumentRef);

					bmaapGranule.getPlatform().getListInstrument().add(instrument);
				}
			}
		}

		if (granule.getSpatial() != null) {
			addSpacialAttributes(granule, bmaapGranule);
		}

		// ----------------------------Polarisation
		// if contains additional attributes :
		addPolarization(granule, bmaapGranule);

		LOG.debug("Get Spatial info from Collection.");
		// set orbit number and orbit direction
		if (collection.getSpatial() != null && collection.getSpatial().getOrbitParameters() != null) {
			int obritNumber = Integer
					.parseInt(collection.getSpatial().getOrbitParameters().getNumberOfOrbits().toString());
			bmaapGranule.setOrbitNumber(obritNumber);
			// TODO voir avec kaylin ou perrine si c'est bien cet information qu'il faut
			// mettre
			// Orbit direction
			// /Granule/Spatial/HorizontalSpatialDomain/Orbit/StartDirection[A|D]
			// /Granule/OrbitCalculatedSpatialDomain/OrbitNumber

			// TODO data.setOrbitDirection(

		}
		if (collection.getPlatforms() != null) {
			for (int i = 0; i < collection.getPlatforms().getPlatform().size(); i++) {
				// TODO check information from collection if not present in the granule

			}
		}
		LOG.debug("End of mappingToBMAAPGranule Class");
		return bmaapGranule;
	}

	private void addPolarization(Granule granule, com.esa.bmap.model.Granule data) {
		if (granule.getAdditionalAttributes() != null
				&& granule.getAdditionalAttributes().getAdditionalAttribute() != null) {
			LOG.debug(" Polarization mapping.");
			List<AdditionalAttributeRef> list = granule.getAdditionalAttributes().getAdditionalAttribute();

			// Search Polarisation attributes in all additionalAttribute field :
			List<Polarization> polarizations = new ArrayList<>();
			Polarization polarization1 = null;
			for (int y = 0; y < list.size(); y++) {

				if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getName()
						.equals("Polarization")) {
					// granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue().get(y);
					int sizePolarizationList = granule.getAdditionalAttributes().getAdditionalAttribute().get(y)
							.getValues().getValue().size();
					for (int i = 0; i < sizePolarizationList; i++) {
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("HH")) {
							polarization1 = Polarization.HH;
							polarizations.add(Polarization.HH);

						}
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("HV")) {
							polarization1 = Polarization.HV;
							polarizations.add(Polarization.HV);
						}
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("VH")) {
							polarization1 = Polarization.VH;
							polarizations.add(Polarization.VH);
						}
						if (granule.getAdditionalAttributes().getAdditionalAttribute().get(y).getValues().getValue()
								.get(i).equals("VV")) {
							polarization1 = Polarization.VV;
							polarizations.add(Polarization.VV);
						}
					}
					data.setPolarization(polarization1);

				}

				// data.setPolarization(polarization);
			}
		}
	}

	/**
	 * Create a bmaap instrument
	 * 
	 * @param instrumentRef the cmr instrument
	 * @return
	 */
	private Instrument createBMAAPInstrument(InstrumentRef instrumentRef) {
		Instrument instrument = new Instrument();
		instrument.setName(instrumentRef.getShortName());
		if (instrumentRef.getCharacteristics() != null) {
			instrument.setDescription(instrumentRef.getCharacteristics().getCharacteristic().toString());
		}
		if (instrumentRef.getSensors() != null) {
			for (SensorRef sensorRef : instrumentRef.getSensors().getSensor()) {
				Sensor bmaapSensor = new Sensor();
				bmaapSensor.setName(sensorRef.getShortName());
				if (sensorRef.getCharacteristics() != null) {
					bmaapSensor.setDescription(sensorRef.getCharacteristics().toString());
				}
				instrument.getSensors().add(bmaapSensor);
			}
		}
		return instrument;
	}

	// =====================================================================================
	// =====================================================================================
	// =============================== GENERALS METHODES :
	// ===============================
	// =====================================================================================
	// =====================================================================================

	/**
	 * Check if the granule contains a SubRegion field in the additional attributes
	 * 
	 * @param granule full Granule Object
	 * @return boolean [true] => granule contains a SubRegion [false] => granule
	 *         does not contain a SubRegion
	 */
	private boolean checkGranuleContainsSubRegion(Granule granule) {
		LOG.debug("Start of checkGranuleContainsSubRegion Class");

		boolean subRegion = false;
		if (additionalAttributesTypeList.get(ADDITIONAL_ATTRIBUTE_SUB_REGION) != null) {
			subRegion = true;
		}
		LOG.debug("End of checkGranuleContainsSubRegion Class");
		return subRegion;
	}

	/**
	 * Convert a XMLGregorianCalendar (CMR) to a LocalDateTime (Esa)
	 * 
	 * @param date XMLGregorianCalendar (CMR)
	 * @return LocalDateTime (Esa)
	 */
	public LocalDateTime convertCMRDateToESADate(XMLGregorianCalendar date) {
		LOG.debug("Start of  ConvertCMRDateToESADate Class");
		ZonedDateTime out1;
		out1 = date.toGregorianCalendar().toZonedDateTime().toLocalDateTime().atZone(ZoneId.systemDefault());

		LocalDateTime out = out1.toLocalDateTime();

		LOG.debug("End of  ConvertCMRDateToESADate Class");
		return out;
	}

	public String getCollectionProcessingLevel(String CollectionRef) {

		return CollectionRef;

	}
}
