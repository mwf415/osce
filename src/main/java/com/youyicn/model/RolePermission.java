package com.youyicn.model;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "role_permission")
public class RolePermission implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色id
     */
    private Long rid;

    /**
     * 权限id
     */
    private Long pid;

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
     * 获取角色id
     *
     * @return rid - 角色id
     */
    public Long getRid() {
        return rid;
    }

    /**
     * 设置角色id
     *
     * @param rid 角色id
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }

    /**
     * 获取权限id
     *
     * @return pid - 权限id
     */
    public Long getPid() {
        return pid;
    }

    /**
     * 设置权限id
     *
     * @param pid 权限id
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }
}