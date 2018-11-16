package com.youyicn.model;

import java.io.Serializable;

import javax.persistence.*;

@Table(name = "cycle_user_role")
public class UserRole  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long uid;

    private Long rid;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户id
     *
     * @return uid - 用户id
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户id
     *
     * @param uid 用户id
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * @return rid
     */
    public Long getRid() {
        return rid;
    }

    /**
     * @param rid
     */
    public void setRid(Long rid) {
        this.rid = rid;
    }
}