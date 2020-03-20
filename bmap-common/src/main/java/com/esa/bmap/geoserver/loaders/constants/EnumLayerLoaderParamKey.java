/*
 * $Id: EnumLayerLoaderParamKey.java 5301 2017-09-07 08:08:54Z csouriss $
 *
 * ======================================================
 *
 * Project : CCS
 * Produit par Capgemini.
 *
 * ======================================================
 * HISTORIQUE
 * FIN-HISTORIQUE
 * ======================================================
 */
package com.esa.bmap.geoserver.loaders.constants;

/**
 * Enumeration of keys used by individual map loaders to retrieve additional
 * parameters within the parameters map provided to them.
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public enum EnumLayerLoaderParamKey {

    /**
     * Level of DTED to be loaded (used by the
     * {@link com.dga.ccs.maps.loader.loaders.DTEDLoader DTEDLoader}).
     */
    DTED_NUMBER,

    /**
     * Name of a layer group to create (used by any map loader publishing some
     * group of layers onto the GeoServer).
     */
    LAYER_GROUP;

}
