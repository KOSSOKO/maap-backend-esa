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
 * A partial add to be applied to a
 *       collection. The partial add may contain one or more fields.
 *       If the field being added contains a unique key and the field
 *       already exists, it will be updated or replaced, otherwise it
 *       will be added. Refer to the individual field documentation
 *       for details.
 * 
 * <p>Classe Java pour CollectionPartialAdd complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CollectionPartialAdd">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Targets" type="{}ListOfCollectionUpdateTargets"/>
 *         &lt;element name="Fields" type="{}ListOfCollectionAddFields"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionPartialAdd", propOrder = {
    "targets",
    "fields"
})
public class CollectionPartialAdd {

    @XmlElement(name = "Targets", required = true)
    protected ListOfCollectionUpdateTargets targets;
    @XmlElement(name = "Fields", required = true)
    protected ListOfCollectionAddFields fields;

    /**
     * Obtient la valeur de la propri�t� targets.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollectionUpdateTargets }
     *     
     */
    public ListOfCollectionUpdateTargets getTargets() {
        return targets;
    }

    /**
     * D�finit la valeur de la propri�t� targets.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollectionUpdateTargets }
     *     
     */
    public void setTargets(ListOfCollectionUpdateTargets value) {
        this.targets = value;
    }

    /**
     * Obtient la valeur de la propri�t� fields.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollectionAddFields }
     *     
     */
    public ListOfCollectionAddFields getFields() {
        return fields;
    }

    /**
     * D�finit la valeur de la propri�t� fields.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollectionAddFields }
     *     
     */
    public void setFields(ListOfCollectionAddFields value) {
        this.fields = value;
    }

}
