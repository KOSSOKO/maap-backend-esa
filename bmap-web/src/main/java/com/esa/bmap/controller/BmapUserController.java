package com.esa.bmap.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.model.BmaapUser;
import com.esa.bmap.service.usermanagement.interfaces.BmapUserServiceInterface;

@RestController
@RequestMapping(value="/bmapuser")
public class BmapUserController {

	@Resource
	private BmapUserServiceInterface bmapUserService;

	/**
	 * add an user in the database 
	 * @param user
	 * @return BmaapUser
	 * @throws BmapException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public BmaapUser addUser(@RequestBody BmaapUser user) throws BmapException {
		//we create a new algorithm and return it
		return this.bmapUserService.addBmapUser(user);
	}
	
	/**
	 * delete algorithm by id
	 * @param id
	 * @throws BmapException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE) 
	public void deleteUser(@PathVariable(value = "id") Long id) throws BmapException {
		this.bmapUserService.deleteBmapUser(id);
	}
	
	/**
	 * get the user by it's id
	 * @param id
	 * @throws BmapException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) 
	public BmaapUser getUserById(@PathVariable(value = "id") Long id) throws BmapException {
		return this.bmapUserService.getBmapUserById(id);
	}
}
