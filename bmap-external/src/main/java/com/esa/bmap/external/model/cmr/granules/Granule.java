//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

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
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;html:p xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;Stores the basic descriptive characteristics
 *         associated with a granule.&lt;/html:p&gt;
 * </pre>
 * 
 *         
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;html:p xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;Adopted from Release B Science Data Processing
 *         Segment (SDPS) for the ECS Project. Refer to 311-CD-008-001
 *         May 15, 1996. Additional attributes been added for book
 *         keeping information.&lt;/html:p&gt;
 * </pre>
 * 
 *       
 * 
 * <p>Classe Java pour Granule complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Granule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GranuleUR" type="{}GranuleUR"/>
 *         &lt;element name="InsertTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DeleteTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="Collection" type="{}CollectionRef"/>
 *         &lt;element name="RestrictionFlag" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="RestrictionComment" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="1024"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DataGranule" type="{}DataGranule" minOccurs="0"/>
 *         &lt;element name="PGEVersionClass" type="{}PGEVersionClass" minOccurs="0"/>
 *         &lt;element name="Temporal" type="{}Temporal" minOccurs="0"/>
 *         &lt;element name="Spatial" type="{}Spatial" minOccurs="0"/>
 *         &lt;element name="OrbitCalculatedSpatialDomains" type="{}ListOfOrbitCalculatedSpatialDomains" minOccurs="0"/>
 *         &lt;element name="MeasuredParameters" type="{}ListOfMeasuredParameters" minOccurs="0"/>
 *         &lt;element name="Platforms" type="{}ListOfPlatformRefs" minOccurs="0"/>
 *         &lt;element name="Campaigns" type="{}ListOfCampaignRefs" minOccurs="0"/>
 *         &lt;element name="AdditionalAttributes" type="{}ListOfAdditionalAttributeRefs" minOccurs="0"/>
 *         &lt;element name="InputGranules" type="{}ListOfInputGranules" minOccurs="0"/>
 *         &lt;element name="TwoDCoordinateSystem" type="{}TwoDCoordinateSystem" minOccurs="0"/>
 *         &lt;element name="Price" type="{}DollarAmount" minOccurs="0"/>
 *         &lt;element name="OnlineAccessURLs" type="{}ListOfOnlineAccessURLs" minOccurs="0"/>
 *         &lt;element name="OnlineResources" type="{}ListOfOnlineResources" minOccurs="0"/>
 *         &lt;element name="Orderable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="DataFormat" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="CloudCover" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MetadataStandardName" type="{}MetadataStandardName" minOccurs="0"/>
 *         &lt;element name="MetadataStandardVersion" type="{}MetadataStandardVersion" minOccurs="0"/>
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
@XmlType(name = "Granule", propOrder = {
    "granuleUR",
    "insertTime",
    "lastUpdate",
    "deleteTime",
    "collection",
    "restrictionFlag",
    "restrictionComment",
    "dataGranule",
    "pgeVersionClass",
    "temporal",
    "spatial",
    "orbitCalculatedSpatialDomains",
    "measuredParameters",
    "platforms",
    "campaigns",
    "additionalAttributes",
    "inputGranules",
    "twoDCoordinateSystem",
    "price",
    "onlineAccessURLs",
    "onlineResources",
    "orderable",
    "dataFormat",
    "visible",
    "cloudCover",
    "metadataStandardName",
    "metadataStandardVersion",
    "associatedBrowseImages",
    "associatedBrowseImageUrls"
})
@XmlRootElement(name="Granule")
public class Granule {

