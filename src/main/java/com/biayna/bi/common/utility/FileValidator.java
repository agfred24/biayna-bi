package com.biayna.bi.common.utility;


import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class FileValidator {

	private static final Logger logger = LogManager.getLogger(FileValidator.class);
	/**
	 * Private constructor to prevent instantiation
	 */
	private FileValidator() {
		// empty constructor
	}
	
	public static File validateCSV(File f) {
		return f;
		
	}
}
