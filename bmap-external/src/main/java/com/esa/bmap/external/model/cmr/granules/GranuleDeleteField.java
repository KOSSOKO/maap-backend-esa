//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The field that should be deleted. Some
 *       fields require a value to indicate the key of the field to
 *       the correct instance can be identified.
 * 
 * <p>Classe Java pour GranuleDeleteField complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GranuleDeleteField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="BrowseImage" type="{}ProviderBrowseId"/>
 *         &lt;element name="OnlineAccessURL">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OnlineResource">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MeasuredParameter">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="250"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AdditionalAttribute">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Temporal" type="{}EmptyType"/>
 *         &lt;element name="DayNightFlag" type="{}EmptyType"/>
 *         &lt;element name="DeleteTime" type="{}EmptyType"/>
 *         &lt;element name="CloudCover" type="{}EmptyType"/>
 *         &lt;element name="RestrictionFlag" type="{}EmptyType"/>
 *         &lt;element name="AllOnlineAccessURLs" type="{}EmptyType"/>
 *         &lt;element name="AllOnlineResources" type="{}EmptyType"/>
 *         &lt;element name="AllAdditionalAttributes" type="{}EmptyType"/>
 *         &lt;element name="AllBrowseImages" type="{}EmptyType"/>
 *         &lt;element name="Spatial" type="{}EmptyType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GranuleDeleteField", propOrder = {
    "browseImage",
    "onlineAccessURL",
    "onlineResource",
    "measuredParameter",
    "additionalAttribute",
    "temporal",
    "dayNightFlag",
    "deleteTime",
    "cloudCover",
    "restrictionFlag",
    "allOnlineAccessURLs",
    "allOnlineResources",
    "allAdditionalAttributes",
    "allBrowseImages",
    "spatial"
})
public class GranuleDeleteField {

    @XmlElement(name = "BrowseImage")
    protected String browseImage;
    @XmlElement(name = "OnlineAccessURL")
    protected String onlineAccessURL;
    @XmlElement(name = "OnlineResource")
    protected String onlineResource;
    @XmlElement(name = "MeasuredParameter")
    protected String measuredParameter;
    @XmlElement(name = "AdditionalAttribute")
    protected String additionalAttribute;
    @XmlElement(name = "Temporal")
    protected EmptyType temporal;
    @XmlElement(name = "DayNightFlag")
    protected EmptyType dayNightFlag;
    @XmlElement(name = "DeleteTime")
    protected EmptyType deleteTime;
    @XmlElement(name = "CloudCover")
    protected EmptyType cloudCover;
    @XmlElement(name = "RestrictionFlag")
    protected EmptyType restrictionFlag;
    @XmlElement(name = "AllOnlineAccessURLs")
    protected EmptyType allOnlineAccessURLs;
    @XmlElement(name = "AllOnlineResources")
    protected EmptyType allOnlineResources;
    @XmlElement(name = "AllAdditionalAttributes")
    protected EmptyType allAdditionalAttributes;
    @XmlElement(name = "AllBrowseImages")
    protected EmptyType allBrowseImages;
    @XmlElement(name = "Spatial")
    protected EmptyType spatial;

