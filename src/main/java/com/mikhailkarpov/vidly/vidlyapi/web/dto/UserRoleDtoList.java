package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserRoleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserRoleDtoList {

    @NotEmpty
    private List<UserRoleDto> roles;

    public UserRoleDtoList(@NotEmpty List<UserRoleDto> roles) {
        this.roles = roles;
    }
}
