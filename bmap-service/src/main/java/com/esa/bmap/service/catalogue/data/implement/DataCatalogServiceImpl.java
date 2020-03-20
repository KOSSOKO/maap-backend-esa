package com.esa.bmap.service.catalogue.data.implement;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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
import com.esa.bmap.geoserver.loaders.WorkspacesLoader;
import com.esa.bmap.geoserver.loaders.interfaces.ShapeFileLoaderInterface;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.Instrument;
import com.esa.bmap.model.Platform;
import com.esa.bmap.model.Privacy;
import com.esa.bmap.model.Status;
import com.esa.bmap.model.SubRegion;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
import com.esa.bmap.service.catalogue.implement.DataCatalogServiceCMRImpl;
import com.obs.services.ObsClient;
import com.obs.services.ObsConfiguration;

/**
 * DataCatalogueServiceImpl
 * 
 * @author QFAURE
 *
 */
@Service(value = "DataCatalogueServiceInterface")
public class DataCatalogServiceImpl implements DataCatalogServiceInterface {

	/**
	 * Logger
	 */
	private static final Logger LOG = LoggerFactory.getLogger(DataCatalogServiceInterface.class);

	/**
	 * Set the properties to true to enable the ingestion into CMR
	 */
	@Value("${CMR.ingestion.enable}")
	private boolean enableCMRIngestion;

	/**
	 * Set the properties to true to enable the ingestion into CMR
	 */
	@Value("${datasource.privateUserDataDir}")
	private String privateUserDataFolder;

	/**
	 * Set the properties to true to enable the ingestion into CMR
	 */
	@Value("${CMR.search.enable}")
	private boolean enableCMRSearch;

	@Value("${bucketS3.userfolder}")
	private String s3UserFolder;
	// s3 endpoint
	@Value("${bucketS3.endpoint}")
	public String s3endpoint;

	// s3 admin access key
	@Value("${bucketS3.access.key}")
	public String s3AdminAccessKey;

	// s3 admin access key
	@Value("${bucketS3.secret.key}")
	public String s3AdminSecretKey;

	// s3 bucket copa name
	@Value("${bucketS3.copa.name}")
	public String s3CopaBucketName;

	// s3 bucket bmap name
	@Value("${bucketS3.bmap.name}")
	public String s3BmapBucketName;

	// s3 connection timeout
	@Value("${bucketS3.connection.timeout}")
	public String s3ConnectionTimeout;
	// s3 connection timeout
	@Value("${bucketS3.socket.timeout}")
	public String s3SocketTimeout;

	// Geoserver workspace where the data layers will be published
	@Value("${geoserver.workspace}")
	private String GEOSERVER_WORKSPACE;

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

	@Autowired
	private S3BucketService s3Service;

	@Autowired
	WorkspacesLoader workspaceLoader;

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
	 * {@inheritDoc}
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
		if (enableCMRIngestion) {
			LOG.info(Common.getMessageValue("DataCatalogService.ingestion.cmrEnabled"));
			if (connectionCmr) {
				LOG.info(Common.getMessageValue("DataCatalogService.ingestion.cmrConnection.success"));
				// ingest in the CMR api :
				dataCatalogServiceCMRImpl.addGranule(granule);
			} else {
				throw new BmapException(Common.getMessageValue("DataCatalogService.ingestion.cmrConnection.error"));
			}
		} else {
			LOG.info(Common.getMessageValue("DataCatalogService.ingestion.cmrDisabled"));
		}

