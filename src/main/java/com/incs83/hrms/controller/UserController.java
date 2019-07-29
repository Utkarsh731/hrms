package com.incs83.hrms.controller;

import com.incs83.hrms.entity.User;
import com.incs83.hrms.repository.PasscodeRepository;
import com.incs83.hrms.request.UserRequest;
import com.incs83.hrms.responseDTO.ApiResponseDTO;
import com.incs83.hrms.services.UserServices;
import com.incs83.hrms.util.ResponseUtil;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserServices userServices;
    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    private PasscodeRepository passcodeRepository;
    @RequestMapping(method=RequestMethod.GET,value="/")
    public ApiResponseDTO getAll(@RequestHeader(name="token") String token){
        Base64.Decoder dec = Base64.getDecoder();
        String decoded = new String(dec.decode(token));
        String[] values = decoded.split(",");
        String role=values[2];

        if (Objects.nonNull (passcodeRepository.findByCode (token))) {
            if (role.equals ("admin")) {
                return responseUtil.ok (200, userServices.getAll ( ), "success");
            } else {
                return responseUtil.ok (404, "you are not admin", "You are not allowed to access.");
            }
        }
        else {
            return responseUtil.ok (404, "Wrong code", "Error");
        }}

    @RequestMapping(method= RequestMethod.POST,value="/")
public ApiResponseDTO post(@RequestBody UserRequest user,@RequestHeader(name="token") String token){
        if (Objects.nonNull (passcodeRepository.findByCode (token))){
        userServices.post (user);
            return responseUtil.ok(200,"posted successfully","success");}
        else {
            return responseUtil.ok (404, "Wrong code", "Error");
        }
    }
@RequestMapping(method=RequestMethod.PUT,value="/{id}")
public ApiResponseDTO update(@RequestBody User user, @PathVariable("id") String id,@RequestHeader(name="token")String token){
    if (Objects.nonNull (passcodeRepository.findByCode (token))){
        userServices.update (id,user);
        return responseUtil.ok(200,"updated successfully","success");
}
    else {
        return responseUtil.ok (404, "Wrong code", "Error");
    }}

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ApiResponseDTO viewById(@PathVariable("id") String id,@RequestHeader(name="token") String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))){
        return responseUtil.ok(200,userServices.getId (id),"success");
    } else {
            return responseUtil.ok (404, "Wrong code", "Error");
        }}
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ApiResponseDTO deleteUser(@RequestBody User user, @PathVariable("id") String id,@RequestHeader(name="token")String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))){
            userServices.deleteUser (id, user);
        return responseUtil.ok(200,"deleted successfully","success");}
else {
        return responseUtil.ok (404, "Wrong code", "Error");
    }
}}
