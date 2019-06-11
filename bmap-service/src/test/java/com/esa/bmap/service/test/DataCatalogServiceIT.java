//package com.esa.bmap.service.test;
//
//import java.util.Collection;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.esa.bmap.BmapServiceTestContext;
//import com.esa.bmap.model.Granule;
//
//import com.esa.bmap.model.Data;
//import com.esa.bmap.model.GranuleCriteria;
//
//import com.esa.bmap.model.SubRegion;
//import com.esa.bmap.model.UtilsTest;
//import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
//
///**
// * DataCatalogueServiceTest
// * 
// * @author QFAURE
// *
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes = { BmapServiceTestContext.class })
//public class DataCatalogServiceIT {
//
//	@Autowired
//	private DataCatalogServiceInterface dataCatalogueService;
//
//	private Logger log = LoggerFactory.getLogger(DataCatalogServiceIT.class);
//
//	/**
//	 * Tests save + get by id for airborne data.
//	 */
//	@Test
//	public void testAirborneData() {
//
//		try {
//
//			// instantiation of airborne data with attributes
//			Granule airbornData1 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//
//			// test: save airborne data to database
//			Granule airbornData2 = (Granule) this.dataCatalogueService.addGranule(airbornData1);
//
//			// test: get airborne data id from object and get saved airborne data from database with id
//			Assert.assertNotNull(this.dataCatalogueService.getDataById(airbornData2.getId()));
//
//			this.log.info(airbornData1.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	/**
//	 * Tests save + get by id for biomass official product.
//	 */
//	@Test
//	public void testBioMassOfficialProduct() {
//
//		try {
//
//			// instantiation of biomass official product with attributes
//			BiomassOfficialProduct biomassOfficialProduct1 = (BiomassOfficialProduct) UtilsTest.initData(UtilsTest.BIOMASSOFFICIALPRODUCT);
//
//			// test: save biomass official product to database
//			BiomassOfficialProduct biomassOfficialProduct2 = (BiomassOfficialProduct) this.dataCatalogueService.addData(biomassOfficialProduct1);
//
//			// test: get biomass official product id from object and get saved biomass official product from database with id
//			Assert.assertNotNull(this.dataCatalogueService.getDataById(biomassOfficialProduct2.getId()));
//
//			this.log.info(biomassOfficialProduct1.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	/**
//	 * Tests save + get by id for region of interest.
//	 */
//	@Test
//	public void testRegionOfInterest() {
//
//		try {
//
//			// instantiation of region of interest with attributes
//			RegionOfInterest regionOfInterest1 = (RegionOfInterest) UtilsTest.initData(UtilsTest.REGIONOFINTEREST);
//
//			// test: save region of interest to database
//			RegionOfInterest regionOfInterest2 = (RegionOfInterest) this.dataCatalogueService.addData(regionOfInterest1);
//
//			// test: get region of interest id from object and get saved region of interest from database with id
//			Assert.assertNotNull(this.dataCatalogueService.getDataById(regionOfInterest2.getId()));
//
//			this.log.info(regionOfInterest1.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	/**
//	 * Tests save + get by id for SAR raw data.
//	 */
//	@Test
//	public void testSARRawData() {
//
//		try {
//
//			// instantiation of SAR raw data with attributes
//			SARRawData sARRawData1 = (SARRawData) UtilsTest.initData(UtilsTest.SARRAWDATA);
//
//			// test: save SAR raw data to database
//			SARRawData sARRawData2 = (SARRawData) this.dataCatalogueService.addData(sARRawData1);
//
//			// test: get SAR raw data id from object and get saved SAR raw data from database with id
//			Assert.assertNotNull(this.dataCatalogueService.getDataById(sARRawData2.getId()));
//
//			this.log.info(sARRawData1.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	/**
//	 * Tests save + get by id ground campaign.
//	 */
//	@Test
//	public void testAddGroundCampaign() {
//
//		try {
//
//			// instantiation of ground campaign
//			GroundCampaign groundCampaign1 = new GroundCampaign("ground campaign name");
//
//			// test: save ground campaign to database
//			GroundCampaign groundCampaign2 = this.dataCatalogueService.addGroundCampaign(groundCampaign1);
//
//			// test: get ground campaign id from object and get saved ground campaign from database with id
//			Assert.assertNotNull(this.dataCatalogueService.getGroundCampaignById(groundCampaign2.getId()));
//
//			this.log.info(groundCampaign1.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	/**
//	 * Tests save + get by id for sub-region.
//	 */
//	@Test
//	public void testAddSubRegion() {
//
//		try {
//
//			// instantiation of sub-region
//			SubRegion subRegion1 = new SubRegion("sub-region name");
//
//			// test: save sub-region to database
//			SubRegion subRegion2 = this.dataCatalogueService.addSubRegion(subRegion1);
//
//			// test: get sub-region id from object and get saved sub-region from database with id
//			Assert.assertNotNull(this.dataCatalogueService.getSubRegionById(subRegion2.getId()));
//
//			this.log.info(subRegion1.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	/**
//	 * Tests to ensure child objects already persisted do not get duplicated in the database.
//	 */
//	@Test
//	public void testDuplicateChildObjects() {
//
//		for (int i = 0; i < 10; i++) {
//			testAirborneData();
//			testBioMassOfficialProduct();
//			testRegionOfInterest();
//			testSARRawData();
//		}
//	}
//
//	/**
//	 * Tests get data by criteria for all data types.
//	 */
//	@Test
//	public void testGetDataByCriteria() {
//
//		try {
//
//			// instantiation of data with attributes
//			Granule airbornData = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			BiomassOfficialProduct biomassOfficialProduct = (BiomassOfficialProduct) UtilsTest.initData(UtilsTest.BIOMASSOFFICIALPRODUCT);
//			RegionOfInterest regionOfInterest = (RegionOfInterest) UtilsTest.initData(UtilsTest.REGIONOFINTEREST);
//			SARRawData sARRawData = (SARRawData) UtilsTest.initData(UtilsTest.SARRAWDATA);
//
//			// save data to database
//			airbornData = (Granule) this.dataCatalogueService.addData(airbornData);
//			biomassOfficialProduct = (BiomassOfficialProduct) this.dataCatalogueService.addData(biomassOfficialProduct);
//			regionOfInterest = (RegionOfInterest) this.dataCatalogueService.addData(regionOfInterest);
//			sARRawData = (SARRawData) this.dataCatalogueService.addData(sARRawData);
//
//			// instantiation of data criteria with attributes
//			GranuleCriteria granuleCriteria = UtilsTest.initGranuleCriteria();
//
//			// test: get the data by criteria from the database
//			Collection<Data> collection = this.dataCatalogueService.getDataByCriteria(granuleCriteria);
//			Assert.assertNotNull(collection);
//
//			// test: assert the number of returned data
//			// Assert.assertEquals(4, collection.size());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//}
