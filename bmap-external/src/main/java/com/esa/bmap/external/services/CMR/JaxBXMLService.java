package com.esa.bmap.external.services.CMR;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;

// Generals : 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
//External CMR Models : 
import com.esa.bmap.external.model.cmr.collections.Collection;
import com.esa.bmap.external.model.cmr.granules.Granule;

public class JaxBXMLService {
	private static final Logger LOG = LoggerFactory.getLogger(JaxBXMLService.class);
	XMLToolBox xmlToolBox = new XMLToolBox();
	private static JAXBContext jaxbContextGranule;
	private static JAXBContext jaxbContextCollection;
	static {
		try {
			// one time instance creation
			jaxbContextGranule = JAXBContext.newInstance(Granule.class, Granule.class);
		} catch (JAXBException e) {
			throw new IllegalStateException(e);
		}
	}
	static {
		try {
			// one time instance creation
			jaxbContextCollection = JAXBContext.newInstance(Collection.class, Collection.class);
		} catch (JAXBException e) {
			throw new IllegalStateException(e);
		}
	}

	// ===================================================================================
	// ===================================================================================
	// ================================== Granules :
	// =====================================
	// ===================================================================================
	// ===================================================================================
	/**
	 * * Transform a full Granule jaxBObject To a Xml file
	 * 
	 * @param granule Cmr granule full Object
	 * @return A Document Object which contains the Xml
	 * @throws BmapException 
	 */
	public Document createDocXMLFromGranule(Granule granule) throws BmapException {

		// Create the Document
		LOG.debug("Start of  createDocXMLFromGranule");
		Document document = null;
		try {
			Marshaller marshaller = jaxbContextGranule.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
			// Create output Document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
			marshaller.marshal(granule, document);

		} catch (JAXBException | ParserConfigurationException e) {
			throw new BmapException("Failed to create Doc XML from Granule", e);

		}

		document = xmlToolBox.deleteNamespace(document);

		DOMSource source = new DOMSource(document);
		FileWriter writer;
		try {
			writer = new FileWriter(new File("output.xml"));
			StreamResult result2 = new StreamResult(writer);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result2);
		} catch (IOException | TransformerException e) {
			throw new BmapException("Failed to create Doc XML from Collection", e);
		}
		LOG.debug("End of  createDocXMLFromGranule");

