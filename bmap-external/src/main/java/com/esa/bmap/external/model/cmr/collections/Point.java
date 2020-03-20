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
 * This entity holds the horizontal spatial
 * 				coverage of a point.
 * 
 * <p>Classe Java pour Point complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Point">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PointLongitude" type="{}Longitude"/>
 *         &lt;element name="PointLatitude" type="{}Latitude"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Point", propOrder = {
    "pointLongitude",
    "pointLatitude"
})
public class Point {

    @XmlElement(name = "PointLongitude", required = true)
    protected BigDecimal pointLongitude;
    @XmlElement(name = "PointLatitude", required = true)
    protected BigDecimal pointLatitude;

    /**
     * Obtient la valeur de la propriété pointLongitude.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPointLongitude() {
        return pointLongitude;
    }

    /**
     * Définit la valeur de la propriété pointLongitude.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPointLongitude(BigDecimal value) {
        this.pointLongitude = value;
    }

    /**
     * Obtient la valeur de la propriété pointLatitude.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPointLatitude() {
        return pointLatitude;
    }

    /**
     * Définit la valeur de la propriété pointLatitude.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPointLatitude(BigDecimal value) {
        this.pointLatitude = value;
    }

}
