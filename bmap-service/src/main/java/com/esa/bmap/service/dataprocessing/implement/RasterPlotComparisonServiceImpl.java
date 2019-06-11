package com.esa.bmap.service.dataprocessing.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.service.dataprocessing.interfaces.DataProcessingInterface;
import com.esa.bmap.service.dataprocessing.interfaces.RasterPlotComparisonServiceInterface;

@Service(value = "RasterPlotComparisonServiceInterface")
public class RasterPlotComparisonServiceImpl implements RasterPlotComparisonServiceInterface {

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
	public String getRasterPlotComparison(String xGranuleId,String yGranuleId, String wkt) throws BmapException{
		//splitting granuleID to get granule Name and Collection (used to retrieve file path)
		String[] sepXdata = xGranuleId.split(GRANULE_ID_DELIMITER);
		String[] sepYdata = yGranuleId.split(GRANULE_ID_DELIMITER);
		
		return this.dataProcessingService.getRasterPlotComparison(pythonDataDir + "/" + sepXdata[0] + "/" + sepXdata[1], pythonDataDir + "/"+ sepYdata[0] + "/" + sepYdata[1], wkt).replaceAll("'", "\"").replaceAll("None", "\"None\"");
		
	}

}
