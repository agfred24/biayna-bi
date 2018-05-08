package com.biayna.bi.domain.user.accounts;

import java.time.LocalDateTime;

public class UserVO {
	private int roleId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String confirmPassword;
	private boolean enabled;
	private short loginAttempts;
	private LocalDateTime lastLogin;
	
	public UserVO() {
		super();
	}

	public UserVO(int roleId, String firstName, String lastName, String email, String password, String confirmPassword,
			boolean enabled, short loginAttempts, LocalDateTime lastLogin) {
		super();
		this.roleId = roleId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.enabled = enabled;
		this.loginAttempts = loginAttempts;
		this.lastLogin = lastLogin;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public short getLoginAttempts() {
		return loginAttempts;
	}

	public void setLoginAttempts(short loginAttempts) {
		this.loginAttempts = loginAttempts;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}