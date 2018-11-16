package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Station;
import com.youyicn.model.ToolGroup;

public interface ToolGroupService extends IService<ToolGroup>{
	
	PageInfo<ToolGroup> selectByPageAssotiation(ToolGroup toolGroup,int start,int length);
	
	List<ToolGroup> getAll();
	
	

}
