package com.roomTypePic.model;

import java.util.List;


public class RoomTypePicService {

	private RoomTypePicDAO_interface dao;

	public RoomTypePicService() {
		dao = new RoomTypePicDAO();
	}

	public RoomTypePicVO addRoomTypePic( Integer room_type_id, byte[] room_pic) {
		RoomTypePicVO roomTypePicVO = new RoomTypePicVO();

		roomTypePicVO.setRoom_type_id(room_type_id);;
		roomTypePicVO.setRoom_pic(room_pic);;
		
		dao.insert(roomTypePicVO);

		return roomTypePicVO;
	}

	public RoomTypePicVO updateRoomTypePic(Integer room_photo_id, Integer room_type_id, byte[] room_pic) {

		RoomTypePicVO roomTypePicVO = new RoomTypePicVO();

		roomTypePicVO.setRoom_photo_id(room_photo_id);;
		roomTypePicVO.setRoom_type_id(room_type_id);;
		roomTypePicVO.setRoom_pic(room_pic);;
		dao.update(roomTypePicVO);

		return roomTypePicVO;
	}

	public void deleteRoomType(Integer room_photo_id) {
		dao.delete(room_photo_id);
	}

	public RoomTypePicVO getOneRoomType(Integer room_photo_id) {
		return dao.findByPrimaryKey(room_photo_id);
	}

	public List<RoomTypePicVO> getAll() {
		return dao.getAll();
	}
	
	public void deleteRoomTypePicByRoomTypeId(Integer room_type_id) {
		dao.deleteByRoomTypeId(room_type_id);
	}
	
	public List<RoomTypePicVO> getFKPic(Integer room_type_id) {
		return dao.findByFK(room_type_id);
	}
}
