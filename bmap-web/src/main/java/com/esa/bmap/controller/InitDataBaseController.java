package com.esa.bmap.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esa.bmap.service.catalogue.data.interfaces.InitDataBaseServiceInterface;

@RestController
@RequestMapping(value="/catalogue/initdatabase")
public class InitDataBaseController  {

	@Resource
	private InitDataBaseServiceInterface initDataBaseService;
	
	/**
	 * post all init algorithm 
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void initBdd() {
		this.initDataBaseService.initDataBase();
	}
}
