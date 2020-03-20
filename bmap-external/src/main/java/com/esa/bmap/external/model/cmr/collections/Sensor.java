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
 * This entity holds the referential
 *       information for collection source/sensor configuration
 *       including sensor parameters setting such as technique
 *       etc.
 * 
 * <p>Classe Java pour Sensor complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Sensor">
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
 *         &lt;element name="Characteristics" type="{}ListOfCharacteristics" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sensor", propOrder = {
    "shortName",
    "longName",
    "technique",
    "characteristics"
})
public class Sensor {

    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "LongName")
    protected String longName;
    @XmlElement(name = "Technique")
    protected String technique;
    @XmlElement(name = "Characteristics")
    protected ListOfCharacteristics characteristics;

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

}
