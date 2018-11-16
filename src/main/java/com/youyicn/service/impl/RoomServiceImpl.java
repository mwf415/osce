package com.youyicn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youyicn.mapper.RoomMapper;
import com.youyicn.model.Room;
import com.youyicn.service.RoomService;

@Service
@Transactional
public class RoomServiceImpl extends BaseService<Room> implements RoomService {
	@Resource
	private RoomMapper roomMapper;

	@Override
	public List<Room> selectAll() {
		return roomMapper.selectAll();
	}

   
}
