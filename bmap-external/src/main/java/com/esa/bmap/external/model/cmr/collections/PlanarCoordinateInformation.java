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
 * <p>Classe Java pour PlanarCoordinateInformation complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlanarCoordinateInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DistanceUnits" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="EncodingMethod" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="DistanceAndBearingRepresentation" type="{}DistanceAndBearingRepresentation"/>
 *           &lt;element name="CoordinateRepresentation" type="{}CoordinateRepresentation"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlanarCoordinateInformation", propOrder = {
    "distanceUnits",
    "encodingMethod",
    "distanceAndBearingRepresentation",
    "coordinateRepresentation"
})
public class PlanarCoordinateInformation {

    @XmlElement(name = "DistanceUnits")
    protected String distanceUnits;
    @XmlElement(name = "EncodingMethod")
    protected String encodingMethod;
    @XmlElement(name = "DistanceAndBearingRepresentation")
    protected DistanceAndBearingRepresentation distanceAndBearingRepresentation;
    @XmlElement(name = "CoordinateRepresentation")
    protected CoordinateRepresentation coordinateRepresentation;

    /**
     * Obtient la valeur de la propri�t� distanceUnits.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDistanceUnits() {
        return distanceUnits;
    }

    /**
     * D�finit la valeur de la propri�t� distanceUnits.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDistanceUnits(String value) {
        this.distanceUnits = value;
    }

    /**
     * Obtient la valeur de la propri�t� encodingMethod.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncodingMethod() {
        return encodingMethod;
    }

    /**
     * D�finit la valeur de la propri�t� encodingMethod.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncodingMethod(String value) {
        this.encodingMethod = value;
    }

    /**
     * Obtient la valeur de la propri�t� distanceAndBearingRepresentation.
     * 
     * @return
     *     possible object is
     *     {@link DistanceAndBearingRepresentation }
     *     
     */
    public DistanceAndBearingRepresentation getDistanceAndBearingRepresentation() {
        return distanceAndBearingRepresentation;
    }

    /**
     * D�finit la valeur de la propri�t� distanceAndBearingRepresentation.
     * 
     * @param value
     *     allowed object is
     *     {@link DistanceAndBearingRepresentation }
     *     
     */
    public void setDistanceAndBearingRepresentation(DistanceAndBearingRepresentation value) {
        this.distanceAndBearingRepresentation = value;
    }

    /**
     * Obtient la valeur de la propri�t� coordinateRepresentation.
     * 
     * @return
     *     possible object is
     *     {@link CoordinateRepresentation }
     *     
     */
    public CoordinateRepresentation getCoordinateRepresentation() {
        return coordinateRepresentation;
    }

    /**
     * D�finit la valeur de la propri�t� coordinateRepresentation.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinateRepresentation }
     *     
     */
    public void setCoordinateRepresentation(CoordinateRepresentation value) {
        this.coordinateRepresentation = value;
    }

}
