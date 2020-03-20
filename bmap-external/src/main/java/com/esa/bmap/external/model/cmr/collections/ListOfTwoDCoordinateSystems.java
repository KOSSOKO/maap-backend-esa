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
 * <p>Classe Java pour ListOfTwoDCoordinateSystems complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ListOfTwoDCoordinateSystems">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TwoDCoordinateSystem" type="{}TwoDCoordinateSystem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfTwoDCoordinateSystems", propOrder = {
    "twoDCoordinateSystem"
})
public class ListOfTwoDCoordinateSystems {

    @XmlElement(name = "TwoDCoordinateSystem")
    protected List<TwoDCoordinateSystem> twoDCoordinateSystem;

    /**
     * Gets the value of the twoDCoordinateSystem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the twoDCoordinateSystem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTwoDCoordinateSystem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TwoDCoordinateSystem }
     * 
     * 
     */
    public List<TwoDCoordinateSystem> getTwoDCoordinateSystem() {
        if (twoDCoordinateSystem == null) {
            twoDCoordinateSystem = new ArrayList<TwoDCoordinateSystem>();
        }
        return this.twoDCoordinateSystem;
    }

}
