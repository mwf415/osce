package com.youyicn.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "osce_sort")
public class OsceSort {
    /**
     * 编号Id
     */
    @Id
    @Column(name = "sort_id")
    private Integer sortid;

    @Column(name = "exam_id")
    private Integer examid;

    @Column(name = "user_id")
    private String userid;

    @Column(name = "user_name")
    private String username;

    /**
     * 分组，根据站点分组
     */
    @Column(name = "group_id")
    private Integer groupid;

    /**
     * 第几组的第几人
     */
    @Column(name = "sort_no")
    private Integer sortno;

    /**
     * 总站点数
     */
    @Column(name = "station_num")
    private Integer stationnum;

    /**
     * 已完成站数
     */
    private Integer finished;

    /**
     * 未完成站点数
     */
    private Integer unfinished;

    /**
     * 获取编号_id
     *
     * @return sort_id - 编号Id
     */
    public Integer getSortid() {
        return sortid;
    }

    /**
     * 设置编号Id
     *
     * @param sortid 编号Id
     */
    public void setSortid(Integer sortid) {
        this.sortid = sortid;
    }

    /**
     * @return examId
     */
    public Integer getExamid() {
        return examid;
    }

    /**
     * @param examid
     */
    public void setExamid(Integer examid) {
        this.examid = examid;
    }

    /**
     * @return userId
     */
  

    /**
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取分组，根据站点分组
     *
     * @return groupId - 分组，根据站点分组
     */
    public Integer getGroupid() {
        return groupid;
    }

    /**
     * 设置分组，根据站点分组
     *
     * @param groupid 分组，根据站点分组
     */
    public void setGroupid(Integer groupid) {
        this.groupid = groupid;
    }

    /**
     * 获取第几组的第几人
     *
     * @return sortNo - 第几组的第几人
     */
    public Integer getSortno() {
        return sortno;
    }

    /**
     * 设置第几组的第几人
     *
     * @param sortno 第几组的第几人
     */
    public void setSortno(Integer sortno) {
        this.sortno = sortno;
    }

    /**
     * 获取总站点数
     *
     * @return stationNum - 总站点数
     */
    public Integer getStationnum() {
        return stationnum;
    }

    /**
     * 设置总站点数
     *
     * @param stationnum 总站点数
     */
    public void setStationnum(Integer stationnum) {
        this.stationnum = stationnum;
    }

    /**
     * 获取已完成站数
     *
     * @return finished - 已完成站数
     */
    public Integer getFinished() {
        return finished;
    }

    /**
     * 设置已完成站数
     *
     * @param finished 已完成站数
     */
    public void setFinished(Integer finished) {
        this.finished = finished;
    }

    /**
     * 获取未完成站点数
     *
     * @return unfinished - 未完成站点数
     */
    public Integer getUnfinished() {
        return unfinished;
    }

    /**
     * 设置未完成站点数
     *
     * @param unfinished 未完成站点数
     */
    public void setUnfinished(Integer unfinished) {
        this.unfinished = unfinished;
    }
}