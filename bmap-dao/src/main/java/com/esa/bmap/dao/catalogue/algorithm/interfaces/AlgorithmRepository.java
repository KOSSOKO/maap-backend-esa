package com.esa.bmap.dao.catalogue.algorithm.interfaces;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.esa.bmap.model.Algorithm;
import com.esa.bmap.model.Status;

public interface AlgorithmRepository extends CrudRepository<Algorithm, Integer> {
	/**
	 * find algorithms that have the same status necessarily with status toBeApproved to show them to the admin so that he can either approvedIt or deleteIt
	 * @param status
	 * @return List<Algorithm>
	 */
	Collection<Algorithm> findByStatus(Status status);
	
	/**
	 * search function
	 * @param algorithmName
	 * @param algorithmContributer
	 * @return List<Algorithm>
	 */
	@Query(value="SELECT a FROM Algorithm a where"
			+ " a.tags like %:criteria% or "
			+ " a.author.name like %:criteria% or "
			+ " a.applicationZone like %:criteria% or "
			+ " a.averageTime like %:criteria% or " 
			+ " a.currentVersion like %:criteria% or "
			+ " a.status like %:criteria% ")
	Collection<Algorithm> findAlgorithmsByCriteria(@Param("criteria")String criteria);
	
	/**
	 * Find the ten last algorithms published order by id
	 * @return Collection<Algorithm> 
	 */
	@Query(value="SELECT a FROM Algorithm a order by a.id desc")
	List<Algorithm> findTenLastAlgo(Pageable pageable);
}
