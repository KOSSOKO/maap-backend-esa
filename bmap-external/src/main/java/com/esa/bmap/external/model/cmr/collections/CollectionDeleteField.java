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
 * The field that should be deleted. Some
 *       fields require a value to indicate the key of the field to
 *       the correct instance can be identified.
 * 
 * <p>Classe Java pour CollectionDeleteField complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CollectionDeleteField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="BrowseImage" type="{}ProviderBrowseId"/>
 *         &lt;element name="AllBrowseImages" type="{}EmptyType"/>
 *         &lt;element name="Temporal" type="{}EmptyType"/>
 *         &lt;element name="DeleteTime" type="{}EmptyType"/>
 *         &lt;element name="RestrictionFlag" type="{}EmptyType"/>
 *         &lt;element name="Spatial" type="{}EmptyType"/>
 *         &lt;element name="ScienceKeyword" type="{}ScienceKeyword"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionDeleteField", propOrder = {
    "browseImage",
    "allBrowseImages",
    "temporal",
    "deleteTime",
    "restrictionFlag",
    "spatial",
    "scienceKeyword"
})
public class CollectionDeleteField {

    @XmlElement(name = "BrowseImage")
    protected String browseImage;
    @XmlElement(name = "AllBrowseImages")
    protected EmptyType allBrowseImages;
    @XmlElement(name = "Temporal")
    protected EmptyType temporal;
    @XmlElement(name = "DeleteTime")
    protected EmptyType deleteTime;
    @XmlElement(name = "RestrictionFlag")
    protected EmptyType restrictionFlag;
    @XmlElement(name = "Spatial")
    protected EmptyType spatial;
    @XmlElement(name = "ScienceKeyword")
    protected ScienceKeyword scienceKeyword;

    /**
     * Obtient la valeur de la propri�t� browseImage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrowseImage() {
        return browseImage;
    }

    /**
     * D�finit la valeur de la propri�t� browseImage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrowseImage(String value) {
        this.browseImage = value;
    }

    /**
     * Obtient la valeur de la propri�t� allBrowseImages.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getAllBrowseImages() {
        return allBrowseImages;
    }

    /**
     * D�finit la valeur de la propri�t� allBrowseImages.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setAllBrowseImages(EmptyType value) {
        this.allBrowseImages = value;
    }

    /**
     * Obtient la valeur de la propri�t� temporal.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getTemporal() {
        return temporal;
    }

    /**
     * D�finit la valeur de la propri�t� temporal.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setTemporal(EmptyType value) {
        this.temporal = value;
    }

    /**
     * Obtient la valeur de la propri�t� deleteTime.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getDeleteTime() {
        return deleteTime;
    }

    /**
     * D�finit la valeur de la propri�t� deleteTime.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setDeleteTime(EmptyType value) {
        this.deleteTime = value;
    }

    /**
     * Obtient la valeur de la propri�t� restrictionFlag.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getRestrictionFlag() {
        return restrictionFlag;
    }

    /**
     * D�finit la valeur de la propri�t� restrictionFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setRestrictionFlag(EmptyType value) {
        this.restrictionFlag = value;
    }

    /**
     * Obtient la valeur de la propri�t� spatial.
     * 
     * @return
     *     possible object is
     *     {@link EmptyType }
     *     
     */
    public EmptyType getSpatial() {
        return spatial;
    }

    /**
     * D�finit la valeur de la propri�t� spatial.
     * 
     * @param value
     *     allowed object is
     *     {@link EmptyType }
     *     
     */
    public void setSpatial(EmptyType value) {
        this.spatial = value;
    }

    /**
     * Obtient la valeur de la propri�t� scienceKeyword.
     * 
     * @return
     *     possible object is
     *     {@link ScienceKeyword }
     *     
     */
    public ScienceKeyword getScienceKeyword() {
        return scienceKeyword;
    }

    /**
     * D�finit la valeur de la propri�t� scienceKeyword.
     * 
     * @param value
     *     allowed object is
     *     {@link ScienceKeyword }
     *     
     */
    public void setScienceKeyword(ScienceKeyword value) {
        this.scienceKeyword = value;
    }

}
