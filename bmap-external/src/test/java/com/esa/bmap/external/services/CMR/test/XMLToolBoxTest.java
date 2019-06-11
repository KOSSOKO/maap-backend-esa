package com.esa.bmap.external.services.CMR.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;

import com.esa.bmap.common.Common;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.services.CMR.JaxBXMLService;
import com.esa.bmap.external.services.CMR.XMLToolBox;
import com.esa.bmap.model.Quadrangle;
import com.esa.bmap.model.QuadrangleType;
import com.vividsolutions.jts.geom.Coordinate;

public class XMLToolBoxTest {
	private XMLToolBox xmlToolBox = new XMLToolBox();
	private UtilisGranuleTest granuleInit = new UtilisGranuleTest();
	private JaxBXMLService jaxbService = new JaxBXMLService();

	@Test
	/**
	 * Generate a xml file from a granule , save it in a file , load it in a
	 * Document Object And finnally compare these two Document Object Test
	 * convertXMLDocumentToXmlString Test createDocXMLFromGranule Test
	 * initGranulePolarisation Test loadXMLFromFile Test createXmlFileFromDoc
	 * 
	 */
	public void testLoadXMLFromFile() {
		Granule granule = new Granule();
		granule = granuleInit.initGranulePolarisation(granule);
		Document doc = jaxbService.createDocXMLFromGranule(granule);
		xmlToolBox.createXmlFileFromDoc(doc, "generatedGranules/granuleTest");
		Document doc2 = xmlToolBox.loadXMLFromFile("generatedGranules/granuleTest");

		String docString = xmlToolBox.convertXMLDocumentToXmlString(doc);
		String doc2String = xmlToolBox.convertXMLDocumentToXmlString(doc2);

		try {
			Assert.assertEquals(docString, doc2String);
			;
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}

	}

	@Test
	/**
	 * test the testValidateGranuleXML function Test initGranulePolarisation Test
	 * createXmlFileFromDoc Test ValidateGranuleXML
	 */
	public void testValidateGranuleXML() {

		Granule granule = new Granule();
		granule = granuleInit.initGranulePolarisation(granule);
		Document doc = jaxbService.createDocXMLFromGranule(granule);
		xmlToolBox.createXmlFileFromDoc(doc, "generatedGranules/granuleTest");
		boolean result = xmlToolBox.ValidateGranuleXML( Common.getPropertiesValue("Download.FilePath")+"granuleTest.xml", "xsdSchema/Granule.xsd");
		try {
			Assert.assertTrue(result);
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Test the conversion of a xmlString into a Document object
	 */
	public void testConvertStringToDocument() {

		Granule granule = new Granule();
		granule = granuleInit.initGranulePolarisation(granule);
		Document doc = jaxbService.createDocXMLFromGranule(granule);
		String xmlString = xmlToolBox.convertXMLDocumentToXmlString(doc);
		Document doc2 = xmlToolBox.convertStringToDocument(xmlString);
		String xmlString2 = xmlToolBox.convertXMLDocumentToXmlString(doc2);

		try {
			Assert.assertEquals(xmlString, xmlString2);
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	@Test
	/**
	 * Test to extract a textcontent node from a xml document
	 */
	public void testGetTagNameTextContentFromXML() {
		Granule granule = new Granule();
		granule = granuleInit.initGranulePolarisation(granule);
		Document doc = jaxbService.createDocXMLFromGranule(granule);
		ArrayList<String> tags = xmlToolBox.getTagNameTextContentFromXML(doc, "GranuleUR");
		String testTags = tags.get(0);
		String testShouldBe = "GranuleURtest";
		try {
			Assert.assertEquals(testTags, testShouldBe);
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

	@Test
	// test the the date conversion 
	public void testConvertLocalDateToSimpleDate() {
		
		
			String date = "2018-09-24T00:00:00";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
			LocalDate localDate = LocalDate.parse(date, formatter);
			String simpleDateString = xmlToolBox.convertLocalDateToSimpleDate(localDate);

			try {
				Assert.assertEquals(simpleDateString, date);
			} catch (Exception e) {

				Assert.fail(e.getMessage());
			}
	}

	

	@Test
	/**
	 * test the conversion of a quadrangle to a Gpolygon string before the ingestion process 
	 */
	public void testCreateGpolygonString() {
		Quadrangle quadrangle = new Quadrangle(QuadrangleType.LATLONG, new Coordinate[] { new Coordinate(1.0, 3.0),
				new Coordinate(3.0, 3.0), new Coordinate(1.0, 6.0), new Coordinate(3.0, 6.0) }, 3, 3);
		String GpolygonString = xmlToolBox.createGpolygonString(quadrangle);
		String ExpectedGpolygonString="1.0,3.0,3.0,3.0,1.0,6.0,3.0,6.0,1.0,3.0";
		try {
			Assert.assertEquals(GpolygonString, ExpectedGpolygonString);
		} catch (Exception e) {

			Assert.fail(e.getMessage());
		}
	}

}
