package com.youyicn.model;

import java.util.Date;
import javax.persistence.*;

@Table(name="cycle_arrturn")
public class Arrturn {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "hospitalId")
    private String hospitalId;

    @Column(name = "loginName")
    private String loginName;

    /**
     * 姓名
     */
    @Column(name = "realName")
    private String realName;

    /**
     * 开始时间
     */
    @Column(name = "startTime")
    private Date startTime;

    @Column(name = "endTime")
    private Date endTime;

    /**
     * 科室名称
     */
    @Column(name = "roomName")
    private String roomName;

    @Column(name = "teacherNum1")
    private String teacherNum1;

    /**
     * 带教老师
     */
    @Column(name = "teacherName1")
    private String teacherName1;

    @Column(name = "teacherNum2")
    private String teacherNum2;

    /**
     * 有可能两个带教老师，这个备用
     */
    @Column(name = "teacherName2")
    private String teacherName2;

    /**
     * 级别
     */
    private String grade;
    
    @Column(name = "baseName")
    private String baseName;

    @Column(name = "checkStatus")
    private String checkStatus;

    /**
     * 主要用来区分本科室和研究生的
     */
    @Column(name = "trainTime")
    private String trainTime;

    /**
     * 批次
     */
    private String batch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getTeacherNum1() {
		return teacherNum1;
	}

	public void setTeacherNum1(String teacherNum1) {
		this.teacherNum1 = teacherNum1;
	}

	public String getTeacherName1() {
		return teacherName1;
	}

	public void setTeacherName1(String teacherName1) {
		this.teacherName1 = teacherName1;
	}

	public String getTeacherNum2() {
		return teacherNum2;
	}

	public void setTeacherNum2(String teacherNum2) {
		this.teacherNum2 = teacherNum2;
	}

	public String getTeacherName2() {
		return teacherName2;
	}

	public void setTeacherName2(String teacherName2) {
		this.teacherName2 = teacherName2;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getTrainTime() {
		return trainTime;
	}

	public void setTrainTime(String trainTime) {
		this.trainTime = trainTime;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
    
    
}