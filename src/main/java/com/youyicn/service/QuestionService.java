package com.youyicn.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.youyicn.model.Question;
import com.youyicn.model.ScoreItem;

/**
 * Created by yangqj on 2017/4/25.
 */
public interface QuestionService extends IService<Question> {
	PageInfo<Question> selectByPage(Question Question, int start, int length);
	List<ScoreItem> listScoreItemByQuestionId(Integer questionId);
	List<Question> listQuestionByExamId(Integer examId);
	void saveScoreItems(Integer questionId, String[] titles, String[] subtitles, Double[] scores, Integer[] sorts);
}
