package com.youyicn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.model.Station;
import com.youyicn.model.Tool;
import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolGroup;
import com.youyicn.service.ToolApplyRecordService;
import com.youyicn.service.ToolGroupService;
import com.youyicn.service.ToolService;


@RestController
@RequestMapping("/applys")
public class ToolApplyRecordController {

	@Autowired
	public ToolApplyRecordService toolApplyRecordService;	

	
	@RequestMapping("/listApplysByToolId")
    @ResponseBody
    public Map<String, Object> listQuestionByExamId(Integer toolId){
    	Map<String, Object> result = Maps.newHashMap();
	    boolean success = false;
	    String msg = "获取数据失败！";
	    Object data = null;
	    try {
			data = toolApplyRecordService.listApplysByToolId(toolId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    result.put("success", success);
	    result.put("msg", msg);
	    result.put("data", data);
	    
        return result;
    }
    
	
	
}
