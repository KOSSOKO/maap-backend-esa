//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This entity is used to store the
 *       characteristics of the orbit calculated spatial domain to
 *       include the model name, orbit number, start and stop orbit
 *       number, equator crossing date and time, and equator crossing
 *       longitude.
 * 
 * <p>Classe Java pour OrbitCalculatedSpatialDomain complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OrbitCalculatedSpatialDomain">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrbitalModelName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OrbitNumber" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="StartOrbitNumber" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="StopOrbitNumber" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="EquatorCrossingLongitude" type="{}Longitude" minOccurs="0"/>
 *         &lt;element name="EquatorCrossingDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrbitCalculatedSpatialDomain", propOrder = {
    "orbitalModelName",
    "orbitNumber",
    "startOrbitNumber",
    "stopOrbitNumber",
    "equatorCrossingLongitude",
    "equatorCrossingDateTime"
})
public class OrbitCalculatedSpatialDomain {

    @XmlElement(name = "OrbitalModelName")
    protected String orbitalModelName;
    @XmlElement(name = "OrbitNumber")
    protected BigInteger orbitNumber;
    @XmlElement(name = "StartOrbitNumber")
    protected BigDecimal startOrbitNumber;
    @XmlElement(name = "StopOrbitNumber")
    protected BigDecimal stopOrbitNumber;
    @XmlElement(name = "EquatorCrossingLongitude")
    protected BigDecimal equatorCrossingLongitude;
    @XmlElement(name = "EquatorCrossingDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar equatorCrossingDateTime;

    /**
     * Obtient la valeur de la propri�t� orbitalModelName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrbitalModelName() {
        return orbitalModelName;
    }

    /**
     * D�finit la valeur de la propri�t� orbitalModelName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrbitalModelName(String value) {
        this.orbitalModelName = value;
    }

    /**
     * Obtient la valeur de la propri�t� orbitNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrbitNumber() {
        return orbitNumber;
    }

    /**
     * D�finit la valeur de la propri�t� orbitNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrbitNumber(BigInteger value) {
        this.orbitNumber = value;
    }

    /**
     * Obtient la valeur de la propri�t� startOrbitNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartOrbitNumber() {
        return startOrbitNumber;
    }

    /**
     * D�finit la valeur de la propri�t� startOrbitNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartOrbitNumber(BigDecimal value) {
        this.startOrbitNumber = value;
    }

    /**
     * Obtient la valeur de la propri�t� stopOrbitNumber.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStopOrbitNumber() {
        return stopOrbitNumber;
    }

    /**
     * D�finit la valeur de la propri�t� stopOrbitNumber.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStopOrbitNumber(BigDecimal value) {
        this.stopOrbitNumber = value;
    }

    /**
     * Obtient la valeur de la propri�t� equatorCrossingLongitude.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getEquatorCrossingLongitude() {
        return equatorCrossingLongitude;
    }

    /**
     * D�finit la valeur de la propri�t� equatorCrossingLongitude.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setEquatorCrossingLongitude(BigDecimal value) {
        this.equatorCrossingLongitude = value;
    }

    /**
     * Obtient la valeur de la propri�t� equatorCrossingDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEquatorCrossingDateTime() {
        return equatorCrossingDateTime;
    }

    /**
     * D�finit la valeur de la propri�t� equatorCrossingDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEquatorCrossingDateTime(XMLGregorianCalendar value) {
        this.equatorCrossingDateTime = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((equatorCrossingDateTime == null) ? 0 : equatorCrossingDateTime.hashCode());
		result = prime * result + ((equatorCrossingLongitude == null) ? 0 : equatorCrossingLongitude.hashCode());
		result = prime * result + ((orbitNumber == null) ? 0 : orbitNumber.hashCode());
		result = prime * result + ((orbitalModelName == null) ? 0 : orbitalModelName.hashCode());
		result = prime * result + ((startOrbitNumber == null) ? 0 : startOrbitNumber.hashCode());
		result = prime * result + ((stopOrbitNumber == null) ? 0 : stopOrbitNumber.hashCode());
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
		OrbitCalculatedSpatialDomain other = (OrbitCalculatedSpatialDomain) obj;
		if (equatorCrossingDateTime == null) {
			if (other.equatorCrossingDateTime != null)
				return false;
		} else if (!equatorCrossingDateTime.equals(other.equatorCrossingDateTime))
			return false;
		if (equatorCrossingLongitude == null) {
			if (other.equatorCrossingLongitude != null)
				return false;
		} else if (!equatorCrossingLongitude.equals(other.equatorCrossingLongitude))
			return false;
		if (orbitNumber == null) {
			if (other.orbitNumber != null)
				return false;
		} else if (!orbitNumber.equals(other.orbitNumber))
			return false;
		if (orbitalModelName == null) {
			if (other.orbitalModelName != null)
				return false;
		} else if (!orbitalModelName.equals(other.orbitalModelName))
			return false;
		if (startOrbitNumber == null) {
			if (other.startOrbitNumber != null)
				return false;
		} else if (!startOrbitNumber.equals(other.startOrbitNumber))
			return false;
		if (stopOrbitNumber == null) {
			if (other.stopOrbitNumber != null)
				return false;
		} else if (!stopOrbitNumber.equals(other.stopOrbitNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrbitCalculatedSpatialDomain [orbitalModelName=" + orbitalModelName + ", orbitNumber=" + orbitNumber
				+ ", startOrbitNumber=" + startOrbitNumber + ", stopOrbitNumber=" + stopOrbitNumber
				+ ", equatorCrossingLongitude=" + equatorCrossingLongitude + ", equatorCrossingDateTime="
				+ equatorCrossingDateTime + "]";
	}

}
