package com.biayna.bi.controller;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.biayna.bi.MQ.MLSHistoryDataConsumer;
import com.biayna.bi.MQ.MLSHistoryDataFileInfo;
import com.biayna.bi.MQ.Publisher;
import com.biayna.bi.common.exceptions.NetworkException;
import com.biayna.bi.common.utility.SystemTime;

/**
 * The Upload Controller maps two requests: 
 * 1. /upload path takes you to the upload.html to upload the file
 * 2. /uploadProcess accepts the uploaded file and sends to AMQP
 * 
 * @author Fred A.
 *
 */
@Controller
public class UploadController {
	
	private static Logger logger = LogManager.getLogger();
	private static final String UPLOAD_DIR = "uploads";
	
	@RequestMapping(path="/upload")
	public String getUploadPage() {
		return "upload";
	}
	
	@RequestMapping(path="/uploadProcess", method=RequestMethod.POST)
	public String processFileUpload(@RequestParam("userFile") MultipartFile multipartFile, Model model, HttpServletRequest request) throws IOException, ServletException, TimeoutException, NetworkException{
		
		// gets absolute path of the web application
		//String applicationPath = request.getServletContext().getRealPath("");
		String applicationPath = System.getProperty("catalina.home");
		
		// constructs path of the directory to save the uploaded file
		String uploadFilePath = new StringBuilder(applicationPath).append(File.separator).append(UPLOAD_DIR).toString();
		
		File fileSaveDir = new File(uploadFilePath);
		if (!fileSaveDir.exists()) {
			fileSaveDir.mkdirs();
		}
		logger.info("Upload File Directory="+fileSaveDir.getAbsolutePath());
		logger.info("Upload is processing...");
		
		/*if (!multipartFile.getOriginalFilename().equals("")) {
			FileUpload.uploadFile(request, multipartFile);
		}*/
		
				
		long size = multipartFile.getSize();
		logger.info("size: " + size);
		//String contextPath = context.getRealPath("/WEB-INF");
		//logger.info(contextPath);
		
		String currSystemDateTime = SystemTime.getCurrentSystemTime();
		
		String fileName = multipartFile.getOriginalFilename(); //extracting the file name
		int extensionPos = fileName.lastIndexOf(".");
		String newFileNameNoExt = (extensionPos == -1) ? fileName : fileName.substring(0, extensionPos);
		String extension = (extensionPos == -1) ? "" : fileName.substring(extensionPos, fileName.length());
		
		// We're creating new file names by concatenating the system mills to not override the files
		String newFileName = newFileNameNoExt + currSystemDateTime + extension;
		
		String path = uploadFilePath + File.separator + newFileName;
		logger.info("Uploaded time: " + currSystemDateTime); 
		logger.info("Original Uploaded filename: " + fileName); 
		logger.info("Uploaded filepath: " + path);
		
		 //Iterating the parts received from 'multipart/form-data' request
        for(Part part : request.getParts()){        	  		
    		part.write(path);           
        }
        
        // Construct a MLSHistoryDataFileInfo object for the Producer to process 
        MLSHistoryDataFileInfo fileInfo = new MLSHistoryDataFileInfo();
        fileInfo.setName(fileName);
        fileInfo.setPath(path);
        
        // Producer submits MLSHistoryDataFileInfo to process 
        Publisher producer = new Publisher("historyDataConsumerQueue");        
        producer.sendMessage(fileInfo);	
		producer.closeChannel();;
		producer.closeConnection();
		
		// MLSHistoryDataConsumer is a singleton,consequently, it will be initialize only once.  
		MLSHistoryDataConsumer.getInstance();
		
		// model is a map, we're putting new row <String, String> which will be accessed from JSP
		model.addAttribute("fileName", fileName);		
		return "uploadProcess";
	}
	
}