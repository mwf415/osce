package com.youyicn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youyicn.model.BigShowVo;
import com.youyicn.model.OsceSort;
import com.youyicn.service.OsceSortService;

@Controller
public class BigShowController {


    @Autowired
    private OsceSortService osceSortService;
	
	@RequestMapping("bigShowController")
	private String index(Model model,Integer examId){


		List<BigShowVo> bigShowVos = new ArrayList<>();
		if(null==examId){
			model.addAttribute("bigShowVos",bigShowVos);
			return "/sort/bigIndex";
		}
		List<OsceSort> osceSortList = osceSortService.getOsceSortByExamId(examId);
		
		/**这里key是组id，list是该组的人数
		 * */
		Map<Integer,List<OsceSort>> osceSortMap = new HashMap<>();
		 
		for (OsceSort osceSort : osceSortList) {
			BigShowVo bigShowVo = new BigShowVo();
			 bigShowVo.setStationnum(osceSort.getStationnum());
			 bigShowVo.setFinished(osceSort.getFinished());
			 bigShowVo.setGroupId(osceSort.getGroupid());
			 bigShowVo.setUserNameStr(osceSort.getUsername());
			 if(null!=osceSort.getFinished()){
				 bigShowVo.setUnfinished(osceSort.getStationnum()-osceSort.getFinished());
			 }else {
				 bigShowVo.setUnfinished(osceSort.getStationnum());
			 }
			 bigShowVos.add(bigShowVo);
		 }
		model.addAttribute("examId",examId);
		model.addAttribute("bigShowVos",bigShowVos);

		return "/sort/bigIndex";
	}
}
