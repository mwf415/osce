package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Exam;
import com.youyicn.model.ExamCompose;
import com.youyicn.model.ExamUser;

public interface ExamService extends IService<Exam> {

	PageInfo<Exam> selectByPage(Exam exam, Integer roleId, int start, int length);

	void configExam(Exam exam, Integer[] stationIds, String[] stationNames, Integer[] questionIds, String[] questionNames,String[] addresses, String[] teacherNames);
	
	void deleteByExamId(Integer examId);

	PageInfo<ExamCompose> listExamComposePageByCondition(String teacherName, int start,int length);

	List<ExamCompose>  listGetAllComposeWithOutScore(String teacherName);
	
	List<ExamCompose> listExamComposeByCondition(String teacherName,Integer examId);

}
