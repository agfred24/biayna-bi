package com.biayna.bi.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"firstName","authenticated","roleId","roleName"})
public class IndexController {

	@RequestMapping({"", "/", "index"})
	public String getIndexPage(HttpServletRequest request) {		
		return "index";
	}
}
