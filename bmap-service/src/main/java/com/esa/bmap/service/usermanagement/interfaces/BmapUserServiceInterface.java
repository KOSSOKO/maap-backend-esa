package com.esa.bmap.service.usermanagement.interfaces;

import java.util.Collection;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.BmaapUser;

public interface BmapUserServiceInterface {

	/**
	 * Add a new user in the database
	 * Just the id is required
	 * Normally, this id is provided by Liferay
	 * @param idBmapUser
	 * @return bmapUser created in the database
	 * @throws BmapException
	 */
	BmaapUser addBmapUser(BmaapUser user) throws BmapException;
	
	
	/**
	 * Get the user by its id
	 * @param idBmapUser
	 * @return bmapUser created in the database
	 * @throws BmapException
	 */
	BmaapUser getBmapUserById(Long id) throws BmapException;
	
	
	/**
	 * Delete a user by id
	 * This idBmapUser is provided by Liferay
	 * @param idBmapUser
	 */
	void deleteBmapUser(Long idBmapUser) throws BmapException;

	/**
	 * get all the users from the dataBAse
	 * 
	 * @return
	 */
	Collection<BmaapUser> getAllUsers();
}
