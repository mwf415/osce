package com.youyicn.controller;

import com.youyicn.model.UserParm;
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
@RequestMapping(value = "/monitor")
public class MonitorController {

    @Resource
    private OsceSortService osceSortService;

    @RequestMapping("/userIn")
    @ResponseBody
    public Map<String,Object> userIn(String draw,@RequestParam Integer examId ){
        Map<String ,Object> map = new HashMap<>();
        List<UserParm> inUserList =  osceSortService.getUserDetailService(examId);
        map.put("draw",draw);
        map.put("recordsTotal",inUserList.size());
        map.put("recordsFiltered",inUserList.size());
        map.put("data", inUserList);
        return map;
    }

    @RequestMapping("/userOut")
    @ResponseBody
    public Map<String,Object> userOut(String draw,@RequestParam Integer examId ){
        Map<String ,Object> map = new HashMap<>();
        List<UserParm> inUserList =  osceSortService.getUnInUserByExamId(examId);
        map.put("draw",draw);
        map.put("recordsTotal",inUserList.size());
        map.put("recordsFiltered",inUserList.size());
        map.put("data", inUserList);
        return map;
    }


    @RequestMapping("/finishedUser")
    @ResponseBody
    public Map<String,Object> getFinishedUser(Integer examId){
        Map<String ,Object> map = new HashMap<>();
       String finishedUser = osceSortService.getFinishedUser(examId);
        map.put("finishedUser",finishedUser);
        return map;
    }
    @RequestMapping("/userDetail")
    public Map<String,Object> userDetalOut(@RequestParam Integer examId ){
        Map<String ,Object > resultMap = new HashMap<>();
//        List<String> inUserList =  osceSortService.getUnInUserByExamId(examId);
        resultMap.put("date",null);
        return resultMap;
    }


}
