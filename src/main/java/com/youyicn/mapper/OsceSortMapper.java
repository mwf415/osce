package com.youyicn.mapper;

import com.youyicn.model.ExamStationRecord;
import org.apache.ibatis.annotations.Param;

import com.youyicn.model.OsceSort;
import com.youyicn.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface OsceSortMapper extends MyMapper<OsceSort> {
    OsceSort getMaxOsceSort(@Param("exam_id") Integer examId);
    List<String > getInUserByExamId (@Param( "exam_id") Integer examId);

    List<String > getUnInUserByExamId(@Param( "exam_id") Integer examId);
    // 查詢某一考试所有参加考生的学员的详细情况
    List<ExamStationRecord > getUserDetailByExamId(@Param( "exam_id") Integer examId);

    List<ExamStationRecord>  getUserByExamId (Map<String,Integer> parm);
}