package com.mikhailkarpov.vidly.vidlyapi.config;

import com.mikhailkarpov.vidly.vidlyapi.exception.MyBadRequestException;
import com.mikhailkarpov.vidly.vidlyapi.exception.MyResourceNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.ApiErrorDto;
import com.sun.istack.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler({UserAlreadyExistsException.class, MyResourceNotFoundException.class, MyBadRequestException.class})
    public ResponseEntity<Object> handleException(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e instanceof MyResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        ApiErrorDto errorDto = new ApiErrorDto(status, e.getMessage());
        return handleExceptionInternal(e, errorDto, new HttpHeaders(), status, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
