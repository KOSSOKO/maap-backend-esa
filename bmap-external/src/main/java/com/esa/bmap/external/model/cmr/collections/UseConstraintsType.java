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
 * <p>Classe Java pour UseConstraintsType complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="UseConstraintsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Description" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="4000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="LicenseUrl" type="{}OnlineResource"/>
 *           &lt;element name="LicenseText">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;minLength value="1"/>
 *                 &lt;maxLength value="20000"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UseConstraintsType", propOrder = {
    "description",
    "licenseUrl",
    "licenseText"
})
public class UseConstraintsType {

    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "LicenseUrl")
    protected OnlineResource licenseUrl;
    @XmlElement(name = "LicenseText")
    protected String licenseText;

    /**
     * Obtient la valeur de la propri�t� description.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * D�finit la valeur de la propri�t� description.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Obtient la valeur de la propri�t� licenseUrl.
     * 
     * @return
     *     possible object is
     *     {@link OnlineResource }
     *     
     */
    public OnlineResource getLicenseUrl() {
        return licenseUrl;
    }

    /**
     * D�finit la valeur de la propri�t� licenseUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineResource }
     *     
     */
    public void setLicenseUrl(OnlineResource value) {
        this.licenseUrl = value;
    }

    /**
     * Obtient la valeur de la propri�t� licenseText.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLicenseText() {
        return licenseText;
    }

    /**
     * D�finit la valeur de la propri�t� licenseText.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLicenseText(String value) {
        this.licenseText = value;
    }

}
