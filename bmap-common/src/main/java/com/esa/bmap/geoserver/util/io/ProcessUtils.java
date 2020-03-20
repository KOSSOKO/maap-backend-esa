/*
 * $Id: ProcessUtils.java 5301 2017-09-07 08:08:54Z csouriss $
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
//package com.dga.ccs.maps.loader.util.io;
package com.esa.bmap.geoserver.util.io;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.exceptions.ProcessException;
//import com.dga.ccs.maps.loader.Messages;
//import com.dga.ccs.maps.loader.exceptions.ProcessException;

/**
 * Class gathering utility methods related to system processes launch and manipulation.
 * <p>
 * <b>Important</b>: this class is an extract of {@link com.dga.ccs.commons.system.util.os.ProcessUtils CCS-Commons/ProcessUtils} class that doesn't require Spring dependencies. This is mainly a code duplication though.
 * </p>
 *
 * @author $Author: csouriss $
 * @version $Revision: 5301 $
 */
public final class ProcessUtils {

	/**
	 * Class logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(ProcessUtils.class);

	/**
	 * Default private constructor prohibiting this class' instantiation.
	 */
	private ProcessUtils() {
		super();
	}

	/**
	 * Execute some command into a new process and wait for it to terminate.
	 * 
	 * @param command
	 * The command (with arguments) to be launched.
	 * @return The run process exit code.
	 * @throws ProcessException
	 * If some error happens when launching the process, or if it gets interrupted before termination.
	 */
	public static int launchAndWaitForProcess(String[] command)
			// throws ProcessException {
			throws BmapException {
		int returnCode;
		try {
			// Launch the process
			// if (LOG.isDebugEnabled()) {
			// LOG.debug(Messages.getInstance().get(
			// "maps_loader.processes.launch", //$NON-NLS-1$
			// Arrays.toString(command)));
			// }
			Process process = launchProcess(command);

			// Wait for the process to return an exit code
			returnCode = process.waitFor();
			if (returnCode != 0) {
				// throw new ProcessException(Messages.getInstance().get(
				// "maps_loader.errors.process_exec_error", command[0], //$NON-NLS-1$
				// formatCommandArgs(command)));
				LOG.error(Common.getMessageValue("processutils.launchandwaitforprocess.error"));
				throw new BmapException(Common.getMessageValue("processutils.launchandwaitforprocess.error"));
			}

		} catch (Exception e) {
			// throw new ProcessException(Messages.getInstance().get(
			// "maps_loader.errors.process_exec_error", command[0], //$NON-NLS-1$
			// formatCommandArgs(command)), e);
			// Restore interrupted state...
			Thread.currentThread().interrupt();
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}

		return returnCode;
	}

	/**
	 * Launch some new process and wait for it to terminate.
	 * 
	 * @param builder
	 * The instance providing the process to launch.
	 * @return The run process exit code.
	 * @throws ProcessException
	 * If some error happens when launching the process, or if it gets interrupted before termination.
	 */
	public static int launchAndWaitForProcess(ProcessBuilder builder) throws BmapException {
		// throws ProcessException {
		int returnCode;
		try {
			// Launch the process
			// if (LOG.isDebugEnabled()) {
			// LOG.debug(Messages.getInstance().get(
			// "maps_loader.processes.launch", //$NON-NLS-1$
			// Arrays.toString(builder.command().toArray())));
			// }
			Process process = builder.start();

			// Wait for the process to return an exit code
			returnCode = process.waitFor();
			if (returnCode != 0) {
				LOG.error(Common.getMessageValue("processutils.launchandwaitforprocess.error"));
				throw new BmapException(Common.getMessageValue("processutils.launchandwaitforprocess.error"));
			}
			// throw new ProcessException(Messages.getInstance().get(
			// "maps_loader.errors.process_exec_error", //$NON-NLS-1$
			// builder.command().get(0), formatCommandArgs(builder)));
			// }

		} catch (Exception e) {
			// throw new ProcessException(Messages.getInstance().get(
			// "maps_loader.errors.process_exec_error", //$NON-NLS-1$
			// builder.command().get(0), formatCommandArgs(builder)), e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}

		return returnCode;
	}

	/**
	 * Execute some command into a new process without waiting for it to terminate.
	 * 
	 * @param command
	 * The command (with arguments) to be launched.
	 * @return The process launched.
	 * @throws ProcessException
	 * If some error happens when launching the process.
	 */
	public static Process launchProcess(String[] command) throws BmapException {
		try {
			return Runtime.getRuntime().exec(command);

		} catch (Exception e) {
			// throw new ProcessException(Messages.getInstance().get(
			// "maps_loader.errors.process_exec_error", command[0], //$NON-NLS-1$
			// formatCommandArgs(command)), e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

	/**
	 * Formats an array of command arguments to make it printable into a log entry.
	 * 
	 * @param commandArgs
	 * The command arguments to process.
	 * @return A printable string presenting the provided command arguments.
	 */
	public static String formatCommandArgs(String[] commandArgs) {
		return Arrays.stream(commandArgs).map(arg -> '\"' + arg + '\"').collect(Collectors.joining(StringUtils.SPACE));
	}

	/**
	 * Formats some process' command arguments to make them printable into a log entry.
	 * 
	 * @param builder
	 * The instance providing the process' command arguments to format.
	 * @return A printable string presenting the provided command arguments.
	 */
	public static String formatCommandArgs(ProcessBuilder builder) {
		return builder.command().stream().map(arg -> '\"' + arg + '\"').collect(Collectors.joining(StringUtils.SPACE));
	}
}
