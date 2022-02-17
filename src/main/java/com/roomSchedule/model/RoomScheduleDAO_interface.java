package com.roomSchedule.model;

import java.util.List;


public interface RoomScheduleDAO_interface {
	public void insert(RoomScheduleVO room_scheduleVO);
	public void update(RoomScheduleVO room_scheduleVO);
	public void delete(Integer room_schedule_id);
	public RoomScheduleVO findByPrimaryKey(Integer room_schedule_id);
	public List<RoomScheduleVO> getAll();

}
