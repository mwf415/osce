package com.youyicn.mapper;

import org.apache.ibatis.annotations.Param;

import com.youyicn.model.OsceSort;
import com.youyicn.util.MyMapper;

public interface OsceSortMapper extends MyMapper<OsceSort> {
    OsceSort getMaxOsceSort(@Param("examId") Integer examId);

}