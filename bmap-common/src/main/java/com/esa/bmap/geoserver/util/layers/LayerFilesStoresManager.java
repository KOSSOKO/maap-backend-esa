/*
 * $Id: LayerFilesStoresManager.java 5325 2017-09-08 09:33:08Z acannac $
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
package com.esa.bmap.geoserver.util.layers;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Properties;
import java.util.function.BiFunction;

import org.apache.commons.lang3.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;
import com.esa.bmap.geoserver.api.Messages;
import com.esa.bmap.geoserver.api.exceptions.LoaderException;
import com.esa.bmap.geoserver.loaders.constants.MapFileExtensions;
//import com.dga.ccs.maps.loader.loaders.constants.MapFileExtensions;
import com.esa.bmap.geoserver.loaders.providers.Context;
//import com.dga.ccs.maps.loader.providers.Context;
//import com.dga.ccs.maps.loader.providers.ToolConfig;
//import com.dga.ccs.maps.loader.util.io.ProcessUtils;
//import com.dga.ccs.maps.loader.util.log.IntegratedLogger;
import com.esa.bmap.geoserver.loaders.providers.ToolConfig;
import com.esa.bmap.geoserver.util.io.ProcessUtils;

/**
 * Instance managing map data files' physical storing on the server hosting the GeoServer.
 * <p>
 * Physical files are stored within workspaces, and each workspace name matches some workspace published on the configured GeoServer instance. A workspace name contains the base layer name and some version suffix. Its structure follows the scheme below:
 * <ul>
 * <li>workspace name</li>
 * <ul>
 * <li>styles sub-directory (gathering SLD files)</li>
 * <li>storage sub-directory #1 (some GeoServer layer)</li>
 * <li>...</li>
 * <li>storage sub-directory #n</li>
 * </ul>
 * </ul>
 * </p>
 *
 * @author $Author: acannac $
 * @version $Revision: 5325 $
 */
public final class LayerFilesStoresManager {

	/** Logger. */
	private static final Logger LOG = LoggerFactory.getLogger(LayerFilesStoresManager.class);

	/**
	 * Singleton instance of this layer data manager.
	 */
	private static final LayerFilesStoresManager INSTANCE = new LayerFilesStoresManager();

	/**
	 * Property key used to retrieve within tool's configuration the root of the map data files physical store to manage.
	 */
	private static final String ROOT_FOLDER_PROPERTY_NAME = "ccs.layer.data.dir"; //$NON-NLS-1$

	/**
	 * Name of the workspace sub-directory gathering SLD style files.
	 */
	private static final String STYLE_DIRECTORY_NAME = "styles"; //$NON-NLS-1$

	/**
	 * Function initializing some layer files storage created by this {@link LayerFilesStoresManager}.
	 * 
	 * @see InitStorageFunction#apply(Path)
	 */
	@FunctionalInterface
	public static interface InitStorageFunction {

		/**
		 * Initialize the provided storage.
		 * 
		 * @param storage
		 * Storage to initialize.
		 * @throws LoaderException
		 * If anything goes wrong when initializing.
		 */
		void apply(Path storage) throws BmapException;
		// throws LoaderException;
	}

	/**
	 * Void layer files storage initializing function.
	 */
	public static final InitStorageFunction NOOP_STORE_INIT = path -> {
		// Empty lambda
	};

	/**
	 * Function handling a single entry file (accepted as a map data file to process).
	 * 
	 * @see HandleFileFunction#apply(Path, Path)
	 */
	@FunctionalInterface
	public static interface HandleFileFunction {

		/**
		 * Process some provided accepted data file and fill the target file store accordingly.
		 * 
		 * @param sourceFile
		 * The entry file to handle.
		 * @param targetDir
		 * The root of the target file store to fill.
		 * @throws IOException
		 * If anything goes wrong when handling this file.
		 */
		void apply(Path sourceFile, Path targetDir) throws IOException;
	}

	/**
	 * Root of the map data files physical store to manage.
	 */
	private Path rootFolder;

