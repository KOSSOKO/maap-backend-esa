package com.esa.bmap.service.catalogue.interfaces;

import com.esa.bmap.common.exceptions.BmapException;

/**
 * Data reader service interface.
 * 
 * @author QFAURE
 *
 */
public interface DataReaderService {

	/**
	 * Reads through the official data directory and ingests all data.
	 * 
	 * @throws BmapException
	 */
	void readOfficialDataDirectory() throws BmapException;

	/**
	 * Reads through the given user data directory and ingests all data.
	 * 
	 * @param dataDirPath
	 * Path to the user data directory.
	 * @throws BmapException
	 */
	void readUserDataDirectory(String dataDirPath) throws BmapException;
}
