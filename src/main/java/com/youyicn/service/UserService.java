package com.youyicn.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Role;
import com.youyicn.model.User;

public interface UserService extends IService<User>{
	
	PageInfo<User> selectByPage(User user, int start, int length);
	
	User selectByLoginName(String loginName);
	
	void delUser(Integer userid);
	
	List<User> selectByLoginNames(String[] loginNames);
	
	List<Role> listRoleByUserId(Integer userId);
}
