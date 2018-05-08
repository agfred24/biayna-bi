package com.biayna.bi.domain.user.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_role")
public class RoleDao {

	@Id
	@Column(name = "role_id")
	private int roleId;
	
	@Column(name = "role_name", nullable = false, unique=true)
	private String role;
	
	public RoleDao() {}

	public RoleDao(int roleId, String role) {
		super();
		this.roleId = roleId;
		this.role = role;
	}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
