package com.youyicn.service.impl;

import com.youyicn.mapper.ExamComposeMapper;
import com.youyicn.model.ExamCompose;
import com.youyicn.service.ExamComposeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ExamComposeServiceImpl extends BaseService<ExamCompose> implements ExamComposeService {
	
	@Resource
	private ExamComposeMapper examComposeMapper;
	


	@Override
	public List<ExamCompose> listExamComposeByExamId(Integer examId) {
		Example example = new Example(ExamCompose.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("examId", examId);
		return examComposeMapper.selectByExample(example);
	}

	@Override
	public Integer countStationByExamId(Integer examId) {
		return examComposeMapper.countStationByExamId(examId);
	}

}
