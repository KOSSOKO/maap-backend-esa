/*
 * $Id: MapFileExtensions.java 5301 2017-09-07 08:08:54Z csouriss $
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
 * Constants class gathering lower-case extensions of files manipulated by the
 * map loaders instances of this tool, alongside some related utility methods.
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public final class MapFileExtensions {

    /** Simple image files' extension. */
    public static final String IMG = "img"; //$NON-NLS-1$

    /** GeoTiff files' extension. */
    public static final String TIF = "tif"; //$NON-NLS-1$

    /** Shape files' extension. */
    public static final String SHP = "shp"; //$NON-NLS-1$

    /** Projection files' extension. */
    public static final String PRJ = "prj"; //$NON-NLS-1$

    /** DBase files' extension. */
    public static final String DBF = "dbf"; //$NON-NLS-1$

    /**
     * Extensions of files related to shape files, that this loader will handle
     * and load to the configured GeoServer.
     */
    protected static final String[] SHP_RELATED_EXTENSIONS = { "cpg", DBF, PRJ, //$NON-NLS-1$
            SHP, "shx", "fix", "qix", "sbn", "sbx" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$

    /** Layer style file extension. */
    public static final String SLD = "sld"; //$NON-NLS-1$

    /**
     * Extensions of files read and processed by the
     * {@link com.dga.ccs.maps.loader.loaders.BDOrthoLoader BDOrthoLoader}.
     */
    protected static final String[] BD_ORTHO_RELATED_EXTENSIONS = { "ecw", "grf", //$NON-NLS-1$ //$NON-NLS-2$
            "tab" }; //$NON-NLS-1$

    /** Extension of files embedding 0-level DTED data. */
    public static final String DTED_0 = "dt0"; //$NON-NLS-1$

    /** Extension of files embedding 1-level DTED data. */
    public static final String DTED_1 = "dt1"; //$NON-NLS-1$

    /** Extension of files embedding 2-level DTED data. */
    public static final String DTED_2 = "dt2"; //$NON-NLS-1$

    /**
     * Private constructor prohibiting this class' instantiation.
     */
    private MapFileExtensions() {
        super();
    }

    /**
     * Append some file extension to some provided base file name (without
     * extension).
     * 
     * @param baseFileName
     *            A base file name (without extension).
     * @param extension
     *            A file extension.
     * @return The full file name (with extension), basically
     *         "[baseFileName].[extension]".
     */
    public static String concat(String baseFileName, String extension) {
        return baseFileName + "." + extension; //$NON-NLS-1$
    }

}
