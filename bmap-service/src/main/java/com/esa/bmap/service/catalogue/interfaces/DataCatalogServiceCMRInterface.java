package com.esa.bmap.service.catalogue.interfaces;

import java.util.Collection;
import java.util.List;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;

/**
 * DataCatalogServiceCMRInterface
 * @author THBLED
 *
 */
public interface DataCatalogServiceCMRInterface {

	/**
	 * Add Granule to CMR.
	 * 
	 * @param granule
	 * @return
	 * @throws BmapException
	 */
	Granule addGranule(Granule granule) throws BmapException;

	/**
	 * Delete all data.
	 * 
	 * @throws BmapException
	 */
	void deleteAllData(String collectionShortName) throws BmapException;

	/**
	 * Get any data type using criteria.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	Collection<Granule> getDataByCriteria(GranuleCriteria dataCriteria) throws BmapException;
	
	/**
	 * Test the connection between Biomass and the CMR platforms
	 * @throws BmapException
	 */
	boolean testConnectionTimeOut(int timeOut,boolean doTest) throws BmapException;
	
	/**
	 * send the xml of a granule to the CMR api and test the validation 
	 */
	void validateGranule()throws BmapException;

	/**
	 * Delete a granule from cmr
	 * @param granuleUR
	 * @throws BmapException
	 */
	void deleteGranule(String granuleUR) throws BmapException;

	/**
	 * Method to get all biomass collections
	 * @return 
	 * @throws BmapException
	 */
	List<String> getBiomassCollections() throws BmapException;
}
