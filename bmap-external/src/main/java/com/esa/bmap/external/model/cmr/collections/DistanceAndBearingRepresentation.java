//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DistanceAndBearingRepresentation complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DistanceAndBearingRepresentation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DistanceResolution" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BearingResolution" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="BearingUnits" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BearingReferenceDirection" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="BearingReferenceMeridian" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
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
@XmlType(name = "DistanceAndBearingRepresentation", propOrder = {
    "distanceResolution",
    "bearingResolution",
    "bearingUnits",
    "bearingReferenceDirection",
    "bearingReferenceMeridian"
})
public class DistanceAndBearingRepresentation {

    @XmlElement(name = "DistanceResolution")
    protected BigDecimal distanceResolution;
    @XmlElement(name = "BearingResolution")
    protected BigDecimal bearingResolution;
    @XmlElement(name = "BearingUnits")
    protected String bearingUnits;
    @XmlElement(name = "BearingReferenceDirection")
    protected String bearingReferenceDirection;
    @XmlElement(name = "BearingReferenceMeridian")
    protected String bearingReferenceMeridian;

    /**
     * Obtient la valeur de la propriété distanceResolution.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDistanceResolution() {
        return distanceResolution;
    }

    /**
     * Définit la valeur de la propriété distanceResolution.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDistanceResolution(BigDecimal value) {
        this.distanceResolution = value;
    }

    /**
     * Obtient la valeur de la propriété bearingResolution.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBearingResolution() {
        return bearingResolution;
    }

    /**
     * Définit la valeur de la propriété bearingResolution.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBearingResolution(BigDecimal value) {
        this.bearingResolution = value;
    }

    /**
     * Obtient la valeur de la propriété bearingUnits.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBearingUnits() {
        return bearingUnits;
    }

    /**
     * Définit la valeur de la propriété bearingUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBearingUnits(String value) {
        this.bearingUnits = value;
    }

    /**
     * Obtient la valeur de la propriété bearingReferenceDirection.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBearingReferenceDirection() {
        return bearingReferenceDirection;
    }

    /**
     * Définit la valeur de la propriété bearingReferenceDirection.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBearingReferenceDirection(String value) {
        this.bearingReferenceDirection = value;
    }

    /**
     * Obtient la valeur de la propriété bearingReferenceMeridian.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBearingReferenceMeridian() {
        return bearingReferenceMeridian;
    }

    /**
     * Définit la valeur de la propriété bearingReferenceMeridian.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBearingReferenceMeridian(String value) {
        this.bearingReferenceMeridian = value;
    }

}
