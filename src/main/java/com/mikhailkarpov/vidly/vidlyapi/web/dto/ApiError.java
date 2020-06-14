package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiError {

    private Integer status;
    private String message;

    public ApiError(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
