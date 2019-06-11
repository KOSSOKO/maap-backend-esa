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
//External CMR Models : 
import com.esa.bmap.external.model.cmr.collections.Collection;
import com.esa.bmap.external.model.cmr.granules.Granule;

public class JaxBXMLService {
	private static final Logger LOG = LoggerFactory.getLogger(JaxBXMLService.class);
	XMLToolBox xmlToolBox = new XMLToolBox();

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
	 */
	public Document createDocXMLFromGranule(Granule granule) {
		// Create the Document
		LOG.debug("Start of  createDocXMLFromGranule");
		Document document = null;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Granule.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
			// Create output Document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
			marshaller.marshal(granule, document);
			// marshaller.marshal(granule, System.out);

		} catch (JAXBException | ParserConfigurationException e) {
			e.printStackTrace();
			LOG.error(e.toString());

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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOG.debug("End of  createDocXMLFromGranule");
		return document;
	}

	/**
	 * create XML file From Granule Document
	 * 
	 * @param document
	 * @param fileName
	 */
	public void createXMLfileFromGranuleDoc(Document document, String fileName) {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		LOG.debug("End of  createXMLfileFromGranuleDoc");
	}

	/**
	 * Create a xmlFile on hard Disk root from a Granule object
	 * 
	 * @param granule the granule object enttry
	 * @param fileName the output fileName with/without filepath and ! without
	 *            extension .xml
	 */
	public void createXmlFileFromGranule(Granule granule, String fileName) {

		LOG.debug("Start of  createXmlFileFromGranule");

		JAXBContext context;

		try {
			context = JAXBContext.newInstance(Granule.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
			// marshaller.marshal(granule, System.out);
			OutputStream os = new FileOutputStream(fileName + ".xml");
			marshaller.marshal(granule, os);

		} catch (JAXBException | FileNotFoundException | FactoryConfigurationError e) {
			e.printStackTrace();
			LOG.error(e.toString());

		}
		LOG.debug("End of  createXmlFileFromGranule");
	}

	/**
	 * marshal a Granule object to a xml String
	 * 
	 * @param granule
	 * @param fileName
	 */
	public String marshalGranuleToXMLString(Granule granule) {

		LOG.debug("Start of  marshalGranuleToXMLString");

		JAXBContext context;
		String xmlString = null;
		try {
			context = JAXBContext.newInstance(Granule.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N

			StringWriter sw = new StringWriter();
			marshaller.marshal(granule, sw);
			xmlString = sw.toString();

		} catch (JAXBException | FactoryConfigurationError e) {
			e.printStackTrace();
			LOG.error(e.toString());

		}
		LOG.debug("End of  marshalGranuleToXMLString");
		return xmlString;

	}

	/**
	 * Convert a Granule Xml from CMR to a Granule JaxB Object
	 * 
	 * @param document The Granule.xml
	 * @return Granule java class
	 */
	public Granule createGranuleFromXML(Document document) {
		LOG.debug("Start of createGranuleFromXML ");

		Granule granule2 = new Granule();
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Granule.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			granule2 = (Granule) jaxbUnmarshaller.unmarshal(document);
		} catch (JAXBException e) {
			LOG.error(e.toString());
			e.printStackTrace();
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

		JAXBContext jaxbContext = JAXBContext.newInstance(Granule.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
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
	 */
	public Document createDocXMLFromCollection(Collection collection) {
		// Create the Document
		LOG.debug("Start of  createDocXMLFromCollection");
		Document document = null;
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Collection.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			// Create output Document
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.newDocument();
			marshaller.marshal(collection, document);
			// marshaller.marshal(collection, System.out);

		} catch (JAXBException | ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOG.error(e.toString());

		}
		LOG.debug("End of  createDocXMLFromCollection");
		return document;
	}

	/**
	 * Convert a collection Xml from CMR to a collection JaxB Object
	 * 
	 * @param document The collection.xml
	 * @return collection java class
	 */
	public Collection createCollectionFromXML(Document document) {
		LOG.debug("Start of  createCollectionFromXML");
		ArrayList<String> xmlString = xmlToolBox.getTagNameTextContentFromXML(document, "Collection");

		// document = xmlToolBox.convertStringToDocument(xmlString.get(0));

		xmlToolBox.createXmlFileFromDoc(document, Common.getPropertiesValue("Download.FilePath") + "fileName");
		Collection collection = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Collection.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			collection = (Collection) jaxbUnmarshaller.unmarshal(document);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			LOG.debug(e.toString());
			e.printStackTrace();
		}
		LOG.debug("End of createCollectionFromXML");
		return collection;
	}

}
