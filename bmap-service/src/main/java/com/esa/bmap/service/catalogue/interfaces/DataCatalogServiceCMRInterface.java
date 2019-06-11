package com.esa.bmap.service.catalogue.interfaces;

import java.util.Collection;

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
	 * Add data to database.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	Granule addData(Granule data) throws BmapException;

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
}
