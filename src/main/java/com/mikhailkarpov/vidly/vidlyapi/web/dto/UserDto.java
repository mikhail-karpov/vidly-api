package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED) // for Jackson
@AllArgsConstructor
@Getter
@ToString
public class UserDto {

    private Long id;
    private String email;

    public static UserDto fromEntity(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.id = entity.getId();
        dto.email = entity.getEmail();

        return dto;
    }
}
