package com.youyicn.service.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.youyicn.mapper.*;
import com.youyicn.model.*;
import com.youyicn.service.ExamService;
import com.youyicn.service.UserService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ExamServiceImpl extends BaseService<Exam> implements ExamService {
	@Resource
	private ExamMapper examMapper;
	@Resource
	private UserService userService;
	@Resource
	private ExamUserMapper examUserMapper;
	@Resource
	private ExamComposeMapper examComposeMapper;
	@Resource
	private ExamStationRecordMapper examStationScoreMapper;
	@Resource
	private StationMapper stationMapper;
	@Resource
	private RoleMapper roleMapper;
	
	@Override
	public PageInfo<Exam> selectByPage(Exam exam, Integer roleId, int start, int length) {
    	User user = (User) SecurityUtils.getSubject().getSession().getAttribute("userSession");
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("start", start);
		condition.put("length", length);
		if(roleId==null || roleId>7){
			return new PageInfo<Exam>(new ArrayList()) ;
		}else if(roleId==4 || roleId==5){
			exam.setBaseName(user.getBaseName());
		}else if(roleId==6 || roleId==7){
			exam.setRoomName(user.getRoomName());
		}
		condition.put("exam", exam);
		List<Exam> list = examMapper.selectPageByCondition(condition);
		return new PageInfo<Exam>(list) ;
	}

	@Override
	public void configExam(Exam exam, Integer[] stationIds, String[] stationNames, Integer[] questionIds, String[] questionNames,String[] addresses, String[] teacherNames) {
		
		//删除原来的考站配置数据
		Example example = new Example(ExamCompose.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("examId", exam.getId());
		examComposeMapper.deleteByExample(example);
		//生成考站配置数据
		List<ExamCompose> examComposes = Lists.newArrayList();
		for(int i=0; i<stationIds.length; i++){
			ExamCompose ec = new ExamCompose();
			ec.setExamId(exam.getId());
			ec.setExamTitle(exam.getTitle());
			ec.setStationId(stationIds[i]);
			ec.setStationName(stationNames[i]);
			ec.setQuestionId(questionIds[i]);
			ec.setQuestionTitle(questionNames[i]);
			ec.setTeacherNames(teacherNames[i]);
			ec.setAddress(addresses[i]);
			examComposes.add(ec);
		}
		examComposeMapper.insertList(examComposes);
		
		//生成考生总分记录及考站分数记录，不能再配置试卷
		String userIds = exam.getUserIds();
		String userNames = exam.getUserNames();
		if(StringUtils.isNotBlank(userIds) && ArrayUtils.isNotEmpty(stationIds) && ArrayUtils.isNotEmpty(stationIds)){
			List<ExamStationRecord> examStationScores = Lists.newArrayList();
			List<ExamUser> examUsers = Lists.newArrayList();
			String[] userIdsArr = userIds.split(",");
			for(int i=0; i<userIdsArr.length; i++){
				//生成考生总分记录
				String userId = userIdsArr[i].split("-")[0];
				String userTypeStr = userIdsArr[i].split("-")[1];
				int userType = StringUtils.isNotBlank(userTypeStr)?Integer.parseInt(userTypeStr):0;
				//获取真实姓名
				String[] userNamesArr = userNames.split(",");
				int index = userNamesArr[i].indexOf("(");
				String realName = userNamesArr[i].substring(index+1, userNamesArr[i].length()-1);
				
				ExamUser examUser = new ExamUser();
				examUser.setExamId(exam.getId());
				examUser.setExamTitle(exam.getTitle());
				examUser.setState(0);
				examUser.setUserId(userId);
				examUser.setUserType(userType);
				examUser.setRealName(realName);
				examUser.setCreateTime(new Date());
				examUsers.add(examUser);
				//生成学生考站记录
				for(int j=0; j<stationIds.length;j++){
					ExamStationRecord esr = new ExamStationRecord();
					esr.setExamId(exam.getId());
					esr.setExamTitle(exam.getTitle());
					esr.setStationId(stationIds[j]);
					esr.setStationName(stationNames[j]);
					esr.setQuestionId(questionIds[j]);
					esr.setQuestionTitle(questionNames[j]);
					esr.setUserId(userId);
					esr.setRealName(realName);
					esr.setState((byte)0);
					examStationScores.add(esr);
				}
			}
			examUserMapper.insertList(examUsers);
			examStationScoreMapper.insertList(examStationScores);
			//更新userIds, userNames
			examMapper.updateByPrimaryKeySelective(exam);
		}
		
		
	}

	@Override
	public void deleteByExamId(Integer examId) {
		//删除原来的考试配置数据
		Example example = new Example(ExamCompose.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("examId", examId);
		examComposeMapper.deleteByExample(example);
		//删除考试
		examMapper.deleteByPrimaryKey(examId);
		
	}

	/**
	 * @Description:根据老师查询负责的考站
	 * @fieldName: 
	 * @return: 
	 */
	@Override
	public PageInfo<ExamCompose> listExamComposePageByCondition(
			String teacherName, int start, int length) {
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("teacherName", teacherName);
		condition.put("start", start);
		condition.put("length", length);
		List<ExamCompose> list = examComposeMapper.listExamComposePageByCondition(condition);
		return new PageInfo<ExamCompose>(list) ;
	}
	
	/**
	 * by mowenfeng  查询老师的任务
	 */
	@Override
	public List<ExamCompose> listExamComposeByCondition(String teacherName,Integer examId) {
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("teacherName", teacherName);
		condition.put("examId", examId);
		
		List<ExamCompose> list = examComposeMapper.listExamComposeByCondition(condition);
		return list ;
	}

	@Override
	public List<Exam> listTodayExam(Date startTime, Date endTime) {
		Example example = new Example(ExamCompose.class);
		Criteria criteria = example.createCriteria();
		criteria.andGreaterThan("start_time", startTime);
		criteria.andLessThan("end_time", endTime);
		List<Exam> examList = examMapper.selectByExample(example);
		return examList;
	}

	@Override
	public List<ExamCompose> listGetAllComposeWithOutScore(String teacherName) {
		Map<String, Object> condition = Maps.newHashMap();
		condition.put("teacherName", teacherName);
		List<ExamCompose> list = examComposeMapper.listExamComposePageByCondition(condition);

		return list;
	}

}
