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
 * The reference frame or system from which
 *       altitude or depths are measured. The term 'altitude' is used
 *       instead of the common term 'elevation' to conform to the
 *       terminology in Federal Information Processing Standards 70-1
 *       and 173. The information contains the datum name, distance
 *       units and encoding method, which provide the definition for
 *       the system.
 * 
 * <p>Classe Java pour VerticalSystemDefinition complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VerticalSystemDefinition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatumName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
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
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Resolutions" type="{}ListOfResolutions" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VerticalSystemDefinition", propOrder = {
    "datumName",
    "distanceUnits",
    "encodingMethod",
    "resolutions"
})
public class VerticalSystemDefinition {

    @XmlElement(name = "DatumName")
    protected String datumName;
    @XmlElement(name = "DistanceUnits")
    protected String distanceUnits;
    @XmlElement(name = "EncodingMethod")
    protected String encodingMethod;
    @XmlElement(name = "Resolutions")
    protected ListOfResolutions resolutions;

    /**
     * Obtient la valeur de la propriété datumName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatumName() {
        return datumName;
    }

    /**
     * Définit la valeur de la propriété datumName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatumName(String value) {
        this.datumName = value;
    }

    /**
     * Obtient la valeur de la propriété distanceUnits.
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
     * Définit la valeur de la propriété distanceUnits.
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
     * Obtient la valeur de la propriété encodingMethod.
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
     * Définit la valeur de la propriété encodingMethod.
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
     * Obtient la valeur de la propriété resolutions.
     * 
     * @return
     *     possible object is
     *     {@link ListOfResolutions }
     *     
     */
    public ListOfResolutions getResolutions() {
        return resolutions;
    }

    /**
     * Définit la valeur de la propriété resolutions.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfResolutions }
     *     
     */
    public void setResolutions(ListOfResolutions value) {
        this.resolutions = value;
    }

}
