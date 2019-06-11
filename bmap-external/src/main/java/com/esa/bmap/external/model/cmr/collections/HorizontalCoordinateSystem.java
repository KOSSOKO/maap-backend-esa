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
 * <p>Classe Java pour HorizontalCoordinateSystem complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HorizontalCoordinateSystem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GeodeticModel" type="{}GeodeticModel" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="GeographicCoordinateSystem" type="{}GeographicCoordinateSystem"/>
 *           &lt;element name="PlanarCoordinateSystems" type="{}ListOfPlanarCoordinateSystems"/>
 *           &lt;element name="LocalCoordinateSystem" type="{}LocalCoordinateSystem"/>
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
@XmlType(name = "HorizontalCoordinateSystem", propOrder = {
    "geodeticModel",
    "geographicCoordinateSystem",
    "planarCoordinateSystems",
    "localCoordinateSystem"
})
public class HorizontalCoordinateSystem {

    @XmlElement(name = "GeodeticModel")
    protected GeodeticModel geodeticModel;
    @XmlElement(name = "GeographicCoordinateSystem")
    protected GeographicCoordinateSystem geographicCoordinateSystem;
    @XmlElement(name = "PlanarCoordinateSystems")
    protected ListOfPlanarCoordinateSystems planarCoordinateSystems;
    @XmlElement(name = "LocalCoordinateSystem")
    protected LocalCoordinateSystem localCoordinateSystem;

    /**
     * Obtient la valeur de la propri�t� geodeticModel.
     * 
     * @return
     *     possible object is
     *     {@link GeodeticModel }
     *     
     */
    public GeodeticModel getGeodeticModel() {
        return geodeticModel;
    }

    /**
     * D�finit la valeur de la propri�t� geodeticModel.
     * 
     * @param value
     *     allowed object is
     *     {@link GeodeticModel }
     *     
     */
    public void setGeodeticModel(GeodeticModel value) {
        this.geodeticModel = value;
    }

    /**
     * Obtient la valeur de la propri�t� geographicCoordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link GeographicCoordinateSystem }
     *     
     */
    public GeographicCoordinateSystem getGeographicCoordinateSystem() {
        return geographicCoordinateSystem;
    }

    /**
     * D�finit la valeur de la propri�t� geographicCoordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link GeographicCoordinateSystem }
     *     
     */
    public void setGeographicCoordinateSystem(GeographicCoordinateSystem value) {
        this.geographicCoordinateSystem = value;
    }

    /**
     * Obtient la valeur de la propri�t� planarCoordinateSystems.
     * 
     * @return
     *     possible object is
     *     {@link ListOfPlanarCoordinateSystems }
     *     
     */
    public ListOfPlanarCoordinateSystems getPlanarCoordinateSystems() {
        return planarCoordinateSystems;
    }

    /**
     * D�finit la valeur de la propri�t� planarCoordinateSystems.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfPlanarCoordinateSystems }
     *     
     */
    public void setPlanarCoordinateSystems(ListOfPlanarCoordinateSystems value) {
        this.planarCoordinateSystems = value;
    }

    /**
     * Obtient la valeur de la propri�t� localCoordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link LocalCoordinateSystem }
     *     
     */
    public LocalCoordinateSystem getLocalCoordinateSystem() {
        return localCoordinateSystem;
    }

    /**
     * D�finit la valeur de la propri�t� localCoordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalCoordinateSystem }
     *     
     */
    public void setLocalCoordinateSystem(LocalCoordinateSystem value) {
        this.localCoordinateSystem = value;
    }

}
