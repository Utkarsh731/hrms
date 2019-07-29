package com.incs83.hrms.request;

import java.util.List;

public class DepartmentRequest {
    private String name;
    private String description;
//    private List<String> userList;
//
//    public List<String> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<String> userList) {
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
