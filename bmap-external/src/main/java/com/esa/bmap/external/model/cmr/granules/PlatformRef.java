//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * A reference to a platform in the parent
 *       collection that is associated with the acquisition of the
 *       granule. The platform must exist in the parent collection.
 *       For example, Platform types may include (but are not limited
 *       to): ADEOS-II, AEM-2, AM-1, Aqua, Aura, BALLOONS, BUOYS,
 *       C-130, DEM, DMSP-F1, ERS-1, GOES-10, LANDSAT-1, METEOSAT-2,
 *       NIMBUS-2, NOAA-6, TERRA, TRMM, etc.
 * 
 * <p>Classe Java pour PlatformRef complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PlatformRef">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ShortName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Instruments" type="{}ListOfInstrumentRefs" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PlatformRef", propOrder = {
    "shortName",
    "instruments"
})
public class PlatformRef {

    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "Instruments")
    protected ListOfInstrumentRefs instruments;

    /**
     * Obtient la valeur de la propri�t� shortName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * D�finit la valeur de la propri�t� shortName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShortName(String value) {
        this.shortName = value;
    }

    /**
     * Obtient la valeur de la propri�t� instruments.
     * 
     * @return
     *     possible object is
     *     {@link ListOfInstrumentRefs }
     *     
     */
    public ListOfInstrumentRefs getInstruments() {
        return instruments;
    }

    /**
     * D�finit la valeur de la propri�t� instruments.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfInstrumentRefs }
     *     
     */
    public void setInstruments(ListOfInstrumentRefs value) {
        this.instruments = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((instruments == null) ? 0 : instruments.hashCode());
		result = prime * result + ((shortName == null) ? 0 : shortName.hashCode());
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
		PlatformRef other = (PlatformRef) obj;
		if (instruments == null) {
			if (other.instruments != null)
				return false;
		} else if (!instruments.equals(other.instruments))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PlatformRef [shortName=" + shortName + ", instruments=" + instruments + "]";
	}

}
