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
 * <p>Classe Java pour VerticalCoordinateSystem complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propri�t� altitudeSystemDefinition.
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
     * D�finit la valeur de la propri�t� altitudeSystemDefinition.
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
     * Obtient la valeur de la propri�t� depthSystemDefinition.
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
     * D�finit la valeur de la propri�t� depthSystemDefinition.
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
