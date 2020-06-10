package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRoleEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // for Jackson
@Getter
@ToString
public class UserRoleDto {

    private Long id;

    @NotEmpty
    private String name;

    public static UserRoleDto fromEntity(UserRoleEntity entity) {
        UserRoleDto dto = new UserRoleDto();

        dto.id = entity.getId();
        dto.name = entity.getName();

        return dto;
    }
}
