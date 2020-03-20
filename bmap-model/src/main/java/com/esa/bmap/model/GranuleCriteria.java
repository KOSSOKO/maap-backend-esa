package com.esa.bmap.model;

import java.time.LocalDate;
import java.util.List;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.common.utils.Security;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/**
 * GranuleCriteria
 * 
 * @author QFAURE
 *
 */
@JsonInclude(Include.NON_NULL) // ignore nulls during serialization
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class GranuleCriteria {

	/**
	 * Names of a Granule.
	 */
	private String granuleName = null;
	/**
	 * Names of Collections.
	 */
	private List<String> collectionNames = null;

	/**
	 * Names of sub-regions.
	 */
	private List<String> subRegionNames = null;

	/**
	 * Start date / end date to specify a range for the data acquisition date (e.g.
	 * 2007-12-03).
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate startDate = null;

	/**
	 * Start date / end date to specify a range for the data acquisition date (e.g.
	 * 2007-12-03).
	 */
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate endDate = null;

	/**
	 * Data product types (e.g. SLC, DEM).
	 */
	private List<String> productTypes = null;

	/**
	 * Instrument names used to capture the data.
	 */
	private List<String> instrumentNames = null;

	/**
	 * Data polarizations (e.g. HH, HV).
	 */
	private List<Polarization> polarizations = null;

	/**
	 * Data geometry types (e.g. slant range, ground range).
	 */
	private List<String> geometryTypes = null;

	/**
	 * Data processing levels (e.g. L0, L1).
	 */
	private List<String> processingLevels = null;

	/**
	 * Granule heading values (e.g. 310.0).
	 */
	private List<String> headingValues = null;

	/**
	 * Bounding box in which to search for data.
	 */
	private Quadrangle location = null;
	/**
	 * Desired privacy of the granules to retrieve
	 */
	private Privacy privacy = null;

	/**
	 * Bmaap UserID
	 */
	private Long userId = null;

	/**
	 * Creates an empty data criteria.
	 */
	public GranuleCriteria() {
		super();
	}

	/**
	 * Creates an data criteria with the specified parameters.
	 * 
	 * @param name of a granule
	 * @param startDate Start date / end date to specify a range for the data
	 *            acquisition date.
	 * @param endDate Start date / end date to specify a range for the data
	 *            acquisition date.
	 * @param productTypes Data product types (e.g. SLC, DEM).
	 * @param instrumentNames Instrument names used to capture an airborne data.
	 * @param location Bounding box in which to search for data.
	 * @param polarizations Data polarizations (e.g. HH, HV).
	 * @param geometryTypes Data geometry types (e.g. slant range, ground range).
	 * @param processingLevels Data processing levels (e.g. L0, L1).
	 * @param collectionNames Names of ground campaigns.
	 * @param subRegionNames Names of sub-regions.
	 * @throws BmapException When the specified string did not pass the security
	 *             checks.
	 */
	public GranuleCriteria(String granuleName, LocalDate startDate, LocalDate endDate, List<String> productTypes,
			List<String> instrumentNames, Quadrangle location, List<Polarization> polarizations,
			List<String> geometryTypes, List<String> processingLevels, List<String> collectionNames,
			List<String> subRegionNames, List<String> headingValues, String privacy, Long userId) throws BmapException {
		super();
		this.granuleName = granuleName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.productTypes = Security.checkStringList(productTypes);
		this.instrumentNames = Security.checkStringList(instrumentNames);
		this.polarizations = polarizations;
		this.location = location;
		this.geometryTypes = Security.checkStringList(geometryTypes);
		this.processingLevels = Security.checkStringList(processingLevels);
		this.collectionNames = Security.checkStringList(collectionNames);
		this.subRegionNames = Security.checkStringList(subRegionNames);
		this.headingValues = Security.checkStringList(headingValues);

		if (privacy.equals(Privacy.PRIVATE.toString())) {
			this.privacy = Privacy.PRIVATE;
		} else if (privacy.equals(Privacy.PUBLIC.toString())) {
			this.privacy = Privacy.PRIVATE;
		}
		this.userId = userId;

	}

	/**
	 * @return the granuleName
	 */
	public String getGranuleName() {
		return granuleName;
	}

	/**
	 * @param granuleName the granuleName to set
	 */
	public void setGranuleName(String granuleName) {
		this.granuleName = granuleName;
	}

	/**
	 * @return the headingValues
	 */
	public List<String> getHeadingValues() {
		return headingValues;
	}

	/**
	 * @param headingValues the headingValues to set
	 */
	public void setHeadingValues(List<String> headingValues) {
		this.headingValues = headingValues;
	}

	/**
	 * @return The startDate.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate The startDate to set.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return The endDate.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate The endDate to set.
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return The productTypes.
	 */
	public List<String> getProductTypes() {
		return productTypes;
	}

	/**
	 * @param productTypes The productTypes to set.
	 * @throws BmapException When the specified string did not pass the security
	 *             checks.
	 */
	public void setProductTypes(List<String> productTypes) throws BmapException {
		this.productTypes = Security.checkStringList(productTypes);
	}

	/**
	 * @return The instrumentNames.
	 */
	public List<String> getInstrumentNames() {
		return instrumentNames;
	}

	/**
	 * @param instrumentNames The instrumentNames to set.
	 * @throws BmapException When the specified string did not pass the security
	 *             checks.
	 */
	public void setInstrumentNames(List<String> instrumentNames) throws BmapException {
		this.instrumentNames = Security.checkStringList(instrumentNames);
	}

	/**
	 * @return The polarizations.
	 */
	public List<Polarization> getPolarizations() {
		return polarizations;
	}

	/**
	 * @param polarizations The polarizations to set.
	 */
	public void setPolarizations(List<Polarization> polarizations) {
		this.polarizations = polarizations;
	}

	/**
	 * @return The geometryTypes.
	 */
	public List<String> getGeometryTypes() {
		return geometryTypes;
	}

	/**
	 * @param geometryTypes The geometryTypes to set.
	 * @throws BmapException When the specified string did not pass the security
	 *             checks.
	 */
	public void setGeometryTypes(List<String> geometryTypes) throws BmapException {
		this.geometryTypes = Security.checkStringList(geometryTypes);
	}

	/**
	 * @return The location.
	 */
	public Quadrangle getLocation() {
		return location;
	}

	/**
	 * @param location The location to set.
	 */
	public void setLocation(Quadrangle location) {
		this.location = location;
	}

	/**
	 * @return The processingLevels.
	 */
	public List<String> getProcessingLevels() {
		return processingLevels;
	}

	/**
	 * @param processingLevels The processingLevels to set.
	 * @throws BmapException When the specified string did not pass the security
	 *             checks.
	 */
	public void setProcessingLevels(List<String> processingLevels) throws BmapException {
		this.processingLevels = Security.checkStringList(processingLevels);
	}

	/**
	 * 
	 * @return The subRegionNames.
	 */
	public List<String> getSubRegionNames() {
		return subRegionNames;
	}

	/**
	 * @param subRegionNames The subRegionNames to set.
	 * @throws BmapException When the specified string did not pass the security
	 *             checks.
	 */
	public void setSubRegionNames(List<String> subRegionNames) throws BmapException {
		this.subRegionNames = Security.checkStringList(subRegionNames);
	}

	/**
	 * @param collectionNames The collectionNames to set.
	 * @throws BmapException When the specified string did not pass the security
	 *             checks.
	 */
	public List<String> getCollectionNames() {
		return collectionNames;
	}

	/**
	 * @param collectionNames the collectionNames to set
	 */
	public void setCollectionNames(List<String> collectionNames) {
		this.collectionNames = collectionNames;
	}

	/**
	 * @return the privacy
	 */
	public Privacy getPrivacy() {
		return privacy;
	}

	/**
	 * @param privacy the privacy to set
	 */
	public void setPrivacy(Privacy privacy) {
		this.privacy = privacy;
	}

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class GranuleCriteria {\n");
		sb.append("    granuleName: ").append(Utils.toIndentedString(this.granuleName)).append("\n");
		sb.append("    startDate: ").append(Utils.toIndentedString(this.startDate)).append("\n");
		sb.append("    endDate: ").append(Utils.toIndentedString(this.endDate)).append("\n");
		sb.append("    productTypes: ").append(Utils.toIndentedString(this.productTypes)).append("\n");
		sb.append("    instrumentNames:").append(Utils.toIndentedString(this.instrumentNames)).append("\n");
		// sb.append("
		// location:").append(Utils.toIndentedString(this.location.toString())).append("\n");
		sb.append("    polarizations: ").append(Utils.toIndentedString(this.polarizations)).append("\n");
		sb.append("    geometryTypes: ").append(Utils.toIndentedString(this.geometryTypes)).append("\n");
		sb.append("    processingLevels:").append(Utils.toIndentedString(this.processingLevels)).append("\n");
		sb.append("    collectionNames: ").append(Utils.toIndentedString(this.collectionNames)).append("\n");
		sb.append("    subRegionNames: ").append(Utils.toIndentedString(this.subRegionNames)).append("\n");
		sb.append("    headingValues: ").append(Utils.toIndentedString(this.headingValues)).append("\n");
		sb.append("    privacy: ").append(Utils.toIndentedString(this.privacy)).append("\n");
		sb.append("    userId: ").append(Utils.toIndentedString(this.userId)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
