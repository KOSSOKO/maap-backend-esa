package com.esa.bmap.service.catalogue.data.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.dao.catalogue.data.DataDao;
import com.esa.bmap.dao.catalogue.data.interfaces.CollectionRepository;
import com.esa.bmap.dao.catalogue.data.interfaces.DataRepository;
import com.esa.bmap.dao.catalogue.data.interfaces.GranuleRepository;
import com.esa.bmap.dao.catalogue.data.interfaces.InstrumentRepository;
import com.esa.bmap.dao.catalogue.data.interfaces.PlatformRepository;
import com.esa.bmap.dao.catalogue.data.interfaces.SubRegionRepository;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.Instrument;
import com.esa.bmap.model.Platform;
import com.esa.bmap.model.SubRegion;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
import com.esa.bmap.service.catalogue.implement.DataCatalogServiceCMRImpl;

/**
 * DataCatalogueServiceImpl
 * 
 * @author QFAURE
 *
 */
@Service(value = "DataCatalogueServiceInterface")
public class DataCatalogServiceImpl implements DataCatalogServiceInterface {

	private static final Logger LOG = LoggerFactory.getLogger(DataCatalogServiceInterface.class);

	/**
	 * Set the properties to true to enable the ingestion into CMR
	 */
	@Value("${CMR.ingestion.enable}")
	private boolean enableCMRIngestion;

	/**
	 * Set the properties to true to enable the ingestion into CMR
	 */
	@Value("${CMR.search.enable}")
	private boolean enableCMRSearch;

	@Autowired
	private DataCatalogServiceCMRImpl dataCatalogServiceCMRImpl;

	@Autowired
	private DataRepository<Data> dataDao;

	@Autowired
	private GranuleRepository<Granule> granuleDao;

	@Autowired
	private CollectionRepository collectionDao;

	@Autowired
	private PlatformRepository platformDao;

	@Autowired
	private SubRegionRepository subRegionDao;

	@Autowired
	private InstrumentRepository instrumentDao;

	@Autowired
	private DataDao dataDaoCustom;

	/**
	 * Add data to database.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Data addData(Data data) throws BmapException {

		return this.dataDao.save(data);
	}

	/**
	 * Add data to database.
	 * 
	 * @param bmaapData
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Granule addGranule(Granule granule) throws BmapException {

		// child objects
		SubRegion subRegion = null;
		Collection collection = null;
		Platform platform = null;

		LOG.info("Start of addData");
		boolean connectionCmr = isCMRConnectionAvailable();

		// if connection CMR is ok proceed to ingest:
		if (connectionCmr) {
			LOG.info("CMR is responding");
			// ingest in the CMR api :
			dataCatalogServiceCMRImpl.addData(granule);
		} else {
			throw new BmapException();
		}

		// ingest in the local database
		switch (granule.getCollection().getCategoryKeyWords()) {
		case Collection.COLLECTION_TYPE_GROUND_CAMPAIGN:

			// get the child objects from the database
			if (granule.getCollection() != null) {
				collection = this.collectionDao.findByShortName(((Granule) granule).getCollection().getShortName());
			}
			if (granule.getSubRegion() != null) {
				subRegion = this.subRegionDao.findByName(((Granule) granule).getSubRegion().getName());
			}
			if (granule.getPlatform() != null) {
				platform = this.platformDao.findByName(((Granule) granule).getPlatform().getName());
			}

			// if the child objects don't exist in the database, save them
			// otherwise, get them from the database and set them in the
			// data to save

			if (collection == null) {
				this.collectionDao.save(((Granule) granule).getCollection());
			} else {
				((Granule) granule).setCollection(collection);
			}

			if (subRegion == null) {
				this.subRegionDao.save(((Granule) granule).getSubRegion());
			} else {
				((Granule) granule).setSubRegion(subRegion);
			}
			if (platform == null && granule.getGranuleScene() == null) {
				if (granule.getPlatform().getListInstrument() != null) {
					for (int i = 0; i < granule.getPlatform().getListInstrument().size(); i++) {
						Instrument instrument = this.instrumentDao
								.findByName(granule.getPlatform().getListInstrument().get(i).getName());
						if (instrument == null) {
							this.instrumentDao.save(granule.getPlatform().getListInstrument().get(i));
						}
					}
				}

				this.platformDao.save(((Granule) granule).getPlatform());

			} else {
				((Granule) granule).setPlatform(platform);
			}

			break;

		case "Satellite Data":

			break;

		case "L2 official Products":

			break;

		}

		granule = this.granuleDao.save(granule);

		return granule;

	}

	/**
	 * Add sub region to database.
	 * 
	 * @param subRegion
	 * @return
	 * @throws BmapException
	 */
	@Override
	public SubRegion addSubRegion(SubRegion subRegion) throws BmapException {
		return this.subRegionDao.save(subRegion);
	}

