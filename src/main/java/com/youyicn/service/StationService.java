package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Station;


public interface StationService extends IService<Station> {
	
	PageInfo<Station> selectByPage(Station station, int start, int length);
	
	List<Station> selectAll();

}
