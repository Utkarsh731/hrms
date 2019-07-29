package com.incs83.hrms.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class User extends ParentEntity{
    @Id
    private String id;
    private String fname;
    private String lname;
    private String email;
    private String password;
    private Long phone;
    @OneToOne(cascade = CascadeType.ALL)
    private Details details;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Role> roleList;
    @ManyToMany(cascade =CascadeType.ALL)
    private List<Department>  departmentList;

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }
}
