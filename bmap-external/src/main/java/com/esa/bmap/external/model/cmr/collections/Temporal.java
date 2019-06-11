//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This entity contains records, which
 *       describe the basis of the time system used for a specific
 *       collection.
 * 
 * <p>Classe Java pour Temporal complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Temporal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TimeType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DateType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TemporalRangeType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PrecisionOfSeconds" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="EndsAtPresentFlag" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="RangeDateTime" type="{}RangeDateTime" maxOccurs="unbounded"/>
 *           &lt;element name="SingleDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" maxOccurs="unbounded"/>
 *           &lt;element name="PeriodicDateTime" type="{}PeriodicDateTime" maxOccurs="unbounded"/>
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
@XmlType(name = "Temporal", propOrder = {
    "timeType",
    "dateType",
    "temporalRangeType",
    "precisionOfSeconds",
    "endsAtPresentFlag",
    "rangeDateTime",
    "singleDateTime",
    "periodicDateTime"
})
public class Temporal {

    @XmlElement(name = "TimeType")
    protected String timeType;
    @XmlElement(name = "DateType")
    protected String dateType;
    @XmlElement(name = "TemporalRangeType")
    protected String temporalRangeType;
    @XmlElement(name = "PrecisionOfSeconds")
    protected Integer precisionOfSeconds;
    @XmlElement(name = "EndsAtPresentFlag")
    protected Boolean endsAtPresentFlag;
    @XmlElement(name = "RangeDateTime")
    protected List<RangeDateTime> rangeDateTime;
    @XmlElement(name = "SingleDateTime")
    @XmlSchemaType(name = "dateTime")
    protected List<XMLGregorianCalendar> singleDateTime;
    @XmlElement(name = "PeriodicDateTime")
    protected List<PeriodicDateTime> periodicDateTime;

    /**
     * Obtient la valeur de la propriété timeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeType() {
        return timeType;
    }

    /**
     * Définit la valeur de la propriété timeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeType(String value) {
        this.timeType = value;
    }

    /**
     * Obtient la valeur de la propriété dateType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateType() {
        return dateType;
    }

    /**
     * Définit la valeur de la propriété dateType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateType(String value) {
        this.dateType = value;
    }

    /**
     * Obtient la valeur de la propriété temporalRangeType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTemporalRangeType() {
        return temporalRangeType;
    }

    /**
     * Définit la valeur de la propriété temporalRangeType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTemporalRangeType(String value) {
        this.temporalRangeType = value;
    }

    /**
     * Obtient la valeur de la propriété precisionOfSeconds.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrecisionOfSeconds() {
        return precisionOfSeconds;
    }

    /**
     * Définit la valeur de la propriété precisionOfSeconds.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrecisionOfSeconds(Integer value) {
        this.precisionOfSeconds = value;
    }

    /**
     * Obtient la valeur de la propriété endsAtPresentFlag.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEndsAtPresentFlag() {
        return endsAtPresentFlag;
    }

    /**
     * Définit la valeur de la propriété endsAtPresentFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEndsAtPresentFlag(Boolean value) {
        this.endsAtPresentFlag = value;
    }

    /**
     * Gets the value of the rangeDateTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rangeDateTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRangeDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RangeDateTime }
     * 
     * 
     */
    public List<RangeDateTime> getRangeDateTime() {
        if (rangeDateTime == null) {
            rangeDateTime = new ArrayList<RangeDateTime>();
        }
        return this.rangeDateTime;
    }

    /**
     * Gets the value of the singleDateTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the singleDateTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSingleDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getSingleDateTime() {
        if (singleDateTime == null) {
            singleDateTime = new ArrayList<XMLGregorianCalendar>();
        }
        return this.singleDateTime;
    }

    /**
     * Gets the value of the periodicDateTime property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the periodicDateTime property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeriodicDateTime().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeriodicDateTime }
     * 
     * 
     */
    public List<PeriodicDateTime> getPeriodicDateTime() {
        if (periodicDateTime == null) {
            periodicDateTime = new ArrayList<PeriodicDateTime>();
        }
        return this.periodicDateTime;
    }

}
