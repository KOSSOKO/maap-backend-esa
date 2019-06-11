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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A reference to a granule to be
 *       updated
 * 
 * <p>Classe Java pour GranuleUpdateTarget complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GranuleUpdateTarget">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="LastUpdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="GranuleUR" type="{}GranuleUR"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GranuleUpdateTarget", propOrder = {
    "lastUpdate",
    "granuleUR"
})
public class GranuleUpdateTarget {

    @XmlElement(name = "LastUpdate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastUpdate;
    @XmlElement(name = "GranuleUR", required = true)
    protected String granuleUR;

    /**
     * Obtient la valeur de la propri�t� lastUpdate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastUpdate() {
        return lastUpdate;
    }

    /**
     * D�finit la valeur de la propri�t� lastUpdate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastUpdate(XMLGregorianCalendar value) {
        this.lastUpdate = value;
    }

    /**
     * Obtient la valeur de la propri�t� granuleUR.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGranuleUR() {
        return granuleUR;
    }

    /**
     * D�finit la valeur de la propri�t� granuleUR.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGranuleUR(String value) {
        this.granuleUR = value;
    }

	@Override
	public String toString() {
		return "GranuleUpdateTarget [lastUpdate=" + lastUpdate + ", granuleUR=" + granuleUR + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((granuleUR == null) ? 0 : granuleUR.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
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
		GranuleUpdateTarget other = (GranuleUpdateTarget) obj;
		if (granuleUR == null) {
			if (other.granuleUR != null)
				return false;
		} else if (!granuleUR.equals(other.granuleUR))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		return true;
	}

}
