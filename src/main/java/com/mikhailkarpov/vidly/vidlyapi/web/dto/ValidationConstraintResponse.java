package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ValidationConstraintResponse {

    private Map<String, String> errors;

}
