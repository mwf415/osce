package com.youyicn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Table(name = "osce_exam_user")
public class ExamUser implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "user_type")
    private Integer userType;
    
    @Column(name = "exam_id")
    private Integer examId;

    @Column(name = "exam_title")
    private String examTitle;

    @Column(name = "score")
    private Integer score;
    
    //0未完成1已完成
    @Column(name = "state")
    private Integer state;

    @Column(name = "join_time")
    private Date joinTime;

    private String mark;

    //试卷创建时间
    @Column(name = "create_Time")
    private Date createTime;

    //评分时间
    @Column(name = "update_Time")
    private Date updateTime;
    
    @Column(name = "system_id")
    private Integer systemId;

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
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
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

    public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
     * @return join_time
     */
    public Date getJoinTime() {
        return joinTime;
    }

    /**
     * @param joinTime
     */
    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    /**
     * @return remark
     */
    public String getMark() {
        return mark;
    }

    /**
     * @param remark
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
     * @return system_id
     */
    public Integer getSystemId() {
        return systemId;
    }

    /**
     * @param systemId
     */
    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }
}