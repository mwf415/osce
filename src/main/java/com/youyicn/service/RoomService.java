package com.youyicn.service;

import java.util.List;

import com.youyicn.model.Room;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface RoomService extends IService<Room> {

	List<Room> selectAll();
	
}
