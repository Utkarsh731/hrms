package com.incs83.hrms.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role extends ParentEntity{
    @Id
    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