	/**
	 * Private constructor prohibiting other instantiations of this singleton class.
	 */
	private LayerFilesStoresManager() {
		super();
	}

	/**
	 * Get this singleton's layer data manager instance.
	 * 
	 * @return The singleton's layer data manager instance.
	 */
	public static LayerFilesStoresManager getInstance() {
		return INSTANCE;
	}

	/**
	 * Initialize this singleton's layer data manager instance.
	 */
	public void init() {
		this.rootFolder = Paths.get(ToolConfig.getInstance().getMainConfig().getProperty(ROOT_FOLDER_PROPERTY_NAME));
	}

	/**
	 * Create a layer files store with the given name and associated to the given workspace name.
	 * <p>
	 * If the considered layer files store already exists, calling this method won't change a thing.
	 * </p>
	 * 
	 * @param workspace
	 * The name of the versioned workspace to which the store to create should be associated.
	 * @param storage
	 * The name of the store to create, or {@code null} if the sole workspace creation is requested.
	 * @return The path to the target layer files storage requested, or to the workspace root if provided {@code storage} was {@code null}.
	 * @throws LoaderException
	 * If the layer files store could not be created.
	 */
	public Path createStore(String workspace, String storage)
			// throws LoaderException {
			throws BmapException {
		// Make sure the associated workspace exists
		Path workspaceRoot = rootFolder.resolve(workspace);
		Path result = workspaceRoot;
		boolean created = false;
		if (!Files.exists(workspaceRoot)) {
			createDirectory(workspaceRoot, false);
			created = true;
		}

		// If some sub-store creation is requested, do it
		if (storage != null) {
			Path storeRoot = workspaceRoot.resolve(storage);
			result = storeRoot;
			if (!Files.exists(storeRoot)) {
				createDirectory(storeRoot, false);
				created = true;
			}
		}

		// Give back store's root
		if (created) {
			// LOG.info(Messages.getInstance()
			// .get("maps_loader.stores_loader.store_created", result)); //$NON-NLS-1$);
		}
		return result;
	}

