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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.biayna.bi.common.utility.ReadConfiguration;
import com.biayna.bi.domain.user.accounts.PermissionChecker;


@Controller
@RequestMapping(path="/authentication")
@SessionAttributes({"firstName","authenticated","roleId","roleName"})
public class LoginController {
	
	private static Logger logger = LogManager.getLogger();
	
	@RequestMapping(path="/login", method=RequestMethod.GET)
	public String getLoginPage(final HttpServletRequest request) {	
		return "login";
	}

	
	@RequestMapping(path="/login", method=RequestMethod.POST)
	public ModelAndView handleLogin(@RequestParam("email") String email, @RequestParam("password") String password, 
			final RedirectAttributes redirectAttributes, final Model model, final HttpServletRequest request) {
		
		LoginVO credentials = new LoginVO(email, password);
		PermissionChecker validateCredentials = new PermissionCheckerImpl();
		ReadConfiguration objPropertiesFile = new ReadConfiguration();
		String loginError = objPropertiesFile.readKey("error.properties", "login.failed");
		

		User user = validateCredentials.checkCredentials(credentials);
		if (user==null) {
			model.addAttribute ("error", loginError);
			return new ModelAndView("login");
			//return "login";
		} else {
			/*redirectAttributes.addFlashAttribute("firstName", user.getFirstname());	
			redirectAttributes.addFlashAttribute("authenticated", true);
			redirectAttributes.addFlashAttribute("roleId", user.getRole().getRoleId());
			return "redirect:/index.html";	*/	
			model.addAttribute ("firstName", user.getFirstname());
			model.addAttribute ("authenticated", true);
			model.addAttribute ("roleId", user.getRole().getRoleId());
			model.addAttribute("roleName", user.getRole().getRoleName());
			
			RedirectView view = new RedirectView("/index.html", true);
			view.setExposeModelAttributes(false);
			return new ModelAndView(view);
		}
	}
}