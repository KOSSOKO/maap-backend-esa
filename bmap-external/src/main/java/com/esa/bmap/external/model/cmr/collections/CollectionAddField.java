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
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
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
     * Obtient la valeur de la propri�t� browseImageUrl.
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
     * D�finit la valeur de la propri�t� browseImageUrl.
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
     * Obtient la valeur de la propri�t� visible.
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
     * D�finit la valeur de la propri�t� visible.
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
     * Obtient la valeur de la propri�t� temporal.
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
     * D�finit la valeur de la propri�t� temporal.
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
     * Obtient la valeur de la propri�t� deleteTime.
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
     * D�finit la valeur de la propri�t� deleteTime.
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
     * Obtient la valeur de la propri�t� restrictionFlag.
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
     * D�finit la valeur de la propri�t� restrictionFlag.
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
     * Obtient la valeur de la propri�t� spatial.
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
     * D�finit la valeur de la propri�t� spatial.
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
