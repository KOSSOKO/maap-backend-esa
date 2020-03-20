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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A field to be added to a granule. Not all
 *       fields of a granule support partial updates. If an update is
 *       required for a non-supported field, perform a complete
 *       granule replacement rather than a partial metadata
 *       update.
 * 
 * <p>Classe Java pour GranuleAddField complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GranuleAddField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;choice>
 *             &lt;element name="BrowseImage" type="{}ProviderBrowseId"/>
 *             &lt;element name="BrowseImageUrl" type="{}ProviderBrowseUrl"/>
 *           &lt;/choice>
 *           &lt;element name="OnlineAccessURL" type="{}OnlineAccessURL"/>
 *           &lt;element name="OnlineResource" type="{}OnlineResource"/>
 *           &lt;element name="MeasuredParameter" type="{}MeasuredParameter"/>
 *           &lt;element name="AdditionalAttribute" type="{}AdditionalAttributeRef"/>
 *           &lt;element name="Temporal" type="{}Temporal"/>
 *           &lt;element name="DayNightFlag" type="{}DayNightFlag"/>
 *           &lt;element name="DeleteTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *           &lt;element name="CloudCover" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *           &lt;element name="RestrictionFlag" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *           &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *           &lt;element name="Spatial" type="{}Spatial"/>
 *         &lt;/choice>
 *         &lt;element name="UpdateTargetFields" type="{}ListOfGranuleUpdateTargetFields" minOccurs="0"/>
 *         &lt;element name="OldValue" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GranuleAddField", propOrder = {
    "browseImage",
    "browseImageUrl",
    "onlineAccessURL",
    "onlineResource",
    "measuredParameter",
    "additionalAttribute",
    "temporal",
    "dayNightFlag",
    "deleteTime",
    "cloudCover",
    "restrictionFlag",
    "visible",
    "spatial",
    "updateTargetFields",
    "oldValue"
})
public class GranuleAddField {

    @XmlElement(name = "BrowseImage")
    protected String browseImage;
    @XmlElement(name = "BrowseImageUrl")
    protected ProviderBrowseUrl browseImageUrl;
    @XmlElement(name = "OnlineAccessURL")
    protected OnlineAccessURL onlineAccessURL;
    @XmlElement(name = "OnlineResource")
    protected OnlineResource onlineResource;
    @XmlElement(name = "MeasuredParameter")
    protected MeasuredParameter measuredParameter;
    @XmlElement(name = "AdditionalAttribute")
    protected AdditionalAttributeRef additionalAttribute;
    @XmlElement(name = "Temporal")
    protected Temporal temporal;
    @XmlElement(name = "DayNightFlag")
    @XmlSchemaType(name = "string")
    protected DayNightFlag dayNightFlag;
    @XmlElement(name = "DeleteTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deleteTime;
    @XmlElement(name = "CloudCover")
    protected BigDecimal cloudCover;
    @XmlElement(name = "RestrictionFlag")
    protected BigDecimal restrictionFlag;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "Spatial")
    protected Spatial spatial;
    @XmlElement(name = "UpdateTargetFields")
    protected ListOfGranuleUpdateTargetFields updateTargetFields;
    @XmlElement(name = "OldValue")
    protected String oldValue;

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
     * Obtient la valeur de la propri�t� browseImageUrl.
     * 
     * @return
     *     possible object is
     *     {@link ProviderBrowseUrl }
     *     
     */
    public ProviderBrowseUrl getBrowseImageUrl() {
        return browseImageUrl;
    }

    /**
     * D�finit la valeur de la propri�t� browseImageUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderBrowseUrl }
     *     
     */
    public void setBrowseImageUrl(ProviderBrowseUrl value) {
        this.browseImageUrl = value;
    }

    /**
     * Obtient la valeur de la propri�t� onlineAccessURL.
     * 
     * @return
     *     possible object is
     *     {@link OnlineAccessURL }
     *     
     */
    public OnlineAccessURL getOnlineAccessURL() {
        return onlineAccessURL;
    }

    /**
     * D�finit la valeur de la propri�t� onlineAccessURL.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineAccessURL }
     *     
     */
    public void setOnlineAccessURL(OnlineAccessURL value) {
        this.onlineAccessURL = value;
    }

    /**
     * Obtient la valeur de la propri�t� onlineResource.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResource }
     *     
     */
    public OnlineResource getOnlineResource() {
        return onlineResource;
    }

    /**
     * D�finit la valeur de la propri�t� onlineResource.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResource }
     *     
     */
    public void setOnlineResource(OnlineResource value) {
        this.onlineResource = value;
    }

    /**
     * Obtient la valeur de la propri�t� measuredParameter.
     * 
     * @return
     *     possible object is
     *     {@link MeasuredParameter }
     *     
     */
    public MeasuredParameter getMeasuredParameter() {
        return measuredParameter;
    }

