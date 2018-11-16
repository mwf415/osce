package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.ArrturnRule;
import com.youyicn.model.Base;
import com.youyicn.model.Subject;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface CycleBaseService extends IService<Base> {
	
	PageInfo<Base> selectByPage(Base base, int start, int length);
	List<Base> selectAll();
	void deleteByKey(Integer key);
	List<Subject> listSubjectByBaseId(Integer baseId);
	void saveSubjects(Integer baseId, String baseName, String subjectNames);
	//根据基地获取轮转科室
	List<ArrturnRule> listArrturnRuleByBaseName(String baseName);
}
