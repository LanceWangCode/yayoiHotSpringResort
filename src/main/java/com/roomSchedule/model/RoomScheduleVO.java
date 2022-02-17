package com.roomSchedule.model;

import java.sql.Date;

public class RoomScheduleVO {
	private Integer room_schedule_id; 
	private Integer room_type_id;
	private Date room_schedule_date;
	private Integer room_amount;
	private Integer room_rsv_booked;
	
	public Integer getRoom_schedule_id() {
		return room_schedule_id;
	}
	public void setRoom_schedule_id(Integer room_schedule_id) {
		this.room_schedule_id = room_schedule_id;
	}
	public Integer getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}
	public Date getRoom_schedule_date() {
		return room_schedule_date;
	}
	public void setRoom_schedule_date(Date room_schedule_date) {
		this.room_schedule_date = room_schedule_date;
	}
	public Integer getRoom_amount() {
		return room_amount;
	}
	public void setRoom_amount(Integer room_amount) {
		this.room_amount = room_amount;
	}
	public Integer getRoom_rsv_booked() {
		return room_rsv_booked;
	}
	public void setRoom_rsv_booked(Integer room_rsv_booked) {
		this.room_rsv_booked = room_rsv_booked;
	}
	

}
