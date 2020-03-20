//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity contains the domain value and
 * 				type for the granule's vertical spatial
 * 				domain.
 * 
 * <p>Classe Java pour ListOfVerticalSpatialDomains complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ListOfVerticalSpatialDomains">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VerticalSpatialDomain" type="{}VerticalSpatialDomain" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfVerticalSpatialDomains", propOrder = {
    "verticalSpatialDomain"
})
public class ListOfVerticalSpatialDomains {

    @XmlElement(name = "VerticalSpatialDomain")
    protected List<VerticalSpatialDomain> verticalSpatialDomain;

    /**
     * Gets the value of the verticalSpatialDomain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verticalSpatialDomain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerticalSpatialDomain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerticalSpatialDomain }
     * 
     * 
     */
    public List<VerticalSpatialDomain> getVerticalSpatialDomain() {
        if (verticalSpatialDomain == null) {
            verticalSpatialDomain = new ArrayList<VerticalSpatialDomain>();
        }
        return this.verticalSpatialDomain;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((verticalSpatialDomain == null) ? 0 : verticalSpatialDomain.hashCode());
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
		ListOfVerticalSpatialDomains other = (ListOfVerticalSpatialDomains) obj;
		if (verticalSpatialDomain == null) {
			if (other.verticalSpatialDomain != null)
				return false;
		} else if (!verticalSpatialDomain.equals(other.verticalSpatialDomain))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListOfVerticalSpatialDomains [verticalSpatialDomain=" + verticalSpatialDomain + "]";
	}

}
