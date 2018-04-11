package com.biayna.bi.domain.user.accounts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.biayna.bi.common.helper.RegistrationLoginHelper;

public class PermissionCheckerImpl implements PermissionChecker {

	private static Logger logger = LogManager.getLogger();
	
	@Override
	public User checkCredentials(LoginVO credentials) {
		UserVO userVO = null;
		User userDTO = null;
		if (RegistrationLoginHelper.isUserCredentialsAcceptable(credentials)) {
			
			UserDaoProcessor up = new UserDaoProcessor();
			userVO = up.validateUser(credentials);
			if (userVO == null) {
				return null;
			} else if (userVO.getPassword() == null || userVO.getPassword().isEmpty()) {
				return null;
			} else if (RegistrationLoginHelper.isCorrectPassword(credentials.getPassword(), userVO.getPassword())) {
				userDTO = RegistrationLoginHelper.getUser(userVO);
			} 
		}
		return userDTO;
	}

	
	public static void main(String...strings) {
		PermissionChecker login = new PermissionCheckerImpl();
		
		LoginVO l = new LoginVO("agfred@ucla.edu", "Password7?");
		User user = login.checkCredentials(l);
		if (user!=null) {
			System.out.println("HELLO THERE " + user.getFirstname());
		} else {
			System.out.println("HELLO THERE: User is null.");
		}
	}
	
}
