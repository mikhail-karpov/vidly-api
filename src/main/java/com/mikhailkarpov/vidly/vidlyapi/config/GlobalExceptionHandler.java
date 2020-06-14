package com.mikhailkarpov.vidly.vidlyapi.config;

import com.mikhailkarpov.vidly.vidlyapi.exception.MyBadRequestException;
import com.mikhailkarpov.vidly.vidlyapi.exception.MyResourceNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.ApiError;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.ValidationConstraintResponse;
import com.sun.istack.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler({UserAlreadyExistsException.class, MyResourceNotFoundException.class, MyBadRequestException.class})
    public ResponseEntity<Object> handleException(Exception e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (e instanceof MyResourceNotFoundException) {
            status = HttpStatus.NOT_FOUND;
        }

        ApiError errorDto = new ApiError(status, e.getMessage());
        return handleExceptionInternal(e, errorDto, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException e, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {
            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                String errorMsg = error.getDefaultMessage();

                errors.put(fieldName, errorMsg);
            }
        });

        ValidationConstraintResponse response = errors.isEmpty() ? null : new ValidationConstraintResponse(errors);
        return handleExceptionInternal(e, response, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
