package com.youyicn.model;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "osce_station")
public class Station implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 考站名称
     */
    private String name;

    /**
     * 地点
     */
    private String address;

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
     * 获取考站名称
     *
     * @return name - 考站名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置考站名称
     *
     * @param name 考站名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取地点
     *
     * @return address - 地点
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地点
     *
     * @param address 地点
     */
    public void setAddress(String address) {
        this.address = address;
    }
}