package com.youyicn.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.model.Tool;
import com.youyicn.model.ToolGroup;
import com.youyicn.service.ToolGroupService;
import com.youyicn.service.ToolService;
import com.youyicn.util.QrCodeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/26.
 */
@Controller
@RequestMapping("/tools")
public class ToolsController {

    @Value("${cn.onlov.qrcodehost}")
    private String host;

    @Value("${cn.onlov.qrcodefilepath}")
    private String path;

    @Resource
    private ToolService toolService;
    @Resource
    private ToolGroupService toolGroupService;

    
   
    @RequestMapping
    @ResponseBody
    public  Map<String,Object> getAll(Tool tool, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<Tool> pageInfo = toolService.selectByPageAssotiation(tool, start, length);
//        PageInfo<Station> pageInfo = stationService.selectByPage(station, start, length);
        List<ToolGroup> groupList = toolGroupService.getAll();
        map.put("groupList", groupList);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    
    
    
    @RequestMapping("/listToolsByGroupId")
    @ResponseBody
    public Map<String, Object> listQuestionByExamId(Integer groupId){
    	
    	Map<String, Object> result = Maps.newHashMap();
	    boolean success = false;
	    String msg = "获取数据失败！";
	    Object data = null;
	    try {
			data = toolService.listToolsByGroupId(groupId);
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
    
    
    
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(Tool tool) {
        try {        	
        	toolService.save(tool);
        	Integer toolId = tool.getId();
        	QrCodeUtil.getInstance();
			String applyurl = null;
//                    QrCodeUtil.createStringMark(tool,propertiesConfig.getQrcodefilepath(), toolId, propertiesConfig.getQrcodehost());
        	tool.setCodeApplyUrl(applyurl);
        	toolService.updateNotNull(tool);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Tool tool) {
    	System.out.println("aaaaaaa");
        try {
        	toolService.updateNotNull(tool);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
}
