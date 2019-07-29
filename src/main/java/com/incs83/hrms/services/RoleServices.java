package com.incs83.hrms.services;

import com.incs83.hrms.entity.Role;
import com.incs83.hrms.repository.RoleRepository;
import com.incs83.hrms.request.RoleRequest;
import com.incs83.hrms.response.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoleServices {
    @Autowired
    private RoleRepository roleRepository;
    public List<RoleResponse> getAll(){
        List<RoleResponse> roleList = new ArrayList<> ( );
        roleRepository.findAll ( ).forEach (q -> {
            RoleResponse roleResponse = new RoleResponse ( );
            roleResponse.setName (q.getName ( ));
            roleResponse.setId (q.getId ());
            roleList.add (roleResponse);
        });
        return roleList;
    }
    public void post(RoleRequest roleRequest){
        Role role=new Role ();
        role.setId (UUID.randomUUID ( ).toString ( ).replaceAll ("-", ""));
        role.setName (roleRequest.getName ( ));

        roleRepository.save (role);
    }
    public void updateRole(String id,Role role){
        roleRepository.save(role);
    }
    public void deleteRole(String id, Role role) {

        roleRepository.deleteById (id);
    }
    public RoleResponse getId(String id) {
        RoleResponse roleResponse = new RoleResponse ( );
        Role role = (Role) roleRepository.findById (id).get ( );
        roleResponse.setName (role.getName());

        return roleResponse;
    }
}
