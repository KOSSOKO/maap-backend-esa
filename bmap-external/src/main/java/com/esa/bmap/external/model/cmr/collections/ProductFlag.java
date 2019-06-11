//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour ProductFlag.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="ProductFlag">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DATA_PRODUCT_FILE"/>
 *     &lt;enumeration value="INSTRUMENT_ANCILLARY_FILE"/>
 *     &lt;enumeration value="SYSTEM/SPACECRAFT_FILE"/>
 *     &lt;enumeration value="EXTERNAL_DATA"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProductFlag")
@XmlEnum
public enum ProductFlag {

    DATA_PRODUCT_FILE("DATA_PRODUCT_FILE"),
    INSTRUMENT_ANCILLARY_FILE("INSTRUMENT_ANCILLARY_FILE"),
    @XmlEnumValue("SYSTEM/SPACECRAFT_FILE")
    SYSTEM_SPACECRAFT_FILE("SYSTEM/SPACECRAFT_FILE"),
    EXTERNAL_DATA("EXTERNAL_DATA");
    private final String value;

    ProductFlag(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ProductFlag fromValue(String v) {
        for (ProductFlag c: ProductFlag.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
