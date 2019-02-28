package com.youyicn.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.youyicn.mapper.ExamComposeMapper;
import com.youyicn.mapper.QuestionMapper;
import com.youyicn.mapper.ScoreItemMapper;
import com.youyicn.model.ExamCompose;
import com.youyicn.model.Question;
import com.youyicn.model.ScoreItem;
import com.youyicn.service.QuestionService;

@Service
@Transactional
public class QuestionServiceImpl extends BaseService<Question> implements QuestionService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private ScoreItemMapper scoreItemMapper;

    @Resource
    private ExamComposeMapper examComposeMapper;

    @Override
    public PageInfo<Question> selectByPage(Question question, int start, int length) {
        int page = start / length + 1;
        Example example = new Example(Question.class);
        example.orderBy("id").desc();
        Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(question.getBaseName())) {
            criteria.andEqualTo("baseName", question.getBaseName());
        }
        if (StringUtils.isNotBlank(question.getRoomName())) {
            criteria.andEqualTo("roomName", question.getRoomName());
        }
        if (StringUtils.isNotBlank(question.getTitle())) {
            criteria.andLike("title", "%" + question.getTitle() + "%");
        }
        if (StringUtils.isNotBlank(question.getQuestionType())) {
            criteria.andEqualTo("questionType", question.getQuestionType());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<Question> questionList = selectByExample(example);
        return new PageInfo<>(questionList);
    }

    @Override
    public List<ScoreItem> listScoreItemByQuestionId(Integer questionId) {
        Example example = new Example(ScoreItem.class);
        example.orderBy("sort");
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("questionId", questionId);
        return scoreItemMapper.selectByExample(example);
    }

    @Override
    public List<Question> listQuestionByExamId(Integer examId) {
        Example example = new Example(ExamCompose.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("examId", examId);
        List<ExamCompose> ExamComposes = examComposeMapper.selectByExample(example);

        //获取所有题目ids
        List<Integer> idsList = Lists.newArrayList();
        for (ExamCompose ec : ExamComposes) {
            idsList.add(ec.getQuestionId());
        }
        String idsStr = StringUtils.join(idsList, ",");
//		String idsStr = String.join(",", idsList);//java 8
        Example example2 = new Example(Question.class);
        example2.orderBy("stationId");
        Criteria criteria2 = example2.createCriteria();
        HashSet<String> set = new HashSet<String>(Arrays.asList(idsStr.split(",")));
        criteria2.andIn("id", set);
        List<Question> questions = questionMapper.selectByExample(example2);
        return questions;
    }

    @Override
    public void saveScoreItems(Integer questionId, String[] titles, String[] subtitles, Double[] scores, Integer[] sorts) {
        //删除原来的评分项
        Example example = new Example(ScoreItem.class);
        Criteria criteria = example.createCriteria();
        criteria.andEqualTo("questionId", questionId);
        scoreItemMapper.deleteByExample(example);
        //新增评分项
        List<ScoreItem> scoreItems = Lists.newArrayList();
        if (titles != null && titles.length > 0) {
            for (int i = 0; i < titles.length; i++) {
                ScoreItem si = new ScoreItem();
                si.setQuestionId(questionId);
                si.setTitle(titles[i]);
                si.setSubtitle(subtitles[i]);
                si.setScore(scores[i]);
                si.setSort(sorts[i]);
                scoreItems.add(si);
            }
            scoreItemMapper.insertList(scoreItems);
        }
    }

}
