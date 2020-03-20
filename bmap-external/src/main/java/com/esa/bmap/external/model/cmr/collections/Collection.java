//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//

package com.esa.bmap.external.model.cmr.collections;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * 
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;p xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;This entity contains brief description of collections
 *         including the dataset identifier, short and long names, and
 *         the version of the collection. The dataset identifier is
 *         the primary identifier used by ECHO to identify a
 *         collection. This also provides further description of the
 *         collection to include media, revision date, usage, and
 *         processing and archive centers etc.In this entity, the
 *         aggregation information such as total number of granules
 *         contained by this collection etc. are stored as well.&lt;/p&gt;
 * </pre>
 * 
 * 
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;p xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;Adopted from Release B Science Data Processing Segment
 *         (SDPS) for the ECS Project. Refer to 311-CD-008-001 May 15,
 *         1996.Additional fields are added to host the collection
 *         aggregation information, price, book keeping information,
 *         and various default setting etc. Collection's dataset ID
 *         attribute is added to hold an alternative unique identifier
 *         of the collection per provider.&lt;/p&gt;
 * </pre>
 * 
 * 
 * 
 * <p>
 * Classe Java pour Collection complex type.
 * 
 * <p>
 * Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette
 * classe.
 * 
 * <pre>
 * &lt;complexType name="Collection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShortName" type="{}CollectionShortName"/>
 *         &lt;element name="VersionId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="InsertTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DeleteTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="LongName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1024"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DataSetId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1030"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Description">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="12000"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DOI" type="{}DoiType" minOccurs="0"/>
 *         &lt;element name="CollectionDataType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="SCIENCE_QUALITY"/>
 *               &lt;enumeration value="NEAR_REAL_TIME"/>
 *               &lt;enumeration value="OTHER"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Orderable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RevisionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="SuggestedUsage" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="4000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProcessingCenter" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="240"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProcessingLevelId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProcessingLevelDescription" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ArchiveCenter" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="240"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VersionDescription" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="4000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CitationForExternalPublication" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="4000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CollectionState" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MaintenanceAndUpdateFrequency" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="RestrictionFlag" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="RestrictionComment" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="UseConstraints" type="{}UseConstraintsType" minOccurs="0"/>
 *         &lt;element name="Price" type="{}DollarAmount" minOccurs="0"/>
 *         &lt;element name="DataFormat" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SpatialKeywords" type="{}ListOfKeywords" minOccurs="0"/>
 *         &lt;element name="TemporalKeywords" type="{}ListOfKeywords" minOccurs="0"/>
 *         &lt;element name="Temporal" type="{}Temporal" minOccurs="0"/>
 *         &lt;element name="Contacts" type="{}ListOfContacts" minOccurs="0"/>
 *         &lt;element name="ScienceKeywords" type="{}ListOfScienceKeywords" minOccurs="0"/>
 *         &lt;element name="Platforms" type="{}ListOfPlatforms" minOccurs="0"/>
 *         &lt;element name="AdditionalAttributes" type="{}ListOfAdditionalAttributes" minOccurs="0"/>
 *         &lt;element name="CSDTDescriptions" type="{}ListOfCSDTDescriptions" minOccurs="0"/>
 *         &lt;element name="CollectionAssociations" type="{}ListOfCollectionAssociations" minOccurs="0"/>
 *         &lt;element name="Campaigns" type="{}ListOfCampaigns" minOccurs="0"/>
 *         &lt;element name="AlgorithmPackages" type="{}ListOfAlgorithmPackages" minOccurs="0"/>
 *         &lt;element name="TwoDCoordinateSystems" type="{}ListOfTwoDCoordinateSystems" minOccurs="0"/>
 *         &lt;element name="SpatialInfo" type="{}SpatialInfo" minOccurs="0"/>
 *         &lt;element name="OnlineAccessURLs" type="{}ListOfOnlineAccessURLs" minOccurs="0"/>
 *         &lt;element name="OnlineResources" type="{}ListOfOnlineResources" minOccurs="0"/>
 *         &lt;element name="AssociatedDIFs" type="{}ListOfAssociatedDIFs" minOccurs="0"/>
 *         &lt;element name="Spatial" type="{}Spatial" minOccurs="0"/>
 *         &lt;element name="MetadataStandardName" type="{}MetadataStandardName" minOccurs="0"/>
 *         &lt;element name="MetadataStandardVersion" type="{}MetadataStandardVersion" minOccurs="0"/>
 *         &lt;element name="ProductFlag" type="{}ProductFlag" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="AssociatedBrowseImages" type="{}ListOfProviderBrowseIds" minOccurs="0"/>
 *           &lt;element name="AssociatedBrowseImageUrls" type="{}ListOfProviderBrowseUrls" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Collection", propOrder = { "shortName", "versionId", "insertTime", "lastUpdate", "deleteTime",
		"longName", "dataSetId", "description", "doi", "collectionDataType", "orderable", "visible", "revisionDate",
		"suggestedUsage", "processingCenter", "processingLevelId", "processingLevelDescription", "archiveCenter",
		"versionDescription", "citationForExternalPublication", "collectionState", "maintenanceAndUpdateFrequency",
		"restrictionFlag", "restrictionComment", "useConstraints", "price", "dataFormat", "spatialKeywords",
		"temporalKeywords", "temporal", "contacts", "scienceKeywords", "platforms", "additionalAttributes",
		"csdtDescriptions", "collectionAssociations", "campaigns", "algorithmPackages", "twoDCoordinateSystems",
		"spatialInfo", "onlineAccessURLs", "onlineResources", "associatedDIFs", "spatial", "metadataStandardName",
		"metadataStandardVersion", "productFlag", "associatedBrowseImages", "associatedBrowseImageUrls" })
