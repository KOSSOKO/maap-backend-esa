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
 * <p>Classe Java pour MeasuredParameter complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MeasuredParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ParameterName">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="250"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="QAStats" type="{}QAStats" minOccurs="0"/>
 *         &lt;element name="QAFlags" type="{}QAFlags" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MeasuredParameter", propOrder = {
    "parameterName",
    "qaStats",
    "qaFlags"
})
public class MeasuredParameter {

    @XmlElement(name = "ParameterName", required = true)
    protected String parameterName;
    @XmlElement(name = "QAStats")
    protected QAStats qaStats;
    @XmlElement(name = "QAFlags")
    protected QAFlags qaFlags;

    /**
     * Obtient la valeur de la propri�t� parameterName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * D�finit la valeur de la propri�t� parameterName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterName(String value) {
        this.parameterName = value;
    }

    /**
     * Obtient la valeur de la propri�t� qaStats.
     * 
     * @return
     *     possible object is
     *     {@link QAStats }
     *     
     */
    public QAStats getQAStats() {
        return qaStats;
    }

    /**
     * D�finit la valeur de la propri�t� qaStats.
     * 
     * @param value
     *     allowed object is
     *     {@link QAStats }
     *     
     */
    public void setQAStats(QAStats value) {
        this.qaStats = value;
    }

    /**
     * Obtient la valeur de la propri�t� qaFlags.
     * 
     * @return
     *     possible object is
     *     {@link QAFlags }
     *     
     */
    public QAFlags getQAFlags() {
        return qaFlags;
    }

    /**
     * D�finit la valeur de la propri�t� qaFlags.
     * 
     * @param value
     *     allowed object is
     *     {@link QAFlags }
     *     
     */
    public void setQAFlags(QAFlags value) {
        this.qaFlags = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((parameterName == null) ? 0 : parameterName.hashCode());
		result = prime * result + ((qaFlags == null) ? 0 : qaFlags.hashCode());
		result = prime * result + ((qaStats == null) ? 0 : qaStats.hashCode());
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
		MeasuredParameter other = (MeasuredParameter) obj;
		if (parameterName == null) {
			if (other.parameterName != null)
				return false;
		} else if (!parameterName.equals(other.parameterName))
			return false;
		if (qaFlags == null) {
			if (other.qaFlags != null)
				return false;
		} else if (!qaFlags.equals(other.qaFlags))
			return false;
		if (qaStats == null) {
			if (other.qaStats != null)
				return false;
		} else if (!qaStats.equals(other.qaStats))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MeasuredParameter [parameterName=" + parameterName + ", qaStats=" + qaStats + ", qaFlags=" + qaFlags
				+ "]";
	}

}
