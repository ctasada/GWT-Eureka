package com.blogspot.ctasada.gwt.eureka.client.utils;

/**
 * This class contains general methods to work with Strings.
 * 
 * @author Carlos Tasada
 *
 */
public final class StringUtils {

	private StringUtils() {
	}

	/**
	 * This method returns an String where the ingested value is repeated n times.
	 * @param s Input String
	 * @param n Number of times is repeated
	 * @return new String with the parameter repeated
	 */
	public static String repeat(String s, int n) {
		if (s == null) {
			return null;
		}
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(s);
		}
		return sb.toString();
	}
}
