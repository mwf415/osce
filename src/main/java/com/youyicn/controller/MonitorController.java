package com.youyicn.controller;

import com.youyicn.model.*;
import com.youyicn.service.ExamComposeService;
import com.youyicn.service.ExamService;
import com.youyicn.service.OsceSortService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {

    @Resource
    private OsceSortService osceSortService;
    @Resource
    private ExamService examService;

    @RequestMapping("/index")
    public String index (Model model, HttpServletRequest request, HttpServletResponse response,@RequestParam Integer examId ){
        List<UserParm> inUserList =  osceSortService.getInUserByExamId(examId); // 正在考试的学生
        String toInUser =osceSortService.toInExamByExamId(examId);//候考的学生
        Exam exam = osceSortService.getExamByExamId(examId);
        String outUserList =  osceSortService.getUnInUserByExamId(examId); // 没有报考的学生
        String finishedUser = osceSortService.getFinishedUser(examId); // 已经完成的学生
        // 候考学生
        model.addAttribute("inUserList", inUserList);
        model.addAttribute("outUserList", outUserList);
        model.addAttribute("finishedUser", finishedUser);
        model.addAttribute("toInUser", toInUser);
        model.addAttribute("exam", exam);
        return "/monitor/monitor";
    }




    /**
     * 已经完成考试的人员
     * @param examId
     * @return
     */

    @RequestMapping("/userDetail")
    public Map<String,Object> userDetalOut(@RequestParam Integer examId ){
        Map<String ,Object > resultMap = new HashMap<>();
//        List<String> inUserList =  osceSortService.getUnInUserByExamId(examId);
        resultMap.put("date",null);
        return resultMap;
    }


}
