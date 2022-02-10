package com.rolesAccessRight.model;

import java.io.Serializable;

public class RolesAccessRightVO implements Serializable{
	private Integer roles_id;
	private Integer function_id;
	
	public Integer getRoles_id() {
		return roles_id;
	}
	public void setRoles_id(Integer roles_id) {
		this.roles_id = roles_id;
	}
	public Integer getFunction_id() {
		return function_id;
	}
	public void setFunction_id(Integer function_id) {
		this.function_id = function_id;
	}
	
}
