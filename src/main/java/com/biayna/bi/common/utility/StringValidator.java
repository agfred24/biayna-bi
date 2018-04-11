package com.biayna.bi.common.utility;

import java.util.HashSet;
import java.util.Set;

public class StringValidator {

	/**
	 * May include the following characters: 
	 * a-z A-Z 0-9 @ + ! ^ ? ~ _
	 */
	private static final String SPECIALCHAR = "@+!^?~_";
	
	private static Set<Character> validCharacters() {
		
		char[] specialCharArr = SPECIALCHAR.toCharArray();
		Set<Character> specialCharSet = new HashSet<>();
		for (char c : specialCharArr) {
			specialCharSet.add(c);
		}
		return specialCharSet;
	}


	
	public static boolean isEmptyOrNull(final String s) {
		return s == null || s.trim().isEmpty();
	}
	
	/*
	*	TO-DO
	*	Cannot reuse your 3 prior passwords.
	*	Must be different from your User ID.
	*/
	
	/**
	*	Contains at least 10 characters
	**/
	public static boolean isTenCharactersOrMore(String input) {
		if (input.length()>9) {
			return true;
		}
		return false;
	}

	/**
	*	Contains at most 128 characters
	**/
	public static boolean isLessThan128Characters(String input) {
		if (input.length()>128) {
			return false;
		}
		return true;
	}
	
	/**
	*	not more than 2 identical characters in a row (e.g., 111 not allowed)
	**/
	public static boolean hasMoreThanTwoConsecutiveIdenticalCharacters(String input) {
		
		int count = 1;
		char[] inputCh = input.toCharArray();
		for (int i=1; i<inputCh.length; i++) {
			if (count==3) {
				return true;
			}
			if (inputCh[i-1]==inputCh[i]) {
				count++;
			} else {
				count = 1;
			}
		}
		return false;
	}

	/**
	*	WHITELIST = "[a-zA-Z0-9@+!^?~_]{10,}";
	**/
	public static boolean isPassesWhiteList (String input) {
		
		Set<Character> specialCharSet = validCharacters();
		char[] inputCh = input.toCharArray();
		for (char c : inputCh) {
			if (Character.isLetterOrDigit(c)) {
				continue;
			} else if (specialCharSet.contains(c)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	
	/**
	*	at least 1 uppercase character (A-Z)
	*	at least 1 lowercase character (a-z)
	*	at least 1 digit (0-9)
	*	at least 1 special character (punctuation) — do not forget to treat space as special characters too
	**/
	public static boolean isPasswordComplex(String input) {
		boolean isLowerCase = false, isUpperCase = false, isDigit = false, isSpecialChar = false;
		
		
		Set<Character> specialCharSet = validCharacters();
		
		char[] inputCh = input.toCharArray();
		for (int i=0; i<inputCh.length; i++) {
			if (Character.isUpperCase(inputCh[i])) {
				isUpperCase = true;
			} else if (Character.isLowerCase(inputCh[i])){
				isLowerCase = true;
			} else if (Character.isDigit(inputCh[i])) {
				isDigit = true;
			} else if (specialCharSet.contains(inputCh[i])) {
				isSpecialChar = true;
			}
		}

		return isLowerCase && isUpperCase && isDigit && isSpecialChar;
	}
	
		
	public static void main(String...strings) {

		//System.out.println(isPassesWhiteList(".3sdfsd123"));
		/*long start_time = System.currentTimeMillis();
		System.out.println(isPassesWhiteList("0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ0K@9o3ralksdjf@+!^?~_kFDLIJ"));
		long end_time = System.currentTimeMillis();
		long difference = end_time-start_time;
		*/
		System.out.println(hasMoreThanTwoConsecutiveIdenticalCharacters("aa;lsdfjklajss;;;kj"));
		
		//System.out.println(difference);
		
	}
}
