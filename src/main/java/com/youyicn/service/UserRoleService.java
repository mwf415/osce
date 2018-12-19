package com.youyicn.service;

import com.youyicn.model.UserRole;

public interface UserRoleService extends IService<UserRole> {
	void addUserRole(long userId, Long[] roleIds);
}
