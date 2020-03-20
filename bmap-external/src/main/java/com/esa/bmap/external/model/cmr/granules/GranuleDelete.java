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
 * Granule that should be immediately
 *       deleted.
 * 
 * <p>Classe Java pour GranuleDelete complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GranuleDelete">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
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
@XmlType(name = "GranuleDelete", propOrder = {
    "granuleUR"
})
public class GranuleDelete {

    @XmlElement(name = "GranuleUR", required = true)
    protected String granuleUR;

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
		return "GranuleDelete [granuleUR=" + granuleUR + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((granuleUR == null) ? 0 : granuleUR.hashCode());
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
		GranuleDelete other = (GranuleDelete) obj;
		if (granuleUR == null) {
			if (other.granuleUR != null)
				return false;
		} else if (!granuleUR.equals(other.granuleUR))
			return false;
		return true;
	}

}
