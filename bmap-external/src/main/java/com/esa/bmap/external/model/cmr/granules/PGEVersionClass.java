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
 * This entity stores the basic descriptive
 *       characteristics associated with a granule.
 * 
 * <p>Classe Java pour PGEVersionClass complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PGEVersionClass">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PGEName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="PGEVersion">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="10"/>
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
@XmlType(name = "PGEVersionClass", propOrder = {
    "pgeName",
    "pgeVersion"
})
public class PGEVersionClass {

    @XmlElement(name = "PGEName")
    protected String pgeName;
    @XmlElement(name = "PGEVersion", required = true)
    protected String pgeVersion;

    /**
     * Obtient la valeur de la propri�t� pgeName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPGEName() {
        return pgeName;
    }

    /**
     * D�finit la valeur de la propri�t� pgeName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPGEName(String value) {
        this.pgeName = value;
    }

    /**
     * Obtient la valeur de la propri�t� pgeVersion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPGEVersion() {
        return pgeVersion;
    }

    /**
     * D�finit la valeur de la propri�t� pgeVersion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPGEVersion(String value) {
        this.pgeVersion = value;
    }

}
