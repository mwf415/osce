package com.youyicn.model;

import java.util.List;

public class BigShowRsp {
    private List<UserParm> inUserList;

    private List<BigShowEntity> toInUser ;

    private String finishedUser;

    private String outUserList ;

    private Exam exam;


    @Override
    public String toString() {
        return "BigShowRsp{" +
                "inUserList=" + inUserList +
                ", toInUser=" + toInUser +
                ", finishedUser='" + finishedUser + '\'' +
                ", outUserList='" + outUserList + '\'' +
                ", exam=" + exam +
                '}';
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public List<UserParm> getInUserList() {
        return inUserList;
    }

    public void setInUserList(List<UserParm> inUserList) {
        this.inUserList = inUserList;
    }

    public List<BigShowEntity> getToInUser() {
        return toInUser;
    }

    public void setToInUser(List<BigShowEntity> toInUser) {
        this.toInUser = toInUser;
    }

    public String getFinishedUser() {
        return finishedUser;
    }

    public void setFinishedUser(String finishedUser) {
        this.finishedUser = finishedUser;
    }

    public String getOutUserList() {
        return outUserList;
    }

    public void setOutUserList(String outUserList) {
        this.outUserList = outUserList;
    }
}
