package com.youyicn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.youyicn.mapper.ScoreItemMapper;
import com.youyicn.model.ScoreItem;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.youyicn.model.Question;
import com.youyicn.model.Station;
import com.youyicn.service.QuestionService;
import com.youyicn.shiro.ShiroService;
import tk.mybatis.mapper.entity.Example;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private ScoreItemMapper scoreItemMapper;

    @RequestMapping
    public Map<String,Object> getAll(Question questions, String draw,
                                     @RequestParam(required = false, defaultValue = "1") int start,
                                     @RequestParam(required = false, defaultValue = "10") int length){
        Map<String,Object> map = new HashMap<>();
        PageInfo<Question> pageInfo = questionService.selectByPage(questions, start, length);
        map.put("draw",draw);
        map.put("recordsTotal",pageInfo.getTotal());
        map.put("recordsFiltered",pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }

    @RequestMapping("/listScoreItemByQuestionId")
    public Map<String, Object> listScoreItemByQuestionId(@RequestParam Integer questionId){
    	Map<String, Object> result = Maps.newHashMap();
	    boolean success = false;
	    String msg = "获取数据失败！";
	    Object data = null;
	    try {
    		data = questionService.listScoreItemByQuestionId(questionId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}

	    result.put("success", success);
	    result.put("msg", msg);
	    result.put("data", data);

        return result;
    }

    @RequestMapping("/listQuestionByExamId")
    public Map<String, Object> listQuestionByExamId(Integer examId){
    	Map<String, Object> result = Maps.newHashMap();
	    boolean success = false;
	    String msg = "获取数据失败！";
	    Object data = null;
	    try {
			data = questionService.listQuestionByExamId(examId);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}

	    result.put("success", success);
	    result.put("msg", msg);
	    result.put("data", data);

        return result;
    }

    @RequestMapping(value = "/add")
    public String add(Question question){
        try{
            questionService.save(question);
            //更新权限
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/delete")
    public String delete(Integer id){
        try{
            questionService.delete(id);
            //更新权限
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }
    @RequestMapping(value = "/copy")
    @Transactional
    public String copy(Integer id){
        try{
            /**
             * 获取对应的题目
             */
            Question question = questionService.selectByKey(id);
            Question newQuestion = new Question();
            BeanUtils.copyProperties(newQuestion,question);
            newQuestion.setId(null);
            newQuestion.setFilePath(null);
            newQuestion.setAnswer(null);
            questionService.save(newQuestion);

            /**
             * 获取对应的评分项
             */
            Example example = new Example(ScoreItem.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("questionId", id);
            List<ScoreItem> scoreItems = scoreItemMapper.selectByExample(example);
            List<ScoreItem> newItems = new ArrayList<>();
            if(null != scoreItems && scoreItems.size()>0){
                for (ScoreItem scoreItem : scoreItems) {
                    ScoreItem scoreItem1 = new ScoreItem();
                    BeanUtils.copyProperties(scoreItem1,scoreItem);
                    scoreItem1.setId(null);
                    scoreItem1.setQuestionId(newQuestion.getId());
                    newItems.add(scoreItem1);
                }
                scoreItemMapper.insertList(newItems);
            }
            //更新权限
            return "success";
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/update")
    public String update(Question question) {
        try {
        	questionService.updateNotNull(question);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping(value = "/saveScoreItems")
    public String saveScoreItems(@RequestParam Integer questionId, String[] titles, String[] subtitles, Double[] scores, Integer[] sorts) {
        try {
        	questionService.saveScoreItems(questionId, titles, subtitles, scores, sorts);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    @RequestMapping("/getQuestionById")
    public Map<String, Object> getQuestionById(Integer id){
    	Map<String, Object> result = Maps.newHashMap();
	    boolean success = false;
	    String msg = "获取数据失败！";
	    Object data = null;
	    try {
			data = questionService.selectByKey(id);
			success = true;
			msg = "获取数据成功！";
		} catch (Exception e) {
			e.printStackTrace();
		}

	    result.put("success", success);
	    result.put("msg", msg);
	    result.put("data", data);

        return result;
    }

}
