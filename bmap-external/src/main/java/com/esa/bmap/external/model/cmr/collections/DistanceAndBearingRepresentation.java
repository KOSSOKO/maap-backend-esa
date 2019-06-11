//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
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
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propri�t� distanceResolution.
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
     * D�finit la valeur de la propri�t� distanceResolution.
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
     * Obtient la valeur de la propri�t� bearingResolution.
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
     * D�finit la valeur de la propri�t� bearingResolution.
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
     * Obtient la valeur de la propri�t� bearingUnits.
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
     * D�finit la valeur de la propri�t� bearingUnits.
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
     * Obtient la valeur de la propri�t� bearingReferenceDirection.
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
     * D�finit la valeur de la propri�t� bearingReferenceDirection.
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
     * Obtient la valeur de la propri�t� bearingReferenceMeridian.
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
     * D�finit la valeur de la propri�t� bearingReferenceMeridian.
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
