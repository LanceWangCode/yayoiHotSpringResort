package com.roomOrder.model;

import java.sql.Date;

public class RoomOrderVO {
	private Integer room_order_id; 
	private Integer mem_id;
	private Date order_date;
	private Integer room_order_status;
	private Integer room_charge;
	private Integer room_review;
	
	
	public Integer getRoom_order_id() {
		return room_order_id;
	}
	public void setRoom_order_id(Integer room_order_id) {
		this.room_order_id = room_order_id;
	}
	public Integer getMem_id() {
		return mem_id;
	}
	public void setMem_id(Integer mem_id) {
		this.mem_id = mem_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Integer getRoom_order_status() {
		return room_order_status;
	}
	public void setRoom_order_status(Integer room_order_status) {
		this.room_order_status = room_order_status;
	}
	public Integer getRoom_charge() {
		return room_charge;
	}
	public void setRoom_charge(Integer room_charge) {
		this.room_charge = room_charge;
	}
	public Integer getRoom_review() {
		return room_review;
	}
	public void setRoom_review(Integer room_review) {
		this.room_review = room_review;
	}
	
	

}
