package com.youyicn.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.youyicn.mapper.ArrturnRuleMapper;
import com.youyicn.mapper.RoomMapper;
import com.youyicn.mapper.SubjectMapper;
import com.youyicn.model.ArrturnRule;
import com.youyicn.model.Room;
import com.youyicn.model.Subject;
import com.youyicn.service.CycleRoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class CycleRoomServiceImpl extends RoomService<Room> implements CycleRoomService {
	@Resource
	private RoomMapper roomMapper;
	@Resource
	private SubjectMapper subjectMapper;
	@Resource
	private ArrturnRuleMapper arrturnRuleMapper;
	
	@Override
	public PageInfo<Room> selectByPage(Room room, int start, int length) {
		int page = start/length+1;
		Example example = new Example(Room.class);
		example.orderBy("id").desc();
		Criteria criteria = example.createCriteria();
		if(room.getRoomNum()!=null){
			criteria.andEqualTo("roomNum", room.getRoomNum());
		}
		if(StringUtils.isNotBlank(room.getValue())){
			criteria.andLike("roomNum", "%" + room.getValue() + "%");
		}
		
		PageHelper.startPage(page, length);
		List<Room> list = mapper.selectByExample(example);
		return new PageInfo(list);
	}
	
	@Override
	public List<Room> selectAll() {
		return roomMapper.selectAll();
	}

	@Override
	public void deleteByKey(Integer key) {
		mapper.deleteByPrimaryKey(key);
		//删除基地下的专业
		Example example = new Example(Subject.class);
		Criteria criteria = example.createCriteria();
		subjectMapper.deleteByExample(example);
	}

	@Override
	public List<Subject> listSubjectByRoomId(Integer roomId) {
		Example example = new Example(Subject.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("roomId", roomId);
		List<Subject> list = subjectMapper.selectByExample(example);
		return list;
	}


	@Override
	public List<ArrturnRule> listArrturnRuleByRoomName(String roomName) {
		Example example = new Example(ArrturnRule.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("roomName", roomName);
		List<ArrturnRule> list = arrturnRuleMapper.selectByExample(example);
		return list;
	}


   
}
