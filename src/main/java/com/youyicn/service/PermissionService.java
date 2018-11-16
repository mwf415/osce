package com.youyicn.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Permission;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface PermissionService extends IService<Permission> {
	PageInfo<Permission> selectByPage(Permission Permission, int start, int length);
	List<Permission> queryAll();
	List<Permission> queryAllMenu();
	List<Permission> loadUserPermissions(Map<String, Object> map);
	List<Permission> queryPermissionsListWithSelected(Integer rid);
	List<Permission> loadUserPermissionsTree(Integer userId);
	void deleteByKeys(String[] keys);
}
