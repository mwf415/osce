package com.youyicn.model;

import javax.persistence.*;

@Table(name = "osce_score_item")
public class ScoreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "question_id")
    private Integer questionId;

    /**
     * 操作项目
     */
    private String title;

    /**
     * 操作内容
     */
    private String subtitle;

    /**
     * 分值
     */
    private Double score;

    /**
     * 排序
     */
    private int sort;


    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return question_id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * 获取操作项目
     *
     * @return title - 操作项目
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置操作项目
     *
     * @param title 操作项目
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取操作内容
     *
     * @return subtitle - 操作内容
     */
    public String getSubtitle() {
        return subtitle;
    }

    /**
     * 设置操作内容
     *
     * @param subtitle 操作内容
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    /**
     * 获取分值
     *
     * @return score - 分值
     */
    public Double getScore() {
        return score;
    }

    /**
     * 设置分值
     *
     * @param score 分值
     */
    public void setScore(Double score) {
        this.score = score;
    }

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