    @XmlElement(name = "GranuleUR", required = true)
    protected String granuleUR;
    @XmlElement(name = "InsertTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar insertTime;
    @XmlElement(name = "LastUpdate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    @XmlElement(name = "DeleteTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deleteTime;
    @XmlElement(name = "Collection", required = true)
    protected CollectionRef collection;
    @XmlElement(name = "RestrictionFlag")
    protected BigDecimal restrictionFlag;
    @XmlElement(name = "RestrictionComment")
    protected String restrictionComment;
    @XmlElement(name = "DataGranule")
    protected DataGranule dataGranule;
    @XmlElement(name = "PGEVersionClass")
    protected PGEVersionClass pgeVersionClass;
    @XmlElement(name = "Temporal")
    protected Temporal temporal;
    @XmlElement(name = "Spatial")
    protected Spatial spatial;
    @XmlElement(name = "OrbitCalculatedSpatialDomains")
    protected ListOfOrbitCalculatedSpatialDomains orbitCalculatedSpatialDomains;
    @XmlElement(name = "MeasuredParameters")
    protected ListOfMeasuredParameters measuredParameters;
    @XmlElement(name = "Platforms")
    protected ListOfPlatformRefs platforms;
    @XmlElement(name = "Campaigns")
    protected ListOfCampaignRefs campaigns;
    @XmlElement(name = "AdditionalAttributes")
    protected ListOfAdditionalAttributeRefs additionalAttributes;
    @XmlElement(name = "InputGranules")
    protected ListOfInputGranules inputGranules;
    @XmlElement(name = "TwoDCoordinateSystem")
    protected TwoDCoordinateSystem twoDCoordinateSystem;
    @XmlElement(name = "Price")
    protected BigDecimal price;
    @XmlElement(name = "OnlineAccessURLs")
    protected ListOfOnlineAccessURLs onlineAccessURLs;
    @XmlElement(name = "OnlineResources")
    protected ListOfOnlineResources onlineResources;
    @XmlElement(name = "Orderable")
    protected Boolean orderable;
    @XmlElement(name = "DataFormat")
    protected String dataFormat;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "CloudCover")
    protected BigDecimal cloudCover;
    @XmlElement(name = "MetadataStandardName")
    protected String metadataStandardName;
    @XmlElement(name = "MetadataStandardVersion")
    protected String metadataStandardVersion;
    @XmlElement(name = "AssociatedBrowseImages")
    protected ListOfProviderBrowseIds associatedBrowseImages;
    @XmlElement(name = "AssociatedBrowseImageUrls")
    protected ListOfProviderBrowseUrls associatedBrowseImageUrls;

    /**
     * Obtient la valeur de la propri�t� granuleUR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGranuleUR() {
        return granuleUR;
    }

    /**
     * D�finit la valeur de la propri�t� granuleUR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGranuleUR(String value) {
        this.granuleUR = value;
    }

    /**
     * Obtient la valeur de la propri�t� insertTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsertTime() {
        return insertTime;
    }

    /**
     * D�finit la valeur de la propri�t� insertTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsertTime(XMLGregorianCalendar value) {
        this.insertTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� lastUpdate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * D�finit la valeur de la propri�t� lastUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    /**
     * Obtient la valeur de la propri�t� deleteTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeleteTime() {
        return deleteTime;
    }

    /**
     * D�finit la valeur de la propri�t� deleteTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeleteTime(XMLGregorianCalendar value) {
        this.deleteTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� collection.
     * 
     * @return
     *     possible object is
     *     {@link CollectionRef }
     *     
     */
    public CollectionRef getCollection() {
        return collection;
    }

    /**
     * D�finit la valeur de la propri�t� collection.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionRef }
     *     
     */
    public void setCollection(CollectionRef value) {
        this.collection = value;
    }

    /**
     * Obtient la valeur de la propri�t� restrictionFlag.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRestrictionFlag() {
        return restrictionFlag;
    }

    /**
     * D�finit la valeur de la propri�t� restrictionFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRestrictionFlag(BigDecimal value) {
        this.restrictionFlag = value;
    }

    /**
     * Obtient la valeur de la propri�t� restrictionComment.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRestrictionComment() {
        return restrictionComment;
    }

    /**
     * D�finit la valeur de la propri�t� restrictionComment.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRestrictionComment(String value) {
        this.restrictionComment = value;
    }

    /**
     * Obtient la valeur de la propri�t� dataGranule.
     * 
     * @return
     *     possible object is
     *     {@link DataGranule }
     *     
     */
    public DataGranule getDataGranule() {
        return dataGranule;
    }

    /**
     * D�finit la valeur de la propri�t� dataGranule.
     * 
     * @param value
     *     allowed object is
     *     {@link DataGranule }
     *     
     */
    public void setDataGranule(DataGranule value) {
        this.dataGranule = value;
    }

    /**
     * Obtient la valeur de la propri�t� pgeVersionClass.
     * 
     * @return
     *     possible object is
     *     {@link PGEVersionClass }
     *     
     */
    public PGEVersionClass getPGEVersionClass() {
        return pgeVersionClass;
    }

    /**
     * D�finit la valeur de la propri�t� pgeVersionClass.
     * 
     * @param value
     *     allowed object is
     *     {@link PGEVersionClass }
     *     
     */
    public void setPGEVersionClass(PGEVersionClass value) {
        this.pgeVersionClass = value;
    }

    /**
     * Obtient la valeur de la propri�t� temporal.
     * 
     * @return
     *     possible object is
     *     {@link Temporal }
     *     
     */
    public Temporal getTemporal() {
        return temporal;
    }

    /**
     * D�finit la valeur de la propri�t� temporal.
     * 
     * @param value
     *     allowed object is
     *     {@link Temporal }
     *     
     */
    public void setTemporal(Temporal value) {
        this.temporal = value;
    }

    /**
     * Obtient la valeur de la propri�t� spatial.
     * 
     * @return
     *     possible object is
     *     {@link Spatial }
     *     
     */
    public Spatial getSpatial() {
        return spatial;
    }

    /**
     * D�finit la valeur de la propri�t� spatial.
     * 
     * @param value
     *     allowed object is
     *     {@link Spatial }
     *     
     */
    public void setSpatial(Spatial value) {
        this.spatial = value;
    }

    /**
     * Obtient la valeur de la propri�t� orbitCalculatedSpatialDomains.
     * 
     * @return
     *     possible object is
     *     {@link ListOfOrbitCalculatedSpatialDomains }
     *     
     */
    public ListOfOrbitCalculatedSpatialDomains getOrbitCalculatedSpatialDomains() {
        return orbitCalculatedSpatialDomains;
    }

    /**
     * D�finit la valeur de la propri�t� orbitCalculatedSpatialDomains.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfOrbitCalculatedSpatialDomains }
     *     
     */
    public void setOrbitCalculatedSpatialDomains(ListOfOrbitCalculatedSpatialDomains value) {
        this.orbitCalculatedSpatialDomains = value;
    }

    /**
     * Obtient la valeur de la propri�t� measuredParameters.
     * 
     * @return
     *     possible object is
     *     {@link ListOfMeasuredParameters }
     *     
     */
    public ListOfMeasuredParameters getMeasuredParameters() {
        return measuredParameters;
    }

    /**
     * D�finit la valeur de la propri�t� measuredParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfMeasuredParameters }
     *     
     */
    public void setMeasuredParameters(ListOfMeasuredParameters value) {
        this.measuredParameters = value;
    }

    /**
     * Obtient la valeur de la propri�t� platforms.
     * 
     * @return
     *     possible object is
     *     {@link ListOfPlatformRefs }
     *     
     */
    public ListOfPlatformRefs getPlatforms() {
        return platforms;
    }

    /**
     * D�finit la valeur de la propri�t� platforms.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfPlatformRefs }
     *     
     */
    public void setPlatforms(ListOfPlatformRefs value) {
        this.platforms = value;
    }

    /**
     * Obtient la valeur de la propri�t� campaigns.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCampaignRefs }
     *     
     */
    public ListOfCampaignRefs getCampaigns() {
        return campaigns;
    }

    /**
     * D�finit la valeur de la propri�t� campaigns.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCampaignRefs }
     *     
     */
    public void setCampaigns(ListOfCampaignRefs value) {
        this.campaigns = value;
    }

    /**
     * Obtient la valeur de la propri�t� additionalAttributes.
     * 
     * @return
     *     possible object is
     *     {@link ListOfAdditionalAttributeRefs }
     *     
     */
    public ListOfAdditionalAttributeRefs getAdditionalAttributes() {
        return additionalAttributes;
    }

    /**
     * D�finit la valeur de la propri�t� additionalAttributes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfAdditionalAttributeRefs }
     *     
     */
    public void setAdditionalAttributes(ListOfAdditionalAttributeRefs value) {
        this.additionalAttributes = value;
    }

    /**
     * Obtient la valeur de la propri�t� inputGranules.
     * 
     * @return
     *     possible object is
     *     {@link ListOfInputGranules }
     *     
     */
    public ListOfInputGranules getInputGranules() {
        return inputGranules;
    }

    /**
     * D�finit la valeur de la propri�t� inputGranules.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfInputGranules }
     *     
     */
    public void setInputGranules(ListOfInputGranules value) {
        this.inputGranules = value;
    }

    /**
     * Obtient la valeur de la propri�t� twoDCoordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link TwoDCoordinateSystem }
     *     
     */
    public TwoDCoordinateSystem getTwoDCoordinateSystem() {
        return twoDCoordinateSystem;
    }

    /**
     * D�finit la valeur de la propri�t� twoDCoordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoDCoordinateSystem }
     *     
     */
    public void setTwoDCoordinateSystem(TwoDCoordinateSystem value) {
        this.twoDCoordinateSystem = value;
    }

    /**
     * Obtient la valeur de la propri�t� price.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * D�finit la valeur de la propri�t� price.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPrice(BigDecimal value) {
        this.price = value;
    }

    /**
     * Obtient la valeur de la propri�t� onlineAccessURLs.
     * 
     * @return
     *     possible object is
     *     {@link ListOfOnlineAccessURLs }
     *     
     */
    public ListOfOnlineAccessURLs getOnlineAccessURLs() {
        return onlineAccessURLs;
    }

    /**
     * D�finit la valeur de la propri�t� onlineAccessURLs.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfOnlineAccessURLs }
     *     
     */
    public void setOnlineAccessURLs(ListOfOnlineAccessURLs value) {
        this.onlineAccessURLs = value;
    }

    /**
     * Obtient la valeur de la propri�t� onlineResources.
     * 
     * @return
     *     possible object is
     *     {@link ListOfOnlineResources }
     *     
     */
    public ListOfOnlineResources getOnlineResources() {
        return onlineResources;
    }

    /**
     * D�finit la valeur de la propri�t� onlineResources.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfOnlineResources }
     *     
     */
    public void setOnlineResources(ListOfOnlineResources value) {
        this.onlineResources = value;
    }

    /**
     * Obtient la valeur de la propri�t� orderable.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrderable() {
        return orderable;
    }

    /**
     * D�finit la valeur de la propri�t� orderable.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrderable(Boolean value) {
        this.orderable = value;
    }

    /**
     * Obtient la valeur de la propri�t� dataFormat.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataFormat() {
        return dataFormat;
    }

    /**
     * D�finit la valeur de la propri�t� dataFormat.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataFormat(String value) {
        this.dataFormat = value;
    }

    /**
     * Obtient la valeur de la propri�t� visible.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisible() {
        return visible;
    }

    /**
     * D�finit la valeur de la propri�t� visible.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisible(Boolean value) {
        this.visible = value;
    }

    /**
     * Obtient la valeur de la propri�t� cloudCover.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCloudCover() {
        return cloudCover;
    }

    @Override
	public String toString() {
		return "Granule [granuleUR=" + granuleUR + ", insertTime=" + insertTime + ", lastUpdate=" + lastUpdate
				+ ", deleteTime=" + deleteTime + ", collection=" + collection + ", restrictionFlag=" + restrictionFlag
				+ ", restrictionComment=" + restrictionComment + ", dataGranule=" + dataGranule + ", pgeVersionClass="
				+ pgeVersionClass + ", temporal=" + temporal + ", spatial=" + spatial
				+ ", orbitCalculatedSpatialDomains=" + orbitCalculatedSpatialDomains + ", measuredParameters="
				+ measuredParameters + ", platforms=" + platforms + ", campaigns=" + campaigns
				+ ", additionalAttributes=" + additionalAttributes + ", inputGranules=" + inputGranules
				+ ", twoDCoordinateSystem=" + twoDCoordinateSystem + ", price=" + price + ", onlineAccessURLs="
				+ onlineAccessURLs + ", onlineResources=" + onlineResources + ", orderable=" + orderable
				+ ", dataFormat=" + dataFormat + ", visible=" + visible + ", cloudCover=" + cloudCover
				+ ", metadataStandardName=" + metadataStandardName + ", metadataStandardVersion="
				+ metadataStandardVersion + ", associatedBrowseImages=" + associatedBrowseImages
				+ ", associatedBrowseImageUrls=" + associatedBrowseImageUrls + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalAttributes == null) ? 0 : additionalAttributes.hashCode());
		result = prime * result + ((associatedBrowseImageUrls == null) ? 0 : associatedBrowseImageUrls.hashCode());
		result = prime * result + ((associatedBrowseImages == null) ? 0 : associatedBrowseImages.hashCode());
		result = prime * result + ((campaigns == null) ? 0 : campaigns.hashCode());
		result = prime * result + ((cloudCover == null) ? 0 : cloudCover.hashCode());
		result = prime * result + ((collection == null) ? 0 : collection.hashCode());
		result = prime * result + ((dataFormat == null) ? 0 : dataFormat.hashCode());
		result = prime * result + ((dataGranule == null) ? 0 : dataGranule.hashCode());
		result = prime * result + ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + ((granuleUR == null) ? 0 : granuleUR.hashCode());
		result = prime * result + ((inputGranules == null) ? 0 : inputGranules.hashCode());
		result = prime * result + ((insertTime == null) ? 0 : insertTime.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((measuredParameters == null) ? 0 : measuredParameters.hashCode());
		result = prime * result + ((metadataStandardName == null) ? 0 : metadataStandardName.hashCode());
		result = prime * result + ((metadataStandardVersion == null) ? 0 : metadataStandardVersion.hashCode());
		result = prime * result + ((onlineAccessURLs == null) ? 0 : onlineAccessURLs.hashCode());
		result = prime * result + ((onlineResources == null) ? 0 : onlineResources.hashCode());
		result = prime * result
				+ ((orbitCalculatedSpatialDomains == null) ? 0 : orbitCalculatedSpatialDomains.hashCode());
		result = prime * result + ((orderable == null) ? 0 : orderable.hashCode());
		result = prime * result + ((pgeVersionClass == null) ? 0 : pgeVersionClass.hashCode());
		result = prime * result + ((platforms == null) ? 0 : platforms.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((restrictionComment == null) ? 0 : restrictionComment.hashCode());
		result = prime * result + ((restrictionFlag == null) ? 0 : restrictionFlag.hashCode());
		result = prime * result + ((spatial == null) ? 0 : spatial.hashCode());
		result = prime * result + ((temporal == null) ? 0 : temporal.hashCode());
		result = prime * result + ((twoDCoordinateSystem == null) ? 0 : twoDCoordinateSystem.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
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
		Granule other = (Granule) obj;
		if (additionalAttributes == null) {
			if (other.additionalAttributes != null)
				return false;
		} else if (!additionalAttributes.equals(other.additionalAttributes))
			return false;
		if (associatedBrowseImageUrls == null) {
			if (other.associatedBrowseImageUrls != null)
				return false;
		} else if (!associatedBrowseImageUrls.equals(other.associatedBrowseImageUrls))
			return false;
		if (associatedBrowseImages == null) {
			if (other.associatedBrowseImages != null)
				return false;
		} else if (!associatedBrowseImages.equals(other.associatedBrowseImages))
			return false;
		if (campaigns == null) {
			if (other.campaigns != null)
				return false;
		} else if (!campaigns.equals(other.campaigns))
			return false;
		if (cloudCover == null) {
			if (other.cloudCover != null)
				return false;
		} else if (!cloudCover.equals(other.cloudCover))
			return false;
		if (collection == null) {
			if (other.collection != null)
				return false;
		} else if (!collection.equals(other.collection))
			return false;
		if (dataFormat == null) {
			if (other.dataFormat != null)
				return false;
		} else if (!dataFormat.equals(other.dataFormat))
			return false;
		if (dataGranule == null) {
			if (other.dataGranule != null)
				return false;
		} else if (!dataGranule.equals(other.dataGranule))
			return false;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (granuleUR == null) {
			if (other.granuleUR != null)
				return false;
		} else if (!granuleUR.equals(other.granuleUR))
			return false;
		if (inputGranules == null) {
			if (other.inputGranules != null)
				return false;
		} else if (!inputGranules.equals(other.inputGranules))
			return false;
		if (insertTime == null) {
			if (other.insertTime != null)
				return false;
		} else if (!insertTime.equals(other.insertTime))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (measuredParameters == null) {
			if (other.measuredParameters != null)
				return false;
		} else if (!measuredParameters.equals(other.measuredParameters))
			return false;
		if (metadataStandardName == null) {
			if (other.metadataStandardName != null)
				return false;
		} else if (!metadataStandardName.equals(other.metadataStandardName))
			return false;
		if (metadataStandardVersion == null) {
			if (other.metadataStandardVersion != null)
				return false;
		} else if (!metadataStandardVersion.equals(other.metadataStandardVersion))
			return false;
		if (onlineAccessURLs == null) {
			if (other.onlineAccessURLs != null)
				return false;
		} else if (!onlineAccessURLs.equals(other.onlineAccessURLs))
			return false;
		if (onlineResources == null) {
			if (other.onlineResources != null)
				return false;
		} else if (!onlineResources.equals(other.onlineResources))
			return false;
		if (orbitCalculatedSpatialDomains == null) {
			if (other.orbitCalculatedSpatialDomains != null)
				return false;
		} else if (!orbitCalculatedSpatialDomains.equals(other.orbitCalculatedSpatialDomains))
			return false;
		if (orderable == null) {
			if (other.orderable != null)
				return false;
		} else if (!orderable.equals(other.orderable))
			return false;
		if (pgeVersionClass == null) {
			if (other.pgeVersionClass != null)
				return false;
		} else if (!pgeVersionClass.equals(other.pgeVersionClass))
			return false;
		if (platforms == null) {
			if (other.platforms != null)
				return false;
		} else if (!platforms.equals(other.platforms))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (restrictionComment == null) {
			if (other.restrictionComment != null)
				return false;
		} else if (!restrictionComment.equals(other.restrictionComment))
			return false;
		if (restrictionFlag == null) {
			if (other.restrictionFlag != null)
				return false;
		} else if (!restrictionFlag.equals(other.restrictionFlag))
			return false;
		if (spatial == null) {
			if (other.spatial != null)
				return false;
		} else if (!spatial.equals(other.spatial))
			return false;
		if (temporal == null) {
			if (other.temporal != null)
				return false;
		} else if (!temporal.equals(other.temporal))
			return false;
		if (twoDCoordinateSystem == null) {
			if (other.twoDCoordinateSystem != null)
				return false;
		} else if (!twoDCoordinateSystem.equals(other.twoDCoordinateSystem))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		return true;
	}

	/**
     * D�finit la valeur de la propri�t� cloudCover.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCloudCover(BigDecimal value) {
        this.cloudCover = value;
    }

    /**
     * Obtient la valeur de la propri�t� metadataStandardName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetadataStandardName() {
        return metadataStandardName;
    }

    /**
     * D�finit la valeur de la propri�t� metadataStandardName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetadataStandardName(String value) {
        this.metadataStandardName = value;
    }

    /**
     * Obtient la valeur de la propri�t� metadataStandardVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMetadataStandardVersion() {
        return metadataStandardVersion;
    }

    /**
     * D�finit la valeur de la propri�t� metadataStandardVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMetadataStandardVersion(String value) {
        this.metadataStandardVersion = value;
    }

    /**
     * Obtient la valeur de la propri�t� associatedBrowseImages.
     * 
     * @return
     *     possible object is
     *     {@link ListOfProviderBrowseIds }
     *     
     */
    public ListOfProviderBrowseIds getAssociatedBrowseImages() {
        return associatedBrowseImages;
    }

    /**
     * D�finit la valeur de la propri�t� associatedBrowseImages.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfProviderBrowseIds }
     *     
     */
    public void setAssociatedBrowseImages(ListOfProviderBrowseIds value) {
        this.associatedBrowseImages = value;
    }

    /**
     * Obtient la valeur de la propri�t� associatedBrowseImageUrls.
     * 
     * @return
     *     possible object is
     *     {@link ListOfProviderBrowseUrls }
     *     
     */
    public ListOfProviderBrowseUrls getAssociatedBrowseImageUrls() {
        return associatedBrowseImageUrls;
    }

    /**
     * D�finit la valeur de la propri�t� associatedBrowseImageUrls.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfProviderBrowseUrls }
     *     
     */
    public void setAssociatedBrowseImageUrls(ListOfProviderBrowseUrls value) {
        this.associatedBrowseImageUrls = value;
    }

}
