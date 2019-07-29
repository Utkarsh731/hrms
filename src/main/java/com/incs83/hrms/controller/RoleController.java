package com.incs83.hrms.controller;

import com.incs83.hrms.entity.Role;
import com.incs83.hrms.repository.PasscodeRepository;
import com.incs83.hrms.request.RoleRequest;
import com.incs83.hrms.responseDTO.ApiResponseDTO;
import com.incs83.hrms.services.RoleServices;
import com.incs83.hrms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/Role")
public class RoleController {
    @Autowired
    private RoleServices roleServices;
    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    private PasscodeRepository passcodeRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public ApiResponseDTO view(@RequestHeader(name = "token") String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))) {
            return responseUtil.ok (200, roleServices.getAll ( ), "success");
        } else {
            return responseUtil.ok (404, "Wrong code", "Error");
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ApiResponseDTO post(@RequestBody RoleRequest role, @RequestHeader(name = "token") String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))) {
            roleServices.post (role);
            return responseUtil.ok(200,"Successfully Posted","Success");
        }
        else{
            return responseUtil.ok (404,"Wrong code","Error");
        }

    }


    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ApiResponseDTO viewById(@PathVariable("id") String id,@RequestHeader(name="token")String token) {
        if(Objects.nonNull(passcodeRepository.findByCode (token))){
        return responseUtil.ok(200,roleServices.getId (id),"success");
    }
        else{
            return responseUtil.ok (404,"Wrong code","Error");
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ApiResponseDTO updateRole(@RequestBody Role role, @PathVariable("id") String id,@RequestHeader(name="token")String token) {
        if(Objects.nonNull(passcodeRepository.findByCode (token))) {
            roleServices.updateRole (id, role);
            return responseUtil.ok(200,"Successfully updated","Success");
        }
        else{
            return responseUtil.ok (404,"Wrong code","Error");
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ApiResponseDTO deleteRole(@RequestBody Role role, @PathVariable("id") String id,@RequestHeader(name="token")String token) {
        if(Objects.nonNull(passcodeRepository.findByCode (token))) {
            roleServices.deleteRole (id, role);
            return responseUtil.ok (200,"Successfully deleted","Success");
        }
        else{
            return responseUtil.ok (404,"Wrong code","Error");
        }
    }
}
