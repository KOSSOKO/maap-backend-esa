package com.esa.bmap.controller;

import java.io.File;
import java.io.FilenameFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.service.acquisition.interfaces.AcquisitionInterface;

@RestController
@RequestMapping(value = "/catalogue/groundCampaignAcquisition")
public class GroundCampaignReaderController {

	@Autowired
	private AcquisitionInterface dataReader;

	@Value("${datasource.dataDirectory}")
	private String dataDirectory;

	private static final Logger LOG = LoggerFactory.getLogger(GroundCampaignReaderController.class);

	/**
	 * Main Function : Web service which launches the indexation of all the data
	 * 
	 * @throws BmapException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void ingest() throws BmapException {

		File dataDir = new File(dataDirectory);

		if (dataDir.exists() && dataDir.isDirectory()) {

			File[] fileList = dataDir.listFiles();

			for (File currentFile : fileList) {

				if (currentFile.isDirectory()) {

					FilenameFilter filter = new FilenameFilter() {
						@Override
						public boolean accept(File dir, String name) {
							return name.endsWith("modified.xml");
						}
					};

					File[] xmlFileList = currentFile.listFiles(filter);

					if (xmlFileList.length == 1) {

						File xmlFile = xmlFileList[0];
						String folderName = currentFile.getAbsolutePath();

						LOG.info("XML file found: " + xmlFile.getAbsolutePath() + ". Parsing Campaign: " + currentFile.getName() + ", in folder: " + folderName);

						this.dataReader.Main(xmlFile.getAbsolutePath(), currentFile.getName(), folderName);

					} else {

						LOG.error(Common.getMessageValue("groundcampaignreadercontroller.ingest.multiplemodifiedxml"));
						throw new BmapException(Common.getMessageValue("groundcampaignreadercontroller.ingest.multiplemodifiedxml"));
					}
				}
			}

		} else {

			LOG.error(Common.getMessageValue("groundcampaignreadercontroller.ingest.datadirectorynotexist"));
			throw new BmapException(Common.getMessageValue("groundcampaignreadercontroller.ingest.datadirectorynotexist"));
		}
	}
}
