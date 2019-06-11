//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.21 � 03:24:05 PM CET 
//


package com.esa.bmap.external.model.cmr.granules;

import java.math.BigDecimal;
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
 * <p>Classe Java pour QAStats complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="QAStats">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="QAPercentMissingData" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="QAPercentOutOfBoundsData" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="QAPercentInterpolatedData" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="QAPercentCloudCover" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QAStats", propOrder = {
    "qaPercentMissingData",
    "qaPercentOutOfBoundsData",
    "qaPercentInterpolatedData",
    "qaPercentCloudCover"
})
public class QAStats {

    @XmlElement(name = "QAPercentMissingData")
    protected BigDecimal qaPercentMissingData;
    @XmlElement(name = "QAPercentOutOfBoundsData")
    protected BigDecimal qaPercentOutOfBoundsData;
    @XmlElement(name = "QAPercentInterpolatedData")
    protected BigDecimal qaPercentInterpolatedData;
    @XmlElement(name = "QAPercentCloudCover")
    protected BigDecimal qaPercentCloudCover;

    /**
     * Obtient la valeur de la propri�t� qaPercentMissingData.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQAPercentMissingData() {
        return qaPercentMissingData;
    }

    /**
     * D�finit la valeur de la propri�t� qaPercentMissingData.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQAPercentMissingData(BigDecimal value) {
        this.qaPercentMissingData = value;
    }

    /**
     * Obtient la valeur de la propri�t� qaPercentOutOfBoundsData.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQAPercentOutOfBoundsData() {
        return qaPercentOutOfBoundsData;
    }

    /**
     * D�finit la valeur de la propri�t� qaPercentOutOfBoundsData.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQAPercentOutOfBoundsData(BigDecimal value) {
        this.qaPercentOutOfBoundsData = value;
    }

    /**
     * Obtient la valeur de la propri�t� qaPercentInterpolatedData.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQAPercentInterpolatedData() {
        return qaPercentInterpolatedData;
    }

    /**
     * D�finit la valeur de la propri�t� qaPercentInterpolatedData.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQAPercentInterpolatedData(BigDecimal value) {
        this.qaPercentInterpolatedData = value;
    }

    /**
     * Obtient la valeur de la propri�t� qaPercentCloudCover.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getQAPercentCloudCover() {
        return qaPercentCloudCover;
    }

    /**
     * D�finit la valeur de la propri�t� qaPercentCloudCover.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setQAPercentCloudCover(BigDecimal value) {
        this.qaPercentCloudCover = value;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((qaPercentCloudCover == null) ? 0 : qaPercentCloudCover.hashCode());
		result = prime * result + ((qaPercentInterpolatedData == null) ? 0 : qaPercentInterpolatedData.hashCode());
		result = prime * result + ((qaPercentMissingData == null) ? 0 : qaPercentMissingData.hashCode());
		result = prime * result + ((qaPercentOutOfBoundsData == null) ? 0 : qaPercentOutOfBoundsData.hashCode());
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
		QAStats other = (QAStats) obj;
		if (qaPercentCloudCover == null) {
			if (other.qaPercentCloudCover != null)
				return false;
		} else if (!qaPercentCloudCover.equals(other.qaPercentCloudCover))
			return false;
		if (qaPercentInterpolatedData == null) {
			if (other.qaPercentInterpolatedData != null)
				return false;
		} else if (!qaPercentInterpolatedData.equals(other.qaPercentInterpolatedData))
			return false;
		if (qaPercentMissingData == null) {
			if (other.qaPercentMissingData != null)
				return false;
		} else if (!qaPercentMissingData.equals(other.qaPercentMissingData))
			return false;
		if (qaPercentOutOfBoundsData == null) {
			if (other.qaPercentOutOfBoundsData != null)
				return false;
		} else if (!qaPercentOutOfBoundsData.equals(other.qaPercentOutOfBoundsData))
			return false;
		return true;
	}

}
