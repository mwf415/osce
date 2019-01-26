package com.youyicn.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 学生的考站的很表
 */
@Table(name = "osce_exam_station_record")
public class ExamStationRecord implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 存的是loginName,擦家考试的学生名称 区分用户
     */
    @Column(name = "user_id")
    private String userId;

    @Column(name = "real_name")
    private String realName;
    
    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "exam_title")
    private String examTitle;

    /**
     * 考站id
     */
    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_title")
    private String questionTitle;

    @Column(name = "station_id")
    private Integer stationId;

    @Column(name = "station_name")
    private String stationName;

    /**
     * 考站得分
     */
    private Integer score;

    /**
     * 0未考试，1已考试
     */
    private Byte state;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "update_by")
    private String updateBy;

    /**
     * 测试项目和得分详情
     */
    @Column(name = "exam_data")
    private String examData;

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
     * 获取存的是loginName,区分用户
     *
     * @return user_id - 存的是loginName,区分用户
     */
    public String getUserId() {
        return userId;
    }
    
    public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
     * 设置存的是loginName,区分用户
     *
     * @param userId 存的是loginName,区分用户
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return exam_id
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * @param examId
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    /**
     * @return exam_title
     */
    public String getExamTitle() {
        return examTitle;
    }

    /**
     * @param examTitle
     */
    public void setExamTitle(String examTitle) {
        this.examTitle = examTitle;
    }

    /**
     * 获取考站id
     *
     * @return question_id - 考站id
     */
    public Integer getQuestionId() {
        return questionId;
    }

    /**
     * 设置考站id
     *
     * @param questionId 考站id
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    /**
     * @return question_title
     */
    public String getQuestionTitle() {
        return questionTitle;
    }

    /**
     * @param questionTitle
     */
    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    /**
     * @return station_id
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * @param stationId
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * @return station_name
     */
    public String getStationName() {
        return stationName;
    }

    /**
     * @param stationName
     */
    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    /**
     * 获取考站得分
     *
     * @return score - 考站得分
     */
    public Integer getScore() {
        return score;
    }

    /**
     * 设置考站得分
     *
     * @param score 考站得分
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 获取0未考试，1已考试
     *
     * @return state - 0未考试，1已考试
     */
    public Byte getState() {
        return state;
    }

    /**
     * 设置0未考试，1已考试
     *
     * @param state 0未考试，1已考试
     */
    public void setState(Byte state) {
        this.state = state;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return update_by
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取测试项目和得分详情
     *
     * @return exam_data - 测试项目和得分详情
     */
    public String getExamData() {
        return examData;
    }

    /**
     * 设置测试项目和得分详情
     *
     * @param examData 测试项目和得分详情
     */
    public void setExamData(String examData) {
        this.examData = examData;
    }
}