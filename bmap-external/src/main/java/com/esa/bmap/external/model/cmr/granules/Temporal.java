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
 * This entity contains records, which
 *       describe the basis of the time system used for a specific
 *       collection.
 * 
 * <p>Classe Java pour Temporal complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Temporal">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="RangeDateTime" type="{}RangeDateTime"/>
 *         &lt;element name="SingleDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Temporal", propOrder = {
    "rangeDateTime",
    "singleDateTime"
})
public class Temporal {

    @XmlElement(name = "RangeDateTime")
    protected RangeDateTime rangeDateTime;
    @XmlElement(name = "SingleDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar singleDateTime;

    /**
     * Obtient la valeur de la propri�t� rangeDateTime.
     * 
     * @return
     *     possible object is
     *     {@link RangeDateTime }
     *     
     */
    public RangeDateTime getRangeDateTime() {
        return rangeDateTime;
    }

    /**
     * D�finit la valeur de la propri�t� rangeDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link RangeDateTime }
     *     
     */
    public void setRangeDateTime(RangeDateTime value) {
        this.rangeDateTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� singleDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSingleDateTime() {
        return singleDateTime;
    }

    /**
     * D�finit la valeur de la propri�t� singleDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSingleDateTime(XMLGregorianCalendar value) {
        this.singleDateTime = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rangeDateTime == null) ? 0 : rangeDateTime.hashCode());
		result = prime * result + ((singleDateTime == null) ? 0 : singleDateTime.hashCode());
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
		Temporal other = (Temporal) obj;
		if (rangeDateTime == null) {
			if (other.rangeDateTime != null)
				return false;
		} else if (!rangeDateTime.equals(other.rangeDateTime))
			return false;
		if (singleDateTime == null) {
			if (other.singleDateTime != null)
				return false;
		} else if (!singleDateTime.equals(other.singleDateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Temporal [rangeDateTime=" + rangeDateTime + ", singleDateTime=" + singleDateTime + "]";
	}

}
