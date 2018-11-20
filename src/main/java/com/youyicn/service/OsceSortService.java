package com.youyicn.service;

import java.util.List;

import com.youyicn.model.Exam;
import com.youyicn.model.OsceSort;
import com.youyicn.model.UserParm;

public interface OsceSortService  extends IService<OsceSort> {

    OsceSort getByExamIdAndUserId(Integer examId,String userId);

    OsceSort getMaxOsceSort (Integer examId);
    
    List<OsceSort> getOsceSortByExamId(Integer examId);


    // 查看没有报名的学生
    String getUnInUserByExamId(Integer examId);

    // 查看正在考考试的，考生的详细情况,
    public List<UserParm> getInUserByExamId(Integer examId);

    // 查找已经考完的学生
    public String getFinishedUser(Integer examId);

    // 查找候考学生
    public  String toInExamByExamId(Integer examId);

    public Exam getExamByExamId(Integer examId);


}
