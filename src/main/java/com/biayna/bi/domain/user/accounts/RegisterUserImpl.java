package com.biayna.bi.domain.user.accounts;

import com.biayna.bi.common.helper.RegistrationLoginHelper;
import com.biayna.bi.common.utility.FormatString;

public class RegisterUserImpl implements RegisterUser {
	
	@Override
	public RegistrationErrors registerUser (UserVO user) {
		
		user.setPassword(RegistrationLoginHelper.encryptPassword(user.getPassword()));
		user.setEmail(user.getEmail().toLowerCase().trim());
		user.setFirstName(FormatString.capitalize(user.getFirstName()));
		user.setLastName(FormatString.capitalize(user.getLastName()));
		UserDaoProcessor up = new UserDaoProcessor();
		
		return up.registerUser(user);
	}
}
