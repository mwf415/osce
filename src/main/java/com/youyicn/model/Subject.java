package com.youyicn.model;

import javax.persistence.*;

@Table(name = "osce_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "base_id")
    private Integer baseId;

    @Column(name = "base_name")
    private String baseName;

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
     * @return base_id
     */
    public Integer getBaseId() {
        return baseId;
    }

    /**
     * @param baseId
     */
    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    /**
     * @return base_name
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * @param baseName
     */
    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }
}