package com.youyicn.service;

import com.youyicn.model.UserRole;

public interface UserRoleService extends IService<UserRole> {
	void addUserRole(Long userId, Long[] roleIds);
}
