package com.esa.bmap.service.catalogue.data.interfaces;



import com.esa.bmap.common.exceptions.BmapException;
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
	void deleteDataByGroundCampaignName(String groundCampaignName)
			throws BmapException;

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
	 * Get granule by its name.
	 * 
	 * @param granuleName (collection/granuleUR)
	 * @return Granule
	 * @throws BmapException
	 */
	Granule getGranuleByGranuleName(String granuleName) throws BmapException;
	
	/**
	 * Get any data type using criteria.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	java.util.Collection<Granule> getGranuleByCriteria(
			GranuleCriteria granuleCriteria) throws BmapException;

}
