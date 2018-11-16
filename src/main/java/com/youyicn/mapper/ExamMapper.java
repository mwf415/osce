package com.youyicn.mapper;

import java.util.List;
import java.util.Map;

import com.youyicn.model.Exam;
import com.youyicn.util.MyMapper;

public interface ExamMapper extends MyMapper<Exam> {
	
	List<Exam> selectPageByCondition(Map condition);
}