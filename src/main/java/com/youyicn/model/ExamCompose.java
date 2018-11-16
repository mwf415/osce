package com.youyicn.model;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "osce_exam_compose")
public class ExamCompose implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "exam_title")
    private String examTitle;
    
    @Column(name = "station_id")
    private Integer stationId;

    @Column(name = "station_name")
    private String stationName;

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "question_title")
    private String questionTitle;

    /**
     * 地点
     */
    private String address;

    /**
     * 老师
     */
    @Column(name = "teacher_names")
    private String teacherNames;

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

    
    public String getExamTitle() {
		return examTitle;
	}

	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
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
     * 获取地点
     *
     * @return address - 地点
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地点
     *
     * @param address 地点
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取老师
     *
     * @return teacher_names - 老师
     */
    public String getTeacherNames() {
        return teacherNames;
    }

    /**
     * 设置老师
     *
     * @param teacherNames 老师
     */
    public void setTeacherNames(String teacherNames) {
        this.teacherNames = teacherNames;
    }
}