//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity stores the online URL(s) for
 * 				the granule if there is any. Those URL either provides the
 * 				site that user can obtain granule data or give the further
 * 				instruction of obtaining the granule data.
 * 
 * <p>Classe Java pour ListOfOnlineAccessURLs complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ListOfOnlineAccessURLs">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OnlineAccessURL" type="{}OnlineAccessURL" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfOnlineAccessURLs", propOrder = {
    "onlineAccessURL"
})
public class ListOfOnlineAccessURLs {

    @XmlElement(name = "OnlineAccessURL")
    protected List<OnlineAccessURL> onlineAccessURL;

    /**
     * Gets the value of the onlineAccessURL property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the onlineAccessURL property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOnlineAccessURL().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OnlineAccessURL }
     * 
     * 
     */
    public List<OnlineAccessURL> getOnlineAccessURL() {
        if (onlineAccessURL == null) {
            onlineAccessURL = new ArrayList<OnlineAccessURL>();
        }
        return this.onlineAccessURL;
    }

}
