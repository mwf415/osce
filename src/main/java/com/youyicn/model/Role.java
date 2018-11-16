package com.youyicn.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;

@Table(name = "cycle_role")
public class Role implements Serializable{
    @Id
    @Column(name = "roleId")
    private Integer roleId;

    /**
     * 角色名称
     */
    @Column(name = "roleName")
    private String roleName;

    /**
     * 角色编号
     */
    @Column(name = "roleNum")
    private String roleNum;

    /**
     * 角色描述
     */
    @Column(name = "roleDes")
    private String roleDes;
    
    @Transient
    private Integer selected;
    
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleNum() {
		return roleNum;
	}

	public void setRoleNum(String roleNum) {
		this.roleNum = roleNum;
	}

	public String getRoleDes() {
		return roleDes;
	}

	public void setRoleDes(String roleDes) {
		this.roleDes = roleDes;
	}

	public Integer getSelected() {
		return selected;
	}

	public void setSelected(Integer selected) {
		this.selected = selected;
	}

    
}