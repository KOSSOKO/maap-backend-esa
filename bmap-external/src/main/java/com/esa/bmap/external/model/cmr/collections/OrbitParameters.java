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
 * Orbit parameters for the collection used by
 *       the Orbital Backtrack Algorithm.
 * 
 * <p>Classe Java pour OrbitParameters complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="OrbitParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SwathWidth" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Period" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="InclinationAngle" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="NumberOfOrbits" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="StartCircularLatitude" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrbitParameters", propOrder = {
    "swathWidth",
    "period",
    "inclinationAngle",
    "numberOfOrbits",
    "startCircularLatitude"
})
public class OrbitParameters {

    @XmlElement(name = "SwathWidth", required = true)
    protected BigDecimal swathWidth;
    @XmlElement(name = "Period", required = true)
    protected BigDecimal period;
    @XmlElement(name = "InclinationAngle", required = true)
    protected BigDecimal inclinationAngle;
    @XmlElement(name = "NumberOfOrbits", required = true)
    protected BigDecimal numberOfOrbits;
    @XmlElement(name = "StartCircularLatitude")
    protected BigDecimal startCircularLatitude;

    /**
     * Obtient la valeur de la propri�t� swathWidth.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSwathWidth() {
        return swathWidth;
    }

    /**
     * D�finit la valeur de la propri�t� swathWidth.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSwathWidth(BigDecimal value) {
        this.swathWidth = value;
    }

    /**
     * Obtient la valeur de la propri�t� period.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriod() {
        return period;
    }

    /**
     * D�finit la valeur de la propri�t� period.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriod(BigDecimal value) {
        this.period = value;
    }

    /**
     * Obtient la valeur de la propri�t� inclinationAngle.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getInclinationAngle() {
        return inclinationAngle;
    }

    /**
     * D�finit la valeur de la propri�t� inclinationAngle.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setInclinationAngle(BigDecimal value) {
        this.inclinationAngle = value;
    }

    /**
     * Obtient la valeur de la propri�t� numberOfOrbits.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getNumberOfOrbits() {
        return numberOfOrbits;
    }

    /**
     * D�finit la valeur de la propri�t� numberOfOrbits.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setNumberOfOrbits(BigDecimal value) {
        this.numberOfOrbits = value;
    }

    /**
     * Obtient la valeur de la propri�t� startCircularLatitude.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStartCircularLatitude() {
        return startCircularLatitude;
    }

    /**
     * D�finit la valeur de la propri�t� startCircularLatitude.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStartCircularLatitude(BigDecimal value) {
        this.startCircularLatitude = value;
    }

}
