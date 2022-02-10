package com.emplyee.model;

import java.io.Serializable;

public class EmplyeeVO implements Serializable {
	private Integer empno;
	private Integer roles_id;
	private String emp_name;
	private String emp_password;
	private Boolean emp_status;
	
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public Integer getRoles_id() {
		return roles_id;
	}
	public void setRoles_id(Integer roles_id) {
		this.roles_id = roles_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public Boolean getEmp_status() {
		return emp_status;
	}
	public void setEmp_status(Boolean emp_status) {
		this.emp_status = emp_status;
	}
	

}
