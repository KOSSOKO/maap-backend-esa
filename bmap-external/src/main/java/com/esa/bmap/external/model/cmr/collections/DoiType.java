//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This element stores the DOI (Digital Object Identifier) that identifies the collection.  Note: The values should start with the directory indicator which in ESDIS' case is 10.  If the DOI was registered through ESDIS, the beginning of the string should be 10.5067. The DOI URL is not stored here; it should be stored as an OnlineResource. The DOI organization that is responsible for creating the DOI is described in the Authority element. For ESDIS records the value of https://doi.org/ should be used. While this element is not required, NASA metadata providers are strongly encouraged to include DOI and DOI Authority for their collections. For those that want to specify that a DOI is not applicable for their record use the second option.
 * 
 * <p>Classe Java pour DoiType complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="DoiType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="DOI">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;maxLength value="1024"/>
 *                 &lt;minLength value="1"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *           &lt;element name="Authority" minOccurs="0">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;maxLength value="80"/>
 *                 &lt;minLength value="1"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="MissingReason">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;enumeration value="Not Applicable"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *           &lt;element name="Explanation" minOccurs="0">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;maxLength value="80"/>
 *                 &lt;minLength value="1"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DoiType", propOrder = {
    "doi",
    "authority",
    "missingReason",
    "explanation"
})
public class DoiType {

    @XmlElement(name = "DOI")
    protected String doi;
    @XmlElement(name = "Authority")
    protected String authority;
    @XmlElement(name = "MissingReason")
    protected String missingReason;
    @XmlElement(name = "Explanation")
    protected String explanation;

    /**
     * Obtient la valeur de la propriété doi.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOI() {
        return doi;
    }

    /**
     * Définit la valeur de la propriété doi.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOI(String value) {
        this.doi = value;
    }

    /**
     * Obtient la valeur de la propriété authority.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Définit la valeur de la propriété authority.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthority(String value) {
        this.authority = value;
    }

    /**
     * Obtient la valeur de la propriété missingReason.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMissingReason() {
        return missingReason;
    }

    /**
     * Définit la valeur de la propriété missingReason.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMissingReason(String value) {
        this.missingReason = value;
    }

    /**
     * Obtient la valeur de la propriété explanation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExplanation() {
        return explanation;
    }

    /**
     * Définit la valeur de la propriété explanation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExplanation(String value) {
        this.explanation = value;
    }

}
