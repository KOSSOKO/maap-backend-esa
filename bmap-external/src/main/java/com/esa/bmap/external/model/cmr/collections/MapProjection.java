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
 * <p>Classe Java pour MapProjection complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MapProjection">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MapProjectionName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="MapProjectionPointer" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="255"/>
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
@XmlType(name = "MapProjection", propOrder = {
    "mapProjectionName",
    "mapProjectionPointer"
})
public class MapProjection {

    @XmlElement(name = "MapProjectionName")
    protected String mapProjectionName;
    @XmlElement(name = "MapProjectionPointer")
    protected String mapProjectionPointer;

    /**
     * Obtient la valeur de la propriété mapProjectionName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapProjectionName() {
        return mapProjectionName;
    }

    /**
     * Définit la valeur de la propriété mapProjectionName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapProjectionName(String value) {
        this.mapProjectionName = value;
    }

    /**
     * Obtient la valeur de la propriété mapProjectionPointer.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMapProjectionPointer() {
        return mapProjectionPointer;
    }

    /**
     * Définit la valeur de la propriété mapProjectionPointer.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMapProjectionPointer(String value) {
        this.mapProjectionPointer = value;
    }

}
