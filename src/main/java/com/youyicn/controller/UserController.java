package com.youyicn.controller;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.User;
import com.youyicn.model.UserRole;
import com.youyicn.service.UserRoleService;
import com.youyicn.service.UserService;
import com.youyicn.util.PasswordHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/22.
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private UserRoleService userRoleService;

    @RequestMapping
    public Map<String,Object> getAll(User user, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<User> pageInfo = userService.selectByPage(user, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    /**
     * 保存用户角色
     * @param
     * @return
     */
    @RequestMapping("/saveUserRoles")
    public String saveUserRoles(Long uid, Long[] rids){
        if(StringUtils.isEmpty(uid))
            return "error";
        try {
            userRoleService.addUserRole(uid, rids);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/add")
    public String add(User user) {
        User u = userService.selectByLoginName(user.getLoginName());
        if(u != null)
            return "error";
        try {
//            user.setEnable(1);
            PasswordHelper passwordHelper = new PasswordHelper();
            passwordHelper.encryptPassword(user);
            user.setIdentityId(1);
            user.setStatus(1+"");
            userService.save(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
      try{
          userService.delUser(id);
          return "success";
      }catch (Exception e){
          e.printStackTrace();
          return "fail";
      }
    }

}
