package com.youyicn.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.ExamStationRecord;
import com.youyicn.service.ExamStationRecordService;

@RestController
@RequestMapping("/examStationScore")
public class ExamScoreController {

    @Resource
    private ExamStationRecordService examStationScoreService;

    @RequestMapping
    public Map<String,Object> getAll(ExamStationRecord examStationScore, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<ExamStationRecord> pageInfo = examStationScoreService.selectByPage(examStationScore, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

}
