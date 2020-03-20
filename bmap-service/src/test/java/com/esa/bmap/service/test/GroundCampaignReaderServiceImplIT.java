package com.esa.bmap.service.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Collection;
import com.esa.bmap.service.catalogue.data.interfaces.GroundCampaignReaderServiceInterface;
import com.esa.bmap.service.dataprocessing.implement.RasterStatisticsServiceImpl;
import com.esa.bmap.service.dataprocessing.interfaces.RasterStatisticsServiceInterface;

@RunWith(SpringRunner.class)
// @SpringBootTest
// @ContextConfiguration(classes = { BmapServiceTestContext.class })
public class GroundCampaignReaderServiceImplIT {

	// @Autowired
	private GroundCampaignReaderServiceInterface groundCampaignReader;
//
//	@Value("${datasource.dataDirectoryTest}")
//	String dataDirectoryTest;
//
//	@Value("${datasource.pathShpTest}")
//	String pathShpTest;
//
//	@Value("${datasource.pathTiffAzTest}")
//	String pathTiffAzTest;
//	@Value("${datasource.pathTiffRgTest}")
//	String pathTiffRgTest;
//	@Value("${datasource.pathTiffDemTest}")
//	String pathTiffDemTest;
//
//	@Value("${datasource.pathTiffSlcHhTest}")
//	String pathTiffSlcHhTest;
//	@Value("${datasource.pathTiffSlcHvTest}")
//	String pathTiffSlcHvTest;
//	@Value("${datasource.pathTiffSlcVhTest}")
//	String pathTiffSlcVhTest;
//	@Value("${datasource.pathTiffSlcVvTest}")
//	String pathTiffSlcVvTest;
//	@Value("${datasource.pathTiffIncTest}")
//	String pathTiffIncTest;
//	@Value("${datasource.pathTiffKzTest}")
//	String pathTiffKzTest;
//
//	@Value("${datasource.path3band}")
//	String path3band;
//
//	// directory on python server for user data
//	@Value("${pythonserver.userdatadir}")
//	private String privateUserDataDir;
//	// directory on python server for campaign data
//	@Value("${pythonserver.datadir}")
//	private String campaignDataDir;
	private static final Logger LOG = LoggerFactory.getLogger(GroundCampaignReaderServiceImplIT.class);

	/**
	 * Ingest an roi data in database and in Geoserver
	 * 
	 * @throws BmapException
	 */
	@Test
	@Ignore
	@Deprecated
	public void testRetrieveROI() throws BmapException {

//		// expected bounding box for biosar1_roi_insitu1.shp = [419606.18, 6480624.67,
//		// 419689.7, 6480710.93]
//
//		File file = new File(dataDirectoryTest + "\\" + pathShpTest + ".shp");
//
//		// test: database ingestion of the given shapefile
//		try {
//
//			groundCampaignReader.retrieveROI(file.getAbsolutePath(), pathShpTest, "insitu1", new Collection("biosar1"),
//					file.getParentFile().getAbsolutePath(), null);
//
//		} catch (Exception e) {
//			fail("error wile retrieving roi");
//			LOG.error(e.getMessage(), e);
//			throw new BmapException(e.getMessage(), e);
//		}
	}

	@Test
	@Ignore
	@Deprecated
	public void testRasterStats() throws BmapException {

		RasterStatisticsServiceInterface rasterStats = new RasterStatisticsServiceImpl();
		rasterStats.getRasterBasicStats("/app/data/User_Data" + "/" + "afrisar_dlr/afrisar_dlr_Ra1-0_az.tiff");
		assertNotNull(rasterStats);

	}

	/**
	 * Ingest an airborn data in database and in Geoserver (type georeferenced
	 * Geotiff)
	 * 
	 * @throws BmapException
	 */
	@Test
	@Ignore
	@Deprecated
	public void testRetrieveGeotiff() throws BmapException {
//
//		String subregionName = "test";
//		Collection collection = new Collection("biosar1");
//		String date = "02/04/2007 11:52";
//
//		File fileAz = new File(dataDirectoryTest + "\\" + pathTiffAzTest + ".tiff");
//		File fileRg = new File(dataDirectoryTest + "\\" + pathTiffRgTest + ".tiff");
//		File fileDem = new File(dataDirectoryTest + "\\" + pathTiffDemTest + ".tiff");
//
//		// test: database ingestion of the given airborneData
//		try {
//
//			groundCampaignReader.ingestGeotiff(fileAz, date, "surfaceresol", "grdresol", null);
//			// groundCampaignReader.ingestGeotiff(fileAz.getAbsolutePath(), pathTiffAzTest,
//			// date, subregionName, collection, "biosar1", "az", "instrument name");
//			// groundCampaignReader.ingestGeotiff(fileRg.getAbsolutePath(), pathTiffRgTest,
//			// date, subregionName, collection, "biosar1", "rg", "instrument name");
//			// groundCampaignReader.ingestGeotiff(fileDem.getAbsolutePath(),
//			// pathTiffDemTest, date, subregionName, collection, "biosar1", "dem",
//			// "instrument name");
//
//		} catch (Exception e) {
//
//			LOG.error(e.getMessage(), e);
//			fail("error wile ingesting geotiff");
//			throw new BmapException(e.getMessage(), e);
//		}
	}

}