	/**
	 * Get data by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Data getDataById(long id) throws BmapException {
		Optional<Data> data = this.dataDao.findById(id);
		if (data.isPresent()) {
			return data.get();
		} else {
			return null;
		}
	}

	/**
	 * Get granule by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Granule getGranuleById(long id) throws BmapException {
		Optional<Granule> data = this.granuleDao.findById(id);
		if (data.isPresent()) {
			return data.get();
		} else {
			return null;
		}
	}

	/**
	 * Get ground campaign by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	@Override
	public Collection getCollectionById(long id) throws BmapException {
		Optional<Collection> collection = this.collectionDao.findById(id);
		if (collection.isPresent()) {
			return collection.get();
		} else {
			return null;
		}
	}

	/**
	 * Get sub region by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	@Override
	public SubRegion getSubRegionById(long id) throws BmapException {
		Optional<SubRegion> subRegion = this.subRegionDao.findById(id);
		if (subRegion.isPresent()) {
			return subRegion.get();
		} else {
			return null;
		}
	}

	/**
	 * Delete all data. TODO check the validity of the method
	 * 
	 * @throws BmapException
	 */
	public void deleteAllData() throws BmapException {
		this.dataDao.deleteAll();
	}

	/**
	 * Delete all ground campaign.
	 * 
	 * @throws BmapException
	 */
	@Override
	public void deleteAllCollection() throws BmapException {
		this.collectionDao.deleteAll();
	}

	/**
	 * Delete all sub region. TODO check the validity of the method
	 * 
	 * @throws BmapException
	 */
	public void deleteAllSubRegion() throws BmapException {
		this.subRegionDao.deleteAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface
	 * #deleteDataByGroundCampaignName(java.lang.String)
	 */
	@Override
	public void deleteDataByGroundCampaignName(String groundCampaignName) throws BmapException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface
	 * #deleteData(com.esa.bmap.model.Data)
	 */
	@Override
	public void deleteData(Data data) throws BmapException {
		// TODO Auto-generated method stub

	}

	/**
	 * Get any data type using criteria.
	 * 
	 * @param bmaapData
	 * @return
	 * @throws BmapException
	 */
	@Override
	public java.util.Collection<Granule> getGranuleByCriteria(GranuleCriteria granuleCriteria) throws BmapException {

		LOG.info("Start of getCranuleByCriteria");
		java.util.Collection<Granule> listGranule;
		// test the connection to the cmr service :
		boolean connectionCmr = isCMRConnectionAvailable();

		// if connection CMR is ok proceed to ingest:
		if (enableCMRSearch) {
			if (connectionCmr) {
				LOG.info("connectionCmr = true");

				// search in the CMR api :
				listGranule = dataCatalogServiceCMRImpl.getDataByCriteria(granuleCriteria);
			} else {
				listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
			}
		} else {
			// only search in local database
			listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
		}
		LOG.info("End of getCranuleByCriteria");
		return listGranule;
	}

	/**
	 * Test the connectivity with the CMR api.
	 * 
	 * @return a boolean
	 */
	private boolean isCMRConnectionAvailable() {
		Boolean bool = true;
		switch (Common.getPropertiesValue("Request.Test.Nasa.Connection.TimeOut.Boolean").toLowerCase()) {
		case "true":
			bool = true;
			break;
		case "false":
			bool = false;
			break;
		}
		int timer = Integer.parseInt(Common.getPropertiesValue("Request.Test.Nasa.Connection.TimeOut"));
		boolean connectionCmr = dataCatalogServiceCMRImpl.testConnectionTimeOut(timer, bool);
		return connectionCmr;
	}

	@Override
	public Collection addCollection(Collection collection) throws BmapException {

		return this.collectionDao.save(collection);
	}

	/**
	 * Get granule by its name.
	 * 
	 * @param granuleName (collection/granuleUR)
	 * @return Granule
	 * @throws BmapException
	 */
	@Override
	public Granule getGranuleByGranuleName(String granuleName) throws BmapException {
		LOG.info("test");
		LOG.info(granuleName);
		GranuleCriteria granuleCriteria = new GranuleCriteria();
		String[] granuleString = granuleName.split(":@");
		List<String> collectionNames = new ArrayList<String>();
		collectionNames.add(granuleString[0]);
		String granuleShortName = granuleString[1];
		granuleCriteria.setCollectionNames(collectionNames);
		granuleCriteria.setGranuleName(granuleShortName);

		LOG.info("Start of getCranuleByCriteria");
		java.util.Collection<Granule> listGranule;
		// test the connection to the cmr service :
		boolean connectionCmr = isCMRConnectionAvailable();

		// if connection CMR is ok proceed to ingest:
		if (enableCMRSearch) {
			if (connectionCmr) {
				LOG.info("connectionCmr = true");

				// search in the CMR api :
				listGranule = dataCatalogServiceCMRImpl.getDataByCriteria(granuleCriteria);
			} else {
				listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
			}
		} else {
			// only search in local database
			listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
		}
		LOG.info("End of getCranuleByCriteria");
		return listGranule.iterator().next();
	}

}
