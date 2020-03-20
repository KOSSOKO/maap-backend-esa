package com.esa.bmap.service.catalogue.data.interfaces;

import java.util.List;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.SubRegion;

/**
 * DataCatalogueServiceInterface
 * 
 * @author QFAURE
 *
 */
public interface DataCatalogServiceInterface {

	/**
	 * Add Granule to database.
	 * 
	 * @param granule
	 * @return
	 * @throws BmapException
	 */
	Granule addGranule(Granule granule) throws BmapException;

	/**
	 * Add user Granule to database
	 * 
	 * @param dataPath
	 * @param userId
	 * @return
	 * @throws BmapException
	 */
	Granule addUserGranule(String dataPath, long userId) throws BmapException;

	/**
	 * Share User granule - Update privacy and status of the granule to respectively
	 * PUBLIC and SHARED
	 * 
	 * @param granuleUR unique id of the granule as so : CollectionName:@granuleName
	 * @return Updated Granule
	 * @throws BmapException
	 */
	Granule shareUserGranule(String granuleUR) throws BmapException;

	/**
	 * Add data to database.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	Data addData(Data data) throws BmapException;

	/**
	 * Add Collection to database.
	 * 
	 * @param collection
	 * @return
	 * @throws BmapException
	 */
	Collection addCollection(Collection collection) throws BmapException;

	/**
	 * Delete data by ground campaign name.
	 * 
	 * @param groundCampaignName
	 * @throws BmapException
	 */
	void deleteDataByGroundCampaignName(String groundCampaignName) throws BmapException;

	/**
	 * Add sub region to database.
	 * 
	 * @param subRegion
	 * @return
	 * @throws BmapException
	 */
	SubRegion addSubRegion(SubRegion subRegion) throws BmapException;

	/**
	 * Get granule by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	Granule getGranuleById(long id) throws BmapException;

	/**
	 * Get data by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	Data getDataById(long id) throws BmapException;

	/**
	 * Get sub region by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	SubRegion getSubRegionById(long id) throws BmapException;

	/**
	 * @param id
	 * @return Collection
	 * @throws BmapException
	 */
	Collection getCollectionById(long id) throws BmapException;

	/**
	 * @param shortNamed
	 * @return Collection
	 * @throws BmapException
	 */
	Collection getCollectionByShortName(String shortName) throws BmapException;

	/**
	 * Delete data.
	 * 
	 * @param data
	 * @throws BmapException
	 */
	void deleteData(Data data) throws BmapException;

	/**
	 * Delete all data.
	 * 
	 * @throws BmapException
	 */
	void deleteAllData() throws BmapException;

	/**
	 * Delete all Collections.
	 * 
	 * @throws BmapException
	 */
	void deleteAllCollection() throws BmapException;

	/**
	 * Get granule by its name
	 * 
	 * @param granuleName (collection/granuleUR)
	 * @return Granule
	 * @throws BmapException
	 */
	Granule getGranuleByGranuleName(String granuleName) throws BmapException;

	/**
	 * Get granule by its name in dataBase
	 * 
	 * @param granuleName (collection/granuleUR)
	 * @return Granule
	 * @throws BmapException
	 */
	Granule getGranuleByGranuleNameDataBase(String granuleName) throws BmapException;

	/**
	 * We move the data to the dedicated repository before ingestion
	 * 
	 * @param dataPath
	 * @param user
	 * @return
	 * @throws BmapException
	 */
	String movePrivateDataBeforeIngestion(String dataPath, BmaapUser user) throws BmapException;

	/**
	 * Get any data type using criteria.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	java.util.Collection<Granule> getGranuleByCriteria(GranuleCriteria granuleCriteria) throws BmapException;

	/**
	 * Add a private Granule in Database
	 * 
	 * @param granule object to add to database
	 * @return
	 * @throws BmapException
	 */
	Granule addPrivateGranule(Granule granule) throws BmapException;

	/**
	 * Return All Collection of dataBase
	 * 
	 * @return
	 * @throws BmapException
	 */
	Iterable<Collection> getAllCollection() throws BmapException;

	/**
	 * Return All Granules of dataBase
	 * 
	 * @return
	 * @throws BmapException
	 */
	Iterable<Granule> getAllGranules() throws BmapException;

	/**
	 * Delete granule by its granuleUR
	 * 
	 * @return granule
	 * @throws BmapException
	 */
	void deleteGranuleByGranuleUR(String granuleUR) throws BmapException;

	/**
	 * Delete granule by its id
	 * 
	 * @return granule
	 * @throws BmapException
	 */
	void deleteGranuleById(long id) throws BmapException;

	/**
	 * Delete granule from CMR by its granuleUR
	 * 
	 * @param granuleUR
	 * @throws BmapException 
	 */
	void deleteGranuleByGranuleURFromCMR(String granuleUR) throws BmapException;

	/**
	 * Get all Biomass Collection names
	 * @return
	 * @throws BmapException
	 */
	List<String> getBiomassCollections() throws BmapException;

}
