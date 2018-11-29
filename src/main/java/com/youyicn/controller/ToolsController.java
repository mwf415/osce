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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/26.
 */
@Controller
@RequestMapping("/tools")
public class ToolsController {
    @Value("${server.port}")
    private String servicePort;
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
        toolService.save(tool);
        return "success";
    }

    /**
     * 下载二维码
     * @param toolId
     */
    @RequestMapping(value = "/qrToolDown")
    public void toolQrDown(@Param("toolId") Integer toolId, HttpServletResponse response) {
        if (null != toolId) {
            Tool tool = toolService.selectByKey(toolId);
            String urlMethod = "/showToolQr";
            Map<String, String> strings = new HashMap<>();// 二维码中的文字
            strings.put("toolId",toolId+"");
            LinkedHashMap<String, String> wordsString = new LinkedHashMap<>();
            wordsString.put("NAME", tool.getName().substring(0,15));
            wordsString.put("SUPPLIER", tool.getProductor());
            wordsString.put("NUM", tool.getToolNum());
            File file = QrCodeUtil.getInstance().createQRCode(urlMethod, servicePort, strings, wordsString,12);

            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + tool.getToolNum() + ".png");// 设置文件名
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 扫描二维码显示的数据
     * @param toolId
     */
    @RequestMapping(value = "/showToolQr")
    public String showToolQr(@Param("toolId") Integer toolId , Model model) {
       model.addAttribute("toolId",toolId);
        return "tool/tool_apply";
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
