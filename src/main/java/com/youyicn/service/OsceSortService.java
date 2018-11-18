package com.youyicn.service;

import java.util.List;
import java.util.Map;

import com.youyicn.model.OsceSort;
import com.youyicn.model.UserParm;
import org.apache.ibatis.annotations.Param;

public interface OsceSortService  extends IService<OsceSort> {

    OsceSort getByExamIdAndUserId(Integer examId,String userId);

    OsceSort getMaxOsceSort (Integer examId);
    
    List<OsceSort> getOsceSortByExamId(Integer examId);

    List<String > getInUserByExamId (Integer examId);

    List<String > getUnInUserByExamId( Integer examId);

    List<String >  getUserByExamId (Integer examId ,Integer stationId ,Integer state );


    public List<UserParm> getUserDetailService(Integer examId);

}
