package com.spring.university.aspects;

import com.spring.university.json.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalRequestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setTimeStamp(LocalDateTime.now(Clock.systemUTC()).toInstant(ZoneOffset.UTC).toEpochMilli());
        apiResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURL().toString());
        apiResponse.setStatus(status.value());
        apiResponse.setException(ex.getMessage());
        apiResponse.setError("Not Acceptable");
        List<String> messages = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        apiResponse.setMessage(messages);
        return new ResponseEntity<>(apiResponse, HttpStatus.PARTIAL_CONTENT);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception ex, WebRequest request, HttpStatus status){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setTimeStamp(LocalDateTime.now(Clock.systemUTC()).toInstant(ZoneOffset.UTC).toEpochMilli());
        apiResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURL().toString());
        apiResponse.setStatus(status.value());
        apiResponse.setException(status.toString());
        apiResponse.setError("------->>>>"+ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setTimeStamp(LocalDateTime.now(Clock.systemUTC()).toInstant(ZoneOffset.UTC).toEpochMilli());
        apiResponse.setPath(((ServletWebRequest)request).getRequest().getRequestURL().toString());
        apiResponse.setStatus(status.value());
        apiResponse.setException(status.toString());
        apiResponse.setError(ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
