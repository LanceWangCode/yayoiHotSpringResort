package com.roomOrderList.model;

import java.util.List;

public interface RoomOrderListDAO_interface {
	public void insert(RoomOrderListVO roomOrderListVO);
	public void update(RoomOrderListVO roomOrderListVO);
	public void delete(Integer room_order_list_id);
	public RoomOrderListVO findByPrimaryKey(Integer room_order_list_id);
	public List<RoomOrderListVO> getAll();

}
