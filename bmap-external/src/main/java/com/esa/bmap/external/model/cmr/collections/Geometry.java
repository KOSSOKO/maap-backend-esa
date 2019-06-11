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
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour Geometry complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Geometry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CoordinateSystem" type="{}CoordinateSystem"/>
 *         &lt;choice maxOccurs="unbounded">
 *           &lt;element name="Point" type="{}Point"/>
 *           &lt;element name="BoundingRectangle" type="{}BoundingRectangle"/>
 *           &lt;element name="GPolygon" type="{}GPolygon"/>
 *           &lt;element name="Line" type="{}Line"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Geometry", propOrder = {
    "coordinateSystem",
    "pointOrBoundingRectangleOrGPolygon"
})
public class Geometry {

    @XmlElement(name = "CoordinateSystem", required = true)
    @XmlSchemaType(name = "string")
    protected CoordinateSystem coordinateSystem;
    @XmlElements({
        @XmlElement(name = "Point", type = Point.class),
        @XmlElement(name = "BoundingRectangle", type = BoundingRectangle.class),
        @XmlElement(name = "GPolygon", type = GPolygon.class),
        @XmlElement(name = "Line", type = Line.class)
    })
    protected List<Object> pointOrBoundingRectangleOrGPolygon;

    /**
     * Obtient la valeur de la propriété coordinateSystem.
     * 
     * @return
     *     possible object is
     *     {@link CoordinateSystem }
     *     
     */
    public CoordinateSystem getCoordinateSystem() {
        return coordinateSystem;
    }

    /**
     * Définit la valeur de la propriété coordinateSystem.
     * 
     * @param value
     *     allowed object is
     *     {@link CoordinateSystem }
     *     
     */
    public void setCoordinateSystem(CoordinateSystem value) {
        this.coordinateSystem = value;
    }

    /**
     * Gets the value of the pointOrBoundingRectangleOrGPolygon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pointOrBoundingRectangleOrGPolygon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPointOrBoundingRectangleOrGPolygon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Point }
     * {@link BoundingRectangle }
     * {@link GPolygon }
     * {@link Line }
     * 
     * 
     */
    public List<Object> getPointOrBoundingRectangleOrGPolygon() {
        if (pointOrBoundingRectangleOrGPolygon == null) {
            pointOrBoundingRectangleOrGPolygon = new ArrayList<Object>();
        }
        return this.pointOrBoundingRectangleOrGPolygon;
    }

}
