package com.youyicn.service;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.ArrturnRule;
import com.youyicn.model.Room;
import com.youyicn.model.Subject;

import java.util.List;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface CycleRoomService extends IService<Room> {
	
	PageInfo<Room> selectByPage(Room base, int start, int length);
	List<Room> selectAll();
	void deleteByKey(Integer key);
	List<Subject> listSubjectByRoomId(Integer baseId);
	//根据基地获取轮转科室
	List<ArrturnRule> listArrturnRuleByRoomName(String baseName);
}
