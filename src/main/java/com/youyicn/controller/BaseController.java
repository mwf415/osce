package com.youyicn.controller;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Base;
import com.youyicn.service.CycleBaseService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangqj on 2017/4/26.
 */
@RestController
@RequestMapping("/bases")
public class BaseController {
    @Resource
    private CycleBaseService baseService;
    
    @RequestMapping
    public  Map<String,Object> getAll(Base base, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<Base> pageInfo = baseService.selectByPage(base, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }
    
    @RequestMapping("getAll")
    public  Map<String,Object> getAll(){
        Map<String,Object> map = new HashMap<>();
        boolean success = false;
        String msg = "获取数据失败！";
        Object data = null;
        
        List<Base> list = null;
        try {
        	list = baseService.selectAll();
        	success = true;
        	data = list;
        	msg = "获取数据成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        map.put("success", success);
        map.put("data", data);
        map.put("msg", msg);
        return map;
    }
    
    @RequestMapping(value = "/add")
    public String add(Base base) {
        try {
            baseService.save(base);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            baseService.deleteByKey(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value = "/update")
    public String update(Base base) {
        try {
            baseService.updateNotNull(base);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    /**
     * discription:根据基地查找专业
     * @param baseId
     * @return
     */
    @RequestMapping(value = "/listSubjectByBaseId")
    public Map<String, Object> listSubjectByBaseId(Integer baseId) {
        Map<String,Object> map = new HashMap<>();
        boolean success = false;
        String msg = "获取数据失败！";
        Object data = null;
        
        try {
        	data = baseService.listSubjectByBaseId(baseId);
        	success = true;
        	msg = "获取数据成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        map.put("success", success);
        map.put("data", data);
        map.put("msg", msg);
        return map;
    }

    @RequestMapping(value = "/saveSubjects")
    public String saveSubjects(@RequestParam Integer baseId, @RequestParam String baseName, String subjectNames) {
        try {
            baseService.saveSubjects(baseId, baseName, subjectNames);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    
    /**
     * discription:根据基地查找专业
     * @param
     * @return
     */
    @RequestMapping(value = "/listArrturnRuleByBaseName")
    public Map<String, Object> listArrturnRuleByBaseName(String baseName) {
        Map<String,Object> map = new HashMap<>();
        boolean success = false;
        String msg = "获取数据失败！";
        Object data = null;
        
        try {
        	data = baseService.listArrturnRuleByBaseName(baseName);
        	success = true;
        	msg = "获取数据成功";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        map.put("success", success);
        map.put("data", data);
        map.put("msg", msg);
        return map;
    }
    
}
