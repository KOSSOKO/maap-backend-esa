package com.esa.bmap.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Executable
 */
@Entity
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
public class Executable  {

	//Unique identifier representing a specific Executable.	
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	//Name of the Executable.
	@Column(unique = true, nullable = false)
	private String name = null;

	//Url of the Executable
	@Column(unique = true, nullable = false)
	private String url = null;

	//Algorithm linked to this executable
//	@OneToOne(mappedBy="executable")
//	private Algorithm algorithm;
	
	
	public Executable() {
		super();
	}
	
	
	/**
	 * constructor with parameters  
	 * @param name
	 * @param url
	 */
	public Executable(String name, String url) {
		this.name = name;	
		this.url = url;
	}
	/**
	 *  Get Unique identifier representing a specific Executable.
	 * @return executableId
	 **/
	public Long getId() {
		return id;
	}

	/**
	 * Set Unique identifier representing a specific Executable.
	 * @param executableId
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * get name of the Executable.
	 * @return name
	 **/
	public String getName() {
		return name;
	}

	/**
	 * set name of the Executable.
	 * @param name
	 */
	public void setName(String executableName) {
		this.name = executableName;
	}

	/**
	 * url of the Executable
	 * @param url
	 * @return
	 */
	public Executable url(String url) {
		this.url = url;
		return this;
	}

	/**
	 * get url of the Executable
	 * @return url
	 **/
	public String getUrl() {
		return url;
	}

	/**
	 * set url of the Executable
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

//	/**
//	 * @return the algorithm
//	 */
//	public Algorithm getAlgorithm() {
//		return algorithm;
//	}
//	/**
//	 * @param algorithm the algorithm to set
//	 */
//	public void setAlgorithm(Algorithm algorithm) {
//		this.algorithm = algorithm;
//	}
	/**
	 * equals to compare two Executable
	 */
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Executable executable = (Executable) o;
		return Objects.equals(this.id, executable.id) &&
				Objects.equals(this.name, executable.name) &&
				Objects.equals(this.url, executable.url);
	}

	/**
	 * hash code function
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id, name, url);
	}

	/**
	 * toString function
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Executable {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    url: ").append(toIndentedString(url)).append("\n");
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

