package com.esa.bmap.service.catalogue.algorithm.interfaces;

import java.util.Collection;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Algorithm;
import com.esa.bmap.model.AlgorithmCriteria;
import com.esa.bmap.model.Status;



public interface AlgorithmServiceInterface {

	/**
	 * add a new Algorithm (admin who can use this option)
	 * @param algorithm
	 * @return Algorithm
	 * @throws BmapException 
	 */
	Algorithm addAlgorithm(Algorithm algorithm) throws BmapException;
	

	/**
	 * update algorithm statud only admin can use this function
	 * @param idAlgo
	 * @param status
	 * @throws BmapException 
	 */
	Algorithm updateStatus(int idAlgo, Status status) throws BmapException;
	
	/**
	 * delete an algorithm by id
	 * @param idAlgo
	 */
	void deleteAlgorithm(int idAlgo) throws BmapException;
	
	/**
	 * delete all algorithm form the data base
	 */
	void deleteAll();
	
	/**
	 * update an existing algorithm in the dataBase
	 * @param algorithm
	 * @return 
	 */
	Algorithm updateAlgorithm(Algorithm algorithm);

	/**
	 * get an algorithm using his id
	 * @param idAlgorithm
	 * @return
	 * @throws BmapException
	 */
	Algorithm getAlgorithm(int idAlgorithm) throws BmapException;
	
	/**
	 * get an algorithm using it's name
	 * @param name
	 * @return
	 * @throws BmapException
	 */
	Algorithm getAlgorithmByName(String name) throws BmapException;
	/**
	 * get all the algorithms from the dataBAse
	 * @return
	 */
	Collection<Algorithm> getAllAlgorithms() throws BmapException;
	
	/**
	 * get all algorithms by the project id 
	 * @param idProject
	 * @return
	 * @throws BmapException
	 */
	Collection<Algorithm> getAlgorithmsByProject(Long idProject) throws BmapException;
	
	/**
	 * get list of all algorithm that have the same status 
	 * @param status
	 * @return
	 * @throws BmapException
	 */
	Collection<Algorithm> getAlgorithmsByStatus(Status status) throws BmapException;
	
	/**
	 * 
	 * @param Criteria
	 * @return
	 */
	Collection<Algorithm> searchAlgorithm(String Criteria) throws BmapException;

	/**
	 * get list of all last ten algorithms published 
	 * @return Collection<Algorithm>
	 * @throws BmapException
	 */
	Collection<Algorithm> getLastTenAlgorithmsPublished() throws BmapException;


	/**
	 * Get an algorithm using a criteria object
	 * @param algoCriteria
	 * @return
	 */
	Collection<Algorithm> getAlgorithmByCriteria(AlgorithmCriteria algoCriteria) throws BmapException;
	

}
