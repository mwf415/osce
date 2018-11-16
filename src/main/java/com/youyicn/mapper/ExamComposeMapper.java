package com.youyicn.mapper;

import com.youyicn.model.ExamCompose;
import com.youyicn.util.MyMapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ExamComposeMapper extends MyMapper<ExamCompose> {
	
	List<ExamCompose> listExamComposePageByCondition(Map condition);
	List<ExamCompose> listExamComposeByCondition(Map condition);

	Integer countStationByExamId(@Param("examId") Integer examId);
	
}