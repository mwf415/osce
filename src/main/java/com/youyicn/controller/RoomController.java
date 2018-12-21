package com.youyicn.controller;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Room;
import com.youyicn.service.CycleRoomService;
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
@RequestMapping("/rooms")
public class RoomController {
    @Resource
    private CycleRoomService roomService;

    @RequestMapping
    public  Map<String,Object> getAll(Room room, String draw,
                                      @RequestParam(required = false, defaultValue = "1") int start,
                                      @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<Room> pageInfo = roomService.selectByPage(room, start, length);
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

    @RequestMapping(value = "/add")
    public String add(Room room) {
        try {
            roomService.save(room);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            roomService.deleteByKey(id);
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/update")
    public String update(Room room) {
        try {
            roomService.updateNotNull(room);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    /**
     * discription:根据基地查找专业
     * @param roomId
     * @return
     */
    @RequestMapping(value = "/listSubjectByRoomId")
    public Map<String, Object> listSubjectByRoomId(Integer roomId) {
        Map<String,Object> map = new HashMap<>();
        boolean success = false;
        String msg = "获取数据失败！";
        Object data = null;

        try {
            data = roomService.listSubjectByRoomId(roomId);
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


    /**
     * discription:根据基地查找专业
     * @param
     * @return
     */
    @RequestMapping(value = "/listArrturnRuleByRoomName")
    public Map<String, Object> listArrturnRuleByRoomName(String roomName) {
        Map<String,Object> map = new HashMap<>();
        boolean success = false;
        String msg = "获取数据失败！";
        Object data = null;

        try {
            data = roomService.listArrturnRuleByRoomName(roomName);
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
