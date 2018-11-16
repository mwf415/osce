package com.youyicn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.ToolApplyRecordMapper;
import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolApplyRecordVo;
import com.youyicn.service.ToolApplyRecordService;
@Service("toolApplyRecordService")
@Transactional
public class ToolApplyRecordServiceImpl extends BaseService<ToolApplyRecord> implements ToolApplyRecordService {
	
	@Autowired
	private ToolApplyRecordMapper toolApplyRecordMapper;
	
	public int save(ToolApplyRecord toolApplyRecord){
		return toolApplyRecordMapper.insert(toolApplyRecord);
		
	}

	@Override
	public PageInfo<ToolApplyRecord> selectByPageAssotiation(ToolApplyRecord toolApplyRecord, int start, int length) {

			
			int page = start/length + 1;
			//分页查询
		    PageHelper.startPage(page, length);
		    List<ToolApplyRecord> list = toolApplyRecordMapper.selectByEntity(toolApplyRecord);
		    return new PageInfo(list);
		}

	@Override
	public List<ToolApplyRecordVo> listApplysByToolId(Integer toolId) {
		
		return toolApplyRecordMapper.listApplysByToolId(toolId);
	}


	
}
