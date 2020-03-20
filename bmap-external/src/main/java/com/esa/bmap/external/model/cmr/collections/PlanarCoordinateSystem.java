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
 * This entity stores collections' coordinate
 *       system reference information include description of the
 *       system and geo-reference information, the name of the map
 *       projection [the systematic representation of all or part of
 *       the surface of the Earth on a plane or developable surface],
 *       a logical pointer to the map projection details, the
 *       description of the coordinate system and geo-reference
 *       information, and the resolutions units, direction, and
 *       meridian for the planar coordinate system
 *       etc.
 * 
 * <p>Classe Java pour PlanarCoordinateSystem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarCoordinateSystem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PlanarCoordinateSystemId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PlanarCoordinateInformation" type="{}PlanarCoordinateInformation" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="MapProjection" type="{}MapProjection"/>
 *           &lt;element name="LocalPlanarCoordinateSystem" type="{}LocalCoordinateSystem"/>
 *           &lt;element name="GridCoordinateSystemName" minOccurs="0">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;minLength value="1"/>
 *                 &lt;maxLength value="255"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
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
@XmlType(name = "PlanarCoordinateSystem", propOrder = {
    "planarCoordinateSystemId",
    "planarCoordinateInformation",
    "mapProjection",
    "localPlanarCoordinateSystem",
    "gridCoordinateSystemName"
})
public class PlanarCoordinateSystem {

    @XmlElement(name = "PlanarCoordinateSystemId", required = true)
    protected String planarCoordinateSystemId;
    @XmlElement(name = "PlanarCoordinateInformation")
    protected PlanarCoordinateInformation planarCoordinateInformation;
    @XmlElement(name = "MapProjection")
    protected MapProjection mapProjection;
    @XmlElement(name = "LocalPlanarCoordinateSystem")
    protected LocalCoordinateSystem localPlanarCoordinateSystem;
    @XmlElement(name = "GridCoordinateSystemName")
    protected String gridCoordinateSystemName;

    /**
     * Obtient la valeur de la propriété planarCoordinateSystemId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanarCoordinateSystemId() {
        return planarCoordinateSystemId;
    }

    /**
     * Définit la valeur de la propriété planarCoordinateSystemId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanarCoordinateSystemId(String value) {
        this.planarCoordinateSystemId = value;
    }

    /**
     * Obtient la valeur de la propriété planarCoordinateInformation.
     * 
     * @return
     *     possible object is
     *     {@link PlanarCoordinateInformation }
     *     
     */
    public PlanarCoordinateInformation getPlanarCoordinateInformation() {
        return planarCoordinateInformation;
    }

    /**
     * Définit la valeur de la propriété planarCoordinateInformation.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanarCoordinateInformation }
     *     
     */
    public void setPlanarCoordinateInformation(PlanarCoordinateInformation value) {
        this.planarCoordinateInformation = value;
    }

    /**
     * Obtient la valeur de la propriété mapProjection.
     * 
     * @return
     *     possible object is
     *     {@link MapProjection }
     *     
     */
    public MapProjection getMapProjection() {
        return mapProjection;
    }

    /**
     * Définit la valeur de la propriété mapProjection.
     * 
     * @param value
     *     allowed object is
     *     {@link MapProjection }
     *     
     */
    public void setMapProjection(MapProjection value) {
        this.mapProjection = value;
    }

    /**
     * Obtient la valeur de la propriété localPlanarCoordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link LocalCoordinateSystem }
     *     
     */
    public LocalCoordinateSystem getLocalPlanarCoordinateSystem() {
        return localPlanarCoordinateSystem;
    }

    /**
     * Définit la valeur de la propriété localPlanarCoordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalCoordinateSystem }
     *     
     */
    public void setLocalPlanarCoordinateSystem(LocalCoordinateSystem value) {
        this.localPlanarCoordinateSystem = value;
    }

    /**
     * Obtient la valeur de la propriété gridCoordinateSystemName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGridCoordinateSystemName() {
        return gridCoordinateSystemName;
    }

    /**
     * Définit la valeur de la propriété gridCoordinateSystemName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGridCoordinateSystemName(String value) {
        this.gridCoordinateSystemName = value;
    }

}
