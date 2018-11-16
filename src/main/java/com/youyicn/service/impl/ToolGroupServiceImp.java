package com.youyicn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.ToolGroupMapper;
import com.youyicn.model.Station;
import com.youyicn.model.ToolGroup;
import com.youyicn.service.ToolGroupService;


@Service("toolGroupService")
@Transactional
public class ToolGroupServiceImp extends BaseService<ToolGroup> implements ToolGroupService {
	
	@Autowired
	private ToolGroupMapper toolGroupMapper;
	

	
	
	@Override
	public PageInfo<ToolGroup> selectByPageAssotiation(ToolGroup toolGroup, int start, int length) {
		
		int page = start/length + 1;
		//分页查询
	    PageHelper.startPage(page, length);
	    List<ToolGroup> list = toolGroupMapper.selectByEntity(toolGroup);
	    return new PageInfo(list);
	}




	@Override
	public List<ToolGroup> getAll() {
		// TODO Auto-generated method stub
		return toolGroupMapper.getAll();
	}

	

}
