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
 * <p>Classe Java pour GranuleUpdateTargetField.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="GranuleUpdateTargetField">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="URL"/>
 *     &lt;enumeration value="VALUE"/>
 *     &lt;enumeration value="NAME"/>
 *     &lt;enumeration value="MIME_TYPE"/>
 *     &lt;enumeration value="DESCRIPTION"/>
 *     &lt;enumeration value="TYPE"/>
 *     &lt;enumeration value="AUTO_QUALITY_FLAG"/>
 *     &lt;enumeration value="AUTO_QUALITY_FLAG_EX"/>
 *     &lt;enumeration value="OP_QUALITY_FLAG"/>
 *     &lt;enumeration value="OP_QUALITY_FLAG_EX"/>
 *     &lt;enumeration value="SCIENCE_QUALITY_FLAG"/>
 *     &lt;enumeration value="SCIENCE_QUALITY_FLAG_EX"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "GranuleUpdateTargetField")
@XmlEnum
public enum GranuleUpdateTargetField {

    URL,
    VALUE,
    NAME,
    MIME_TYPE,
    DESCRIPTION,
    TYPE,
    AUTO_QUALITY_FLAG,
    AUTO_QUALITY_FLAG_EX,
    OP_QUALITY_FLAG,
    OP_QUALITY_FLAG_EX,
    SCIENCE_QUALITY_FLAG,
    SCIENCE_QUALITY_FLAG_EX;

    public String value() {
        return name();
    }

    public static GranuleUpdateTargetField fromValue(String v) {
        return valueOf(v);
    }

}
