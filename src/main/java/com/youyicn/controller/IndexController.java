package com.youyicn.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.youyicn.model.Base;
import com.youyicn.model.Role;
import com.youyicn.model.Room;
import com.youyicn.model.Station;
import com.youyicn.model.User;
import com.youyicn.service.CycleBaseService;
import com.youyicn.service.RoleService;
import com.youyicn.service.RoomService;
import com.youyicn.service.StationService;
import com.youyicn.service.UserService;

/**
 * Created by yangqj on 2017/4/21.
 */
@Controller
public class IndexController {
	
	@Resource
	private UserService userService;
	@Resource
	private RoleService roleService;
	@Resource
	private CycleBaseService baseService;
    @Resource
    private RoomService roomService;
	@Resource
	private StationService stationService;
	
    @RequestMapping(value="/login",method= RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(HttpServletRequest request, User user, Model model){
        if (StringUtils.isEmpty(user.getLoginName()) || StringUtils.isEmpty(user.getUserPwd())) {
            request.setAttribute("msg", "用户名或密码不能为空！");
            return "/login";
        }
        User u = userService.selectByLoginName(user.getLoginName());
        if(u==null || u.getIdentityId()!=user.getIdentityId()){
            request.setAttribute("msg", "用户不存在！");
            return "/login";
        }
        UsernamePasswordToken token = null;
        try {
        	Subject subject = SecurityUtils.getSubject();
        	if(user.getUserPwd()!=null){
        		token=new UsernamePasswordToken(user.getLoginName(),user.getUserPwd());
        		subject.login(token);
        		if(user.getIdentityId()==2)//如果是学生，跳转到学生页面
        			return "redirect:/myExams/examsPage";
        		return "redirect:examsPage";
        	}
        }catch (LockedAccountException lae) {
            request.setAttribute("msg", "用户已经被锁定不能登录，请与管理员联系！");
        } catch (AuthenticationException e) {
            request.setAttribute("msg", "用户或密码不正确！");
        }
        
        if(token!=null){
        	token.clear();
        }
        return "login";
        
        
    }
    @RequestMapping(value="/usersPage")
    public String usersPage(){
        return "user/users";
    }

    @RequestMapping("/rolesPage")
    public String rolesPage(){
        return "role/roles";
    }

    @RequestMapping("/permissionsPage")
    public String permissionsPage(){
        return "resources/resources";
    }
    
    @RequestMapping("/stationsPage")
    public String stationsPage(){
        return "station/stations";
    }
    @RequestMapping("/groupsPage")
    public String toolGroupPage(){
    	return "group/groups";
    }
    
    @RequestMapping("/toolsPage")
    public String toolPage(){
    	return "tool/tools";
    }

    @RequestMapping("/applyPage")
    public String applyPage(){
    	return "apply/applys";
    }

    /**
     * 动态监控页面
     * @return
     */
    @RequestMapping("/allMonitorPage")
    public String allMonitor(Model model){
        model.addAttribute("topTitle","全院监控");
        return "/monitor/monitor";
    }

    @RequestMapping("/baseMonitorPage")
    public String baseMonitor(Model model){
        model.addAttribute("topTitle","基地监控");
        return "/monitor/monitor";
    }

    @RequestMapping("/roomMonitorPage")
    public String roomMonitor(Model model){
        model.addAttribute("topTitle","科室监控");
        return "/monitor/monitor";
    }

    @RequestMapping("/stationMonitorPage")
    public String stationMonitor(Model model){
        model.addAttribute("topTitle","站点监控");
        return "/monitor/monitor";
    }


    @RequestMapping("/questionsPage")
    public String questionsPage(Model model){
    	List<Base> bases = baseService.selectAll();
    	List<Room> rooms = roomService.selectAll();
    	model.addAttribute("bases", bases);
    	model.addAttribute("rooms", rooms);
        return "/question/questions";
    }

    /**
     * 安排考试
     * @param model
     * @return
     */
    @RequestMapping(value={"/examsPage",""})
    public String examsPage(Model model){
    	Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
    	List<Role> roles = roleService.queryRoleListByUserId(userId);
    	List<Base> bases = baseService.selectAll();
    	List<Room> rooms = roomService.selectAll();
    	model.addAttribute("roles", roles);
    	model.addAttribute("bases", bases);
    	model.addAttribute("rooms", rooms);
        return "/exam/exams";
    }

    /**
     * 考试详情
     * @param model
     * @return
     */
    @RequestMapping(value={"/examsDetail"})
    public String examsDetail(Model model){
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        List<Role> roles = roleService.queryRoleListByUserId(userId);
        List<Base> bases = baseService.selectAll();
        List<Room> rooms = roomService.selectAll();
        model.addAttribute("roles", roles);
        model.addAttribute("bases", bases);
        model.addAttribute("rooms", rooms);
        return "/exam/exams_detail";
    }
    @RequestMapping("/basesPage")
    public String baseStationItemsPage(Model model){
    	List<Base> bases = baseService.selectAll();
    	List<Station> stations = stationService.selectAll();
    	model.addAttribute("bases", bases);
    	model.addAttribute("stations", stations);
        return "/base/bases";
    }
    
    @RequestMapping("/403")
    public String forbidden(){
        return "403";
    }
}
