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
 * The unique identifier for a browse image
 * 				from the provider.
 * 
 * <p>Classe Java pour ProviderBrowseElement complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ProviderBrowseElement">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ProviderBrowseId" type="{}ProviderBrowseId"/>
 *         &lt;element name="ProviderBrowseUrl" type="{}ProviderBrowseUrl"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProviderBrowseElement", propOrder = {
    "providerBrowseId",
    "providerBrowseUrl"
})
public class ProviderBrowseElement {

    @XmlElement(name = "ProviderBrowseId")
    protected String providerBrowseId;
    @XmlElement(name = "ProviderBrowseUrl")
    protected ProviderBrowseUrl providerBrowseUrl;

    /**
     * Obtient la valeur de la propri�t� providerBrowseId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderBrowseId() {
        return providerBrowseId;
    }

    /**
     * D�finit la valeur de la propri�t� providerBrowseId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderBrowseId(String value) {
        this.providerBrowseId = value;
    }

    /**
     * Obtient la valeur de la propri�t� providerBrowseUrl.
     * 
     * @return
     *     possible object is
     *     {@link ProviderBrowseUrl }
     *     
     */
    public ProviderBrowseUrl getProviderBrowseUrl() {
        return providerBrowseUrl;
    }

    /**
     * D�finit la valeur de la propri�t� providerBrowseUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderBrowseUrl }
     *     
     */
    public void setProviderBrowseUrl(ProviderBrowseUrl value) {
        this.providerBrowseUrl = value;
    }

}
