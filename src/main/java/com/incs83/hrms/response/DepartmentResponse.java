package com.incs83.hrms.response;

import com.incs83.hrms.entity.User;

import java.util.List;

public class DepartmentResponse {
    private String name;
    private String description;
//    private List<User> userList;
//
//    public List<User> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<User> userList) {
//        this.userList = userList;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
