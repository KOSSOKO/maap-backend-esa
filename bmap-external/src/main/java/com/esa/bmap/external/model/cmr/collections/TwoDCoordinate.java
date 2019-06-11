//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Defines the minimum and maximum value for
 *       one dimension of a two dimensional
 *       coordinate.
 * 
 * <p>Classe Java pour TwoDCoordinate complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="TwoDCoordinate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MinimumValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="MaximumValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TwoDCoordinate", propOrder = {
    "minimumValue",
    "maximumValue"
})
public class TwoDCoordinate {

    @XmlElement(name = "MinimumValue")
    protected BigDecimal minimumValue;
    @XmlElement(name = "MaximumValue")
    protected BigDecimal maximumValue;

    /**
     * Obtient la valeur de la propri�t� minimumValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMinimumValue() {
        return minimumValue;
    }

    /**
     * D�finit la valeur de la propri�t� minimumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMinimumValue(BigDecimal value) {
        this.minimumValue = value;
    }

    /**
     * Obtient la valeur de la propri�t� maximumValue.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getMaximumValue() {
        return maximumValue;
    }

    /**
     * D�finit la valeur de la propri�t� maximumValue.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setMaximumValue(BigDecimal value) {
        this.maximumValue = value;
    }

}
