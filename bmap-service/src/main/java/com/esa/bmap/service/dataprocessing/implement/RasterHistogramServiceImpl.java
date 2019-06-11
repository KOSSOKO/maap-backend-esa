package com.esa.bmap.service.dataprocessing.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.service.dataprocessing.interfaces.DataProcessingInterface;
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
	
	/**
	 * Delimiter used to split the granule id to get collection and granule name
	 */
	private static final String GRANULE_ID_DELIMITER = ":@";
	
	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getRasterHistogram(String granuleId) throws BmapException {
		//splitting granuleID to get granule Name and Collection (used to retrieve file path)
		String[] sep = granuleId.split(GRANULE_ID_DELIMITER);

		return this.dataProcessingService.getRasterHistogram(pythonDataDir + "/" + sep[0] + "/" + sep[1]);

	}

}
