package com.youyicn.service.impl;

import com.youyicn.mapper.OsceOffenderMapper;
import com.youyicn.model.OsceOffender;
import com.youyicn.service.OsceOffenderService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class OsceOffenderServiceImpl implements OsceOffenderService {
    @Autowired
    private OsceOffenderMapper osceOffenderMapper;

    @Override
    public Map<String,OsceOffender> getByExamId(Integer examId) {
        Example example = new Example(OsceOffender.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("examId", examId);
        List<OsceOffender> osceOffenderList = osceOffenderMapper.selectByExample(example);
        Map<String ,OsceOffender >  map = new HashedMap();
        if (osceOffenderList.size()>0){
            for (OsceOffender osceOffender : osceOffenderList) {
                map.put(osceOffender.getUserId(),osceOffender);

            }
        }
        return map;
    }
}
