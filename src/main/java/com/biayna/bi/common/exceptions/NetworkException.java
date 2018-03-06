package com.biayna.bi.common.exceptions;

/**
 * NetworkException represents a checked exception in this application.
 * During network connection, anything that can be fixed with user input 
 * and is not a system error will be wrapped and thrown as this type of exception.
 * 
 * Name: Fred A.
 * Date: 2018-03-05
 */

public class NetworkException extends Exception {

	private static final long serialVersionUID = 1L;

	public enum NetworkExceptionMessages {
		/**
		 * Validation errors when required values are not provided.
		 */
		ACCOUNT_VALUES_NOT_SET("Account values are not available!"),
		ENTRY_CONTAINS_NO_PIPE("Account entry does not contain pipe delimiter!"),
		HOST_AND_FOREIGN_KEYS_ARE_MISSING("Host name and Foreign keys are missing!");

		private final String message;

		/**
		 * Used to initialize the enumerator by its error message.
		 * 
		 * @param message denoting the type of error.
		 */
		NetworkExceptionMessages(final String message) {
			this.message = message;
		}

		/**
		 * Returns the enumeration code.
		 * 
		 * @return error message.
		 */
		public String getMessage() {
			return message;
		}
	}
	
	private NetworkExceptionMessages type;
	
	/**
	 * Empty constructor that calls the parent Exception empty constructor.
	 */
	public NetworkException() {
		super();
	}
	
	public NetworkException(final String desc, final NetworkExceptionMessages type) {
		super(desc);
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public NetworkExceptionMessages getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(NetworkExceptionMessages type) {
		this.type = type;
	}
	
	
}
