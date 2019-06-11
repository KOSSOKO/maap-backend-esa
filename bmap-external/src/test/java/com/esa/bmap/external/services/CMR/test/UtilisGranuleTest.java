package com.esa.bmap.external.services.CMR.test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.model.cmr.collections.Collection;
import com.esa.bmap.external.model.cmr.collections.ListOfAdditionalAttributes;
import com.esa.bmap.external.model.cmr.granules.AdditionalAttributeRef;
import com.esa.bmap.external.model.cmr.granules.Boundary;
import com.esa.bmap.external.model.cmr.granules.CollectionRef;
import com.esa.bmap.external.model.cmr.granules.DataGranule;
import com.esa.bmap.external.model.cmr.granules.DayNightFlag;
import com.esa.bmap.external.model.cmr.granules.GPolygon;
import com.esa.bmap.external.model.cmr.granules.Geometry;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.model.cmr.granules.HorizontalSpatialDomain;
import com.esa.bmap.external.model.cmr.granules.ListOfAdditionalAttributeRefs;
import com.esa.bmap.external.model.cmr.granules.ListOfAdditionalAttributeValues;
import com.esa.bmap.external.model.cmr.granules.ListOfOnlineAccessURLs;
import com.esa.bmap.external.model.cmr.granules.OnlineAccessURL;
import com.esa.bmap.external.model.cmr.granules.Point;
import com.esa.bmap.external.model.cmr.granules.RangeDateTime;
import com.esa.bmap.external.model.cmr.granules.Spatial;
import com.esa.bmap.external.model.cmr.granules.Temporal;
import com.esa.bmap.external.services.CMR.MapperCMRToESA;
import com.esa.bmap.external.services.CMR.MapperESAToCMR;
import com.esa.bmap.model.UtilsTest;

/**
 * 
 * @author THBLED
 *
 */
public class UtilisGranuleTest {

	private static final String HTTPS_BMAP_CATALOGUE_DATA_TIFF = "https://bmap-catalogue-data.oss.eu-west-0.prod-cloud-ocb.orange-business.com/Campaign_data/biosar1/biosar1_412_SLC_HV.tiff";

	public static final List<String> ADDITIONAL_ATTRIBUTE_MIN_MAX_AVG_STDDEV_LIST_OF_STRNIG = Arrays.asList("1", "2", "3");
	
	public static final String SCENE_GRANULE_UR_B111 = "B111";
	
	public static final String ADDITIONAL_ATTRIBUTE_MASTER_GRANULE_REF_N_A = "n/a";

	public static final String ADDITIONAL_ATTRIBUTE_DEM_GRANULE_REF_REF = "ref";

	public static final String ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF_BIOSAR1_412_SLC_VH_TIFF = "biosar1_412_SLC_VH.tiff";

	public static final String ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF_BIOSAR1_412_SLC_VV_TIFF = "biosar1_412_SLC_VV.tiff";

	public static final String ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF_BIOSAR1_412_SLC_HV_TIFF = "biosar1_412_SLC_HV.tiff";

	public static final String ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF_BIOSAR1_412_SLC_HH_TIFF = "biosar1_412_SLC_HH.tiff";
	
	public static final String ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF_BIOSAR1_412_AZ_TIFF = "biosar1_412_az.tiff";

	public static final String ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF_BIOSAR1_412_KZ_TIFF = "biosar1_412_kz.tiff";

	public static final String ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF_BIOSAR1_412_INC_TIFF = "biosar1_412_inc.tiff";

	public static final String ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF_BIOSAR1_412_RG_TIFF = "biosar1_412_rg.tiff";

	public static final String ADDITIONAL_ATTRIBUTE_SCENE_Z_FLIGHT_4004_58 = "4004.58";

	public static final String ADDITIONAL_ATTRIBUTE_SCENE_Z_FIELD_120_0 = "120.0";

	public static final String ADDITIONAL_ATTRIBUTE_SCENE_SURFACE_RESOL_NA_N = "NaN";

	public static final String ADDITIONAL_ATTRIBUTE_SCENE_SLR_START_4287_32 = "4287.32";

	public static final String ADDITIONAL_ATTRIBUTE_SCENE_PIXEL_SPACING_1_49854 = "1.49854";

	public static final String ADDITIONAL_ATTRIBUTE_SCENE_HEADING_178_0 = "178.0";

	public static final String ADDITIONAL_ATTRIBUTE_SCENE_GRD_RESOL_2_0 = "2.0";

	public static final Logger LOG = LoggerFactory.getLogger(UtilsTest.class);
	
	MapperESAToCMR mapper = new MapperESAToCMR();

