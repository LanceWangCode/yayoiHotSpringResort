package com.room.model;

import java.util.List;

public interface RoomDAO_interface {
	public void insert(RoomVO roomVo);
	public void update(RoomVO roomVo);
	public void delete(Integer room_id);
	public RoomVO findByPK(Integer room_id);
	public List<RoomVO> getAll();

}
