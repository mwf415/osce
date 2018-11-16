package com.youyicn.model;

import javax.persistence.*;

@Table(name = "cycle_arrturn_rule")
public class ArrturnRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "baseName")
    private String baseName;

    /**
     * 学历
     */
    private String education;

    /**
     * 科室
     */
    @Column(name = "roomName")
    private String roomName;

    /**
     * 周期（单位：月）
     */
    private String period;

    /**
     * 轮转年限类型,存放2  或者3  代表2年或者3年轮转期
     */
    private Byte type;

    /**
     * 排序
     */
    @Column(name = "roomSort")
    private Integer roomSort;

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


    public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	/**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    
    public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
     * 获取周期（单位：月）
     *
     * @return period - 周期（单位：月）
     */
    public String getPeriod() {
        return period;
    }

    /**
     * 设置周期（单位：月）
     *
     * @param period 周期（单位：月）
     */
    public void setPeriod(String period) {
        this.period = period;
    }

    /**
     * 获取轮转年限类型,存放2  或者3  代表2年或者3年轮转期
     *
     * @return type - 轮转年限类型,存放2  或者3  代表2年或者3年轮转期
     */
    public Byte getType() {
        return type;
    }

    /**
     * 设置轮转年限类型,存放2  或者3  代表2年或者3年轮转期
     *
     * @param type 轮转年限类型,存放2  或者3  代表2年或者3年轮转期
     */
    public void setType(Byte type) {
        this.type = type;
    }

	public Integer getRoomSort() {
		return roomSort;
	}

	public void setRoomSort(Integer roomSort) {
		this.roomSort = roomSort;
	}
    
    

}