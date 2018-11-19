package com.youyicn.mapper;

import com.youyicn.model.ExamUser;
import com.youyicn.util.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamUserMapper extends MyMapper<ExamUser> {
    List<ExamUser> getUnInUserByExamId(@Param("exam_id") Integer examId );
    List<ExamUser> getFinishedUserByExamId(@Param("exam_id") Integer examId );

}