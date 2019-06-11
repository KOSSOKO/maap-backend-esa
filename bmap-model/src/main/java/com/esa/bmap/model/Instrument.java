package com.esa.bmap.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Instrument
 * 
 * @author QFAURE
 *
 */
@Entity
@Table(name = "instrument")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Instrument {

	// TODO QFA: add frequency attribute

	/**
	 * Unique number identifying the instrument.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	/**
	 * Name of the instrument (e.g. P-band).
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name = null;

	/**
	 * Description of the instrument.
	 */
	@Column(name = "description", nullable = true)
	private String description = null;
	
	
	@OneToMany
	private List<Sensor> sensors = null;



	private static final Logger LOG = LoggerFactory.getLogger(Instrument.class);

	/**
	 * Creates an empty instrument.
	 */
	public Instrument() {
		super();
	}

	/**
	 * Creates an instrument with the specified parameters.
	 * 
	 * @param name
	 * Name of the instrument (e.g. P-band).
	 * @param description
	 * Description of the instrument.
	 * @throws BmapException
	 * When the name is null.
	 */
	public Instrument(String name, String description) throws BmapException {
		super();
		if (name != null) {
			this.name = name;
			this.description = description;
		} else {
			LOG.error(Common.getMessageValue("instrument.constructor.error"));
			throw new BmapException(Common.getMessageValue("instrument.constructor.error"));
		}
	}

	/**
	 * @return The id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 * The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return The name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 * The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return The description.
	 */
	public String getDescription() {
		return description;
	}
	
	
	/**
	 * Getter of the sensors
	 * @return
	 */
	public List<Sensor> getSensors() {
		return sensors;
	}

	/**
	 * Setter of the sensors
	 * @param sensors
	 */
	public void setSensors(List<Sensor> sensors) {
		this.sensors = sensors;
	}

	/**
	 * @param description
	 * The description to set.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Instrument instrument = (Instrument) o;
		return Objects.equals(this.id, instrument.id) && Objects.equals(this.name, instrument.name) && Objects.equals(this.description, instrument.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.description);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Instrument {\n");
		sb.append("    id: ").append(Utils.toIndentedString(this.id)).append("\n");
		sb.append("    name: ").append(Utils.toIndentedString(this.name)).append("\n");
		sb.append("    description: ").append(Utils.toIndentedString(this.description)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
