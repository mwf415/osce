package com.youyicn.service;

import com.youyicn.model.RolePermission;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface RolePermissionService extends IService<RolePermission> {
	
	public void addRolePermission(Long rid, Long[] pids);
	
	/**
	 * discription:当删除资源的时候，同时删除关联数据
	 * @param ids 资源ids
	 */
	void deleteByPermissionKeys(String[] ids);
	
}
