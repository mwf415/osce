package com.youyicn.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.youyicn.mapper.PermissionMapper;
import com.youyicn.mapper.RolePermissionMapper;
import com.youyicn.model.Permission;
import com.youyicn.model.RolePermission;
import com.youyicn.module.constants.Constants;
import com.youyicn.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl extends BaseService<Permission> implements PermissionService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());  
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Override
    public PageInfo<Permission> selectByPage(Permission Permission, int start, int length) {
        int page = start/length+1;
        Example example = new Example(Permission.class);
        //分页查询
        PageHelper.startPage(page, length);
        List<Permission> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    @Cacheable(value="permissions", key="'all'")
    public List<Permission> queryAll(){
        return permissionMapper.queryAll();
    }

	@Override
	@Cacheable(value="permissions", key="'all_menu'")
	public List<Permission> queryAllMenu() {
		Example example = new Example(Permission.class);
		example.orderBy("sort");
//		example.setOrderByClause("sort desc");
		
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("systemId", Constants.SYSTEM_OSCE_ID);
		criteria.andEqualTo("type", Constants.MENU_TYPE);
		List<Permission> list = selectByExample(example);
		return list;
	}
	
    @Override
    @Cacheable(value="permissions",key="'list_'+#map['id'].toString()+'_'+#map['type']")
    public List<Permission> loadUserPermissions(Map<String, Object> map) {
    	logger.debug("loadUserPermissions id={},type={}" , new Object[]{map.get("id"),map.get("type")});
        return permissionMapper.loadUserPermissions(map);
    }

    @Override
    public List<Permission> queryPermissionsListWithSelected(Integer rid) {
        return permissionMapper.queryPermissionsListWithSelected(rid);
    }

	@Override
	@Cacheable(value="permissions",key="'tree_'+#userId")
	public List<Permission> loadUserPermissionsTree(Integer userId) {
        return getPermissions(userId);
	}

    @Override
    @CachePut(value="permissions",key="'tree_'+#userId")
    public List<Permission> updateUserPermissionsTree(Integer userId) {
        return getPermissions(userId);
    }

    private List<Permission> getPermissions(Integer userId) {
        logger.debug("loadUserPermissionsTree userId={}" , userId);
        Map<String,Object> map = Maps.newHashMap();
        map.put("type",1);
        map.put("id",userId);
        List<Permission> loadUserPermissions = null;
        if(userId==1){
            loadUserPermissions = queryAllMenu();
        }else{
            loadUserPermissions = loadUserPermissions(map);
        }
        List<Permission> list = getChildren(loadUserPermissions, 0);
        return list;
    }

    // 取节点的所有children  
    private List<Permission> getChildren(List<Permission> results, Integer rootId) {  
  
        List<Permission> list = Lists.newArrayList();  
        for (int i = 0; i < results.size(); i++) {  
            Permission root = results.get(i);  
            if (rootId == root.getPid()) {  
                List<Permission> children = getChildren(results,  root.getId());  
                if (!children.isEmpty()) {  
                    root.setChildren(children);  
                }  
                list.add(root);  
            }  
        }  
        return list;  
    }

	@Override
	public void deleteByKeys(String[] keys) {
		//删除资源
		if(keys!=null){
			//删除关联数据
			Example example1 = new Example(Permission.class);
			Criteria criteria1 = example1.createCriteria();
			criteria1.andIn("id", Arrays.asList(keys));
			mapper.deleteByExample(example1);
			//删除关联数据
			Example example = new Example(RolePermission.class);
			Criteria criteria = example.createCriteria();
			criteria.andIn("pid", Arrays.asList(keys));
			rolePermissionMapper.deleteByExample(example);
		}
	}

}
