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
 * This entity stores the reference frame or
 *       system from which altitudes (elevations) are measured. The
 *       information contains the datum name, distance units and
 *       encoding method, which provide the definition for the system.
 *       This table also stores the characteristics of the reference
 *       frame or system from which depths are measured. The
 *       additional information in the table are geometry reference
 *       data etc.
 * 
 * <p>Classe Java pour SpatialInfo complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="SpatialInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpatialCoverageType">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VerticalCoordinateSystem" type="{}VerticalCoordinateSystem" minOccurs="0"/>
 *         &lt;element name="HorizontalCoordinateSystem" type="{}HorizontalCoordinateSystem" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpatialInfo", propOrder = {
    "spatialCoverageType",
    "verticalCoordinateSystem",
    "horizontalCoordinateSystem"
})
public class SpatialInfo {

    @XmlElement(name = "SpatialCoverageType", required = true)
    protected String spatialCoverageType;
    @XmlElement(name = "VerticalCoordinateSystem")
    protected VerticalCoordinateSystem verticalCoordinateSystem;
    @XmlElement(name = "HorizontalCoordinateSystem")
    protected HorizontalCoordinateSystem horizontalCoordinateSystem;

    /**
     * Obtient la valeur de la propriété spatialCoverageType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpatialCoverageType() {
        return spatialCoverageType;
    }

    /**
     * Définit la valeur de la propriété spatialCoverageType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpatialCoverageType(String value) {
        this.spatialCoverageType = value;
    }

    /**
     * Obtient la valeur de la propriété verticalCoordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link VerticalCoordinateSystem }
     *     
     */
    public VerticalCoordinateSystem getVerticalCoordinateSystem() {
        return verticalCoordinateSystem;
    }

    /**
     * Définit la valeur de la propriété verticalCoordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link VerticalCoordinateSystem }
     *     
     */
    public void setVerticalCoordinateSystem(VerticalCoordinateSystem value) {
        this.verticalCoordinateSystem = value;
    }

    /**
     * Obtient la valeur de la propriété horizontalCoordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link HorizontalCoordinateSystem }
     *     
     */
    public HorizontalCoordinateSystem getHorizontalCoordinateSystem() {
        return horizontalCoordinateSystem;
    }

    /**
     * Définit la valeur de la propriété horizontalCoordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link HorizontalCoordinateSystem }
     *     
     */
    public void setHorizontalCoordinateSystem(HorizontalCoordinateSystem value) {
        this.horizontalCoordinateSystem = value;
    }

}
