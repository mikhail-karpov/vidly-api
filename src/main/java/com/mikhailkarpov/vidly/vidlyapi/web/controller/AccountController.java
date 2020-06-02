package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.exception.AccountAlreadyExistsException;
import com.mikhailkarpov.vidly.vidlyapi.service.AccountService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AccountDetails;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.AccountRegistrationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/accounts")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Object> register(@RequestBody AccountRegistrationRequest request) {
        try {
            AccountDetails account = accountService.register(request);
            return ResponseEntity.ok().body(account);
        } catch (AccountAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
