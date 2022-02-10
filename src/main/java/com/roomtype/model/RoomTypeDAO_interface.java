package com.roomtype.model;

import java.util.List;

public interface RoomTypeDAO_interface {
	public void insert(RoomTypeVO roomTypeVO);
	public void update(RoomTypeVO roomTypeVO);
	public void delete(Integer room_type_id);
	public RoomTypeVO findByPK(Integer room_type_id);
	public List<RoomTypeVO> getAll();

}
