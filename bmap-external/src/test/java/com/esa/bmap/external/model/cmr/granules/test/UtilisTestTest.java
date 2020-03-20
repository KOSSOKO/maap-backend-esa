package com.esa.bmap.external.model.cmr.granules.test;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.model.cmr.granules.CollectionRef;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.services.CMR.test.UtilisGranuleTest;
/**
 * 
 * @author THBLED
 *
 */
public class UtilisTestTest {

	UtilisGranuleTest granuleInit = new UtilisGranuleTest();

	@Test
	/**
	 * test initialisation of a granule who contains an additional attributes fields subRegion
	 * 
	 * 
	 * @throws BmapException
	 */
	public void testInitGranuleSubRegion() throws BmapException {

		try {
			Granule granule1 = new Granule();
			Granule granule2 = new Granule();
			granule1 = granuleInit.initGranuleSubRegion(granule1);
			granule2 = granuleInit.initGranuleSubRegion(granule2);
			assertEquals(granule1, granule2);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * test initialisation of a granule who contains an additional attributes fields
	 * subRegion
	 * 
	 * 
	 * @throws BmapException
	 */
	public void testInitGranuleSubRegionNotEgal() {

		try {
			Granule granule1 = new Granule();
			Granule granule2 = new Granule();
			granule1 = granuleInit.initGranuleSubRegion(granule1);
			granule1.setGranuleUR("granule not egal");
			granule2 = granuleInit.initGranuleSubRegion(granule2);
			Assert.assertNotEquals(granule1, granule2);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Test initialisation of a granule with polarisation parameter
	 */
	public void testInitGranulePolarisation() {
		try {
			Granule granule = new Granule();
			Granule granule1 = (Granule) granuleInit.initGranulePolarisation(granule);
			Granule granule2 = (Granule) granuleInit.initGranulePolarisation(granule);
			Assert.assertEquals(granule1, granule2);

		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Test the initialisation of two granule 
	 */
	public void testInitGranulePolarisationNotEgal() {
		try {
			Granule granule = new Granule();
			Granule granule1 = (Granule) granuleInit.initGranulePolarisation(granule);
			Granule granule2 = (Granule) granuleInit.initGranulePolarisation(granule);
			CollectionRef collectionRef = new CollectionRef();
			collectionRef.setDataSetId("2");
			collectionRef.setShortName("testSh");
			collectionRef.setVersionId("2");
			granule1.setCollection(collectionRef);

			Assert.assertNotEquals(granule1, granule2);

		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

}
