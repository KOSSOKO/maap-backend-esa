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
 * <p>Classe Java pour GeographicCoordinateSystem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GeographicCoordinateSystem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GeographicCoordinateUnits" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="LatitudeResolution" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="LongitudeResolution" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeographicCoordinateSystem", propOrder = {
    "geographicCoordinateUnits",
    "latitudeResolution",
    "longitudeResolution"
})
public class GeographicCoordinateSystem {

    @XmlElement(name = "GeographicCoordinateUnits")
    protected String geographicCoordinateUnits;
    @XmlElement(name = "LatitudeResolution")
    protected BigDecimal latitudeResolution;
    @XmlElement(name = "LongitudeResolution")
    protected BigDecimal longitudeResolution;

    /**
     * Obtient la valeur de la propriété geographicCoordinateUnits.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeographicCoordinateUnits() {
        return geographicCoordinateUnits;
    }

    /**
     * Définit la valeur de la propriété geographicCoordinateUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeographicCoordinateUnits(String value) {
        this.geographicCoordinateUnits = value;
    }

    /**
     * Obtient la valeur de la propriété latitudeResolution.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLatitudeResolution() {
        return latitudeResolution;
    }

    /**
     * Définit la valeur de la propriété latitudeResolution.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLatitudeResolution(BigDecimal value) {
        this.latitudeResolution = value;
    }

    /**
     * Obtient la valeur de la propriété longitudeResolution.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLongitudeResolution() {
        return longitudeResolution;
    }

    /**
     * Définit la valeur de la propriété longitudeResolution.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLongitudeResolution(BigDecimal value) {
        this.longitudeResolution = value;
    }

}
