package com.youyicn.mapper;

import com.youyicn.model.ExamStationRecord;
import org.apache.ibatis.annotations.Param;

import com.youyicn.model.OsceSort;
import com.youyicn.util.MyMapper;

import java.util.List;
import java.util.Map;

public interface OsceSortMapper extends MyMapper<OsceSort> {
    OsceSort getMaxOsceSort(@Param("exam_id") Integer examId);

    List<OsceSort> getInUserByExamId (@Param( "exam_id") Integer examId); // 查询出某一个考试的所有人
    /**
     * c查询候考的人
     */
    List<OsceSort> toBeInUserByExamId (@Param("exam_id")Integer examId);



}