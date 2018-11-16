package com.youyicn.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.youyicn.model.UserRole;
import com.youyicn.service.UserRoleService;

@Service
@Transactional
public class UserRoleServiceImpl extends BaseService<UserRole> implements UserRoleService {

   @Override
   @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
   public void addUserRole(Long userId, Long[] roleIds) {
       //删除
       Example example = new Example(UserRole.class);
       Example.Criteria criteria = example.createCriteria();
       criteria.andEqualTo("uid", userId);
       mapper.deleteByExample(example);
       //添加
       for (Long roleId : roleIds) {
           UserRole u = new UserRole();
           u.setUid(userId);
           u.setRid(roleId);
           mapper.insertSelective(u);
       }

   }
}
