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
import javax.xml.bind.annotation.XmlType;


/**
 * This entity holds the horizontal spatial
 * 				coverage of a point.
 * 
 * <p>Classe Java pour Point complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propri�t� pointLongitude.
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
     * D�finit la valeur de la propri�t� pointLongitude.
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
     * Obtient la valeur de la propri�t� pointLatitude.
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
     * D�finit la valeur de la propri�t� pointLatitude.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPointLatitude(BigDecimal value) {
        this.pointLatitude = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pointLatitude == null) ? 0 : pointLatitude.hashCode());
		result = prime * result + ((pointLongitude == null) ? 0 : pointLongitude.hashCode());
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
		Point other = (Point) obj;
		if (pointLatitude == null) {
			if (other.pointLatitude != null)
				return false;
		} else if (!pointLatitude.equals(other.pointLatitude))
			return false;
		if (pointLongitude == null) {
			if (other.pointLongitude != null)
				return false;
		} else if (!pointLongitude.equals(other.pointLongitude))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [pointLongitude=" + pointLongitude + ", pointLatitude=" + pointLatitude + "]";
	}

}
