package com.youyicn.controller;

import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;
import com.github.pagehelper.PageInfo;
import com.youyicn.model.Exam;
import com.youyicn.model.ExamUser;
import com.youyicn.service.ExamService;
import com.youyicn.service.ExamUserService;
import com.youyicn.util.EchartUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/count")
public class AcountController {

    @Resource
    private ExamService examService;

    @RequestMapping
    @ResponseBody
    public  Map<String,Object> getAll(Exam exam, String draw, Integer roleId,
                                      @RequestParam(required = false, defaultValue = "1") int start,
                                      @RequestParam(required = false, defaultValue = "10") int length){

        Map<String,Object> map = new HashMap<>();
        PageInfo<Exam> pageInfo = examService.selectByPage(exam, roleId, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }



    @Autowired
    private ExamUserService examUserService ;
    @RequestMapping("/detail")
    public String detail(@Param("examId") Integer examId){
        // 查询所有的考生信息
        List<ExamUser> examUserList = examUserService.selectById(examId);
        String examTitle ="";
        String[] types = { "0-60分", "60-70", "70-80", "80-90", "90-10" };
        int[] datas= new int[4];
        if(examUserList.size()>0){
            examTitle = examUserList.get(0).getExamTitle();





        }
        GsonOption pie = EchartUtils.getPie(types, datas, examTitle);
        String pieString  = pie.toString();

        return "/count/count";
    }


}
