//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * This entity stores the start and end
 * 				date/time of a collection.
 * 
 * <p>Classe Java pour RangeDateTime complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="RangeDateTime">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="BeginningDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EndingDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RangeDateTime", propOrder = {
    "beginningDateTime",
    "endingDateTime"
})
public class RangeDateTime {

    @XmlElement(name = "BeginningDateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar beginningDateTime;
    @XmlElement(name = "EndingDateTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endingDateTime;

    /**
     * Obtient la valeur de la propri�t� beginningDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBeginningDateTime() {
        return beginningDateTime;
    }

    /**
     * D�finit la valeur de la propri�t� beginningDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBeginningDateTime(XMLGregorianCalendar value) {
        this.beginningDateTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� endingDateTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndingDateTime() {
        return endingDateTime;
    }

    /**
     * D�finit la valeur de la propri�t� endingDateTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndingDateTime(XMLGregorianCalendar value) {
        this.endingDateTime = value;
    }

}
