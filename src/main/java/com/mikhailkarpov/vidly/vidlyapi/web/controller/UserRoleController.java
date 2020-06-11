package com.mikhailkarpov.vidly.vidlyapi.web.controller;

import com.mikhailkarpov.vidly.vidlyapi.exception.MyBadRequestException;
import com.mikhailkarpov.vidly.vidlyapi.exception.UserRoleNotFoundException;
import com.mikhailkarpov.vidly.vidlyapi.service.UserRoleService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserRoleDto;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserRoleDtoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users/roles")
@Slf4j
public class UserRoleController {

    private UserRoleService userRoleService;

    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping
    public ResponseEntity<UserRoleDtoList> findAll() {
        log.debug("Request for all user_roles");
        List<UserRoleDto> roleList = userRoleService.findAll();
        return ResponseEntity.ok(new UserRoleDtoList(roleList));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserRoleDto> findById(@PathVariable Long id) {
        log.debug("Request for user_role with id {}", id);
        UserRoleDto userRole = userRoleService.findById(id).orElseThrow(() -> new UserRoleNotFoundException(id));
        return ResponseEntity.ok(userRole);
    }

    @PostMapping
    public ResponseEntity<UserRoleDto> create(@Valid @RequestBody UserRoleDto role) {
        log.debug("Request to create new user_role: {}", role);
        UserRoleDto userRole = userRoleService.create(role);
        return ResponseEntity.ok(userRole);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserRoleDto> update(@PathVariable Long id, @Valid @RequestBody UserRoleDto role) {
        log.debug("Request to update user_role: {}", role);

        Long roleId = role.getId();

        if (roleId == null)
            throw new MyBadRequestException("Role id is null");
        if (!roleId.equals(id))
            throw new MyBadRequestException("Role id and URI id don't match");

        UserRoleDto updatedRole = userRoleService.update(role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        log.debug("Request to delete role with id {}", id);
        userRoleService.deleteById(id);
    }
}
