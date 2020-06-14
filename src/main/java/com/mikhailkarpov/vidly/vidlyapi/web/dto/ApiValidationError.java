package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApiValidationError extends ApiError {

    private List<FieldValidationError> errors;

    public ApiValidationError(@NotNull BindingResult result) {
        super("Validation error(s)");
        setErrors(result);
    }

    private void setErrors(BindingResult result) {
        this.errors = result
                .getFieldErrors()
                .stream()
                .map(error -> FieldValidationError.builder()
                        .field(error.getField())
                        .rejectedValue(error.getRejectedValue())
                        .message(error.getDefaultMessage()).build())
                .sorted(Comparator.comparing(FieldValidationError::getField))
                .collect(Collectors.toList());
    }


    @AllArgsConstructor
    @Getter
    @Builder
    private static class FieldValidationError {

        private String field;
        private Object rejectedValue;
        private String message;
    }
}
