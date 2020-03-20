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
 * This entity holds the horizontal spatial
 * 				coverage of a line. ECHO stores horizontal spatial coverage
 * 				bounding rectangle type information using oracle spatial type
 * 				expression as a four points polygon. TODO possible merge with
 * 				collection but collection does not have
 * 				CenterPoint
 * 
 * <p>Classe Java pour BoundingRectangle complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="BoundingRectangle">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="WestBoundingCoordinate" type="{}Longitude"/>
 *         &lt;element name="NorthBoundingCoordinate" type="{}Latitude"/>
 *         &lt;element name="EastBoundingCoordinate" type="{}Longitude"/>
 *         &lt;element name="SouthBoundingCoordinate" type="{}Latitude"/>
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
@XmlType(name = "BoundingRectangle", propOrder = {
    "westBoundingCoordinate",
    "northBoundingCoordinate",
    "eastBoundingCoordinate",
    "southBoundingCoordinate",
    "centerPoint"
})
public class BoundingRectangle {

    @XmlElement(name = "WestBoundingCoordinate", required = true)
    protected BigDecimal westBoundingCoordinate;
    @XmlElement(name = "NorthBoundingCoordinate", required = true)
    protected BigDecimal northBoundingCoordinate;
    @XmlElement(name = "EastBoundingCoordinate", required = true)
    protected BigDecimal eastBoundingCoordinate;
    @XmlElement(name = "SouthBoundingCoordinate", required = true)
    protected BigDecimal southBoundingCoordinate;
    @XmlElement(name = "CenterPoint")
    protected Point centerPoint;

    /**
     * Obtient la valeur de la propri�t� westBoundingCoordinate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getWestBoundingCoordinate() {
        return westBoundingCoordinate;
    }

    /**
     * D�finit la valeur de la propri�t� westBoundingCoordinate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setWestBoundingCoordinate(BigDecimal value) {
        this.westBoundingCoordinate = value;
    }

    /**
     * Obtient la valeur de la propri�t� northBoundingCoordinate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNorthBoundingCoordinate() {
        return northBoundingCoordinate;
    }

    /**
     * D�finit la valeur de la propri�t� northBoundingCoordinate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNorthBoundingCoordinate(BigDecimal value) {
        this.northBoundingCoordinate = value;
    }

    /**
     * Obtient la valeur de la propri�t� eastBoundingCoordinate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEastBoundingCoordinate() {
        return eastBoundingCoordinate;
    }

    /**
     * D�finit la valeur de la propri�t� eastBoundingCoordinate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEastBoundingCoordinate(BigDecimal value) {
        this.eastBoundingCoordinate = value;
    }

    /**
     * Obtient la valeur de la propri�t� southBoundingCoordinate.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSouthBoundingCoordinate() {
        return southBoundingCoordinate;
    }

    /**
     * D�finit la valeur de la propri�t� southBoundingCoordinate.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSouthBoundingCoordinate(BigDecimal value) {
        this.southBoundingCoordinate = value;
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
