package com.esa.bmap.common.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esa.bmap.common.Common;
import com.esa.bmap.common.exceptions.BmapException;

/**
 * Security
 * 
 * @author QFAURE
 *
 */
public class Security {

	private static final Logger LOG = LoggerFactory.getLogger(Security.class);

	private Security() {
		super();
	}

	/**
	 * Checks and returns the specified string if its length doesn't exceed 255 characters and if it only contains letters, numbers or spaces.
	 * 
	 * @param string
	 * @return
	 * @throws BmapException
	 */
	public static String checkString(String string) throws BmapException {
		if (string != null && string.length() <= 255 && string.matches("^[-_a-zA-Z0-9\\s]*$")) {
			
			//old pattern "^[-_a-zA-Z0-9\\s]*$"
			return string.toLowerCase();
		} else if (string == null) {
			return null;
		} else {
			LOG.warn(Common.getMessageValue("security.checkstring.failed"));
			throw new BmapException(Common.getMessageValue("security.checkstring.failed"));
		}
	}

	/**
	 * Checks and returns the specified string list with checkString().
	 * 
	 * @param stringList
	 * @return
	 * @throws BmapException
	 */
	public static List<String> checkStringList(List<String> stringList) throws BmapException {
		if (stringList != null) {
			for (String currentString : stringList) {
				checkString(currentString);
			}
		}
		return stringList;
	}
}
