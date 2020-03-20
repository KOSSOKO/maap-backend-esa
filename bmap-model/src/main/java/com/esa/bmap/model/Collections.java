package com.esa.bmap.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;


/**
 * Collections CMR
 * @author THBLED
 *
 */
@Entity
@Table(name = "collections")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Collections {
	
	/**
	 * Unique number identifying the instrument.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;
	
	
	/**
	 * Collection shortName
	 */
	@Column(name = "shortName", nullable = true)

	private String shortName = null;
	/**
	 * Collection dataSetId
	 */
	@Column(name = "dataSetId", nullable = true)
	private String dataSetId = null;
	/**
	 * Collection versionId.
	 */
	@Column(name = "versionId", nullable = true)
	private String versionId = null;
	/**
	 * collectionType 
	 */
	@Column(name = "collectionType", nullable = true)
	private String collectionType = null;
	
	
	/**
	 * URL of the COLLECTION.
	 */
	@Column(name = "collectionURL", nullable = true)
	private String collectionURL = null;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(Collections.class);

	/**
	 * Creates an empty Collections.
	 */
	public Collections() {
		super();
	}
	/**
	 * Creates an instrument with the specified parameters.
	 * 
	 * @param name
	 * Name of the instrument (e.g. P-band).
	 * @param description
	 * Description of the instrument.
	 * @throws BmapException
	 * When the name is null.
	 */
	public Collections(String shortName, String dataSetId,String versionId,String collectionType) throws BmapException {
		super();
		if (shortName != null) {
			this.shortName = shortName;
			this.dataSetId = dataSetId;
			this.versionId=versionId;
			this.collectionType=collectionType;
			
		}
		else {
			LOG.error(Common.getMessageValue("Collections.constructor.error"));
			throw new BmapException(Common.getMessageValue("Collections.constructor.error"));
		}
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getDataSetId() {
		return dataSetId;
	}
	public void setDataSetId(String dataSetId) {
		this.dataSetId = dataSetId;
	}
	public String getVersionId() {
		return versionId;
	}
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}
	public String getCollectionType() {
		return collectionType;
	}
	public void setCollectionType(String collectionType) {
		this.collectionType = collectionType;
	}
	
	
	
	public String getCollectionURL() {
		return collectionURL;
	}
	public void setCollectionURL(String collectionURL) {
		this.collectionURL = collectionURL;
	}
	@Override
	public String toString() {
		return "Collections [id=" + id + ", shortName=" + shortName + ", dataSetId=" + dataSetId + ", versionId="
				+ versionId + ", collectionType=" + collectionType + ", collectionURL=" + collectionURL + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collectionType == null) ? 0 : collectionType.hashCode());
		result = prime * result + ((collectionURL == null) ? 0 : collectionURL.hashCode());
		result = prime * result + ((dataSetId == null) ? 0 : dataSetId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
		result = prime * result + ((versionId == null) ? 0 : versionId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Collections other = (Collections) obj;
		if (collectionType == null) {
			if (other.collectionType != null)
				return false;
		} else if (!collectionType.equals(other.collectionType))
			return false;
		if (collectionURL == null) {
			if (other.collectionURL != null)
				return false;
		} else if (!collectionURL.equals(other.collectionURL))
			return false;
		if (dataSetId == null) {
			if (other.dataSetId != null)
				return false;
		} else if (!dataSetId.equals(other.dataSetId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		if (versionId == null) {
			if (other.versionId != null)
				return false;
		} else if (!versionId.equals(other.versionId))
			return false;
		return true;
	}
	
	
}
