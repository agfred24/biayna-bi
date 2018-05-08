package com.biayna.bi.domain.user.accounts;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

import javax.management.relation.Role;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_account")
public class UserAccountDao {
	@Id
	private String email;
	private String password;
	private boolean enabled;
	@Column(name = "login_attempts")
	private short loginAttempts;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column(name = "last_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;
	
	@Column(name = "last_updated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;
	
	@OneToOne (cascade= {CascadeType.PERSIST})
	@JoinColumn(name="user_id", referencedColumnName="user_id", unique=true)
	private UserDao userDao;
	
	// Hibernate requires a default constructor for entity
	public UserAccountDao() {}

	public UserAccountDao(String email, String password, boolean enabled, short loginAttempts, Date created,
			Date lastLogin, Date lastUpdated, UserDao userDao) {
		super();
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.loginAttempts = loginAttempts;
		this.created = created;
		this.lastLogin = lastLogin;
		this.lastUpdated = lastUpdated;
		this.userDao = userDao;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
