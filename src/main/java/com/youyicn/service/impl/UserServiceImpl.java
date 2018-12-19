package com.youyicn.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.youyicn.mapper.RoleMapper;
import com.youyicn.mapper.UserMapper;
import com.youyicn.mapper.UserRoleMapper;
import com.youyicn.model.Role;
import com.youyicn.model.User;
import com.youyicn.model.UserRole;
import com.youyicn.service.UserService;

/**
 * Created by yangqj on 2017/4/21.
 */
@Service("userService")
@Transactional
public class UserServiceImpl extends BaseService<User> implements UserService{
    @Resource
    private UserRoleMapper UserRoleMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;
    @Override
    public PageInfo<User> selectByPage(User user, int start, int length) {
        int page = start/length+1;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if(null!=user.getIdentityId()){
            criteria.andEqualTo("identityId", user.getIdentityId());
        }
        if(StringUtils.isNotBlank(user.getBaseName())){
        	criteria.andEqualTo("baseName", user.getBaseName());
        }
        if(StringUtils.isNotBlank(user.getLoginName())){
        	criteria.andLike("loginName", "%"+user.getLoginName()+"%");
        }
        if(StringUtils.isNotBlank(user.getRoomName())){
        	criteria.andEqualTo("roomName", user.getRoomName());
        }
        if(StringUtils.isNotBlank(user.getRealName())){
        	criteria.andLike("realName", "%"+user.getRealName()+"%");
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<User> userList = selectByExample(example);
        return new PageInfo<>(userList);
    }

    @Override
    public User selectByLoginName(String loginName) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("loginName",loginName);
        List<User> userList = selectByExample(example);
        if(userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delUser(Integer userid) {
        //删除用户表
        mapper.deleteByPrimaryKey(userid);
        //删除用户角色表
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("uid",userid);
        UserRoleMapper.deleteByExample(example);
    }

	@Override
	public List<User> selectByLoginNames(String[] loginNames) {
		Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("loginName",Arrays.asList(loginNames));
        List<User> list = userMapper.selectByExample(example);
        return list;
	}

	/**
	 * @Description:
	 * @fieldName: 
	 * @return: 
	 */
	@Override
	public List<Role> listRoleByUserId(Integer userId) {
		List<Role> roles = roleMapper.queryRoleListWithSelected(userId);
		return roles;
	}

	
}
