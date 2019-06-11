package com.esa.bmap.service.acquisition.implement;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Granule;
import com.esa.bmap.service.acquisition.interfaces.AcquisitionInterface;
import com.esa.bmap.service.catalogue.data.implement.GroundCampaignReaderServiceImpl;;

@Service(value = "GroundCampaignAcquistionServiceInterface")
public class GroundCampaignAcquisitionImpl implements AcquisitionInterface {

	@Autowired
	private GroundCampaignReaderServiceImpl groundCampaignReader;

	// creating the groundCampaign

	private static final Logger LOG = LoggerFactory.getLogger(GroundCampaignReaderServiceImpl.class);

	@Value("${datasource.dataDirectory}")
	private String dataDirectory;
	@Value("${geoserver.url}")
	private String geoserverurl;
	
	/**
	 * Main Function : Parses the XML parameter file, and indexes all the data
	 * 
	 * @param path
	 *            path to the folder which contains all the data (All the .tif /
	 *            .shp / XMLParamGeneral)
	 * @throws BmapException
	 */
	@Override
	public void Main(String path, String campaignName, String folderName) throws BmapException {
		String baseDir = dataDirectory + "/" + campaignName;

		try {
			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// creation of the ground Campaign Object
			Collection collection = new Collection(campaignName, campaignName, "1", geoserverurl, Collection.COLLECTION_TYPE_GROUND_CAMPAIGN, "L1");

			doc.getDocumentElement().normalize();

			// Getting all the nodes relative to Airborn Data (Scene) and Region of Interest
			// (ROI)
			NodeList SceneList = doc.getElementsByTagName("Scene");
			NodeList ROIList = doc.getElementsByTagName("ROI");
			String instrumentName = doc.getElementsByTagName("Scenes").item(0).getAttributes()
					.getNamedItem("instrument").getNodeValue();

			LOG.info("------AIRBORN DATA------\n");

			// For each Scene tag, calling the private method to build a list of AirbornData
			for (int temp = 0; temp < SceneList.getLength(); temp++) {
				Node nNode = SceneList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					groundCampaignReader.searchAirbornFile(eElement, campaignName, folderName, collection,
							instrumentName);
				}
			}

			// For each ROI tag, calling the private method to build an ROI
			LOG.info("------SHAPEFILE------\n");

			for (int temp = 0; temp < ROIList.getLength(); temp++) {
				Node nNode = ROIList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String dataName = campaignName + "_roi_" + eElement.getAttribute("name");

					String shpFile = dataName + ".shp";

					String roiSubRegionName = eElement.getAttribute("subregion");

					// Creating Scene Granule and ingest in database
					
					
					// setting of the ROI object
					groundCampaignReader.retrieveROI(baseDir + "/" + shpFile, dataName, dataName, roiSubRegionName,
							collection, baseDir);

				}
			}

		} catch (Exception e) {
			LOG.info(e.getMessage());
			LOG.error(Common.getMessageValue("groundcampaignacquisition.main.error"));
			throw new BmapException(Common.getMessageValue("groundcampaignacquisition.main.error"));
		}
	}

}
