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
 * <p>Classe Java pour GranuleMetaDataFile complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GranuleMetaDataFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataCenter" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Granules" type="{}ListOfGranules" minOccurs="0"/>
 *         &lt;element name="GranulePartialAdds" type="{}ListOfGranulePartialAdds" minOccurs="0"/>
 *         &lt;element name="GranulePartialDeletes" type="{}ListOfGranulePartialDeletes" minOccurs="0"/>
 *         &lt;element name="GranuleDeletes" type="{}ListOfGranuleDeletes" minOccurs="0"/>
 *         &lt;element name="GranuleVerifications" type="{}ListOfGranules" minOccurs="0"/>
 *         &lt;element name="GranuleInventories" type="{}ListOfGranuleInventories" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GranuleMetaDataFile", propOrder = {
    "dataCenter",
    "granules",
    "granulePartialAdds",
    "granulePartialDeletes",
    "granuleDeletes",
    "granuleVerifications",
    "granuleInventories"
})
public class GranuleMetaDataFile {

    @XmlElement(name = "DataCenter")
    protected String dataCenter;
    @XmlElement(name = "Granules")
    protected ListOfGranules granules;
    @XmlElement(name = "GranulePartialAdds")
    protected ListOfGranulePartialAdds granulePartialAdds;
    @XmlElement(name = "GranulePartialDeletes")
    protected ListOfGranulePartialDeletes granulePartialDeletes;
    @XmlElement(name = "GranuleDeletes")
    protected ListOfGranuleDeletes granuleDeletes;
    @XmlElement(name = "GranuleVerifications")
    protected ListOfGranules granuleVerifications;
    @XmlElement(name = "GranuleInventories")
    protected ListOfGranuleInventories granuleInventories;

    /**
     * Obtient la valeur de la propri�t� dataCenter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataCenter() {
        return dataCenter;
    }

    /**
     * D�finit la valeur de la propri�t� dataCenter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataCenter(String value) {
        this.dataCenter = value;
    }

    /**
     * Obtient la valeur de la propri�t� granules.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranules }
     *     
     */
    public ListOfGranules getGranules() {
        return granules;
    }

    /**
     * D�finit la valeur de la propri�t� granules.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranules }
     *     
     */
    public void setGranules(ListOfGranules value) {
        this.granules = value;
    }

    /**
     * Obtient la valeur de la propri�t� granulePartialAdds.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranulePartialAdds }
     *     
     */
    public ListOfGranulePartialAdds getGranulePartialAdds() {
        return granulePartialAdds;
    }

    /**
     * D�finit la valeur de la propri�t� granulePartialAdds.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranulePartialAdds }
     *     
     */
    public void setGranulePartialAdds(ListOfGranulePartialAdds value) {
        this.granulePartialAdds = value;
    }

    /**
     * Obtient la valeur de la propri�t� granulePartialDeletes.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranulePartialDeletes }
     *     
     */
    public ListOfGranulePartialDeletes getGranulePartialDeletes() {
        return granulePartialDeletes;
    }

    /**
     * D�finit la valeur de la propri�t� granulePartialDeletes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranulePartialDeletes }
     *     
     */
    public void setGranulePartialDeletes(ListOfGranulePartialDeletes value) {
        this.granulePartialDeletes = value;
    }

    /**
     * Obtient la valeur de la propri�t� granuleDeletes.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranuleDeletes }
     *     
     */
    public ListOfGranuleDeletes getGranuleDeletes() {
        return granuleDeletes;
    }

    /**
     * D�finit la valeur de la propri�t� granuleDeletes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranuleDeletes }
     *     
     */
    public void setGranuleDeletes(ListOfGranuleDeletes value) {
        this.granuleDeletes = value;
    }

    /**
     * Obtient la valeur de la propri�t� granuleVerifications.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranules }
     *     
     */
    public ListOfGranules getGranuleVerifications() {
        return granuleVerifications;
    }

    /**
     * D�finit la valeur de la propri�t� granuleVerifications.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranules }
     *     
     */
    public void setGranuleVerifications(ListOfGranules value) {
        this.granuleVerifications = value;
    }

    /**
     * Obtient la valeur de la propri�t� granuleInventories.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranuleInventories }
     *     
     */
    public ListOfGranuleInventories getGranuleInventories() {
        return granuleInventories;
    }

    /**
     * D�finit la valeur de la propri�t� granuleInventories.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranuleInventories }
     *     
     */
    public void setGranuleInventories(ListOfGranuleInventories value) {
        this.granuleInventories = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataCenter == null) ? 0 : dataCenter.hashCode());
		result = prime * result + ((granuleDeletes == null) ? 0 : granuleDeletes.hashCode());
		result = prime * result + ((granuleInventories == null) ? 0 : granuleInventories.hashCode());
		result = prime * result + ((granulePartialAdds == null) ? 0 : granulePartialAdds.hashCode());
		result = prime * result + ((granulePartialDeletes == null) ? 0 : granulePartialDeletes.hashCode());
		result = prime * result + ((granuleVerifications == null) ? 0 : granuleVerifications.hashCode());
		result = prime * result + ((granules == null) ? 0 : granules.hashCode());
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
		GranuleMetaDataFile other = (GranuleMetaDataFile) obj;
		if (dataCenter == null) {
			if (other.dataCenter != null)
				return false;
		} else if (!dataCenter.equals(other.dataCenter))
			return false;
		if (granuleDeletes == null) {
			if (other.granuleDeletes != null)
				return false;
		} else if (!granuleDeletes.equals(other.granuleDeletes))
			return false;
		if (granuleInventories == null) {
			if (other.granuleInventories != null)
				return false;
		} else if (!granuleInventories.equals(other.granuleInventories))
			return false;
		if (granulePartialAdds == null) {
			if (other.granulePartialAdds != null)
				return false;
		} else if (!granulePartialAdds.equals(other.granulePartialAdds))
			return false;
		if (granulePartialDeletes == null) {
			if (other.granulePartialDeletes != null)
				return false;
		} else if (!granulePartialDeletes.equals(other.granulePartialDeletes))
			return false;
		if (granuleVerifications == null) {
			if (other.granuleVerifications != null)
				return false;
		} else if (!granuleVerifications.equals(other.granuleVerifications))
			return false;
		if (granules == null) {
			if (other.granules != null)
				return false;
		} else if (!granules.equals(other.granules))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GranuleMetaDataFile [dataCenter=" + dataCenter + ", granules=" + granules + ", granulePartialAdds="
				+ granulePartialAdds + ", granulePartialDeletes=" + granulePartialDeletes + ", granuleDeletes="
				+ granuleDeletes + ", granuleVerifications=" + granuleVerifications + ", granuleInventories="
				+ granuleInventories + "]";
	}

}
