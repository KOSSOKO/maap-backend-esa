package com.esa.bmap.dto;

import com.esa.bmap.model.AbstractUser;

/**
 * @author Capgemini
 * @version 0.0.1
 * This class is as Data Transfert Object between Liferay and the bmap back-end
 * The class contains all of required attributes to create a user in Liferay
 * 
 */
public class GitlabUserDTO extends AbstractUser {

	private long id;
	private String email;
	private String firstNames;
	private String LastName;
	private String username;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the firstNames
	 */
	public String getFirstNames() {
		return firstNames;
	}
	/**
	 * @param firstNames the firstNames to set
	 */
	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return LastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	

	
	
	
	

}
