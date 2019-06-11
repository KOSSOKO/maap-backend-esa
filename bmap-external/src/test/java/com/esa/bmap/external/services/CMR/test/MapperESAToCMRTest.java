package com.esa.bmap.external.services.CMR.test;

import java.time.LocalDateTime;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.model.cmr.granules.Boundary;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.services.CMR.MapperESAToCMR;
import com.esa.bmap.model.Quadrangle;
import com.esa.bmap.model.QuadrangleType;
import com.esa.bmap.model.UtilsTest;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * 
 * @author THBLED
 *
 */
public class MapperESAToCMRTest {

	MapperESAToCMR map = new MapperESAToCMR();
	Granule granule = new Granule();

	@Test
	/**
	 * Test mapper AirbornData to Granule cmr
	 */
	public void testMapESAtoCMRGranuleAirborneDataString() throws BmapException {
			// instantiation of airborne data with attributes
			com.esa.bmap.model.Granule airbornData1 = UtilsTest.initData(UtilsTest.AIRBORNEDATA);
			airbornData1.setId((long) 1);
			com.esa.bmap.model.Granule airbornData2 = UtilsTest.initData(UtilsTest.AIRBORNEDATA);
			airbornData2.setId((long) 3);
			airbornData2.getCollection().setProcessingLevel("2");

			Granule granule1 = (Granule) map.mapESAAirbornDatatoCMR(granule, airbornData1);
			Granule granule2 = (Granule) map.mapESAAirbornDatatoCMR(granule, airbornData2);
			// test: equals
			Assert.assertEquals(granule1, granule2);
	}

	@Test
	/**
	 * Test map SARRawData to Granule
	 */
	public void testMapESAtoCMRGranuleSARRawDataString() throws BmapException {
			// instantiation of SAR raw data with attributes
			com.esa.bmap.model.Granule sARRawData1 = UtilsTest.initData(UtilsTest.SARRAWDATA);
			sARRawData1.setId((long) 1);
			sARRawData1.getDataList().get(0).setFileName("testfileName");
			sARRawData1.getDataList().get(0).setFileSize((float) 0.455);
			com.esa.bmap.model.Granule sARRawData2 = UtilsTest.initData(UtilsTest.SARRAWDATA);
			sARRawData2.setId((long) 2);

			Granule granule1 = (Granule) map.mapESASARtoCMR(granule, sARRawData1);
			Granule granule2 = (Granule) map.mapESASARtoCMR(granule, sARRawData2);
			// test: equals
			Assert.assertEquals(granule1, granule2);
	}

	@Test
	/**
	 * Test map ROI to Granule
	 */
	public void testMapESAtoCMRGranuleRegionOfInterestString() throws BmapException {
			// instantiation of region of interest with attributes
			com.esa.bmap.model.Granule regionOfInterest1 = UtilsTest.initData(UtilsTest.REGIONOFINTEREST);
			regionOfInterest1.setId((long) 1);
			LocalDateTime acquisitionDate3 = LocalDateTime.of(1988, 10, 10, 20, 00);
			regionOfInterest1.getDataList().get(0).setAcquisitionDate(acquisitionDate3);

			com.esa.bmap.model.Granule regionOfInterest2 = UtilsTest.initData(UtilsTest.REGIONOFINTEREST);
			regionOfInterest2.setId((long) 1);

			Granule granule1 = (Granule) map.mapESAROItoCMR(granule, regionOfInterest1);
			Granule granule2 = (Granule) map.mapESAROItoCMR(granule, regionOfInterest2);
			// test: equals
			Assert.assertEquals(granule1, granule2);
	}

	@Test
	/**
	 * Test the egality of two created Gpolygon from a quadrangle
	 */
	public void testCreateBoundaryGpolygon() {

		try {
			Quadrangle quadrangle = new Quadrangle(QuadrangleType.LATLONG, new Coordinate[] { new Coordinate(1.0, 3.0),
					new Coordinate(3.0, 3.0), new Coordinate(1.0, 6.0), new Coordinate(3.0, 6.0) }, 3, 3);
			Boundary boundary1 = (Boundary) map.createBoundaryGpolygon(quadrangle);
			Boundary boundary2 = (Boundary) map.createBoundaryGpolygon(quadrangle);
			Assert.assertEquals(boundary1, boundary2);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Test convert LocalDateTime TO XMLGregorianCalendar ESA to CMR
	 */
	public void testConvertESADateToCMRDate() {

		try {

			LocalDateTime acquisitionDate3 = LocalDateTime.of(2018, 11, 12, 12, 00);
			LocalDateTime uploadDate3 = LocalDateTime.of(2018, 11, 12, 12, 00);

			XMLGregorianCalendar date1 = (XMLGregorianCalendar) map.convertESADateToCMRDate(acquisitionDate3);
			XMLGregorianCalendar date2 = (XMLGregorianCalendar) map.convertESADateToCMRDate(uploadDate3);

			Assert.assertEquals(date1, date2);
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}

	}

}
