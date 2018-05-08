package com.biayna.bi.domain.user.accounts;

public class RegistrationErrors {
	private String role;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;
	private String databaseError;
	
	public RegistrationErrors() {
		super();
	}

	public RegistrationErrors(String role, String firstName, String lastName, String email, String password,
			String confirmPassword, String databaseError) {
		super();
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.databaseError = databaseError;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getDatabaseError() {
		return databaseError;
	}

	public void setDatabaseError(String databaseError) {
		this.databaseError = databaseError;
	}

}
