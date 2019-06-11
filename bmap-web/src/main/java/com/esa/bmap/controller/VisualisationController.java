package com.esa.bmap.controller;

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
import com.esa.bmap.model.visualisation.GranuleRoiSubset;
import com.esa.bmap.model.visualisation.GranuleWktSubset;
import com.esa.bmap.model.visualisation.PlotComparisonHolder;
import com.esa.bmap.service.catalogue.data.implement.GroundCampaignReaderServiceImpl;
import com.esa.bmap.service.dataprocessing.interfaces.CustomProfileServiceInterface;
import com.esa.bmap.service.dataprocessing.interfaces.RasterHistogramServiceInterface;
import com.esa.bmap.service.dataprocessing.interfaces.RasterPlotComparisonServiceInterface;
import com.esa.bmap.service.dataprocessing.interfaces.RasterStatisticsService;

/**
 * @author edupin
 *
 */
@RestController
@RequestMapping(value = "/visualisation")
public class VisualisationController {
	private final Logger LOG = LoggerFactory.getLogger(VisualisationController.class);

	@Resource
	private RasterHistogramServiceInterface rasterHistogramServiceInterface;

	@Resource
	private RasterStatisticsService rasterStatisticsService;

	@Resource
	private CustomProfileServiceInterface customProfileServiceInterface;

	@Resource
	private RasterPlotComparisonServiceInterface rasterPlotComparisonServiceInterface;

	/**
	 * Get raster Raster basic statistics (min, max, average, standard deviation)
	 *
	 * @param granuleId Id of the granule to analyze
	 * @return json string representing the raster statistics of each data band
	 * @throws BmapException
	 */
	@RequestMapping(value = "raster/statistics/{id}", method = RequestMethod.GET)
	public String getRasterBasicStats(@PathVariable(value = "id") String granuleId) throws BmapException {

		String[] sep = granuleId.split(":@");

		return this.rasterStatisticsService.getRasterBasicStats(sep[0] + "/" + sep[1]).toString();
	}

	/**
	 * Get raster Raster subset statistics
	 *
	 * @param granuleWktSubset Object holding information on the raster granule to
	 *            analyze and the area selected from which to generate statistics
	 * @return json string representing the raster statistics of each data band
	 * @throws BmapException
	 */
	@RequestMapping(value = "raster/basicStatsSubset", method = RequestMethod.POST)
	public String getRasterSubsetStats(@RequestBody GranuleWktSubset granuleWktSubset) throws BmapException {

		return this.rasterStatisticsService.getSubsetStats(granuleWktSubset.getGranuleID(), granuleWktSubset.getWkt());
	}

	/**
	 * Get raster Raster statistics from ROI data
	 *
	 * @param granuleRoiSubset Object holding information on the raster granule to
	 *            analyze and the roi granule used to subset the raster with in
	 *            order to generate statistics
	 * @return json string representing the raster statistics of each data band
	 * @throws BmapException
	 */
	@RequestMapping(value = "raster/basicStatsRoiSubset", method = RequestMethod.POST)
	public String getRasterRoiSubsetStats(@RequestBody GranuleRoiSubset granuleRoiSubset) throws BmapException {

		return this.rasterStatisticsService.getRoiSubsetStats(granuleRoiSubset.getGranuleRaster(),
				granuleRoiSubset.getGranuleRoi());
	}

	/**
	 * Get raster Data frequency histogram
	 * 
	 * @param granuleId base raster granule used to generate frequency histogram
	 * @return json string representing the frequency histogram of each data band
	 * @throws BmapException
	 */
	@RequestMapping(value = "raster/histogram/{id}", method = RequestMethod.GET)
	public String getRasterHistogram(@PathVariable(value = "id") String granuleId) throws BmapException {

		return this.rasterHistogramServiceInterface.getRasterHistogram(granuleId);
	}

	/**
	 * Get raster Raster Custom Profile from wkt Line
	 * 
	 * @param granuleWktSubset Object holding the information on the coordinates of
	 *            the line selected, and the base Raster granule to generate custom
	 *            profile
	 * @return json String containing custom profile values
	 * @throws BmapException
	 */
	@RequestMapping(value = "raster/customProfile", method = RequestMethod.POST)
	public String getCustomProfile(@RequestBody GranuleWktSubset granuleWktSubset) throws BmapException {

		return this.customProfileServiceInterface.getCustomProfile(granuleWktSubset.getGranuleID(),
				granuleWktSubset.getWkt());
	}

	/**
	 * Get a two rasters Comparison
	 * 
	 * @param plotComparisonHolder Object holding the information of the two raster
	 *            granules to compare along with the area to subset the data with
	 * @return String the bytes array representing the scatterplot chart
	 * @throws BmapException
	 */
	@RequestMapping(value = "comparison/rasterComparison", method = RequestMethod.POST)
	public String getPlotComparison(@RequestBody PlotComparisonHolder plotComparisonHolder) throws BmapException {
		LOG.info("Getting Plot Comparison");
		return this.rasterPlotComparisonServiceInterface.getRasterPlotComparison(plotComparisonHolder.getGranuleXaxe(),
				plotComparisonHolder.getGranuleYaxe(), plotComparisonHolder.getWkt());
	}

}
