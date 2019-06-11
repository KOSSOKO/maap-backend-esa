package com.esa.bmap.dto;

import com.esa.bmap.model.AbstractUser;

/**
 * @author Capgemini
 * @version 0.0.1
 * This class is as Data Transfert Object between Liferay and the bmap back-end
 * The class contains all of required attributes to create a user in Liferay
 * 
 */
public class LiferayUserDTO extends AbstractUser {

	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Boolean isMale;
	/**
	 * @return the userId
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the isMale
	 */
	public Boolean getIsMale() {
		return isMale;
	}
	/**
	 * @param isMale the isMale to set
	 */
	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

	
	

}
