package com.esa.bmap.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * BmaapUser
 */
@Entity
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class BmaapUser {

	//unique identifier for the class project
	@Id
	private Long id = null;
	
	//Name of the user
	@Column(name = "name", unique = false, nullable = true)
	private String name = null;
	
	/**
	 * Default constructor
	 */
	public BmaapUser() {
		super();
	}

	/**
	 * Default constructor
	 * @param systemRessource
	 */
	public BmaapUser(Long id, String name) {
		super();
		//this.systemResource = systemResource;
		this.id = id;
		this.name = name;
	}


	/**
	 * Unique identifier representind a specific user.
	 * @return userId
	 **/
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		BmaapUser user = (BmaapUser) o;
		return Objects.equals(this.id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class BmaapUser {\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}

}

