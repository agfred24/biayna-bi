package com.biayna.bi.common.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReadHTMLTemplates {
	    private ClassLoader objClassLoader = null;
	    private static final String directory = "HTMLTemplates";
	    private static Logger logger = LogManager.getLogger();
	    
	    public ReadHTMLTemplates() {}
	    
	    /**
	     * Reads HTML Template
	     * 
	     * @return
	     * @throws URISyntaxException 
	     */
		public String readFileUploadEmailTemplate(String fileName) throws URISyntaxException {
			StringBuilder html = new StringBuilder();
			if (fileName != null && !fileName.trim().isEmpty()) {
				objClassLoader = getClass().getClassLoader();
				File file = new File(
						objClassLoader.getResource(directory + File.separator + "fileUploadEmail.html").toURI());
				Path path = file.toPath();
	
				try (BufferedReader reader = Files.newBufferedReader(path, Charset.forName("UTF-8"))) {
					String line = null;
					while ((line = reader.readLine()) != null) {
						if (line.contains("$fileName")) {
							html.append(line.replace("$fileName", fileName));
						} else {
							html.append(line);
						}
	
					}
				} catch (IOException x) {
					logger.error("ERROR: Email HTML page has NOT been generated for this file: " + fileName);
				}
	
			}
			logger.info("Email HTML page has been generated for this file: " + fileName);
			return html.toString();
		}
	    
}
