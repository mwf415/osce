package com.youyicn.controller;

import java.lang.System;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.youyicn.model.*;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.service.ToolApplyRecordService;
import com.youyicn.service.ToolGroupService;
import com.youyicn.service.ToolService;


@RestController
@RequestMapping("/applys")
public class ToolApplyRecordController {

    @Autowired
    public ToolApplyRecordService toolApplyRecordService;

    @Autowired
    private ToolService toolService;

    @RequestMapping("/listApplysByToolId")
    @ResponseBody
    public Map<String, Object> listQuestionByExamId(@Param("toolId") Integer toolId , String draw, Integer roleId,
                                                    @RequestParam(required = false, defaultValue = "1") int start,
                                                    @RequestParam(required = false, defaultValue = "10") int length ) {


        Map<String,Object> map = new HashMap<>();
        PageInfo<ToolApplyRecordVo> pageInfo = toolApplyRecordService.listApplysByToolId(toolId,start,length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        map.put("success", true);
        return map;
    }

    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> toolApply( ToolApplyRecord toolApplyRecord) {
        Integer toolId = toolApplyRecord.getToolId();
        Tool tool = toolService.selectByKey(toolId);
        Map<String, Object> result = Maps.newHashMap();
        if(null!=tool){
            Integer applyStatus = tool.getStatus();
            if(TOOL_STATUS_RETURN!=applyStatus){  // 1  k可以使用
                result.put("success",false);
                result.put("msg","该设备不能被借用");
                return  result;
            }
        }
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        Integer id = user.getId();
        String roomName = user.getRoomName();
        String baseName  = user.getBaseName();
        toolApplyRecord.setDepartment(baseName+"--"+roomName);
        toolApplyRecord.setUserId(id);
        toolApplyRecord.setStatus(TOOL_STATUS_IN_USER); // 状态： 使用中状态
        int i = toolApplyRecordService.save(toolApplyRecord);
        if(i==1) {
            tool.setStatus(TOOL_STATUS_IN_USER);
            toolService.updateNotNull(tool);
            result.put("success", "success");
        }else {
            result.put("success", "false");
        }
        return result;
    }

    /**
     * 定时任务，扫描该还的，但是没有还的设备
     */

    public static final Integer TOOL_STATUS_CAN = 0; // 0 报废  1 已归还，2 借用中   3 使用结束未归还  4 使用造成损坏。  5 维修中
    public static final Integer TOOL_STATUS_RETURN = 1; // 可以借用，已归还
    public static final Integer TOOL_STATUS_IN_USER = 2 ; // 借用中
    public static final Integer TOOL_STATUS_OUT_TIME = 3 ; // 使用结束未归还
    public static final Integer TOOL_STATUS_OUT_DEMAGE = 4 ; // 使用造成损坏
    public static final Integer TOOL_STATUS_OUT_FIXING = 5 ; // 维修中


    @Scheduled(cron = "0 0 1 * * ?")
    public void demoSchedule() {
        System.out.println("");
        // 查询状态是1 并且到期的借用单，同时更改设施状态为2 到期未还 ；

        List<ToolApplyRecord> toolApplyRecords = toolApplyRecordService.timerListToReturn();
        for (ToolApplyRecord toolApplyRecord : toolApplyRecords) {
            Integer toolId = toolApplyRecord.getToolId();
            Tool tool  = toolService.selectByKey(toolId);
            tool.setStatus(TOOL_STATUS_OUT_TIME);
            toolService.updateNotNull(tool);
            toolApplyRecord.setStatus(TOOL_STATUS_OUT_TIME);
            toolApplyRecordService.updateNotNull(toolApplyRecord);
            System.out.println("定时任务定时扫描还款"+toolApplyRecord.toString());
        }

        System.out.println("定时任务：此刻时间是 测试03"+new Date());
    }


    @RequestMapping("/return")
    @ResponseBody
    public Map<String ,Object> returnTool(@Param("toolId")Integer toolId,@Param("status") int status){
        Map<String ,Object> result = new HashMap<>();

        try {
            ToolApplyRecord toolApplyRecord = toolApplyRecordService.getinUseByToolId(toolId);
            Tool tool = toolService.selectByKey(toolId);
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userSession");
            toolApplyRecord.setStatus(status);
            toolApplyRecord.setReturnUserId(user.getId());
            toolApplyRecordService.updateNotNull(toolApplyRecord);
            tool.setStatus(status);
            toolService.updateNotNull(tool);
            result.put("msg","success");

        }catch (Exception e){
            result.put("msg","fail");
        }
        return result;
    }



}