		// ingest in the local database
		switch (granule.getCollection().getCategoryKeyWords()) {
		case Collection.COLLECTION_TYPE_GROUND_CAMPAIGN:

			// get the child objects from the database
			if (granule.getCollection() != null) {
				collection = this.collectionDao
						.findByShortNameIgnoreCase(((Granule) granule).getCollection().getShortName());
			}
			if (granule.getSubRegion() != null) {
				subRegion = this.subRegionDao.findByName(((Granule) granule).getSubRegion().getName());
				if (subRegion == null) {
					this.subRegionDao.save(((Granule) granule).getSubRegion());
				} else {
					((Granule) granule).setSubRegion(subRegion);
				}
			}

			if (granule.getPlatform() != null) {
				platform = this.platformDao.findByName(((Granule) granule).getPlatform().getName());
				if (platform == null && granule.getGranuleScene() == null) {
					// Set plateform and the list of the instrument only for a
					// granule scene

					if (granule.getPlatform().getListInstrument() != null) {
						for (int i = 0; i < granule.getPlatform().getListInstrument().size(); i++) {
							Instrument instrument = this.instrumentDao
									.findByName(granule.getPlatform().getListInstrument().get(i).getName());
							if (instrument == null) {
								this.instrumentDao.save(granule.getPlatform().getListInstrument().get(i));
							}
						}
					}
					// creat a new plateform here and the created platform will
					// be referenced in the granule.
					this.platformDao.save(granule.getPlatform());

				} else {
					// set to the granule the found plateform in database
					granule.setPlatform(platform);
				}
			}

			// if the child objects don't exist in the database, save them
			// otherwise, get them from the database and set them in the
			// data to save

			if (collection == null) {
				this.collectionDao.save(((Granule) granule).getCollection());
			} else {
				((Granule) granule).setCollection(collection);
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
	 * {@inheritDoc}
	 */
	@Override
	public Granule addPrivateGranule(Granule granule) throws BmapException {
		// Checking existence of child object in dataBase
		// Checking if collection exists
		if (granule.getCollection() != null) {
			Collection collection = this.collectionDao
					.findByShortNameIgnoreCase(granule.getCollection().getShortName());
			if (collection == null) {
				this.collectionDao.save(granule.getCollection());
			} else {
				granule.setCollection(collection);
			}
		}
		// Checking if subregion exists
		if (granule.getSubRegion() != null) {
			SubRegion subRegion = this.subRegionDao.findByName(granule.getSubRegion().getName());
			if (subRegion == null) {
				this.subRegionDao.save(granule.getSubRegion());
			} else {
				granule.setSubRegion(subRegion);
			}
		}
		// Checking if platform exists, save it if not
		if (granule.getPlatform() != null) {
			Platform platform = this.platformDao.findByName(granule.getPlatform().getName());
			if (platform == null) {
				this.platformDao.save(granule.getPlatform());
			} else {
				granule.setPlatform(platform);
			}
		}
		// saving granule in database
		granule = this.granuleDao.save(granule);

		return granule;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SubRegion addSubRegion(SubRegion subRegion) throws BmapException {
		return this.subRegionDao.save(subRegion);
	}

	/**
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
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
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAllData() throws BmapException {
		this.dataDao.deleteAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAllCollection() throws BmapException {
		this.collectionDao.deleteAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public void deleteAllSubRegion() throws BmapException {
		this.subRegionDao.deleteAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteDataByGroundCampaignName(String groundCampaignName) throws BmapException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteData(Data data) throws BmapException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Collection<Granule> getGranuleByCriteria(GranuleCriteria granuleCriteria) throws BmapException {
		LOG.info("Start of getCranuleByCriteria");
		java.util.Collection<Granule> listGranule = new ArrayList<Granule>();
		// test the connection to the cmr service :
		boolean connectionCmr = isCMRConnectionAvailable();
		if (granuleCriteria.getPrivacy() != null) {
			switch (granuleCriteria.getPrivacy()) {
			case PRIVATE:
				List<String> collectionList = new ArrayList<String>();
				collectionList.add(com.esa.bmap.model.Collection.COLLECTION_TYPE_L2_USER_DATAS);
				granuleCriteria.setCollectionNames(collectionList);
				listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
				break;
			case PUBLIC:
				if (enableCMRSearch) {
					if (connectionCmr) {
						LOG.info("connectionCmr = true");
						// search in the CMR api :
						listGranule = this.dataCatalogServiceCMRImpl.getDataByCriteria(granuleCriteria);
					} else {
						listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
					}
				} else {
					// only search in local database
					listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
				}
				break;
			default:

				break;

			}
		} else {
			// we first search for public granules with given collection name criteria, then
			// we search private granuls with user data collection name matching other
			// criteria
			granuleCriteria.setPrivacy(Privacy.PUBLIC);
			// Adding public data from cmr and matching given criteria
			if (enableCMRSearch) {
				if (connectionCmr) {
					LOG.info("connectionCmr = true");

					// search in the CMR api :
					listGranule.addAll(this.dataCatalogServiceCMRImpl.getDataByCriteria(granuleCriteria));
				} else {
					listGranule.addAll(this.dataDaoCustom.findByCriteria(granuleCriteria));
				}
			} else {
				// only search in local database
				listGranule.addAll(this.dataDaoCustom.findByCriteria(granuleCriteria));
			}
			// Adding private data dao for given user id and matching given criteria
			granuleCriteria.setPrivacy(Privacy.PRIVATE);
			List<String> collectionListBoth = new ArrayList<String>();
			collectionListBoth.add(com.esa.bmap.model.Collection.COLLECTION_TYPE_L2_USER_DATAS);
			granuleCriteria.setCollectionNames(collectionListBoth);
			listGranule.addAll(this.dataDaoCustom.findByCriteria(granuleCriteria));
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
		boolean connectionAvailable = true;
		switch (Common.getPropertiesValue("Request.Test.Nasa.Connection.TimeOut.Boolean").toLowerCase()) {
		case "false":
			connectionAvailable = false;
			break;
		}
		int timer = Integer.parseInt(Common.getPropertiesValue("Request.Test.Nasa.Connection.TimeOut"));
		boolean connectionCmr = dataCatalogServiceCMRImpl.testConnectionTimeOut(timer, connectionAvailable);
		return connectionCmr;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection addCollection(Collection collection) throws BmapException {

		return this.collectionDao.save(collection);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Granule getGranuleByGranuleName(String granuleName) throws BmapException {
		java.util.Collection<Granule> listGranule = new ArrayList<Granule>();
		GranuleCriteria granuleCriteria = new GranuleCriteria();
		String[] granuleString = granuleName.split(Granule.GRANULE_ID_DELIMITER);
		List<String> collectionNames = new ArrayList<String>();
		collectionNames.add(granuleString[0]);
		String granuleShortName = granuleString[1];
		granuleCriteria.setCollectionNames(collectionNames);
		granuleCriteria.setGranuleName(granuleShortName);

		if (granuleString[0].equals(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {
			granuleCriteria.setPrivacy(Privacy.PRIVATE);
		} else {
			granuleCriteria.setPrivacy(Privacy.PUBLIC);
		}

		LOG.info("Start of getCranuleByCriteria");
		// test the connection to the cmr service :
		boolean connectionCmr = isCMRConnectionAvailable();
		if (granuleCriteria.getPrivacy() != null) {
			switch (granuleCriteria.getPrivacy()) {
			case PUBLIC:
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
				break;
			case PRIVATE:
				List<String> collectionList = new ArrayList<String>();
				collectionList.add(com.esa.bmap.model.Collection.COLLECTION_TYPE_L2_USER_DATAS);
				granuleCriteria.setCollectionNames(collectionList);
				listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
				break;
			default:
				break;
			}
		}

		LOG.info("End of getCranuleByCriteria");
		return listGranule.iterator().next();
	}

	/**
	 * {@inheritDoc}
	 */
	@Deprecated
	@Override
	public Granule addUserGranule(String dataPath, long userId) throws BmapException {

		// First we read the data using te path to determine the type (ROI or Raster)
		// Of the data
		Granule granule = new Granule();

		// We have at leat one data, so we create the first data
		List<Data> dataList = new ArrayList<Data>();
		Data data = new Data();
		data.setFilePath(dataPath);
		dataList.add(data);

		// We set the granule with the first data
		granule.setDataList(dataList);

		// Wwe have to find

		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Granule shareUserGranule(String granuleUR) throws BmapException {

		String[] result = granuleUR.split(Granule.GRANULE_ID_DELIMITER);

		Granule granule = granuleDao.findByGranuleUR(result[0], result[1]).get(0);

		LOG.info(granule.toString());

		/*
		 * Constructs a obs client instance with your account for accessing OBS
		 */
		ObsConfiguration obsConfiguration = new ObsConfiguration();
		obsConfiguration.setEndPoint(s3endpoint);
		obsConfiguration.setSocketTimeout(Integer.valueOf(s3SocketTimeout));
		obsConfiguration.setConnectionTimeout(Integer.valueOf(s3ConnectionTimeout));

		ObsClient obsClient = new ObsClient(s3AdminAccessKey, s3AdminSecretKey, obsConfiguration);

		if (granule.getProductType().equalsIgnoreCase(Granule.PRODUCT_TYPE_ROI)) {

			// it is a roi file, an upload of every file and the zip file is needed
			for (int i = 0; i < granule.getDataList().size(); i++) {
				File fileToUpload = new File(granule.getDataList().get(i).getFilePath());

				// Building Object Key. Separator is a url one
				String objectKey = s3UserFolder + String.valueOf(granule.getAuthor().getId()) + "/"
						+ fileToUpload.getName();
				LOG.info(fileToUpload.getAbsolutePath());
				S3BucketService.uploadFileToS3Bucket(obsClient, fileToUpload, s3BmapBucketName, objectKey);

			}

			LOG.info("uploading roi zip file");

			String zipFileName = granule.getDataList().get(0).getFileName().substring(0,
					granule.getDataList().get(0).getFileName().lastIndexOf('.')) + Granule.EXTENSION_ZIP;

			File zipFile = new File(granule.getDataList().get(0).getFilePath().substring(0,
					granule.getDataList().get(0).getFilePath().lastIndexOf('.')) + Granule.EXTENSION_ZIP);

			LOG.debug(zipFile.getAbsolutePath());
			// Building Object Key. Separator is a url one
			String objectKey = s3UserFolder + String.valueOf(granule.getAuthor().getId()) + "/" + zipFileName;

			S3BucketService.uploadFileToS3Bucket(obsClient, zipFile, s3BmapBucketName, objectKey);

		} else {
			// it is a TIFF file (raster)
			File fileToUpload = new File(granule.getDataList().get(0).getFilePath());

			// Building Object Key. Separator is a url one
			String objectKey = s3UserFolder + String.valueOf(granule.getAuthor().getId()) + "/"
					+ fileToUpload.getName();
			LOG.debug(fileToUpload.getAbsolutePath());
			S3BucketService.uploadFileToS3Bucket(obsClient, fileToUpload, s3BmapBucketName, objectKey);
		}
		granule.setPrivacy(Privacy.PUBLIC);
		granule.setStatus(Status.SHARED);

		// saving granule in DB and CMR
		granuleDao.save(granule);
		dataCatalogServiceCMRImpl.addGranule(granule);

		return granule;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String movePrivateDataBeforeIngestion(String dataPath, BmaapUser user) throws BmapException {

		// We check if the file exists
		File dataOrigin = new File(dataPath);
		LOG.info("The data path is : " + dataOrigin.getAbsolutePath());
		if (!dataOrigin.exists()) {
			throw new BmapException("The file does not exist", HttpStatus.NOT_FOUND);
		}

		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

		// We move the data to this destination
		File finalFile = new File(privateUserDataFolder,
				user.getId().toString() + '-' + FilenameUtils.removeExtension(dataOrigin.getName()) + fileSuffix + "."
						+ FilenameUtils.getExtension(dataOrigin.getName()));
		try {
			FileUtils.moveFile(dataOrigin, finalFile);
		} catch (IOException e) {
			throw new BmapException("The file was not move correctly. ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		LOG.info("The file is move to " + finalFile.getAbsolutePath());
		// Boolean isCorrectlyMoved = dataOrigin.renameTo(finalFile);
		//
		// if (!isCorrectlyMoved) {
		// throw new BmapException("The file was not move correctly. It may exist
		// already.",
		// HttpStatus.INTERNAL_SERVER_ERROR);
		// }
		// We return the path of this new data

		return finalFile.getAbsolutePath();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Granule getGranuleByGranuleNameDataBase(String granuleName) throws BmapException {
		java.util.Collection<Granule> listGranule = new ArrayList<Granule>();
		GranuleCriteria granuleCriteria = new GranuleCriteria();
		String[] granuleString = granuleName.split(":@");
		List<String> collectionNames = new ArrayList<String>();
		collectionNames.add(granuleString[0]);
		String granuleShortName = granuleString[1];
		granuleCriteria.setCollectionNames(collectionNames);
		granuleCriteria.setGranuleName(granuleShortName);

		if (granuleString[0].equals(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {
			granuleCriteria.setPrivacy(Privacy.PRIVATE);
		} else {
			granuleCriteria.setPrivacy(Privacy.PUBLIC);
		}

		LOG.info("Start of getCranuleByCriteria");
		// test the connection to the cmr service :

		if (granuleCriteria.getPrivacy() != null) {
			switch (granuleCriteria.getPrivacy()) {
			case PUBLIC:
				// if connection CMR is ok proceed to ingest:

				LOG.info("connectionCmr = true");

				// search in the CMR api :
				listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);

				break;
			case PRIVATE:
				List<String> collectionList = new ArrayList<String>();
				collectionList.add(com.esa.bmap.model.Collection.COLLECTION_TYPE_L2_USER_DATAS);
				granuleCriteria.setCollectionNames(collectionList);
				listGranule = this.dataDaoCustom.findByCriteria(granuleCriteria);
				break;
			default:
				break;
			}
		}

		LOG.info("End of getCranuleByCriteria");
		if (!listGranule.isEmpty() && listGranule != null) {
			return listGranule.iterator().next();
		} else {
			return null;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection getCollectionByShortName(String shortName) throws BmapException {
		Collection collection = this.collectionDao.findByShortName(shortName);
		return collection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Collection> getAllCollection() throws BmapException {
		Iterable<Collection> collection = this.collectionDao.findAll();
		return collection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<Granule> getAllGranules() throws BmapException {
		Iterable<Granule> granuleIterable = this.granuleDao.findAll();
		return granuleIterable;
	}

	@Override
	public void deleteGranuleByGranuleUR(String granuleUR) throws BmapException {

		// searching granule from granuleUR
		GranuleCriteria granuleCriteria = new GranuleCriteria();
		String[] granuleURSplit = granuleUR.split(Granule.GRANULE_ID_DELIMITER);
		List<String> collectionNames = new ArrayList<>();
		collectionNames.add(granuleURSplit[0]);
		granuleCriteria.setCollectionNames(collectionNames);
		granuleCriteria.setGranuleName(granuleURSplit[1]);
		java.util.Collection<Granule> granules = this.getGranuleByCriteria(granuleCriteria);
		Granule granule = granules.iterator().next();

		// delete retrieve granule
		granuleDao.delete(granule);
		workspaceLoader.deleteStore(GEOSERVER_WORKSPACE, granule.getName());

	}

	@Override
	public void deleteGranuleById(long id) throws BmapException {
		// getting granule by id
		Granule granule = this.getGranuleById(Long.valueOf(id));
		// delete retrieve granule
		workspaceLoader.deleteStore(GEOSERVER_WORKSPACE, granule.getName());
		granuleDao.delete(granule);

	}

	@Override
	public void deleteGranuleByGranuleURFromCMR(String granuleUR) throws BmapException {
		dataCatalogServiceCMRImpl.deleteGranule(granuleUR);
	}

	@Override
	public List<String> getBiomassCollections() throws BmapException {
		return dataCatalogServiceCMRImpl.getBiomassCollections();
	}

}
