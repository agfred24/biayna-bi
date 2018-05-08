package com.biayna.bi.common.utility;

public class FormatString {
	
	public static String capitalize(final String s) {
		String cap = s.trim();
		if (cap.length() == 0) {
			return cap;
		}
		return cap.substring(0, 1).toUpperCase() + cap.substring(1).toLowerCase();
	}
}
