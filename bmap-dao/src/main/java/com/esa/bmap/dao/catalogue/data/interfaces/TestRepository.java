package com.esa.bmap.dao.catalogue.data.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esa.bmap.model.Data;

@Repository
public interface TestRepository extends CrudRepository<Data, Long> { //, TestRepositoryCustom

}
