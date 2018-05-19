package com.biayna.bi.web;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@WebFilter(urlPatterns = { "/*" }, description = "Session Checker Filter")
public class SessionCheckerFilter implements Filter {
	private FilterConfig config = null;

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		config.getServletContext().log("Initializing SessionCheckerFilter");
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws ServletException, IOException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		boolean isLoggedIn = false;
		/**
		 * Check to see if user's session attribute contains an attribute named
		 * "authenticated". If the attribute doesn't exist, redirect user to the login
		 * page.
		 **/
		if (request.getSession().getAttribute("authenticated") != null) {
			isLoggedIn = (boolean) request.getSession().getAttribute("authenticated");
		}
		String uri = request.getRequestURI();

		if (AuthorizationChecker.getInstance().isAuthorizationRequired(uri)) {

			if (!isLoggedIn) {
				if (!uri.endsWith("login.html")) {
					response.sendRedirect(request.getContextPath() + "/authentication/login.html");
				}
			} else {
				if (!uri.endsWith("login.html")) {
					if (!uri.endsWith("logout.html")) {
						// Check to see if the user has a permission to access the requested page.
						if (request.getSession().getAttribute("roleId") != null) {
							int roleId = (Integer) request.getSession().getAttribute("roleId");
							if (!AuthorizationChecker.getInstance().isAuthorized(roleId, uri)) {
								response.sendRedirect(request.getContextPath() + "/pageNotFound.html");
							}
						} else {
							response.sendRedirect(request.getContextPath() + "/authentication/login.html");
						}
					}
				} else {
					response.sendRedirect(request.getContextPath() + "/index.html");
				}
			}
		}
		chain.doFilter(req, res);
	}

	public void destroy() {
		config.getServletContext().log("Destroying SessionCheckerFilter");
	}
}