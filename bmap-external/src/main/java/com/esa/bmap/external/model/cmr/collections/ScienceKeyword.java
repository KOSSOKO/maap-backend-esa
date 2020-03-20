//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source. 
// G�n�r� le : 2019.01.25 � 02:55:58 PM CET 
//


package com.esa.bmap.external.model.cmr.collections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * This entity holds cross reference between
 *       collections and science keywords.
 * 
 * <p>Classe Java pour ScienceKeyword complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="ScienceKeyword">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CategoryKeyword">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="500"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TopicKeyword">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="500"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="TermKeyword">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="500"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="VariableLevel1Keyword" type="{}VariableLevel1Keyword" minOccurs="0"/>
 *         &lt;element name="DetailedVariableKeyword" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="80"/>
 *               &lt;minLength value="1"/>
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
@XmlType(name = "ScienceKeyword", propOrder = {
    "categoryKeyword",
    "topicKeyword",
    "termKeyword",
    "variableLevel1Keyword",
    "detailedVariableKeyword"
})
public class ScienceKeyword {

    @XmlElement(name = "CategoryKeyword", required = true)
    protected String categoryKeyword;
    @XmlElement(name = "TopicKeyword", required = true)
    protected String topicKeyword;
    @XmlElement(name = "TermKeyword", required = true)
    protected String termKeyword;
    @XmlElement(name = "VariableLevel1Keyword")
    protected VariableLevel1Keyword variableLevel1Keyword;
    @XmlElement(name = "DetailedVariableKeyword")
    protected String detailedVariableKeyword;

    /**
     * Obtient la valeur de la propri�t� categoryKeyword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryKeyword() {
        return categoryKeyword;
    }

    /**
     * D�finit la valeur de la propri�t� categoryKeyword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryKeyword(String value) {
        this.categoryKeyword = value;
    }

    /**
     * Obtient la valeur de la propri�t� topicKeyword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTopicKeyword() {
        return topicKeyword;
    }

    /**
     * D�finit la valeur de la propri�t� topicKeyword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTopicKeyword(String value) {
        this.topicKeyword = value;
    }

    /**
     * Obtient la valeur de la propri�t� termKeyword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermKeyword() {
        return termKeyword;
    }

    /**
     * D�finit la valeur de la propri�t� termKeyword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermKeyword(String value) {
        this.termKeyword = value;
    }

    /**
     * Obtient la valeur de la propri�t� variableLevel1Keyword.
     * 
     * @return
     *     possible object is
     *     {@link VariableLevel1Keyword }
     *     
     */
    public VariableLevel1Keyword getVariableLevel1Keyword() {
        return variableLevel1Keyword;
    }

    /**
     * D�finit la valeur de la propri�t� variableLevel1Keyword.
     * 
     * @param value
     *     allowed object is
     *     {@link VariableLevel1Keyword }
     *     
     */
    public void setVariableLevel1Keyword(VariableLevel1Keyword value) {
        this.variableLevel1Keyword = value;
    }

    /**
     * Obtient la valeur de la propri�t� detailedVariableKeyword.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailedVariableKeyword() {
        return detailedVariableKeyword;
    }

    /**
     * D�finit la valeur de la propri�t� detailedVariableKeyword.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailedVariableKeyword(String value) {
        this.detailedVariableKeyword = value;
    }

}
