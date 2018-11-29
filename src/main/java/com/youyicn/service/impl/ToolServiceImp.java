package com.youyicn.service.impl;
import java.util.List;

import com.youyicn.model.Station;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.ToolMapper;
import com.youyicn.model.ExamCompose;
import com.youyicn.model.Tool;
import com.youyicn.service.ToolService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;


@Service("toolService")
@Transactional
public class ToolServiceImp extends BaseService<Tool> implements ToolService {
	
	@Autowired
	private ToolMapper toolMapper;

	@Override
	public PageInfo<Tool> selectByPageAssotiation(Tool tool, int start, int length) {
		int page = start/length+1;
		Example example = new Example(Tool.class);
		example.orderBy("id").desc();
		Criteria criteria = example.createCriteria();
		if(tool.getId()!=null){
			criteria.andEqualTo("id", tool.getId());
		}
		if(StringUtils.isNotBlank(tool.getName())){
			criteria.andLike("name", "%"+tool.getName()+"%");
		}
		if(null!=tool.getToolGroupId()){
			criteria.andEqualTo("toolGroupId",tool.getToolGroupId());
		}
		//分页查询
		PageHelper.startPage(page, length);
		List<Tool> toolList = selectByExample(example);
		return new PageInfo<>(toolList);

	}


		

}
