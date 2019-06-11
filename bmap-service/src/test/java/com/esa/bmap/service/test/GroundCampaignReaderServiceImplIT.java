package com.esa.bmap.service.test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.DataSourceException;
import org.geotools.gce.image.WorldImageReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengis.geometry.Envelope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Collection;
import com.esa.bmap.service.catalogue.data.interfaces.GroundCampaignReaderServiceInterface;
import com.esa.bmap.service.dataprocessing.implement.RasterStatisticsServiceImpl;
import com.esa.bmap.service.dataprocessing.interfaces.RasterStatisticsService;

@RunWith(SpringRunner.class)
// @SpringBootTest
// @ContextConfiguration(classes = { BmapServiceTestContext.class })
public class GroundCampaignReaderServiceImplIT {

	// @Autowired
	private GroundCampaignReaderServiceInterface groundCampaignReader;

	@Value("${datasource.dataDirectoryTest}")
	String dataDirectoryTest;

	@Value("${datasource.pathShpTest}")
	String pathShpTest;

	@Value("${datasource.pathTiffAzTest}")
	String pathTiffAzTest;
	@Value("${datasource.pathTiffRgTest}")
	String pathTiffRgTest;
	@Value("${datasource.pathTiffDemTest}")
	String pathTiffDemTest;

	@Value("${datasource.pathTiffSlcHhTest}")
	String pathTiffSlcHhTest;
	@Value("${datasource.pathTiffSlcHvTest}")
	String pathTiffSlcHvTest;
	@Value("${datasource.pathTiffSlcVhTest}")
	String pathTiffSlcVhTest;
	@Value("${datasource.pathTiffSlcVvTest}")
	String pathTiffSlcVvTest;
	@Value("${datasource.pathTiffIncTest}")
	String pathTiffIncTest;
	@Value("${datasource.pathTiffKzTest}")
	String pathTiffKzTest;

	@Value("${datasource.path3band}")
	String path3band;
	private static final Logger LOG = LoggerFactory.getLogger(GroundCampaignReaderServiceImplIT.class);

	/**
	 * Ingest an roi data in database and in Geoserver
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testRetrieveROI() throws BmapException {

		// expected bounding box for biosar1_roi_insitu1.shp = [419606.18, 6480624.67,
		// 419689.7, 6480710.93]

		File file = new File(dataDirectoryTest + "\\" + pathShpTest + ".shp");

		// test: database ingestion of the given shapefile
		try {

			groundCampaignReader.retrieveROI(file.getAbsolutePath(), pathShpTest, "insitu1", "test",
					new Collection("biosar1"), file.getParentFile().getAbsolutePath());

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

	@Test
	public void testRasterStats() throws BmapException {

		RasterStatisticsService rasterStats = new RasterStatisticsServiceImpl();
		rasterStats.getRasterBasicStats("afrisar_dlr/afrisar_dlr_Ra1-0_az.tiff");
	}

	/**
	 * Ingest an airborn data in database and in Geoserver (type georeferenced
	 * Geotiff)
	 * 
	 * @throws BmapException
	 */
	@Test
	public void testRetrieveGeotiff() throws BmapException {

		String subregionName = "test";
		Collection collection = new Collection("biosar1");
		String date = "02/04/2007 11:52";

		File fileAz = new File(dataDirectoryTest + "\\" + pathTiffAzTest + ".tiff");
		File fileRg = new File(dataDirectoryTest + "\\" + pathTiffRgTest + ".tiff");
		File fileDem = new File(dataDirectoryTest + "\\" + pathTiffDemTest + ".tiff");

		// test: database ingestion of the given airborneData
		try {

			groundCampaignReader.ingestGeotiff(fileAz, "heading", "zflight", "zterrain", "slrstart", "pixelspacing",
					"surfaceresol", "grdresol", date, "dem", "master", "subregion", "fileName", collection, "tiff",
					"instrumentName", null);
			// groundCampaignReader.ingestGeotiff(fileAz.getAbsolutePath(), pathTiffAzTest,
			// date, subregionName, collection, "biosar1", "az", "instrument name");
			// groundCampaignReader.ingestGeotiff(fileRg.getAbsolutePath(), pathTiffRgTest,
			// date, subregionName, collection, "biosar1", "rg", "instrument name");
			// groundCampaignReader.ingestGeotiff(fileDem.getAbsolutePath(),
			// pathTiffDemTest, date, subregionName, collection, "biosar1", "dem",
			// "instrument name");

		} catch (Exception e) {

			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

//	/**
//	 * Ingest an airborn data in database and in Geoserver (type non-georeferenced
//	 * WorldImage)
//	 *
//	 * @throws BmapException
//	 */
//	@Test
//	public void testRetrieveWorldImage() throws BmapException {
//
//		Collection collection = new Collection("biosar1");
//		String date = "02/04/2007 11:52";
//		URL url = null;
//		try {
//			url = new File(
//					"C:\\Program Files (x86)\\GeoServer 2.14.1\\data_dir\\data\\testbed\\Campaign_data\\afrisar_dlr\\afrisar_dlr_T2-0_SLC_HH.tiff")
//							.toURI().toURL();
//			WorldImageReader reader = new WorldImageReader(url);
//			GridCoverage2D coverage = (GridCoverage2D) reader.read(null);
//			Envelope env = coverage.getEnvelope();
//		
//
//		} catch (IllegalArgumentException | IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//	}
}
