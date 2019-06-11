package com.esa.bmap.external.services.CMR.test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.postgresql.util.LruCache.CreateAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.BmapExternalTestContext;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.model.cmr.collections.Collection;
import com.esa.bmap.external.model.cmr.collections.OrbitParameters;
import com.esa.bmap.external.model.cmr.collections.Spatial;
import com.esa.bmap.external.model.cmr.granules.CollectionRef;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.services.CMR.CMRAdditionAttributes;
import com.esa.bmap.external.services.CMR.MapperCMRToESA;
import com.esa.bmap.external.services.CMR.MapperESAToCMR;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.util.GranuleHelper;

/**
 * 
 * @author THBLED
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { BmapExternalTestContext.class })
public class MapperCMRToESATest {

	private static final String COLLECTION_VERSION_ID = "1.0";
	private static final String COLLECTION_SHORT_NAME_BIOSAR1 = "biosar1";

	@Autowired
	private MapperCMRToESA mapCmrEsa;

	private MapperESAToCMR mapEsaCmr = new MapperESAToCMR();
	private UtilisGranuleTest granuleInit = new UtilisGranuleTest();

	@Test
	/**
	 * Test to map a granule to a SARRawData
	 * 
	 * @throws BmapException
	 */
	public void testMapCMRToESA() throws BmapException {
		boolean result = false;
		// initiate a granule
		Granule granule = new Granule();
		// initiate a SARRawData granule compliente
		granule = granuleInit.initGranulePolarisation(granule);

		Collection collection = addCollectionFields(granule);
		collection.setProcessingLevelId("L1");
		Spatial spatial = new Spatial();
		OrbitParameters orbitParameters = new OrbitParameters();
		orbitParameters.setNumberOfOrbits(BigDecimal.valueOf(8));
		spatial.setOrbitParameters(orbitParameters);
		collection.setSpatial(spatial);
		collection = granuleInit.initCollectionAdditionalAttributes(collection,
				CMRAdditionAttributes.ADDITIONAL_ATTRIBUTE_CATEGORY_KEY_WORDS,
				com.esa.bmap.model.Collection.COLLECTION_TYPE_SATELLITE);
		// map the granule to an Esa data object

		com.esa.bmap.model.Granule bmaapGranule = mapCmrEsa.mapCMRToESA(
				granule, collection);
		// airbornedata :

		if (GranuleHelper.isAirbornData(bmaapGranule,
				bmaapGranule.getCollection())) {
			result = false;
		} else if (GranuleHelper.isSARRawData(bmaapGranule,
				bmaapGranule.getCollection())) {
			// SARRawData :
			result = true;
		} else if (GranuleHelper.isRegionOfInterest(bmaapGranule)) {
			// RegionOfInterest :
			result = false;
		}

		Assert.assertTrue(result);

	}

	/**
	 * Map with additional attributes ( subregion )
	 * 
	 * @throws BmapException
	 */
	@SuppressWarnings("static-access")
	@Test
	public void additionalAttributes() throws BmapException {
		boolean result = false;
		// initiate a granule
		Granule granule = new Granule();

		// initiate a SARRawData granule compliente
		granule = granuleInit.initGranulePolarisation(granule);
		granule = granuleInit.initMiniMaxAvgStdDeviationAttributes(granule);
		Collection collection = addCollectionFields(granule);
		collection = granuleInit.initCollectionAdditionalAttributes(collection,
				CMRAdditionAttributes.ADDITIONAL_ATTRIBUTE_CATEGORY_KEY_WORDS,
				com.esa.bmap.model.Collection.COLLECTION_TYPE_GROUND_CAMPAIGN);
		// map the granule to a Esa data object

		com.esa.bmap.model.Granule bmaapGranule = mapCmrEsa.mapCMRToESA(
				granule, collection);

		if (GranuleHelper.isAirbornData(bmaapGranule, bmaapGranule.getCollection())) {
			result = true;

		} else if (GranuleHelper.isSARRawData(bmaapGranule,bmaapGranule.getCollection())) {
			// SARRawData :
			result = false;
		} else if (GranuleHelper.isRegionOfInterest(bmaapGranule)) {
			// RegionOfInterest :
			result = false;
		}

		List<Double> listOfDouble = granuleInit.ADDITIONAL_ATTRIBUTE_MIN_MAX_AVG_STDDEV_LIST_OF_STRNIG
				.stream().map(Double::parseDouble).collect(Collectors.toList());
		Assert.assertEquals(1, bmaapGranule.getDataList().size());
		Data data = bmaapGranule.getDataList().get(0);
		Assert.assertEquals(listOfDouble, data.getMins());
		Assert.assertEquals(listOfDouble, data.getMaxs());
		Assert.assertEquals(listOfDouble, data.getAvgs());
		Assert.assertEquals(listOfDouble, data.getStdDeviations());

		Assert.assertTrue(result);

	}

	/**
	 * Map with additional attributes ( subregion )
	 * 
	 * @throws BmapException
	 * @throws DatatypeConfigurationException
	 */
	@SuppressWarnings("static-access")
	@Test
	public void sceneAdditionalAttributes() throws BmapException,
			DatatypeConfigurationException {
		boolean result = false;
		// initiate a scne granule
		Granule scene = new Granule();
		scene.setGranuleUR(granuleInit.SCENE_GRANULE_UR_B111);
		granuleInit.initTimeData(scene);
		scene = granuleInit.initSceneAttributes(scene);

		// initiate a SARRawData granule compliente
		Collection collection = addCollectionFields(scene);
		collection = granuleInit.initCollectionAdditionalAttributes(collection,
				CMRAdditionAttributes.ADDITIONAL_ATTRIBUTE_CATEGORY_KEY_WORDS,
				com.esa.bmap.model.Collection.COLLECTION_TYPE_GROUND_CAMPAIGN);
		// map the granule to a Esa data object

		com.esa.bmap.model.Granule bmaapSceneGranule = mapCmrEsa.mapCMRToESA(
				scene, collection);

		Assert.assertEquals(
				granuleInit.ADDITIONAL_ATTRIBUTE_DEM_GRANULE_REF_REF,
				bmaapSceneGranule.getDem());
		Assert.assertEquals(
				granuleInit.ADDITIONAL_ATTRIBUTE_MASTER_GRANULE_REF_N_A,
				bmaapSceneGranule.getMaster());
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF_BIOSAR1_412_INC_TIFF));
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF_BIOSAR1_412_AZ_TIFF));
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF_BIOSAR1_412_KZ_TIFF));
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF_BIOSAR1_412_RG_TIFF));
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF_BIOSAR1_412_SLC_HH_TIFF));
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF_BIOSAR1_412_SLC_HV_TIFF));
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF_BIOSAR1_412_SLC_VH_TIFF));
		Assert.assertTrue(bmaapSceneGranule
				.getGranuleList()
				.contains(
						granuleInit.ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF_BIOSAR1_412_SLC_VV_TIFF));

	}

	private Collection addCollectionFields(Granule scene) {
		Collection collection = new Collection();
		collection.setShortName(COLLECTION_SHORT_NAME_BIOSAR1);
		collection.setVersionId(COLLECTION_VERSION_ID);
		collection.setDataSetId(COLLECTION_SHORT_NAME_BIOSAR1);
		CollectionRef collectionRef = new CollectionRef();
		collectionRef.setShortName(collection.getShortName());
		collectionRef.setVersionId(collection.getVersionId());
		collectionRef.setDataSetId(collection.getDataSetId());
		scene.setCollection(collectionRef);

		return collection;
	}

	@Test
	/**
	 * Test the mapping Granule to SarRawData class
	 */
	public void testMapToSARRawData() throws BmapException {
		Granule granule = new Granule();
		granule = granuleInit.initGranulePolarisation(granule);
		Collection collection = addCollectionFields(granule);
		collection = granuleInit.initCollectionAdditionalAttributes(collection,
				CMRAdditionAttributes.ADDITIONAL_ATTRIBUTE_CATEGORY_KEY_WORDS,
				com.esa.bmap.model.Collection.COLLECTION_TYPE_SATELLITE);
		// map data object to granule object
		Granule cmrGranule2 = new Granule();
		com.esa.bmap.model.Granule bmaapGranule = mapCmrEsa.mapCMRToESA(
				granule, collection);

		// SARRawData :
		if (GranuleHelper.isSARRawData(bmaapGranule,
				bmaapGranule.getCollection())) {
			cmrGranule2 = mapEsaCmr.mapESASARtoCMR(granule, bmaapGranule);
		}

		Assert.assertEquals(granule, cmrGranule2);

	}

	@Test
	/**
	 * Test the conversion date methode CMR (XMLGregorianCalendar) to Esa date type
	 * (LocalDateTime) format : "yyyy-MM-dd'T'HH:mm:ss"
	 * 
	 */
	public void testConvertCMRDateToESADate() {

		try {
			// Create a well formated CMR date
			String FORMATER = "yyyy-MM-dd'T'HH:mm:ss";
			DateFormat format = new SimpleDateFormat(FORMATER);
			Date date = new Date();
			XMLGregorianCalendar gDateFormatted = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(format.format(date));
			// Convert Cmr date to ESA date :
			LocalDateTime EsaDate = mapCmrEsa
					.convertCMRDateToESADate(gDateFormatted);
			// Convert ESA date to CMR date :
			XMLGregorianCalendar date2 = mapEsaCmr
					.convertESADateToCMRDate(EsaDate);
			Assert.assertEquals(gDateFormatted, date2);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

	}
}
