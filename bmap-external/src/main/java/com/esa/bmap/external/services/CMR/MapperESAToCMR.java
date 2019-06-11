package com.esa.bmap.external.services.CMR;

//GENERALS :
import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.external.model.cmr.collections.AdditionalAttribute;
import com.esa.bmap.external.model.cmr.collections.ListOfAdditionalAttributes;
//Externals Model CMR : 
import com.esa.bmap.external.model.cmr.granules.AdditionalAttributeRef;
import com.esa.bmap.external.model.cmr.granules.Boundary;
import com.esa.bmap.external.model.cmr.granules.CampaignRef;
import com.esa.bmap.external.model.cmr.granules.CollectionRef;
import com.esa.bmap.external.model.cmr.granules.DataGranule;
import com.esa.bmap.external.model.cmr.granules.DayNightFlag;
import com.esa.bmap.external.model.cmr.granules.GPolygon;
import com.esa.bmap.external.model.cmr.granules.Geometry;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.model.cmr.granules.HorizontalSpatialDomain;
import com.esa.bmap.external.model.cmr.granules.ListOfAdditionalAttributeRefs;
import com.esa.bmap.external.model.cmr.granules.ListOfAdditionalAttributeValues;
import com.esa.bmap.external.model.cmr.granules.ListOfCampaignRefs;
import com.esa.bmap.external.model.cmr.granules.ListOfMeasuredParameters;
import com.esa.bmap.external.model.cmr.granules.ListOfOnlineAccessURLs;
import com.esa.bmap.external.model.cmr.granules.MeasuredParameter;
import com.esa.bmap.external.model.cmr.granules.OnlineAccessURL;
import com.esa.bmap.external.model.cmr.granules.Point;
import com.esa.bmap.external.model.cmr.granules.RangeDateTime;
import com.esa.bmap.external.model.cmr.granules.Spatial;
import com.esa.bmap.external.model.cmr.granules.Temporal;
//ESA Model : 

import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Polarization;
import com.esa.bmap.model.Quadrangle;
import com.esa.bmap.model.util.GranuleHelper;
import com.google.common.io.Files;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * Mapper ESA DataModel to CMR DataModel classes
 * 
 * @author THBLED
 *
 */
public class MapperESAToCMR implements CMRAdditionAttributes {

	private static final Logger LOG = LoggerFactory
			.getLogger(MapperESAToCMR.class);

	public MapperESAToCMR() {
		super();

	}

	/**
	 * find and redirect the data type
	 * 
	 * @param bmaapData
	 * @param collection
	 * @return granule object
	 */
	public Granule findTypeData(com.esa.bmap.model.Granule bmapGranule,
			String collection) {
		LOG.debug("Start of  findTypeData Class");
		Granule granule = new Granule();
		granule = mapGeneralsFields(granule, bmapGranule);

		if (bmapGranule.getCollection().getCategoryKeyWords()
				.indexOf(Collection.COLLECTION_TYPE_GROUND_CAMPAIGN) >= 0) {

			if (GranuleHelper.isScene(bmapGranule)) {
				if (bmapGranule.getGranuleList().size() > 0) {
					granule = mapESAScenetoCMR(granule, bmapGranule);
				}// else first persistence of the granule scene without the list
					// of granules should be updated later
				LOG.warn("  First persistence of a granule scene, waiting update with granule list");
			} else if (GranuleHelper.isROIFile(bmapGranule.getDataList().get(0)
					.getFileName())) {
				granule = mapESAROItoCMR(granule, bmapGranule);
			} else {// it should be airborn data
				granule = mapESAAirbornDatatoCMR(granule, bmapGranule);
			}

		} else if (bmapGranule.getCollection().getCategoryKeyWords()
				.indexOf(Collection.COLLECTION_TYPE_SATELLITE) >= 0) {
			granule = mapESASARtoCMR(granule, bmapGranule);
		} else {
			LOG.error("  Enable to find the mapper to use for the categoryKeywords :"
					+ bmapGranule.getCollection().getCategoryKeyWords());
		}

		LOG.debug("End of findTypeData Class");
		return granule;
	}

	private Granule mapESAScenetoCMR(Granule granule,
			com.esa.bmap.model.Granule bmapGranule) {
		
		// Spatial Node inherited from the DEM
		if (bmapGranule.getQuadrangle() != null) {
			Spatial spatial = createSpatial(bmapGranule);
			granule.setSpatial(spatial);
		}

		ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs = granule
				.getAdditionalAttributes();
		if (listOfAdditionalAttributeRefs == null) {
			listOfAdditionalAttributeRefs = new ListOfAdditionalAttributeRefs();
			granule.setAdditionalAttributes(listOfAdditionalAttributeRefs);
		}

		// Slant range pixel spacing (m)
		AdditionalAttributeRef additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_SCENE_PIXEL_SPACING,
				bmapGranule.getPixelSpacing());
		addAdditionalAttribute(listOfAdditionalAttributeRefs,
				additionalAttributeRef2);

