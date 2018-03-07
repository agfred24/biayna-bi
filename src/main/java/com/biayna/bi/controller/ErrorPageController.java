package com.biayna.bi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Error Page Controller maps to error: 
 * 1. /defaultError for HTTP 500 Internal Server Error
 * 2. /pageNotFound for HTTP 404 Not Found
 * 
 * @author Fred A.
 *
 */
@Controller
public class ErrorPageController {
	
	@RequestMapping(path="/internalServerError")
	public String getErrorPage() {
		return "internalServerError";
	}
	
	@RequestMapping(path="/pageNotFound")
	public String getPageNotFoundPage() {
		return "pageNotFound";
	}
}