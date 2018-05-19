package com.biayna.bi.web;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AuthorizationChecker {

	private static AuthorizationChecker instance;

	private AuthorizationChecker() {
	}

	public static AuthorizationChecker getInstance() {
		if (instance == null) {
			instance = new AuthorizationChecker();
		}
		return instance;
	}

	private String[] adminAccessPages = { "", "index.html", "registration.html" };
	private String[] producerAccessPages = { "", "index.html" };
	private String[] userAccessPages = { "", "index.html" };
	private String[] noAuthList = { "pageNotFound.html", "internalServerError.html" };

	protected boolean isAuthorized(int roleId, String uri) {
		String requestedPageName = uri.substring(uri.lastIndexOf("/") + 1);

		if (roleId == 3) {
			for (String page : adminAccessPages) {
				if (page.equals(requestedPageName)) {
					return true;
				}
			}
		} else if (roleId == 2) {
			for (String page : producerAccessPages) {
				if (page.equals(requestedPageName)) {
					return true;
				}
			}
		} else if (roleId == 1) {
			for (String page : userAccessPages) {
				if (page.equals(requestedPageName)) {
					return true;
				}
			}
		}

		return false;
	}

	protected boolean isAuthorizationRequired(String uri) {
		Set<String> pathAttributes = new HashSet<>(Arrays.asList(uri.split("/")));
		for (String page : noAuthList) {
			if (pathAttributes.contains(page)) {
				return false;
			}
		}
		return true;
	}
}
