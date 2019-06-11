package com.esa.bmap.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * This class represents a sensor mode.
 * 
 * @author QFAURE
 *
 */
@Entity
@Table(name = "sensor_mode")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Sensor {

	/**
	 * Unique number identifying the sensor.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	/**
	 * Name of the sensor mode.
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name = null;

	/**
	 * Description of the sensor mode.
	 */
	@Column(name = "description", nullable = true)
	private String description = null;

	private static final Logger LOG = LoggerFactory.getLogger(Sensor.class);

	/**
	 * Creates an empty sensor mode.
	 */
	public Sensor() {
		super();
	}

	/**
	 * Creates a sensor mode with the specified parameters.
	 * 
	 * @param name
	 * Name of the sensor mode.
	 * @param description
	 * Description of the sensor mode.
	 * @throws BmapException
	 * When the name is null.
	 */
	public Sensor(String name, String description) throws BmapException {
		super();
		if (name != null) {
			this.name = name;
			this.description = description;
		} else {
			LOG.error(Common.getMessageValue("sensormode.constructor.error"));
			throw new BmapException(Common.getMessageValue("sensormode.constructor.error"));
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
		Sensor sensor = (Sensor) o;
		return Objects.equals(this.id, sensor.id) && Objects.equals(this.name, sensor.name) && Objects.equals(this.description, sensor.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name, this.description);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SensorMode {\n");
		sb.append("    id: ").append(Utils.toIndentedString(this.id)).append("\n");
		sb.append("    name: ").append(Utils.toIndentedString(this.name)).append("\n");
		sb.append("    description: ").append(Utils.toIndentedString(this.description)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}
