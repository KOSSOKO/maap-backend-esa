//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
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
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété swathWidth.
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
     * Définit la valeur de la propriété swathWidth.
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
     * Obtient la valeur de la propriété period.
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
     * Définit la valeur de la propriété period.
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
     * Obtient la valeur de la propriété inclinationAngle.
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
     * Définit la valeur de la propriété inclinationAngle.
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
     * Obtient la valeur de la propriété numberOfOrbits.
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
     * Définit la valeur de la propriété numberOfOrbits.
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
     * Obtient la valeur de la propriété startCircularLatitude.
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
     * Définit la valeur de la propriété startCircularLatitude.
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
