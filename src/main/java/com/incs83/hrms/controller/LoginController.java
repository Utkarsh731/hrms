package com.incs83.hrms.controller;

import com.incs83.hrms.request.LoginRequest;
import com.incs83.hrms.responseDTO.ApiResponseDTO;
import com.incs83.hrms.services.LoginServices;
import com.incs83.hrms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Login")
public class LoginController {
    @Autowired
    private LoginServices loginServices;
    @Autowired
    private ResponseUtil responseUtil;
    @RequestMapping(method = RequestMethod.POST,value = "/")
    public ApiResponseDTO login(@RequestBody LoginRequest loginRequest){

        return responseUtil.ok(200,loginServices.post(loginRequest),"success");
    }
}

