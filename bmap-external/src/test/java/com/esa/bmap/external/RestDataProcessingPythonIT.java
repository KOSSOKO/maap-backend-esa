package com.esa.bmap.external;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.services.dataprocessing.interfaces.DataProcessingInterface;

import junit.framework.TestCase;

/**
 * @author edupin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { BmapServiceExternalTestContext.class })
public class RestDataProcessingPythonIT extends TestCase {

	// Class handling external service call (python server)
	@Autowired
	private DataProcessingInterface restDataProcessingPython;

	// Logger
	private Logger LOG = LoggerFactory.getLogger(RestDataProcessingPythonIT.class);

	@Value("${datasource.pathTiffHHTest}")
	String pathTiffHHTest;
	@Value("${datasource.pathTiffDEMTest}")
	String pathTiffDEMTest;
	
	@Value("${datasource.pathTiffROITest}")
	String pathTiffROITest;
	
	@Value("${wkt.georeferencedWktLine}")
	String georeferencedWktLine;

	@Value("${wkt.georeferencedWktPolygon}")
	String georeferencedWktPolygon;

	/**
	 * test to get Min, Max, Average and Standard deviation of a Data
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testGetDataBasicStats() throws BmapException {

		try {

			String rasterGranuleStats = restDataProcessingPython.getRasterBasicStats(pathTiffHHTest);
			LOG.info(rasterGranuleStats);

			Assert.assertNotNull(rasterGranuleStats);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * test to get raster data frequency Histogram
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testGetRasterHist() throws BmapException {

		try {

			String rasterHist = restDataProcessingPython.getRasterHistogram(pathTiffDEMTest);

			Assert.assertNotNull(rasterHist);
			LOG.info(rasterHist);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * test to get raster data Custom profile from a wkt line
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testGetCustomProfile() throws BmapException {

		try {

			String customProfile = restDataProcessingPython.getCustomProfile(pathTiffDEMTest, georeferencedWktLine);

			Assert.assertNotNull(customProfile);
			LOG.info(customProfile);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * test to get raster data subset stats from a wkt polygon
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testGetSubsetStats() throws BmapException {

		try {

			String subsetStats = restDataProcessingPython.getSubsetStats(pathTiffDEMTest, georeferencedWktPolygon);

			Assert.assertNotNull(subsetStats);
			LOG.info(subsetStats);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	/**
	 * test to get raster data subset stats from a roi granule
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testGetRoiSubsetStats() throws BmapException {

		try {

			String subsetStats = restDataProcessingPython.getRoiSubsetStats(pathTiffDEMTest, pathTiffROITest);

			Assert.assertNotNull(subsetStats);
			LOG.info(subsetStats);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}
}
