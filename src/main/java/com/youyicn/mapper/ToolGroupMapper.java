package com.youyicn.mapper;

import java.util.List;

import com.youyicn.model.ToolGroup;
import com.youyicn.util.MyMapper;

public interface ToolGroupMapper extends MyMapper<ToolGroup> {
	
	
	List<ToolGroup> selectByEntity(ToolGroup toolGroup);
	List<ToolGroup> getAll();
}