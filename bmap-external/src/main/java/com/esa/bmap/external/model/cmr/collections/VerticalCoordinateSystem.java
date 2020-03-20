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
 * <p>Classe Java pour VerticalCoordinateSystem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VerticalCoordinateSystem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AltitudeSystemDefinition" type="{}VerticalSystemDefinition" minOccurs="0"/>
 *         &lt;element name="DepthSystemDefinition" type="{}VerticalSystemDefinition" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerticalCoordinateSystem", propOrder = {
    "altitudeSystemDefinition",
    "depthSystemDefinition"
})
public class VerticalCoordinateSystem {

    @XmlElement(name = "AltitudeSystemDefinition")
    protected VerticalSystemDefinition altitudeSystemDefinition;
    @XmlElement(name = "DepthSystemDefinition")
    protected VerticalSystemDefinition depthSystemDefinition;

    /**
     * Obtient la valeur de la propriété altitudeSystemDefinition.
     * 
     * @return
     *     possible object is
     *     {@link VerticalSystemDefinition }
     *     
     */
    public VerticalSystemDefinition getAltitudeSystemDefinition() {
        return altitudeSystemDefinition;
    }

    /**
     * Définit la valeur de la propriété altitudeSystemDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link VerticalSystemDefinition }
     *     
     */
    public void setAltitudeSystemDefinition(VerticalSystemDefinition value) {
        this.altitudeSystemDefinition = value;
    }

    /**
     * Obtient la valeur de la propriété depthSystemDefinition.
     * 
     * @return
     *     possible object is
     *     {@link VerticalSystemDefinition }
     *     
     */
    public VerticalSystemDefinition getDepthSystemDefinition() {
        return depthSystemDefinition;
    }

    /**
     * Définit la valeur de la propriété depthSystemDefinition.
     * 
     * @param value
     *     allowed object is
     *     {@link VerticalSystemDefinition }
     *     
     */
    public void setDepthSystemDefinition(VerticalSystemDefinition value) {
        this.depthSystemDefinition = value;
    }

}
