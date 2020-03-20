package com.esa.bmap.service.dataprocessing.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.services.dataprocessing.interfaces.DataProcessingInterface;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Granule;
import com.esa.bmap.service.dataprocessing.interfaces.RasterHistogramServiceInterface;

@Service(value = "RasterHistogramServiceInterface")
public class RasterHistogramServiceImpl implements RasterHistogramServiceInterface {

	@Autowired
	private DataProcessingInterface dataProcessingService;
	/**
	 * Data directory of the campaigns mounted on the python server (not needed)
	 */
	@Value("${pythonserver.datadir}")
	private String pythonDataDir;
	
	@Value("${pythonserver.userdatadir}")
	private String pythonUserDataDir;
	
	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getRasterHistogram(String granuleId) throws BmapException {
		//splitting granuleID to get granule Name and Collection (used to retrieve file path)
		String[] sep = granuleId.split(Granule.GRANULE_ID_DELIMITER);
		String baseDataDir = null;

		if (sep[0].equalsIgnoreCase(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {

			baseDataDir = pythonUserDataDir;

		} else {
			baseDataDir = pythonDataDir + "/" + sep[0];
		}
		
		return this.dataProcessingService.getRasterHistogram(baseDataDir + "/" + sep[1]);

	}

}
