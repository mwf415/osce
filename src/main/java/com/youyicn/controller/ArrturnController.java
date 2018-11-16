package com.youyicn.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Arrturn;
import com.youyicn.service.ArrturnService;

/**
 * discription:轮转学生
 * @author zhangxiaowei02
 * 2018年5月15日
 */
@RestController
@RequestMapping("/arrturns")
public class ArrturnController {
    @Resource
    private ArrturnService arrturnService;
    
    @RequestMapping
    public  Map<String,Object> getAll(Arrturn arrturn, String draw, String outTime,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "100") int length){

        Map<String,Object> map = new HashMap<>();
        Date startTime, endTime;
        DateTime dateTime = new DateTime();
        if(StringUtils.isNotBlank(outTime)){
        	String[] split = outTime.split("/");
        	dateTime = dateTime.withYear(Integer.parseInt(split[0]));
        	dateTime = dateTime.withMonthOfYear(Integer.parseInt(split[1]));
        }
        startTime = dateTime.dayOfMonth().withMinimumValue().secondOfDay().withMinimumValue().toDate();
        endTime = dateTime.dayOfMonth().withMaximumValue().secondOfDay().withMaximumValue().toDate();
        
        PageInfo<Arrturn> pageInfo = arrturnService.selectByPage(arrturn, start, length, startTime, endTime);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    

}
