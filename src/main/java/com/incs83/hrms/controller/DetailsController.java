package com.incs83.hrms.controller;

import com.incs83.hrms.entity.Details;
import com.incs83.hrms.entity.Passcode;
import com.incs83.hrms.repository.PasscodeRepository;
import com.incs83.hrms.request.DetailsRequest;
import com.incs83.hrms.responseDTO.ApiResponseDTO;
import com.incs83.hrms.services.DetailsServices;
import com.incs83.hrms.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/Details")
public class DetailsController {
    @Autowired
    private DetailsServices detailsServices;
    @Autowired
    private ResponseUtil responseUtil;
    @Autowired
    private PasscodeRepository passcodeRepository;
    @RequestMapping(method=RequestMethod.GET,value = "/")
    public ApiResponseDTO view(  @RequestHeader(name="token") String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))) {
            return responseUtil.ok (200, detailsServices.getAll ( ), "success");
        }
        else {
        return responseUtil.ok(404,"error","wrong code");
        }
        }

    @RequestMapping(method = RequestMethod.POST, value = "/")
    public ApiResponseDTO post(@RequestBody DetailsRequest details,@RequestHeader(name="token")String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))){
            detailsServices.post (details);
        return responseUtil.ok(200,"Data successfully posted ","success");
    }
else {
return responseUtil.ok(404,"error","wrong code");
}
}

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ApiResponseDTO viewById(@PathVariable("id") String id,@RequestHeader(name="token")String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))){
        return responseUtil.ok(200,detailsServices.getId (id),"success");
    }
else{
    return responseUtil.ok(404,"error","wrong code");
        }
}

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ApiResponseDTO updateDepartment(@RequestBody Details details, @PathVariable("id") String id, @RequestHeader(name="token")String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))) {
            detailsServices.updateDetails (id, details);
            return responseUtil.ok (200, "Successfully Updated", "success");
        }
        else {
        return responseUtil.ok(404,"error","Wrong code");
        }
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ApiResponseDTO deleteEmployee(@RequestBody Details details, @PathVariable("id") String id,@RequestHeader(name="token")String token) {
        if (Objects.nonNull (passcodeRepository.findByCode (token))) {
            detailsServices.deleteDetails (id, details);
            return responseUtil.ok (200, "data deleted successfully", "success");
        }
        else {
        return responseUtil.ok (404,"Error","Wrong code");
        }
}}
