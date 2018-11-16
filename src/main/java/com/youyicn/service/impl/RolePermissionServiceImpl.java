package com.youyicn.service.impl;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.youyicn.mapper.RolePermissionMapper;
import com.youyicn.model.RolePermission;
import com.youyicn.service.RolePermissionService;

@Service
@Transactional
public class RolePermissionServiceImpl extends BaseService<RolePermission> implements RolePermissionService {
   
	@Override
    //更新权限
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    @CacheEvict(cacheNames="permissions", allEntries=true)
    public void addRolePermission(Long rid, Long[] pids){
        //删除
        Example example = new Example(RolePermission.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("rid", rid);
        mapper.deleteByExample(example);
        //添加
        for(Long pid: pids){
        	RolePermission record = new RolePermission();
        	record.setRid(rid);
        	record.setPid(pid);
        	mapper.insertSelective(record);
        }
	}

	@Override
	public void deleteByPermissionKeys(String[] ids) {
		Example example = new Example(RolePermission.class);
		Criteria criteria = example.createCriteria();
		criteria.andIn("pid", Arrays.asList(ids));
		mapper.deleteByExample(example);
	}
}
