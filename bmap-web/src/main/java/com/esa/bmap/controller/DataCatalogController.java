package com.esa.bmap.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
//biomass.pl.s2-eu.capgemini.com/gitlab/bmap/back-end.git
import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.model.DataFormat;
import com.esa.bmap.model.DataFormatPrivate;
import com.esa.bmap.model.DataType;
import com.esa.bmap.model.GeometryTypes;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.model.Polarization;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
import com.esa.bmap.service.catalogue.data.interfaces.GroundCampaignReaderServiceInterface;
import com.esa.bmap.service.usermanagement.interfaces.BmapUserServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * DataCatalogueController
 * 
 * @author QFAURE
 *
 */
@RestController
@RequestMapping(value = "/catalogue/granule")
public class DataCatalogController {

	@Resource
	private BmapUserServiceInterface bmapUserService;

	@Autowired
	private GroundCampaignReaderServiceInterface groundCompaignReaderInterface;

	@Resource
	private DataCatalogServiceInterface dataCatalogueService;

	private static final Logger LOG = LoggerFactory.getLogger(DataCatalogController.class);

	/**
	 * Get data by id.
	 * 
	 * @param id
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Granule getGranuleById(@PathVariable(value = "id") long id) throws BmapException {
		return this.dataCatalogueService.getGranuleById(id);
	}

	/**
	 * method to delete a granule by its granuleUR
	 * 
	 * @param granuleUR
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "granuleUR/{granuleUR}", method = RequestMethod.DELETE)
	public void deleteGranuleByGranuleUR(@PathVariable(value = "granuleUR") String granuleUR) throws BmapException {

		dataCatalogueService.deleteGranuleByGranuleUR(granuleUR);

	}

	/**
	 * method to delete a granule from CMR
	 * 
	 * @param granuleUR
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "cmr/{granuleUR}", method = RequestMethod.DELETE)
	public void deleteGranuleByGranuleURFromCMR(@PathVariable(value = "granuleUR") String granuleUR)
			throws BmapException {

		dataCatalogueService.deleteGranuleByGranuleURFromCMR(granuleUR);

	}

	/**
	 * method to delete a granule by its id
	 * 
	 * @param id
	 * @throws BmapException
	 */
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void deleteGranuleById(@PathVariable(value = "id") long id) throws BmapException {

		dataCatalogueService.deleteGranuleById(id);

	}

	/**
	 * Get data by granuleName.
	 * 
	 * @param granuleName (collection/granuleUR)
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/granulename/{granuleName}", method = RequestMethod.GET)
	public Granule getGranuleByGranuleName(@PathVariable(value = "granuleName") String granuleName)
			throws BmapException {

		return this.dataCatalogueService.getGranuleByGranuleName(granuleName);
	}

	/**
	 * Get any data type using criteria.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Collection<Granule> getGranuleByCriteria(@RequestBody GranuleCriteria granuleCriteria) throws BmapException {
		return this.dataCatalogueService.getGranuleByCriteria(granuleCriteria);
	}

	/**
	 * Delete data by ground campaign name.
	 * 
	 * @param groundCampaignName
	 * @throws BmapException
	 */
	@RequestMapping(value = "/delete/{groundCampaignName}", method = RequestMethod.GET)
	public void deleteDataByGroundCampaignName(@PathVariable(value = "groundCampaignName") String groundCampaignName)
			throws BmapException {
		// TODO Rework to include deletion on CMR
		this.dataCatalogueService.deleteDataByGroundCampaignName(groundCampaignName);
	}

	/**
	 * Share a Granule Data
	 * 
	 * @param granuleUR unique ID of the granule Data to share as so :
	 *            collectionName:@granuleName
	 * @return shared Granule Data
	 * @throws BmapException
	 */
	@RequestMapping(value = "/share/{granuleUR}", method = RequestMethod.GET)
	public Granule shareGranule(@PathVariable(value = "granuleUR") String granuleUR) throws BmapException {
		return this.dataCatalogueService.shareUserGranule(granuleUR);
	}

	/**
	 * Ingest data from Eclipse che or COPA for example
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/private/add", method = RequestMethod.GET)
	public void ingestUserData(@RequestParam(value = "dataPath") String dataPath,
			@RequestParam(value = "subregionName", required = false) String subregionName,
			@RequestParam(value = "polarization", required = false) Polarization polarization,
			@RequestParam(value = "dataFormat") DataFormatPrivate dataFormat,
			@RequestParam(value = "collectionName", required = false) String collectionName,
			@RequestParam(value = "userId") long userId) throws BmapException {

		LOG.info("Parameters passed to service : dataPath= " + dataPath + " - subregionName= " + subregionName
				+ " - polarization= " + polarization + " - dataFormat= " + dataFormat + " - collectionName= "
				+ collectionName + " - userId=" + userId);
		BmaapUser bmaapUser = bmapUserService.getBmapUserById(userId);

		try {
			if (subregionName != null && !subregionName.isEmpty()) {
				subregionName = java.net.URLDecoder.decode(subregionName, StandardCharsets.UTF_8.name());
			}

		} catch (UnsupportedEncodingException e) {
			throw new BmapException("Failed to decode request parameters");
		}
		if (bmaapUser == null) {
			LOG.error(Common.getMessageValue("errorMessage.userUnknown"), userId);
			throw new BmapException("User unknown", HttpStatus.NOT_FOUND);
		}
		// With the path of the data, we call the service to build the Granule scene
		groundCompaignReaderInterface.ingestPrivateData(dataPath, subregionName, polarization, dataFormat,
				collectionName, bmaapUser);
	}

	/**
	 * Expose the service to return the list of polarization
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/polarization", method = RequestMethod.GET)
	public Polarization[] getAllPolarization() throws BmapException {
		return Polarization.values();
	}

	/**
	 * Expose the service to return the list of dataFormats
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/dataformat", method = RequestMethod.GET)
	public DataFormat[] getAllDataFormat() throws BmapException {
		return DataFormat.values();
	}

	/**
	 * Expose the service to return the list of dataTypes
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/dataTypes", method = RequestMethod.GET)
	public DataType[] getAllDataTypes() {
		return DataType.values();
	}

	/**
	 * Expose the service to return the list of geometryTypes
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/geometryTypes", method = RequestMethod.GET)
	public GeometryTypes[] getAllGeometryTypes() {
		return GeometryTypes.values();
	}

	/**
	 * Expose the service to return the list of DataFormatPrivate
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/dataformatprivate", method = RequestMethod.GET)
	public DataFormatPrivate[] getAllDataFormatPrivate() throws BmapException {
		return DataFormatPrivate.values();
	}

	@RequestMapping(value = "/collections/names", method = RequestMethod.GET)
	public String getAllBiomassCollections() throws BmapException {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString;
		try {
			jsonString = objectMapper.writeValueAsString(dataCatalogueService.getBiomassCollections());
		} catch (JsonProcessingException e) {
			LOG.error(e.getMessage(), e);
			throw new BmapException("Failed to serialize to json string");
		}
		System.out.println(jsonString);
		return jsonString;
	}

}
