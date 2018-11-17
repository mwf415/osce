package com.youyicn.controller;

import com.youyicn.service.OsceSortService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MonitorController {

    @Resource
    private OsceSortService osceSortService;

    @RequestMapping("userIn")
    @ResponseBody
    public Map<String,Object> userIn(@RequestParam Integer examId ){
        Map<String ,Object> resultMap = new HashMap<>();
        List<String> inUserList =  osceSortService.getInUserByExamId(examId);
        resultMap.put("date",inUserList);
        return resultMap;
    }
    @RequestMapping("userIn")
    public Map<String,Object> userOut(@RequestParam Integer examId ){
        Map<String ,Object > resultMap = new HashMap<>();
        List<String> inUserList =  osceSortService.getUnInUserByExamId(examId);
        resultMap.put("date",inUserList);
        return resultMap;
    }


    @RequestMapping("userOut")
    public Map<String,Object> userStation(@RequestParam Integer examId ){
        Map<String ,Object > resultMap = new HashMap<>();
        List<String> inUserList =  osceSortService.getUnInUserByExamId(examId);
        resultMap.put("date",inUserList);
        return resultMap;
    }
}
