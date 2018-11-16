package com.youyicn.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.ArrturnMapper;
import com.youyicn.model.Arrturn;
import com.youyicn.service.ArrturnService;

@Service
public class ArrturnServiceImpl extends BaseService<Arrturn> implements ArrturnService {
	@Resource
	private ArrturnMapper arrturnMapper;

	@Override
	public PageInfo<Arrturn> selectByPage(Arrturn arrturn, int start, int length, Date startTime, Date endTime) {
		int page = start / length + 1;
		Example example = new Example(Arrturn.class);
		Criteria criteria = example.createCriteria();
		if (StringUtils.isNotBlank(arrturn.getBaseName())) {
			criteria.andEqualTo("baseName", arrturn.getBaseName());
		}
		if (StringUtils.isNotBlank(arrturn.getRoomName())) {
			criteria.andEqualTo("roomName", arrturn.getRoomName());
		}
		if (StringUtils.isNotBlank(arrturn.getLoginName())) {
			criteria.andLike("loginName", "%"+arrturn.getLoginName()+"%");
		}
		if (StringUtils.isNotBlank(arrturn.getRealName())) {
			criteria.andLike("realName", "%"+arrturn.getRealName()+"%");
		}
		criteria.andBetween("endTime", startTime, endTime);
		// 分页查询
		PageHelper.startPage(page, length);
		List<Arrturn> arrturnsList = selectByExample(example);
		return new PageInfo<>(arrturnsList);
	}

}
