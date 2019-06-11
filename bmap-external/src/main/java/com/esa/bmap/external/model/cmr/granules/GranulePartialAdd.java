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
 * A partial add to be applied to a granule.
 *       The partial add may contain one or more fields. If the field
 *       being updated contains a unique key and the field already
 *       exists, it will be updated or replaced, otherwise it will be
 *       added. Refer to the individual field documentation for
 *       details.
 * 
 * <p>Classe Java pour GranulePartialAdd complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GranulePartialAdd">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Targets" type="{}ListOfGranuleUpdateTargets"/>
 *         &lt;element name="Fields" type="{}ListOfGranuleAddFields"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GranulePartialAdd", propOrder = {
    "targets",
    "fields"
})
public class GranulePartialAdd {

    @XmlElement(name = "Targets", required = true)
    protected ListOfGranuleUpdateTargets targets;
    @XmlElement(name = "Fields", required = true)
    protected ListOfGranuleAddFields fields;

    /**
     * Obtient la valeur de la propri�t� targets.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranuleUpdateTargets }
     *     
     */
    public ListOfGranuleUpdateTargets getTargets() {
        return targets;
    }

    /**
     * D�finit la valeur de la propri�t� targets.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranuleUpdateTargets }
     *     
     */
    public void setTargets(ListOfGranuleUpdateTargets value) {
        this.targets = value;
    }

    /**
     * Obtient la valeur de la propri�t� fields.
     * 
     * @return
     *     possible object is
     *     {@link ListOfGranuleAddFields }
     *     
     */
    public ListOfGranuleAddFields getFields() {
        return fields;
    }

    /**
     * D�finit la valeur de la propri�t� fields.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfGranuleAddFields }
     *     
     */
    public void setFields(ListOfGranuleAddFields value) {
        this.fields = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((targets == null) ? 0 : targets.hashCode());
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
		GranulePartialAdd other = (GranulePartialAdd) obj;
		if (fields == null) {
			if (other.fields != null)
				return false;
		} else if (!fields.equals(other.fields))
			return false;
		if (targets == null) {
			if (other.targets != null)
				return false;
		} else if (!targets.equals(other.targets))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GranulePartialAdd [targets=" + targets + ", fields=" + fields + "]";
	}

}
