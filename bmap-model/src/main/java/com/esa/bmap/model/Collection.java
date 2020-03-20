package com.esa.bmap.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author edupin
 *
 */
@Entity
@Table(name = "collection")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Collection {

	/**
	 * Collection type ground campaign used in the categoryKeywords (Airborn and
	 * ROI)
	 */
	public static final String COLLECTION_TYPE_GROUND_CAMPAIGN = "GroundCampaign Data";

	/**
	 * Collection type user data (Airborn and ROI)
	 */
	public static final String COLLECTION_TYPE_USER_DATA = "USER DATA";
	/**
	 * Collection type satellite used in the categoryKeywords
	 */
	public static final String COLLECTION_TYPE_SATELLITE = "Satellite Data";

	/**
	 * Collection type L2 official products used in the categoryKeywords
	 */
	public static final String COLLECTION_TYPE_L2_OFFICAL_PRODUCTS = "L2_OFFICIAL_PRODUCTS";

	/**
	 * Collection type L2 user data used in the ingestion of private data
	 */
	public static final String COLLECTION_TYPE_L2_USER_DATAS = "L2_USER_DATA";

	/**
	 * Constant for the processingLevel L1
	 */
	public static final String PROCESSING_LEVEL_1 = "L1";
	/**
	 * Constant for the version id 1
	 */
	public static final String VERSION_ID_1 = "1";

	/**
	 * Unique number identifying the Collection.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	/**
	 * Name of the Collection (e.g; Tropisar).
	 */
	@Column(name = "shortName", nullable = false, unique = true)
	private String shortName = null;

	/**
	 * Collection dataSetId is or contains the shortName is used to delete the
	 * Collection.
	 */
	@Column(name = "dataSetId", nullable = true, unique = false)
	private String dataSetId = null;

	/**
	 * Version Id of the Collection (e.g; 1 ).
	 */
	@Column(name = "versionId", nullable = false, unique = false)
	private String versionId = null;

	/**
	 * collectionType of the Collection (e.g; ).
	 */
	@Column(name = "collectionType", nullable = false, unique = false)
	private String collectionType = null;

	/**
	 * urlCartoServer of the Collection (e.g; http://host/wms).
	 */
	@Column(name = "urlCartoServer", nullable = false, unique = false)
	private String urlCartoServer = null;

	/**
	 * categoryKeyWords of the Collection (e.g; GroundCampaign(AirborneData, Roi) ).
	 */
	@Column(name = "categoryKeyWords", nullable = false, unique = false)
	private String categoryKeyWords = null;

	/**
	 * processingLevel of the Collection (e.g; L1 ).
	 */
	@Column(name = "processingLevel", nullable = false, unique = false)
	private String processingLevel = null;

	/**
	 * Creates a collection with the specified parameters main called from the
	 * ingestion
	 * 
	 * @param id
	 * @param shortName
	 * @param versionId
	 * @param collectionType
	 * @param urlCartoServer
	 * @param categoryKeyWords
	 * @param processingLevel use one of the constant PROCESSING_LEVEL_XXX define in
	 *            this class
	 */
	public Collection(String shortName, String dataSetId, String versionId, String urlCartoServer,
			String categoryKeyWords, String processingLevel) {
		super();
		this.shortName = shortName;
		this.dataSetId = dataSetId;
		this.versionId = versionId;
		this.collectionType = categoryKeyWords;
		this.urlCartoServer = urlCartoServer;
		this.categoryKeyWords = categoryKeyWords;
		this.processingLevel = processingLevel;
	}

	/**
	 * Creates a collection with a shortName.
	 */
	public Collection(String shortName) {
		super();
		this.shortName = shortName;
	}

	/**
	 * Creates an empty collection.
	 */
	public Collection() {
		super();
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the shortName
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName the shortName to set
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * @return the versionId
	 */
	public String getVersionId() {
		return versionId;
	}

	/**
	 * @param versionId the versionId to set
	 */
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	/**
	 * @return the collectionType
	 */
	public String getCollectionType() {
		return collectionType;
	}

	/**
	 * @param collectionType the collectionType to set
	 */
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}

	/**
	 * @return the urlCartoServer
	 */
	public String getUrlCartoServer() {
		return urlCartoServer;
	}

	/**
	 * @param urlCartoServer the urlCartoServer to set
	 */
	public void setUrlCartoServer(String urlCartoServer) {
		this.urlCartoServer = urlCartoServer;
	}

	/**
	 * @return the categoryKeyWords
	 */
	public String getCategoryKeyWords() {
		return categoryKeyWords;
	}

	/**
	 * @param categoryKeyWords the categoryKeyWords to set
	 */
	public void setCategoryKeyWords(String categoryKeyWords) {
		this.categoryKeyWords = categoryKeyWords;
	}

	/**
	 * @return the processingLevel
	 */
	public String getProcessingLevel() {
		return processingLevel;
	}

	/**
	 * @param processingLevel the processingLevel to set
	 */
	public void setProcessingLevel(String processingLevel) {
		this.processingLevel = processingLevel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.versionId, this.shortName, this.collectionType, this.urlCartoServer,
				this.categoryKeyWords, this.processingLevel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Collection collection = (Collection) o;
		return Objects.equals(this.id, collection.id) && Objects.equals(this.shortName, collection.shortName)
				&& Objects.equals(this.versionId, collection.versionId)
				&& Objects.equals(this.collectionType, collection.collectionType)
				&& Objects.equals(this.urlCartoServer, collection.urlCartoServer)
				&& Objects.equals(this.categoryKeyWords, collection.categoryKeyWords)
				&& Objects.equals(this.processingLevel, collection.processingLevel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Collection {\n");
		sb.append("    id: ").append(Utils.toIndentedString(this.id)).append("\n");
		sb.append("    shortName: ").append(Utils.toIndentedString(this.shortName)).append("\n");
		sb.append("    versionId: ").append(Utils.toIndentedString(this.versionId)).append("\n");
		sb.append("    collectionType: ").append(Utils.toIndentedString(this.collectionType)).append("\n");
		sb.append("    urlCartoServer: ").append(Utils.toIndentedString(this.urlCartoServer)).append("\n");
		sb.append("    categoryKeyWords: ").append(Utils.toIndentedString(this.categoryKeyWords)).append("\n");
		sb.append("    processingLevel: ").append(Utils.toIndentedString(this.processingLevel)).append("\n");
		sb.append("}");
		return sb.toString();
	}

}
