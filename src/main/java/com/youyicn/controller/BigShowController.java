package com.youyicn.controller;

import cn.onlov.utils.DateUtil;
import com.youyicn.model.BigShowEntity;
import com.youyicn.model.BigShowRsp;
import com.youyicn.model.Exam;
import com.youyicn.model.UserParm;
import com.youyicn.service.ExamService;
import com.youyicn.service.OsceSortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

/**
 * 大屏显示功能
 */
@Controller
public class BigShowController {


    @Autowired
    private OsceSortService osceSortService;

    @Autowired
    private ExamService examService;

    @RequestMapping("bigShowController")
    private String index(Model model) {
        //查看所有正在考试的考试
        Date date = new Date();
        Date startDate = DateUtil.getStartTimeOfDay(date);
        Date endDate = DateUtil.getEndTimeOfDay(date);

        List<Exam> examList = examService.listTodayExam(startDate, endDate);
        Map<String , BigShowEntity> result = new HashMap<>();
        List<BigShowRsp>bigShowRsps = new ArrayList<>();
        if (examList.size()>0) {
            for (Exam exam : examList) {
                BigShowRsp bigShowRsp = new BigShowRsp();
                Integer examId =exam.getId();
                List<UserParm> inUserList = osceSortService.getInUserByExamId(examId); // 正在考试的学生
                List<BigShowEntity> toInUser = osceSortService.toInExamByExamId(examId);//候考的学生
                String outUserList = osceSortService.getUnInUserByExamId(examId); // 没有报考的学生
                String finishedUser = osceSortService.getFinishedUser(examId); // 已经完成的学生

                bigShowRsp.setExam(exam);
                bigShowRsp.setInUserList(inUserList);
                bigShowRsp.setFinishedUser(finishedUser);
                bigShowRsp.setToInUser(toInUser);
                bigShowRsp.setOutUserList(outUserList);
                bigShowRsps.add(bigShowRsp);
            }
        }
        model.addAttribute("bigShowRsps",bigShowRsps);
        return "/sort/bigIndex";
    }
}
