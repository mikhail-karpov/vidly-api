package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRole;
import com.mikhailkarpov.vidly.vidlyapi.validation.ValidPassword;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDto {

    private Long id;

    @Email
    @NotEmpty
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ValidPassword
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ValidPassword
    private String matchingPassword;

    private List<UserRole> roles;

    public static UserDto fromEntity(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.id = entity.getId();
        dto.email = entity.getEmail();
        dto.roles = entity.getRoles().stream().collect(Collectors.toList());
        dto.password = entity.getPassword();

        return dto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + "[******]" + '\'' +
                ", matchingPassword='" + "[******]" + '\'' +
                ", roles=" + roles +
                '}';
    }
}
