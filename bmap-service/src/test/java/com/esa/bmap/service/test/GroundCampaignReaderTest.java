package com.esa.bmap.service.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.gce.image.WorldImageFormat;
import org.geotools.gce.image.WorldImageReader;
import org.geotools.referencing.CRS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.opengis.geometry.Envelope;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.TransformException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.esa.bmap.BmapServiceTestContext;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.loaders.interfaces.GeotiffLoaderInterface;
import com.esa.bmap.model.GranuleCriteria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { BmapServiceTestContext.class })
public class GroundCampaignReaderTest {
	// @Autowired
	// private GroundCampaignReaderServiceInterface dataReader;
	// final String GEOSERVER_DATA_DIR = System.getenv("GEOSERVER_DATA_DIR");
	// final Logger LOG = LoggerFactory.getLogger(GroundCampaignReaderTest.class);
	// final String GEOSERVER_WORKSPACE = "BMAPCatalogue_v1.0";
	// final String GEOSERVER_URL = "http://localhost:8089/geoserver";

	@Value("${geoserver.username}")
	private String GEOSERVER_USERNAME;

	@Value("${geoserver.workspace}")
	private String GEOSERVER_WORKSPACE;

	@Value("${geoserver.password}")
	private String GEOSERVER_PASSWORD;

	@Value("${geoserver.url}")
	private String GEOSERVER_URL;

	@Value("${geoserver.datadir}")
	private String DATA_DIR;

	@Autowired
	GeotiffLoaderInterface geoLS;

	private final Logger LOG = LoggerFactory.getLogger(GroundCampaignReaderTest.class);

	@Test
	public void retreiveTiffTest() throws IOException, IllegalArgumentException, BmapException,
			NoSuchAuthorityCodeException, FactoryException, TransformException {

		
		GranuleCriteria granuleCriteriaTest = new GranuleCriteria();

		List<String> subRegions = new ArrayList<String>();
		subRegions.add("La Lope");
		subRegions.add("Rabi");
		granuleCriteriaTest.setSubRegionNames(subRegions);
		LOG.info(granuleCriteriaTest.toString());
		
		String path = "C:\\Program Files (x86)\\GeoServer 2.14.1\\data_dir\\data\\Campaign_data\\afrisar_dlr\\afrisar_dlr_Ra1-0_az.tiff";
		File geoTiffFile = new File(path);

//		GeoTiffFormat format = new GeoTiffFormat();
//		Hints hint = new Hints();
//
//		hint.put(Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE);
//		GeoTiffReader tiffReader = format.getReader(geoTiffFile);
//
//		GridCoverage2D coverage = tiffReader.read(null);
//		CoordinateReferenceSystem crs = coverage.getCoordinateReferenceSystem();
//		
//		LOG.info(CRS.lookupIdentifier(crs, true));
//
//		
//		CoordinateReferenceSystem sourceCRS = CRS.decode("EPSG:4326");
//		LOG.info(CRS.lookupIdentifier(sourceCRS, true));
//
//	
//
//		ReferencedEnvelope bbox = new ReferencedEnvelope(crs);
//		bbox.setBounds(coverage.getEnvelope2D());
//		boolean lenient = false;
//		CoordinateReferenceSystem target = DefaultGeographicCRS.WGS84;
//
//		MathTransform transform = CRS.findMathTransform(crs, target, lenient);
//		ReferencedEnvelope res = new ReferencedEnvelope(JTS.transform(bbox, transform), target);

		WorldImageReader worldImagereader=new WorldImageFormat().getReader(geoTiffFile);
		
		GridCoverage2D coverage=worldImagereader.read(null);
		Envelope env = new WorldImageFormat().getReader(geoTiffFile).read(null).getEnvelope();
		CoordinateReferenceSystem crs = coverage.getCoordinateReferenceSystem();
		
		LOG.info(CRS.lookupIdentifier(crs, true));

	}
	//
	// @Test
	// public void testPropertiesfile() throws BmapException {
	//
	// InputStream inputStream = null;
	//
	// try {
	// Properties prop = new Properties();
	// String propFileName = "application.properties";
	//
	// inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
	//
	// if (inputStream != null) {
	// prop.load(inputStream);
	// } else {
	// throw new BmapException("");
	// }
	// GEOSERVER_URL = prop.getProperty("geoserver.url");
	// GEOSERVER_USERNAME = prop.getProperty("geoserver.username");
	// GEOSERVER_PASSWORD = prop.getProperty("geoserver.password");
	// GEOSERVER_WORKSPACE = prop.getProperty("geoserver.workspace");
	//
	// System.out.println(GEOSERVER_URL);
	// System.out.println(GEOSERVER_DATA_DIR);
	// System.out.println(GEOSERVER_USERNAME);
	// System.out.println(GEOSERVER_PASSWORD);
	// System.out.println(GEOSERVER_WORKSPACE);
	//
	// } catch (Exception e) {
	// System.out.println("Exception: " + e);
	// } finally {
	// try {
	// inputStream.close();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// throw new BmapException("");
	// }
	// }
	// }
}
