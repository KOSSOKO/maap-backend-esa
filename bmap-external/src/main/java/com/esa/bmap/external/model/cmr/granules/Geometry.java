//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity holds the geometry representing
 *       the spatial coverage information of a
 *       granule.
 * 
 * <p>Classe Java pour Geometry complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Geometry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element name="Point" type="{}Point"/>
 *         &lt;element name="BoundingRectangle" type="{}BoundingRectangle"/>
 *         &lt;element name="GPolygon" type="{}GPolygon"/>
 *         &lt;element name="Line" type="{}Line"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Geometry", propOrder = {
    "pointOrBoundingRectangleOrGPolygon"
})
public class Geometry {

    @XmlElements({
        @XmlElement(name = "Point", type = Point.class),
        @XmlElement(name = "BoundingRectangle", type = BoundingRectangle.class),
        @XmlElement(name = "GPolygon", type = GPolygon.class),
        @XmlElement(name = "Line", type = Line.class)
    })
    protected List<Object> pointOrBoundingRectangleOrGPolygon;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((pointOrBoundingRectangleOrGPolygon == null) ? 0 : pointOrBoundingRectangleOrGPolygon.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Geometry other = (Geometry) obj;
		if (pointOrBoundingRectangleOrGPolygon == null) {
			if (other.pointOrBoundingRectangleOrGPolygon != null)
				return false;
		} else if (!pointOrBoundingRectangleOrGPolygon.equals(other.pointOrBoundingRectangleOrGPolygon))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Geometry [pointOrBoundingRectangleOrGPolygon=" + pointOrBoundingRectangleOrGPolygon + "]";
	}

}
