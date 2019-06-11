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
 *       spatial domain information.
 * 
 * <p>Classe Java pour Spatial complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Spatial">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GranuleLocality" type="{}ListOfLocalityValues" minOccurs="0"/>
 *         &lt;element name="VerticalSpatialDomains" type="{}ListOfVerticalSpatialDomains" minOccurs="0"/>
 *         &lt;element name="HorizontalSpatialDomain" type="{}HorizontalSpatialDomain" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Spatial", propOrder = {
    "granuleLocality",
    "verticalSpatialDomains",
    "horizontalSpatialDomain"
})
public class Spatial {

    @XmlElement(name = "GranuleLocality")
    protected ListOfLocalityValues granuleLocality;
    @XmlElement(name = "VerticalSpatialDomains")
    protected ListOfVerticalSpatialDomains verticalSpatialDomains;
    @XmlElement(name = "HorizontalSpatialDomain")
    protected HorizontalSpatialDomain horizontalSpatialDomain;

    /**
     * Obtient la valeur de la propri�t� granuleLocality.
     * 
     * @return
     *     possible object is
     *     {@link ListOfLocalityValues }
     *     
     */
    public ListOfLocalityValues getGranuleLocality() {
        return granuleLocality;
    }

    /**
     * D�finit la valeur de la propri�t� granuleLocality.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfLocalityValues }
     *     
     */
    public void setGranuleLocality(ListOfLocalityValues value) {
        this.granuleLocality = value;
    }

    /**
     * Obtient la valeur de la propri�t� verticalSpatialDomains.
     * 
     * @return
     *     possible object is
     *     {@link ListOfVerticalSpatialDomains }
     *     
     */
    public ListOfVerticalSpatialDomains getVerticalSpatialDomains() {
        return verticalSpatialDomains;
    }

    /**
     * D�finit la valeur de la propri�t� verticalSpatialDomains.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfVerticalSpatialDomains }
     *     
     */
    public void setVerticalSpatialDomains(ListOfVerticalSpatialDomains value) {
        this.verticalSpatialDomains = value;
    }

    /**
     * Obtient la valeur de la propri�t� horizontalSpatialDomain.
     * 
     * @return
     *     possible object is
     *     {@link HorizontalSpatialDomain }
     *     
     */
    public HorizontalSpatialDomain getHorizontalSpatialDomain() {
        return horizontalSpatialDomain;
    }

    /**
     * D�finit la valeur de la propri�t� horizontalSpatialDomain.
     * 
     * @param value
     *     allowed object is
     *     {@link HorizontalSpatialDomain }
     *     
     */
    public void setHorizontalSpatialDomain(HorizontalSpatialDomain value) {
        this.horizontalSpatialDomain = value;
    }

	@Override
	public String toString() {
		return "Spatial [granuleLocality=" + granuleLocality + ", verticalSpatialDomains=" + verticalSpatialDomains
				+ ", horizontalSpatialDomain=" + horizontalSpatialDomain + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((granuleLocality == null) ? 0 : granuleLocality.hashCode());
		result = prime * result + ((horizontalSpatialDomain == null) ? 0 : horizontalSpatialDomain.hashCode());
		result = prime * result + ((verticalSpatialDomains == null) ? 0 : verticalSpatialDomains.hashCode());
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
		Spatial other = (Spatial) obj;
		if (granuleLocality == null) {
			if (other.granuleLocality != null)
				return false;
		} else if (!granuleLocality.equals(other.granuleLocality))
			return false;
		if (horizontalSpatialDomain == null) {
			if (other.horizontalSpatialDomain != null)
				return false;
		} else if (!horizontalSpatialDomain.equals(other.horizontalSpatialDomain))
			return false;
		if (verticalSpatialDomains == null) {
			if (other.verticalSpatialDomains != null)
				return false;
		} else if (!verticalSpatialDomains.equals(other.verticalSpatialDomains))
			return false;
		return true;
	}

}
