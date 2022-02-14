package com.roomType.model;

import java.io.Serializable;

public class RoomTypeVO implements Serializable {
	private Integer room_type_id;
	private String room_type_name;
	private Integer room_type_amount;
	private String room_type_content;
	private boolean room_type_sale_status;
	private Integer room_total_person;
	private Integer room_total_score;
	private Integer room_type_price;
	
	public Integer getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}
	public String getRoom_type_name() {
		return room_type_name;
	}
	public void setRoom_type_name(String room_type_name) {
		this.room_type_name = room_type_name;
	}
	public Integer getRoom_type_amount() {
		return room_type_amount;
	}
	public void setRoom_type_amount(Integer room_type_amount) {
		this.room_type_amount = room_type_amount;
	}
	public String getRoom_type_content() {
		return room_type_content;
	}
	public void setRoom_type_content(String room_type_content) {
		this.room_type_content = room_type_content;
	}
	public boolean getRoom_type_sale_status() {
		return room_type_sale_status;
	}
	public void setRoom_type_sale_status(boolean room_type_sale_status) {
		this.room_type_sale_status = room_type_sale_status;
	}
	public Integer getRoom_total_person() {
		return room_total_person;
	}
	public void setRoom_total_person(Integer room_total_person) {
		this.room_total_person = room_total_person;
	}
	public Integer getRoom_total_score() {
		return room_total_score;
	}
	public void setRoom_total_score(Integer room_total_score) {
		this.room_total_score = room_total_score;
	}
	public Integer getRoom_type_price() {
		return room_type_price;
	}
	public void setRoom_type_price(Integer room_type_price) {
		this.room_type_price = room_type_price;
	}
	
	
	
	
	
	
}
