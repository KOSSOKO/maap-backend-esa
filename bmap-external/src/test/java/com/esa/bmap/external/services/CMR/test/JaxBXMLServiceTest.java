package com.esa.bmap.external.services.CMR.test;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.model.cmr.collections.Collection;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.services.CMR.JaxBXMLService;
import com.esa.bmap.external.services.CMR.XMLToolBox;

public class JaxBXMLServiceTest {
	XMLToolBox xmlToolBox = new XMLToolBox();
	JaxBXMLService jaxbService = new JaxBXMLService();
	UtilisGranuleTest granuleInit = new UtilisGranuleTest();
	Document document1 = null;

	@Test
	/**
	 * Test to create a Doc xml echo 10 from a granule object
	 */
	public void testCreateDocXMLFromGranule() throws Exception {

		// initiate a Granule object :
		Granule granule1 = new Granule();
		granule1 = granuleInit.initGranuleSubRegion(granule1);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		document1 = db.newDocument();
		document1 = jaxbService.createDocXMLFromGranule(granule1);
		xmlToolBox.toString(document1);
		Granule granule2 = new Granule();
		granule2 = jaxbService.createGranuleFromXML(document1);

		try {
			Assert.assertEquals(granule1, granule2);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Test to create a Doc xml echo 10 from a collection object
	 */
	public void testCreateDocXMLFromCollection() throws Exception {

		// initiate a Granule object :
		Collection collection1 = new Collection();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		document1 = db.newDocument();
		document1 = jaxbService.createDocXMLFromCollection(collection1);
		xmlToolBox.toString(document1);
		Collection collection2 = new Collection();
		collection2 = jaxbService.createCollectionFromXML(document1);

		try {
			Assert.assertEquals(collection1.getShortName(), collection2.getShortName());
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	
	@Test
	/**
	 * Test to create granule.xml from a Test generated granule Object 
	 * and validate the created xml file with the Granule.xsd provide by CMR
	 * 
	 */
	public void testCreateXmlFile() throws BmapException{
		
		Granule granule = new Granule();
		granule = granuleInit.initGranulePolarisation(granule);
		String fileName = Common.getPropertiesValue("Download.FilePath")+"Granule.xml";
		Document doc = jaxbService.createDocXMLFromGranule(granule);
		jaxbService.createXMLfileFromGranuleDoc(doc, fileName);
		String xsdFile = Common.getPropertiesValue("xsd.FilePath")+"Granule.xsd";
		boolean result = xmlToolBox.ValidateGranuleXML(fileName, xsdFile);
		try {
			Assert.assertTrue(result);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}
}