//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour GranuleSpatialRepresentation.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="GranuleSpatialRepresentation">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CARTESIAN"/>
 *     &lt;enumeration value="GEODETIC"/>
 *     &lt;enumeration value="ORBIT"/>
 *     &lt;enumeration value="NO_SPATIAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GranuleSpatialRepresentation")
@XmlEnum
public enum GranuleSpatialRepresentation {

    CARTESIAN,
    GEODETIC,
    ORBIT,
    NO_SPATIAL;

    public String value() {
        return name();
    }

    public static GranuleSpatialRepresentation fromValue(String v) {
        return valueOf(v);
    }

}
