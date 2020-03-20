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
 * This class represents an sub-region.
 * 
 * @author QFAURE
 *
 */
@Entity
@Table(name = "sub_region")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class SubRegion {

	/**
	 * Unique number identifying the sub-region.
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	/**
	 * Name of the sub-region (e.g. Paracou).
	 */
	@Column(name = "name", nullable = false, unique = true)
	private String name = null;

	private static final Logger LOG = LoggerFactory.getLogger(SubRegion.class);

	/**
	 * Creates an empty sub-region.
	 */
	public SubRegion() {
		super();
	}

	/**
	 * Creates a sub-region with the specified parameters.
	 * 
	 * @param name
	 * Name of the sub-region (e.g. Paracou).
	 * @throws BmapException
	 * When the name is null.
	 */
	public SubRegion(String name) throws BmapException {
		super();
		if (name != null) {
			this.name = name;
		} else {
			LOG.error(Common.getMessageValue("subregion.constructor.error"));
			throw new BmapException(Common.getMessageValue("subregion.constructor.error"));
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SubRegion subRegion = (SubRegion) o;
		return Objects.equals(this.id, subRegion.id) && Objects.equals(this.name, subRegion.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SubRegion {\n");
		sb.append("    id: ").append(Utils.toIndentedString(this.id)).append("\n");
		sb.append("    name: ").append(Utils.toIndentedString(this.name)).append("\n");
		sb.append("}");
		return sb.toString();
	}
}