	/**
	 * Recursively delete the layer files store with the given name and associated to the given workspace name.
	 * 
	 * @param workspace
	 * The name of the versioned workspace to which the store to delete is associated.
	 * @param storage
	 * The name of the store to delete, or {@code null} if the entire workspace deletion is requested.
	 * @throws LoaderException
	 * If the layer files store could not be deleted.
	 */
	public void deleteStore(String workspace, String storage)
			// throws LoaderException {
			throws BmapException {
		Messages messages = Messages.getInstance();
		Path destPath = storage == null ? rootFolder.resolve(workspace) : rootFolder.resolve(workspace).resolve(storage);

		// Nothing to do if the store doesn't exist
		if (!Files.exists(destPath)) {
			// String message = messages.get(
			// "maps_loader.stores_loader.no_store_to_delete", //$NON-NLS-1$
			// destPath);
			// LOG.info(message);
			// IntegratedLogger.publishInfo(message);
			return;
		}

		// Otherwise, recursively delete the specified store
		try {
			Files.walkFileTree(destPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
			// String successMsg = messages
			// .get("maps_loader.stores_loader.store_deleted", destPath); //$NON-NLS-1$
			// LOG.info(successMsg);
			// IntegratedLogger.publishInfo(successMsg);

		} catch (Exception e) {
			// String errorMsg = messages.get(
			// "maps_loader.stores_loader.delete_store_error", //$NON-NLS-1$
			// destPath);
			// throw new LoaderException(errorMsg, e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

	/**
	 * Copy some provided style file into some specified layer version workspace.
	 * 
	 * @param styleName
	 * The name of the style to add (which is the base name of the associated SLD file).
	 * @param workspaceName
	 * The name of the workspace into which it should be added.
	 * @return The location of the style file within the specified workspace.
	 * @throws LoaderException
	 * If the style file cannot be copied.
	 */
	public Path addStyleToWorkspace(String styleName, String workspaceName)
			// throws LoaderException {
			throws BmapException {
		// Make sure the styles directory has been created
		Path stylesDir = rootFolder.resolve(workspaceName).resolve(STYLE_DIRECTORY_NAME);
		createDirectory(stylesDir, false);

		// Retrieve the SLD file from tool
		Path sldSourceFile = Context.getInstance().getStyleFile(styleName);
		if (sldSourceFile == null) {
			// String noStyleMsg = Messages.getInstance()
			// .get("maps_loader.stores_loader.no_style_error", styleName); //$NON-NLS-1$
			// throw new LoaderException(noStyleMsg);
			LOG.error(Common.getMessageValue("layerfilesstoresmanager.addstyletoworkspace.error"));
			throw new BmapException(Common.getMessageValue("layerfilesstoresmanager.addstyletoworkspace.error"));
		}

		// Copy the specified style file to it
		Path sldTargetFile = stylesDir.resolve(MapFileExtensions.concat(styleName, MapFileExtensions.SLD));
		try {
			copyFile(sldSourceFile, sldTargetFile);
			return sldTargetFile;

		} catch (Exception e) {
			// String errorMsg = Messages.getInstance().get(
			// "maps_loader.stores_loader.add_style_error", //$NON-NLS-1$
			// styleName, workspaceName, sldTargetFile);
			// throw new LoaderException(errorMsg, e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

	/**
	 * Remove some style file from some specific layer version workspace.
	 * 
	 * @param sldFileName
	 * The name of the SLD file to delete.
	 * @param workspaceName
	 * The name of the workspace from which it should be deleted.
	 * @throws LoaderException
	 * If the style file cannot be deleted.
	 */
	public void removeStyleFromWorkspace(String sldFileName, String workspaceName) throws BmapException {
		// throws LoaderException {
		Path sldFile = rootFolder.resolve(workspaceName).resolve(STYLE_DIRECTORY_NAME).resolve(sldFileName);
		try {
			Files.delete(sldFile);
		} catch (Exception e) {
			// String errorMsg = Messages.getInstance().get(
			// "maps_loader.stores_loader.remove_style_error", //$NON-NLS-1$
			// sldFileName, workspaceName, sldFile);
			// throw new LoaderException(errorMsg, e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

	/**
	 * Create a sub-directory into some storage and set the appropriate permissions onto it.
	 * 
	 * @param dir
	 * The full path of the sub-directory to create.
	 * @param recursive
	 * Flag indicating whether non-existent sub-directories should also be created.
	 * @throws LoaderException
	 * Thrown if any error occurs while creating / initializing the sub-directory.
	 */
	public void createDirectory(Path dir, boolean recursive)
			// throws LoaderException {
			throws BmapException {
		try {
			// If the parent doesn't exist, check for all parents
			// While parent folder doesn't exist, create it, AND SET
			// PERMISIONS
			if (!Files.exists(dir.getParent())) {
				createDirectory(dir.getParent(), false);
			}
			// Create the store folder
			if (recursive) {
				Files.createDirectories(dir);
			} else {
				Files.createDirectory(dir);
			}
			setDirectoryPermissions(dir);

		} catch (Exception e) {
			// throw new LoaderException(Messages.getInstance()
			// .get("maps_loader.stores_loader.create_store_error", dir), //$NON-NLS-1$
			// e);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

	/**
	 * Import some file into the data directory managed by this tool.
	 * 
	 * @param sourceFilePath
	 * The file to copy.
	 * @param targetFilePath
	 * The copy target.
	 * @throws IOException
	 * If something goes wrong when copying the file or applying permissions.
	 */
	public void copyFile(Path sourceFilePath, Path targetFilePath) throws IOException {
		Files.copy(sourceFilePath, targetFilePath);
		setFilePermissions(targetFilePath);
	}

	/**
	 * Set appropriate permissions on some directory just created by this tool.
	 * 
	 * @param dirPath
	 * The directory to modify.
	 * @throws IOException
	 * If something goes wrong when applying permissions.
	 */
	public void setDirectoryPermissions(Path dirPath) throws IOException {
		setPermissions(dirPath, "rwx", "rwx", true); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Set appropriate permissions on some file just created by this tool.
	 * 
	 * @param filePath
	 * The file to modify.
	 * @throws IOException
	 * If something goes wrong when applying permissions.
	 */
	public void setFilePermissions(Path filePath) throws IOException {
		setPermissions(filePath, "rw", "r", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Set appropriate permissions on some file created by the GeoServer within the data directory managed by this tool.
	 * 
	 * @param filePath
	 * The file to modify.
	 * @throws IOException
	 * If something goes wrong when applying permissions.
	 */
	public void setPubFilePermissions(Path filePath) throws IOException {
		setPermissions(filePath, "rw", "rw", false); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Set appropriate permissions on some file or directory.
	 * 
	 * @param path
	 * The file or directory to modify.
	 * @param loadingGroupRights
	 * The POSIX rights to give to the group of users responsible for loading data.
	 * @param publishingGroupRights
	 * The POSIX rights to give to the group of users responsible for performing data publication (i.e. GeoServer's group).
	 * @param directory
	 * {@code true} if the provided path denotes a directory, {@code false} if it denotes a file.
	 * @throws IOException
	 * If something goes wrong when applying permissions.
	 */
	private void setPermissions(Path path, String loadingGroupRights, String publishingGroupRights, boolean directory) throws IOException {
		if (SystemUtils.IS_OS_LINUX) {
			String targetPathStr = path.toString();

			// Retrieve groups names
			Properties mainConfig = ToolConfig.getInstance().getMainConfig();
			String loadingGroup = mainConfig.getProperty("group_names.loading_users"); //$NON-NLS-1$
			String publishingGroup = mainConfig.getProperty("group_names.publishing_users"); //$NON-NLS-1$

			// Build and execute the command:
			StringBuilder permsCmdBuilder = new StringBuilder();
			// - restrict POSIX rights to the owner only
			permsCmdBuilder.append(directory ? "chmod -R" : "chmod") //$NON-NLS-1$ //$NON-NLS-2$
					.append(" go-rwx ").append(targetPathStr); //$NON-NLS-1$
			// - add ACLs for both loading & publishing groups
			permsCmdBuilder.append(" && ") //$NON-NLS-1$
					.append(directory ? "setfacl -R" : "setfacl") //$NON-NLS-1$ //$NON-NLS-2$
					.append(" -m g:").append(loadingGroup).append(':') //$NON-NLS-1$
					.append(loadingGroupRights).append(" -m g:") //$NON-NLS-1$
					.append(publishingGroup).append(':').append(publishingGroupRights).append(' ').append(targetPathStr);
			// - execute the command
			try {
				ProcessUtils.launchAndWaitForProcess(new String[] { "/bin/bash", //$NON-NLS-1$
						"-c", permsCmdBuilder.toString() }); //$NON-NLS-1$
			} catch (Exception e) {
				// String msgKey = directory ? "maps_loader.stores_loader.change_store_perms_error" //$NON-NLS-1$
				// : "maps_loader.stores_loader.change_file_perms_error"; //$NON-NLS-1$
				LOG.error(e.getMessage(), e);
				throw new IOException(e.getMessage(), e);
				// Messages.getInstance().get(msgKey, targetPathStr), e);
			}
		}
	}

	/**
	 * Copy files located within some folder chosen by the user onto some target layer files storage (created by this method on purpose).
	 * 
	 * @param source
	 * The folder chosen by the user which contains data files to process.
	 * @param workspace
	 * The name of the versioned workspace to which processed data should be associated.
	 * @param storage
	 * The storage name under which processed data should be placed.
	 * @param acceptFile
	 * The function deciding whether or not some entry file found should be processed.
	 * @param acceptDir
	 * The function deciding whether or not some entire entry directory found should be processed.
	 * @return The path to the target layer files storage created.
	 * @throws LoaderException
	 * If anything goes wrong when processing.
	 */
	public Path copyData(Path source, String workspace, String storage, BiFunction<Path, BasicFileAttributes, Boolean> acceptFile, BiFunction<Path, BasicFileAttributes, Boolean> acceptDir)
			// throws LoaderException {
			throws BmapException {
		return processData(source, workspace, storage, NOOP_STORE_INIT, acceptFile, acceptDir, (sourceFile, targetDir) -> {
			Path currentRelativePath = source.relativize(sourceFile);
			Path resolvedPath = targetDir.resolve(currentRelativePath);
			Path parent = resolvedPath.getParent();
			try {
				if (!Files.exists(parent)) {
					createDirectory(parent, true);
				}
				// } catch (LoaderException e) {
			} catch (BmapException e) {
				if (e.getCause() != null && e.getCause() instanceof IOException) {
					throw (IOException) e.getCause();
				} else {
					IOException wrapperException = new IOException(e);
					throw wrapperException;
				}
			}
			copyFile(sourceFile, resolvedPath);
		});
	}

	/**
	 * Process files located within some folder chosen by the user to load on some target layer files storage (created by this method on purpose).
	 * 
	 * @param source
	 * The folder chosen by the user which contains data files to process.
	 * @param workspace
	 * The name of the versioned workspace to which processed data should be associated.
	 * @param storage
	 * The storage name under which processed data should be placed.
	 * @param initTargetStorage
	 * A function to run on the target storage prior to process entry files (may be {@link #NOOP_STORE_INIT}).
	 * @param acceptFile
	 * The function deciding whether or not some entry file found should be processed.
	 * @param acceptDir
	 * The function deciding whether or not some entire entry directory found should be processed.
	 * @param handleFile
	 * The function called upon any entry file acceptance.
	 * @return The path to the target layer files storage created.
	 * @throws LoaderException
	 * If anything goes wrong when processing.
	 */
	public Path processData(Path source, String workspace, String storage, InitStorageFunction initTargetStorage, BiFunction<Path, BasicFileAttributes, Boolean> acceptFile, BiFunction<Path, BasicFileAttributes, Boolean> acceptDir, HandleFileFunction handleFile) throws BmapException {
		// throws LoaderException {
		Path targetStore = createStore(workspace, storage);
		processData(source, targetStore, initTargetStorage, acceptFile, acceptDir, handleFile);
		return targetStore;
	}

	/**
	 * Process files located within some folder chosen by the user to load onto some existing target layer files storage (which path is provided).
	 * 
	 * @param source
	 * The folder chosen by the user which contains data files to process.
	 * @param target
	 * The target layer files storage root.
	 * @param initTargetStorage
	 * A function to run on the target storage prior to process entry files (may be {@link #NOOP_STORE_INIT}).
	 * @param acceptFile
	 * The function deciding whether or not some entry file found should be processed.
	 * @param acceptDir
	 * The function deciding whether or not some entire entry directory found should be processed.
	 * @param handleFile
	 * The function called upon any entry file acceptance.
	 * @throws LoaderException
	 * If anything goes wrong when processing.
	 */
	public void processData(Path source, Path target, InitStorageFunction initTargetStorage, BiFunction<Path, BasicFileAttributes, Boolean> acceptFile, BiFunction<Path, BasicFileAttributes, Boolean> acceptDir, HandleFileFunction handleFile) throws BmapException {
		// throws LoaderException {
		try {
			// Initialize the storage
			initTargetStorage.apply(target);

			// Run through the source directory to process files
			Files.walkFileTree(source, new SimpleFileVisitor<Path>() {

				@Override
				public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
					if (acceptDir.apply(dir, attrs)) {
						return super.preVisitDirectory(dir, attrs);
					} else {
						return FileVisitResult.SKIP_SUBTREE;
					}
				}

				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					if (acceptFile.apply(file, attrs)) {
						handleFile.apply(file, target);
					}
					return FileVisitResult.CONTINUE;
				}
			});

		} catch (Exception e) {
			// String errorMsg = Messages.getInstance().get(
			// "maps_loader.stores_loader.process_error", source, //$NON-NLS-1$
			// target);
			LOG.error(e.getMessage(), e);
			throw new BmapException(e.getMessage(), e);
		}
	}

}
