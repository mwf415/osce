package com.youyicn.mapper;

import java.util.List;

import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolApplyRecordVo;
import com.youyicn.model.ToolGroup;
import com.youyicn.util.MyMapper;

public interface ToolApplyRecordMapper extends MyMapper<ToolApplyRecord> {
	
	List<ToolApplyRecord> selectByEntity(ToolApplyRecord toolApplyRecord);
	
	//根据toolid查找借用记录
	 List<ToolApplyRecordVo > listApplysByToolId (Integer toolId);
	 
	 
}