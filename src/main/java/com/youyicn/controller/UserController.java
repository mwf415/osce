package com.youyicn.controller;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.User;
import com.youyicn.service.UserRoleService;
import com.youyicn.service.UserService;
import com.youyicn.util.PasswordHelper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
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

    @RequestMapping(value = "/batchSave")
    public String batchSave( String[] loginNames , String[] userNums, String[] real_names,
                             String[] user_pwds,
                             String[] baseNames, String[] roomNames,
                             Integer[] identity_ids, Integer[] grades, Byte[] trainTimes){
      try{
          List<User>userList = userService.selectByLoginNames(loginNames);
          Map<String,Integer> map = new HashMap<>();
          for (int i = 0; i < loginNames.length; i++) {
            map.put(loginNames[i],i);
          }

          if(userList.size()>0){
              StringBuilder sb = new StringBuilder();
              for (User user : userList) {
                  String loginName = user.getLoginName();
                  Integer i = map.get(loginName);
                 String realName = real_names[i];
                  sb.append(realName+";");
              }
              return sb.toString();
          }

          for (int i = 0; i < loginNames.length; i++) {
             String loginName = loginNames[i];
             String userNum = userNums[i];
             String real_name = real_names[i];
             String user_pwd = user_pwds[i];
             String baseName = baseNames[i];
             String roomName = roomNames[i];
             Integer identity_id = identity_ids[i];
             Integer grade = grades[i];
             byte trainTime = trainTimes[i];
             User user = new User();
             user.setLoginName(loginName);
              user.setUserNum(userNum);
              user.setRealName(real_name);

              user.setUserPwd(user_pwd.trim());
              user.setBaseName(baseName);
              user.setRoomName(roomName);
              user.setIdentityId(identity_id);
              user.setGrade(grade);
              user.setTraintime(trainTime);
              user.setStatus(1+"");
              PasswordHelper passwordHelper = new PasswordHelper();
              passwordHelper.encryptPassword(user);
              userService.save(user);
          }

          return "success";
      }catch (Exception e){
          e.printStackTrace();
          return "fail";
      }
    }

}