		// A0 used for sigma0 computation (m2)
		additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_SCENE_SURFACE_RESOL,
				bmapGranule.getSurfaceResol());
		addAdditionalAttribute(listOfAdditionalAttributeRefs,
				additionalAttributeRef2);

		// Ground range pixel spacing (m)
		additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_SCENE_GRD_RESOL, bmapGranule.getGrdResol());
		addAdditionalAttribute(listOfAdditionalAttributeRefs,
				additionalAttributeRef2);

		// Slant range swath start (m)
		additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_SCENE_SLR_START, bmapGranule.getSlrStart());
		addAdditionalAttribute(listOfAdditionalAttributeRefs,
				additionalAttributeRef2);
		
		// Average altitude of the ground (m)
		additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_SCENE_Z_FIELD, bmapGranule.getzTerrain());
		addAdditionalAttribute(listOfAdditionalAttributeRefs,
				additionalAttributeRef2);

		// Average altitude of flight (m)
		additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_SCENE_Z_FLIGHT, bmapGranule.getzFlight());
		addAdditionalAttribute(listOfAdditionalAttributeRefs,
				additionalAttributeRef2);

		// Direction of flight, clockwise, with respect to the North (deg)
		additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_SCENE_HEADING, bmapGranule.getHeading());
		addAdditionalAttribute(listOfAdditionalAttributeRefs,
				additionalAttributeRef2);

		for (String granuleName : bmapGranule.getGranuleList()) {
			String granuleType = GranuleHelper.getGranuleType(granuleName);
			if (ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF;
			} else if (ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF
					.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF;

			} else if (ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF
					.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF;

			} else if (ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF
					.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF;

			} else if (ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF
					.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF;
			} else if (ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF
					.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF;
			} else if (ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF
					.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF;
			} else if (ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF
					.endsWith(granuleType)) {
				granuleType = ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF;
			}// take only the granule type return by the helper
			additionalAttributeRef2 = createAdditionalAttribute(granuleType,
					granuleName);
			addAdditionalAttribute(listOfAdditionalAttributeRefs,
					additionalAttributeRef2);
		}

		// DEM if specified
		if (bmapGranule.getDem() != null) {
			additionalAttributeRef2 = createAdditionalAttribute(
					ADDITIONAL_ATTRIBUTE_DEM_GRANULE_REF, bmapGranule.getDem());
			addAdditionalAttribute(listOfAdditionalAttributeRefs,
					additionalAttributeRef2);
		}

		// MASTER if specified
		if (bmapGranule.getMaster() != null) {
			additionalAttributeRef2 = createAdditionalAttribute(
					ADDITIONAL_ATTRIBUTE_MASTER_GRANULE_REF,
					bmapGranule.getMaster());
			addAdditionalAttribute(listOfAdditionalAttributeRefs,
					additionalAttributeRef2);
		}
		return granule;
	}

	/**
	 * Test the additional attribute and add it in the specified list if not null
	 * @param listOfAdditionalAttributeRefs the holder of the additional attributes
	 * @param additionalAttributeRef2 the additional attribute
	 */
	private void addAdditionalAttribute(
			ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs,
			AdditionalAttributeRef additionalAttributeRef2) {
		if (additionalAttributeRef2 != null) {
			listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
					additionalAttributeRef2);
		}//else do not add the additional attribute
	}

	/**
	 * Map all the commons fields between SARRawData / AirborneData and Region
	 * of Interest
	 * 
	 * @param granule
	 * @param bmaapGranule
	 * @return
	 */
	public Granule mapGeneralsFields(Granule granule,
			com.esa.bmap.model.Granule bmaapGranule) {
		// ==================================================
		// -------Collection REF :
		CollectionRef collectionRef = new CollectionRef();

		collectionRef.setShortName(bmaapGranule.getCollection().getShortName().toUpperCase());
		collectionRef.setVersionId(bmaapGranule.getCollection().getVersionId());

		granule.setCollection(collectionRef);
		// ==================================================

		// ================================================
		// --------- Additional Attributes :
		// ==================================================
		if (granule.getAdditionalAttributes() == null) {
			ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs = new ListOfAdditionalAttributeRefs();
			granule.setAdditionalAttributes(listOfAdditionalAttributeRefs);
		}// else get the already set additional attributes

		// -------Granule :
		granule.setGranuleUR(bmaapGranule.getName());
		

		
		DataGranule dataGranule = new DataGranule();
		Temporal temporal = new Temporal();
		if (bmaapGranule.getDataList().size() > 0) {
			granule.setInsertTime(convertESADateToCMRDate(bmaapGranule
					.getDataList().get(0).getUploadDate()));

			granule.setLastUpdate(convertESADateToCMRDate(bmaapGranule
					.getUpdateDate()));
			
			
			// ------------------ Geometry_type :
			addAttributeGeolocated(bmaapGranule, granule.getAdditionalAttributes());
			
			// -------------------dataGranule
			// In case of several data do a sum of all the data
			float size = 0;
			for (int i = 0; i < bmaapGranule.getDataList().size(); i++) {
				size = size + bmaapGranule.getDataList().get(i).getFileSize();

				if (bmaapGranule.getDataList().get(i).getFilePath()
						.endsWith(EXTENSION_TIFF)) {
					List<String> listOfString = null;
					AdditionalAttributeRef attRef =null;
					if (bmaapGranule.getDataList().get(i).getMins() != null) {

						listOfString = bmaapGranule.getDataList()
								.get(i).getMins().stream()
								.map(s -> s.toString())
								.collect(Collectors.toList());
						 attRef = createAdditionalAttribute(
								ADDITIONAL_ATTRIBUTE_BANDS_MIN, listOfString);
						granule.getAdditionalAttributes()
								.getAdditionalAttribute().add(attRef);
					}
					if (bmaapGranule.getDataList().get(i).getMaxs() != null) {

						listOfString = bmaapGranule.getDataList()
								.get(i).getMaxs().stream()
								.map(s -> s.toString())
								.collect(Collectors.toList());
						 attRef = createAdditionalAttribute(
								ADDITIONAL_ATTRIBUTE_BANDS_MAX, listOfString);
						granule.getAdditionalAttributes()
								.getAdditionalAttribute().add(attRef);
					}
					if (bmaapGranule.getDataList().get(i).getAvgs() != null) {

						listOfString = bmaapGranule.getDataList()
								.get(i).getAvgs().stream()
								.map(s -> s.toString())
								.collect(Collectors.toList());
						 attRef = createAdditionalAttribute(
								ADDITIONAL_ATTRIBUTE_BANDS_AVERAGE, listOfString);
						granule.getAdditionalAttributes()
								.getAdditionalAttribute().add(attRef);
					}
					if (bmaapGranule.getDataList().get(i).getStdDeviations() != null) {

						listOfString = bmaapGranule.getDataList()
								.get(i).getStdDeviations().stream()
								.map(s -> s.toString())
								.collect(Collectors.toList());
						 attRef = createAdditionalAttribute(
								ADDITIONAL_ATTRIBUTE_BANDS_STANDARD_DEVIATION, listOfString);
						granule.getAdditionalAttributes()
								.getAdditionalAttribute().add(attRef);
					}
				}
			}

			dataGranule.setSizeMBDataGranule((double) (size / 1000));// /1000 ?
			dataGranule.setDayNightFlag(DayNightFlag.fromValue("BOTH"));

			// -------------------Production date
			dataGranule
					.setProductionDateTime(convertESADateToCMRDate(bmaapGranule
							.getDataList().get(0).getAcquisitionDate()));
			granule.setDataGranule(dataGranule);

			// -------------------temporal

			RangeDateTime rangeDateTime = new RangeDateTime();
			rangeDateTime
					.setBeginningDateTime(convertESADateToCMRDate(bmaapGranule
							.getDataList().get(0).getAcquisitionDate()));
			rangeDateTime
					.setEndingDateTime(convertESADateToCMRDate(bmaapGranule
							.getDataList().get(0).getAcquisitionDate()));
			temporal.setRangeDateTime(rangeDateTime);
			// this is not a required
			granule.setTemporal(temporal);
		}else{//Granule scene without data
			granule.setInsertTime(convertESADateToCMRDate(bmaapGranule.getUpdateDate()));
			granule.setLastUpdate(convertESADateToCMRDate(LocalDateTime.now()));
		}
		// ==================================================

		// ================================================
		// --------- Measured Parameters :
		// ==================================================
		MeasuredParameter measuredParameter = new MeasuredParameter();
		measuredParameter.setParameterName(FORESTS);
		ListOfMeasuredParameters measuredParameters = new ListOfMeasuredParameters();
		measuredParameters.getMeasuredParameter().add(measuredParameter);

		// -----------------------
		MeasuredParameter measuredParameter2 = new MeasuredParameter();
		measuredParameter2.setParameterName(CANOPY_CHARACTERISTICS);
		measuredParameters.getMeasuredParameter().add(measuredParameter2);

		// -----------------------
		MeasuredParameter measuredParameter3 = new MeasuredParameter();
		measuredParameter3
				.setParameterName(FOREST_COMPOSITION_VEGETATION_STRUCTURE);
		measuredParameters.getMeasuredParameter().add(measuredParameter3);

		// -----------------------
		MeasuredParameter measuredParameter4 = new MeasuredParameter();
		measuredParameter4.setParameterName(TOPOGRAPHIC_EFFECTS);
		measuredParameters.getMeasuredParameter().add(measuredParameter4);
		granule.setMeasuredParameters(measuredParameters);

		// -----------------------
		MeasuredParameter measuredParameter5 = new MeasuredParameter();
		measuredParameter5.setParameterName(BIOMASS);
		measuredParameters.getMeasuredParameter().add(measuredParameter5);
		granule.setMeasuredParameters(measuredParameters);

		// ==================================================

		// ------------ OnlinesRessources
		// ------------ DATA URL
		ListOfOnlineAccessURLs listOfOnlineAccessURLs = new ListOfOnlineAccessURLs();
		for (int i = 0; i < bmaapGranule.getDataList().size(); i++) {
			OnlineAccessURL onlineAccessURL = new OnlineAccessURL();
			onlineAccessURL.setURL(bmaapGranule.getDataList().get(i)
					.getUrlToData());
			if (bmaapGranule.getDataList().get(i).getFilePath()
					.endsWith(EXTENSION_TIFF)) {
				onlineAccessURL.setMimeType(MIMETYPE_IMAGE_TIFF);
			} else {
				String mimeType = MimetypesFileTypeMap
						.getDefaultFileTypeMap()
						.getContentType(
								bmaapGranule.getDataList().get(i).getFilePath());
				onlineAccessURL.setMimeType(mimeType);
			}

			listOfOnlineAccessURLs.getOnlineAccessURL().add(onlineAccessURL);
		}

		if (listOfOnlineAccessURLs.getOnlineAccessURL().size() > 0) {
			granule.setOnlineAccessURLs(listOfOnlineAccessURLs);
		}
		granule.setPrice(BigDecimal.valueOf(0));
		granule.setOrderable(false);

		if (GranuleHelper.isRegionOfInterest(bmaapGranule)) {
			addDataFormat(granule, bmaapGranule,
					ADDITIONAL_ATTRIBUTE_DATA_FORMAT_SHAPEFILE);
		} else {
			// get the filename extension of the first data
			addDataFormat(granule, bmaapGranule, null);
		}

		// Set Data Status to Official
		AdditionalAttributeRef additionalAttributeRef2 = createAdditionalAttribute(
				ADDITIONAL_ATTRIBUTE_DATASET_STATUS,
				ADDITIONAL_ATTRIBUTE_DATASET_STATUS_OFFICIAL);
		granule.getAdditionalAttributes().getAdditionalAttribute()
				.add(additionalAttributeRef2);

		// Set Scene Name if specified
		if (bmaapGranule.getGranuleScene() != null) {
			AdditionalAttributeRef additionalAttributeRef3 = createAdditionalAttribute(
					ADDITIONAL_ATTRIBUTE_SCENE_NAME, bmaapGranule
							.getGranuleScene().getName());
			granule.getAdditionalAttributes().getAdditionalAttribute()
					.add(additionalAttributeRef3);
		}

		if(bmaapGranule.getProductType()!=null){
			AdditionalAttributeRef additionalAttributeRef4 = createAdditionalAttribute(
					ADDITIONAL_ATTRIBUTE_PRODUCT_TYPE, bmaapGranule
							.getProductType());
			granule.getAdditionalAttributes().getAdditionalAttribute()
					.add(additionalAttributeRef4);
		}
		
		if(bmaapGranule.getEpsgCodeNative()!=null){
			AdditionalAttributeRef additionalAttributeRef4 = createAdditionalAttribute(
					ADDITIONAL_ATTRIBUTE_EPSG_CODE_NATIVE, bmaapGranule
							.getEpsgCodeNative());
			granule.getAdditionalAttributes().getAdditionalAttribute()
					.add(additionalAttributeRef4);
		}
		
		if(bmaapGranule.getEpsgCodeDeclared()!=null){
			AdditionalAttributeRef additionalAttributeRef4 = createAdditionalAttribute(
					ADDITIONAL_ATTRIBUTE_EPSG_CODE_DECLARED, bmaapGranule
							.getEpsgCodeDeclared());
			granule.getAdditionalAttributes().getAdditionalAttribute()
					.add(additionalAttributeRef4);
		}
		
		return granule;
	}

	/**
	 * Add the dataformat to the granule
	 * 
	 * @param granule the cmr objet
	 * @param bmaapGranule the bmaap object
	 */
	private void addDataFormat(Granule granule,
			com.esa.bmap.model.Granule bmaapGranule, String format) {
		if (bmaapGranule.getDataList() != null
				&& bmaapGranule.getDataList().size() > 0) {
			AdditionalAttributeRef additionalAttributeDataFormat = null;
			if (format == null) {
				// put the file extension in the data format additional
				// attribute
				additionalAttributeDataFormat = createAdditionalAttribute(
						ADDITIONAL_ATTRIBUTE_DATA_FORMAT,
						Files.getFileExtension(bmaapGranule.getDataList()
								.get(0).getFileName()));

			} else {
				additionalAttributeDataFormat = createAdditionalAttribute(
						ADDITIONAL_ATTRIBUTE_DATA_FORMAT, format);
			}
			granule.getAdditionalAttributes().getAdditionalAttribute()
					.add(additionalAttributeDataFormat);
		}
	}

	/**
	 * Method to create an additional attribute using simple string key and
	 * value.
	 * 
	 * @param key name of the additional attribute
	 * @param value of the additional attribute
	 * @return AdditionalAttributeRef
	 */
	public AdditionalAttributeRef createAdditionalAttribute(String key,
			String value) {
		AdditionalAttributeRef additionalAttributeRef2 = null;
		if (value != null && value.length() > 0) {
			additionalAttributeRef2 = new AdditionalAttributeRef();
			ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
			listOfAdditionalAttributeValues2.getValue().add(value);
			additionalAttributeRef2.setName(key);
			additionalAttributeRef2.setValues(listOfAdditionalAttributeValues2);
		}
		return additionalAttributeRef2;
	}

	/**
	 * Method to create a list of additional attribute using simple string key
	 * and a list of string.
	 * 
	 * @param key name of the additional attribute
	 * @param value of the additional attribute
	 * @return AdditionalAttributeRef
	 */
	public AdditionalAttributeRef createAdditionalAttribute(String key,
			List<String> values) {
		AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();
		ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
		listOfAdditionalAttributeValues2.getValue().addAll(values);
		additionalAttributeRef2.setName(key);
		additionalAttributeRef2.setValues(listOfAdditionalAttributeValues2);
		return additionalAttributeRef2;
	}

	
	/**
	 * Method to create a list of additional attribute using simple string key
	 * and a list of string.
	 * 
	 * @param key name of the additional attribute
	 * @param value of the additional attribute
	 * @return AdditionalAttributeRef
	 */
	public AdditionalAttribute createCollectionAdditionalAttribute(String key,
			String value) {
		AdditionalAttribute additionalAttributeRef2 = new AdditionalAttribute();
		additionalAttributeRef2.setName(key);
		additionalAttributeRef2.setValue(value);
		return additionalAttributeRef2;
	}
	/**
	 * Create a Granule CMR Object from an AirbornData ESA
	 * 
	 * @param granule An Empty Granule CMR
	 * @param bmapGranule A FULL AirbornData ESA
	 * @return A full Granule CMR
	 */
	public Granule mapESAAirbornDatatoCMR(Granule granule,
			com.esa.bmap.model.Granule bmapGranule) {
		LOG.debug("Start of  mapESAtoCMR AirborneData");
		LOG.debug("Source Type : AirbornData");

		// ==================================================
		// pre creates the list of additional attributes for later use
		ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs = granule
				.getAdditionalAttributes();
		if (listOfAdditionalAttributeRefs == null) {
			listOfAdditionalAttributeRefs = new ListOfAdditionalAttributeRefs();
			granule.setAdditionalAttributes(listOfAdditionalAttributeRefs);
		}

		// ----------- Spatial Node
		Spatial spatial = createSpatial(bmapGranule);
		granule.setSpatial(spatial);
		
		//Set the Height Width from granule
		if (bmapGranule.getHeight() != null) {
			granule.getAdditionalAttributes()
					.getAdditionalAttribute()
					.add(createAdditionalAttribute(ADDITIONAL_ATTRIBUTE_HEIGHT,
							bmapGranule.getHeight().toString()));
		}
		if (bmapGranule.getWidth() != null) {
			granule.getAdditionalAttributes()
					.getAdditionalAttribute()
					.add(createAdditionalAttribute(ADDITIONAL_ATTRIBUTE_WIDTH,
							bmapGranule.getWidth().toString()));
		}
		// ==================================================
		// --------- listOfCampaignRefs
		// =================================================
// Required project declaration in MMT. "Project" is an alias for "Campaign"
// Project References have [biosar1] which do not reference any projects in parent collection.
//		ListOfCampaignRefs listOfCampaignRefs = new ListOfCampaignRefs();
//		CampaignRef campaignRef = new CampaignRef();
//		campaignRef.setShortName(bmapGranule.getCollection().getShortName());
//		listOfCampaignRefs.getCampaign().add(campaignRef);
//		granule.setCampaigns(listOfCampaignRefs);

		// ==================================================
		// --------- Additional Attributes : Polarisation HH HV VH VV
		// ==================================================

		// if (data.getPolarization().values().length)

		if (bmapGranule.getPolarization() != null) {
			// IF match we create an additional field
			if (bmapGranule.getPolarization().equals(Polarization.HV)) {
				AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();
				ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
				listOfAdditionalAttributeValues2.getValue().add("HV");
				additionalAttributeRef2.setName("Polarization");
				additionalAttributeRef2
						.setValues(listOfAdditionalAttributeValues2);
				listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
						additionalAttributeRef2);
			} else if (bmapGranule.getPolarization().equals(Polarization.HH)) {
				AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();
				ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
				listOfAdditionalAttributeValues2.getValue().add("HH");
				additionalAttributeRef2.setName("Polarization");
				additionalAttributeRef2
						.setValues(listOfAdditionalAttributeValues2);
				listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
						additionalAttributeRef2);
			} else if (bmapGranule.getPolarization().equals(Polarization.VV)) {
				AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();
				ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
				listOfAdditionalAttributeValues2.getValue().add("VV");
				additionalAttributeRef2.setName("Polarization");
				additionalAttributeRef2
						.setValues(listOfAdditionalAttributeValues2);
				listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
						additionalAttributeRef2);
			} else if (bmapGranule.getPolarization().equals(Polarization.VH)) {
				AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();
				ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
				listOfAdditionalAttributeValues2.getValue().add("VH");
				additionalAttributeRef2.setName("Polarization");
				additionalAttributeRef2
						.setValues(listOfAdditionalAttributeValues2);
				listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
						additionalAttributeRef2);
			}
		}

		// ------------------ SubRegion :
		addSubRegion(granule, bmapGranule);

		// ------------------ Instrument :
		// Should be inherited from the collection to check
		// ==================================================
		// ==================================================
		// --------------------ADD ALL


		LOG.debug("End of  mapESAtoCMR AirborneData");
		return granule;
	}

	/**
	 * Add the CMR attribute Geolocated in case of data with  geometrytype geolocated
	 * @param bmapGranule
	 * @param listOfAdditionalAttributeRefs
	 */
	private void addAttributeGeolocated(com.esa.bmap.model.Granule bmapGranule,
			ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs) {
		AdditionalAttributeRef additionalAttributeRef5 = new AdditionalAttributeRef();
		// define the additionals attributes fields
		additionalAttributeRef5.setName(ADDITIONAL_ATTRIBTE_GEOMETRY_TYPE);
		// add all the values in the additional attributes node
		ListOfAdditionalAttributeValues listOfAdditionalAttributeValues5 = new ListOfAdditionalAttributeValues();
		listOfAdditionalAttributeValues5 = new ListOfAdditionalAttributeValues();

		if (com.esa.bmap.model.Granule.GEOMETRY_TYPE_GEOLOCATED
				.equals(bmapGranule.getDataList().get(0).getGeometryType())) {
			listOfAdditionalAttributeValues5.getValue().add("true");
		} else {
			listOfAdditionalAttributeValues5.getValue().add("false");
		}
		additionalAttributeRef5.setValues(listOfAdditionalAttributeValues5);
		listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
				additionalAttributeRef5);
	}

	/**
	 * Create Spatial node for CMR object
	 * @param bmapGranule
	 * @return
	 */
	private Spatial createSpatial(com.esa.bmap.model.Granule bmapGranule) {
		Spatial spatial = new Spatial();
		HorizontalSpatialDomain horizontalSpatialDomain = new HorizontalSpatialDomain();
		Geometry geometry = new Geometry();
		GPolygon gPolygon = new GPolygon();

		if (bmapGranule.getQuadrangle().getGeometry() != null) {

			gPolygon.setBoundary(createBoundaryGpolygon(bmapGranule
					.getQuadrangle()));

		} else {
			// ----Set boundary Gpolygon for non georeferenced SLC data :
			gPolygon.setBoundary(createHeightWidthGpolygon(bmapGranule
					.getQuadrangle()));
			
		}
		
		// add the polygon to the geometry
		geometry.getPointOrBoundingRectangleOrGPolygon().add(gPolygon);

		horizontalSpatialDomain.setGeometry(geometry);
		spatial.setHorizontalSpatialDomain(horizontalSpatialDomain);
		return spatial;
	}

	/**
	 * Add the attribte subregion to the cmr object
	 * 
	 * @param granule the cmr object
	 * @param bmapGranule the bmaap object
	 */
	private void addSubRegion(Granule granule,
			com.esa.bmap.model.Granule bmapGranule) {
		AdditionalAttributeRef additionalAttributeRef3 = new AdditionalAttributeRef();
		ListOfAdditionalAttributeValues listOfAdditionalAttributeValues3 = new ListOfAdditionalAttributeValues();
		if (bmapGranule.getSubRegion().getName().equals("")) {
			LOG.warn("  No Site Name specified for the granule "
					+ bmapGranule.getName());
		} else {
			listOfAdditionalAttributeValues3.getValue().add(
					bmapGranule.getSubRegion().getName());
			additionalAttributeRef3.setName(ADDITIONAL_ATTRIBUTE_SUB_REGION);
			additionalAttributeRef3.setValues(listOfAdditionalAttributeValues3);
			granule.getAdditionalAttributes().getAdditionalAttribute()
					.add(additionalAttributeRef3);
		}
	}

	/**
	 * Create a Granule CMR Object from an SARRawData ESA
	 * 
	 * @param granule An Empty Granule CMR
	 * @param bmaapData A FULL SARRawData ESA
	 * @return A full Granule CMR
	 */
	public Granule mapESASARtoCMR(Granule granule,
			com.esa.bmap.model.Granule bmapGranule) {
		LOG.debug("Start of  mapESAtoCMR ");
		LOG.debug("Source Type : SARRawData");

		// ==================================================

		// ----------- Spatial
		// TODO have to check if Lat Long is correct for all points generated :
		Spatial spatial = new Spatial();
		HorizontalSpatialDomain horizontalSpatialDomain = new HorizontalSpatialDomain();
		Geometry geometry = new Geometry();
		GPolygon gPolygon = new GPolygon();
		// ----Set boundary Gpolygon
		if (bmapGranule.getQuadrangle().getGeometry() != null) {
			gPolygon.setBoundary(createBoundaryGpolygon(bmapGranule
					.getQuadrangle()));
		}
		// add the polygon to the geometry
		geometry.getPointOrBoundingRectangleOrGPolygon().add(gPolygon);

		horizontalSpatialDomain.setGeometry(geometry);
		spatial.setHorizontalSpatialDomain(horizontalSpatialDomain);
		// ==================================================
		// --------- Additional Attributes : Polarisation HH HV VH VV
		// if (data.getPolarization().values().length)
		ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs = new ListOfAdditionalAttributeRefs();
		bmapGranule.getPolarization();
		// IF match we create an additional field
		if (Polarization.fromValue("VV") != null) {

			AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();
			ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
			listOfAdditionalAttributeValues2.getValue().add("HH");
			listOfAdditionalAttributeValues2.getValue().add("HV");
			listOfAdditionalAttributeValues2.getValue().add("VV");
			listOfAdditionalAttributeValues2.getValue().add("VH");
			additionalAttributeRef2.setName("Polarization");
			additionalAttributeRef2.setValues(listOfAdditionalAttributeValues2);
			listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
					additionalAttributeRef2);
		}

		// ===========================================================
		// ========================ADD ALL ===========================
		granule.setSpatial(spatial);
		granule.setAdditionalAttributes(listOfAdditionalAttributeRefs);
		LOG.debug("End of  MapESAtoCMR ");
		return granule;
	}

	/**
	 * Create a Granule CMR Object from an RegionOfInterest ESA
	 * 
	 * @param granule An Empty Granule CMR
	 * @param data A FULL RegionOfInterest ESA
	 * @return A full Granule CMR
	 */
	public Granule mapESAROItoCMR(Granule granule,
			com.esa.bmap.model.Granule data) {
		LOG.debug("Start of  mapESAtoCMR ");
		LOG.debug("Source Type : RegionOfInterest");

		// ----------- Spatial
		// TODO have to check if Lat Long is correct for all points generated :
		Spatial spatial = new Spatial();
		HorizontalSpatialDomain horizontalSpatialDomain = new HorizontalSpatialDomain();
		Geometry geometry = new Geometry();
		GPolygon gPolygon = new GPolygon();
		// ----Set boundary Gpolygon
		if (data.getQuadrangle().getGeometry() != null) {
			gPolygon.setBoundary(createBoundaryGpolygon(data.getQuadrangle()));
		}
		// add the polygon to the geometry
		geometry.getPointOrBoundingRectangleOrGPolygon().add(gPolygon);

		horizontalSpatialDomain.setGeometry(geometry);
		spatial.setHorizontalSpatialDomain(horizontalSpatialDomain);
		// ==================================================
		// --------- Additional Attributes :
		if (granule.getAdditionalAttributes() == null) {
			ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs = new ListOfAdditionalAttributeRefs();
			granule.setAdditionalAttributes(listOfAdditionalAttributeRefs);
		}
		// ------------------ SubRegion :
		addSubRegion(granule, data);

		// ------------ OnlinesRessources

		// ===========================================================
		// ========================ADD ALL ===========================
		granule.setSpatial(spatial);

		LOG.debug("end of  MapESAtoCMR ");
		return granule;

	}

	// ======================================================================================================
	// ======================================================================================================
	// ======================================================================================================
	// ===================================== GLOBAL METHODES :
	// ============================================
	// ======================================================================================================
	/**
	 * Create a boundary Gpolygon for CMR with Quadrangle Esa properties
	 * 
	 * @param quadrangle ESA quadrangle
	 * @return Boundary object for CMR
	 */
	public Boundary createBoundaryGpolygon(Quadrangle quadrangle) {
		LOG.debug("Start of  CreateBoundaryGpolygon Class");
		LOG.debug("Quadrangle to Boundary points");
		// get an array with Coordinate object witch have each Lat and Long
		// points
		LOG.debug("coordinates length test :  ");
		Coordinate[] coordinates = quadrangle.getGeometry().getCoordinates();
		LOG.debug("coordinates length : " + coordinates.length);
		Boundary boundary = new Boundary();

		// For all coordinates points :
		// Don't put the point twice, for a rectangle only 4 points for CMR
		//    *---------------*
		//    |               |
		//    |               |
		//    *_______________*
		for (int i = 0; i < coordinates.length-1; i++) {
			Coordinate coordinateP1 = coordinates[i];
			BigDecimal Lat = BigDecimal.valueOf(coordinateP1.y);
			BigDecimal Long = BigDecimal.valueOf(coordinateP1.x);
			Point point = new Point();
			point.setPointLatitude(Lat);
			point.setPointLongitude(Long);
			// add all points to the boundary pointsList
			boundary.getPoint().add(i, point);

		}

		LOG.debug("End of  CreateBoundaryGpolygon Class");
		return boundary;
	}

	/**
	 * Create a boundary Gpolygon for CMR with quadrangle height and width for
	 * SLC non georefenced data
	 * 
	 * @param quadrangle ESA quadrangle
	 * @return Boundary object for CMR
	 */
	public Boundary createHeightWidthGpolygon(Quadrangle quadrangle) {
		LOG.debug("Start of  createHeightWidthGpolygon ");
		Boundary boundary = new Boundary();

		BigDecimal height = BigDecimal.valueOf(quadrangle.getHeight());
		BigDecimal width = BigDecimal.valueOf(quadrangle.getWidth());
		Point point = new Point();
		point.setPointLatitude(height);
		point.setPointLongitude(width);
		boundary.getPoint().add(point);

		LOG.debug("End of  createHeightWidthGpolygon ");
		return boundary;
	}

	/**
	 * Convert a LocalDateTime (ESA) to a XMLGregorianCalendar (CMR)
	 * 
	 * @param date LocalDateTime (ESA)
	 * @return XMLGregorianCalendar (CMR)
	 * @throws DatatypeConfigurationException
	 */
	public XMLGregorianCalendar convertESADateToCMRDate(LocalDateTime date) {
		LOG.debug("Start of ConvertEsaDateToCmrDate");

		XMLGregorianCalendar out = null;
		try {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date date2 = new Date();
			date2 = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

			out = DatatypeFactory.newInstance().newXMLGregorianCalendar(
					format.format(date2));

		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
			LOG.error(e.toString());
		}
		LOG.debug("End of ConvertEsaDateToCmrDate");
		return out;
	}

}
