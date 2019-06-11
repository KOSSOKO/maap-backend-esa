package com.esa.bmap.model.util;

import org.junit.Test;

import com.vividsolutions.jts.util.Assert;

public class GranuleHelperTest {

	@Test
	public void granuleNameTest() {
		Assert.equals("az", GranuleHelper.getGranuleType("biosar1_412_az.tiff"));
		Assert.equals("inc", GranuleHelper.getGranuleType("biosar1_412_inc.tiff"));
		Assert.equals("rg", GranuleHelper.getGranuleType("biosar1_412_rg.tiff"));
		Assert.equals("SLC_HH", GranuleHelper.getGranuleType("biosar1_412_SLC_HH.tiff"));
		Assert.equals("SLC_HV", GranuleHelper.getGranuleType("biosar1_412_SLC_HV.tiff"));
		Assert.equals("SLC_VH", GranuleHelper.getGranuleType("biosar1_412_SLC_VH.tiff"));
		Assert.equals("SLC_VV", GranuleHelper.getGranuleType("biosar1_412_SLC_VV.tiff"));


	}

}
