package com.room.model;

public class RoomVO implements java.io.Serializable{
	private Integer room_id; 
	private Integer room_type_id;
	private Integer qtyofbeds;
	private String room_guest_name;
	private Boolean room_sale_status;
	private Integer room_status;
	
	
	public Integer getRoom_id() {
		return room_id;
	}
	public void setRoom_id(Integer room_id) {
		this.room_id = room_id;
	}
	public Integer getRoom_type_id() {
		return room_type_id;
	}
	public void setRoom_type_id(Integer room_type_id) {
		this.room_type_id = room_type_id;
	}
	public Integer getQtyofbeds() {
		return qtyofbeds;
	}
	public void setQtyofbeds(Integer qtyofbeds) {
		this.qtyofbeds = qtyofbeds;
	}
	public String getRoom_guest_name() {
		return room_guest_name;
	}
	public void setRoom_guest_name(String room_guest_name) {
		this.room_guest_name = room_guest_name;
	}
	public Boolean getRoom_sale_status() {
		return room_sale_status;
	}
	public void setRoom_sale_status(Boolean room_sale_status) {
		this.room_sale_status = room_sale_status;
	}
	public Integer getRoom_status() {
		return room_status;
	}
	public void setRoom_status(Integer room_status) {
		this.room_status = room_status;
	}
	
	
}
