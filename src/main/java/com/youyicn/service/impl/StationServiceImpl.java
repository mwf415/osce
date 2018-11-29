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
import com.youyicn.mapper.StationMapper;
import com.youyicn.model.Station;
import com.youyicn.service.StationService;

@Service
@Transactional
public class StationServiceImpl extends BaseService<Station> implements StationService {
	@Resource
	   private StationMapper stationMapper;

	   @Override
	   public PageInfo<Station> selectByPage(Station station, int start, int length) {
	       int page = start/length+1;
	       Example example = new Example(Station.class);
		   example.orderBy("sortid").desc();
	       Criteria criteria = example.createCriteria();
	       if(station.getId()!=null){	
	    	   criteria.andEqualTo("id", station.getId());
	       }
	       if(StringUtils.isNotBlank(station.getName())){	
	    	   criteria.andLike("name", station.getName());
	       }
	       //分页查询
	       PageHelper.startPage(page, length);
	       List<Station> stationsList = selectByExample(example);
	       return new PageInfo<>(stationsList);
	   }


	@Override
	public List<Station> selectAll() {
		return stationMapper.selectAll();
	}

}
