package com.youyicn.mapper;

import com.youyicn.model.RolePermission;
import com.youyicn.util.MyMapper;

public interface RolePermissionMapper extends MyMapper<RolePermission> {
	void addRolePermission(Long rid, Long[] pids);
	
}