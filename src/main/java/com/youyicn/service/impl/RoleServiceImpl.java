package com.youyicn.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.RoleMapper;
import com.youyicn.mapper.RolePermissionMapper;
import com.youyicn.model.Role;
import com.youyicn.model.RolePermission;
import com.youyicn.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends BaseService<Role> implements RoleService {
   @Resource
   private RoleMapper roleMapper;
   @Resource
   private RolePermissionMapper rolePermissionMapper;

   @Override
   public List<Role> queryRoleListWithSelected(Integer uid) {
       return roleMapper.queryRoleListWithSelected(uid);
   }

   @Override
   public PageInfo<Role> selectByPage(Role role, int start, int length) {
       int page = start/length+1;
       Example example = new Example(Role.class);
       //分页查询
       PageHelper.startPage(page, length);
       List<Role> rolesList = selectByExample(example);
       return new PageInfo<>(rolesList);
   }

   @Override
   @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
   public void delRole(Integer roleid) {
       //删除角色
       mapper.deleteByPrimaryKey(roleid);
       //删除角色资源
       Example example = new Example(RolePermission.class);
       Example.Criteria criteria = example.createCriteria();
       criteria.andEqualTo("rid",roleid);
       rolePermissionMapper.deleteByExample(example);

   }

/**
 * @Description:
 * @fieldName: 
 * @return: 
 */
@Override
public List<Role> queryRoleListByUserId(Integer userId) {
	
	return roleMapper.queryRoleListByUserId(userId);
}
}
