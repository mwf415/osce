package com.youyicn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Station;
import com.youyicn.service.StationService;

/**
 * Created by yangqj on 2017/4/26.
 */
@RestController
@RequestMapping("/stations")
public class StationController {
    @Resource
    private StationService stationService;
    
    @RequestMapping
    public  Map<String,Object> getAll(Station station, String draw,
                             @RequestParam(required = false, defaultValue = "1") int start,
                             @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<Station> pageInfo = stationService.selectByPage(station, start, length);
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
        
        List<Station> list = null;
        try {
        	list = stationService.selectAll();
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
    public String add(Station station) {
        try {
            stationService.save(station);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            stationService.delete(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    
    @RequestMapping(value = "/update")
    public String update(Station station) {
        try {
            stationService.updateNotNull(station);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

}
