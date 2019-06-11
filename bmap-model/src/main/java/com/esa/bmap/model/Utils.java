package com.esa.bmap.model;

/**
 * Utils
 * 
 * @author QFAURE
 *
 */
public class Utils {

	/**
	 * Convert the given object to string with each line indented by 4 spaces (except the first line).
	 * 
	 * @param o
	 * @return
	 */
	public static String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
