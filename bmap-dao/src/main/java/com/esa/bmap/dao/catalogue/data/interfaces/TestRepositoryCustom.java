package com.esa.bmap.dao.catalogue.data.interfaces;

import java.util.Collection;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.GranuleCriteria;

/**
 * CustomizedDataRepository
 * 
 * @author QFAURE
 *
 */
public interface TestRepositoryCustom {

	Collection<Data> custom(GranuleCriteria granuleCriteria) throws BmapException;
}
