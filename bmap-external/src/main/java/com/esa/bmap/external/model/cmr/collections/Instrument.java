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
import javax.xml.bind.annotation.XmlType;


/**
 * This entity registers the device used to
 *       measure or record data, including direct human observation.
 *       In cases where instruments have a single sensor or the
 *       instrument and sensor are used synonymously (e.g. AVHRR) the
 *       both Instrument and Sensor should be recorded. The Sensor
 *       information is represented by some other
 *       entities.
 * 
 * <p>Classe Java pour Instrument complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Instrument">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShortName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LongName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Technique" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="NumberOfSensors" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="Characteristics" type="{}ListOfCharacteristics" minOccurs="0"/>
 *         &lt;element name="Sensors" type="{}ListOfSensors" minOccurs="0"/>
 *         &lt;element name="OperationModes" type="{}ListOfOperationModes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Instrument", propOrder = {
    "shortName",
    "longName",
    "technique",
    "numberOfSensors",
    "characteristics",
    "sensors",
    "operationModes"
})
public class Instrument {

    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "LongName")
    protected String longName;
    @XmlElement(name = "Technique")
    protected String technique;
    @XmlElement(name = "NumberOfSensors")
    protected Integer numberOfSensors;
    @XmlElement(name = "Characteristics")
    protected ListOfCharacteristics characteristics;
    @XmlElement(name = "Sensors")
    protected ListOfSensors sensors;
    @XmlElement(name = "OperationModes")
    protected ListOfOperationModes operationModes;

    /**
     * Obtient la valeur de la propri�t� shortName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * D�finit la valeur de la propri�t� shortName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Obtient la valeur de la propri�t� longName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongName() {
        return longName;
    }

    /**
     * D�finit la valeur de la propri�t� longName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongName(String value) {
        this.longName = value;
    }

    /**
     * Obtient la valeur de la propri�t� technique.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnique() {
        return technique;
    }

    /**
     * D�finit la valeur de la propri�t� technique.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnique(String value) {
        this.technique = value;
    }

    /**
     * Obtient la valeur de la propri�t� numberOfSensors.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfSensors() {
        return numberOfSensors;
    }

    /**
     * D�finit la valeur de la propri�t� numberOfSensors.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfSensors(Integer value) {
        this.numberOfSensors = value;
    }

    /**
     * Obtient la valeur de la propri�t� characteristics.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCharacteristics }
     *     
     */
    public ListOfCharacteristics getCharacteristics() {
        return characteristics;
    }

    /**
     * D�finit la valeur de la propri�t� characteristics.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCharacteristics }
     *     
     */
    public void setCharacteristics(ListOfCharacteristics value) {
        this.characteristics = value;
    }

    /**
     * Obtient la valeur de la propri�t� sensors.
     * 
     * @return
     *     possible object is
     *     {@link ListOfSensors }
     *     
     */
    public ListOfSensors getSensors() {
        return sensors;
    }

    /**
     * D�finit la valeur de la propri�t� sensors.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfSensors }
     *     
     */
    public void setSensors(ListOfSensors value) {
        this.sensors = value;
    }

    /**
     * Obtient la valeur de la propri�t� operationModes.
     * 
     * @return
     *     possible object is
     *     {@link ListOfOperationModes }
     *     
     */
    public ListOfOperationModes getOperationModes() {
        return operationModes;
    }

    /**
     * D�finit la valeur de la propri�t� operationModes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfOperationModes }
     *     
     */
    public void setOperationModes(ListOfOperationModes value) {
        this.operationModes = value;
    }

}
