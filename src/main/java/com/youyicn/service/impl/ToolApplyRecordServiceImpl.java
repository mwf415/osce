package com.youyicn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.ToolApplyRecordMapper;
import com.youyicn.model.ToolApplyRecord;
import com.youyicn.model.ToolApplyRecordVo;
import com.youyicn.service.ToolApplyRecordService;
import tk.mybatis.mapper.entity.Example;

@Service("toolApplyRecordService")
@Transactional
public class ToolApplyRecordServiceImpl extends BaseService<ToolApplyRecord> implements ToolApplyRecordService {

    @Autowired
    private ToolApplyRecordMapper toolApplyRecordMapper;

    public int save(ToolApplyRecord toolApplyRecord) {
        return toolApplyRecordMapper.insert(toolApplyRecord);

    }

    @Override
    public PageInfo<ToolApplyRecord> selectByPageAssotiation(ToolApplyRecord toolApplyRecord, int start, int length) {
        int page = start / length + 1;
        //分页查询
        PageHelper.startPage(page, length);
        List<ToolApplyRecord> list = toolApplyRecordMapper.selectByEntity(toolApplyRecord);
        return new PageInfo(list);
    }

    @Override
    public PageInfo<ToolApplyRecordVo> listApplysByToolId(Integer toolId , int start, int length) {
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("start", start);
        condition.put("length", length);
        condition.put("toolId",toolId);
        List<ToolApplyRecordVo> list = toolApplyRecordMapper.listApplysByToolId(condition);
        return new PageInfo<ToolApplyRecordVo>(list);
    }

    @Override
    public List<ToolApplyRecord> timerListToReturn() {
        Date date = new Date();
        Example example = new Example(ToolApplyRecord.class);
        Example.Criteria criteria = example.createCriteria();


        criteria.andEqualTo("status", 2); // 0 报废  1 是可以借用，2 借用中   3 使用结束未归还  4 使用造成损坏。  5 维修中
        criteria.andLessThanOrEqualTo("endTime", date);

        List<ToolApplyRecord> osceSorts = toolApplyRecordMapper.selectByExample(example);
        return osceSorts;


    }

    @Override
    public ToolApplyRecord getinUseByToolId(Integer toolId) {
        Date date = new Date();
        Example example = new Example(ToolApplyRecord.class);
        Example.Criteria criteria = example.createCriteria();
        List<Integer> statusList = new ArrayList<>();
        statusList.add(2);
        statusList.add(3);
        criteria.andIn("status", statusList); // 0 报废  1 是可以借用，2 借用中   3 使用结束未归还  4 使用造成损坏。  5 维修中
        criteria.andEqualTo("toolId", toolId); // 0 报废  1 是可以借用，2 借用中   3 使用结束未归还  4 使用造成损坏。  5 维修中
        List<ToolApplyRecord> toolApplyRecords = toolApplyRecordMapper.selectByExample(example);
        if (toolApplyRecords.size() > 0) {
            return toolApplyRecords.get(0);
        }

        return null;
    }


}
