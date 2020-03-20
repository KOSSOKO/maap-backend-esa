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
 * A reference to the device in the parent
 *       collection that was used to measure or record data, including
 *       direct human observation. In cases where instruments have a
 *       single sensor or the instrument and sensor are used
 *       synonymously (e.g. AVHRR) the both Instrument and Sensor
 *       should be recorded. The Sensor information is represented by
 *       child entities. The instrument reference may contain granule
 *       specific characteristics and operation modes. These
 *       characteristics and modes are not checked against the
 *       referenced instrument.
 * 
 * <p>Classe Java pour InstrumentRef complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="InstrumentRef">
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
 *         &lt;element name="Characteristics" type="{}ListOfCharacteristicRefs" minOccurs="0"/>
 *         &lt;element name="Sensors" type="{}ListOfSensorRefs" minOccurs="0"/>
 *         &lt;element name="OperationModes" type="{}ListOfOperationModes" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InstrumentRef", propOrder = {
    "shortName",
    "characteristics",
    "sensors",
    "operationModes"
})
public class InstrumentRef {

    @XmlElement(name = "ShortName", required = true)
    protected String shortName;
    @XmlElement(name = "Characteristics")
    protected ListOfCharacteristicRefs characteristics;
    @XmlElement(name = "Sensors")
    protected ListOfSensorRefs sensors;
    @XmlElement(name = "OperationModes")
    protected ListOfOperationModes operationModes;

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
     * Obtient la valeur de la propri�t� characteristics.
     * 
     * @return
     *     possible object is
     *     {@link ListOfCharacteristicRefs }
     *     
     */
    public ListOfCharacteristicRefs getCharacteristics() {
        return characteristics;
    }

    /**
     * D�finit la valeur de la propri�t� characteristics.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfCharacteristicRefs }
     *     
     */
    public void setCharacteristics(ListOfCharacteristicRefs value) {
        this.characteristics = value;
    }

    /**
     * Obtient la valeur de la propri�t� sensors.
     * 
     * @return
     *     possible object is
     *     {@link ListOfSensorRefs }
     *     
     */
    public ListOfSensorRefs getSensors() {
        return sensors;
    }

    /**
     * D�finit la valeur de la propri�t� sensors.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfSensorRefs }
     *     
     */
    public void setSensors(ListOfSensorRefs value) {
        this.sensors = value;
    }

    /**
     * Obtient la valeur de la propri�t� operationModes.
     * 
     * @return
     *     possible object is
     *     {@link ListOfOperationModes }
     *     
     */
    public ListOfOperationModes getOperationModes() {
        return operationModes;
    }

    /**
     * D�finit la valeur de la propri�t� operationModes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfOperationModes }
     *     
     */
    public void setOperationModes(ListOfOperationModes value) {
        this.operationModes = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((characteristics == null) ? 0 : characteristics.hashCode());
		result = prime * result + ((operationModes == null) ? 0 : operationModes.hashCode());
		result = prime * result + ((sensors == null) ? 0 : sensors.hashCode());
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
		InstrumentRef other = (InstrumentRef) obj;
		if (characteristics == null) {
			if (other.characteristics != null)
				return false;
		} else if (!characteristics.equals(other.characteristics))
			return false;
		if (operationModes == null) {
			if (other.operationModes != null)
				return false;
		} else if (!operationModes.equals(other.operationModes))
			return false;
		if (sensors == null) {
			if (other.sensors != null)
				return false;
		} else if (!sensors.equals(other.sensors))
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
		return "InstrumentRef [shortName=" + shortName + ", characteristics=" + characteristics + ", sensors=" + sensors
				+ ", operationModes=" + operationModes + "]";
	}

}
