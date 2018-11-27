package com.youyicn.service.impl;

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
import com.youyicn.mapper.ArrturnRuleMapper;
import com.youyicn.mapper.BaseMapper;
import com.youyicn.mapper.SubjectMapper;
import com.youyicn.model.ArrturnRule;
import com.youyicn.model.Base;
import com.youyicn.model.Subject;
import com.youyicn.service.CycleBaseService;

@Service
@Transactional
public class CycleBaseServiceImpl extends BaseService<Base> implements CycleBaseService {
	@Resource
	private BaseMapper baseMapper;
	@Resource
	private SubjectMapper subjectMapper;
	@Resource
	private ArrturnRuleMapper arrturnRuleMapper;
	
	@Override
	public PageInfo<Base> selectByPage(Base base, int start, int length) {
		int page = start/length+1;
		Example example = new Example(Base.class);
		example.orderBy("id").desc();
		Criteria criteria = example.createCriteria();
		if(base.getBaseNum()!=null){
			criteria.andEqualTo("baseNum", base.getBaseNum());
		}
		if(StringUtils.isNotBlank(base.getValue())){
			criteria.andLike("baseNum", "%" + base.getValue() + "%");
		}
		
		PageHelper.startPage(page, length);
		List<Base> list = mapper.selectByExample(example);
		return new PageInfo(list);
	}
	
	@Override
	public List<Base> selectAll() {
		return baseMapper.selectAll();
	}

	@Override
	public void deleteByKey(Integer key) {
		mapper.deleteByPrimaryKey(key);
		//删除基地下的专业
		Example example = new Example(Subject.class);
		Criteria criteria = example.createCriteria();
		subjectMapper.deleteByExample(example);
	}

	@Override
	public List<Subject> listSubjectByBaseId(Integer baseId) {
		Example example = new Example(Subject.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("baseId", baseId);
		List<Subject> list = subjectMapper.selectByExample(example);
		return list;
	}

	@Override
	public void saveSubjects(Integer baseId, String baseName, String subjectNames) {
		//删除原来的专业
		Example example = new Example(Subject.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("baseId", baseId);
		subjectMapper.deleteByExample(example);
		//新增专业
		List<Subject> subjects = Lists.newArrayList();
		if(StringUtils.isNotBlank(subjectNames)){
			for(String name: subjectNames.split(",")){
				Subject s = new Subject();
				s.setBaseId(baseId);
				s.setBaseName(baseName);
				s.setName(name);
				subjects.add(s);
			}
		}
		subjectMapper.insertList(subjects);
		
	}

	@Override
	public List<ArrturnRule> listArrturnRuleByBaseName(String baseName) {
		Example example = new Example(ArrturnRule.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("baseName", baseName);
		List<ArrturnRule> list = arrturnRuleMapper.selectByExample(example);
		return list;
	}


   
}
