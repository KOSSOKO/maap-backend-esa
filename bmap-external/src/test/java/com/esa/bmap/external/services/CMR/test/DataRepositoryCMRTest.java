package com.esa.bmap.external.services.CMR.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.BmapExternalTestContext;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.services.CMR.DataRepositoryCMR;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.UtilsTest;

/**
 * 
 * @author THBLED
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { BmapExternalTestContext.class })
public class DataRepositoryCMRTest {

	@Autowired
	DataRepositoryCMR dataRepo; 

	@Test
	/**
	 * Test to get collections object from CMR with it shortname
	 */
	public void testgetCollection() {
		String collectionShortName = "Polarimetric_CT_1601";
		com.esa.bmap.external.model.cmr.collections.Collection collection = new com.esa.bmap.external.model.cmr.collections.Collection();
		try {
			collection = dataRepo.getCollectionCMR(collectionShortName);
			assertEquals(collectionShortName, collection.getShortName());
		} catch (BmapException e) {

			e.printStackTrace();
		}

	}

	@Test
	/**
	 * Find granules from a collections with collection name :
	 * AfriSAR_Mondah_Field_Data_1580
	 * 
	 * BY COLLECTION NAME
	 * 
	 * @throws BmapException
	 */
	public void testGetGranuleXMLWithCollectionName() throws BmapException {
		GranuleCriteria datacriteria = new GranuleCriteria();

		List<String> collectionNames = new ArrayList<>();

		collectionNames.add("AfriSAR_Mondah_Field_Data_1580");

		datacriteria.setCollectionNames(collectionNames);
		dataRepo.findByCriteria(datacriteria);
	}
//
//	@Test
//	/**
//	 * Find granules from a collections with collection name and date:
//	 * Polarimetric_CT_1601
//	 * 
//	 * BY DATE RANGE
//	 * 
//	 * @throws BmapException
//	 */
//	public void testGetGranuleXMLWithCollectionNameAndDate() throws BmapException {
//		GranuleCriteria granuleCriteria = new GranuleCriteria();
//		List<String> collectionNames = new ArrayList<>();
//
//		collectionNames.add("Polarimetric_CT_1601");
//		LocalDate startDate = LocalDate.of(2000, 01, 01);
//		LocalDate endDate = LocalDate.of(2999, 01, 01);
//		granuleCriteria.setEndDate(endDate);
//		granuleCriteria.setStartDate(startDate);
//
//		granuleCriteria.setCollectionNames(collectionNames);
//		dataRepo.findByCriteria(granuleCriteria);
//
//	}

	@Test
	/**
	 * Find granules from a collections with some criteria
	 * 
	 * @throws BmapException
	 */
	public void testAfrisarNASAGranulesFindByCriteria() throws BmapException {

		GranuleCriteria datacriteria = new GranuleCriteria();
		List<String> collectionNames = new ArrayList<>();
		collectionNames.add("AfriSAR_Mondah_Field_Data_1580");
		datacriteria.setCollectionNames(collectionNames);
		Collection<com.esa.bmap.model.Granule> dataCollection = new ArrayList<>();
		dataCollection = dataRepo.findByCriteria(datacriteria);

		try {
			// test if dataCollection is not empty
			Assert.assertTrue(!dataCollection.isEmpty());

		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Find granules from a collections with some criteria
	 * 
	 * @throws BmapException
	 */
	public void testGranulesFindByCriteria() throws BmapException {

		GranuleCriteria datacriteria = new GranuleCriteria();
		List<String> collectionNames = new ArrayList<>();
		collectionNames.add("AfriSAR_Mondah_Field_Data_1580");
		datacriteria.setCollectionNames(collectionNames);
		// datacriteria.setLocation(location);
		Collection<com.esa.bmap.model.Granule> dataCollection = new ArrayList<>();
		dataCollection = dataRepo.findByCriteria(datacriteria);
		try {
			// test if dataCollection is not empty
			Assert.assertNotNull(dataCollection.toArray()[0]);

		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Test ingestion of an AirbornData in the CMR Catalogue
	 */
	public void testAirbornData() {
		boolean result = false;

		try {
			// AirbornData
			com.esa.bmap.model.Granule airbornData1 =  UtilsTest.initData(UtilsTest.AIRBORNEDATA);
			airbornData1.setId((long) 1);
			airbornData1.getDataList().get(0).setFileName("AirbornData1");
			dataRepo.save(airbornData1);
			result=true;
			Assert.assertTrue(result);
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
		
	}

//	@Test
//	public void testDeleteAllGranuleInCollection() {
//		boolean result = false;
//		String collectionShortName = "Collection";
//		try {
//			dataRepo.deleteAllGranuleInCollection(collectionShortName);
//			 result = true;
//		} catch (BmapException e1) {
//			e1.printStackTrace();
//		}
//		try {
//
//			Assert.assertTrue(result);
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}

	
//
//	@Test
//	/**
//	 * Test the downloadTheDataFromS3 function 
//	 * should be ok after a download
//	 */
//	public void testDownloadTheDataFromS3() {
//		boolean result = false;
//		String TargetUrl = "https://oss.eu-west-0.prod-cloud-ocb.orange-business.com/bmap-catalogue-data/Campaign_data/biosar1/biosar1_param.xml";
//		String Filename = "biosar1_param.xml";
//		try {
//			dataRepo.downloadTheDataFromS3(TargetUrl, Filename);
//			result=true;
//		} catch (BmapException e1) {
//			e1.printStackTrace();
//		}
//		try {
//			Assert.assertTrue(result);
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//
//	}

}
