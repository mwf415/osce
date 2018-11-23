package com.youyicn.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.youyicn.mapper.ExamStationRecordMapper;
import com.youyicn.mapper.ExamUserMapper;
import com.youyicn.model.ExamStationRecord;
import com.youyicn.model.ExamUser;
import com.youyicn.service.ExamUserService;

/**
 * Created by yangqj on 2017/4/21.
 */
@Service("examUserService")
@Transactional
public class ExamUserServiceImpl extends BaseService<ExamUser> implements ExamUserService {
    @Resource
    private ExamUserMapper examUserMapper;
    @Resource
    private ExamStationRecordMapper examStationRecordMapper;

    @Override
    public PageInfo<ExamUser> selectByPage(ExamUser examUser, int start, int length) {
        int page = start / length + 1;
        Example example = new Example(ExamUser.class);
        example.orderBy("createTime desc");
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examId", examUser.getExamId());
        if (StringUtils.isNotBlank(examUser.getUserId()))
            criteria.andEqualTo("userId", examUser.getUserId());
        if (StringUtils.isNotBlank(examUser.getRealName()))
            criteria.andEqualTo("realName", examUser.getRealName());
        criteria.andEqualTo("userType", examUser.getUserType());
        criteria.andEqualTo("state", examUser.getState());
        //分页查询
        PageHelper.startPage(page, length);
        List<ExamUser> examUserList = selectByExample(example);
        return new PageInfo<>(examUserList);
    }

    @Override
    public void deleteUserRecords(Integer examId, String[] userIds) {
        //删除考生考试记录
        Example example = new Example(ExamUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examId", examId);
        criteria.andIn("userId", Arrays.asList(userIds));
        examUserMapper.deleteByExample(example);
        //删除考生相关考站记录
        Example example1 = new Example(ExamStationRecord.class);
        Criteria criteria1 = example1.createCriteria();
        criteria1.andEqualTo("examId", examId);
        criteria1.andIn("userId", Arrays.asList(userIds));
        examStationRecordMapper.deleteByExample(example1);
    }

    @Override
    public List<ExamUser> selectById(Integer examId) {
        Example example = new Example(ExamUser.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examId", examId);
        List<ExamUser> examUsers = examUserMapper.selectByExample(example);
        return examUsers;
    }

}
