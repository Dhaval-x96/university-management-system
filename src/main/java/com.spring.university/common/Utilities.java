package com.spring.university.common;

import com.spring.university.json.ApiResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Utilities {
    public static ApiResponse getExceptionObject(HttpServletRequest request, Integer status, Exception ex){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setTimeStamp(LocalDateTime.now(Clock.systemUTC()).toInstant(ZoneOffset.UTC).toEpochMilli());
        apiResponse.setPath(request.getRequestURL().toString());
        apiResponse.setStatus(status);
        apiResponse.setException(ex.getMessage());
        apiResponse.setError("Not Acceptable");
        return apiResponse;
    }
}
