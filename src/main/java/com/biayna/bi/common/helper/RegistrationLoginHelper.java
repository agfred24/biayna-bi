package com.biayna.bi.common.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.biayna.bi.common.utility.StringValidator;
import com.biayna.bi.domain.user.accounts.LoginVO;
import com.biayna.bi.domain.user.accounts.User;
import com.biayna.bi.domain.user.accounts.UserVO;

public class RegistrationLoginHelper {
	
	private static Logger logger = LogManager.getLogger();
	
	// usernames/userids are case insensitive
	public static boolean isUserCredentialsAcceptable(final LoginVO userCredentials) {

		String email = userCredentials.getEmail().trim();
		String password = userCredentials.getPassword();

		if (StringValidator.isEmptyOrNull(email)) {
			return false;
		} else if (StringValidator.isEmptyOrNull(password)) {
			return false;
		} else if (email.equals(password)) {
			return false;
		} else if (!StringValidator.isTenCharactersOrMore(password)) {
			return false;
		} else if (!StringValidator.isLessThan128Characters(password)) {
			return false;
		} else if (!StringValidator.isPassesWhiteList(password)) {
			return false;
		} else if (!StringValidator.isPasswordComplex(password)) {
			return false;
		} else if (StringValidator.hasMoreThanTwoConsecutiveIdenticalCharacters(userCredentials.getPassword())) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isUserRegistrationInformationAcceptable(final UserVO user) {

		LoginVO login = new LoginVO(user.getEmail(), user.getPassword());
		
		if (!RegistrationLoginHelper.isUserCredentialsAcceptable(login)) {
			return false;
		} else if (StringValidator.isEmptyOrNull(user.getFirstname())){
			return false;
		} else {
			return true;
		}			
	}
	
	public static User getUser(final UserVO userVO) {
		User userDTO = null;
		if (userVO != null) {
			userDTO = new User();
			userDTO.setEmail(userVO.getEmail());
			userDTO.setFirstname(userVO.getFirstname());
			userDTO.setLastname(userVO.getLastname());
			userDTO.setPhone(userVO.getPhone());
		} 
		return userDTO;
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
