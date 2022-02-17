package com.roomOrderList.model;

import java.sql.Date;

public class RoomOrderListVO {
	private Integer room_order_list_id; 
	private Integer room_id;
	private Integer room_order_id;
	private Integer number_of_people;
	private Date arrival_date;
	private Date departure_date;
	private String special_req;
	private Integer room_price;
	private Integer service_order_id;
	private Integer room_type_id;
	
	public Integer getRoom_order_list_id() {
		return room_order_list_id;
	}
	public void setRoom_order_list_id(Integer room_order_list_id) {
		this.room_order_list_id = room_order_list_id;
	}
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public Integer getRoom_order_id() {
		return room_order_id;
	}
	public void setRoom_order_id(Integer room_order_id) {
		this.room_order_id = room_order_id;
	}
	public Integer getNumber_of_people() {
		return number_of_people;
	}
	public void setNumber_of_people(Integer number_of_people) {
		this.number_of_people = number_of_people;
	}
	public Date getArrival_date() {
		return arrival_date;
	}
	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}
	public Date getDeparture_date() {
		return departure_date;
	}
	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}
	public String getSpecial_req() {
		return special_req;
	}
	public void setSpecial_req(String special_req) {
		this.special_req = special_req;
	}
	public Integer getRoom_price() {
		return room_price;
	}
	public void setRoom_price(Integer room_price) {
		this.room_price = room_price;
	}
	public Integer getService_order_id() {
		return service_order_id;
	}
	public void setService_order_id(Integer service_order_id) {
		this.service_order_id = service_order_id;
	}
	public Integer getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}
	
	
	
	
	
}
