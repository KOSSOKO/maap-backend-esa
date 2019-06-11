//package com.esa.bmap.controller.test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.bedatadriven.jackson.datatype.jts.JtsModule;
//import com.esa.bmap.common.exceptions.BmapException;
//import com.esa.bmap.controller.DataCatalogController;
//import com.esa.bmap.model.Granule;
//import com.esa.bmap.model.GranuleCriteria;
//import com.esa.bmap.model.UtilsTest;
//import com.esa.bmap.service.catalogue.data.interfaces.DataCatalogServiceInterface;
//import com.fasterxml.jackson.databind.JavaType;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
///**
// * DataCatalogControllerUT
// * 
// * @author QFAURE
// *
// */
//@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = { DataCatalogController.class }, secure = false)
//public class DataCatalogControllerUT {
//
//	@Autowired
//	private MockMvc mvc;
//
//	@MockBean
//	private DataCatalogServiceInterface service;
//
//	private Logger log = LoggerFactory.getLogger(DataCatalogControllerUT.class);
//
//	/*
//	 * Tests serialization/deserialization of airborne data using get data by id.
//	 */
//	@Test
//	public void getGranuleById() {
//
//		try {
//
//			// instantiation of airborne data with attributes
//			Granule granule1 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			granule1.setId((long) 1);
//
//			Granule airbornData2 = (Granule) getGranuleById(granule1);
//
//			// test: get airborne data id from object and get saved airborne data from database with id
//			Assert.assertNotNull(airbornData2);
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//
//	/*
//	 * Used to test serialization/deserialization using get granule by id.
//	 */
//	public Granule getGranuleById(Granule granule) {
//
//		Granule granuleToReturn = null;
//
//		try {
//
//			// configure and perform an action on the controller
//			Mockito.when(service.getGranuleById(granule.getId())).thenReturn(granule);
//			String str = mvc.perform(MockMvcRequestBuilders.get("/catalogue/data/{id}", granule.getId()).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse().getContentAsString();
//			this.log.info(str);
//
//			// deserialize the returned string
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.registerModule(new JtsModule());
//			granuleToReturn = mapper.readValue(str, Granule.class);
//			this.log.info(granuleToReturn.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//
//		return granuleToReturn;
//	}
//
//	/*
//	 * Tests serialization/deserialization of airborne data using get data by criteria.
//	 */
//	@Test
//	public void getAirborneDataByCriteria() {
//
//		try {
//
//			// instantiation of airborne data with attributes
//			Granule airbornData1 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			airbornData1.setId((long) 1);
//			Granule airbornData2 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			airbornData2.setId((long) 2);
//
//			Collection<Granule> airborneDataCollection1 = new ArrayList<>();
//			airborneDataCollection1.add(airbornData1);
//			airborneDataCollection1.add(airbornData2);
//
//			Collection<Granule> airborneDataCollection2 = (Collection<Granule>) getGranuleByCriteria(UtilsTest.initGranuleCriteria(), airborneDataCollection1);
//
//			// test: get airborne data id from object and get saved airborne data from database with id
//			Assert.assertNotNull(airborneDataCollection2);
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	
//
//	/*
//	 * Used to test serialization/deserialization using get data by criteria.
//	 */
//	public Collection<Granule> getGranuleByCriteria(GranuleCriteria granuleCriteria, Collection<Granule> granuleCollection) {
//
//		Collection<Granule> granuleToReturn = new ArrayList<>();
//
//		try {
//
//			// configure and perform an action on the controller
//			Mockito.when(service.getGranuleByCriteria(Mockito.any(GranuleCriteria.class))).thenReturn(granuleCollection);
//			ObjectMapper mapper = new ObjectMapper();
//			String requestbody = mapper.writer().writeValueAsString(new GranuleCriteria()); // TODO QFA: used data criteria specified as parameter and add spring object mapper to test context for it to work
//			String str = mvc.perform(MockMvcRequestBuilders.post("/catalogue/data/").contentType(MediaType.APPLICATION_JSON_VALUE).content(requestbody).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn().getResponse().getContentAsString();
//			this.log.info(str);
//
//			// deserialize the returned string
//			mapper.registerModule(new JtsModule());
//			JavaType dataCollectionJavaType = mapper.getTypeFactory().constructCollectionType(Collection.class, Granule.class);
//			granuleToReturn = mapper.readerFor(dataCollectionJavaType).readValue(str);
//			this.log.info(granuleToReturn.toString());
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//
//		return granuleToReturn;
//	}
//
//	/*
//	 * Tries to deserialize data criteria jsons which do not meet security requirements.
//	 */
//	@Test
//	public void serializeIllegalGranuleCriteria() {
//
//		String json1 = "{\"GranuleCriteria\":{\"productTypes\":[\"*\"]}}";
//		String json2 = "{\"GranuleCriteria\":{\"instrumentNames\":[\"*\"]}}";
//		String json3 = "{\"GranuleCriteria\":{\"geometryTypes\":[\"*\"]}}";
//		String json4 = "{\"GranuleCriteria\":{\"processingLevels\":[\"*\"]}}";
//		String json5 = "{\"GranuleCriteria\":{\"groundCampaignNames\":[\"*\"]}}";
//		String json6 = "{\"GranuleCriteria\":{\"subRegionNames\":[\"*\"]}}";
//
//		ArrayList<String> list = new ArrayList<>();
//		list.add(json1);
//		list.add(json2);
//		list.add(json3);
//		list.add(json4);
//		list.add(json5);
//		list.add(json6);
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		for (String currentJson : list) {
//
//			boolean fail = true;
//
//			try {
//
//				mapper.readValue(currentJson, GranuleCriteria.class);
//
//			} catch (IOException e) {
//
//				if (e.getCause().getClass().equals(BmapException.class)) {
//
//					fail = false;
//
//				} else {
//
//					Assert.fail(e.getMessage());
//				}
//			}
//
//			if (fail) {
//
//				Assert.fail("The security check exception was not thrown.");
//			}
//		}
//	}
//}
