package com.biayna.bi.domain.user.accounts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(path="/authentication")
public class LogoutController {
	
	private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(path="/logout", method=RequestMethod.GET)
	public String handleLogout(final HttpServletRequest request) {
		
			HttpSession session = request.getSession();
			session.removeAttribute("firstName");
			session.removeAttribute("authenticated");
			session.removeAttribute("roleId");
			session.removeAttribute("roleName");
			session.invalidate();			
				
			return "logout";
	}

}