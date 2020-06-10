package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // for Jackson
@AllArgsConstructor
@Getter
@ToString
public class UserDto {

    private Long id;
    private String email;
    private UserRoleDtoList roles;

    public static UserDto fromEntity(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.id = entity.getId();
        dto.email = entity.getEmail();

        List<UserRoleDto> roles = entity.getRoles().stream().map(UserRoleDto::fromEntity).collect(Collectors.toList());
        dto.roles = new UserRoleDtoList(roles);

        return dto;
    }
}
