package com.esa.bmap.external.services.CMR;

import java.util.Collection;
import java.util.List;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.GranuleCriteria;

public interface DataRepositoryCMRInterface {

	public abstract void downloadTheDataFromS3(String TargetUrl, String Filename) throws BmapException;

	public abstract void downloadTheData(String TargetUrl, String Filename) throws BmapException;

	public abstract boolean deleteAllGranuleInCollection(String CollectiondataSetId) throws BmapException;

	public abstract void save(com.esa.bmap.model.Granule bmaapGranule) throws BmapException;

	public abstract com.esa.bmap.external.model.cmr.collections.Collection getCollectionCMR(String collectionShortName)
			throws BmapException;

	public abstract Collection<com.esa.bmap.model.Granule> findByCriteria(GranuleCriteria dataCriteria)
			throws BmapException;

	/**
	 * Get a collection by its dataSetId
	 * 
	 * @param conceptId
	 * @return
	 * @throws BmapException
	 */
	com.esa.bmap.external.model.cmr.collections.Collection getCollectionByDataSetId(String conceptId)
			throws BmapException;

	/**
	 * Delete a granule from CMR
	 * 
	 * @param CollectiondataSetId
	 * @return
	 * @throws BmapException
	 */
	boolean deleteGranuleFromCMR(String CollectiondataSetId) throws BmapException;

	/**
	 * method to get all biomass collections
	 * 
	 * @return
	 * @throws BmapException
	 */
	List<String> getBiomassCollections() throws BmapException;

}
