package com.biayna.bi.domain.user.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.biayna.bi.common.helper.RegistrationLoginHelper;

public class PermissionCheckerImpl implements PermissionChecker {

	private static Logger logger = LogManager.getLogger();
	

	public User checkCredentials(LoginVO credentials) {
		
		User userDTO = null;

		if (RegistrationLoginHelper.isUserCredentialsAcceptable(credentials)) {			
			UserDaoProcessor up = new UserDaoProcessor();
			userDTO = up.validateUser(credentials);
		}
		return userDTO;
	}

}
