//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity stores orbital coverage
 *       information of the granule. This coverage is an alternative
 *       way of express granule spatial coverage. This information
 *       supports orbital backtrack search apply on
 *       granule.
 * 
 * <p>Classe Java pour Orbit complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Orbit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AscendingCrossing" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="StartLat" type="{}Latitude"/>
 *         &lt;element name="StartDirection" type="{}OrbitDirection"/>
 *         &lt;element name="EndLat" type="{}Latitude"/>
 *         &lt;element name="EndDirection" type="{}OrbitDirection"/>
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
@XmlType(name = "Orbit", propOrder = {
    "ascendingCrossing",
    "startLat",
    "startDirection",
    "endLat",
    "endDirection",
    "centerPoint"
})
public class Orbit {

    @XmlElement(name = "AscendingCrossing", required = true)
    protected BigDecimal ascendingCrossing;
    @XmlElement(name = "StartLat", required = true)
    protected BigDecimal startLat;
    @XmlElement(name = "StartDirection", required = true)
    @XmlSchemaType(name = "string")
    protected OrbitDirection startDirection;
    @XmlElement(name = "EndLat", required = true)
    protected BigDecimal endLat;
    @XmlElement(name = "EndDirection", required = true)
    @XmlSchemaType(name = "string")
    protected OrbitDirection endDirection;
    @XmlElement(name = "CenterPoint")
    protected Point centerPoint;

    /**
     * Obtient la valeur de la propri�t� ascendingCrossing.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAscendingCrossing() {
        return ascendingCrossing;
    }

    /**
     * D�finit la valeur de la propri�t� ascendingCrossing.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAscendingCrossing(BigDecimal value) {
        this.ascendingCrossing = value;
    }

    /**
     * Obtient la valeur de la propri�t� startLat.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartLat() {
        return startLat;
    }

    /**
     * D�finit la valeur de la propri�t� startLat.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartLat(BigDecimal value) {
        this.startLat = value;
    }

    /**
     * Obtient la valeur de la propri�t� startDirection.
     * 
     * @return
     *     possible object is
     *     {@link OrbitDirection }
     *     
     */
    public OrbitDirection getStartDirection() {
        return startDirection;
    }

    /**
     * D�finit la valeur de la propri�t� startDirection.
     * 
     * @param value
     *     allowed object is
     *     {@link OrbitDirection }
     *     
     */
    public void setStartDirection(OrbitDirection value) {
        this.startDirection = value;
    }

    /**
     * Obtient la valeur de la propri�t� endLat.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEndLat() {
        return endLat;
    }

    /**
     * D�finit la valeur de la propri�t� endLat.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEndLat(BigDecimal value) {
        this.endLat = value;
    }

    /**
     * Obtient la valeur de la propri�t� endDirection.
     * 
     * @return
     *     possible object is
     *     {@link OrbitDirection }
     *     
     */
    public OrbitDirection getEndDirection() {
        return endDirection;
    }

    /**
     * D�finit la valeur de la propri�t� endDirection.
     * 
     * @param value
     *     allowed object is
     *     {@link OrbitDirection }
     *     
     */
    public void setEndDirection(OrbitDirection value) {
        this.endDirection = value;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ascendingCrossing == null) ? 0 : ascendingCrossing.hashCode());
		result = prime * result + ((centerPoint == null) ? 0 : centerPoint.hashCode());
		result = prime * result + ((endDirection == null) ? 0 : endDirection.hashCode());
		result = prime * result + ((endLat == null) ? 0 : endLat.hashCode());
		result = prime * result + ((startDirection == null) ? 0 : startDirection.hashCode());
		result = prime * result + ((startLat == null) ? 0 : startLat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orbit other = (Orbit) obj;
		if (ascendingCrossing == null) {
			if (other.ascendingCrossing != null)
				return false;
		} else if (!ascendingCrossing.equals(other.ascendingCrossing))
			return false;
		if (centerPoint == null) {
			if (other.centerPoint != null)
				return false;
		} else if (!centerPoint.equals(other.centerPoint))
			return false;
		if (endDirection != other.endDirection)
			return false;
		if (endLat == null) {
			if (other.endLat != null)
				return false;
		} else if (!endLat.equals(other.endLat))
			return false;
		if (startDirection != other.startDirection)
			return false;
		if (startLat == null) {
			if (other.startLat != null)
				return false;
		} else if (!startLat.equals(other.startLat))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Orbit [ascendingCrossing=" + ascendingCrossing + ", startLat=" + startLat + ", startDirection="
				+ startDirection + ", endLat=" + endLat + ", endDirection=" + endDirection + ", centerPoint="
				+ centerPoint + "]";
	}

}
