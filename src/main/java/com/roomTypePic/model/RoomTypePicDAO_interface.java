package com.roomTypePic.model;

import java.util.List;

public interface RoomTypePicDAO_interface {
	public void insert(RoomTypePicVO room_type_picVO);
	public void update(RoomTypePicVO room_type_picVO);
	public void delete(Integer room_photo_id);
	public RoomTypePicVO findByPrimaryKey(Integer room_photo_id);
	public List<RoomTypePicVO> getAll();
	
	public RoomTypePicVO findByFKreturnVO(Integer room_type_id);
	public void deleteByRoomTypeId(Integer room_type_id);
	public List<RoomTypePicVO> findByFK(Integer room_photo_id);
	
}