    /**
     * Obtient la valeur de la propri�t� browseImage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrowseImage() {
        return browseImage;
    }

    /**
     * D�finit la valeur de la propri�t� browseImage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrowseImage(String value) {
        this.browseImage = value;
    }

    /**
     * Obtient la valeur de la propri�t� onlineAccessURL.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineAccessURL() {
        return onlineAccessURL;
    }

    /**
     * D�finit la valeur de la propri�t� onlineAccessURL.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineAccessURL(String value) {
        this.onlineAccessURL = value;
    }

    /**
     * Obtient la valeur de la propri�t� onlineResource.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOnlineResource() {
        return onlineResource;
    }

    /**
     * D�finit la valeur de la propri�t� onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOnlineResource(String value) {
        this.onlineResource = value;
    }

    /**
     * Obtient la valeur de la propri�t� measuredParameter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasuredParameter() {
        return measuredParameter;
    }

    /**
     * D�finit la valeur de la propri�t� measuredParameter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasuredParameter(String value) {
        this.measuredParameter = value;
    }

    /**
     * Obtient la valeur de la propri�t� additionalAttribute.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdditionalAttribute() {
        return additionalAttribute;
    }

    /**
     * D�finit la valeur de la propri�t� additionalAttribute.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdditionalAttribute(String value) {
        this.additionalAttribute = value;
    }

    /**
     * Obtient la valeur de la propri�t� temporal.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getTemporal() {
        return temporal;
    }

    /**
     * D�finit la valeur de la propri�t� temporal.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setTemporal(EmptyType value) {
        this.temporal = value;
    }

    /**
     * Obtient la valeur de la propri�t� dayNightFlag.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getDayNightFlag() {
        return dayNightFlag;
    }

    /**
     * D�finit la valeur de la propri�t� dayNightFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setDayNightFlag(EmptyType value) {
        this.dayNightFlag = value;
    }

    /**
     * Obtient la valeur de la propri�t� deleteTime.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getDeleteTime() {
        return deleteTime;
    }

    /**
     * D�finit la valeur de la propri�t� deleteTime.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setDeleteTime(EmptyType value) {
        this.deleteTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� cloudCover.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getCloudCover() {
        return cloudCover;
    }

    /**
     * D�finit la valeur de la propri�t� cloudCover.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setCloudCover(EmptyType value) {
        this.cloudCover = value;
    }

    /**
     * Obtient la valeur de la propri�t� restrictionFlag.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getRestrictionFlag() {
        return restrictionFlag;
    }

    /**
     * D�finit la valeur de la propri�t� restrictionFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setRestrictionFlag(EmptyType value) {
        this.restrictionFlag = value;
    }

    /**
     * Obtient la valeur de la propri�t� allOnlineAccessURLs.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getAllOnlineAccessURLs() {
        return allOnlineAccessURLs;
    }

    /**
     * D�finit la valeur de la propri�t� allOnlineAccessURLs.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setAllOnlineAccessURLs(EmptyType value) {
        this.allOnlineAccessURLs = value;
    }

    /**
     * Obtient la valeur de la propri�t� allOnlineResources.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getAllOnlineResources() {
        return allOnlineResources;
    }

    /**
     * D�finit la valeur de la propri�t� allOnlineResources.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setAllOnlineResources(EmptyType value) {
        this.allOnlineResources = value;
    }

    /**
     * Obtient la valeur de la propri�t� allAdditionalAttributes.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getAllAdditionalAttributes() {
        return allAdditionalAttributes;
    }

    /**
     * D�finit la valeur de la propri�t� allAdditionalAttributes.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setAllAdditionalAttributes(EmptyType value) {
        this.allAdditionalAttributes = value;
    }

    /**
     * Obtient la valeur de la propri�t� allBrowseImages.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getAllBrowseImages() {
        return allBrowseImages;
    }

    /**
     * D�finit la valeur de la propri�t� allBrowseImages.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setAllBrowseImages(EmptyType value) {
        this.allBrowseImages = value;
    }

    /**
     * Obtient la valeur de la propri�t� spatial.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getSpatial() {
        return spatial;
    }

    /**
     * D�finit la valeur de la propri�t� spatial.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setSpatial(EmptyType value) {
        this.spatial = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalAttribute == null) ? 0 : additionalAttribute.hashCode());
		result = prime * result + ((allAdditionalAttributes == null) ? 0 : allAdditionalAttributes.hashCode());
		result = prime * result + ((allBrowseImages == null) ? 0 : allBrowseImages.hashCode());
		result = prime * result + ((allOnlineAccessURLs == null) ? 0 : allOnlineAccessURLs.hashCode());
		result = prime * result + ((allOnlineResources == null) ? 0 : allOnlineResources.hashCode());
		result = prime * result + ((browseImage == null) ? 0 : browseImage.hashCode());
		result = prime * result + ((cloudCover == null) ? 0 : cloudCover.hashCode());
		result = prime * result + ((dayNightFlag == null) ? 0 : dayNightFlag.hashCode());
		result = prime * result + ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + ((measuredParameter == null) ? 0 : measuredParameter.hashCode());
		result = prime * result + ((onlineAccessURL == null) ? 0 : onlineAccessURL.hashCode());
		result = prime * result + ((onlineResource == null) ? 0 : onlineResource.hashCode());
		result = prime * result + ((restrictionFlag == null) ? 0 : restrictionFlag.hashCode());
		result = prime * result + ((spatial == null) ? 0 : spatial.hashCode());
		result = prime * result + ((temporal == null) ? 0 : temporal.hashCode());
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
		GranuleDeleteField other = (GranuleDeleteField) obj;
		if (additionalAttribute == null) {
			if (other.additionalAttribute != null)
				return false;
		} else if (!additionalAttribute.equals(other.additionalAttribute))
			return false;
		if (allAdditionalAttributes == null) {
			if (other.allAdditionalAttributes != null)
				return false;
		} else if (!allAdditionalAttributes.equals(other.allAdditionalAttributes))
			return false;
		if (allBrowseImages == null) {
			if (other.allBrowseImages != null)
				return false;
		} else if (!allBrowseImages.equals(other.allBrowseImages))
			return false;
		if (allOnlineAccessURLs == null) {
			if (other.allOnlineAccessURLs != null)
				return false;
		} else if (!allOnlineAccessURLs.equals(other.allOnlineAccessURLs))
			return false;
		if (allOnlineResources == null) {
			if (other.allOnlineResources != null)
				return false;
		} else if (!allOnlineResources.equals(other.allOnlineResources))
			return false;
		if (browseImage == null) {
			if (other.browseImage != null)
				return false;
		} else if (!browseImage.equals(other.browseImage))
			return false;
		if (cloudCover == null) {
			if (other.cloudCover != null)
				return false;
		} else if (!cloudCover.equals(other.cloudCover))
			return false;
		if (dayNightFlag == null) {
			if (other.dayNightFlag != null)
				return false;
		} else if (!dayNightFlag.equals(other.dayNightFlag))
			return false;
		if (deleteTime == null) {
			if (other.deleteTime != null)
				return false;
		} else if (!deleteTime.equals(other.deleteTime))
			return false;
		if (measuredParameter == null) {
			if (other.measuredParameter != null)
				return false;
		} else if (!measuredParameter.equals(other.measuredParameter))
			return false;
		if (onlineAccessURL == null) {
			if (other.onlineAccessURL != null)
				return false;
		} else if (!onlineAccessURL.equals(other.onlineAccessURL))
			return false;
		if (onlineResource == null) {
			if (other.onlineResource != null)
				return false;
		} else if (!onlineResource.equals(other.onlineResource))
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
		return true;
	}

	@Override
	public String toString() {
		return "GranuleDeleteField [browseImage=" + browseImage + ", onlineAccessURL=" + onlineAccessURL
				+ ", onlineResource=" + onlineResource + ", measuredParameter=" + measuredParameter
				+ ", additionalAttribute=" + additionalAttribute + ", temporal=" + temporal + ", dayNightFlag="
				+ dayNightFlag + ", deleteTime=" + deleteTime + ", cloudCover=" + cloudCover + ", restrictionFlag="
				+ restrictionFlag + ", allOnlineAccessURLs=" + allOnlineAccessURLs + ", allOnlineResources="
				+ allOnlineResources + ", allAdditionalAttributes=" + allAdditionalAttributes + ", allBrowseImages="
				+ allBrowseImages + ", spatial=" + spatial + "]";
	}

}
