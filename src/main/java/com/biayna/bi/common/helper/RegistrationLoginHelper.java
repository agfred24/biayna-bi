package com.biayna.bi.common.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.biayna.bi.common.utility.ReadConfiguration;
import com.biayna.bi.common.utility.StringValidator;
import com.biayna.bi.domain.user.accounts.LoginVO;
import com.biayna.bi.domain.user.accounts.RegistrationErrors;
import com.biayna.bi.domain.user.accounts.User;
import com.biayna.bi.domain.user.accounts.UserVO;

public class RegistrationLoginHelper {
	
	private static Logger logger = LogManager.getLogger();
			
	// usernames/userids are case insensitive
	public static boolean isUserCredentialsAcceptable(final LoginVO userCredentials) {

		String email = userCredentials.getEmail().trim();
		String password = userCredentials.getPassword();
				
		if (isEmailValid(email) && isPasswordValid(email, password)) {
			return true;
		} else {
			return false;
		}		
	}
	
	private static boolean isEmailValid(final String email) {
		if (StringValidator.isEmptyOrNull(email)) {
			return false;
		} else {
			return true;
		}
	}
	
	private static String validateEmailAddressSyntax (final String email) {
		ReadConfiguration objPropertiesFile = new ReadConfiguration();
		String errorMessage = "";
		if (StringValidator.isEmptyOrNull(email)) {
			errorMessage = objPropertiesFile.readKey("error.properties", "registration.email.empty");
		} else if (!StringValidator.isValidEmailAddress(email)) {
			errorMessage = objPropertiesFile.readKey("error.properties", "registration.email.isNotValid");
		}		
		return errorMessage;
	}
	
	private static boolean isPasswordValid (final String email, final String password) {
		boolean isPassValid = false;
		if (StringValidator.isEmptyOrNull(password)) {
			isPassValid = false;
		} else if (email.equals(password)) {
			isPassValid = false;
		} else if (!StringValidator.isTenCharactersOrMore(password)) {
			isPassValid = false;
		} else if (!StringValidator.isLessThan128Characters(password)) {
			isPassValid = false;
		} else if (!StringValidator.isPassesWhiteList(password)) {
			isPassValid = false;
		} else if (!StringValidator.isPasswordComplex(password)) {
			isPassValid = false;
		} else if (StringValidator.hasMoreThanTwoConsecutiveIdenticalCharacters(password)) {
			isPassValid = false;
		} else {
			isPassValid = true;
		}
		
		return isPassValid;
	}
	
	public static RegistrationErrors isUserRegistrationInformationAcceptable(final UserVO user) {
		ReadConfiguration objPropertiesFile = new ReadConfiguration();
		RegistrationErrors errors = new RegistrationErrors();
		boolean isRegInfoValid = true;
		
		String email = validateEmailAddressSyntax(user.getEmail());
		if (!StringValidator.isEmptyOrNull(email)) {
			errors.setEmail(email);
			isRegInfoValid = false;
		}
		if (!isPasswordValid(user.getEmail(), user.getPassword())) {
			errors.setPassword(objPropertiesFile.readKey("error.properties", "registration.password"));
			errors.setConfirmPassword(" ");
			isRegInfoValid = false;
		}
		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.setConfirmPassword(objPropertiesFile.readKey("error.properties", "registration.confirmPassword"));
			if (StringValidator.isEmptyOrNull(errors.getPassword())){
				errors.setPassword(" ");
			}
			isRegInfoValid = false;
		}
		if (!(user.getRoleId()>0 && user.getRoleId()<4)){
			errors.setRole(objPropertiesFile.readKey("error.properties", "registration.role.notSelected"));
			isRegInfoValid = false;
		} 
		if (StringValidator.isEmptyOrNull(user.getFirstName())){
			errors.setFirstName(objPropertiesFile.readKey("error.properties", "registration.firstName.empty"));
			isRegInfoValid = false;
		} 
		if (StringValidator.isEmptyOrNull(user.getLastName())){
			errors.setLastName(objPropertiesFile.readKey("error.properties", "registration.lastName.empty"));
			isRegInfoValid = false;
		} 
		
		if (isRegInfoValid) {
			return null;
		} else {
			return errors;
		}			
	}
	
	
	public static boolean isCorrectPassword(final String plaintextPass, final String password) {
		boolean isMatched = false;
		try {
			byte[] bPlaintextPass = plaintextPass.getBytes(StandardCharsets.UTF_8);
			String salt = password.substring(0, 22);
			byte[] bSalt = Base64.getDecoder().decode(salt);

			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(bPlaintextPass);
			md.update(bSalt);
			byte[] hash = md.digest();
			String hashPassword = salt + Base64.getEncoder().encodeToString(hash);
			if (hashPassword.equals(password)) {
				isMatched = true;
			} else {
				isMatched = false;
			}
		} catch (NoSuchAlgorithmException e) {
			logger.error("isCorrectPassword: NoSuchAlgorithmException... ");
			e.printStackTrace();
		}
		return isMatched;
	}
	
	public static String encryptPassword(final String password) {
		String encryptedPassword = null;
		try {
			byte[] bPlaintextPass = password.getBytes(StandardCharsets.UTF_8);
			String salt = getSalt();
			byte[] bSalt = Base64.getDecoder().decode(salt);

			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(bPlaintextPass);
			md.update(bSalt);
			byte[] hash = md.digest();
			System.out.println(salt);
			encryptedPassword = salt + Base64.getEncoder().encodeToString(hash);
			System.out.println(encryptedPassword);
			
		} catch (NoSuchAlgorithmException e) {
			logger.error("Encrypt: NoSuchAlgorithmException... ");
			e.printStackTrace();
		}
		return encryptedPassword;
	}
	
	private static String getSalt() throws NoSuchAlgorithmException {
		byte[] bSalt = new byte[16];
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		random.nextBytes(bSalt);
		String origSalt = Base64.getEncoder().encodeToString(bSalt);
		// Salt is 22 char long
		String salt = origSalt.substring(0, 22);
		return salt;
	}
	
	/*public static void main (String...strings) throws NoSuchAlgorithmException {
		UserVO user1 = new UserVO("", "password", null, null, null);
		user1 = encryptPassword(user1);
		
		UserVO user2 = new UserVO(null, "password", null, null, null);
		user2 = encryptPassword(user2);
		
		if (isCorrectPassword("password", user1.getPassword())) {
			System.out.println("TRUE");
		} else {
			System.out.println("FALSE");
		}	
	}*/
}
