package com.youyicn.model;

public class BigShowVo {

	private String userNameStr ;
	private Integer groupId;
	private Integer stationnum;
	private Integer finished;
	private Integer unfinished;
	public String getUserNameStr() {
		return userNameStr;
	}
	public void setUserNameStr(String userNameStr) {
		this.userNameStr = userNameStr;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Integer getStationnum() {
		return stationnum;
	}
	public void setStationnum(Integer stationnum) {
		this.stationnum = stationnum;
	}
	public Integer getFinished() {
		return finished;
	}
	public void setFinished(Integer finished) {
		this.finished = finished;
	}
	public Integer getUnfinished() {
		return unfinished;
	}
	public void setUnfinished(Integer unfinished) {
		this.unfinished = unfinished;
	}
	@Override
	public String toString() {
		return "BigShowVo [userNameStr=" + userNameStr + ", groupId=" + groupId + ", stationnum=" + stationnum
				+ ", finished=" + finished + ", unfinished=" + unfinished + "]";
	}
	 
	
	 
	 
}