	/**
	 * Initialise a Granule for Unit test with an additional attribute SubRegion
	 * 
	 * @return a Granule object with the scene attribute
	 * @throws BmapException
	 */
	public Granule initSceneAttributes(Granule granule) throws BmapException {
		LOG.debug("Start of  initSceneAttributes Class");
		
		ListOfAdditionalAttributeRefs atts = granule.getAdditionalAttributes();
		if (atts == null) {
			atts = new ListOfAdditionalAttributeRefs();
			granule.setAdditionalAttributes(atts);
		}


		AdditionalAttributeRef attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_GRD_RESOL, ADDITIONAL_ATTRIBUTE_SCENE_GRD_RESOL_2_0);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);

		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_HEADING, ADDITIONAL_ATTRIBUTE_SCENE_HEADING_178_0);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
	
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_PIXEL_SPACING, ADDITIONAL_ATTRIBUTE_SCENE_PIXEL_SPACING_1_49854);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_SLR_START, ADDITIONAL_ATTRIBUTE_SCENE_SLR_START_4287_32);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_SURFACE_RESOL, ADDITIONAL_ATTRIBUTE_SCENE_SURFACE_RESOL_NA_N);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_Z_FIELD, ADDITIONAL_ATTRIBUTE_SCENE_Z_FIELD_120_0);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_Z_FLIGHT, ADDITIONAL_ATTRIBUTE_SCENE_Z_FLIGHT_4004_58);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
	
		
		//Ref on others granules
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF, ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF_BIOSAR1_412_AZ_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF, ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF_BIOSAR1_412_KZ_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF, ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF_BIOSAR1_412_RG_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
	
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF, ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF_BIOSAR1_412_INC_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF, ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF_BIOSAR1_412_SLC_HH_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF, ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF_BIOSAR1_412_SLC_HV_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF, ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF_BIOSAR1_412_SLC_VV_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF, ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF_BIOSAR1_412_SLC_VH_TIFF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
	
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_DEM_GRANULE_REF, ADDITIONAL_ATTRIBUTE_DEM_GRANULE_REF_REF);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
	
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_MASTER_GRANULE_REF, ADDITIONAL_ATTRIBUTE_MASTER_GRANULE_REF_N_A);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);			
		
		return granule;

	}

	/**
	 * Initialise a Granule for Unit test with an additional attribute SubRegion
	 * 
	 * @return a Granule object with the scene attribute
	 * @throws BmapException
	 */
	public Granule initMiniMaxAvgStdDeviationAttributes(Granule granule)
			throws BmapException {
		LOG.debug("Start of  initMiniMaxAvgStdDeviationAttributes Class");
		MapperESAToCMR mapper = new MapperESAToCMR();

		ListOfAdditionalAttributeRefs atts = granule.getAdditionalAttributes();
		if (atts == null) {
			atts = new ListOfAdditionalAttributeRefs();
			granule.setAdditionalAttributes(atts);
		}
		

		AdditionalAttributeRef attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_BANDS_MIN, ADDITIONAL_ATTRIBUTE_MIN_MAX_AVG_STDDEV_LIST_OF_STRNIG);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);

		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_BANDS_MAX, ADDITIONAL_ATTRIBUTE_MIN_MAX_AVG_STDDEV_LIST_OF_STRNIG);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_BANDS_AVERAGE, ADDITIONAL_ATTRIBUTE_MIN_MAX_AVG_STDDEV_LIST_OF_STRNIG);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		
		
		attRef = mapper.createAdditionalAttribute(
				MapperESAToCMR.ADDITIONAL_ATTRIBUTE_BANDS_STANDARD_DEVIATION, ADDITIONAL_ATTRIBUTE_MIN_MAX_AVG_STDDEV_LIST_OF_STRNIG);
		granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
		return granule;

	}

	/**
	 * Initialise a Granule for Unit test with an additional attribute SubRegion
	 * 
	 * @return a Granule object with a subregion field
	 * @throws BmapException
	 */
	public Granule initGranuleSubRegion(Granule granule) throws BmapException {
		LOG.debug("Start of  initGranuleSubRegion Class");

		try {


			// INITIALISE A GRANULE ITEM
			granule.setGranuleUR("GranuleURtest");
			
			XMLGregorianCalendar date2 = initTimeData(granule);
			

			DataGranule dataGranule = new DataGranule();
			dataGranule.setSizeMBDataGranule(92.3434);
			dataGranule.setDayNightFlag(DayNightFlag.fromValue("BOTH"));
			granule.setDataGranule(dataGranule);
			dataGranule.setProductionDateTime(date2);

			// collections references :
			CollectionRef collectionRef = new CollectionRef();
			collectionRef.setShortName("CollectionShortName");
			collectionRef.setVersionId("1");
			granule.setCollection(collectionRef);

			// temporal properties :
			Temporal temporal = new Temporal();
			RangeDateTime rangeDateTime = new RangeDateTime();
			rangeDateTime.setBeginningDateTime(date2);
			rangeDateTime.setEndingDateTime(date2);
			temporal.setRangeDateTime(rangeDateTime);

			// spatial properties :
			Spatial spatial = new Spatial();
			HorizontalSpatialDomain horizontalSpatialDomain = new HorizontalSpatialDomain();
			Geometry geometry = new Geometry();
			GPolygon gPolygon = new GPolygon();
			Boundary boundary = new Boundary();
			BigDecimal Lat = BigDecimal.valueOf(0.1223);
			BigDecimal Long = BigDecimal.valueOf(0.67223);
			Point point = new Point();
			Point point2 = new Point();
			point2.setPointLatitude(BigDecimal.valueOf(0.34323));
			point2.setPointLongitude(BigDecimal.valueOf(2.123));
			Point point3 = new Point();
			point3.setPointLatitude(BigDecimal.valueOf(1.223));
			point3.setPointLongitude(BigDecimal.valueOf(2.12323));
			Point point4 = new Point();
			point4.setPointLatitude(BigDecimal.valueOf(4.34323));
			point4.setPointLongitude(BigDecimal.valueOf(12.123));
			point.setPointLatitude(Lat);
			point.setPointLongitude(Long);
			boundary.getPoint().add(point);
			boundary.getPoint().add(point2);
			boundary.getPoint().add(point3);
			boundary.getPoint().add(point4);
			gPolygon.setBoundary(boundary);
			// add the polygon to the geometry
			geometry.getPointOrBoundingRectangleOrGPolygon().add(gPolygon);

			horizontalSpatialDomain.setGeometry(geometry);
			spatial.setHorizontalSpatialDomain(horizontalSpatialDomain);

			// Additional attributes :
			ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs = new ListOfAdditionalAttributeRefs();
			AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();

			ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
			listOfAdditionalAttributeValues2.getValue().add("subRegionValue");
			additionalAttributeRef2.setName("Site Name");
			additionalAttributeRef2.setValues(listOfAdditionalAttributeValues2);
			listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
					additionalAttributeRef2);

			// ------------ DATA URL
			OnlineAccessURL onlineAccessURL = new OnlineAccessURL();
			onlineAccessURL.setURL("https://host/parent1/"+granule.getGranuleUR());
			onlineAccessURL.setMimeType("MimeType");
			onlineAccessURL
					.setURLDescription("This link provides direct download access to the granule.");
			ListOfOnlineAccessURLs listOfOnlineAccessURLs = new ListOfOnlineAccessURLs();
			listOfOnlineAccessURLs.getOnlineAccessURL().add(onlineAccessURL);

			// attach all properties to the granule :
			granule.setTemporal(temporal);
			granule.setSpatial(spatial);
			granule.setAdditionalAttributes(listOfAdditionalAttributeRefs);
			granule.setOnlineAccessURLs(listOfOnlineAccessURLs);

		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}

		LOG.debug("End of  initGranuleSubRegion Class");
		return granule;

	}

	XMLGregorianCalendar initTimeData(Granule granule)
			throws DatatypeConfigurationException {
		XMLGregorianCalendar date2;
		// initialise a reference date for initialise a granule :
		date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(2018,
				12, 10, 23, 12, 10, 23, 120);
		granule.setInsertTime(date2);
		granule.setLastUpdate(date2);
		return date2;
	}

	/**
	 * * Initialise a Granule for Unit test with an additional attribute
	 * SubRegion
	 * 
	 * @return a granule object with all polarisation
	 * @throws BmapException
	 */
	public Granule initGranulePolarisation(Granule granule) {
		LOG.debug("Start of  initGranulePolarisation Class");
		granule = new Granule();
		XMLGregorianCalendar date2;
		try {
			// initialise a reference date for initialise a granule :

			date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(2018,
					12, 10, 10, 12, 10, 23, 120);

			// INITIALISE A GRANULE ITEM
			granule.setGranuleUR("GranuleURtest");
			granule.setInsertTime(date2);
			granule.setLastUpdate(date2);

			DataGranule dataGranule = new DataGranule();
			dataGranule.setSizeMBDataGranule(92.3434);
			dataGranule.setDayNightFlag(DayNightFlag.fromValue("BOTH"));
			granule.setDataGranule(dataGranule);
			dataGranule.setProductionDateTime(date2);

			// collections references :
			CollectionRef collectionRef = new CollectionRef();
			collectionRef.setShortName("CollectionShortName");
			collectionRef.setVersionId("1");
			granule.setCollection(collectionRef);

			// temporal properties :
			Temporal temporal = new Temporal();
			RangeDateTime rangeDateTime = new RangeDateTime();
			rangeDateTime.setBeginningDateTime(date2);
			rangeDateTime.setEndingDateTime(date2);
			temporal.setRangeDateTime(rangeDateTime);

			// spatial properties :
			Spatial spatial = new Spatial();
			HorizontalSpatialDomain horizontalSpatialDomain = new HorizontalSpatialDomain();
			Geometry geometry = new Geometry();
			GPolygon gPolygon = new GPolygon();

			Boundary boundary = new Boundary();
			BigDecimal Lat = BigDecimal.valueOf(0.1223);
			BigDecimal Long = BigDecimal.valueOf(0.67223);
			Point point = new Point();
			Point point2 = new Point();
			point2.setPointLatitude(BigDecimal.valueOf(0.34323));
			point2.setPointLongitude(BigDecimal.valueOf(2.123));
			Point point3 = new Point();
			point3.setPointLatitude(BigDecimal.valueOf(1.223));
			point3.setPointLongitude(BigDecimal.valueOf(2.12323));
			Point point4 = new Point();
			point4.setPointLatitude(BigDecimal.valueOf(4.34323));
			point4.setPointLongitude(BigDecimal.valueOf(12.123));
			point.setPointLatitude(Lat);
			point.setPointLongitude(Long);
			boundary.getPoint().add(point);
			boundary.getPoint().add(point2);
			boundary.getPoint().add(point3);
			boundary.getPoint().add(point4);
			gPolygon.setBoundary(boundary);

			// add the polygon to the geometry
			geometry.getPointOrBoundingRectangleOrGPolygon().add(gPolygon);

			horizontalSpatialDomain.setGeometry(geometry);
			spatial.setHorizontalSpatialDomain(horizontalSpatialDomain);

			// Additional attributes :
			ListOfAdditionalAttributeRefs listOfAdditionalAttributeRefs = new ListOfAdditionalAttributeRefs();

			AdditionalAttributeRef additionalAttributeRef2 = new AdditionalAttributeRef();
			ListOfAdditionalAttributeValues listOfAdditionalAttributeValues2 = new ListOfAdditionalAttributeValues();
			listOfAdditionalAttributeValues2.getValue().add("HH");
			listOfAdditionalAttributeValues2.getValue().add("HV");
			listOfAdditionalAttributeValues2.getValue().add("VV");
			listOfAdditionalAttributeValues2.getValue().add("VH");
			additionalAttributeRef2.setName("Polarisation");
			additionalAttributeRef2.setValues(listOfAdditionalAttributeValues2);

			listOfAdditionalAttributeRefs.getAdditionalAttribute().add(
					additionalAttributeRef2);

			// ------------ DATA URL
			OnlineAccessURL onlineAccessURL = new OnlineAccessURL();
			onlineAccessURL.setURL(HTTPS_BMAP_CATALOGUE_DATA_TIFF);
			onlineAccessURL.setMimeType("MimeType");
			onlineAccessURL
					.setURLDescription("This link provides direct download access to the granule.");
			ListOfOnlineAccessURLs listOfOnlineAccessURLs = new ListOfOnlineAccessURLs();
			listOfOnlineAccessURLs.getOnlineAccessURL().add(0, onlineAccessURL);

			// attach all properties to the granule :
			granule.setTemporal(temporal);
			granule.setSpatial(spatial);
			granule.setAdditionalAttributes(listOfAdditionalAttributeRefs);
			granule.setOnlineAccessURLs(listOfOnlineAccessURLs);
			
			AdditionalAttributeRef attRef = mapper.createAdditionalAttribute(
					MapperESAToCMR.ADDITIONAL_ATTRIBUTE_SCENE_NAME, "B111.xml");
			granule.getAdditionalAttributes().getAdditionalAttribute().add(attRef);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOG.debug("End of  initGranulePolarisation Class");
		return granule;

	}
	
	/**
	 * Add an additional attribute to the specified collection
	 * @param collection the CMR collection 
	 * @param key the key to put in the additional attributes
	 * @param value the value to put for the key
	 * @return
	 */
	public Collection initCollectionAdditionalAttributes(Collection collection, String key, String value){
		
		ListOfAdditionalAttributes atts = collection.getAdditionalAttributes();
		if (atts == null) {
			atts = new ListOfAdditionalAttributes();
			collection.setAdditionalAttributes(atts);
		}
		
		collection.getAdditionalAttributes().getAdditionalAttribute().add(mapper.createCollectionAdditionalAttribute(key, value));
		
		return collection;
		
	}
	
}
