package com.esa.bmap.service.catalogue.data.interfaces;

import java.util.Collection;

import com.esa.bmap.model.Algorithm;

public interface InitDataBaseServiceInterface {

	/**
	 * load some Examples to the database 
	 * @return algorithm that we added into the database
	 */
	Collection<Algorithm> initDataBase();
}
