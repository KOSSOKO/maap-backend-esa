package com.esa.bmap.service.catalogue.data.implement;

import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

import com.esa.bmap.model.Quadrangle;

/**
 * Holder object to create Quadrangle
 * @author jstranig
 *
 */
public class QuadrangleHolder {
	/**
	 * EPSG Code 4326
	 */
	public static final String EPSG_4326 = "EPSG:4326";
	
	/**
	 * The target system for the coordinate system
	 */
	CoordinateReferenceSystem target;

	/**
	 * The epsg code we want to use
	 */
	String epsgCodeDeclared;
	
	/**
	 * The epsg code of the native file
	 */
	String epsgCodeNative;
	
	/**
	 * The quandrangle build in the holder
	 */
	Quadrangle quadrangle;
	
	public QuadrangleHolder() throws NoSuchAuthorityCodeException, FactoryException {
		target = CRS.decode(EPSG_4326);
		epsgCodeDeclared = CRS.lookupIdentifier(target, true);
	}
	
	/**
	 * @return the target
	 */
	public CoordinateReferenceSystem getTarget() {
		return target;
	}
	/**
	 * @param target the target to set
	 */
	public void setTarget(CoordinateReferenceSystem target) {
		this.target = target;
	}

	
	/**
	 * @return the epsgCodeNative
	 */
	public String getEpsgCodeNative() {
		return epsgCodeNative;
	}
	/**
	 * @param epsgCodeNative the epsgCodeNative to set
	 */
	public void setEpsgCodeNative(String epsgCodeNative) {
		this.epsgCodeNative = epsgCodeNative;
	}
	/**
	 * @return the quadrangle
	 */
	public Quadrangle getQuadrangle() {
		return quadrangle;
	}
	/**
	 * @param quadrangle the quadrangle to set
	 */
	public void setQuadrangle(Quadrangle quadrangle) {
		this.quadrangle = quadrangle;
	}

	/**
	 * @return the epsgCodeDeclared
	 */
	public String getEpsgCodeDeclared() {
		return epsgCodeDeclared;
	}
	/**
	 * @param epsgCodeDeclared the epsgCodeDeclared to set
	 */
	public void setEpsgCodeDeclared(String epsgCodeDeclared) {
		this.epsgCodeDeclared = epsgCodeDeclared;
	}

}
