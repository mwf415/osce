package com.youyicn.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "osce_tool")
public class Tool implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tool_group_id")
    private Integer toolGroupId;
    
    @Column(name = "tool_group_name")
    private  String toolGroupName;

    @Column(name = "tool_num")
    private String toolNum;
    private String name;
    private String productor;
    @Column(name = "buy_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyTime;
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    private Integer status; // 状态 0 报废  1 是可以借用，2 借用中   3 使用结束未归还  4 使用造成损坏。  5 维修中
    private String descri;

    @Override
    public String toString() {
        return "Tool{" +
                "id=" + id +
                ", toolGroupId=" + toolGroupId +
                ", toolGroupName='" + toolGroupName + '\'' +
                ", toolNum='" + toolNum + '\'' +
                ", name='" + name + '\'' +
                ", productor='" + productor + '\'' +
                ", buyTime=" + buyTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", descri='" + descri + '\'' +
                '}';
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

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

    public Integer getToolGroupId() {
        return toolGroupId;
    }

    public void setToolGroupId(Integer toolGroupId) {
        this.toolGroupId = toolGroupId;
    }

    public String getToolGroupName() {
        return toolGroupName;
    }

    public void setToolGroupName(String toolGroupName) {
        this.toolGroupName = toolGroupName;
    }

    /**
     * @return tool_num
     */
    public String getToolNum() {
        return toolNum;
    }

    /**
     * @param toolNum
     */
    public void setToolNum(String toolNum) {
        this.toolNum = toolNum;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return productor
     */
    public String getProductor() {
        return productor;
    }

    /**
     * @param productor
     */
    public void setProductor(String productor) {
        this.productor = productor;
    }

    /**
     * @return buy_time
     */
    public Date getBuyTime() {
        return buyTime;
    }

    /**
     * @param buyTime
     */
    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }


	public String getDescri() {
		return descri;
	}

	public void setDescri(String descri) {
		this.descri = descri;
	}

    /**
     * @return desc
     */
	
	
  
}