@XmlRootElement(name = "Collection")
public class Collection {


	@XmlElement(name = "ShortName", required = true)
	protected String shortName;
	@XmlElement(name = "VersionId", required = true)
	protected String versionId;
	@XmlElement(name = "InsertTime", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar insertTime;
	@XmlElement(name = "LastUpdate", required = true)
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar lastUpdate;
	@XmlElement(name = "DeleteTime")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar deleteTime;
	@XmlElement(name = "LongName", required = true)
	protected String longName;
	@XmlElement(name = "DataSetId", required = true)
	protected String dataSetId;
	@XmlElement(name = "Description", required = true)
	protected String description;
	@XmlElement(name = "DOI")
	protected DoiType doi;
	@XmlElement(name = "CollectionDataType")
	protected String collectionDataType;
	@XmlElement(name = "Orderable")
	protected Boolean orderable;
	@XmlElement(name = "Visible")
	protected Boolean visible;
	@XmlElement(name = "RevisionDate")
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar revisionDate;
	@XmlElement(name = "SuggestedUsage")
	protected String suggestedUsage;
	@XmlElement(name = "ProcessingCenter")
	protected String processingCenter;
	@XmlElement(name = "ProcessingLevelId")
	protected String processingLevelId;
	@XmlElement(name = "ProcessingLevelDescription")
	protected String processingLevelDescription;
	@XmlElement(name = "ArchiveCenter")
	protected String archiveCenter;
	@XmlElement(name = "VersionDescription")
	protected String versionDescription;
	@XmlElement(name = "CitationForExternalPublication")
	protected String citationForExternalPublication;
	@XmlElement(name = "CollectionState")
	protected String collectionState;
	@XmlElement(name = "MaintenanceAndUpdateFrequency")
	protected String maintenanceAndUpdateFrequency;
	@XmlElement(name = "RestrictionFlag")
	protected BigDecimal restrictionFlag;
	@XmlElement(name = "RestrictionComment")
	protected String restrictionComment;
	@XmlElement(name = "UseConstraints")
	protected UseConstraintsType useConstraints;
	@XmlElement(name = "Price")
	protected BigDecimal price;
	@XmlElement(name = "DataFormat")
	protected String dataFormat;
	@XmlElement(name = "SpatialKeywords")
	protected ListOfKeywords spatialKeywords;
	@XmlElement(name = "TemporalKeywords")
	protected ListOfKeywords temporalKeywords;
	@XmlElement(name = "Temporal")
	protected Temporal temporal;
	@XmlElement(name = "Contacts")
	protected ListOfContacts contacts;
	@XmlElement(name = "ScienceKeywords")
	protected ListOfScienceKeywords scienceKeywords;
	@XmlElement(name = "Platforms")
	protected ListOfPlatforms platforms;
	@XmlElement(name = "AdditionalAttributes")
	protected ListOfAdditionalAttributes additionalAttributes;
	@XmlElement(name = "CSDTDescriptions")
	protected ListOfCSDTDescriptions csdtDescriptions;
	@XmlElement(name = "CollectionAssociations")
	protected ListOfCollectionAssociations collectionAssociations;
	@XmlElement(name = "Campaigns")
	protected ListOfCampaigns campaigns;
	@XmlElement(name = "AlgorithmPackages")
	protected ListOfAlgorithmPackages algorithmPackages;
	@XmlElement(name = "TwoDCoordinateSystems")
	protected ListOfTwoDCoordinateSystems twoDCoordinateSystems;
	@XmlElement(name = "SpatialInfo")
	protected SpatialInfo spatialInfo;
	@XmlElement(name = "OnlineAccessURLs")
	protected ListOfOnlineAccessURLs onlineAccessURLs;
	@XmlElement(name = "OnlineResources")
	protected ListOfOnlineResources onlineResources;
	@XmlElement(name = "AssociatedDIFs")
	protected ListOfAssociatedDIFs associatedDIFs;
	@XmlElement(name = "Spatial")
	protected Spatial spatial;
	@XmlElement(name = "MetadataStandardName")
	protected String metadataStandardName;
	@XmlElement(name = "MetadataStandardVersion")
	protected String metadataStandardVersion;
	@XmlElement(name = "ProductFlag")
	@XmlSchemaType(name = "string")
	protected ProductFlag productFlag;
	@XmlElement(name = "AssociatedBrowseImages")
	protected ListOfProviderBrowseIds associatedBrowseImages;
	@XmlElement(name = "AssociatedBrowseImageUrls")
	protected ListOfProviderBrowseUrls associatedBrowseImageUrls;

	/**
	 * Obtient la valeur de la propri�t� shortName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * D�finit la valeur de la propri�t� shortName.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setShortName(String value) {
		this.shortName = value;
	}

	/**
	 * Obtient la valeur de la propri�t� versionId.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVersionId() {
		return versionId;
	}

	/**
	 * D�finit la valeur de la propri�t� versionId.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setVersionId(String value) {
		this.versionId = value;
	}

	/**
	 * Obtient la valeur de la propri�t� insertTime.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getInsertTime() {
		return insertTime;
	}

	/**
	 * D�finit la valeur de la propri�t� insertTime.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setInsertTime(XMLGregorianCalendar value) {
		this.insertTime = value;
	}

	/**
	 * Obtient la valeur de la propri�t� lastUpdate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * D�finit la valeur de la propri�t� lastUpdate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setLastUpdate(XMLGregorianCalendar value) {
		this.lastUpdate = value;
	}

	/**
	 * Obtient la valeur de la propri�t� deleteTime.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getDeleteTime() {
		return deleteTime;
	}

	/**
	 * D�finit la valeur de la propri�t� deleteTime.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setDeleteTime(XMLGregorianCalendar value) {
		this.deleteTime = value;
	}

	/**
	 * Obtient la valeur de la propri�t� longName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLongName() {
		return longName;
	}

	/**
	 * D�finit la valeur de la propri�t� longName.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setLongName(String value) {
		this.longName = value;
	}

	/**
	 * Obtient la valeur de la propri�t� dataSetId.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDataSetId() {
		return dataSetId;
	}

	/**
	 * D�finit la valeur de la propri�t� dataSetId.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setDataSetId(String value) {
		this.dataSetId = value;
	}

	/**
	 * Obtient la valeur de la propri�t� description.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * D�finit la valeur de la propri�t� description.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setDescription(String value) {
		this.description = value;
	}

	/**
	 * Obtient la valeur de la propri�t� doi.
	 * 
	 * @return possible object is {@link DoiType }
	 * 
	 */
	public DoiType getDOI() {
		return doi;
	}

	/**
	 * D�finit la valeur de la propri�t� doi.
	 * 
	 * @param value allowed object is {@link DoiType }
	 * 
	 */
	public void setDOI(DoiType value) {
		this.doi = value;
	}

	/**
	 * Obtient la valeur de la propri�t� collectionDataType.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectionDataType() {
		return collectionDataType;
	}

	/**
	 * D�finit la valeur de la propri�t� collectionDataType.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setCollectionDataType(String value) {
		this.collectionDataType = value;
	}

	/**
	 * Obtient la valeur de la propri�t� orderable.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isOrderable() {
		return orderable;
	}

	/**
	 * D�finit la valeur de la propri�t� orderable.
	 * 
	 * @param value allowed object is {@link Boolean }
	 * 
	 */
	public void setOrderable(Boolean value) {
		this.orderable = value;
	}

	/**
	 * Obtient la valeur de la propri�t� visible.
	 * 
	 * @return possible object is {@link Boolean }
	 * 
	 */
	public Boolean isVisible() {
		return visible;
	}

	/**
	 * D�finit la valeur de la propri�t� visible.
	 * 
	 * @param value allowed object is {@link Boolean }
	 * 
	 */
	public void setVisible(Boolean value) {
		this.visible = value;
	}

	/**
	 * Obtient la valeur de la propri�t� revisionDate.
	 * 
	 * @return possible object is {@link XMLGregorianCalendar }
	 * 
	 */
	public XMLGregorianCalendar getRevisionDate() {
		return revisionDate;
	}

	/**
	 * D�finit la valeur de la propri�t� revisionDate.
	 * 
	 * @param value allowed object is {@link XMLGregorianCalendar }
	 * 
	 */
	public void setRevisionDate(XMLGregorianCalendar value) {
		this.revisionDate = value;
	}

	/**
	 * Obtient la valeur de la propri�t� suggestedUsage.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSuggestedUsage() {
		return suggestedUsage;
	}

	/**
	 * D�finit la valeur de la propri�t� suggestedUsage.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setSuggestedUsage(String value) {
		this.suggestedUsage = value;
	}

	/**
	 * Obtient la valeur de la propri�t� processingCenter.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProcessingCenter() {
		return processingCenter;
	}

	/**
	 * D�finit la valeur de la propri�t� processingCenter.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setProcessingCenter(String value) {
		this.processingCenter = value;
	}

	/**
	 * Obtient la valeur de la propri�t� processingLevelId.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProcessingLevelId() {
		return processingLevelId;
	}

	/**
	 * D�finit la valeur de la propri�t� processingLevelId.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setProcessingLevelId(String value) {
		this.processingLevelId = value;
	}

	/**
	 * Obtient la valeur de la propri�t� processingLevelDescription.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getProcessingLevelDescription() {
		return processingLevelDescription;
	}

	/**
	 * D�finit la valeur de la propri�t� processingLevelDescription.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setProcessingLevelDescription(String value) {
		this.processingLevelDescription = value;
	}

	/**
	 * Obtient la valeur de la propri�t� archiveCenter.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArchiveCenter() {
		return archiveCenter;
	}

	/**
	 * D�finit la valeur de la propri�t� archiveCenter.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setArchiveCenter(String value) {
		this.archiveCenter = value;
	}

	/**
	 * Obtient la valeur de la propri�t� versionDescription.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVersionDescription() {
		return versionDescription;
	}

	/**
	 * D�finit la valeur de la propri�t� versionDescription.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setVersionDescription(String value) {
		this.versionDescription = value;
	}

	/**
	 * Obtient la valeur de la propri�t� citationForExternalPublication.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCitationForExternalPublication() {
		return citationForExternalPublication;
	}

	/**
	 * D�finit la valeur de la propri�t� citationForExternalPublication.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setCitationForExternalPublication(String value) {
		this.citationForExternalPublication = value;
	}

	/**
	 * Obtient la valeur de la propri�t� collectionState.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCollectionState() {
		return collectionState;
	}

	/**
	 * D�finit la valeur de la propri�t� collectionState.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setCollectionState(String value) {
		this.collectionState = value;
	}

	/**
	 * Obtient la valeur de la propri�t� maintenanceAndUpdateFrequency.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMaintenanceAndUpdateFrequency() {
		return maintenanceAndUpdateFrequency;
	}

	/**
	 * D�finit la valeur de la propri�t� maintenanceAndUpdateFrequency.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setMaintenanceAndUpdateFrequency(String value) {
		this.maintenanceAndUpdateFrequency = value;
	}

	/**
	 * Obtient la valeur de la propri�t� restrictionFlag.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getRestrictionFlag() {
		return restrictionFlag;
	}

	/**
	 * D�finit la valeur de la propri�t� restrictionFlag.
	 * 
	 * @param value allowed object is {@link BigDecimal }
	 * 
	 */
	public void setRestrictionFlag(BigDecimal value) {
		this.restrictionFlag = value;
	}

	/**
	 * Obtient la valeur de la propri�t� restrictionComment.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRestrictionComment() {
		return restrictionComment;
	}

	/**
	 * D�finit la valeur de la propri�t� restrictionComment.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setRestrictionComment(String value) {
		this.restrictionComment = value;
	}

	/**
	 * Obtient la valeur de la propri�t� useConstraints.
	 * 
	 * @return possible object is {@link UseConstraintsType }
	 * 
	 */
	public UseConstraintsType getUseConstraints() {
		return useConstraints;
	}

	/**
	 * D�finit la valeur de la propri�t� useConstraints.
	 * 
	 * @param value allowed object is {@link UseConstraintsType }
	 * 
	 */
	public void setUseConstraints(UseConstraintsType value) {
		this.useConstraints = value;
	}

	/**
	 * Obtient la valeur de la propri�t� price.
	 * 
	 * @return possible object is {@link BigDecimal }
	 * 
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * D�finit la valeur de la propri�t� price.
	 * 
	 * @param value allowed object is {@link BigDecimal }
	 * 
	 */
	public void setPrice(BigDecimal value) {
		this.price = value;
	}

	/**
	 * Obtient la valeur de la propri�t� dataFormat.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDataFormat() {
		return dataFormat;
	}

	/**
	 * D�finit la valeur de la propri�t� dataFormat.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setDataFormat(String value) {
		this.dataFormat = value;
	}

	/**
	 * Obtient la valeur de la propri�t� spatialKeywords.
	 * 
	 * @return possible object is {@link ListOfKeywords }
	 * 
	 */
	public ListOfKeywords getSpatialKeywords() {
		return spatialKeywords;
	}

	/**
	 * D�finit la valeur de la propri�t� spatialKeywords.
	 * 
	 * @param value allowed object is {@link ListOfKeywords }
	 * 
	 */
	public void setSpatialKeywords(ListOfKeywords value) {
		this.spatialKeywords = value;
	}

	/**
	 * Obtient la valeur de la propri�t� temporalKeywords.
	 * 
	 * @return possible object is {@link ListOfKeywords }
	 * 
	 */
	public ListOfKeywords getTemporalKeywords() {
		return temporalKeywords;
	}

	/**
	 * D�finit la valeur de la propri�t� temporalKeywords.
	 * 
	 * @param value allowed object is {@link ListOfKeywords }
	 * 
	 */
	public void setTemporalKeywords(ListOfKeywords value) {
		this.temporalKeywords = value;
	}

	/**
	 * Obtient la valeur de la propri�t� temporal.
	 * 
	 * @return possible object is {@link Temporal }
	 * 
	 */
	public Temporal getTemporal() {
		return temporal;
	}

	/**
	 * D�finit la valeur de la propri�t� temporal.
	 * 
	 * @param value allowed object is {@link Temporal }
	 * 
	 */
	public void setTemporal(Temporal value) {
		this.temporal = value;
	}

	/**
	 * Obtient la valeur de la propri�t� contacts.
	 * 
	 * @return possible object is {@link ListOfContacts }
	 * 
	 */
	public ListOfContacts getContacts() {
		return contacts;
	}

	/**
	 * D�finit la valeur de la propri�t� contacts.
	 * 
	 * @param value allowed object is {@link ListOfContacts }
	 * 
	 */
	public void setContacts(ListOfContacts value) {
		this.contacts = value;
	}

	/**
	 * Obtient la valeur de la propri�t� scienceKeywords.
	 * 
	 * @return possible object is {@link ListOfScienceKeywords }
	 * 
	 */
	public ListOfScienceKeywords getScienceKeywords() {
		return scienceKeywords;
	}

	/**
	 * D�finit la valeur de la propri�t� scienceKeywords.
	 * 
	 * @param value allowed object is {@link ListOfScienceKeywords }
	 * 
	 */
	public void setScienceKeywords(ListOfScienceKeywords value) {
		this.scienceKeywords = value;
	}

	/**
	 * Obtient la valeur de la propri�t� platforms.
	 * 
	 * @return possible object is {@link ListOfPlatforms }
	 * 
	 */
	public ListOfPlatforms getPlatforms() {
		return platforms;
	}

	/**
	 * D�finit la valeur de la propri�t� platforms.
	 * 
	 * @param value allowed object is {@link ListOfPlatforms }
	 * 
	 */
	public void setPlatforms(ListOfPlatforms value) {
		this.platforms = value;
	}

	/**
	 * Obtient la valeur de la propri�t� additionalAttributes.
	 * 
	 * @return possible object is {@link ListOfAdditionalAttributes }
	 * 
	 */
	public ListOfAdditionalAttributes getAdditionalAttributes() {
		return additionalAttributes;
	}

	/**
	 * D�finit la valeur de la propri�t� additionalAttributes.
	 * 
	 * @param value allowed object is {@link ListOfAdditionalAttributes }
	 * 
	 */
	public void setAdditionalAttributes(ListOfAdditionalAttributes value) {
		this.additionalAttributes = value;
	}

	/**
	 * Obtient la valeur de la propri�t� csdtDescriptions.
	 * 
	 * @return possible object is {@link ListOfCSDTDescriptions }
	 * 
	 */
	public ListOfCSDTDescriptions getCSDTDescriptions() {
		return csdtDescriptions;
	}

	/**
	 * D�finit la valeur de la propri�t� csdtDescriptions.
	 * 
	 * @param value allowed object is {@link ListOfCSDTDescriptions }
	 * 
	 */
	public void setCSDTDescriptions(ListOfCSDTDescriptions value) {
		this.csdtDescriptions = value;
	}

	/**
	 * Obtient la valeur de la propri�t� collectionAssociations.
	 * 
	 * @return possible object is {@link ListOfCollectionAssociations }
	 * 
	 */
	public ListOfCollectionAssociations getCollectionAssociations() {
		return collectionAssociations;
	}

	/**
	 * D�finit la valeur de la propri�t� collectionAssociations.
	 * 
	 * @param value allowed object is {@link ListOfCollectionAssociations }
	 * 
	 */
	public void setCollectionAssociations(ListOfCollectionAssociations value) {
		this.collectionAssociations = value;
	}

	/**
	 * Obtient la valeur de la propri�t� campaigns.
	 * 
	 * @return possible object is {@link ListOfCampaigns }
	 * 
	 */
	public ListOfCampaigns getCampaigns() {
		return campaigns;
	}

	/**
	 * D�finit la valeur de la propri�t� campaigns.
	 * 
	 * @param value allowed object is {@link ListOfCampaigns }
	 * 
	 */
	public void setCampaigns(ListOfCampaigns value) {
		this.campaigns = value;
	}

	/**
	 * Obtient la valeur de la propri�t� algorithmPackages.
	 * 
	 * @return possible object is {@link ListOfAlgorithmPackages }
	 * 
	 */
	public ListOfAlgorithmPackages getAlgorithmPackages() {
		return algorithmPackages;
	}

	/**
	 * D�finit la valeur de la propri�t� algorithmPackages.
	 * 
	 * @param value allowed object is {@link ListOfAlgorithmPackages }
	 * 
	 */
	public void setAlgorithmPackages(ListOfAlgorithmPackages value) {
		this.algorithmPackages = value;
	}

	/**
	 * Obtient la valeur de la propri�t� twoDCoordinateSystems.
	 * 
	 * @return possible object is {@link ListOfTwoDCoordinateSystems }
	 * 
	 */
	public ListOfTwoDCoordinateSystems getTwoDCoordinateSystems() {
		return twoDCoordinateSystems;
	}

	/**
	 * D�finit la valeur de la propri�t� twoDCoordinateSystems.
	 * 
	 * @param value allowed object is {@link ListOfTwoDCoordinateSystems }
	 * 
	 */
	public void setTwoDCoordinateSystems(ListOfTwoDCoordinateSystems value) {
		this.twoDCoordinateSystems = value;
	}

	/**
	 * Obtient la valeur de la propri�t� spatialInfo.
	 * 
	 * @return possible object is {@link SpatialInfo }
	 * 
	 */
	public SpatialInfo getSpatialInfo() {
		return spatialInfo;
	}

	/**
	 * D�finit la valeur de la propri�t� spatialInfo.
	 * 
	 * @param value allowed object is {@link SpatialInfo }
	 * 
	 */
	public void setSpatialInfo(SpatialInfo value) {
		this.spatialInfo = value;
	}

	/**
	 * Obtient la valeur de la propri�t� onlineAccessURLs.
	 * 
	 * @return possible object is {@link ListOfOnlineAccessURLs }
	 * 
	 */
	public ListOfOnlineAccessURLs getOnlineAccessURLs() {
		return onlineAccessURLs;
	}

	/**
	 * D�finit la valeur de la propri�t� onlineAccessURLs.
	 * 
	 * @param value allowed object is {@link ListOfOnlineAccessURLs }
	 * 
	 */
	public void setOnlineAccessURLs(ListOfOnlineAccessURLs value) {
		this.onlineAccessURLs = value;
	}

	/**
	 * Obtient la valeur de la propri�t� onlineResources.
	 * 
	 * @return possible object is {@link ListOfOnlineResources }
	 * 
	 */
	public ListOfOnlineResources getOnlineResources() {
		return onlineResources;
	}

	/**
	 * D�finit la valeur de la propri�t� onlineResources.
	 * 
	 * @param value allowed object is {@link ListOfOnlineResources }
	 * 
	 */
	public void setOnlineResources(ListOfOnlineResources value) {
		this.onlineResources = value;
	}

	/**
	 * Obtient la valeur de la propri�t� associatedDIFs.
	 * 
	 * @return possible object is {@link ListOfAssociatedDIFs }
	 * 
	 */
	public ListOfAssociatedDIFs getAssociatedDIFs() {
		return associatedDIFs;
	}

	/**
	 * D�finit la valeur de la propri�t� associatedDIFs.
	 * 
	 * @param value allowed object is {@link ListOfAssociatedDIFs }
	 * 
	 */
	public void setAssociatedDIFs(ListOfAssociatedDIFs value) {
		this.associatedDIFs = value;
	}

	/**
	 * Obtient la valeur de la propri�t� spatial.
	 * 
	 * @return possible object is {@link Spatial }
	 * 
	 */
	public Spatial getSpatial() {
		return spatial;
	}

	/**
	 * D�finit la valeur de la propri�t� spatial.
	 * 
	 * @param value allowed object is {@link Spatial }
	 * 
	 */
	public void setSpatial(Spatial value) {
		this.spatial = value;
	}

	/**
	 * Obtient la valeur de la propri�t� metadataStandardName.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMetadataStandardName() {
		return metadataStandardName;
	}

	/**
	 * D�finit la valeur de la propri�t� metadataStandardName.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setMetadataStandardName(String value) {
		this.metadataStandardName = value;
	}

	/**
	 * Obtient la valeur de la propri�t� metadataStandardVersion.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMetadataStandardVersion() {
		return metadataStandardVersion;
	}

	/**
	 * D�finit la valeur de la propri�t� metadataStandardVersion.
	 * 
	 * @param value allowed object is {@link String }
	 * 
	 */
	public void setMetadataStandardVersion(String value) {
		this.metadataStandardVersion = value;
	}

	/**
	 * Obtient la valeur de la propri�t� productFlag.
	 * 
	 * @return possible object is {@link ProductFlag }
	 * 
	 */
	public ProductFlag getProductFlag() {
		return productFlag;
	}

	/**
	 * D�finit la valeur de la propri�t� productFlag.
	 * 
	 * @param value allowed object is {@link ProductFlag }
	 * 
	 */
	public void setProductFlag(ProductFlag value) {
		this.productFlag = value;
	}

	/**
	 * Obtient la valeur de la propri�t� associatedBrowseImages.
	 * 
	 * @return possible object is {@link ListOfProviderBrowseIds }
	 * 
	 */
	public ListOfProviderBrowseIds getAssociatedBrowseImages() {
		return associatedBrowseImages;
	}

	/**
	 * D�finit la valeur de la propri�t� associatedBrowseImages.
	 * 
	 * @param value allowed object is {@link ListOfProviderBrowseIds }
	 * 
	 */
	public void setAssociatedBrowseImages(ListOfProviderBrowseIds value) {
		this.associatedBrowseImages = value;
	}

	/**
	 * Obtient la valeur de la propri�t� associatedBrowseImageUrls.
	 * 
	 * @return possible object is {@link ListOfProviderBrowseUrls }
	 * 
	 */
	public ListOfProviderBrowseUrls getAssociatedBrowseImageUrls() {
		return associatedBrowseImageUrls;
	}

	/**
	 * D�finit la valeur de la propri�t� associatedBrowseImageUrls.
	 * 
	 * @param value allowed object is {@link ListOfProviderBrowseUrls }
	 * 
	 */
	public void setAssociatedBrowseImageUrls(ListOfProviderBrowseUrls value) {
		this.associatedBrowseImageUrls = value;
	}

}
