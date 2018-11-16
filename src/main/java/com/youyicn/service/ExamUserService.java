package com.youyicn.service;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.ExamUser;

public interface ExamUserService extends IService<ExamUser>{
	
	PageInfo<ExamUser> selectByPage(ExamUser examUser, int start, int length);
	//删除学生考试记录
	void deleteUserRecords(Integer examId, String[] userIds);
}
