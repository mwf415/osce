package com.youyicn.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.model.Base;
import com.youyicn.model.Exam;
import com.youyicn.model.ExamCompose;
import com.youyicn.model.Room;
import com.youyicn.model.Station;
import com.youyicn.model.User;
import com.youyicn.service.CycleBaseService;
import com.youyicn.service.ExamComposeService;
import com.youyicn.service.ExamService;
import com.youyicn.service.RoomService;
import com.youyicn.service.StationService;

/**
 * Created by yangqj on 2017/4/26.
 */
@Controller
@RequestMapping("/exams")
public class ExamController {
	
	@Resource
    private ExamService examService;
    
    @Resource
    private StationService stationService;
    
    @Resource
    private CycleBaseService cycleBaseService;
    
    @Resource
    private RoomService roomService;

    @Resource
    private ExamComposeService examComposeService;
    
    @RequestMapping
    @ResponseBody
    public  Map<String,Object> getAll(Exam exam, String draw, Integer roleId,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<Exam> pageInfo = examService.selectByPage(exam, roleId, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    
    @RequestMapping(value = "/monitor")
    @ResponseBody
    public  Map<String,Object> monitor(String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        PageInfo<ExamCompose> pageInfo = examService.listExamComposePageByCondition(user.getLoginName()+'('+user.getRealName()+')', start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    
    @RequestMapping(value = "/add")
    @ResponseBody
    public String add(Exam exam) {
        try {
        	User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        	exam.setCreateTime(new Date());
        	exam.setCreateBy(user.getLoginName());
            examService.save(exam);
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
            examService.deleteByExamId(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value = "/update")
    @ResponseBody
    public String update(Exam exam) {
        try {
        	User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
        	exam.setCreateTime(new Date());
        	exam.setCreateBy(user.getLoginName());
            examService.updateNotNull(exam);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    /**
     * discription:获取考试
     * @param examId
     * @return
     */
    @RequestMapping("/listExamComposeByExamId")
    @ResponseBody
    public Map<String, Object> listExamComposeByExamId(Integer examId){
    	Map<String, Object> result = Maps.newHashMap();
	    boolean success = false;
	    String msg = "获取数据失败！";
	    Object data = null;
	    try {
			data = examComposeService.listExamComposeByExamId(examId);
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
    
    @RequestMapping(value="/configExam",method= RequestMethod.GET)
    public String configExam(@RequestParam Integer examId, Model model){
    	Exam exam = examService.selectByKey(examId);
    	List<Station> stations = stationService.selectAll();


        List<ExamCompose> examComposes = examComposeService.listExamComposeByExamId(examId);
        model.addAttribute("exam", exam);
        model.addAttribute("stations", stations);
        model.addAttribute("stationsJson", JSON.toJSONString(stations));

        List<Base> bases = cycleBaseService.selectAll();
        List<Room> rooms = roomService.selectAll();

        model.addAttribute("bases", bases);
        model.addAttribute("rooms", rooms);
        model.addAttribute("examComposes", examComposes);
		return "exam/exams_config";
    	
    }
    //考试配置
    @RequestMapping(value = "/saveConfig", method=RequestMethod.POST)
    @ResponseBody
    public String saveConfig(Exam exam, Integer[] stationIds, String[] stationNames, Integer[] questionIds, String[] questionNames,String[] addresses, String[] teacherNames) {
        try {
        	if(ArrayUtils.isEmpty(stationIds) || ArrayUtils.isEmpty(questionIds))
        		return "fail";
            examService.configExam(exam, stationIds, stationNames, questionIds, questionNames, addresses, teacherNames);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value="/monitorPage")
    public String monitorPage(){
		return "/exam/monitor_stations";
    	
    }
    
    @RequestMapping("/getExamById")
    @ResponseBody
    public Map<String, Object> getQuestionById(Integer id){
    	Map<String, Object> result = Maps.newHashMap();
	    boolean success = false;
	    String msg = "获取数据失败！";
	    Object data = null;
	    try {
			data = examService.selectByKey(id);
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
    
}
