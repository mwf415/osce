package com.youyicn.mapper;

import com.youyicn.model.ExamStationRecord;
import com.youyicn.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamStationRecordMapper extends MyMapper<ExamStationRecord> {
    // 查詢某一考试所有参加考生的学员的详细情况
    List<ExamStationRecord > getUserDetailByExamId(@Param( "exam_id") Integer examId);
}