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

    @Column(name = "code_url")
    private String codeUrl;

    @Column(name = "code_apply_url")
    private String codeApplyUrl;
    
    private Integer status; //状态  1 是正常，2 维修中，0 报废

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
                ", codeUrl='" + codeUrl + '\'' +
                ", codeApplyUrl='" + codeApplyUrl + '\'' +
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

    /**
     * @return code_url
     */
    public String getCodeUrl() {
        return codeUrl;
    }

    /**
     * @param codeUrl
     */
    public void setCodeUrl(String codeUrl) {
        this.codeUrl = codeUrl;
    }

    /**
     * @return code_apply_url
     */
    public String getCodeApplyUrl() {
        return codeApplyUrl;
    }

    /**
     * @param codeApplyUrl
     */
    public void setCodeApplyUrl(String codeApplyUrl) {
        this.codeApplyUrl = codeApplyUrl;
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