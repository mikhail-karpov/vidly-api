package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ApiErrorDto {

    private Integer status;
    private String message;

    public ApiErrorDto(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }
}
