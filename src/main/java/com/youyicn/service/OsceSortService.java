package com.youyicn.service;

import java.util.List;

import com.youyicn.model.OsceSort;
import com.youyicn.model.UserParm;

public interface OsceSortService  extends IService<OsceSort> {

    OsceSort getByExamIdAndUserId(Integer examId,String userId);

    OsceSort getMaxOsceSort (Integer examId);
    
    List<OsceSort> getOsceSortByExamId(Integer examId);


    // 查看没有报名的学生
    List<UserParm> getUnInUserByExamId(Integer examId);
    // 查看考站的学生情况

    List<String >  getUserByExamId (Integer examId ,Integer stationId ,Integer state );

    // 查看正在考考试的，考生的详细情况,
    public List<UserParm> getUserDetailService(Integer examId);

    // 查找已经考完的学生
    public String getFinishedUser(Integer examId);


}
