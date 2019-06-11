package com.esa.bmap.external.services.CMR;

/**
 * CMR Additional attributes to use in the mappers
 * 
 * <P>
 * These constants must be used to set the attribute in the models.
 * </p>
 *
 * @author jstranig
 *
 */
public interface CMRAdditionAttributes {

	
	
	//CMR existing additional attributes
	static final String ADDITIONAL_ATTRIBTE_GEOMETRY_TYPE = "Geolocated";
	static final String ADDITIONAL_ATTRIBUTE_SUB_REGION = "Site Name";
	
	static final String ADDITIONAL_ATTRIBUTE_DATA_FORMAT = "Data Format";
	static final String ADDITIONAL_ATTRIBUTE_DATA_FORMAT_SHAPEFILE = "shapefile";
	
	static final String ADDITIONAL_ATTRIBUTE_DATASET_STATUS = "Dataset Status";	
	static final String ADDITIONAL_ATTRIBUTE_DATASET_STATUS_OFFICIAL ="Official MAAP Data Product";
	
	//BMAAP additional attributes for a collection see Collection constant 
	static final String ADDITIONAL_ATTRIBUTE_CATEGORY_KEY_WORDS = "categoryKeyWords";
	static final String ADDITIONAL_ATTRIBUTE_URL_CARTO = "urlCarto";
	
	//BMAAP additional attributes for a granule
	static final String ADDITIONAL_ATTRIBUTE_HEIGHT = "height";	
	static final String ADDITIONAL_ATTRIBUTE_WIDTH = "width";	
	
	//BMAAP additional attributes for a scene granule
	/**
	 * Slant range pixel spacing (m)
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_PIXEL_SPACING = "pixel_spacing";	
	/**
	 * A0 used for sigma0 computation (m2)
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_SURFACE_RESOL = "surface_resol";
	/**
	 * Ground range pixel spacing (m)
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_GRD_RESOL = "GRD_resol";
	/**
	 * Slant range swath start (m)
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_SLR_START = "SLR_start";
	/**
	 * Average altitude of the ground (m)
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_Z_FIELD = "z_field";
	/**
	 * Average altitude of flight (m)
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_Z_FLIGHT= "z_flight";
	/**
	 * Direction of flight, clockwise, with respect to the North (deg)
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_HEADING = "heading";
	
	/**
	 * List of minimum pixel values for a tiff image which contains multiple bands.
	 */
	static final String ADDITIONAL_ATTRIBUTE_BANDS_MIN = "bands_min";
	
	
	/**
	 * List of maximum pixel values for a tiff image which contains multiple bands.
	 */
	static final String ADDITIONAL_ATTRIBUTE_BANDS_MAX = "bands_max";
  
	   
	/**
	 * List of average values for a tiff image which contains multiple bands.
	 */
	static final String ADDITIONAL_ATTRIBUTE_BANDS_AVERAGE = "bands_avg";
  
	/**
	 * List of standard deviation values for a tiff image which contains multiple bands.
	 */
	static final String ADDITIONAL_ATTRIBUTE_BANDS_STANDARD_DEVIATION = "bands_stdDeviation";

	/**
	 * Here after all the additional attribute referencing another granule
	 */
	/**
	 *  Reference to the granule of type SLC HH
	 */
	static final String ADDITIONAL_ATTRIBUTE_SLC_HH_GRANULE_REF = "SLC_HH";
	/**
	 *  Reference to the granule of type SLC HV
	 */
	static final String ADDITIONAL_ATTRIBUTE_SLC_HV_GRANULE_REF = "SLC_HV";
	/**
	 *  Reference to the granule of type SLC VH
	 */
	static final String ADDITIONAL_ATTRIBUTE_SLC_VH_GRANULE_REF = "SLC_VH";
	/**
	 *  Reference to the granule of type SLC VV
	 */
	static final String ADDITIONAL_ATTRIBUTE_SLC_VV_GRANULE_REF = "SLC_VV";
	/**
	 *  Reference to the granule of type kz
	 */
	static final String ADDITIONAL_ATTRIBUTE_KZ_GRANULE_REF = "kz";
	/**
	 *  Reference to the granule of type az
	 */
	static final String ADDITIONAL_ATTRIBUTE_AZ_GRANULE_REF = "az";
	/**
	 *  Reference to the granule of type rg
	 */
	static final String ADDITIONAL_ATTRIBUTE_RG_GRANULE_REF = "rg";
	/**
	 *  Reference to the granule of type inc
	 */
	static final String ADDITIONAL_ATTRIBUTE_INC_GRANULE_REF = "inc";

	/**
	 * Reference to the granule of type DEM
	 */
	static final String ADDITIONAL_ATTRIBUTE_DEM_GRANULE_REF = "DEM";
		
	/**
	 * Reference to the master granule
	 */
	static final String ADDITIONAL_ATTRIBUTE_MASTER_GRANULE_REF = "Master";
	/**
	 * The name of the scene for a granule data
	 */
	static final String ADDITIONAL_ATTRIBUTE_SCENE_NAME = "Scene Name";
	
	/**
	 * The type of product SLC | DEM | ROI | ..
	 */
	static final String ADDITIONAL_ATTRIBUTE_PRODUCT_TYPE = "Product Type";
	
	/**
	 * Espg Code Native in the original data tiff.
	 */
	static final String ADDITIONAL_ATTRIBUTE_EPSG_CODE_NATIVE = "epsgCodeNative";
	
	/**
	 * Epsg Code Declared for MAAP
	 */
	static final String ADDITIONAL_ATTRIBUTE_EPSG_CODE_DECLARED = "epsgCodeDeclared";
	
	static final String EXTENSION_TIFF = "tiff";
	static final String MIMETYPE_IMAGE_TIFF = "image/tiff";
	static final String PROCESSING_LEVEL_0 = "L0";
	static final String PROCESSING_LEVEL_1 = "L1";
	static final String PROCESSING_LEVEL_2 = "L2";
	static final String FOREST_COMPOSITION_VEGETATION_STRUCTURE = "FOREST COMPOSITION/VEGETATION STRUCTURE";
	static final String BIOMASS = "BIOMASS";
	static final String TOPOGRAPHIC_EFFECTS = "TOPOGRAPHIC EFFECTS";
	static final String FORESTS = "FORESTS";
	static final String CANOPY_CHARACTERISTICS = "CANOPY CHARACTERISTICS";
	static final int SIZE_FACTOR_1000 = 1000;
	
	
	
	/**
	 * Messages 
	 */
	static final String COLLECTION_PROCESSING_LEVEL_MESSAGE = "collection.processing_level.message";
	
	
	
	

}
