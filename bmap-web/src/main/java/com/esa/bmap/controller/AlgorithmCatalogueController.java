package com.esa.bmap.controller;

import java.util.Collection;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Algorithm;
import com.esa.bmap.model.AlgorithmCriteria;
import com.esa.bmap.service.catalogue.algorithm.interfaces.AlgorithmServiceInterface;
import com.esa.bmap.service.catalogue.algorithm.interfaces.GitlabApiInterface;


@RestController
@RequestMapping(value="/catalogue/algorithms")
public class AlgorithmCatalogueController  {
	private final Logger LOG = LoggerFactory.getLogger(AlgorithmCatalogueController.class);	
	@Resource
	private AlgorithmServiceInterface algorithmService;

	@Resource
	private GitlabApiInterface gitlabService;
	
	/**
	 * get algorithm by it's id
	 * @param idAlgorithm
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Algorithm getAlgorithmById(@PathVariable(value = "id") int idAlgorithm) throws BmapException {
		return this.algorithmService.getAlgorithm(idAlgorithm);
	}

	/**
	 * get algorithm by it's id
	 * @param idAlgorithm
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
	public Algorithm getAlgorithmById(@PathVariable(value = "name") String name) throws BmapException {
		return this.algorithmService.getAlgorithmByName(name);
	}

	/**
	 * Get any algorithms type using criteria.
	 * 
	 * @param data
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/search/criteria", method = RequestMethod.POST)
	public Collection<Algorithm> getAlgorithmByCriteria(@RequestBody AlgorithmCriteria algoCriteria) throws BmapException {
		return this.algorithmService.getAlgorithmByCriteria(algoCriteria);
	}
	
	/**
	 * get all algorithms
	 * @return List of algorithms
	 * @throws BmapException 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Collection<Algorithm> getAllAlgorithms() throws BmapException {
		LOG.info("Get all algorithms");
		return this.algorithmService.getAllAlgorithms();
	}
	
	
	/**
	 * Search algorithms using a ctriteria
	 * @param criteria
	 * @return
	 * @throws BmapException
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public Collection<Algorithm> searchAlgorithm(@RequestParam("criteria") String criteria) throws BmapException {
		return this.algorithmService.searchAlgorithm(criteria);
	}
	/**
	 * Get the last ten algorithms published
	 * @return List<Algorithms>
	 * @throws BmapException
	 */
	@RequestMapping(value = "/last/ten", method = RequestMethod.GET)
	public Collection<Algorithm>  getLastTenAlgorithms() throws BmapException {
		return this.algorithmService.getLastTenAlgorithmsPublished();
	}
	
	/**
	 * add an algorithm 
	 * @param algorithm
	 * @return Algorithm
	 * @throws BmapException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Algorithm addAlgorithm(@RequestBody Algorithm algorithm) throws BmapException {
		//we create a new algorithm and return it
		LOG.info("Add a new algorithm" + algorithm.toString());
		return this.algorithmService.addAlgorithm(algorithm);	
	}

	/**
	 * delete algorithm by id
	 * @param id
	 * @throws BmapException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public void deleteAlgorithm(@PathVariable(value = "id") int id) throws BmapException {
		this.algorithmService.deleteAlgorithm(id);
	}
	
	/**
	 * update an algorithm
	 * @param id
	 * @param algorithm
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Algorithm updateAlgorithm(@RequestBody Algorithm algorithm) {
		
		//We update the algorithm
		return this.algorithmService.updateAlgorithm(algorithm);
	}

}
