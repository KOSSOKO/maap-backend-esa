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
import javax.xml.bind.annotation.XmlType;


/**
 * Unique identifier of the DIF (Directory
 *       Interchange Format).
 * 
 * <p>Classe Java pour EntryId complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="EntryId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EntryId" type="{}CommonEntryId"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntryId", propOrder = {
    "entryId"
})
public class EntryId {

    @XmlElement(name = "EntryId", required = true)
    protected String entryId;

    /**
     * Obtient la valeur de la propri�t� entryId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryId() {
        return entryId;
    }

    /**
     * D�finit la valeur de la propri�t� entryId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryId(String value) {
        this.entryId = value;
    }

}
