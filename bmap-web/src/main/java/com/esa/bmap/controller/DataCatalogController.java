package com.esa.bmap.controller;

import java.util.Collection;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Granule;
import com.esa.bmap.model.GranuleCriteria;
import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;

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
	private DataCatalogServiceInterface dataCatalogueService;

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
	 * Get data by granuleName.
	 * 
	 * @param granuleName (collection/granuleUR)
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/granulename/{granuleName}", method = RequestMethod.GET)
	public Granule getGranuleByGranuleName(@PathVariable(value = "granuleName") String granuleName) throws BmapException {
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
	public void deleteDataByGroundCampaignName(@PathVariable(value = "groundCampaignName") String groundCampaignName) throws BmapException {
		this.dataCatalogueService.deleteDataByGroundCampaignName(groundCampaignName);
	}
}
