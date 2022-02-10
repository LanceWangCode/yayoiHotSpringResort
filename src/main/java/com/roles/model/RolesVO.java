package com.roles.model;

import java.io.Serializable;

public class RolesVO implements Serializable{
	private Integer roles_id;
	private String roles_name;
	
	public Integer getRoles_id() {
		return roles_id;
	}
	public void setRoles_id(Integer roles_id) {
		this.roles_id = roles_id;
	}
	public String getRoles_name() {
		return roles_name;
	}
	public void setRoles_name(String roles_name) {
		this.roles_name = roles_name;
	}
	
}
