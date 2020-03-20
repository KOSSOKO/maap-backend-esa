package com.esa.bmap.service.catalogue.data.interfaces;

import java.io.File;

import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.DataFormat;
import com.esa.bmap.model.DataFormatPrivate;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.Polarization;

/**
 * @author edupin
 *
 */
public interface GroundCampaignReaderServiceInterface {

	/**
	 * Extract shapefile shp File metadata and creates an ROI object
	 * 
	 * @param path path to the shapefile .shp File from which the metadata are
	 *            extracted
	 * @param fileName name of the file
	 * @param subregion name of the sub-region
	 * @param GroundCampaign GroundCampaign of the ROI
	 * @param baseDir Parent dir of the ROI file
	 * @param BmaapUser the creator of the granule (can be null)
	 * @throws BmapException
	 * @throws FactoryException
	 * @throws NoSuchAuthorityCodeException
	 */
	public Granule retrieveROI(String path, String roiFileName, String subregionName, Collection collection,
			String baseDir, BmaapUser author) throws BmapException, NoSuchAuthorityCodeException, FactoryException;

	/**
	 * Creates AirbornData object from the path to one tiff file
	 * 
	 * @param path the path to the .tiff
	 * @param fileName tiff's name
	 * @param date Date and time of acquisition
	 * @param region SubRegion
	 * @param Collection collection of the data
	 * @param dataType data Type of the data (i.e: rg, az, kz)
	 * @throws FactoryException
	 * @return the instance of Airborn Data created
	 * @throws BmapException
	 */
	Granule retrieveGeotiff(File file, String f_AcquisitionDate, String fileName, String dataFormat,
			Granule granuleScene) throws BmapException;

	/**
	 * Creates AirbornData object from the path to one tiff file
	 * 
	 * @param path the path to the .tiff
	 * @param fileName tiff's name
	 * @param date Date and time of acquisition
	 * @param region SubRegion
	 * @param collection GroundCampaign of the data
	 * @param dataType data Type of the data (i.e: rg, az, kz)
	 * @throws FactoryException
	 * @return the instance of Airborn Data created
	 * @throws BmapException
	 */
	Granule retrieveWorldImage(File file, String f_date, String fileName, String dataFormat, Granule granuleScene)
			throws BmapException;

	/**
	 * Ingest az, rg .tiff files to database and geoserver
	 * 
	 * @param file
	 * @param f_heading
	 * @param f_zflight
	 * @param f_zTerrain
	 * @param f_slrStart
	 * @param f_pixelSpacing
	 * @param f_surfaceResol
	 * @param f_GRDResol
	 * @param f_date
	 * @param f_dem
	 * @param f_master
	 * @param f_subRegion
	 * @param fileName
	 * @param collection
	 * @param dataType
	 * @param instrumentName
	 * @return Granule
	 * @throws BmapException
	 */
	public Granule ingestGeotiff(File file, String f_AcquisitionDate, String fileName, String dataType,
			Granule granuleScene) throws BmapException;

	/**
	 * Ingest SLC, kz inc or dem tiff files to database and geoserver
	 * 
	 * @param file
	 * @param f_AcquisitionDate
	 * @param fileName
	 * @param dataType
	 * @param granuleScene
	 * @throws BmapException
	 */
	public void ingestWorldImage(File file, String f_AcquisitionDate, String fileName, String dataType,
			Granule granuleScene) throws BmapException;

	/**
	 * for a given tag scene, searches all the tiff files and gives it to
	 * retrieveAirbornData private method
	 * 
	 * @param granuleScene
	 * @param f_AcquisitionDate
	 * @param campaignName
	 * @param folderName
	 * @param instrumentName
	 * @throws BmapException
	 */
	void searchAirbornFile(Granule granuleScene, String f_AcquisitionDate, String campaignName, String folderName,
			String instrumentName) throws BmapException;

	/**
	 * We ingest a data knowing its' path and userId
	 * 
	 * @param dataPath
	 * @param subregionName
	 * @param polarization
	 * @param dataFormat
	 * @param collectionName
	 * @param BmaapUser
	 * @throws BmapException
	 */
	public void ingestPrivateData(String dataPath, String subregionName, Polarization polarization,
			DataFormatPrivate dataFormat, String collectionName, BmaapUser bmaapUser) throws BmapException;

	/**
	 * Save new Granule Data or Update existing granule in Database
	 * 
	 * @param granule
	 * @throws BmapException
	 */
	public Granule saveOrUpdateGranuleData(Granule granule) throws BmapException;
}
