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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.biayna.bi.common.helper.RegistrationLoginHelper;
import com.biayna.bi.common.utility.ReadConfiguration;
import com.biayna.bi.common.utility.StringValidator;


@Controller
@RequestMapping(path="/authentication")
@SessionAttributes({"firstName","authenticated","roleId","roleName"})
public class RegistrationController {
	
	private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(path="/registration", method=RequestMethod.GET)
	public String getRegistrationdPage() {
		return "registration";
	}
	
	@RequestMapping(path="/registration", method=RequestMethod.POST)
	public String processRegistration(@RequestParam("roleId") int roleId, @RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
			@RequestParam("email") String email, @RequestParam("password") String password, 
			@RequestParam("confirmPassword") String confirmPassword, Model model, HttpServletRequest request) {
		
		UserVO user = new UserVO(roleId, firstName, lastName, email, password, confirmPassword, true, (short)0, null);
		RegistrationErrors errors = null;
		errors = RegistrationLoginHelper.isUserRegistrationInformationAcceptable(user);
		if (errors==null) {
			RegisterUser register = new RegisterUserImpl();
			errors = register.registerUser(user);
			
			if (errors==null) {			
				model.addAttribute("isRegistered", true);
			} else {
				errors.setEmail(" ");
				errors.setPassword(" ");
				errors.setConfirmPassword(" ");
				model.addAttribute ("user", user);
				model.addAttribute ("errors", errors);
			}
		} else {
			if (StringValidator.isEmptyOrNull(errors.getPassword())){
				errors.setPassword(" ");
				errors.setConfirmPassword(" ");
			}
			
			model.addAttribute ("user", user);
			model.addAttribute ("errors", errors);
		}
		return "registration";
	}
}