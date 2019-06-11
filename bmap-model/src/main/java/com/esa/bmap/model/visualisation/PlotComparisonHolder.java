package com.esa.bmap.model.visualisation;

import com.esa.bmap.model.Utils;

public class PlotComparisonHolder {

	/**
	 * raster xAxe granule
	 */
	private String granuleXaxe;
	/**
	 * raster yAxe granule
	 */
	private String granuleYaxe;
	/*
	 * wkt polygon geometry used to subset the two rasters with
	 */
	private String wkt;
	
	
	public PlotComparisonHolder() {
		super();
	}


	public PlotComparisonHolder(String granuleXaxe, String granuleYaxe, String wkt) {
		super();
		this.granuleXaxe = granuleXaxe;
		this.granuleYaxe = granuleYaxe;
		this.wkt = wkt;
	}


	/**
	 * @return the granuleXaxe
	 */
	public String getGranuleXaxe() {
		return granuleXaxe;
	}


	/**
	 * @param granuleXaxe the granuleXaxe to set
	 */
	public void setGranuleXaxe(String granuleXaxe) {
		this.granuleXaxe = granuleXaxe;
	}


	/**
	 * @return the granuleYaxe
	 */
	public String getGranuleYaxe() {
		return granuleYaxe;
	}


	/**
	 * @param granuleYaxe the granuleYaxe to set
	 */
	public void setGranuleYaxe(String granuleYaxe) {
		this.granuleYaxe = granuleYaxe;
	}


	/**
	 * @return the wkt
	 */
	public String getWkt() {
		return wkt;
	}


	/**
	 * @param wkt the wkt to set
	 */
	public void setWkt(String wkt) {
		this.wkt = wkt;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class PlotComparisonHolder {\n");
		sb.append("    granuleXaxe: ").append(Utils.toIndentedString(this.granuleXaxe)).append("\n");
		sb.append("    granuleYaxe: ").append(Utils.toIndentedString(this.granuleYaxe)).append("\n");
		sb.append("    wkt: ").append(Utils.toIndentedString(this.wkt)).append("\n");
		sb.append("}");
		return sb.toString();
	}
	
}
