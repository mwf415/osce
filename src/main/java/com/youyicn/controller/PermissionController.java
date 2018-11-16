package com.youyicn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Permission;
import com.youyicn.service.PermissionService;
import com.youyicn.service.RolePermissionService;
import com.youyicn.shiro.ShiroService;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    @Resource
    private PermissionService permissionService;
    @Resource
    private RolePermissionService rolePermissionService;
    @Resource
    private ShiroService shiroService;

    @RequestMapping
    public Map<String,Object> getAll(Permission permissions, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<Permission> pageInfo = permissionService.selectByPage(permissions, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/permissionsWithSelected")
    public List<Permission> permissionsWithSelected(Integer rid){
        return permissionService.queryPermissionsListWithSelected(rid);
    }

    @RequestMapping("/loadMenu")
    public List<Permission> loadMenu(){
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        List<Permission> permissionsList = permissionService.loadUserPermissionsTree(userId);;
        return permissionsList;
    }

    @CacheEvict(cacheNames="permissions", allEntries=true)
    @RequestMapping(value = "/add")
    public String add(Permission permission){
        try{
            permissionService.save(permission);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
    @CacheEvict(cacheNames="permissions", allEntries=true)
    @RequestMapping(value = "/update")
    public String update(Permission permission){
        try{
            permissionService.updateNotNull(permission);
            //更新权限
            shiroService.updatePermission();
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
    @CacheEvict(cacheNames="permissions", allEntries=true)
    @RequestMapping(value = "/delete")
    public String delete(String ids){
        try{
        	if(StringUtils.isNotBlank(ids)){
        		permissionService.deleteByKeys(ids.split(","));
        		//更新权限
        		shiroService.updatePermission();
        		return "success";
        	}
        	 return "fail";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
}
