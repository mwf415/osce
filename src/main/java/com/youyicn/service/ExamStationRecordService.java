package com.youyicn.service;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.ExamStationRecord;

import java.util.List;

public interface ExamStationRecordService extends IService<ExamStationRecord> {

	PageInfo<ExamStationRecord> selectByPage(ExamStationRecord examStationRecord, int start, int length);
	List<ExamStationRecord> getByExamIdAndStationId(ExamStationRecord examStationRecord);
	
	void deleteStationRecords(Integer[] recordIds);
}
