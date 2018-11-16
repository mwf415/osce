package com.youyicn.mapper;

import java.util.List;

import com.youyicn.model.Role;
import com.youyicn.util.MyMapper;

public interface RoleMapper extends MyMapper<Role> {
	
	List<Role> queryRoleListWithSelected(Integer uid);

	List<Role> queryRoleListByUserId(Integer userId);
}