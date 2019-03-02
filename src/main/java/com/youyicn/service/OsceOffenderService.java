package com.youyicn.service;

import com.youyicn.model.OsceOffender;

import java.util.Map;

public interface OsceOffenderService {
    Map<String,OsceOffender> getByExamId(Integer examId);


}
