package com.youyicn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.youyicn.model.Room;
import com.youyicn.service.RoomService;

/**
 * Created by yangqj on 2017/4/26.
 */
@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Resource
    private RoomService roomService;
    
    @RequestMapping("getAll")
    public  Map<String,Object> getAll(){
        Map<String,Object> map = new HashMap<>();
        boolean success = false;
        String msg = "获取数据失败！";
        Object data = null;
        
        List<Room> list = null;
        try {
        	list = roomService.selectAll();
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
    
}
