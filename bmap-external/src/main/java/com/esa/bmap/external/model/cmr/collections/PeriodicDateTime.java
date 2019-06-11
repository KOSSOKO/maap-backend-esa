//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This entity contains the name of the
 *       temporal period in addition to the date, time, duration unit,
 *       and value, and cycle duration unit and value. Used at the
 *       collection level to describe a collection having granules,
 *       which cover a regularly occurring period.
 * 
 * <p>Classe Java pour PeriodicDateTime complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PeriodicDateTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="30"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="StartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="DurationUnit" type="{}DurationUnit"/>
 *         &lt;element name="DurationValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="PeriodCycleDurationUnit" type="{}DurationUnit"/>
 *         &lt;element name="PeriodCycleDurationValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodicDateTime", propOrder = {
    "name",
    "startDate",
    "endDate",
    "durationUnit",
    "durationValue",
    "periodCycleDurationUnit",
    "periodCycleDurationValue"
})
public class PeriodicDateTime {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "StartDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;
    @XmlElement(name = "EndDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endDate;
    @XmlElement(name = "DurationUnit", required = true)
    @XmlSchemaType(name = "string")
    protected DurationUnit durationUnit;
    @XmlElement(name = "DurationValue")
    protected int durationValue;
    @XmlElement(name = "PeriodCycleDurationUnit", required = true)
    @XmlSchemaType(name = "string")
    protected DurationUnit periodCycleDurationUnit;
    @XmlElement(name = "PeriodCycleDurationValue")
    protected int periodCycleDurationValue;

    /**
     * Obtient la valeur de la propri�t� name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * D�finit la valeur de la propri�t� name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtient la valeur de la propri�t� startDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * D�finit la valeur de la propri�t� startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

    /**
     * Obtient la valeur de la propri�t� endDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndDate() {
        return endDate;
    }

    /**
     * D�finit la valeur de la propri�t� endDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndDate(XMLGregorianCalendar value) {
        this.endDate = value;
    }

    /**
     * Obtient la valeur de la propri�t� durationUnit.
     * 
     * @return
     *     possible object is
     *     {@link DurationUnit }
     *     
     */
    public DurationUnit getDurationUnit() {
        return durationUnit;
    }

    /**
     * D�finit la valeur de la propri�t� durationUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationUnit }
     *     
     */
    public void setDurationUnit(DurationUnit value) {
        this.durationUnit = value;
    }

    /**
     * Obtient la valeur de la propri�t� durationValue.
     * 
     */
    public int getDurationValue() {
        return durationValue;
    }

    /**
     * D�finit la valeur de la propri�t� durationValue.
     * 
     */
    public void setDurationValue(int value) {
        this.durationValue = value;
    }

    /**
     * Obtient la valeur de la propri�t� periodCycleDurationUnit.
     * 
     * @return
     *     possible object is
     *     {@link DurationUnit }
     *     
     */
    public DurationUnit getPeriodCycleDurationUnit() {
        return periodCycleDurationUnit;
    }

    /**
     * D�finit la valeur de la propri�t� periodCycleDurationUnit.
     * 
     * @param value
     *     allowed object is
     *     {@link DurationUnit }
     *     
     */
    public void setPeriodCycleDurationUnit(DurationUnit value) {
        this.periodCycleDurationUnit = value;
    }

    /**
     * Obtient la valeur de la propri�t� periodCycleDurationValue.
     * 
     */
    public int getPeriodCycleDurationValue() {
        return periodCycleDurationValue;
    }

    /**
     * D�finit la valeur de la propri�t� periodCycleDurationValue.
     * 
     */
    public void setPeriodCycleDurationValue(int value) {
        this.periodCycleDurationValue = value;
    }

}
