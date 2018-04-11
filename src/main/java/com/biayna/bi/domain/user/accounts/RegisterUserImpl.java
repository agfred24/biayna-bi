package com.biayna.bi.domain.user.accounts;

import com.biayna.bi.common.helper.RegistrationLoginHelper;

public class RegisterUserImpl implements RegisterUser {
	

	
	@Override
	public User registerUser (UserVO user) {
		if (!RegistrationLoginHelper.isUserRegistrationInformationAcceptable(user)) {
			return null;
		} else {
			user.setPassword(RegistrationLoginHelper.encryptPassword(user.getPassword()));
		}
		
		UserDaoProcessor up = new UserDaoProcessor();
		if (up.registerUser(user)) {
			return RegistrationLoginHelper.getUser(user);
		} else {
			return null;
		}
	}
	
	
	public static void main(String...strings) {
		RegisterUser register = new RegisterUserImpl();
		
		UserVO u = new UserVO("agfred@ucla.edu", "Password7?","fred", "agourian", "8183035060");
		System.out.println(u.getEmail() + ": " + u.getPassword());
		User user = register.registerUser(u);
		System.out.println(u.getEmail() + ": " + u.getPassword()  + " length: " + u.getPassword().length());
		if (user!=null) {
			System.out.println("HELLO THERE " + user.getFirstname());
		} else {
			System.out.println("HELLO THERE: User is null.");
		}
		
		/*Login login = new Login("fredagourian", "password7?");
		User u = up.validateUser(login);
		System.out.println(u.getFirstname());*/
	}
	
}
