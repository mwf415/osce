package com.youyicn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.ExamStationRecord;
import com.youyicn.model.ExamUser;
import com.youyicn.model.Station;
import com.youyicn.model.User;
import com.youyicn.service.ExamStationRecordService;
import com.youyicn.service.ExamUserService;
import com.youyicn.service.StationService;
import com.youyicn.service.UserService;

/**
 * discription:考试记录
 * @author zhangxiaowei02
 * 2018年5月8日
 */
@Controller
@RequestMapping("/myExams")
public class MyExamController {
	
    @Resource
    private ExamUserService examUserService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private ExamStationRecordService examStationRecordService;
    
    @Resource
    private StationService stationService;
    
    @RequestMapping
    @ResponseBody
    public Map<String,Object> getAll(ExamUser examUser, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<ExamUser> pageInfo = examUserService.selectByPage(examUser, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    /**
     * discription:我的考试页
     * @return
     */
    @RequestMapping(value="/examsPage")
    public String examsPage(){
		return "/myExam/exams";
    }
    
    /**
     * discription:考试记录页
     * @return
     */
    @RequestMapping(value="/examsRecordPage")
    public String examsRecord(){
		return "/myExam/exams_record";
    }
    
    @RequestMapping(value="/stationRecordListPage")
    public String stationRecordListPage(ExamStationRecord examStationRecord, @RequestParam(required=false, value="actionType", defaultValue="0") Integer actionType, Model model){
    	List<Station> stations = stationService.selectAll();
    	model.addAttribute("examStationRecord", examStationRecord);
    	model.addAttribute("actionType", actionType);
    	model.addAttribute("stations", stations);
		return "/myExam/station_record_list";
    	
    }
    
    @RequestMapping(value="/stationRecordList")
    @ResponseBody
    public Map<String,Object> stationRecordList(ExamStationRecord examStationRecord, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<ExamStationRecord> pageInfo = examStationRecordService.selectByPage(examStationRecord, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    
    @RequestMapping(value="/stationRecordDetail")
    public String stationRecordDetail(@RequestParam Integer stationRecordId, Model model){
    	ExamStationRecord examStationRecord = examStationRecordService.selectByKey(stationRecordId);
    	User user = null;
    	if(examStationRecord!=null){
    		user = userService.selectByLoginName(examStationRecord.getUserId());
    	}
    	model.addAttribute("examStationRecord", examStationRecord);
    	model.addAttribute("user", user);
		return "/myExam/station_record_detail";
    	
    }
    
}
