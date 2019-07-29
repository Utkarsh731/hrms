package com.incs83.hrms.services;

import com.incs83.hrms.entity.User;
import com.incs83.hrms.repository.DepartmentRepository;
import com.incs83.hrms.entity.Department;
import com.incs83.hrms.repository.UserRepository;
import com.incs83.hrms.request.DepartmentRequest;
import com.incs83.hrms.response.DepartmentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DepartmentServices {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private UserRepository userRepository;
    public List<DepartmentResponse> getAll() {
        List<DepartmentResponse> departmentList = new ArrayList<> ( );
        departmentRepository.findAll ( ).forEach (q -> {
            DepartmentResponse departmentResponse = new DepartmentResponse ( );
            departmentResponse.setName (q.getName ( ));
            departmentResponse.setDescription (q.getDescription ());
     //       departmentResponse.setUserList (q.getUserList ());
            departmentList.add (departmentResponse);
        });
        return departmentList;
    }

    public void post(DepartmentRequest departmentRequest) {
        Department department=new Department ();
        department.setId (UUID.randomUUID ( ).toString ( ).replaceAll ("-", ""));
        department.setName (departmentRequest.getName ( ));
        department.setDescription (departmentRequest.getDescription ( ));
        departmentRepository.save (department);
//        List<User> userList=new ArrayList<> ();
//        for(int i=0;i<(departmentRequest.getUserList ().size ());i++) {
//            User user=userRepository.findById (departmentRequest.getUserList ().get (i)).get ();
//            userList.add(user);
//        }
//        department.setUserList (userList);
    }

    public DepartmentResponse getId(String id) {

        DepartmentResponse departmentResponse = new DepartmentResponse ( );
        Department department = (Department) departmentRepository.findById (id).get ( );
        departmentResponse.setName (department.getName());
        departmentResponse.setDescription (department.getDescription ());
        return departmentResponse;
    }

    public void updateDepartment(String id, Department department) {

        departmentRepository.save (department);
    }

    public void deleteEmployee(String id, Department department) {

        departmentRepository.deleteById (id);
    }

}
