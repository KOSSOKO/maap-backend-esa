package com.esa.bmap.service.acquisition.interfaces;

import com.esa.bmap.common.exceptions.BmapException;

public interface AcquisitionInterface {

		/**
		 * @param path
		 * Data directory path
		 * @param fileName
		 * Ground Campaign Name
		 * @param folderName
		 * Ground Campaign folder name
		 * @throws BmapException 
		 */
		void Main(String path, String fileName, String folderName) throws BmapException;
		

	
}
