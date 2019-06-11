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
import javax.xml.bind.annotation.XmlType;


/**
 * This entity describes the relevant
 *       platforms associated with the acquisition of the collection
 *       or granule. For example, Platforms may include (but are not
 *       limited to): ADEOS-II, AEM-2, AM-1, Aqua, Aura, BALLOONS,
 *       BUOYS, C-130, DEM, DMSP-F1, ERS-1, GOES-10, LANDSAT-1,
 *       METEOSAT-2, NIMBUS-2, NOAA-6, TERRA, TRMM,
 *       etc.
 * 
 * <p>Classe Java pour Platform complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Platform">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShortName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LongName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Type">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Characteristics" type="{}ListOfCharacteristics" minOccurs="0"/>
 *         &lt;element name="Instruments" type="{}ListOfInstruments" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Platform", propOrder = {
    "shortName",
    "longName",
    "type",
    "characteristics",
    "instruments"
})
public class Platform {

    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "LongName", required = true)
    protected String longName;
    @XmlElement(name = "Type", required = true)
    protected String type;
    @XmlElement(name = "Characteristics")
    protected ListOfCharacteristics characteristics;
    @XmlElement(name = "Instruments")
    protected ListOfInstruments instruments;

    /**
     * Obtient la valeur de la propriété shortName.
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
     * Définit la valeur de la propriété shortName.
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
     * Obtient la valeur de la propriété longName.
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
     * Définit la valeur de la propriété longName.
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
     * Obtient la valeur de la propriété type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Définit la valeur de la propriété type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Obtient la valeur de la propriété characteristics.
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
     * Définit la valeur de la propriété characteristics.
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
     * Obtient la valeur de la propriété instruments.
     * 
     * @return
     *     possible object is
     *     {@link ListOfInstruments }
     *     
     */
    public ListOfInstruments getInstruments() {
        return instruments;
    }

    /**
     * Définit la valeur de la propriété instruments.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfInstruments }
     *     
     */
    public void setInstruments(ListOfInstruments value) {
        this.instruments = value;
    }

}
