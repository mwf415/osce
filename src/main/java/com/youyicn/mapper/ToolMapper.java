package com.youyicn.mapper;

import java.util.List;

import com.youyicn.model.Station;
import com.youyicn.model.Tool;
import com.youyicn.util.MyMapper;

public interface ToolMapper extends MyMapper<Tool> {
	List<Tool> queryToolsListWithSelected(Integer toolGropId);
	
	List<Tool> selectToolsByGroupId(Integer toolGropId);
	
	List<Tool> selectByEntity(Tool tool);
	
}