		return document;
	}

	/**
	 * create XML file From Granule Document
	 * 
	 * @param document
	 * @param fileName
	 * @throws BmapException 
	 */
	public void createXMLfileFromGranuleDoc(Document document, String fileName) throws BmapException {
		LOG.debug("Start of  createXMLfileFromGranuleDoc");
		// create the xml file
		// transform the DOM Object to an XML File

		DOMSource source = new DOMSource(document);
		FileWriter writer;
		try {
			writer = new FileWriter(new File(fileName));
			StreamResult result2 = new StreamResult(writer);

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.transform(source, result2);
		} catch (IOException | TransformerException e) {
			throw new BmapException("Failed to create XML file from Granule Doc", e);
		}

		LOG.debug("End of  createXMLfileFromGranuleDoc");
	}

	/**
	 * Create a xmlFile on hard Disk root from a Granule object
	 * 
	 * @param granule the granule object enttry
	 * @param fileName the output fileName with/without filepath and ! without
	 *            extension .xml
	 * @throws BmapException
	 */
	public void createXmlFileFromGranule(Granule granule, String fileName) throws BmapException {

		LOG.debug("Start of  createXmlFileFromGranule");

		try {

			Marshaller marshaller = jaxbContextGranule.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
			OutputStream os = new FileOutputStream(fileName + ".xml");
			marshaller.marshal(granule, os);
		} catch (JAXBException | FileNotFoundException | FactoryConfigurationError e) {
			throw new BmapException("Failed to create Xml file from Granule", e);
		}
		LOG.debug("End of  createXmlFileFromGranule");
	}

	/**
	 * marshal a Granule object to a xml String
	 * 
	 * @param granule
	 * @param fileName
	 * @throws BmapException 
	 */
	public String marshalGranuleToXMLString(Granule granule) throws BmapException {

		LOG.debug("Start of  marshalGranuleToXMLString");

		String xmlString = null;
		try {

			Marshaller marshaller = jaxbContextGranule.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N

			StringWriter sw = new StringWriter();
			marshaller.marshal(granule, sw);
			xmlString = sw.toString();
		} catch (JAXBException | FactoryConfigurationError e) {
			throw new BmapException("Failed to marshall Granule to XML", e);
		}
		LOG.debug("End of  marshalGranuleToXMLString");
		return xmlString;

	}

	/**
	 * Convert a Granule Xml from CMR to a Granule JaxB Object
	 * 
	 * @param document The Granule.xml
	 * @return Granule java class
	 * @throws BmapException 
	 */
	public Granule createGranuleFromXML(Document document) throws BmapException {
		LOG.debug("Start of createGranuleFromXML ");

		Granule granule2 = new Granule();
		try {

			Unmarshaller jaxbUnmarshaller = jaxbContextGranule.createUnmarshaller();
			granule2 = (Granule) jaxbUnmarshaller.unmarshal(document);

		} catch (JAXBException e) {
			throw new BmapException("Failed to create Granule from XML", e);
		}

		LOG.debug("End of createGranuleFromXML");
		return granule2;
	}

	/**
	 * Convert a Granule Xml from CMR to a Granule JaxB Object
	 * 
	 * @param node The Granule.xml
	 * @return Granule java class
	 * @throws JAXBException
	 */
	public Granule createGranuleFromNode(Node node) throws JAXBException {
		LOG.debug("Start of createGranuleFromXML ");
		Granule granule2 = new Granule();
		Unmarshaller jaxbUnmarshaller = jaxbContextGranule.createUnmarshaller();
		granule2 = (Granule) jaxbUnmarshaller.unmarshal(node);

		LOG.debug("End of createGranuleFromXML");

		return granule2;
	}

	// ===================================================================================
	// ===================================================================================
	// ================================== Collections :
	// ===================================================================================
	// ===================================================================================

	/**
	 * * Transform a full Collection jaxBObject To a Xml file
	 * 
	 * @param granule Cmr Collection full Object
	 * @return A Document Object which contains the Xml
	 * @throws BmapException 
	 */
	public Document createDocXMLFromCollection(Collection collection) throws BmapException {
		// Create the Document
		LOG.debug("Start of  createDocXMLFromCollection");
		Document document = null;

		try {

			Marshaller marshaller = jaxbContextCollection.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Create output Document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
			marshaller.marshal(collection, document);

		} catch (JAXBException | ParserConfigurationException e) {
			throw new BmapException("Failed to create Doc XML from Collection", e);

		}
		LOG.debug("End of  createDocXMLFromCollection");
		return document;
	}

	/**
	 * Convert a collection Xml from CMR to a collection JaxB Object
	 * 
	 * @param document The collection.xml
	 * @return collection java class
	 * @throws BmapException 
	 */
	public Collection createCollectionFromXML(Document document) throws BmapException {
		LOG.debug("Start of  createCollectionFromXML");
//		ArrayList<String> xmlString = xmlToolBox.getTagNameTextContentFromXML(document, "Collection");

		xmlToolBox.createXmlFileFromDoc(document, Common.getPropertiesValue("Download.FilePath") + "fileName");
		Collection collection = null;
		try {
			Unmarshaller jaxbUnmarshaller = jaxbContextCollection.createUnmarshaller();
			collection = (Collection) jaxbUnmarshaller.unmarshal(document);
		} catch (JAXBException e) {
			throw new BmapException("Failed to create Collection from XML", e);
		}
		LOG.debug("End of createCollectionFromXML");
		return collection;
	}

}
