package com.esa.bmap.service.catalogue.implement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.services.CMR.DataRepositoryCMRInterface;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.service.catalogue.interfaces.DataCatalogServiceCMRInterface;

/**
 * DataCatalogueServiceCMRInterface
 * 
 * @author THBLED
 *
 */
@Service(value = "DataCatalogueServiceCMRInterface")
public class DataCatalogServiceCMRImpl implements DataCatalogServiceCMRInterface {
	private static final Logger LOG = LoggerFactory.getLogger(DataCatalogServiceCMRImpl.class);

	@Autowired
	private DataRepositoryCMRInterface dataRepositoryCMR;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Granule addGranule(Granule granule) throws BmapException {
		if (granule.getDataList().size() > 0) {
			dataRepositoryCMR.save(granule);
		} else {
			LOG.info("Granule without data detected : " + granule.getName());
			dataRepositoryCMR.save(granule);
		}
		// child objects
		return granule;
	}

	/**
	 * Delete all data.
	 * 
	 * @throws BmapException
	 */
	@Override
	public void deleteAllData(String collectionShortName) throws BmapException {
		dataRepositoryCMR.deleteAllGranuleInCollection(collectionShortName);
	}
	
	/**
	 * Delete all data.
	 * 
	 * @throws BmapException
	 */
	@Override
	public void deleteGranule(String granuleUR) throws BmapException {
		dataRepositoryCMR.deleteGranuleFromCMR( granuleUR);
	}

	/**
	 * Get any data type using criteria.
	 * 
	 * @param bmaapData
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Collection<Granule> getDataByCriteria(GranuleCriteria dataCriteria) throws BmapException {
		Collection<Granule> dataCollection = dataRepositoryCMR.findByCriteria(dataCriteria);
		return dataCollection;
	}

	/**
	 * send the xml of a granule to the CMR api and test the validation
	 */
	@Override
	public void validateGranule() throws BmapException {

	}
	@Override
	public List<String> getBiomassCollections() throws BmapException {
		return dataRepositoryCMR.getBiomassCollections();
	}
	
	// ==========================================================================
	// ==========================================================================
	/**
	 * Test the connection to the cmr server return a boolean
	 */
	@Override
	public boolean testConnectionTimeOut(int timeOut, boolean doTest) {

		LOG.info("Start of testConnectionTimeOut Class");
		if (doTest) {

			URL uneURL = null;

			try {

				uneURL = new URL(Common.getPropertiesValue("Request.Adresses.Base"));
				HttpURLConnection connexion = (HttpURLConnection) uneURL.openConnection();
				connexion.setConnectTimeout(timeOut);

				if (connexion.getResponseCode() == HttpURLConnection.HTTP_OK) {

				}
				connexion.disconnect();

			} catch (Exception SocketTimeoutException) {

				LOG.error("CMR services is unreachable ! Connection time Out ! ");
				return false;

			}

		}
		return true;
	}
}
