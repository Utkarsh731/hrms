package com.incs83.hrms.services;

import com.incs83.hrms.entity.Passcode;
import com.incs83.hrms.entity.User;
import com.incs83.hrms.repository.PasscodeRepository;
import com.incs83.hrms.repository.UserRepository;
import com.incs83.hrms.request.LoginRequest;
import com.incs83.hrms.responseDTO.ApiResponseDTO;
import com.incs83.hrms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Optional;

@Service
public class LoginServices {
@Autowired
    UserRepository userRepository;
@Autowired
    ResponseUtil responseUtil;
@Autowired
    PasscodeRepository passcodeRepository;
    public ApiResponseDTO post(LoginRequest loginRequest) {
        String email=loginRequest.getEmail ();
        String password=loginRequest.getPassword ();
        String matchEmail=userRepository.findByEmailAndPassword (email,password).getEmail ();
        String matchPassword=userRepository.findByEmailAndPassword (email,password).getPassword ();

        if((email.equals (matchEmail))&&(password.equals (matchPassword))){
            String token=email+","+password+","+userRepository.findByEmailAndPassword (email,password).getRoleList ().get (0).getName ();
            Base64.Encoder enc = Base64.getEncoder();
            String encoded = enc.encodeToString(token.getBytes());

            Passcode passcode=new Passcode ();
            passcode.setEmail (email);
            passcode.setCode (encoded);
            passcodeRepository.save (passcode);
          return   responseUtil.ok (200,encoded,"password matched");
        }
        else{
return responseUtil.ok(404,"Error","Not found");
        }
    }
}
