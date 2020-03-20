package com.esa.bmap.service.acquisition.implement;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
import com.esa.bmap.model.Instrument;
import com.esa.bmap.model.Platform;
import com.esa.bmap.model.Privacy;
import com.esa.bmap.model.Status;
import com.esa.bmap.model.SubRegion;
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
	 * Scenes xml node
	 */
	private static final String SCENES_NODE = "Scenes";

	/**
	 * Scene xml node
	 */
	private static final String SCENE_NODE = "Scene";

	/**
	 * ROI xml node
	 */
	private static final String ROI_NODE = "ROI";
	/**
	 * Instrument xml attribute
	 */
	private static final String INSTRUMENT_ATTR = "instrument";

	/**
	 * name xml attribute
	 */
	private static final String NAME_ATTR = "name";
	/**
	 * heading xml attribute
	 */
	private static final String HEADING_ATTR = "heading";
	/**
	 * zflight xml attribute
	 */
	private static final String Z_FLIGHT_ATTR = "z_flight";
	/**
	 * zterrain xml attribute
	 */
	private static final String Z_TERRAIN_ATTR = "z_terrain";
	/**
	 * slr start xml attribute
	 */
	private static final String SLR_START_ATTR = "SLR_start";
	/**
	 * pixel_spacing xml attribute
	 */
	private static final String PIXEL_SPACING_ATTR = "pixel_spacing";
	/**
	 * surface_resol xml attribute
	 */
	private static final String SURFACE_RESOL_ATTR = "surface_resol";
	/**
	 * GRD_resol xml attribute
	 */
	private static final String GRD_RESOL_ATTR = "GRD_resol";
	/**
	 * date xml attribute
	 */
	private static final String DATE_ATTR = "date";
	/**
	 * dem xml attribute
	 */
	private static final String DEM_ATTR = "dem";
	/**
	 * master xml attribute
	 */
	private static final String MASTER_ATTR = "master";
	/**
	 * subregion xml attribute
	 */
	private static final String SUBREGION_ATTR = "subregion";

	/**
	 * roi file delimiter
	 */
	private static final String ROI_FILE_DELIMITER = "_roi_";

	/**
	 * Main Function : Parses the XML parameter file, and indexes all the data
	 * 
	 * @param path path to the folder which contains all the data (All the .tif /
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
			Collection collection = new Collection(campaignName, campaignName, Collection.VERSION_ID_1, geoserverurl,
					Collection.COLLECTION_TYPE_GROUND_CAMPAIGN, Collection.PROCESSING_LEVEL_1);

			doc.getDocumentElement().normalize();

			// Getting all the nodes relative to Airborn Data (Scene) and Region of Interest
			// (ROI)
			NodeList SceneList = doc.getElementsByTagName(SCENE_NODE);
			NodeList ROIList = doc.getElementsByTagName(ROI_NODE);
			String instrumentName = doc.getElementsByTagName(SCENES_NODE).item(0).getAttributes()
					.getNamedItem(INSTRUMENT_ATTR).getNodeValue();

			LOG.info("------AIRBORN DATA------\n");

			// For each Scene tag, calling the private method to build a list of AirbornData
			for (int temp = 0; temp < SceneList.getLength(); temp++) {
				Node nNode = SceneList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					// We extract the xml file
					// Getting attributes from Granule Scene node
					String f_scene_name = eElement.getAttribute(NAME_ATTR);
					String f_heading = eElement.getAttribute(HEADING_ATTR);
					String f_zflight = eElement.getAttribute(Z_FLIGHT_ATTR);
					String f_zTerrain = eElement.getAttribute(Z_TERRAIN_ATTR);
					String f_slrStart = eElement.getAttribute(SLR_START_ATTR);
					String f_pixelSpacing = eElement.getAttribute(PIXEL_SPACING_ATTR);
					String f_surfaceResol = eElement.getAttribute(SURFACE_RESOL_ATTR);
					String f_GRDResol = eElement.getAttribute(GRD_RESOL_ATTR);
					String f_AcquisitionDate = eElement.getAttribute(DATE_ATTR);
					String f_dem = eElement.getAttribute(DEM_ATTR);
					String f_master = eElement.getAttribute(MASTER_ATTR);
					String f_subRegion = eElement.getAttribute(SUBREGION_ATTR);
					LocalDateTime updateDate = LocalDateTime.now();

					// Creating child objects
					SubRegion subRegion = new SubRegion(f_subRegion);
					// Create Platform with instrument Name
					List<Instrument> instrumentList = new ArrayList<Instrument>();
					Instrument instrument = new Instrument(instrumentName, "");
					instrumentList.add(instrument);
					Platform platform = new Platform(Platform.GROUND_CAMPAIGN_PLATFORM,
							Platform.GROUND_CAMPAIGN_PLATFORM, instrumentList);

					// With all these information, we build a granule scene
					// Creating Granule Scene
					Granule granuleScene = new Granule(f_scene_name, f_heading, f_zflight, f_zTerrain, f_slrStart,
							f_pixelSpacing, f_surfaceResol, f_GRDResol, updateDate, f_dem, f_master, platform, null,
							collection, subRegion, new ArrayList<String>());
					// The data is private and in the state collaborate
					granuleScene.setPrivacy(Privacy.PUBLIC);
					granuleScene.setStatus(Status.SHARED);
					groundCampaignReader.searchAirbornFile(granuleScene, f_AcquisitionDate, campaignName, folderName,
							instrumentName);
				}
			}

			// For each ROI tag, calling the private method to build an ROI
			LOG.info("------SHAPEFILE------\n");

			for (int temp = 0; temp < ROIList.getLength(); temp++) {
				Node nNode = ROIList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					String dataName = campaignName + ROI_FILE_DELIMITER + eElement.getAttribute(NAME_ATTR);

					String shpFile = dataName + Granule.EXTENSION_SHP;

					String roiSubRegionName = eElement.getAttribute(SUBREGION_ATTR);

					// Creating Scene Granule and ingest in database

					// setting of the ROI object
					Granule granule = groundCampaignReader.retrieveROI(baseDir + "/" + shpFile, dataName,
							roiSubRegionName, collection, baseDir, null);
					groundCampaignReader.saveOrUpdateGranuleData(granule);

				}
			}

		} catch (Exception e) {
			LOG.info(e.getMessage(), e);
			LOG.error(Common.getMessageValue("groundcampaignacquisition.main.error"));
			throw new BmapException(Common.getMessageValue("groundcampaignacquisition.main.error"));
		}
	}

}
