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
 * <p>Classe Java pour GeodeticModel complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="GeodeticModel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HorizontalDatumName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EllipsoidName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SemiMajorAxis" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="DenominatorOfFlatteningRatio" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GeodeticModel", propOrder = {
    "horizontalDatumName",
    "ellipsoidName",
    "semiMajorAxis",
    "denominatorOfFlatteningRatio"
})
public class GeodeticModel {

    @XmlElement(name = "HorizontalDatumName")
    protected String horizontalDatumName;
    @XmlElement(name = "EllipsoidName")
    protected String ellipsoidName;
    @XmlElement(name = "SemiMajorAxis")
    protected BigDecimal semiMajorAxis;
    @XmlElement(name = "DenominatorOfFlatteningRatio")
    protected BigDecimal denominatorOfFlatteningRatio;

    /**
     * Obtient la valeur de la propriété horizontalDatumName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorizontalDatumName() {
        return horizontalDatumName;
    }

    /**
     * Définit la valeur de la propriété horizontalDatumName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorizontalDatumName(String value) {
        this.horizontalDatumName = value;
    }

    /**
     * Obtient la valeur de la propriété ellipsoidName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEllipsoidName() {
        return ellipsoidName;
    }

    /**
     * Définit la valeur de la propriété ellipsoidName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEllipsoidName(String value) {
        this.ellipsoidName = value;
    }

    /**
     * Obtient la valeur de la propriété semiMajorAxis.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSemiMajorAxis() {
        return semiMajorAxis;
    }

    /**
     * Définit la valeur de la propriété semiMajorAxis.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSemiMajorAxis(BigDecimal value) {
        this.semiMajorAxis = value;
    }

    /**
     * Obtient la valeur de la propriété denominatorOfFlatteningRatio.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDenominatorOfFlatteningRatio() {
        return denominatorOfFlatteningRatio;
    }

    /**
     * Définit la valeur de la propriété denominatorOfFlatteningRatio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDenominatorOfFlatteningRatio(BigDecimal value) {
        this.denominatorOfFlatteningRatio = value;
    }

}
