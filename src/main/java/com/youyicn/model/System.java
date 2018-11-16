package com.youyicn.model;

import javax.persistence.*;

public class System {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 系统 exam cycle osce等
     */
    private String name;

    private String desc;

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
     * 获取系统 exam cycle osce等
     *
     * @return name - 系统 exam cycle osce等
     */
    public String getName() {
        return name;
    }

    /**
     * 设置系统 exam cycle osce等
     *
     * @param name 系统 exam cycle osce等
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
}