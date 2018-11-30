package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolApplyRecordVo;

public interface ToolApplyRecordService extends IService<ToolApplyRecord>  {
	
	 PageInfo<ToolApplyRecord> selectByPageAssotiation(ToolApplyRecord toolApplyRecord, int start, int length);

	PageInfo<ToolApplyRecordVo> listApplysByToolId (Integer toolId,int start,int length);

	 List<ToolApplyRecord> timerListToReturn ();

	ToolApplyRecord getinUseByToolId(Integer toolId);

}
