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
 * This entity holds collection horizontal
 *       spatial coverage data.
 * 
 * <p>Classe Java pour HorizontalSpatialDomain complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="HorizontalSpatialDomain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ZoneIdentifier" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Geometry" type="{}Geometry"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HorizontalSpatialDomain", propOrder = {
    "zoneIdentifier",
    "geometry"
})
public class HorizontalSpatialDomain {

    @XmlElement(name = "ZoneIdentifier")
    protected String zoneIdentifier;
    @XmlElement(name = "Geometry", required = true)
    protected Geometry geometry;

    /**
     * Obtient la valeur de la propri�t� zoneIdentifier.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneIdentifier() {
        return zoneIdentifier;
    }

    /**
     * D�finit la valeur de la propri�t� zoneIdentifier.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneIdentifier(String value) {
        this.zoneIdentifier = value;
    }

    /**
     * Obtient la valeur de la propri�t� geometry.
     * 
     * @return
     *     possible object is
     *     {@link Geometry }
     *     
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * D�finit la valeur de la propri�t� geometry.
     * 
     * @param value
     *     allowed object is
     *     {@link Geometry }
     *     
     */
    public void setGeometry(Geometry value) {
        this.geometry = value;
    }

}
