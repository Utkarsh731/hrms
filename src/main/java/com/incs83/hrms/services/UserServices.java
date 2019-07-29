package com.incs83.hrms.services;

import com.incs83.hrms.entity.Department;
import com.incs83.hrms.entity.Details;
import com.incs83.hrms.entity.Role;
import com.incs83.hrms.entity.User;
import com.incs83.hrms.repository.DepartmentRepository;
import com.incs83.hrms.repository.RoleRepository;
import com.incs83.hrms.repository.UserRepository;
import com.incs83.hrms.request.UserRequest;
import com.incs83.hrms.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    public List<UserResponse> getAll(){
        List<UserResponse> userList = new ArrayList<> ( );
        userRepository.findAll ( ).forEach (q -> {
            UserResponse userResponse = new UserResponse ( );
            userResponse.setName (q.getFname ( )+" "+q.getLname ());
            userResponse.setPhone (q.getPhone ());
            userResponse.setEmail (q.getEmail ());
            userResponse.setAddress (q.getDetails ().getAddress1 ());
            userResponse.setBloodgroup (q.getDetails ().getBloodgroup ());
            userResponse.setGender (q.getDetails ().getGender ());
            userResponse.setRoleList (q.getRoleList ());
            userResponse.setDepartmentList (q.getDepartmentList ());
            userList.add (userResponse);
        });
        return userList;
    }
    public void post(UserRequest userRequest){

        User user=new User ();
        user.setId (UUID.randomUUID ( ).toString ( ).replaceAll ("-", ""));
        user.setFname (userRequest.getFname ());
        user.setLname (userRequest.getLname ());
        user.setEmail (userRequest.getEmail ());
        user.setPassword (userRequest.getPassword ());
        user.setPhone (userRequest.getPhone ());

        Details details=new Details ();
        details.setId (UUID.randomUUID ( ).toString ( ).replaceAll ("-", ""));
        details.setGender (userRequest.getGender ());
        details.setBloodgroup (userRequest.getBloodgroup ());
        details.setAddress1 (userRequest.getAddress1 ());
        details.setAddress2 (userRequest.getAddress2 ());
        user.setDetails (details);
        List<Role> roleList=new ArrayList<> ();
        for(int i=0;i<(userRequest.getRoleList ().size ());i++) {
            Role role=roleRepository.findById (userRequest.getRoleList ().get (i)).get ();
            roleList.add(role);
        }
        user.setRoleList (roleList);
        List<Department> departmentList=new ArrayList<> ();
        for(int i=0;i<(userRequest.getDepartmentList ().size ());i++) {
            Department department=departmentRepository.findById (userRequest.getRoleList ().get (i)).get ();
            departmentList.add(department);
        }
        user.setDepartmentList (departmentList);
        user.setRoleList (roleList);
        userRepository.save (user);
    }
    public void update(String id,User user){
        userRepository.save(user);
    }
    public void deleteUser(String id, User user) {

        userRepository.deleteById (id);
    }
    public UserResponse getId(String id) {

        UserResponse userResponse = new UserResponse ( );
        User user = (User) userRepository.findById (id).get ( );
        userResponse.setName (user.getFname ()+" "+user.getLname ());
        userResponse.setEmail (user.getEmail ());
        userResponse.setPhone (user.getPhone ());
        return userResponse;
    }

}
