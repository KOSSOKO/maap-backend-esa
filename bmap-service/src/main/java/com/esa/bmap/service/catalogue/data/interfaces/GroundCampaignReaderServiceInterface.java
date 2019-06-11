package com.esa.bmap.service.catalogue.data.interfaces;

import java.io.File;
import java.util.ArrayList;

import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.w3c.dom.Element;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.Collection;

/**
 * @author edupin
 *
 */
public interface GroundCampaignReaderServiceInterface {

	/**
	 * Extract shapefile shp File metadata and creates an ROI object
	 * 
	 * @param path
	 *            path to the shapefile .shp File from which the metadata are
	 *            extracted
	 * @param fileName
	 *            name of the file
	 * @param roiName
	 *            name of the Region of Interest
	 * @param subregion
	 *            name of the sub-region
	 * @param GroundCampaign
	 *            GroundCampaign of the ROI
	 * @param baseDir
	 *            Parent dir of the ROI file
	 * @throws BmapException
	 * @throws FactoryException 
	 * @throws NoSuchAuthorityCodeException 
	 */
	void retrieveROI(String path, String fileName, String roiName, String subregion, Collection collection,
			String baseDir) throws BmapException, NoSuchAuthorityCodeException, FactoryException;

	/**
	 * Creates AirbornData object from the path to one tiff file
	 * 
	 * @param path
	 *            the path to the .tiff
	 * @param fileName
	 *            tiff's name
	 * @param date
	 *            Date and time of acquisition
	 * @param region
	 *            SubRegion
	 * @param Collection
	 *            collection of the data
	 * @param dataType
	 *            data Type of the data (i.e: rg, az, kz)
	 * @throws FactoryException
	 * @return the instance of Airborn Data created
	 * @throws BmapException
	 */
	Granule retrieveGeotiff(File file, String f_heading, String f_zflight, String f_zTerrain, String f_slrStart,
			String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date, String f_dem,
			String f_master, String f_subRegion, String fileName, Collection collection, String dataType,
			String instrumentName, Granule granuleScene) throws BmapException;

	/**
	 * Creates AirbornData object from the path to one tiff file
	 * 
	 * @param path
	 *            the path to the .tiff
	 * @param fileName
	 *            tiff's name
	 * @param date
	 *            Date and time of acquisition
	 * @param region
	 *            SubRegion
	 * @param collection
	 *            GroundCampaign of the data
	 * @param dataType
	 *            data Type of the data (i.e: rg, az, kz)
	 * @throws FactoryException
	 * @return the instance of Airborn Data created
	 * @throws BmapException
	 */
	Granule retrieveWorldImage(File file, String f_heading, String f_zflight, String f_zTerrain, String f_slrStart,
			String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date, String f_dem,
			String f_master, String f_subRegion, String fileName, Collection collection, String dataType,
			String instrumentName, Granule granuleScene) throws BmapException;

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
	public Granule ingestGeotiff(File file, String f_heading, String f_zflight, String f_zTerrain, String f_slrStart,
			String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date, String f_dem,
			String f_master, String f_subRegion, String fileName, Collection collection, String dataType,
			String instrumentName, Granule granuleScene) throws BmapException;

	/**
	 * Ingest SLC, kz inc or dem tiff files to database and geoserver
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
	 * @throws BmapException
	 */
	public void ingestWorldImage(File file, String f_heading, String f_zflight, String f_zTerrain, String f_slrStart,
			String f_pixelSpacing, String f_surfaceResol, String f_GRDResol, String f_date, String f_dem,
			String f_master, String f_subRegion, String fileName, Collection collection, String dataType,
			String instrumentName, Granule granuleScene) throws BmapException;

	/**
	 * for a given tag scene, searches all the tiff files and gives it to
	 * retrieveAirbornData private method
	 * 
	 * @param eElement
	 *            it's
	 * @param baseDir
	 * @throws FactoryException
	 * @throws BmapException
	 */
	void searchAirbornFile(Element eElement, String campaignName, String folderName, Collection collection,
			String instrumentName) throws BmapException;


}
