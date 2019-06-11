//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A reference to a collection using one of
 * 				the unique keys in the collection. A collection can be
 * 				referenced using a combination of the short name and version
 * 				id or the dataset id. Setting only one of short name and
 * 				version id without the other is not a valid collection
 * 				reference.
 * 
 * <p>Classe Java pour CollectionRef complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CollectionRef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;sequence>
 *           &lt;element name="ShortName" type="{}CollectionShortName"/>
 *           &lt;element name="VersionId">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;maxLength value="80"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="DataSetId">
 *             &lt;simpleType>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                 &lt;maxLength value="1030"/>
 *                 &lt;minLength value="1"/>
 *               &lt;/restriction>
 *             &lt;/simpleType>
 *           &lt;/element>
 *         &lt;/sequence>
 *         &lt;sequence>
 *           &lt;element name="EntryId" type="{}CommonEntryId" maxOccurs="unbounded"/>
 *         &lt;/sequence>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionRef", propOrder = {
    "shortName",
    "versionId",
    "dataSetId",
    "entryId"
})
public class CollectionRef {

    @XmlElement(name = "ShortName")
    protected String shortName;
    @XmlElement(name = "VersionId")
    protected String versionId;
    @XmlElement(name = "DataSetId")
    protected String dataSetId;
    @XmlElement(name = "EntryId")
    protected List<String> entryId;

    /**
     * Obtient la valeur de la propriété shortName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * Définit la valeur de la propriété shortName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Obtient la valeur de la propriété versionId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * Définit la valeur de la propriété versionId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersionId(String value) {
        this.versionId = value;
    }

    /**
     * Obtient la valeur de la propriété dataSetId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSetId() {
        return dataSetId;
    }

    /**
     * Définit la valeur de la propriété dataSetId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSetId(String value) {
        this.dataSetId = value;
    }

    /**
     * Gets the value of the entryId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entryId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntryId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEntryId() {
        if (entryId == null) {
            entryId = new ArrayList<String>();
        }
        return this.entryId;
    }

}
