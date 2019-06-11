package com.esa.bmap.service.catalogue.interfaces;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.opengis.geometry.Envelope;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Collections;

/**
 * Data ingestion service interface.
 * 
 * @author QFAURE
 *
 */
public interface DataIngestionService {

	/**
	 * Ingests the given scene data files: add to catalog and publish on geoserver.
	 * 
	 *  @param collections
	 * the collections CMR object (name,versionId,datasetId)
	 * @param map
	 * The hashmap files : envelope.
	 * @param groundCampaignName
	 * The ground campaign name.
	 * @param instrumentName
	 * The instrument name.
	 * @param subRegionName
	 * The sub-region name.
	 * @param date
	 * The local date time.
	 * @throws BmapException
	 */
	void ingestSceneDataFiles(Collections collections, HashMap<File, Envelope> map, String groundCampaignName, String instrumentName, String subRegionName, LocalDateTime date,String urlToData) throws BmapException;

	/**
	 * Ingests the given region of interest data files: add to catalog and publish on geoserver.
	 * 
	 * @param collections
	 * the collections CMR object (name,versionId,datasetId)
	 * @param map
	 * The hashmap extension : file.
	 * @param referencedEnvelope
	 * The referenced envelope.
	 * @param groundCampaignName
	 * The ground campaign name.
	 * @param subRegionName
	 * The sub-region name.
	 * @throws BmapException
	 */
	void ingestRoiDataFiles(Collections collections,HashMap<String, File> map, ReferencedEnvelope referencedEnvelope, String groundCampaignName, String subRegionName,String urlToData) throws BmapException;
}
