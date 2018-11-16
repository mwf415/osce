package com.youyicn.service;

import com.youyicn.model.ExamCompose;

import java.util.List;

public interface ExamComposeService extends IService<ExamCompose> {
	
	List<ExamCompose> listExamComposeByExamId(Integer examId);
	Integer countStationByExamId(Integer examId); // 查询出来某个examId的站点数量
}
