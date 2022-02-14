package com.roomType.model;

import java.util.List;

import com.roomType.*;


public class RoomTypeService {
	private	RoomTypeDAO_interface dao;
	
	public RoomTypeService() {
		dao = new RoomTypeDAO();
	}
	
	public RoomTypeVO addRoomType(String room_type_name,Integer room_type_amount,String room_type_content,boolean room_type_sale_status,Integer room_total_person,Integer room_total_score,Integer room_type_price) {
		RoomTypeVO roomTypeVO = new RoomTypeVO();
		
		roomTypeVO.setRoom_type_name(room_type_name);
		roomTypeVO.setRoom_type_amount(room_type_amount);
		roomTypeVO.setRoom_type_content(room_type_content);
		roomTypeVO.setRoom_type_sale_status(room_type_sale_status);
		roomTypeVO.setRoom_total_person(room_total_person);
		roomTypeVO.setRoom_total_score(room_total_score);
		roomTypeVO.setRoom_type_price(room_type_price);
		int id = dao.insert(roomTypeVO);
		roomTypeVO.setRoom_type_id(id);
		return roomTypeVO;
	}
	
	public RoomTypeVO updateRoomType(Integer room_type_id,String room_type_name,Integer room_type_amount,String room_type_content,boolean room_type_sale_status,Integer room_total_person,Integer room_total_score,Integer room_type_price) {

		RoomTypeVO roomTypeVO = new RoomTypeVO();
		
		roomTypeVO.setRoom_type_id(room_type_id);
		roomTypeVO.setRoom_type_name(room_type_name);
		roomTypeVO.setRoom_type_amount(room_type_amount);
		roomTypeVO.setRoom_type_content(room_type_content);
		roomTypeVO.setRoom_type_sale_status(room_type_sale_status);
		roomTypeVO.setRoom_total_person(room_total_person);
		roomTypeVO.setRoom_total_score(room_total_score);
		roomTypeVO.setRoom_type_price(room_type_price);
		dao.update(roomTypeVO);

		return roomTypeVO;
	}

	public void deleteRoomType(Integer room_type_id) {
		dao.delete(room_type_id);
	}

	public RoomTypeVO getOneRoomType(Integer room_type_id) {
		return dao.findByPK(room_type_id);
	}

	public List<RoomTypeVO> getAll() {
		return dao.getAll();
	}
}
