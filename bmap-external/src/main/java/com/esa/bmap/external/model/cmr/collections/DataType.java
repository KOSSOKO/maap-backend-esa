//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2019.01.25 à 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour DataType.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="DataType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="STRING"/>
 *     &lt;enumeration value="FLOAT"/>
 *     &lt;enumeration value="INT"/>
 *     &lt;enumeration value="BOOLEAN"/>
 *     &lt;enumeration value="DATE"/>
 *     &lt;enumeration value="TIME"/>
 *     &lt;enumeration value="DATETIME"/>
 *     &lt;enumeration value="DATE_STRING"/>
 *     &lt;enumeration value="TIME_STRING"/>
 *     &lt;enumeration value="DATETIME_STRING"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DataType")
@XmlEnum
public enum DataType {


    /**
     * The parameter value type is XML
     *           primitive string type. See
     *           
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;a xmlns:xs="http://www.w3.org/2001/XMLSchema" href="http://www.w3.org/TR/xmlschema-2/#string"&gt;the
     *           XML Schema specification&lt;/a&gt;
     * </pre>
     * for more
     *           information.
     * 
     */
    STRING,

    /**
     * The parameter value type is XML
     *           primitive float type, without support for positive or
     *           negative infinity, or not-a-number. See
     *           
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;a xmlns:xs="http://www.w3.org/2001/XMLSchema" href="http://www.w3.org/TR/xmlschema-2/#float"&gt;the XML
     *           Schema specification&lt;/a&gt;
     * </pre>
     * for more
     *           information.
     * 
     */
    FLOAT,

    /**
     * The parameter value type is XML derived
     *           int type. See
     *           
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;a xmlns:xs="http://www.w3.org/2001/XMLSchema" href="http://www.w3.org/TR/xmlschema-2/#int"&gt;the XML
     *           Schema specification&lt;/a&gt;
     * </pre>
     * for more
     *           information.
     * 
     */
    INT,

    /**
     * The parameter value type is XML
     *           primitive boolean type. See
     *           
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;a xmlns:xs="http://www.w3.org/2001/XMLSchema" href="http://www.w3.org/TR/xmlschema-2/#boolean"&gt;the
     *           XML Schema specification&lt;/a&gt;
     * </pre>
     * for more
     *           information.
     * 
     */
    BOOLEAN,

    /**
     * The parameter value type is XML
     *           primitive date type but with GMT timezone, meaning any
     *           timezoned or non-timezoned value will be treated as
     *           having GMT timezone. See
     *           
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;a xmlns:xs="http://www.w3.org/2001/XMLSchema" href="http://www.w3.org/TR/xmlschema-2/#date"&gt;the XML
     *           Schema specification&lt;/a&gt;
     * </pre>
     * for more
     *           information.
     * 
     */
    DATE,

    /**
     * The parameter value type is XML
     *           primitive time type but with GMT timezone, meaning any
     *           timezoned or non-timezoned value will be treated as
     *           having GMT timezone. See
     *           
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;a xmlns:xs="http://www.w3.org/2001/XMLSchema" href="http://www.w3.org/TR/xmlschema-2/#time"&gt;the XML
     *           Schema specification&lt;/a&gt;
     * </pre>
     * for more
     *           information.
     * 
     */
    TIME,

    /**
     * The parameter value type is XML
     *           primitive dateTime type. See
     *           
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;a xmlns:xs="http://www.w3.org/2001/XMLSchema" href="http://www.w3.org/TR/xmlschema-2/#dateTime"&gt;the
     *           XML Schema specification&lt;/a&gt;
     * </pre>
     * for more
     *           information.
     * 
     */
    DATETIME,

    /**
     * The parameter value type is a string
     *           that follows a provider-defined date
     *           format.
     * 
     */
    DATE_STRING,

    /**
     * The parameter value type is a string
     *           that follows a provider-defined time
     *           format.
     * 
     */
    TIME_STRING,

    /**
     * The parameter value type is a string
     *           that follows a provider-defined dateTime
     *           format.
     * 
     */
    DATETIME_STRING;

    public String value() {
        return name();
    }

    public static DataType fromValue(String v) {
        return valueOf(v);
    }

}
