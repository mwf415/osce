package com.youyicn.controller;

import com.youyicn.model.*;
import com.youyicn.service.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.System;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * 查找用的的评分表
 */

@Controller
public class QrCodeController {

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamStationRecordService examStationRecordService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private UserService userService;

    @Autowired
    private OsceSortService osceSortService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private  ExamComposeService examComposeService;

    @RequestMapping("/qrCodeController")
    private String getUserSocerTalbe(HttpServletRequest request, HttpServletResponse response,@RequestParam String examId,@RequestParam String userId , Model model) throws IllegalAccessException, InvocationTargetException{

        model.addAttribute("examId",examId);
        model.addAttribute("userId",userId);
        System.out.println("request = [i" + request + "], response = [" + response + "], examId = [" + examId + "], userId = [" + userId + "], model = [" + model + "]");

        User examUser = userService.selectByLoginName(userId);
        model.addAttribute("examUser", examUser);
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        List<Role> roles = roleService.queryRoleListByUserId(user.getId());
        String rolesStr = "";
        for (Role role : roles) {
            rolesStr= role.getRoleName()+":"+ rolesStr;
        }
        /**
         * 第一次扫描，如果能够查询出来信息，说明已经排过序了
         */
        OsceSort osceSort =osceSortService.getByExamIdAndUserId(Integer.parseInt(examId),userId);


        if(rolesStr.contains("排序管理员")){
            // 如果为空，那么需要给他排序，如果不为空，则显示已安排
            if(null==osceSort || null == osceSort.getExamid()){
                return "/sort/sortIndex";
            }else{
            	model.addAttribute("osceSort",osceSort);
            	return "/sort/sortIndexed";
            }
        }
        ExamStationRecord examStationRecord = new ExamStationRecord();
        if(null==osceSort || null == osceSort.getExamid()){
            return "/sort/error";
        }
        /**
         * 查询该老师名下所有的没有打分的学生
         */
        List<ExamCompose> examComposeList = examService.listExamComposeByCondition(user.getLoginName()+'('+user.getRealName()+')',Integer.parseInt(examId));
        if(examComposeList.size()>0){
            for (ExamCompose examCompose : examComposeList) {
                // 获取考站信息和 对应的站点信息
                if(examId.equals(examCompose.getExamId().toString())){
                    Integer stationId = examCompose.getStationId();
                    Byte state = 0;
                    examStationRecord.setUserId(userId);
                    examStationRecord.setExamId(Integer.parseInt(examId));
                    examStationRecord.setState(state);
                    examStationRecord.setStationId(stationId);

                    /**
                     * 根据这两个信息，查找参加该考站的学生的信息
                     */
                    List<ExamStationRecord> examStationRecords = examStationRecordService.getByExamIdAndStationId(examStationRecord);
                    if(examStationRecords.size()>0){
                        for (ExamStationRecord stationRecord : examStationRecords) {
                            if(userId.equals(stationRecord.getUserId())){
                                Integer questionId = stationRecord.getQuestionId();
                                Question question = questionService.selectByKey(questionId);
                                if(null!= question){
                                    model.addAttribute("duration", question.getDuration());
                                }
                                BeanUtils.copyProperties(examStationRecord, stationRecord);
                                List<ScoreItem> scoreItems = questionService.listScoreItemByQuestionId(examStationRecord.getQuestionId());
                                model.addAttribute("examStationRecord", examStationRecord);
                                model.addAttribute("scoreItems", scoreItems);
                                model.addAttribute("user", examUser);
                                return "/exam/monitor_item";
                            }
                        }
                    }
                }
            }
        }
        model.addAttribute("user", examUser);
        return "/exam/monitor_item_error";
    }


    /**
     * 实现打分后修改的功能
     */
    @RequestMapping("/examUsers/updateItem")
    public String updteItem (Integer examId,String  userId, Integer stationId,Model model){
        User examUser = userService.selectByLoginName(userId);
        ExamStationRecord examStationRecordTmp  = new ExamStationRecord();
        examStationRecordTmp.setExamId(examId);
        examStationRecordTmp.setUserId(userId);
        examStationRecordTmp.setStationId(stationId);
        List<ExamStationRecord> stationRecords  = examStationRecordService.getByExamIdAndStationId(examStationRecordTmp);
        if(stationRecords.size()>0){
            ExamStationRecord examStationRecord  = stationRecords.get(0);

            Integer questionId = examStationRecord.getQuestionId();
            Question question = questionService.selectByKey(questionId);
            if(null!= question){
                model.addAttribute("duration", question.getDuration());
            }
            List<ScoreItem> scoreItems = questionService.listScoreItemByQuestionId(questionId);
            model.addAttribute("examStationRecordTmp", examStationRecordTmp);
            model.addAttribute("scoreItems", scoreItems);
            model.addAttribute("user", examUser);

            return "/exam/monitor_item";

        }
        model.addAttribute("user", examUser);
        return "/exam/monitor_item_error";
    }




    @RequestMapping("/addSort")
    public String addSort(HttpServletRequest request, HttpServletResponse response, Integer examId, String userId, ModelMap model){
        OsceSort osceSort =new OsceSort();

        osceSort =osceSortService.getByExamIdAndUserId(examId,userId );
        if(null!=osceSort && null!= osceSort.getGroupid()){
            model.addAttribute("osceSort",osceSort);
            return "/sort/sortIndexed";
        }
        /**
         * 根据用户id 查找用户
         */
        User user = userService.selectByLoginName(userId+"");
        osceSort.setExamid(examId);
        osceSort.setUserid(userId);
        osceSort.setUsername(user.getRealName());

        /**
         *获取上一个排序的最大组数
         */
        Integer groupid=0,sortno=0;
        OsceSort maxOsceSort = osceSortService.getMaxOsceSort(examId); // 获取最后的一个人
        if(null!=maxOsceSort){
            groupid = maxOsceSort.getGroupid();
            sortno = maxOsceSort.getSortno();
        }else {
            groupid=1;
        }
        /**
         * 获取所有的站点数，用来决定是否换组
         */
        Integer stationNum = examComposeService.countStationByExamId(examId);
        if(sortno>=stationNum){
            groupid= groupid+1;
            sortno =1;
        }else{
            sortno=sortno+1;
        }
        osceSort.setSortno(sortno);
        osceSort.setGroupid(groupid);
        osceSort.setStationnum(stationNum);
        osceSort.setFinished(0);
        int save = osceSortService.save(osceSort);
        if(1==save){
            model.addAttribute("osceSort",osceSort);
            return "/sort/sortIndexed";
        }else {
            return "/403";
        }
    }
}
