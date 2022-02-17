package com.roomOrderList.model;

import java.sql.Date;
import java.util.List;

public class RoomOrderListService {
	
	private RoomOrderListDAO_interface dao;
	
	public RoomOrderListService() {
		dao = new RoomOrderListDAO();
	}
	
	public RoomOrderListVO addRoom_order_list(Integer room_id,Integer room_order_id,Integer number_of_people,
			Date arrival_date,Date departure_date,String special_req,Integer room_price,Integer service_order_id,Integer room_type_id) {
		RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
		
		roomOrderListVO.setRoom_id(room_id);
		roomOrderListVO.setRoom_order_id(room_order_id);
		roomOrderListVO.setNumber_of_people(number_of_people);
		roomOrderListVO.setArrival_date(arrival_date);
		roomOrderListVO.setDeparture_date(departure_date);
		roomOrderListVO.setSpecial_req(special_req);
		roomOrderListVO.setRoom_price(room_price);
		roomOrderListVO.setService_order_id(service_order_id);
		roomOrderListVO.setRoom_type_id(room_type_id);
		
		dao.insert(roomOrderListVO);
		
		return roomOrderListVO;		
	}
	
	public RoomOrderListVO updateRoom_order_list(Integer room_order_list_id,Integer room_id,Integer room_order_id,Integer number_of_people,
			Date arrival_date,Date departure_date,String special_req,Integer room_price,Integer service_order_id,Integer room_type_id) {
		RoomOrderListVO roomOrderListVO = new RoomOrderListVO();
		
		roomOrderListVO.setRoom_order_list_id(room_order_list_id);
		roomOrderListVO.setRoom_id(room_id);
		roomOrderListVO.setRoom_order_id(room_order_id);
		roomOrderListVO.setNumber_of_people(number_of_people);
		roomOrderListVO.setArrival_date(arrival_date);
		roomOrderListVO.setDeparture_date(departure_date);
		roomOrderListVO.setSpecial_req(special_req);
		roomOrderListVO.setRoom_price(room_price);
		roomOrderListVO.setService_order_id(service_order_id);
		roomOrderListVO.setRoom_type_id(room_type_id);
		
		dao.update(roomOrderListVO);
	
		return roomOrderListVO;		
	}
	
	public void deleteRoom_order_list(Integer room_order_list_id) {
		dao.delete(room_order_list_id);
	}
	
	public RoomOrderListVO getOneRoom_order_list(Integer room_order_list_id) {
		return dao.findByPrimaryKey(room_order_list_id);
	}
	
	public List<RoomOrderListVO> getall(){
		return dao.getAll();
	}

	
	
	
	
}
