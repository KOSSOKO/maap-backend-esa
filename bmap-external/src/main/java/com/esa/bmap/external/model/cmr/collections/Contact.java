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
 * This entity contains the basic
 *       characteristics for a person or an organization type of
 *       contact. These contacts may provide information about a
 *       Collection, Delivered Algorithm Package, PGE or Data
 *       Originator. System and user profile contact information is
 *       held elsewhere.
 * 
 * <p>Classe Java pour Contact complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="Contact">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Role">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="80"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="HoursOfService" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="1024"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Instructions" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="2048"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OrganizationName" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="200"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="OrganizationAddresses" type="{}ListOfAddresses" minOccurs="0"/>
 *         &lt;element name="OrganizationPhones" type="{}ListOfPhones" minOccurs="0"/>
 *         &lt;element name="OrganizationEmails" type="{}ListOfEmails" minOccurs="0"/>
 *         &lt;element name="ContactPersons" type="{}ListOfContactPersons" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contact", propOrder = {
    "role",
    "hoursOfService",
    "instructions",
    "organizationName",
    "organizationAddresses",
    "organizationPhones",
    "organizationEmails",
    "contactPersons"
})
public class Contact {

    @XmlElement(name = "Role", required = true)
    protected String role;
    @XmlElement(name = "HoursOfService")
    protected String hoursOfService;
    @XmlElement(name = "Instructions")
    protected String instructions;
    @XmlElement(name = "OrganizationName")
    protected String organizationName;
    @XmlElement(name = "OrganizationAddresses")
    protected ListOfAddresses organizationAddresses;
    @XmlElement(name = "OrganizationPhones")
    protected ListOfPhones organizationPhones;
    @XmlElement(name = "OrganizationEmails")
    protected ListOfEmails organizationEmails;
    @XmlElement(name = "ContactPersons")
    protected ListOfContactPersons contactPersons;

    /**
     * Obtient la valeur de la propri�t� role.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRole() {
        return role;
    }

    /**
     * D�finit la valeur de la propri�t� role.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRole(String value) {
        this.role = value;
    }

    /**
     * Obtient la valeur de la propri�t� hoursOfService.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoursOfService() {
        return hoursOfService;
    }

    /**
     * D�finit la valeur de la propri�t� hoursOfService.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoursOfService(String value) {
        this.hoursOfService = value;
    }

    /**
     * Obtient la valeur de la propri�t� instructions.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * D�finit la valeur de la propri�t� instructions.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstructions(String value) {
        this.instructions = value;
    }

    /**
     * Obtient la valeur de la propri�t� organizationName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationName() {
        return organizationName;
    }

    /**
     * D�finit la valeur de la propri�t� organizationName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationName(String value) {
        this.organizationName = value;
    }

    /**
     * Obtient la valeur de la propri�t� organizationAddresses.
     * 
     * @return
     *     possible object is
     *     {@link ListOfAddresses }
     *     
     */
    public ListOfAddresses getOrganizationAddresses() {
        return organizationAddresses;
    }

    /**
     * D�finit la valeur de la propri�t� organizationAddresses.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfAddresses }
     *     
     */
    public void setOrganizationAddresses(ListOfAddresses value) {
        this.organizationAddresses = value;
    }

    /**
     * Obtient la valeur de la propri�t� organizationPhones.
     * 
     * @return
     *     possible object is
     *     {@link ListOfPhones }
     *     
     */
    public ListOfPhones getOrganizationPhones() {
        return organizationPhones;
    }

    /**
     * D�finit la valeur de la propri�t� organizationPhones.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfPhones }
     *     
     */
    public void setOrganizationPhones(ListOfPhones value) {
        this.organizationPhones = value;
    }

    /**
     * Obtient la valeur de la propri�t� organizationEmails.
     * 
     * @return
     *     possible object is
     *     {@link ListOfEmails }
     *     
     */
    public ListOfEmails getOrganizationEmails() {
        return organizationEmails;
    }

    /**
     * D�finit la valeur de la propri�t� organizationEmails.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfEmails }
     *     
     */
    public void setOrganizationEmails(ListOfEmails value) {
        this.organizationEmails = value;
    }

    /**
     * Obtient la valeur de la propri�t� contactPersons.
     * 
     * @return
     *     possible object is
     *     {@link ListOfContactPersons }
     *     
     */
    public ListOfContactPersons getContactPersons() {
        return contactPersons;
    }

    /**
     * D�finit la valeur de la propri�t� contactPersons.
     * 
     * @param value
     *     allowed object is
     *     {@link ListOfContactPersons }
     *     
     */
    public void setContactPersons(ListOfContactPersons value) {
        this.contactPersons = value;
    }

}
