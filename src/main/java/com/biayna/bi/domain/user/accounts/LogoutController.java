package com.biayna.bi.domain.user.accounts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LogoutController {
	
	private static Logger logger = LogManager.getLogger();
	private static final String UPLOAD_DIR = "uploads";
	
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String processFileUpload(HttpServletRequest request) {
		
			HttpSession session = request.getSession();
			session.removeAttribute("userName");
			session.invalidate();			
		
		return "login";
	}
}