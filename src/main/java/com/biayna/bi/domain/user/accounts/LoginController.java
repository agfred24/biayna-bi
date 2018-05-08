package com.biayna.bi.domain.user.accounts;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biayna.bi.common.utility.ReadConfiguration;
import com.biayna.bi.domain.user.accounts.PermissionChecker;


@Controller
@RequestMapping(path="/authentication")
@SessionAttributes({"firstName","authenticated","role"})
public class LoginController {
	
	private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String getLogindPage() {
		return "login";
	}
	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public String handleLogin(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpServletRequest request) {
		
		LoginVO credentials = new LoginVO(email, password);
		PermissionChecker validateCredentials = new PermissionCheckerImpl();
		ReadConfiguration objPropertiesFile = new ReadConfiguration();
		String loginError = objPropertiesFile.readKey("error.properties", "login.failed");
		

		User user = validateCredentials.checkCredentials(credentials);
		if (user==null) {
			model.addAttribute ("error", loginError);
			return "login";
		} else {
			model.addAttribute("firstName", user.getFirstname());	
			model.addAttribute("authenticated", true);
			model.addAttribute("role", user.getRole().getRoleName());
			return "index";			
		}
	}
}