package com.youyicn.mapper;

import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolApplyRecordVo;
import com.youyicn.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface ToolApplyRecordMapper extends MyMapper<ToolApplyRecord> {
	
	List<ToolApplyRecord> selectByEntity(ToolApplyRecord toolApplyRecord);
	
	//根据toolid查找借用记录
	 List<ToolApplyRecordVo > listApplysByToolId (Map<String,Object> map);
	 
	 
}