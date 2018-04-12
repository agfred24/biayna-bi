package com.biayna.bi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping({"", "/", "index"})
	public String getIndexPage(HttpServletRequest request, HttpSession session) {
		return "index";
	}
}
