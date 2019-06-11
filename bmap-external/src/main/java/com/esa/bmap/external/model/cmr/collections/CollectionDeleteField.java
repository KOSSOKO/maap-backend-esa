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
 * The field that should be deleted. Some
 *       fields require a value to indicate the key of the field to
 *       the correct instance can be identified.
 * 
 * <p>Classe Java pour CollectionDeleteField complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propriété browseImage.
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
     * Définit la valeur de la propriété browseImage.
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
     * Obtient la valeur de la propriété allBrowseImages.
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
     * Définit la valeur de la propriété allBrowseImages.
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
     * Obtient la valeur de la propriété temporal.
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
     * Définit la valeur de la propriété temporal.
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
     * Obtient la valeur de la propriété deleteTime.
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
     * Définit la valeur de la propriété deleteTime.
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
     * Obtient la valeur de la propriété restrictionFlag.
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
     * Définit la valeur de la propriété restrictionFlag.
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
     * Obtient la valeur de la propriété spatial.
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
     * Définit la valeur de la propriété spatial.
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
     * Obtient la valeur de la propriété scienceKeyword.
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
     * Définit la valeur de la propriété scienceKeyword.
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
