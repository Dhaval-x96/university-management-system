package com.spring.university.json;

import java.io.Serializable;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class ApiResponse<T> implements Serializable {
    private T response;
    private Integer status;
    private String exception;
    private String error;
    private List<String> message;
    private String path;
    private Long timeStamp;


    public ApiResponse() {
    }
    public ApiResponse(T t) {
        this.response = t;
    }
    public ApiResponse(T t, String path) {
        this.response = t;
        this.path=path;
        this.timeStamp =  LocalDateTime.now(Clock.systemUTC()).toInstant(ZoneOffset.UTC).toEpochMilli();
        this.status = 200;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "response=" + response +
                ", status=" + status +
                ", exception='" + exception + '\'' +
                ", error='" + error + '\'' +
                ", message=" + message +
                ", path='" + path + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
