package com.esa.bmap.model.util;

import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.esa.bmap.model.Collection;
import com.esa.bmap.model.Data;
import com.esa.bmap.model.Granule;

/**
 * 
 * @author jstranig
 *
 */
public class GranuleHelper {

	/**
	 * The string ROI_PATTERN to detect the shape file extensions in the
	 * filename
	 */

	private static final String ROI_PATTERN = "([^\\s]+(\\.(?i)(shp|shx|dbf|prj))$)";

	/**
	 * Granule type pattern matcher last group is the type found For example :
	 * biosar1_412_az.tiff the granule type is az
	 */
	private static final String GRANULE_REF_PATTERN = "(?:[a-zA-Z0-9\\-]+_)+((?:[A-Z]+_[A-Z]+)|(?:[a-z]+))_?\\.tiff";
	/**
	 * Pattern holder the roi extension
	 */
	private static final Pattern pattern;

	/**
	 * Pattern holder the granule type
	 */
	private static final Pattern patternGranuleType;

	static {
		pattern = Pattern.compile(ROI_PATTERN);
		patternGranuleType = Pattern.compile(GRANULE_REF_PATTERN);

	}

	/**
	 * The method returns true when the file name extension is one of the pattern
	 * define in ROI_PATTERN ROI_PATTERN}
	 * 
	 * @param fileName the file to analyze
	 * @return
	 */
	public static boolean isROIFile(final String fileName) {
		// Matcher for the roi pattern
		Matcher matcher = pattern.matcher(fileName);
		return matcher.matches();
	}

	/**
	 * Method to test each data fileName of the granule to find at least one with
	 * and extension of type ROI_PATTERN
	 * 
	 * @param bmaapGranule the granule to test the file names
	 * @return a boolean
	 */
	public static boolean isRegionOfInterest(Granule bmaapGranule) {
		// Matcher for the roi pattern
		boolean isRoi = true;
		Matcher matcher = null;
		if (bmaapGranule.getDataList() != null && bmaapGranule.getDataList().size() > 0) {
			for (Data data : bmaapGranule.getDataList()) {
				matcher = pattern.matcher(data.getFileName());
				if (!matcher.matches()) {
					isRoi = false;
					break;
				}
			}
		} else {
			isRoi = false;
		}
		return isRoi;
	}

	/**
	 * The method returns true when the granule contains the attributes :
	 * pixel_spacing, surface_resol, GRD_resol, SLR_start, z_field, z_flight,
	 * heading.
	 * 
	 * The Method returns true when the granule represent an airborn granule
	 * 
	 * @return
	 */
	public static boolean isScene(Granule bmaapGranule) {
		boolean isScene = false;
		if (bmaapGranule.getPixelSpacing() != null && bmaapGranule.getSurfaceResol() != null
				&& bmaapGranule.getGrdResol() != null && bmaapGranule.getSlrStart() != null
				&& bmaapGranule.getzTerrain() != null && bmaapGranule.getzFlight() != null) {
			isScene = true;
		}

		return isScene;
	}

	/**
	 * The method returns true when the granule contains the attribute subRegion
	 * 
	 * The Method returns true when the granule represent an airborn granule
	 * 
	 * @return
	 */
	public static boolean isAirbornData(Granule bmaapGranule, Collection collection) {
		boolean isAirbornData = false;
		if (collection.getCategoryKeyWords() != null) {
			if (collection.getCategoryKeyWords().contains(Collection.COLLECTION_TYPE_GROUND_CAMPAIGN)
					&& !isRegionOfInterest(bmaapGranule)) {

				isAirbornData = true;
			}
		} else {
			if (bmaapGranule.getOrbitNumber() == null && !isRegionOfInterest(bmaapGranule)) {
				isAirbornData = true;
			}
		}

		return isAirbornData;
	}

	/**
	 * The method returns true when the granule doesn't contain the attribute
	 * subRegion
	 * 
	 * The Method returns true when the granule represent an airborn granule
	 * 
	 * @return
	 */
	public static boolean isSARRawData(Granule bmaapGranule, Collection collection) {
		boolean isSatData = false;
		if (collection.getCategoryKeyWords() != null) {
			if (collection.getCategoryKeyWords().contains(Collection.COLLECTION_TYPE_SATELLITE)
					&& !isRegionOfInterest(bmaapGranule)) {
				isSatData = true;
			}
		} else {
			if (bmaapGranule.getOrbitNumber() != null) {
				isSatData = true;
			}
		}

		return isSatData;
	}

	/**
	 * Match the pattern to find the last string before the dot of the extension
	 * 
	 * @param granuleName the name of the granule
	 * @return the granule type
	 */
	public static String getGranuleType(String granuleName) {
		if (granuleName.contains("SLC")) {

		}
		Matcher matcher = patternGranuleType.matcher(granuleName);
		String granuleType = null;
		if (matcher.find()) {
			granuleType = matcher.group(matcher.groupCount());
		}
		return granuleType;

	}

}
