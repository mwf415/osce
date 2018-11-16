package com.youyicn.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Station;
import com.youyicn.model.Tool;
import com.youyicn.model.ToolGroup;
import com.youyicn.service.ToolGroupService;


@RestController
@RequestMapping("/groups")
public class ToolsGroupController {
	
	
	@Autowired
	public ToolGroupService toolGroupService;	
	
	@RequestMapping
	@ResponseBody
    public  Map<String,Object> getAll(ToolGroup toolGroup, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<ToolGroup> pageInfo = toolGroupService.selectByPageAssotiation(toolGroup, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

	
	

    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(ToolGroup tool) {
        try {
        	toolGroupService.save(tool);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(ToolGroup tool) {
        try {
        	toolGroupService.updateNotNull(tool);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
	
    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(Integer id){
        try{
        	toolGroupService.delete(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
	
}
