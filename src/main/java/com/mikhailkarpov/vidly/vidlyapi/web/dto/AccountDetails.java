package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDetails {

    private Long id;
    private String name;
    private String email;
}
