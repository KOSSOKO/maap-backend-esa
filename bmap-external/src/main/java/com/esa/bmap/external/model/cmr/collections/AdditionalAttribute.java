//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity stores the product specific
 *       attributes (i.e. attributes used to describe the unique
 *       characteristics of the collection which extend beyond those
 *       defined in this model).
 * 
 * <p>Classe Java pour AdditionalAttribute complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="AdditionalAttribute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Name">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="DataType" type="{}DataType"/>
 *         &lt;element name="Description">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MeasurementResolution" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ParameterRangeBegin" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ParameterRangeEnd" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ParameterUnitsOfMeasure" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ParameterValueAccuracy" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ValueAccuracyExplanation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Value" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="500"/>
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
@XmlType(name = "AdditionalAttribute", propOrder = {
    "name",
    "dataType",
    "description",
    "measurementResolution",
    "parameterRangeBegin",
    "parameterRangeEnd",
    "parameterUnitsOfMeasure",
    "parameterValueAccuracy",
    "valueAccuracyExplanation",
    "value"
})
public class AdditionalAttribute {

    @XmlElement(name = "Name", required = true)
    protected String name;
    @XmlElement(name = "DataType", required = true)
    @XmlSchemaType(name = "string")
    protected DataType dataType;
    @XmlElement(name = "Description", required = true)
    protected String description;
    @XmlElement(name = "MeasurementResolution")
    protected String measurementResolution;
    @XmlElement(name = "ParameterRangeBegin")
    protected String parameterRangeBegin;
    @XmlElement(name = "ParameterRangeEnd")
    protected String parameterRangeEnd;
    @XmlElement(name = "ParameterUnitsOfMeasure")
    protected String parameterUnitsOfMeasure;
    @XmlElement(name = "ParameterValueAccuracy")
    protected String parameterValueAccuracy;
    @XmlElement(name = "ValueAccuracyExplanation")
    protected String valueAccuracyExplanation;
    @XmlElement(name = "Value")
    protected String value;

    /**
     * Obtient la valeur de la propriété name.
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
     * Définit la valeur de la propriété name.
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
     * Obtient la valeur de la propriété dataType.
     * 
     * @return
     *     possible object is
     *     {@link DataType }
     *     
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * Définit la valeur de la propriété dataType.
     * 
     * @param value
     *     allowed object is
     *     {@link DataType }
     *     
     */
    public void setDataType(DataType value) {
        this.dataType = value;
    }

    /**
     * Obtient la valeur de la propriété description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la valeur de la propriété description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propriété measurementResolution.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMeasurementResolution() {
        return measurementResolution;
    }

    /**
     * Définit la valeur de la propriété measurementResolution.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMeasurementResolution(String value) {
        this.measurementResolution = value;
    }

    /**
     * Obtient la valeur de la propriété parameterRangeBegin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterRangeBegin() {
        return parameterRangeBegin;
    }

    /**
     * Définit la valeur de la propriété parameterRangeBegin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterRangeBegin(String value) {
        this.parameterRangeBegin = value;
    }

    /**
     * Obtient la valeur de la propriété parameterRangeEnd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterRangeEnd() {
        return parameterRangeEnd;
    }

    /**
     * Définit la valeur de la propriété parameterRangeEnd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterRangeEnd(String value) {
        this.parameterRangeEnd = value;
    }

    /**
     * Obtient la valeur de la propriété parameterUnitsOfMeasure.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterUnitsOfMeasure() {
        return parameterUnitsOfMeasure;
    }

    /**
     * Définit la valeur de la propriété parameterUnitsOfMeasure.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterUnitsOfMeasure(String value) {
        this.parameterUnitsOfMeasure = value;
    }

    /**
     * Obtient la valeur de la propriété parameterValueAccuracy.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterValueAccuracy() {
        return parameterValueAccuracy;
    }

    /**
     * Définit la valeur de la propriété parameterValueAccuracy.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterValueAccuracy(String value) {
        this.parameterValueAccuracy = value;
    }

    /**
     * Obtient la valeur de la propriété valueAccuracyExplanation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValueAccuracyExplanation() {
        return valueAccuracyExplanation;
    }

    /**
     * Définit la valeur de la propriété valueAccuracyExplanation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValueAccuracyExplanation(String value) {
        this.valueAccuracyExplanation = value;
    }

    /**
     * Obtient la valeur de la propriété value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Définit la valeur de la propriété value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
