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
 * A GPolygon represents an area on the earth
 * 				represented by a main boundary with optional boundaries
 * 				excluded regions from the main boundary.
 * 
 * <p>Classe Java pour GPolygon complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GPolygon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Boundary" type="{}Boundary"/>
 *         &lt;element name="ExclusiveZone" type="{}ExclusiveZone" minOccurs="0"/>
 *         &lt;element name="CenterPoint" type="{}Point" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GPolygon", propOrder = {
    "boundary",
    "exclusiveZone",
    "centerPoint"
})
public class GPolygon {

    @XmlElement(name = "Boundary", required = true)
    protected Boundary boundary;
    @XmlElement(name = "ExclusiveZone")
    protected ExclusiveZone exclusiveZone;
    @XmlElement(name = "CenterPoint")
    protected Point centerPoint;

    /**
     * Obtient la valeur de la propri�t� boundary.
     * 
     * @return
     *     possible object is
     *     {@link Boundary }
     *     
     */
    public Boundary getBoundary() {
        return boundary;
    }

    /**
     * D�finit la valeur de la propri�t� boundary.
     * 
     * @param value
     *     allowed object is
     *     {@link Boundary }
     *     
     */
    public void setBoundary(Boundary value) {
        this.boundary = value;
    }

    /**
     * Obtient la valeur de la propri�t� exclusiveZone.
     * 
     * @return
     *     possible object is
     *     {@link ExclusiveZone }
     *     
     */
    public ExclusiveZone getExclusiveZone() {
        return exclusiveZone;
    }

    /**
     * D�finit la valeur de la propri�t� exclusiveZone.
     * 
     * @param value
     *     allowed object is
     *     {@link ExclusiveZone }
     *     
     */
    public void setExclusiveZone(ExclusiveZone value) {
        this.exclusiveZone = value;
    }

    /**
     * Obtient la valeur de la propri�t� centerPoint.
     * 
     * @return
     *     possible object is
     *     {@link Point }
     *     
     */
    public Point getCenterPoint() {
        return centerPoint;
    }

    /**
     * D�finit la valeur de la propri�t� centerPoint.
     * 
     * @param value
     *     allowed object is
     *     {@link Point }
     *     
     */
    public void setCenterPoint(Point value) {
        this.centerPoint = value;
    }

}
