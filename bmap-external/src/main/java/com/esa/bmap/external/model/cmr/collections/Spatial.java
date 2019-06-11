//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity contains collection's spatial
 *       coverage information.
 * 
 * <p>Classe Java pour Spatial complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Spatial">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SpatialCoverageType" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="HorizontalSpatialDomain" type="{}HorizontalSpatialDomain" minOccurs="0"/>
 *         &lt;element name="VerticalSpatialDomain" type="{}VerticalSpatialDomain" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OrbitParameters" type="{}OrbitParameters" minOccurs="0"/>
 *         &lt;element name="GranuleSpatialRepresentation" type="{}GranuleSpatialRepresentation"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Spatial", propOrder = {
    "spatialCoverageType",
    "horizontalSpatialDomain",
    "verticalSpatialDomain",
    "orbitParameters",
    "granuleSpatialRepresentation"
})
public class Spatial {

    @XmlElement(name = "SpatialCoverageType")
    protected String spatialCoverageType;
    @XmlElement(name = "HorizontalSpatialDomain")
    protected HorizontalSpatialDomain horizontalSpatialDomain;
    @XmlElement(name = "VerticalSpatialDomain")
    protected List<VerticalSpatialDomain> verticalSpatialDomain;
    @XmlElement(name = "OrbitParameters")
    protected OrbitParameters orbitParameters;
    @XmlElement(name = "GranuleSpatialRepresentation", required = true)
    @XmlSchemaType(name = "string")
    protected GranuleSpatialRepresentation granuleSpatialRepresentation;

    /**
     * Obtient la valeur de la propri�t� spatialCoverageType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpatialCoverageType() {
        return spatialCoverageType;
    }

    /**
     * D�finit la valeur de la propri�t� spatialCoverageType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpatialCoverageType(String value) {
        this.spatialCoverageType = value;
    }

    /**
     * Obtient la valeur de la propri�t� horizontalSpatialDomain.
     * 
     * @return
     *     possible object is
     *     {@link HorizontalSpatialDomain }
     *     
     */
    public HorizontalSpatialDomain getHorizontalSpatialDomain() {
        return horizontalSpatialDomain;
    }

    /**
     * D�finit la valeur de la propri�t� horizontalSpatialDomain.
     * 
     * @param value
     *     allowed object is
     *     {@link HorizontalSpatialDomain }
     *     
     */
    public void setHorizontalSpatialDomain(HorizontalSpatialDomain value) {
        this.horizontalSpatialDomain = value;
    }

    /**
     * Gets the value of the verticalSpatialDomain property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verticalSpatialDomain property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerticalSpatialDomain().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VerticalSpatialDomain }
     * 
     * 
     */
    public List<VerticalSpatialDomain> getVerticalSpatialDomain() {
        if (verticalSpatialDomain == null) {
            verticalSpatialDomain = new ArrayList<VerticalSpatialDomain>();
        }
        return this.verticalSpatialDomain;
    }

    /**
     * Obtient la valeur de la propri�t� orbitParameters.
     * 
     * @return
     *     possible object is
     *     {@link OrbitParameters }
     *     
     */
    public OrbitParameters getOrbitParameters() {
        return orbitParameters;
    }

    /**
     * D�finit la valeur de la propri�t� orbitParameters.
     * 
     * @param value
     *     allowed object is
     *     {@link OrbitParameters }
     *     
     */
    public void setOrbitParameters(OrbitParameters value) {
        this.orbitParameters = value;
    }

    /**
     * Obtient la valeur de la propri�t� granuleSpatialRepresentation.
     * 
     * @return
     *     possible object is
     *     {@link GranuleSpatialRepresentation }
     *     
     */
    public GranuleSpatialRepresentation getGranuleSpatialRepresentation() {
        return granuleSpatialRepresentation;
    }

    /**
     * D�finit la valeur de la propri�t� granuleSpatialRepresentation.
     * 
     * @param value
     *     allowed object is
     *     {@link GranuleSpatialRepresentation }
     *     
     */
    public void setGranuleSpatialRepresentation(GranuleSpatialRepresentation value) {
        this.granuleSpatialRepresentation = value;
    }

}
