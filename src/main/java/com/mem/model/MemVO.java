package com.mem.model;

import java.io.Serializable;

public class MemVO implements Serializable{
	private Integer mem_id;
	private String mem_email;
	private String mem_password;
	private String mem_name;
	private String mem_phone;
	private String mem_address;
	private byte[] mem_pic;
	private Byte mem_status;
	
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_password() {
		return mem_password;
	}
	public void setMem_password(String mem_password) {
		this.mem_password = mem_password;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_phone() {
		return mem_phone;
	}
	public void setMem_phone(String mem_phone) {
		this.mem_phone = mem_phone;
	}
	public String getMem_address() {
		return mem_address;
	}
	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}
	public byte[] getMem_pic() {
		return mem_pic;
	}
	public void setMem_pic(byte[] mem_pic) {
		this.mem_pic = mem_pic;
	}
	public Byte getMem_status() {
		return mem_status;
	}
	public void setMem_status(Byte mem_status) {
		this.mem_status = mem_status;
	}
	
}
