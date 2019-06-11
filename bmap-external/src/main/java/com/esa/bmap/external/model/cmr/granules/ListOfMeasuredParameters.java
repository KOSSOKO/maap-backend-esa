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
import javax.xml.bind.annotation.XmlType;


/**
 * This entity contains the name of the
 *       geophysical parameter expressed in the data as well as
 *       associated quality flags and quality status. The quality
 *       status contains measures of quality for the granule. The
 *       parameters used to set these measures are not preset and will
 *       be determined by the data producer. Each set of measures can
 *       occur many times either for the granule as a whole or for
 *       individual parameters. The quality flags contain the science,
 *       operational and automatic quality flags which indicate the
 *       overall quality assurance levels of specific parameter values
 *       within a granule.
 * 
 * <p>Classe Java pour ListOfMeasuredParameters complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ListOfMeasuredParameters">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MeasuredParameter" type="{}MeasuredParameter" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListOfMeasuredParameters", propOrder = {
    "measuredParameter"
})
public class ListOfMeasuredParameters {

    @XmlElement(name = "MeasuredParameter")
    protected List<MeasuredParameter> measuredParameter;

    /**
     * Gets the value of the measuredParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the measuredParameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMeasuredParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MeasuredParameter }
     * 
     * 
     */
    public List<MeasuredParameter> getMeasuredParameter() {
        if (measuredParameter == null) {
            measuredParameter = new ArrayList<MeasuredParameter>();
        }
        return this.measuredParameter;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((measuredParameter == null) ? 0 : measuredParameter.hashCode());
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
		ListOfMeasuredParameters other = (ListOfMeasuredParameters) obj;
		if (measuredParameter == null) {
			if (other.measuredParameter != null)
				return false;
		} else if (!measuredParameter.equals(other.measuredParameter))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListOfMeasuredParameters [measuredParameter=" + measuredParameter + "]";
	}

}
