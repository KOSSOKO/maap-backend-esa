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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This entity stores the basic descriptive
 *       characteristics associated with a granule.
 * 
 * <p>Classe Java pour DataGranule complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DataGranule">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SizeMBDataGranule" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="ReprocessingPlanned" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ReprocessingActual" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ProducerGranuleId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="128"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DayNightFlag" type="{}DayNightFlag"/>
 *         &lt;element name="ProductionDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="LocalVersionId" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
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
@XmlType(name = "DataGranule", propOrder = {
    "sizeMBDataGranule",
    "reprocessingPlanned",
    "reprocessingActual",
    "producerGranuleId",
    "dayNightFlag",
    "productionDateTime",
    "localVersionId"
})
public class DataGranule {

    @XmlElement(name = "SizeMBDataGranule")
    protected Double sizeMBDataGranule;
    @XmlElement(name = "ReprocessingPlanned")
    protected String reprocessingPlanned;
    @XmlElement(name = "ReprocessingActual")
    protected String reprocessingActual;
    @XmlElement(name = "ProducerGranuleId")
    protected String producerGranuleId;
    @XmlElement(name = "DayNightFlag", required = true)
    @XmlSchemaType(name = "string")
    protected DayNightFlag dayNightFlag;
    @XmlElement(name = "ProductionDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar productionDateTime;
    @XmlElement(name = "LocalVersionId")
    protected String localVersionId;

    /**
     * Obtient la valeur de la propri�t� sizeMBDataGranule.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSizeMBDataGranule() {
        return sizeMBDataGranule;
    }

    /**
     * D�finit la valeur de la propri�t� sizeMBDataGranule.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSizeMBDataGranule(Double value) {
        this.sizeMBDataGranule = value;
    }

    /**
     * Obtient la valeur de la propri�t� reprocessingPlanned.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReprocessingPlanned() {
        return reprocessingPlanned;
    }

    /**
     * D�finit la valeur de la propri�t� reprocessingPlanned.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReprocessingPlanned(String value) {
        this.reprocessingPlanned = value;
    }

    /**
     * Obtient la valeur de la propri�t� reprocessingActual.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReprocessingActual() {
        return reprocessingActual;
    }

    /**
     * D�finit la valeur de la propri�t� reprocessingActual.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReprocessingActual(String value) {
        this.reprocessingActual = value;
    }

    /**
     * Obtient la valeur de la propri�t� producerGranuleId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProducerGranuleId() {
        return producerGranuleId;
    }

    /**
     * D�finit la valeur de la propri�t� producerGranuleId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProducerGranuleId(String value) {
        this.producerGranuleId = value;
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
     * Obtient la valeur de la propri�t� productionDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProductionDateTime() {
        return productionDateTime;
    }

    /**
     * D�finit la valeur de la propri�t� productionDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProductionDateTime(XMLGregorianCalendar value) {
        this.productionDateTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� localVersionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalVersionId() {
        return localVersionId;
    }

    /**
     * D�finit la valeur de la propri�t� localVersionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalVersionId(String value) {
        this.localVersionId = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dayNightFlag == null) ? 0 : dayNightFlag.hashCode());
		result = prime * result + ((localVersionId == null) ? 0 : localVersionId.hashCode());
		result = prime * result + ((producerGranuleId == null) ? 0 : producerGranuleId.hashCode());
		result = prime * result + ((productionDateTime == null) ? 0 : productionDateTime.hashCode());
		result = prime * result + ((reprocessingActual == null) ? 0 : reprocessingActual.hashCode());
		result = prime * result + ((reprocessingPlanned == null) ? 0 : reprocessingPlanned.hashCode());
		result = prime * result + ((sizeMBDataGranule == null) ? 0 : sizeMBDataGranule.hashCode());
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
		DataGranule other = (DataGranule) obj;
		if (dayNightFlag != other.dayNightFlag)
			return false;
		if (localVersionId == null) {
			if (other.localVersionId != null)
				return false;
		} else if (!localVersionId.equals(other.localVersionId))
			return false;
		if (producerGranuleId == null) {
			if (other.producerGranuleId != null)
				return false;
		} else if (!producerGranuleId.equals(other.producerGranuleId))
			return false;
		if (productionDateTime == null) {
			if (other.productionDateTime != null)
				return false;
		} else if (!productionDateTime.equals(other.productionDateTime))
			return false;
		if (reprocessingActual == null) {
			if (other.reprocessingActual != null)
				return false;
		} else if (!reprocessingActual.equals(other.reprocessingActual))
			return false;
		if (reprocessingPlanned == null) {
			if (other.reprocessingPlanned != null)
				return false;
		} else if (!reprocessingPlanned.equals(other.reprocessingPlanned))
			return false;
		if (sizeMBDataGranule == null) {
			if (other.sizeMBDataGranule != null)
				return false;
		} else if (!sizeMBDataGranule.equals(other.sizeMBDataGranule))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DataGranule [sizeMBDataGranule=" + sizeMBDataGranule + ", reprocessingPlanned=" + reprocessingPlanned
				+ ", reprocessingActual=" + reprocessingActual + ", producerGranuleId=" + producerGranuleId
				+ ", dayNightFlag=" + dayNightFlag + ", productionDateTime=" + productionDateTime + ", localVersionId="
				+ localVersionId + "]";
	}

}
