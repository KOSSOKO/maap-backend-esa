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
 * 
 *         
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;html:p xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;This entity stores the two dimensional coordinate
 *         system information for the granule. The two dimensional
 *         coordinate system information is an alternative way to
 *         express granule's spatial coverage based on a certain two
 *         dimensional coordinate system defined by the
 *         providers.&lt;/html:p&gt;
 * </pre>
 * 
 *         
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;html:p xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;The following discussion on Landsat's PATH/ROW is
 *         an example of grid information. The Path is the
 *         longitudinal center line of a Landsat scene corresponding
 *         to the center of an orbital track that represented by grid
 *         Y lines.&lt;/html:p&gt;
 * </pre>
 * 
 *         
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;html:p xmlns:html="http://www.w3.org/1999/xhtml" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;The Row is the Latitudinal center line of a Landsat
 *         scene that corresponding to grid X lines. The indication of
 *         the two dimensional coordinate system type is "WRS-2". The
 *         two dimensional coordinate system information can be used
 *         to designate a geographic search for a nominal scene
 *         center.&lt;/html:p&gt;
 * </pre>
 * 
 *       
 * 
 * <p>Classe Java pour TwoDCoordinateSystem complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TwoDCoordinateSystem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="StartCoordinate1" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="EndCoordinate1" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="StartCoordinate2" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="EndCoordinate2" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="TwoDCoordinateSystemName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TwoDCoordinateSystem", propOrder = {
    "startCoordinate1",
    "endCoordinate1",
    "startCoordinate2",
    "endCoordinate2",
    "twoDCoordinateSystemName"
})
public class TwoDCoordinateSystem {

    @XmlElement(name = "StartCoordinate1", required = true)
    protected BigDecimal startCoordinate1;
    @XmlElement(name = "EndCoordinate1")
    protected BigDecimal endCoordinate1;
    @XmlElement(name = "StartCoordinate2", required = true)
    protected BigDecimal startCoordinate2;
    @XmlElement(name = "EndCoordinate2")
    protected BigDecimal endCoordinate2;
    @XmlElement(name = "TwoDCoordinateSystemName", required = true)
    protected String twoDCoordinateSystemName;

    /**
     * Obtient la valeur de la propri�t� startCoordinate1.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartCoordinate1() {
        return startCoordinate1;
    }

    /**
     * D�finit la valeur de la propri�t� startCoordinate1.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartCoordinate1(BigDecimal value) {
        this.startCoordinate1 = value;
    }

    /**
     * Obtient la valeur de la propri�t� endCoordinate1.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEndCoordinate1() {
        return endCoordinate1;
    }

    /**
     * D�finit la valeur de la propri�t� endCoordinate1.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEndCoordinate1(BigDecimal value) {
        this.endCoordinate1 = value;
    }

    /**
     * Obtient la valeur de la propri�t� startCoordinate2.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartCoordinate2() {
        return startCoordinate2;
    }

    /**
     * D�finit la valeur de la propri�t� startCoordinate2.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartCoordinate2(BigDecimal value) {
        this.startCoordinate2 = value;
    }

    /**
     * Obtient la valeur de la propri�t� endCoordinate2.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEndCoordinate2() {
        return endCoordinate2;
    }

    /**
     * D�finit la valeur de la propri�t� endCoordinate2.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEndCoordinate2(BigDecimal value) {
        this.endCoordinate2 = value;
    }

    /**
     * Obtient la valeur de la propri�t� twoDCoordinateSystemName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTwoDCoordinateSystemName() {
        return twoDCoordinateSystemName;
    }

    /**
     * D�finit la valeur de la propri�t� twoDCoordinateSystemName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTwoDCoordinateSystemName(String value) {
        this.twoDCoordinateSystemName = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endCoordinate1 == null) ? 0 : endCoordinate1.hashCode());
		result = prime * result + ((endCoordinate2 == null) ? 0 : endCoordinate2.hashCode());
		result = prime * result + ((startCoordinate1 == null) ? 0 : startCoordinate1.hashCode());
		result = prime * result + ((startCoordinate2 == null) ? 0 : startCoordinate2.hashCode());
		result = prime * result + ((twoDCoordinateSystemName == null) ? 0 : twoDCoordinateSystemName.hashCode());
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
		TwoDCoordinateSystem other = (TwoDCoordinateSystem) obj;
		if (endCoordinate1 == null) {
			if (other.endCoordinate1 != null)
				return false;
		} else if (!endCoordinate1.equals(other.endCoordinate1))
			return false;
		if (endCoordinate2 == null) {
			if (other.endCoordinate2 != null)
				return false;
		} else if (!endCoordinate2.equals(other.endCoordinate2))
			return false;
		if (startCoordinate1 == null) {
			if (other.startCoordinate1 != null)
				return false;
		} else if (!startCoordinate1.equals(other.startCoordinate1))
			return false;
		if (startCoordinate2 == null) {
			if (other.startCoordinate2 != null)
				return false;
		} else if (!startCoordinate2.equals(other.startCoordinate2))
			return false;
		if (twoDCoordinateSystemName == null) {
			if (other.twoDCoordinateSystemName != null)
				return false;
		} else if (!twoDCoordinateSystemName.equals(other.twoDCoordinateSystemName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TwoDCoordinateSystem [startCoordinate1=" + startCoordinate1 + ", endCoordinate1=" + endCoordinate1
				+ ", startCoordinate2=" + startCoordinate2 + ", endCoordinate2=" + endCoordinate2
				+ ", twoDCoordinateSystemName=" + twoDCoordinateSystemName + "]";
	}

}
