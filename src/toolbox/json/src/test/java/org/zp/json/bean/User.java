package org.zp.json.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class User {
    @JSONField(ordinal = 1)
    private Long id;
    @JSONField(ordinal = 2)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}