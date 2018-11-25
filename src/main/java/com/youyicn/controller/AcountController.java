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
    @Autowired
    private ExamUserService examUserService ;

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

    @RequestMapping("/detail")
    public String detailIndex(@Param("examId") Integer examId ,Model model){

        model.addAttribute("examId",examId);
        return "/count/count";
    }



    @RequestMapping("/getPie")
    @ResponseBody
    public String detail(@Param("examId") Integer examId){
        // 查询所有的考生信息
        List<ExamUser> examUserList = examUserService.selectById(examId);
        String examTitle ="";
        String[] types = { "0-60分", "60-70", "70-80", "80-90", "90-10" };
        int[] datas= {0,0,0,0,0};
        StringBuffer stringBufferOutUser = new StringBuffer(); // 没有打分的学生
        if(examUserList.size()>0){

            examTitle = examUserList.get(0).getExamTitle();
            for (ExamUser examUser : examUserList) {
                Integer score = examUser.getScore();
                if (null == score){
                    stringBufferOutUser.append(examUser.getRealName()+":"+examId+";  ");
                    continue;
                }
                int b = ((0<score&& score<60)==true?1:0)
                        +((60<=score && score<70)==true?2:0)+
                        ((70<=score&& score<80)==true?3:0)+
                        ((80<=score&& score<90)==true?4:0)+
                        ((90<=score)==true?5:0);

                switch (b){
                    case 1:
                        datas[0]=datas[0]+1;
                        continue;
                    case 2:
                        datas[1]=datas[1]+1;
                        continue;
                    case 3:
                        datas[2]=datas[2]+1;
                        continue;
                    case 4:
                        datas[3]=datas[3]+1;
                        continue;
                    case 5:
                        datas[4]=datas[4]+1;
                        continue;

                }

            }

        }
        GsonOption pie = EchartUtils.getPie(types, datas, examTitle);
        String pieString  = pie.toString();

        return pieString;
    }


}
