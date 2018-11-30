package com.youyicn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "osce_tool_apply_record")
public class ToolApplyRecord implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String department;

    /**
     * 设备id
     */
    @Column(name = "tool_id")
    private Integer toolId;

    @Column(name = "start_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startTime;

    @Column(name = "end_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private Integer status;  // 状态 0 报废  1 已归还，2 借用中   3 使用结束未归还  4 使用造成损坏。  5 维修中

    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "return_user_id")
    private Integer returnUserId;


    @Override
    public String toString() {
        return "ToolApplyRecord{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", toolId=" + toolId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", userId=" + userId +
                ", returnUserId=" + returnUserId +
                '}';
    }

    public Integer getReturnUserId() {
        return returnUserId;
    }

    public void setReturnUserId(Integer returnUserId) {
        this.returnUserId = returnUserId;
    }

    /**
     * @return department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getToolId() {
        return toolId;
    }

    public void setToolId(Integer toolId) {
        this.toolId = toolId;
    }

    /**
     * @return start_time
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}