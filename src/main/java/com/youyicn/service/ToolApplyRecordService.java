package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Tool;
import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolApplyRecordVo;

public interface ToolApplyRecordService {
	
	 PageInfo<ToolApplyRecord> selectByPageAssotiation(ToolApplyRecord toolApplyRecord, int start, int length);
	 
	 List<ToolApplyRecordVo > listApplysByToolId (Integer toolId);

}
