package com.esa.bmap.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author edupin
 *
 */
@Entity
@Table(name = "platform")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Platform {
	/**
	 * Ground Campaign platform value
	 */
	public static final String GROUND_CAMPAIGN_PLATFORM = "GroundCampaign platform";
	/**
	 * Unique number identifying the platform.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	/**
	 * Description of the platform (e.g. TODO TO COMPLETE).
	 */
	@Column(name = "description", nullable = false, unique = false)
	private String description = null;

	/**
	 * Name of the platform (e.g. TODO TO COMPLETE).
	 */
	@Column(name = "name", nullable = false, unique = false)
	private String name = null;

	/**
	 * List of Instruments of the platform (e.g. TODO TO COMPLETE).
	 */
	@OneToMany
	private List<Instrument> listInstrument = null;

	
	/**
	 * Creates an empty platform
	 */
	public Platform() {
		super();
	}

	/**
	 * @param description
	 * @param name
	 * @param listInstrument
	 */
	public Platform( String description, String name, List<Instrument> listInstrument) {
		super();
		this.description = description;
		this.name = name;
		this.listInstrument = listInstrument;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the listInstrument
	 */
	public List<Instrument> getListInstrument() {
		if(listInstrument==null){
			listInstrument = new ArrayList<Instrument>();
		}
		return listInstrument;
	}

	/**
	 * @param listInstrument the listInstrument to set
	 */
	public void setListInstrument(List<Instrument> listInstrument) {
		this.listInstrument = listInstrument;
	}

}
