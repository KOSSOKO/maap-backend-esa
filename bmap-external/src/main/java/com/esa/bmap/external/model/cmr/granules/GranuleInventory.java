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
 * An inventory of granules for a given
 *       collection. An empty GranuleURs element represents an
 *       inventory with no granules.
 * 
 * <p>Classe Java pour GranuleInventory complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GranuleInventory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CollectionRef" type="{}CollectionRef"/>
 *         &lt;element name="GranuleURs" type="{}ListOfGranuleURs"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GranuleInventory", propOrder = {
    "collectionRef",
    "granuleURs"
})
public class GranuleInventory {

    @XmlElement(name = "CollectionRef", required = true)
    protected CollectionRef collectionRef;
    @XmlElement(name = "GranuleURs", required = true)
    protected ListOfGranuleURs granuleURs;

    /**
     * Obtient la valeur de la propri�t� collectionRef.
     * 
     * @return
     *     possible object is
     *     {@link CollectionRef }
     *     
     */
    public CollectionRef getCollectionRef() {
        return collectionRef;
    }

    /**
     * D�finit la valeur de la propri�t� collectionRef.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionRef }
     *     
     */
    public void setCollectionRef(CollectionRef value) {
        this.collectionRef = value;
    }

    /**
     * Obtient la valeur de la propri�t� granuleURs.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranuleURs }
     *     
     */
    public ListOfGranuleURs getGranuleURs() {
        return granuleURs;
    }

    /**
     * D�finit la valeur de la propri�t� granuleURs.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranuleURs }
     *     
     */
    public void setGranuleURs(ListOfGranuleURs value) {
        this.granuleURs = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((collectionRef == null) ? 0 : collectionRef.hashCode());
		result = prime * result + ((granuleURs == null) ? 0 : granuleURs.hashCode());
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
		GranuleInventory other = (GranuleInventory) obj;
		if (collectionRef == null) {
			if (other.collectionRef != null)
				return false;
		} else if (!collectionRef.equals(other.collectionRef))
			return false;
		if (granuleURs == null) {
			if (other.granuleURs != null)
				return false;
		} else if (!granuleURs.equals(other.granuleURs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GranuleInventory [collectionRef=" + collectionRef + ", granuleURs=" + granuleURs + "]";
	}

}
