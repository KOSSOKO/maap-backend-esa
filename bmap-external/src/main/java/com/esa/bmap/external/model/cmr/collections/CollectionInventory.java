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
 * The collection inventory for a provider. An
 *       empty CollectionReferences element represents an inventory
 *       with no collections.
 * 
 * <p>Classe Java pour CollectionInventory complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CollectionInventory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CollectionReferences" type="{}ListOfCollectionRefs"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionInventory", propOrder = {
    "collectionReferences"
})
public class CollectionInventory {

    @XmlElement(name = "CollectionReferences", required = true)
    protected ListOfCollectionRefs collectionReferences;

    /**
     * Obtient la valeur de la propri�t� collectionReferences.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollectionRefs }
     *     
     */
    public ListOfCollectionRefs getCollectionReferences() {
        return collectionReferences;
    }

    /**
     * D�finit la valeur de la propri�t� collectionReferences.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollectionRefs }
     *     
     */
    public void setCollectionReferences(ListOfCollectionRefs value) {
        this.collectionReferences = value;
    }

}
