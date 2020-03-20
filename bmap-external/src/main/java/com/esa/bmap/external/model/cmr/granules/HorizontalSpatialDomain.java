//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This is a pseudo entity to hold granule
 *       horizontal spatial domain information.
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
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;choice>
 *           &lt;element name="Geometry" type="{}Geometry"/>
 *           &lt;element name="Orbit" type="{}Orbit"/>
 *         &lt;/choice>
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
    "geometry",
    "orbit"
})
public class HorizontalSpatialDomain {

    @XmlElement(name = "ZoneIdentifier")
    protected String zoneIdentifier;
    @XmlElement(name = "Geometry")
    protected Geometry geometry;
    @XmlElement(name = "Orbit")
    protected Orbit orbit;

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

    /**
     * Obtient la valeur de la propri�t� orbit.
     * 
     * @return
     *     possible object is
     *     {@link Orbit }
     *     
     */
    public Orbit getOrbit() {
        return orbit;
    }

    /**
     * D�finit la valeur de la propri�t� orbit.
     * 
     * @param value
     *     allowed object is
     *     {@link Orbit }
     *     
     */
    public void setOrbit(Orbit value) {
        this.orbit = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geometry == null) ? 0 : geometry.hashCode());
		result = prime * result + ((orbit == null) ? 0 : orbit.hashCode());
		result = prime * result + ((zoneIdentifier == null) ? 0 : zoneIdentifier.hashCode());
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
		HorizontalSpatialDomain other = (HorizontalSpatialDomain) obj;
		if (geometry == null) {
			if (other.geometry != null)
				return false;
		} else if (!geometry.equals(other.geometry))
			return false;
		if (orbit == null) {
			if (other.orbit != null)
				return false;
		} else if (!orbit.equals(other.orbit))
			return false;
		if (zoneIdentifier == null) {
			if (other.zoneIdentifier != null)
				return false;
		} else if (!zoneIdentifier.equals(other.zoneIdentifier))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HorizontalSpatialDomain [zoneIdentifier=" + zoneIdentifier + ", geometry=" + geometry + ", orbit="
				+ orbit + "]";
	}

}
