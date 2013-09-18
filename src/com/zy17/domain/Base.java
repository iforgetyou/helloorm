package com.zy17.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;


public class Base {

    @Id
    private java.lang.String id;
    private Date createdAt;
    private Date updatedAt;

    public Base() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
