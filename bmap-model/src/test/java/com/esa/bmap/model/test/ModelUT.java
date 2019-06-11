//package com.esa.bmap.model.test;
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.esa.bmap.model.Granule;
//import com.esa.bmap.model.UtilsTest;
//
///**
// * ModelUT
// * 
// * @author QFAURE
// *
// */
//public class ModelUT {
//
//	/*
//	 * Tests equals for airborne data.
//	 */
//	@Test
//	public void granuleEquals() {
//
//		try {
//
//			// instantiation of granule with attributes
//			Granule granule1 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			granule1.setId((long) 1);
//			Granule granule2 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			granule2.setId((long) 1);
//
//			// test: equals
//			Assert.assertEquals(granule1, granule2);
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
//	 * Tests not equals for airborne data.
//	 */
//	@Test
//	public void granuleNotEquals() {
//
//		try {
//
//			// instantiation of granule with attributes
//			Granule granule1 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			granule1.setId((long) 1);
//			Granule granule2 = (Granule) UtilsTest.initData(UtilsTest.AIRBORNEDATA);
//			granule2.setId((long) 2);
//
//			// test: not equals
//			Assert.assertNotEquals(granule1, granule2);
//
//		} catch (Exception e) {
//
//			Assert.fail(e.getMessage());
//		}
//	}
//
//	
//}
