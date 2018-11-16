package com.youyicn.mapper;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Permission;
import com.youyicn.util.MyMapper;

public interface PermissionMapper extends MyMapper<Permission> {
	PageInfo<Permission> selectByPage(Permission permission, int start, int length);
	List<Permission> queryAll();
	List<Permission> loadUserPermissions(Map<String, Object> map);
	List<Permission> queryPermissionsListWithSelected(Integer rid);
}