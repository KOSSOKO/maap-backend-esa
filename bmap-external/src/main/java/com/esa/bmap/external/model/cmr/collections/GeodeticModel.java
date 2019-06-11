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
 * <p>Classe Java pour GeodeticModel complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propri�t� horizontalDatumName.
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
     * D�finit la valeur de la propri�t� horizontalDatumName.
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
     * Obtient la valeur de la propri�t� ellipsoidName.
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
     * D�finit la valeur de la propri�t� ellipsoidName.
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
     * Obtient la valeur de la propri�t� semiMajorAxis.
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
     * D�finit la valeur de la propri�t� semiMajorAxis.
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
     * Obtient la valeur de la propri�t� denominatorOfFlatteningRatio.
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
     * D�finit la valeur de la propri�t� denominatorOfFlatteningRatio.
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
