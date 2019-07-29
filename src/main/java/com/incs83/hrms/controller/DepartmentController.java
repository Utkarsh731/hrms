package com.incs83.hrms.controller;

import com.incs83.hrms.entity.Department;
import com.incs83.hrms.repository.PasscodeRepository;
import com.incs83.hrms.request.DepartmentRequest;
import com.incs83.hrms.responseDTO.ApiResponseDTO;
import com.incs83.hrms.services.DepartmentServices;
import com.incs83.hrms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController

@RequestMapping("/Department")
public class DepartmentController {
    @Autowired
    private DepartmentServices departmentServices;
    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    private PasscodeRepository passcodeRepository;
    @RequestMapping(method = RequestMethod.GET,value = "/")
    public ApiResponseDTO view(@RequestHeader(name="token" )String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))){
        return  responseUtil.ok (200, departmentServices.getAll ( ),"Success");
    }
    else{
        return responseUtil.ok (200,"error","wrong code");
        }}

    @RequestMapping(method = RequestMethod.POST)
    public ApiResponseDTO post(@RequestBody DepartmentRequest department,@RequestHeader(name="token") String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))){
        departmentServices.post (department);
        return responseUtil.ok (200,"data posted successfully","success");
    } else{
            return responseUtil.ok (200,"error","wrong code");
        }}

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ApiResponseDTO viewById(@PathVariable("id") String id,@RequestHeader(name="token") String token) {
            if (Objects.nonNull (passcodeRepository.findByCode (token))){
            return responseUtil.ok(200,departmentServices.getId (id),"Success");
    } else{
                return responseUtil.ok (200,"error","wrong code");
            }}

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ApiResponseDTO updateDepartment(@RequestBody Department department, @PathVariable("id") String id,@RequestHeader(name="token") String token) {
                if (Objects.nonNull (passcodeRepository.findByCode (token))){
                    departmentServices.updateDepartment (id, department);
                    return responseUtil.ok (200,"data updated successfully","success");
                }
                else{
                    return responseUtil.ok (200,"error","wrong code");
                }


    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ApiResponseDTO deleteDepartment(@RequestBody Department department, @PathVariable("id") String id,@RequestHeader(name="token") String token) {
                if (Objects.nonNull (passcodeRepository.findByCode (token))){
                    departmentServices.deleteEmployee (id, department);
                    return responseUtil.ok (200,"deleted successfully","successs");
                }
                else{
                    return responseUtil.ok (200,"error","wrong code");
                }}

}
