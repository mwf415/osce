package com.youyicn.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.ExamStationRecordMapper;
import com.youyicn.model.ExamStationRecord;
import com.youyicn.service.ExamStationRecordService;

@Service
@Transactional
public class ExamStationRecordServiceImpl extends BaseService<ExamStationRecord> implements ExamStationRecordService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
   @Resource
    private ExamStationRecordMapper examScoreMapper;

    @Override
    public PageInfo<ExamStationRecord> selectByPage(ExamStationRecord examStationRecord, int start, int length) {
        int page = start/length+1;
        Example example = new Example(ExamStationRecord.class);
        example.orderBy("id").desc();
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examId", examStationRecord.getExamId());
        criteria.andEqualTo("stationId", examStationRecord.getStationId());
        if(StringUtils.isNotBlank(examStationRecord.getUserId())){
        	criteria.andEqualTo("userId", examStationRecord.getUserId());
        }
        criteria.andEqualTo("state", examStationRecord.getState());
        //分页查询
        PageHelper.startPage(page, length);
        List<ExamStationRecord> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

	@Override
	public void deleteStationRecords(Integer[] recordIds) {
		Example example = new Example(ExamStationRecord.class);
        Criteria criteria = example.createCriteria();
        criteria.andIn("id", Arrays.asList(recordIds));
        mapper.deleteByExample(example);
		
	}

	@Override
    public List<ExamStationRecord> getByExamIdAndStationId(ExamStationRecord examStationRecord){
        Example example = new Example(ExamStationRecord.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examId", examStationRecord.getExamId());
        criteria.andEqualTo("stationId", examStationRecord.getStationId());
        if(StringUtils.isNotBlank(examStationRecord.getUserId())){
            criteria.andEqualTo("userId", examStationRecord.getUserId());
        }
        criteria.andEqualTo("state", examStationRecord.getState());
        List<ExamStationRecord> userList = selectByExample(example);

        return userList;
    }

    
}
