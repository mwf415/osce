package com.youyicn.mapper;

import java.util.List;
import java.util.Map;

import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolApplyRecordVo;
import com.youyicn.model.ToolGroup;
import com.youyicn.util.MyMapper;
import org.springframework.jdbc.support.nativejdbc.OracleJdbc4NativeJdbcExtractor;

public interface ToolApplyRecordMapper extends MyMapper<ToolApplyRecord> {
	
	List<ToolApplyRecord> selectByEntity(ToolApplyRecord toolApplyRecord);
	
	//根据toolid查找借用记录
	 List<ToolApplyRecordVo > listApplysByToolId (Map<String,Object> map);
	 
	 
}