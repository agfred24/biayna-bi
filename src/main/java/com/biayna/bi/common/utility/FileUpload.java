package com.biayna.bi.common.utility;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

public class FileUpload {
	private static final String ABC_PATH = "C:\\J2EE\\biayna-bi\\src\\main\\webapp\\WEB-INF\\assets\\csv\\";
	private static String REAL_PATH = "";
	
	private static Logger logger = LogManager.getLogger();
	
	public static void uploadFile(HttpServletRequest request, MultipartFile multipartFile) {

		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/csv/");
		logger.info(REAL_PATH);
		
		// to make sure that all the directories exists
		
		if(!new File(ABC_PATH).exists()) {
			// create a directory
			new File(ABC_PATH).mkdirs();
		}
		
		if(!new File(REAL_PATH).exists()) {
			// create a directory
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			// server upload
			multipartFile.transferTo(new File(REAL_PATH + multipartFile.getOriginalFilename() + System.currentTimeMillis() + ".csv"));
			
			// project directory upload
			multipartFile.transferTo(new File(ABC_PATH + multipartFile.getOriginalFilename() + System.currentTimeMillis() + ".csv"));
		} catch(IOException ex) {
			
		}
	}
}
