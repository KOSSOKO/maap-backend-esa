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
 * <p>Classe Java pour CollectionMetaDataFile complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CollectionMetaDataFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataCenter" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Collections" type="{}ListOfCollections" minOccurs="0"/>
 *         &lt;element name="CollectionPartialAdds" type="{}ListOfCollectionPartialAdds" minOccurs="0"/>
 *         &lt;element name="CollectionPartialDeletes" type="{}ListOfCollectionPartialDeletes" minOccurs="0"/>
 *         &lt;element name="CollectionDeletes" type="{}ListOfCollectionDeletes" minOccurs="0"/>
 *         &lt;element name="CollectionVerifications" type="{}ListOfCollections" minOccurs="0"/>
 *         &lt;element name="CollectionInventory" type="{}CollectionInventory" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionMetaDataFile", propOrder = {
    "dataCenter",
    "collections",
    "collectionPartialAdds",
    "collectionPartialDeletes",
    "collectionDeletes",
    "collectionVerifications",
    "collectionInventory"
})
public class CollectionMetaDataFile {

    @XmlElement(name = "DataCenter")
    protected String dataCenter;
    @XmlElement(name = "Collections")
    protected ListOfCollections collections;
    @XmlElement(name = "CollectionPartialAdds")
    protected ListOfCollectionPartialAdds collectionPartialAdds;
    @XmlElement(name = "CollectionPartialDeletes")
    protected ListOfCollectionPartialDeletes collectionPartialDeletes;
    @XmlElement(name = "CollectionDeletes")
    protected ListOfCollectionDeletes collectionDeletes;
    @XmlElement(name = "CollectionVerifications")
    protected ListOfCollections collectionVerifications;
    @XmlElement(name = "CollectionInventory")
    protected CollectionInventory collectionInventory;

    /**
     * Obtient la valeur de la propri�t� dataCenter.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataCenter() {
        return dataCenter;
    }

    /**
     * D�finit la valeur de la propri�t� dataCenter.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataCenter(String value) {
        this.dataCenter = value;
    }

    /**
     * Obtient la valeur de la propri�t� collections.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollections }
     *     
     */
    public ListOfCollections getCollections() {
        return collections;
    }

    /**
     * D�finit la valeur de la propri�t� collections.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollections }
     *     
     */
    public void setCollections(ListOfCollections value) {
        this.collections = value;
    }

    /**
     * Obtient la valeur de la propri�t� collectionPartialAdds.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollectionPartialAdds }
     *     
     */
    public ListOfCollectionPartialAdds getCollectionPartialAdds() {
        return collectionPartialAdds;
    }

    /**
     * D�finit la valeur de la propri�t� collectionPartialAdds.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollectionPartialAdds }
     *     
     */
    public void setCollectionPartialAdds(ListOfCollectionPartialAdds value) {
        this.collectionPartialAdds = value;
    }

    /**
     * Obtient la valeur de la propri�t� collectionPartialDeletes.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollectionPartialDeletes }
     *     
     */
    public ListOfCollectionPartialDeletes getCollectionPartialDeletes() {
        return collectionPartialDeletes;
    }

    /**
     * D�finit la valeur de la propri�t� collectionPartialDeletes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollectionPartialDeletes }
     *     
     */
    public void setCollectionPartialDeletes(ListOfCollectionPartialDeletes value) {
        this.collectionPartialDeletes = value;
    }

    /**
     * Obtient la valeur de la propri�t� collectionDeletes.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollectionDeletes }
     *     
     */
    public ListOfCollectionDeletes getCollectionDeletes() {
        return collectionDeletes;
    }

    /**
     * D�finit la valeur de la propri�t� collectionDeletes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollectionDeletes }
     *     
     */
    public void setCollectionDeletes(ListOfCollectionDeletes value) {
        this.collectionDeletes = value;
    }

    /**
     * Obtient la valeur de la propri�t� collectionVerifications.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollections }
     *     
     */
    public ListOfCollections getCollectionVerifications() {
        return collectionVerifications;
    }

    /**
     * D�finit la valeur de la propri�t� collectionVerifications.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollections }
     *     
     */
    public void setCollectionVerifications(ListOfCollections value) {
        this.collectionVerifications = value;
    }

    /**
     * Obtient la valeur de la propri�t� collectionInventory.
     * 
     * @return
     *     possible object is
     *     {@link CollectionInventory }
     *     
     */
    public CollectionInventory getCollectionInventory() {
        return collectionInventory;
    }

    /**
     * D�finit la valeur de la propri�t� collectionInventory.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionInventory }
     *     
     */
    public void setCollectionInventory(CollectionInventory value) {
        this.collectionInventory = value;
    }

}
