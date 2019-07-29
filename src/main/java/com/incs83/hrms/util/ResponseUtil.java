package com.incs83.hrms.util;



import com.incs83.hrms.responseDTO.ApiResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class ResponseUtil {
    public ApiResponseDTO ok(Integer code , Object data , String message){
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        apiResponseDTO.setCode(code);
        apiResponseDTO.setData(data);
        apiResponseDTO.setMessage(message);

        return apiResponseDTO;

    }
}
//response util {
