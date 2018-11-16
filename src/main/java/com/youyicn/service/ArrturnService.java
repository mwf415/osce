package com.youyicn.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Arrturn;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface ArrturnService extends IService<Arrturn> {
	
	PageInfo<Arrturn> selectByPage(Arrturn arrturn, int start, int length, Date startTime, Date endTime);
	
}
