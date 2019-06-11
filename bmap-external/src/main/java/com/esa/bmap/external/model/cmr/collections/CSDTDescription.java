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
 * This entity stores the description of the
 *       data organization of the collection (i.e. a generalized
 *       collection Description in terms of internal structure). There
 *       are many possible structures. All should be describable by
 *       one of the PrimaryCSDTs (fixed domain), but the specific
 *       implementation has an unbounded domain indicating the range
 *       at the lower structured level. While many CSDTs may exist in
 *       a granule, only the primary or dominant CSDT is described
 *       (e.g. PrimaryCSDT = swath, Implementation = HDF-EOS). The
 *       indirect reference is used for collection specific data
 *       organization labels. A comment field is provided for further
 *       explanation.
 * 
 * <p>Classe Java pour CSDTDescription complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CSDTDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PrimaryCSDT">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Implementation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="CSDTComments" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="IndirectReference" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CSDTDescription", propOrder = {
    "primaryCSDT",
    "implementation",
    "csdtComments",
    "indirectReference"
})
public class CSDTDescription {

    @XmlElement(name = "PrimaryCSDT", required = true)
    protected String primaryCSDT;
    @XmlElement(name = "Implementation")
    protected String implementation;
    @XmlElement(name = "CSDTComments")
    protected String csdtComments;
    @XmlElement(name = "IndirectReference")
    protected String indirectReference;

    /**
     * Obtient la valeur de la propri�t� primaryCSDT.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryCSDT() {
        return primaryCSDT;
    }

    /**
     * D�finit la valeur de la propri�t� primaryCSDT.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryCSDT(String value) {
        this.primaryCSDT = value;
    }

    /**
     * Obtient la valeur de la propri�t� implementation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImplementation() {
        return implementation;
    }

    /**
     * D�finit la valeur de la propri�t� implementation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImplementation(String value) {
        this.implementation = value;
    }

    /**
     * Obtient la valeur de la propri�t� csdtComments.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCSDTComments() {
        return csdtComments;
    }

    /**
     * D�finit la valeur de la propri�t� csdtComments.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCSDTComments(String value) {
        this.csdtComments = value;
    }

    /**
     * Obtient la valeur de la propri�t� indirectReference.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirectReference() {
        return indirectReference;
    }

    /**
     * D�finit la valeur de la propri�t� indirectReference.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirectReference(String value) {
        this.indirectReference = value;
    }

}
