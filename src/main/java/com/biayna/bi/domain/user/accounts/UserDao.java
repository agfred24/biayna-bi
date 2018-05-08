package com.biayna.bi.domain.user.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDao {
	
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "firstname",  nullable = false, unique=false)
	private String firstname;
	
	@Column(name = "lastname", nullable = true, unique=false)
	private String lastname;
	
	@OneToOne(mappedBy="userDao")
	private UserAccountDao userAccountDao;
	
	@ManyToOne
	@JoinColumn(name="role_id", referencedColumnName="role_id")
	private RoleDao role;
	
	// Hibernate requires a default constructor for entity
	public UserDao() {}
	
	

	public UserDao(String firstname, String lastname, RoleDao role) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.role = role;
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public UserAccountDao getUserAccountDao() {
		return userAccountDao;
	}

	public void setUserAccountDao(UserAccountDao userAccountDao) {
		this.userAccountDao = userAccountDao;
	}

	public RoleDao getRole() {
		return role;
	}

	public void setRole(RoleDao role) {
		this.role = role;
	}

}
