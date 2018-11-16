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

    @Column(name = "tool_grop_id")
    private Integer toolGropId;
    
    @Column(name = "tool_grop_name")
    private  String toolGropName;

    @Column(name = "tool_num")
    private String toolNum;

    private String name;

    private String productor;

    @Column(name = "buy_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date buyTime;

    @Column(name = "code_url")
    private String codeUrl;

    @Column(name = "code_apply_url")
    private String codeApplyUrl;
    
    private Integer status;

    private String descri;
    
    	

    public String getToolGropName() {
		return toolGropName;
	}

	public void setToolGropName(String toolGropName) {
		this.toolGropName = toolGropName;
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

    /**
     * @return tool_grop_id
     */
    public Integer getToolGropId() {
        return toolGropId;
    }

    /**
     * @param toolGropId
     */
    public void setToolGropId(Integer toolGropId) {
        this.toolGropId = toolGropId;
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

	@Override
	public String toString() {
		return "设备详情 [设备ID=" + id + ", 设备分组=" + toolGropId + ", 设备编号=" + toolNum + ", 设备名称=" + name
				+ ", 厂商=" + productor + ", 采购时间=" + buyTime +  ",设置状态=" + status + ", 设备描述=" + descri + "]";
	}

    /**
     * @return desc
     */
	
	
  
}