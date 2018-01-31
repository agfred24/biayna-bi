package com.biayna.bi.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.biayna.bi.common.utility.FileUpload;

@Controller
public class UploadController {

	/*
	 * @RequestMapping("/hello") public ModelAndView hello() { String message =
	 * "Hello!"; return new ModelAndView("hello", "message", message); }
	 */
	
	
	private static Logger logger = LogManager.getLogger();

	/*@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("login", new User());
		return mav;
	}*/

	/*@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("upload") String str) {

		
		ModelAndView mav = null;
		File file = FileValidator.validateCSV();
		
		 if (null != logInUser) { 
			 mav = new ModelAndView("welcome");
			 mav.addObject("firstname", logInUser.getFirstname()); 
		} else { 
			mav = new ModelAndView("upload"); 
			mav.addObject("message", "Username or Password is wrong!!"); 
		}
		 
		return mav;
	}*/
	
	@RequestMapping(path="/upload")
	public String getUploadPage() {
		return "upload";
	}
	
	@RequestMapping(path="/uploadProcess", method=RequestMethod.POST)
	public String processFileUpload(@RequestParam("userFile") MultipartFile multipartFile, Model model, HttpServletRequest request){
		logger.info("Upload is processing...");
		
		/*if (!multipartFile.getOriginalFilename().equals("")) {
			FileUpload.uploadFile(request, multipartFile);
		}*/
		
		String fileName = multipartFile.getOriginalFilename();
		logger.info("filename: " + fileName);
				
		long size = multipartFile.getSize();
		logger.info("size: " + size);
		//String contextPath = context.getRealPath("/WEB-INF");
		//logger.info(contextPath);

		
		model.addAttribute("fileName", fileName);
		
		return "uploadProcess";
	}

	
}