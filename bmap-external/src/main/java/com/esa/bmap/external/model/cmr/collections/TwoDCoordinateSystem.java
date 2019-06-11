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
 * Defines a named two dimensional coordinate
 *       system for the collection.
 * 
 * <p>Classe Java pour TwoDCoordinateSystem complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TwoDCoordinateSystem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TwoDCoordinateSystemName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Coordinate1" type="{}TwoDCoordinate"/>
 *         &lt;element name="Coordinate2" type="{}TwoDCoordinate"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TwoDCoordinateSystem", propOrder = {
    "twoDCoordinateSystemName",
    "coordinate1",
    "coordinate2"
})
public class TwoDCoordinateSystem {

    @XmlElement(name = "TwoDCoordinateSystemName", required = true)
    protected String twoDCoordinateSystemName;
    @XmlElement(name = "Coordinate1", required = true)
    protected TwoDCoordinate coordinate1;
    @XmlElement(name = "Coordinate2", required = true)
    protected TwoDCoordinate coordinate2;

    /**
     * Obtient la valeur de la propriété twoDCoordinateSystemName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTwoDCoordinateSystemName() {
        return twoDCoordinateSystemName;
    }

    /**
     * Définit la valeur de la propriété twoDCoordinateSystemName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTwoDCoordinateSystemName(String value) {
        this.twoDCoordinateSystemName = value;
    }

    /**
     * Obtient la valeur de la propriété coordinate1.
     * 
     * @return
     *     possible object is
     *     {@link TwoDCoordinate }
     *     
     */
    public TwoDCoordinate getCoordinate1() {
        return coordinate1;
    }

    /**
     * Définit la valeur de la propriété coordinate1.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoDCoordinate }
     *     
     */
    public void setCoordinate1(TwoDCoordinate value) {
        this.coordinate1 = value;
    }

    /**
     * Obtient la valeur de la propriété coordinate2.
     * 
     * @return
     *     possible object is
     *     {@link TwoDCoordinate }
     *     
     */
    public TwoDCoordinate getCoordinate2() {
        return coordinate2;
    }

    /**
     * Définit la valeur de la propriété coordinate2.
     * 
     * @param value
     *     allowed object is
     *     {@link TwoDCoordinate }
     *     
     */
    public void setCoordinate2(TwoDCoordinate value) {
        this.coordinate2 = value;
    }

}
