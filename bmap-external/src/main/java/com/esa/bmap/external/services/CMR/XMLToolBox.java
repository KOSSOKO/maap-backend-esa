package com.esa.bmap.external.services.CMR;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.esa.bmap.model.Quadrangle;
import com.vividsolutions.jts.geom.Coordinate;

/**
 * 
 * @author THBLED A custom XML exploitation toolbox
 */
public class XMLToolBox {
	private static final Logger LOG = LoggerFactory.getLogger(XMLToolBox.class);

	// ========================================================================================
	// ================================== Generals
	// =======================================
	// ========================================================================================

	

	/**
	 * delete the xmlns attributes of the first node of a XML Document
	 * 
	 * @param doc
	 * @return Document
	 */
	public Document deleteNamespace(Document doc) {

		LOG.debug("Start of  deleteNamespace ");
		String xmlString = convertXMLDocumentToXmlString(doc);
		xmlString = xmlString.replaceAll("xmlns=\"CMR/granules\" ", "");
		doc = convertStringToDocument(xmlString);

		Element element = (Element) doc.getFirstChild();
		element.removeAttributeNS("xmlns", "xmlns");
		LOG.debug("End of  deleteNamespace");
		return doc;
	}

	/**
	 * load into a Document object a XML file
	 * 
	 * @param filepath the filepath +fileName without ".xml" extension
	 * @return Document object
	 */
	public Document loadXMLFromFile(String filepath) {
		LOG.debug("Start of  loadXMLFromFile ");
		Document doc = null;
		try {
			File fXmlFile = new File(filepath + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (ParserConfigurationException | IOException | SAXException e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}
		LOG.debug("End of  loadXMLFromFile ");
		return doc;
	}

	/**
	 * test the Validation a granule.xml with the xsd
	 * 
	 * @param xmlFile
	 * @param xsdFile
	 * @return
	 */
	public boolean ValidateGranuleXML(String xmlFile, String xsdFile) {
		LOG.debug("Start of  ValidateGranuleXML Class");
		boolean result = false;

		try {
			// Setup schema validator
			SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema granuleSchema = sf.newSchema(new File(xsdFile));

			Validator validator = granuleSchema.newValidator();
			validator.validate(new StreamSource(new File(xmlFile)));
			result = true;
		} catch (SAXException | IOException e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}

		LOG.debug("End of  ValidateGranuleXML Class");
		return result;
	}

	/**
	 * Remove a Namespace from a XML string
	 * 
	 * @param xml String well formated
	 * @return a Xml string without namespace
	 */
	public String removeNameSpace(String xml) {
		LOG.debug("Start of  removeNameSpace ");
		String modifiedRequestXML = "";
		try {
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.METHOD, "xml");
			transformer.setOutputProperty(OutputKeys.INDENT, "false");

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource(new StringReader(xml));
			Document xmlDoc = builder.parse(inputSource);
			Node root = xmlDoc.getDocumentElement();
			NodeList rootchildren = root.getChildNodes();
			Element newroot = xmlDoc.createElement(root.getNodeName());
			for (int i = 0; i < rootchildren.getLength(); i++) {
				newroot.appendChild(rootchildren.item(i).cloneNode(true));
			}
			xmlDoc.replaceChild(newroot, root);
			DOMSource requestXMLSource = new DOMSource(xmlDoc.getDocumentElement());
			StringWriter requestXMLStringWriter = new StringWriter();
			StreamResult requestXMLStreamResult = new StreamResult(requestXMLStringWriter);
			transformer.transform(requestXMLSource, requestXMLStreamResult);
			modifiedRequestXML = requestXMLStringWriter.toString();

			return modifiedRequestXML;
		} catch (Exception e) {
			LOG.error(e.toString());
		}
		LOG.debug("End of  removeNameSpace ");
		return modifiedRequestXML;
	}

	/**
	 * Convert a XML String to a Document object
	 * 
	 * @param xmlString
	 * @return a Document object
	 */
	public Document convertStringToDocument(String xmlString) {
		LOG.debug("Start of convertStringToDocument ");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;

		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(xmlString)));
			document.setXmlStandalone(true);

		} catch (Exception e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}
		LOG.debug("End of convertStringToDocument ");
		return document;
	}

	/**
	 * Extract Content text from Tags from XML Document
	 * 
	 * @return an arraylist which contains the content of all the tags with these
	 *         tags name
	 */
	public ArrayList<String> getTagNameTextContentFromXML(Document doc, String tagName) {
		LOG.debug("Start of getTagNameTextContentFromXML "+ tagName);
		ArrayList<String> resultList = new ArrayList<String>();
		NodeList locationList = doc.getElementsByTagName(tagName);
		// For each tagName node found :
		// extract each text Content
		for (int i = 0; i < locationList.getLength(); i++) {
			resultList.add(locationList.item(i).getTextContent());
		}
		LOG.debug("End of getTagNameTextContentFromXML ");
		return resultList;
	}

	
	/**
	 * Extract Node  from Tags from XML Document
	 * 
	 * @return an arraylist which contains the content of all the tags with these
	 *         tags name
	 */
	public NodeList getTagNameNodeListFromXML(Document doc, String tagName) {
		LOG.debug("Start of getTagNameTextContentFromXML "+ tagName);
		ArrayList<String> resultList = new ArrayList<String>();
		NodeList locationList = doc.getElementsByTagName(tagName);
		
		LOG.debug("End of getTagNameTextContentFromXML ");
		return locationList;
	}
	/**
	 * extract and build a new xml Document with tags name
	 * 
	 * @param doc     
	 * Document source
	 * @param tagName 
	 * Tag name to extract
	 * @return
	 * 		new xml document object
	 */
	public Document getXMLNodeContentFromTag(Document doc, String tagName) {
		
		NodeList nodeList = doc.getElementsByTagName(tagName);
		
		Document newXmlDocument = null;
		try {
			newXmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			Element root = newXmlDocument.createElement(tagName);
			//SOL1 newXmlDocument.appendChild(root);
			for (int i = 0; i < nodeList.getLength(); i++) {
				if (nodeList.item(i).getNodeName().equals(tagName)) {
				//if (!nodeList.item(i).getTextContent().equals(tagName)) {
					Node node = nodeList.item(i);
					Node copyNode = newXmlDocument.importNode(node, true);
					//root.appendChild(copyNode);
					newXmlDocument.appendChild(copyNode);
					break;
				}

			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		return newXmlDocument;
	}

	/**
	 * Extract a node and its children
	 * 
	 * @param doc
	 * the document object 
	 * @param elementName
	 * the name of the top level node who want to keep
	 * @return
	 * a xml document focused on the elementName and its children
	 */
	public Document extractXMLElementFromXML(Document doc, String elementName) {
		LOG.debug("Start of extractXMLElementFromXML ");
		Node node = doc.getElementsByTagName(elementName).item(0);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document result = dBuilder.newDocument();
			Node importNode = result.importNode(node, true);
			result.appendChild(importNode);
		} catch (ParserConfigurationException e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}
		LOG.debug("Start of extractXMLElementFromXML ");
		return doc;
	}

	/**
	 * Create a XML file on the hard disk from a Document doc
	 * 
	 * @param doc      Document object
	 * @param fileName filePath/filename without extension ".xml"
	 */
	public void createXmlFileFromDoc(Document doc, String fileName) {
		LOG.debug("Start of createXmlFileFromDoc ");
		DOMSource source = new DOMSource(doc);
		StringWriter writer;

		try {
			//writer = new FileWriter(new File(fileName + ".xml"));
			LOG.debug(" content  : "+fileName);
			writer = new StringWriter();
			StreamResult result2 = new StreamResult(writer);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result2);
			LOG.debug(" content  : "+writer.toString());
			
		} catch (TransformerException e) {
			LOG.error(e.toString());
			e.printStackTrace();
		}
		LOG.debug("End of createXmlFileFromDoc ");
	}

	public String convertXMLDocumentToXmlString(Document doc) {
		LOG.debug("End of convertXMLDocumentToXmlString ");
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		String output = "";
		try {
			transformer = tf.newTransformer();
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString();// .replaceAll("\n|\r", "");

		} catch (TransformerException e) {

			e.printStackTrace();
			LOG.error(e.toString());
		}
		LOG.debug("End of convertXMLDocumentToXmlString ");
		return output;

	}

	// ===================================================================================================
	// ================================ GENERALS CONVERSION :
	// ==================================================================================================
	/**
	 * Convert a localDate (ESA) to a String SimpleDateFormat (CMR)
	 * 
	 * @param localDate localDate (ESA)
	 * @return a String SimpleDateFormat (CMR)
	 */
	public String convertLocalDateToSimpleDate(LocalDate localDate) {
		LOG.debug("Start of  convertLocalDateToSimpleDate ");
		// define the timezone utc
		Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		Date res = Date.from(instant);
		// yyyy-MM-ddTHH:mm:ssZ format
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
		String result = formatter.format(res);
		LOG.debug("End of  convertLocalDateToSimpleDate ");
		return result;

	}

	/**
	 * Convert a SimpleDateFormat to a LocalDate yyyy-MM-dd'T'HH:mm:ssZ ==>
	 * yyyy-MM-dd
	 * 
	 * @param dt yyyy-MM-dd'T'HH:mm:ssZ date format
	 * @return String yyyy-MM-dd dateformat
	 */
	public String convertSimpleDateToLocalDate(SimpleDateFormat dt) {
		LOG.debug("Start of  convertSimpleDateToLocalDate ");
		SimpleDateFormat sd2 = new SimpleDateFormat("yyyy-MM-dd");
		String newDate = sd2.format(dt);
		LOG.debug("End of  convertSimpleDateToLocalDate ");
		return newDate;
	}

	/**
	 * Create a string search polygon from a quadrangle datacriteria
	 * 
	 * @param quadrangle ESA quadrangle
	 * @return String contains a Gpolygon formated for CMR
	 */
	public String createGpolygonString(Quadrangle quadrangle) {
		LOG.debug("Start of  createGpolygonString ");

		Coordinate[] coordinates = quadrangle.getGeometry().getCoordinates();
		String polygon = "";
		// For all coordinates points :
		for (int i = 0; i < coordinates.length; i++) {
			Coordinate coordinateP1 = coordinates[i];
			// create the request link
			polygon += BigDecimal.valueOf(coordinateP1.x).toPlainString() + ","
					+ BigDecimal.valueOf(coordinateP1.y).toPlainString() + ",";
		}
		// delete the last "," generated :
		polygon = polygon.substring(0, polygon.length() - 1);
		LOG.debug("End of  createGpolygonString ");
		return polygon;
	}
	


/**
 * Return a string representation of the document
 * @param doc
 * @return
 */
public static String toString(Document doc) {
    try {
        StringWriter sw = new StringWriter();
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

        transformer.transform(new DOMSource(doc), new StreamResult(sw));
        return sw.toString();
    } catch (Exception ex) {
        throw new RuntimeException("Error converting to String", ex);
    }
}


}
