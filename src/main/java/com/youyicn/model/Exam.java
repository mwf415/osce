package com.youyicn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Table(name = "osce_exam")
public class Exam implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    /**
     * 考生id
     */
    @Column(name = "user_ids")
    private String userIds;

    /**
     * 考生姓名
     */
    @Column(name = "user_names")
    private String userNames;

    @Column(name = "base_name")
    private String baseName;
    
    @Column(name = "room_name")
    private String roomName;
    /**
     * 开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH")
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 0开放，1关闭
     */
    private Integer state;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取考生id
     *
     * @return user_ids - 考生id
     */
    public String getUserIds() {
        return userIds;
    }

    /**
     * 设置考生id
     *
     * @param userIds 考生id
     */
    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    /**
     * 获取考生姓名
     *
     * @return user_names - 考生姓名
     */
    public String getUserNames() {
        return userNames;
    }

    /**
     * 设置考生姓名
     *
     * @param userNames 考生姓名
     */
    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }
    
    
    public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
     * 获取开始时间
     *
     * @return start_time - 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间
     *
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取0开放，1关闭
     *
     * @return state - 0开放，1关闭
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置0开放，1关闭
     *
     * @param state 0开放，1关闭
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取创建人
     *
     * @return create_by - 创建人
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建人
     *
     * @param createBy 创建人
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}