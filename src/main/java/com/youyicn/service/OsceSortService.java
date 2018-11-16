package com.youyicn.service;

import java.util.List;

import com.youyicn.model.OsceSort;

public interface OsceSortService  extends IService<OsceSort> {

    OsceSort getByExamIdAndUserId(Integer examId,String userId);

    OsceSort getMaxOsceSort (Integer examId);
    
    List<OsceSort> getOsceSortByExamId(Integer examId);



}
