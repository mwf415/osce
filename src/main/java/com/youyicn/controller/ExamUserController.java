package com.youyicn.controller;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.*;
import com.youyicn.service.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.InvalidSessionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * discription:考试记录
 * @author zhangxiaowei02
 * 2018年5月8日
 */
@Controller
@RequestMapping("/examUsers")
public class ExamUserController {
	
    @Resource
    private ExamService examService;
    
    @Resource
    private ExamUserService examUserService;
    
    @Resource
    private UserService userService;
    
    @Resource
    private StationService stationService;
    
    @Resource
    private ExamStationRecordService examStationRecordService;
    
    @Resource
    private QuestionService questionService;

    @Resource
    private  OsceSortService osceSortService;


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

    @RequestMapping(value = "/deleteUserRecords")
    @ResponseBody
    public String deleteUserRecords(Integer examId, String[] userIds){
      try{
          examUserService.deleteUserRecords(examId, userIds);
          return "success";
      }catch (Exception e){
          e.printStackTrace();
          return "fail";
      }
    }

    @RequestMapping(value = "/deleteStationRecords")
    @ResponseBody
    public String deleteStationRecords(Integer[] recordIds){
      try{
    	  examStationRecordService.deleteStationRecords(recordIds);
          return "success";
      }catch (Exception e){
          e.printStackTrace();
          return "fail";
      }
    }
    
    /**
     * discription:考试用户详情
     * @param examId
     * @param model
     * @return
     */
    @RequestMapping(value="/users")
    public String users(@RequestParam Integer examId, Model model){
        model.addAttribute("examId", examId);
		return "/examUser/users";
    }
    
    @RequestMapping(value="/stationRecordListPage")
    public String stationRecordListPage(ExamStationRecord examStationRecord, Model model){
    	List<Station> stations = stationService.selectAll();
    	model.addAttribute("stations", stations);
    	model.addAttribute("examStationRecord", examStationRecord);
		return "/examUser/station_record_list";
    	
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
		return "/examUser/station_record_detail";
    	
    }
    
    
    @RequestMapping(value="/stationScoreItem")
    public String stationScoreItem(@RequestParam Integer stationRecordId, Model model){
    	ExamStationRecord examStationRecord = examStationRecordService.selectByKey(stationRecordId);
    	List<ScoreItem> scoreItems = questionService.listScoreItemByQuestionId(examStationRecord.getQuestionId());
    	User user = null;
    	if(examStationRecord!=null){
    		user = userService.selectByLoginName(examStationRecord.getUserId());
    	}


    	model.addAttribute("examStationRecord", examStationRecord);
    	model.addAttribute("scoreItems", scoreItems);
    	model.addAttribute("user", user);
    	return "/examUser/station_score_item";
    	
    }
    
    @RequestMapping(value="/saveStationScore")
    @ResponseBody
    public String saveStationScore(ExamStationRecord stationRecord, Model model){
    	try {
			User user = (User)SecurityUtils.getSubject().getSession().getAttribute("userSession");
			stationRecord.setUpdateTime(new Date());
			stationRecord.setUpdateBy(user.getLoginName());
			stationRecord.setState((byte)1);
			examStationRecordService.updateNotNull(stationRecord);

			OsceSort osceSort = osceSortService.getByExamIdAndUserId(stationRecord.getExamId(), stationRecord.getUserId());
            if(null!=osceSort){
                Integer finished = osceSort.getFinished();
                if(null==finished){
                    finished=1;
                }else {
                    finished= finished+1;
                }
                osceSort.setFinished(finished);
                osceSortService.updateNotNull(osceSort);
            }
			//查询考生所有考站记录
			ExamStationRecord temp = new ExamStationRecord();
			temp.setExamId(stationRecord.getExamId());
			temp.setUserId(stationRecord.getUserId());
			List<ExamStationRecord> examStationRecords = examStationRecordService.selectByPage(temp, 0, 25).getList();
			//判断是否考试完成，如果完成，计算总分并保存
			boolean flag = true;
			int sumScore = 0;
			for(ExamStationRecord examStationRecord: examStationRecords){
				Integer score = examStationRecord.getScore();
				if(score==null){
					flag = false;
					break;
				}
				sumScore += score;
			}
			if(flag){
				ExamUser example = new ExamUser();
				example.setUserId(stationRecord.getUserId());
				example.setExamId(stationRecord.getExamId());
				List<ExamUser> examUsers =  examUserService.selectByPage(example, 0, 1).getList();
				if(CollectionUtils.isNotEmpty(examUsers)){
					ExamUser examUser = examUsers.get(0);
					examUser.setState(1);
					examUser.setScore(sumScore);
					examUser.setUpdateTime(new Date());
					examUserService.updateNotNull(examUser);
				}
			}
		} catch (InvalidSessionException e) {
			e.printStackTrace();
			return "fail";
		}
    	return "success";
    	
    }
    
    @RequestMapping(value="/monitorUsers")
    public String monitorUsers(@RequestParam Integer examId, @RequestParam Integer stationId, Model model){
        model.addAttribute("examId", examId);
        model.addAttribute("stationId", stationId);
		return "/exam/monitor_users";
    }
    
    @RequestMapping(value="/monitorItem")
    public String monitorItem(@RequestParam Integer stationRecordId, Model model){
    	ExamStationRecord examStationRecord = examStationRecordService.selectByKey(stationRecordId);
        Integer questionId = examStationRecord.getQuestionId();
        Question question = questionService.selectByKey(questionId);
        Integer duration = question.getDuration();


        List<ScoreItem> scoreItems = questionService.listScoreItemByQuestionId(examStationRecord.getQuestionId());
    	User user = null;
    	if(examStationRecord!=null){
    		user = userService.selectByLoginName(examStationRecord.getUserId());
    	}
    	model.addAttribute("examStationRecord", examStationRecord);
    	model.addAttribute("scoreItems", scoreItems);
    	model.addAttribute("user", user);
    	model.addAttribute("duration", duration);
		return "/exam/monitor_item";
    	
    }
    
    @RequestMapping(value="/monitorDetail")
    public String monitorDetail(@RequestParam Integer stationRecordId, Model model){
    	ExamStationRecord examStationRecord = examStationRecordService.selectByKey(stationRecordId);
    	User user = null;
    	if(examStationRecord!=null){
    		user = userService.selectByLoginName(examStationRecord.getUserId());
    	}
    	model.addAttribute("examStationRecord", examStationRecord);
    	model.addAttribute("user", user);
		return "/exam/monitor_detail";
    	
    }
    
}