    /**
     * D�finit la valeur de la propri�t� measuredParameter.
     * 
     * @param value
     *     allowed object is
     *     {@link MeasuredParameter }
     *     
     */
    public void setMeasuredParameter(MeasuredParameter value) {
        this.measuredParameter = value;
    }

    /**
     * Obtient la valeur de la propri�t� additionalAttribute.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalAttributeRef }
     *     
     */
    public AdditionalAttributeRef getAdditionalAttribute() {
        return additionalAttribute;
    }

    /**
     * D�finit la valeur de la propri�t� additionalAttribute.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalAttributeRef }
     *     
     */
    public void setAdditionalAttribute(AdditionalAttributeRef value) {
        this.additionalAttribute = value;
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
     * Obtient la valeur de la propri�t� dayNightFlag.
     * 
     * @return
     *     possible object is
     *     {@link DayNightFlag }
     *     
     */
    public DayNightFlag getDayNightFlag() {
        return dayNightFlag;
    }

    /**
     * D�finit la valeur de la propri�t� dayNightFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link DayNightFlag }
     *     
     */
    public void setDayNightFlag(DayNightFlag value) {
        this.dayNightFlag = value;
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
     * Obtient la valeur de la propri�t� updateTargetFields.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranuleUpdateTargetFields }
     *     
     */
    public ListOfGranuleUpdateTargetFields getUpdateTargetFields() {
        return updateTargetFields;
    }

    /**
     * D�finit la valeur de la propri�t� updateTargetFields.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranuleUpdateTargetFields }
     *     
     */
    public void setUpdateTargetFields(ListOfGranuleUpdateTargetFields value) {
        this.updateTargetFields = value;
    }

    /**
     * Obtient la valeur de la propri�t� oldValue.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldValue() {
        return oldValue;
    }

    /**
     * D�finit la valeur de la propri�t� oldValue.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldValue(String value) {
        this.oldValue = value;
    }

	@Override
	public String toString() {
		return "GranuleAddField [browseImage=" + browseImage + ", browseImageUrl=" + browseImageUrl
				+ ", onlineAccessURL=" + onlineAccessURL + ", onlineResource=" + onlineResource + ", measuredParameter="
				+ measuredParameter + ", additionalAttribute=" + additionalAttribute + ", temporal=" + temporal
				+ ", dayNightFlag=" + dayNightFlag + ", deleteTime=" + deleteTime + ", cloudCover=" + cloudCover
				+ ", restrictionFlag=" + restrictionFlag + ", visible=" + visible + ", spatial=" + spatial
				+ ", updateTargetFields=" + updateTargetFields + ", oldValue=" + oldValue + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalAttribute == null) ? 0 : additionalAttribute.hashCode());
		result = prime * result + ((browseImage == null) ? 0 : browseImage.hashCode());
		result = prime * result + ((browseImageUrl == null) ? 0 : browseImageUrl.hashCode());
		result = prime * result + ((cloudCover == null) ? 0 : cloudCover.hashCode());
		result = prime * result + ((dayNightFlag == null) ? 0 : dayNightFlag.hashCode());
		result = prime * result + ((deleteTime == null) ? 0 : deleteTime.hashCode());
		result = prime * result + ((measuredParameter == null) ? 0 : measuredParameter.hashCode());
		result = prime * result + ((oldValue == null) ? 0 : oldValue.hashCode());
		result = prime * result + ((onlineAccessURL == null) ? 0 : onlineAccessURL.hashCode());
		result = prime * result + ((onlineResource == null) ? 0 : onlineResource.hashCode());
		result = prime * result + ((restrictionFlag == null) ? 0 : restrictionFlag.hashCode());
		result = prime * result + ((spatial == null) ? 0 : spatial.hashCode());
		result = prime * result + ((temporal == null) ? 0 : temporal.hashCode());
		result = prime * result + ((updateTargetFields == null) ? 0 : updateTargetFields.hashCode());
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
		GranuleAddField other = (GranuleAddField) obj;
		if (additionalAttribute == null) {
			if (other.additionalAttribute != null)
				return false;
		} else if (!additionalAttribute.equals(other.additionalAttribute))
			return false;
		if (browseImage == null) {
			if (other.browseImage != null)
				return false;
		} else if (!browseImage.equals(other.browseImage))
			return false;
		if (browseImageUrl == null) {
			if (other.browseImageUrl != null)
				return false;
		} else if (!browseImageUrl.equals(other.browseImageUrl))
			return false;
		if (cloudCover == null) {
			if (other.cloudCover != null)
				return false;
		} else if (!cloudCover.equals(other.cloudCover))
			return false;
		if (dayNightFlag != other.dayNightFlag)
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
		if (oldValue == null) {
			if (other.oldValue != null)
				return false;
		} else if (!oldValue.equals(other.oldValue))
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
		if (updateTargetFields == null) {
			if (other.updateTargetFields != null)
				return false;
		} else if (!updateTargetFields.equals(other.updateTargetFields))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		return true;
	}

}
