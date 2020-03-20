package com.esa.bmap.service.dataprocessing.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.external.services.dataprocessing.interfaces.DataProcessingInterface;
import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Granule;
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
	@Value("${pythonserver.userdatadir}")
	private String pythonUserDataDir;

	/*
	 * {@inheritDoc}
	 */
	@Override
	public String getRasterPlotComparison(String xGranuleId, String yGranuleId, String wkt) throws BmapException {
		// splitting granuleID to get granule Name and Collection (used to retrieve file
		// path)
		String[] sepXdata = xGranuleId.split(Granule.GRANULE_ID_DELIMITER);
		String[] sepYdata = yGranuleId.split(Granule.GRANULE_ID_DELIMITER);

		String baseXDataDir = null;

		if (sepXdata[0].equalsIgnoreCase(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {

			baseXDataDir = pythonUserDataDir;

		} else {
			baseXDataDir = pythonDataDir + "/" + sepXdata[0];
		}

		String baseYDataDir = null;

		if (sepYdata[0].equalsIgnoreCase(Collection.COLLECTION_TYPE_L2_USER_DATAS)) {

			baseYDataDir = pythonUserDataDir;

		} else {
			baseYDataDir = pythonDataDir + "/" + sepYdata[0];
		}

		return this.dataProcessingService
				.getRasterPlotComparison(baseXDataDir + "/" + sepXdata[1], baseYDataDir + "/" + sepYdata[1], wkt)
				.replaceAll("'", "\"").replaceAll("None", "\"None\"");

	}

}
