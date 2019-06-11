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
 * <p>Classe Java pour QAFlags complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="QAFlags">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AutomaticQualityFlag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="AutomaticQualityFlagExplanation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OperationalQualityFlag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OperationalQualityFlagExplanation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ScienceQualityFlag" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="ScienceQualityFlagExplanation" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QAFlags", propOrder = {
    "automaticQualityFlag",
    "automaticQualityFlagExplanation",
    "operationalQualityFlag",
    "operationalQualityFlagExplanation",
    "scienceQualityFlag",
    "scienceQualityFlagExplanation"
})
public class QAFlags {

    @XmlElement(name = "AutomaticQualityFlag")
    protected String automaticQualityFlag;
    @XmlElement(name = "AutomaticQualityFlagExplanation")
    protected String automaticQualityFlagExplanation;
    @XmlElement(name = "OperationalQualityFlag")
    protected String operationalQualityFlag;
    @XmlElement(name = "OperationalQualityFlagExplanation")
    protected String operationalQualityFlagExplanation;
    @XmlElement(name = "ScienceQualityFlag")
    protected String scienceQualityFlag;
    @XmlElement(name = "ScienceQualityFlagExplanation")
    protected String scienceQualityFlagExplanation;

    /**
     * Obtient la valeur de la propri�t� automaticQualityFlag.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutomaticQualityFlag() {
        return automaticQualityFlag;
    }

    /**
     * D�finit la valeur de la propri�t� automaticQualityFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutomaticQualityFlag(String value) {
        this.automaticQualityFlag = value;
    }

    /**
     * Obtient la valeur de la propri�t� automaticQualityFlagExplanation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutomaticQualityFlagExplanation() {
        return automaticQualityFlagExplanation;
    }

    /**
     * D�finit la valeur de la propri�t� automaticQualityFlagExplanation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutomaticQualityFlagExplanation(String value) {
        this.automaticQualityFlagExplanation = value;
    }

    /**
     * Obtient la valeur de la propri�t� operationalQualityFlag.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationalQualityFlag() {
        return operationalQualityFlag;
    }

    /**
     * D�finit la valeur de la propri�t� operationalQualityFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationalQualityFlag(String value) {
        this.operationalQualityFlag = value;
    }

    /**
     * Obtient la valeur de la propri�t� operationalQualityFlagExplanation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationalQualityFlagExplanation() {
        return operationalQualityFlagExplanation;
    }

    /**
     * D�finit la valeur de la propri�t� operationalQualityFlagExplanation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationalQualityFlagExplanation(String value) {
        this.operationalQualityFlagExplanation = value;
    }

    /**
     * Obtient la valeur de la propri�t� scienceQualityFlag.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScienceQualityFlag() {
        return scienceQualityFlag;
    }

    /**
     * D�finit la valeur de la propri�t� scienceQualityFlag.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScienceQualityFlag(String value) {
        this.scienceQualityFlag = value;
    }

    /**
     * Obtient la valeur de la propri�t� scienceQualityFlagExplanation.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScienceQualityFlagExplanation() {
        return scienceQualityFlagExplanation;
    }

    /**
     * D�finit la valeur de la propri�t� scienceQualityFlagExplanation.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScienceQualityFlagExplanation(String value) {
        this.scienceQualityFlagExplanation = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((automaticQualityFlag == null) ? 0 : automaticQualityFlag.hashCode());
		result = prime * result
				+ ((automaticQualityFlagExplanation == null) ? 0 : automaticQualityFlagExplanation.hashCode());
		result = prime * result + ((operationalQualityFlag == null) ? 0 : operationalQualityFlag.hashCode());
		result = prime * result
				+ ((operationalQualityFlagExplanation == null) ? 0 : operationalQualityFlagExplanation.hashCode());
		result = prime * result + ((scienceQualityFlag == null) ? 0 : scienceQualityFlag.hashCode());
		result = prime * result
				+ ((scienceQualityFlagExplanation == null) ? 0 : scienceQualityFlagExplanation.hashCode());
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
		QAFlags other = (QAFlags) obj;
		if (automaticQualityFlag == null) {
			if (other.automaticQualityFlag != null)
				return false;
		} else if (!automaticQualityFlag.equals(other.automaticQualityFlag))
			return false;
		if (automaticQualityFlagExplanation == null) {
			if (other.automaticQualityFlagExplanation != null)
				return false;
		} else if (!automaticQualityFlagExplanation.equals(other.automaticQualityFlagExplanation))
			return false;
		if (operationalQualityFlag == null) {
			if (other.operationalQualityFlag != null)
				return false;
		} else if (!operationalQualityFlag.equals(other.operationalQualityFlag))
			return false;
		if (operationalQualityFlagExplanation == null) {
			if (other.operationalQualityFlagExplanation != null)
				return false;
		} else if (!operationalQualityFlagExplanation.equals(other.operationalQualityFlagExplanation))
			return false;
		if (scienceQualityFlag == null) {
			if (other.scienceQualityFlag != null)
				return false;
		} else if (!scienceQualityFlag.equals(other.scienceQualityFlag))
			return false;
		if (scienceQualityFlagExplanation == null) {
			if (other.scienceQualityFlagExplanation != null)
				return false;
		} else if (!scienceQualityFlagExplanation.equals(other.scienceQualityFlagExplanation))
			return false;
		return true;
	}

}
