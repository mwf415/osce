package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Tool;


public interface ToolService extends IService<Tool> {
	PageInfo<Tool> selectByPageAssotiation(Tool tool, int start, int length);
	
	List<Tool> listToolsByGroupId(Integer groupId);
	
}
