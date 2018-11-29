package com.youyicn.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.model.Tool;
import com.youyicn.model.ToolGroup;
import com.youyicn.service.ToolGroupService;
import com.youyicn.service.ToolService;
import com.youyicn.util.QrCodeUtil;
import org.apache.ibatis.annotations.Param;
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

    @Resource
    private ToolService toolService;
    @Resource
    private ToolGroupService toolGroupService;


    @RequestMapping
    @ResponseBody
    public Map<String, Object> getAll(Tool tool, String draw, Integer groupId,
                                      @RequestParam(required = false, defaultValue = "1") int start,
                                      @RequestParam(required = false, defaultValue = "10") int length) {

        Map<String, Object> map = new HashMap<>();
        if (null != groupId) {
            tool.setToolGroupId(groupId);
        }
        PageInfo<Tool> pageInfo = toolService.selectByPageAssotiation(tool, start, length);
        /**
         * 处理分组的事情
         */
        List<ToolGroup> groupList = toolGroupService.getAll();
        Map<Integer, String> groupMap = this.getGroupMap(groupList);
        for (Tool tool1 : pageInfo.getList()) {
            Integer toolGroupId = tool1.getToolGroupId();
            if (null != toolGroupId) {
                tool1.setToolGroupName(groupMap.get(toolGroupId));
            }
        }
        map.put("groupList", groupList);
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        map.put("success", true);
        map.put("msg", "成功");

        return map;
    }

    private Map<Integer, String> getGroupMap(List<ToolGroup> groupList) {
        Map<Integer, String> groupMap = new HashMap<>();
        if (groupList.size() > 0) {
            for (ToolGroup toolGroup : groupList) {
                Integer id = toolGroup.getId();
                String name = toolGroup.getName();
                groupMap.put(id, name);
            }
        }
        return groupMap;
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
        try {
            toolService.updateNotNull(tool);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }


}
