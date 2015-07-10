package com.goeuro.test;

public class StringUtils {

	/**
	 * Checks if the specified string object is null, or empty, or whitespace.
	 * @param str String to check.
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		
		return false;
	}
}
