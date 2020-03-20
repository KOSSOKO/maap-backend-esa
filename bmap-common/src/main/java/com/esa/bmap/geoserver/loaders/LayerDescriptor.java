/*
 * $Id: LayerDescriptor.java 5301 2017-09-07 08:08:54Z csouriss $
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
package com.esa.bmap.geoserver.loaders;

/**
 * Bean describing a type of layer managed by this graphical tool (e.g. ASRP
 * 100.000e, SCAN Express 250, ...).
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public class LayerDescriptor {

    /**
     * The name used as layer name for display within this tool.
     */
    private String displayName;

    /**
     * The base used to name any physical storage or GeoServer workspace, layer
     * or group of layers associated to the layer this instance describes.
     */
    private String baseName;

    /**
     * Sole constructor.
     * 
     * @param displayName
     *            The name used as layer name for display within this tool.
     * @param baseName
     *            The base used to name any physical storage or GeoServer
     *            workspace, layer or group of layers associated to the layer
     *            this instance describes.
     */
    public LayerDescriptor(String displayName, String baseName) {
        this.displayName = displayName;
        this.baseName = baseName;
    }

    /**
     * Get the name used as layer name for display within this tool.
     * 
     * @return the layer's display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Get the base used to name any physical storage or GeoServer workspace,
     * layer or group of layers associated to the layer this instance describes.
     * 
     * @return the layer's base name
     */
    public String getBaseName() {
        return baseName;
    }

}
