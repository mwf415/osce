package com.youyicn.model;

import javax.persistence.*;

@Table(name = "osce_offender")
public class OsceOffender {
    @Id
    @Column(name = "offender_id")
    private Integer offenderId;

    /**
     * 学生考试的ID
     */
    @Column(name = "exam_user_id")
    private Integer examUserId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    /**
     * 考试的ID
     */
    @Column(name = "exam_id")
    private Integer examId;

    /**
     * 备注原因
     */
    private String remarks;

    /**
     * @return offender_id
     */
    public Integer getOffenderId() {
        return offenderId;
    }

    /**
     * @param offenderId
     */
    public void setOffenderId(Integer offenderId) {
        this.offenderId = offenderId;
    }

    /**
     * 获取学生考试的ID
     *
     * @return exam_user_id - 学生考试的ID
     */
    public Integer getExamUserId() {
        return examUserId;
    }

    /**
     * 设置学生考试的ID
     *
     * @param examUserId 学生考试的ID
     */
    public void setExamUserId(Integer examUserId) {
        this.examUserId = examUserId;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取考试的ID
     *
     * @return exam_id - 考试的ID
     */
    public Integer getExamId() {
        return examId;
    }

    /**
     * 设置考试的ID
     *
     * @param examId 考试的ID
     */
    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    /**
     * 获取备注原因
     *
     * @return remarks - 备注原因
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 设置备注原因
     *
     * @param remarks 备注原因
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}