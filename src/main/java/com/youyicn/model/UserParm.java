package com.youyicn.model;

public class UserParm {
    private String userName;
    private String userId;
    private String finished;// 已经完成的考站
    private String unFinished;// 未完成的考站
    private Integer groupId; // 分组Id
    private Integer sortNo;// 排序号

    @Override
    public String toString() {
        return "UserParm{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", finished='" + finished + '\'' +
                ", unFinished='" + unFinished + '\'' +
                ", groupId=" + groupId +
                ", sortNo=" + sortNo +
                '}';
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFinished() {
        return finished;
    }

    public void setFinished(String finished) {
        this.finished = finished;
    }

    public String getUnFinished() {
        return unFinished;
    }

    public void setUnFinished(String unFinished) {
        this.unFinished = unFinished;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
