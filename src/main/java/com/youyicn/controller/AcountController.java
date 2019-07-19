package com.youyicn.controller;

import com.github.abel533.echarts.json.GsonOption;
import com.github.pagehelper.PageInfo;
import com.youyicn.model.Exam;
import com.youyicn.model.ExamUser;
import com.youyicn.model.OsceOffender;
import com.youyicn.service.ExamService;
import com.youyicn.service.ExamUserService;
import com.youyicn.service.OsceOffenderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.IntStream;
import cn.onlov.utils.EchartUtils ;

@Controller
@RequestMapping("/count")
public class AcountController {

    @Resource
    private ExamService examService;
    @Autowired
    private ExamUserService examUserService;

    @RequestMapping
    @ResponseBody
    public Map<String, Object> getAll(Exam exam, String draw, Integer roleId,
                                      @RequestParam(required = false, defaultValue = "1") int start,
                                      @RequestParam(required = false, defaultValue = "10") int length) {

        Map<String, Object> map = new HashMap<>();
        PageInfo<Exam> pageInfo = examService.selectByPage(exam, roleId, start, length);
        map.put("draw", draw);
        map.put("recordsTotal", pageInfo.getTotal());
        map.put("recordsFiltered", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/detail")
    public String detailIndex(@Param("examId") Integer examId, Model model) {

        model.addAttribute("examId", examId);
        return "/count/count";
    }


    @RequestMapping("/getPie")
    @ResponseBody
    public String detail(Integer examId) {
        // 查询所有的考生信息
        List<ExamUser> examUserList = examUserService.selectById(examId);
        String examTitle = "";
        String[] types = {"10分以下", "10-20分人数", "20-30分人数", "30-40分人数", "40-50分人数", "50-60分人数", "60-70人数", "70-80人数", "80-90人数", "90-100人数"};
        int[] datas = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        if (examUserList.size() > 0) {
            examTitle = examUserList.get(0).getExamTitle();
            for (ExamUser examUser : examUserList) {
                getDatas(datas, examUser);
            }
        }
        GsonOption pie = EchartUtils.getPie(types, datas, examTitle);
        String pieString = pie.toString();
        return pieString;
    }

    private int[]  getDatas(int[] datas, ExamUser examUser) {
        Integer score = examUser.getScore();
        if (null == score) {
            return datas;
        }
        int b = ((score < 10) == true ? 1 : 0) +
                ((10 < score && score < 20) == true ? 2 : 0) +
                ((20 < score && score < 30) == true ? 3 : 0) +
                ((30 < score && score < 40) == true ? 4 : 0) +
                ((40 < score && score < 50) == true ? 5 : 0) +
                ((50 < score && score < 60) == true ? 6 : 0) +
                ((60 <= score && score < 70) == true ? 7 : 0) +
                ((70 <= score && score < 80) == true ? 8 : 0) +
                ((80 <= score && score < 90) == true ? 9 : 0) +
                ((90 <= score) == true ? 10 : 0);
        switch (b) {
            case 1:
                datas[0] = datas[0] + 1;
                return datas;
            case 2:
                datas[1] = datas[1] + 1;
                return datas;
            case 3:
                datas[2] = datas[2] + 1;
                return datas;
            case 4:
                datas[3] = datas[3] + 1;
                return datas;
            case 5:
                datas[4] = datas[4] + 1;
                return datas;
            case 6:
                datas[5] = datas[5] + 1;
                return datas;
            case 7:
                datas[6] = datas[6] + 1;
                return datas;
            case 8:
                datas[7] = datas[7] + 1;
                return datas;
            case 9:
                datas[8] = datas[8] + 1;
                return datas;
            case 10:
                datas[9] = datas[9] + 1;
                return datas;
        }
        return datas;
    }

    @RequestMapping("/detailStr")
    @ResponseBody
    public Map<String, Object> detailStr(Integer examId) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ExamUser> examUserList = examUserService.selectById(examId);
        StringBuffer finishedUser = new StringBuffer(); // 完成的学生
        int finishedUserCount = 0;
        StringBuffer unFinishedUser = new StringBuffer(); // 没有打分的学生
        int unFinishedUserCount = 0;

        List<Integer> scores = new ArrayList<>();
        if (examUserList.size() > 0) {
            for (ExamUser examUser : examUserList) {
                Integer score = examUser.getScore();
                if (null == score) {
                    unFinishedUser.append(examUser.getRealName() + ":" + examUser.getUserId() + ";  ");
                    unFinishedUserCount = unFinishedUserCount + 1;
                } else {
                    finishedUser.append(examUser.getRealName() + ":" + examUser.getUserId() + ";  ");
                    finishedUserCount = finishedUserCount + 1;
                    scores.add(score);
                }
            }
        }

        if (null != scores && scores.size() > 0) {
            Integer[] scoreTemp = new Integer[scores.size()];
            for (int i = 0; i < scores.size(); i++) {
                scoreTemp[i] = scores.get(i);
            }
            int min = IntStream.range(0, scoreTemp.length).reduce((i, j) -> scoreTemp[i] > scoreTemp[j] ? j : i).getAsInt();
            int max = IntStream.range(0, scoreTemp.length).reduce((i, j) -> scoreTemp[i] < scoreTemp[j] ? j : i).getAsInt();
            resultMap.put("maxScort", scores.get(max));
            resultMap.put("minScort", scores.get(min));
        }
        /**
         * 添加违纪学生名单
         */
        Map<String,OsceOffender> map = osceOffenderService.getByExamId(examId);
        StringBuilder osceOffendersName = new StringBuilder();
        for (String s : map.keySet()) {
            OsceOffender osceOffender = map.get(s);
            osceOffendersName.append(osceOffender.getUserName()+":");
        }
        resultMap.put("osceOffendersName",osceOffendersName);
        resultMap.put("osceOffendersSize" ,map.size());
        resultMap.put("finishedUserCount", finishedUserCount);
        resultMap.put("unFinishedUserCount", unFinishedUserCount);
        resultMap.put("finishedUser", finishedUser);
        resultMap.put("unFinishedUser", unFinishedUser);
        return resultMap;
    }

    @Autowired
    private OsceOffenderService osceOffenderService;


}
