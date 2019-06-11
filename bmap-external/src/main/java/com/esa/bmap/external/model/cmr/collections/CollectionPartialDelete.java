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
 * A partial delete to be applied to a
 *       collection. The partial may contain one or more fields. If a
 *       delete is attempted the value will be set to a default value.
 *       Refer to the individual field documentation for
 *       details.
 * 
 * <p>Classe Java pour CollectionPartialDelete complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="CollectionPartialDelete">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Targets" type="{}ListOfCollectionUpdateTargets"/>
 *         &lt;element name="Fields" type="{}ListOfCollectionDeleteFields"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CollectionPartialDelete", propOrder = {
    "targets",
    "fields"
})
public class CollectionPartialDelete {

    @XmlElement(name = "Targets", required = true)
    protected ListOfCollectionUpdateTargets targets;
    @XmlElement(name = "Fields", required = true)
    protected ListOfCollectionDeleteFields fields;

    /**
     * Obtient la valeur de la propriété targets.
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
     * Définit la valeur de la propriété targets.
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
     * Obtient la valeur de la propriété fields.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCollectionDeleteFields }
     *     
     */
    public ListOfCollectionDeleteFields getFields() {
        return fields;
    }

    /**
     * Définit la valeur de la propriété fields.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCollectionDeleteFields }
     *     
     */
    public void setFields(ListOfCollectionDeleteFields value) {
        this.fields = value;
    }

}
