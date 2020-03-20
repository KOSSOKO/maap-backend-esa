//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.21 à 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour CoordinateSystem.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="CoordinateSystem">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CARTESIAN"/>
 *     &lt;enumeration value="GEODETIC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CoordinateSystem")
@XmlEnum
public enum CoordinateSystem {


    /**
     * Indicates a cartesian coordinate
     * 						system.
     * 
     */
    CARTESIAN,

    /**
     * Indicates a geodetic coordinate
     * 						system.
     * 
     */
    GEODETIC;

    public String value() {
        return name();
    }

    public static CoordinateSystem fromValue(String v) {
        return valueOf(v);
    }

}
