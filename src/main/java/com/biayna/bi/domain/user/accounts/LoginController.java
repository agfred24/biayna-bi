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

import com.biayna.bi.common.utility.ReadConfiguration;


@Controller
public class LoginController {
	
	private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(path="/login")
	public String getLogindPage() {
		return "login";
	}
	
	@RequestMapping(path="/loginProcess", method=RequestMethod.POST)
	public String processLogin(@RequestParam("userName") String username, @RequestParam("password") String password, Model model, HttpServletRequest request) {
		
		LoginVO credentials = new LoginVO(username, password);
		PermissionChecker validateCredentials = new PermissionCheckerImpl();
		ReadConfiguration objPropertiesFile = new ReadConfiguration();
		String loginError = objPropertiesFile.readKey("error.properties", "login.failed");
		

		User user = validateCredentials.checkCredentials(credentials);
		if (user==null) {
			model.addAttribute ("error", loginError);
			return "login";
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("userName", user.getFirstname());
			return "index";			
		}
	}
}