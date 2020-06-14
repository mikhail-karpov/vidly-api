package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ApiError {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    public ApiError(String message) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
    }

    public ApiError(Throwable t) {
        this(t.getMessage());
    }
}
