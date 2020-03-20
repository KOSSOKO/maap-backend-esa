/*
 * $Id: GeoServerStrictApi.java 4629 2017-05-31 14:40:50Z acannac $
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
package com.esa.bmap.geoserver.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.exceptions.PublishException;
import com.esa.bmap.geoserver.api.exceptions.RetrieveException;
//import com.dga.ccs.commons.geoserver.exceptions.PublishException;
//import com.dga.ccs.commons.geoserver.exceptions.RetrieveException;
//import com.dga.ccs.commons.geoserver.manager.GeoServerContext;
import com.esa.bmap.geoserver.api.manager.GeoServerContext;

import it.geosolutions.geoserver.rest.GeoServerRESTManager;

/**
 * Wrapper around the GeoServer REST API helping performing "strict" calls to that API. With this "strict" API, any non-successful REST call to the GeoServer will result in throwing some exception (that may be caught by callers).
 *
 * @author $Author: acannac $
 * @version $Revision: 4629 $
 */
public final class GeoServerStrictApi {

	private static final Logger LOG = LoggerFactory.getLogger(GeoServerStrictApi.class);

	/**
	 * Sole private constructor prohibiting this class' instantiation.
	 */
	private GeoServerStrictApi() {
		super();
	}

	/**
	 * Function performing some GET REST call to the GeoServer.
	 *
	 * @param <T>
	 * The type of data expected as output of this GET REST call.
	 */
	@FunctionalInterface
	public static interface GeoServerGetCall<T> {

		/**
		 * Run the GET REST call.
		 * 
		 * @param mgr
		 * The GeoServer REST API manager instance to use to perform the call.
		 * @return The data expected as output of this GET REST call.
		 * @throws Exception
		 * Any exception that may be raised by the vanilla GeoServer REST API itself.
		 */
		T run(GeoServerRESTManager mgr) throws Exception;
	}

	/**
	 * Run some GET request onto the configured GeoServer instance.
	 * 
	 * @param <T>
	 * The type of data expected as output of the GET REST call to perform.
	 * @param action
	 * The action to apply, providing a {@link GeoServerRESTManager} instance to use to perform the REST call to the GeoServer.
	 * @return The data expected as output of the GET REST call.
	 * @throws RetrieveException
	 * If an error is thrown by the vanilla GeoServer REST API, or if no result is received from the REST call.
	 */
	public static <T> T get(GeoServerGetCall<T> action) throws BmapException {
		// Run the GET request
		T result;
		try {
			GeoServerRESTManager gsManager = GeoServerContext.getInstance().getGsManager();
			result = action.run(gsManager);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
			// throw new RetrieveException(Messages.getInstance()
			// .get("ccs_commons_geoserver.gs_api.get.error"), e); //$NON-NLS-1$
		}

		// If no result was retrieved, assume something went wrong and throw an
		// exception
		if (result == null) {
			LOG.error(Common.getMessageValue("geoserverstrictapi.get.result.null"));
			throw new BmapException(Common.getMessageValue("geoserverstrictapi.get.result.null"));
			// throw new RetrieveException(Messages.getInstance()
			// .get("ccs_commons_geoserver.gs_api.get.no_result")); //$NON-NLS-1$
		} else {
			return result;
		}
	}

	/**
	 * Function performing some POST, PUT or DELETE REST call to the GeoServer (to publish or remove data onto/from it).
	 */
	@FunctionalInterface
	public static interface GeoServerPostCall {

		/**
		 * Run the POST, PUT or DELETE REST call.
		 * 
		 * @param mgr
		 * The GeoServer REST API manager instance to use to perform the call.
		 * @return The vanilla GeoServer REST API's result: {@code true} if the action was successfully performed, {@code false} otherwise.
		 * @throws Exception
		 * Any exception that may be raised by the vanilla GeoServer REST API itself.
		 */
		boolean run(GeoServerRESTManager mgr) throws Exception;
	}

	/**
	 * Run some POST, PUT or DELETE request onto the configured GeoServer instance.
	 * 
	 * @param action
	 * The action to apply, providing a {@link GeoServerRESTManager} instance to use to perform the REST call to the GeoServer.
	 * @throws PublishException
	 * If an error is thrown by the vanilla GeoServer REST API, or if the call's result indicates that the requested action wasn't performed.
	 */
	public static void post(GeoServerPostCall action)
			// throws PublishException {
			throws BmapException {
		// Run the POST request
		boolean done = tryPost(action);

		// If the result is negative, assume something went wrong and throw an
		// exception
		if (!done) {
			LOG.error(Common.getMessageValue("geoserverstrictapi.post.result.false"));
			throw new BmapException(Common.getMessageValue("geoserverstrictapi.post.result.false"));

			// throw new PublishException(Messages.getInstance()
			// .get("ccs_commons_geoserver.gs_api.post.no_result")); //$NON-NLS-1$
		}
	}

	/**
	 * Attempt to run some POST, PUT or DELETE request onto the configured GeoServer instance.
	 * 
	 * @param action
	 * The action to apply, providing a {@link GeoServerRESTManager} instance to use to perform the REST call to the GeoServer.
	 * @return The vanilla GeoServer REST API's result: {@code true} if the action was successfully performed, {@code false} otherwise.
	 * @throws PublishException
	 * If an error is thrown by the vanilla GeoServer REST API.
	 */
	public static boolean tryPost(GeoServerPostCall action)
			// throws PublishException {
			throws BmapException {
		try {
			GeoServerRESTManager gsManager = GeoServerContext.getInstance().getGsManager();
			return action.run(gsManager);

		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
			// throw new PublishException(Messages.getInstance()
			// .get("ccs_commons_geoserver.gs_api.post.error"), e); //$NON-NLS-1$
		}
	}

}
