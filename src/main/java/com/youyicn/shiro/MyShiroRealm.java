package com.youyicn.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.youyicn.model.Permission;
import com.youyicn.model.User;
import com.youyicn.module.constants.Constants;
import com.youyicn.service.PermissionService;
import com.youyicn.service.UserService;

/**
 * Created by yangqj on 2017/4/21.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user= (User) SecurityUtils.getSubject().getPrincipal();//User{id=1, username='evaluate', password='3ef7164d1f6167cb9f2658c07d3c2f0a', enable=1}
        Integer userId = user.getId();
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id",userId);
        List<Permission> loadUserPermissions = null;
        if(userId == 1){
        	loadUserPermissions = permissionService.queryAll();
        }else{
        	loadUserPermissions = permissionService.loadUserPermissions(map);
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(Permission permissions: loadUserPermissions){
            info.addStringPermission(permissions.getUrl());
        }
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	//获取用户的输入的账号.
        String loginName = (String)authcToken.getPrincipal();
        User user = userService.selectByLoginName(loginName);
        if(user==null) throw new UnknownAccountException();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getUserPwd(), //密码
                getName()  //realm name
        );
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return authenticationInfo;
    }

    /**
     * 指定principalCollection 清除
     */
  /*  public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {

        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principalCollection, getName());
        super.clearCachedAuthorizationInfo(principals);
    }
*/
}
