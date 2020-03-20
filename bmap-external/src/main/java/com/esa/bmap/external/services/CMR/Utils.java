package com.esa.bmap.external.services.CMR;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.external.model.cmr.granules.AdditionalAttributeRef;
import com.esa.bmap.external.model.cmr.granules.Granule;
import com.esa.bmap.external.model.cmr.granules.OnlineResource;

public class Utils {
	private static Logger LOG = LoggerFactory.getLogger(Utils.class);

	private static String ESA_ATTRIBUTE = "bands_min";
	private static String URL_LAYERS_PARAM = "LAYERS";



	/**
	 * utils method to check if the granule is a nasa granule or an esa one
	 * 
	 * @param cmrGranule
	 * @return
	 */
	public static Boolean isNasaGranule(Granule cmrGranule) {
		Boolean isNasa = true;
		List<AdditionalAttributeRef> liAdditionalAttributeRefs = cmrGranule.getAdditionalAttributes()
				.getAdditionalAttribute();

		for (Iterator iterator = liAdditionalAttributeRefs.iterator(); iterator.hasNext();) {
			AdditionalAttributeRef additionalAttributeRef = (AdditionalAttributeRef) iterator.next();

			if (additionalAttributeRef.getName().equalsIgnoreCase(ESA_ATTRIBUTE)) {
				isNasa = false;
				break;
			}
		}

		LOG.info("is Nasa Granule? " + isNasa);

		return isNasa;

	}

	/**
	 * utils method to extract layer name from wms onlineResourceUrl
	 * 
	 * @param cmrGranule
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public static String getLayerNameFromGranule(Granule cmrGranule) throws UnsupportedEncodingException {
		String layerName = null;
		Map<String, String> urlParametersMap = new HashMap<String, String>();
		if (cmrGranule.getOnlineResources() != null && !cmrGranule.getOnlineResources().getOnlineResource().isEmpty()) {
			List<OnlineResource> listOfOnlineResources = cmrGranule.getOnlineResources().getOnlineResource();

			for (OnlineResource onlineResource : listOfOnlineResources) {
				if (onlineResource.getType()
						.equalsIgnoreCase(Common.getPropertiesValue("Nasa.VisualizationURL.param"))) {
			
					String url = onlineResource.getURL();
					url = URLDecoder.decode(url, "UTF-8").replace( "&amp;",  "&");

					Pattern pattern = Pattern.compile("(\\w+)=?([^&]+)?");
					Matcher matcher = pattern.matcher(url);
					while (matcher.find()) {
						LOG.debug(" - Key   : " + matcher.group(1));
						LOG.debug("   Value : " + matcher.group(2));
					
						urlParametersMap.put(matcher.group(1), matcher.group(2));
					}
				}
			}
			LOG.debug(urlParametersMap.toString());
			layerName = urlParametersMap.get(URL_LAYERS_PARAM);
			
			
		
		}

		return layerName;

	}
	
}
