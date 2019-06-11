package com.esa.bmap.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

/**
 * SystemResource
 */
@Entity
public class SystemResource  {

	//Unique identifier representing a specific SystemResource
	@Id
   	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	//CPU allowed for the user use
	@Column(unique = false, nullable = true)
	private Integer cpu = null;


	//ram allowed for the user to use
	@Column(unique = false, nullable = true)
	private Integer ram = null;

	//storage left for the user to use
	@Column(unique = false, nullable = true)
	private Integer storage = null;


	/**
	 * Default constructor
	 * Needed by Spring
	 */
	public SystemResource() {
		super();
	}
	
	public SystemResource(@Valid Integer cpu, @Valid Integer ram, Integer storage) {
		super();
		this.cpu = cpu;
		this.ram = ram;
		this.storage = storage;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getCpu() {
		return cpu;
	}

	/**
	 * 
	 * @param cpu
	 */
	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}


	/**
	 * get Unique identifier representing a specific SystemResource
	 * @return id
	 **/
	public Long getId() {
		return id;
	}

	/**
	 * set Unique identifier representing a specific SystemResource
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}


	/**
	 * get ram allowed for the user to use
	 * @return ram
	 **/
	public Integer getRam() {
		return ram;
	}

	/**
	 * set ram allowed for the user to use
	 * @param ram
	 */
	public void setRam(Integer ram) {
		this.ram = ram;
	}

	/**
	 * storage left for the user to use
	 * @return storage
	 **/
	public Integer getStorage() {
		return storage;
	}

	/**
	 * 
	 * @param storage
	 */
	public void setStorage(Integer storage) {
		this.storage = storage;
	}


	/**
	 * 
	 */
	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		SystemResource systemRessource = (SystemResource) o;
		return Objects.equals(this.cpu, systemRessource.cpu) &&
				Objects.equals(this.id, systemRessource.id) &&
				Objects.equals(this.ram, systemRessource.ram) &&
				Objects.equals(this.storage, systemRessource.storage) ;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cpu, id, ram, storage);
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class SystemResource {\n");

		sb.append("    cpu: ").append(toIndentedString(cpu)).append("\n");
		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    ram: ").append(toIndentedString(ram)).append("\n");
		sb.append("    storage: ").append(toIndentedString(storage)).append("\n");
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

