//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A list of urls to browse
 * 				images.
 * 
 * <p>Classe Java pour ListOfProviderBrowseUrls complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ListOfProviderBrowseUrls">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProviderBrowseUrl" type="{}ProviderBrowseUrl" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfProviderBrowseUrls", propOrder = {
    "providerBrowseUrl"
})
public class ListOfProviderBrowseUrls {

    @XmlElement(name = "ProviderBrowseUrl")
    protected List<ProviderBrowseUrl> providerBrowseUrl;

    /**
     * Gets the value of the providerBrowseUrl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the providerBrowseUrl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProviderBrowseUrl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProviderBrowseUrl }
     * 
     * 
     */
    public List<ProviderBrowseUrl> getProviderBrowseUrl() {
        if (providerBrowseUrl == null) {
            providerBrowseUrl = new ArrayList<ProviderBrowseUrl>();
        }
        return this.providerBrowseUrl;
    }

}
