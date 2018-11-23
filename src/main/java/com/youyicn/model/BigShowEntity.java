package com.youyicn.model;

public class BigShowEntity {

    private String userList ; // 所有學生名單

    private  Integer groupId ;// 組名


    @Override
    public String toString() {
        return "BigShowEntity{" +
                "userList='" + userList + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }

    public String getUserList() {

        return userList;
    }

    public void setUserList(String userList) {
        this.userList = userList;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}
