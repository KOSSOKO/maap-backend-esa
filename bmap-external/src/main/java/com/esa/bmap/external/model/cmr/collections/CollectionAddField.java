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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * A field to be updated in a collection. Not
 *       all fields of a collection support partial updates. If an
 *       update is required for a non-supported field, perform a
 *       complete collection replacement rather than a partial
 *       metadata update.
 * 
 * <p>Classe Java pour CollectionAddField complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CollectionAddField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;choice>
 *           &lt;element name="BrowseImage" type="{}ProviderBrowseId"/>
 *           &lt;element name="BrowseImageUrl" type="{}ProviderBrowseUrl"/>
 *         &lt;/choice>
 *         &lt;element name="Visible" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="Temporal" type="{}Temporal"/>
 *         &lt;element name="DeleteTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="RestrictionFlag" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="Spatial" type="{}Spatial"/>
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
@XmlType(name = "CollectionAddField", propOrder = {
    "browseImage",
    "browseImageUrl",
    "visible",
    "temporal",
    "deleteTime",
    "restrictionFlag",
    "spatial",
    "scienceKeyword"
})
public class CollectionAddField {

    @XmlElement(name = "BrowseImage")
    protected String browseImage;
    @XmlElement(name = "BrowseImageUrl")
    protected ProviderBrowseUrl browseImageUrl;
    @XmlElement(name = "Visible")
    protected Boolean visible;
    @XmlElement(name = "Temporal")
    protected Temporal temporal;
    @XmlElement(name = "DeleteTime")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deleteTime;
    @XmlElement(name = "RestrictionFlag")
    protected BigDecimal restrictionFlag;
    @XmlElement(name = "Spatial")
    protected Spatial spatial;
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
     * Obtient la valeur de la propriété browseImageUrl.
     * 
     * @return
     *     possible object is
     *     {@link ProviderBrowseUrl }
     *     
     */
    public ProviderBrowseUrl getBrowseImageUrl() {
        return browseImageUrl;
    }

    /**
     * Définit la valeur de la propriété browseImageUrl.
     * 
     * @param value
     *     allowed object is
     *     {@link ProviderBrowseUrl }
     *     
     */
    public void setBrowseImageUrl(ProviderBrowseUrl value) {
        this.browseImageUrl = value;
    }

    /**
     * Obtient la valeur de la propriété visible.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVisible() {
        return visible;
    }

    /**
     * Définit la valeur de la propriété visible.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVisible(Boolean value) {
        this.visible = value;
    }

    /**
     * Obtient la valeur de la propriété temporal.
     * 
     * @return
     *     possible object is
     *     {@link Temporal }
     *     
     */
    public Temporal getTemporal() {
        return temporal;
    }

    /**
     * Définit la valeur de la propriété temporal.
     * 
     * @param value
     *     allowed object is
     *     {@link Temporal }
     *     
     */
    public void setTemporal(Temporal value) {
        this.temporal = value;
    }

    /**
     * Obtient la valeur de la propriété deleteTime.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeleteTime() {
        return deleteTime;
    }

    /**
     * Définit la valeur de la propriété deleteTime.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeleteTime(XMLGregorianCalendar value) {
        this.deleteTime = value;
    }

    /**
     * Obtient la valeur de la propriété restrictionFlag.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getRestrictionFlag() {
        return restrictionFlag;
    }

    /**
     * Définit la valeur de la propriété restrictionFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setRestrictionFlag(BigDecimal value) {
        this.restrictionFlag = value;
    }

    /**
     * Obtient la valeur de la propriété spatial.
     * 
     * @return
     *     possible object is
     *     {@link Spatial }
     *     
     */
    public Spatial getSpatial() {
        return spatial;
    }

    /**
     * Définit la valeur de la propriété spatial.
     * 
     * @param value
     *     allowed object is
     *     {@link Spatial }
     *     
     */
    public void setSpatial(Spatial value) {
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
