package com.mikhailkarpov.vidly.vidlyapi.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRole;
import com.mikhailkarpov.vidly.vidlyapi.validation.Password;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;

@Data
public class UserDto {

    private Long id;

    @Email(message = "Invalid email")
    @NotEmpty(message = "Email is required")
    private String email;

    @JsonProperty(access = WRITE_ONLY)
    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Password(message = "Password must contain at least one upper-case, one lower-case and one special symbol characters")
    private String password;

    @NotEmpty
    private List<UserRole> roles;

    public static UserDto fromEntity(UserEntity entity) {
        UserDto dto = new UserDto();

        dto.id = entity.getId();
        dto.email = entity.getEmail();
        dto.password = entity.getPassword();
        dto.roles = new ArrayList<>(entity.getRoles());

        return dto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + getEmail() + '\'' +
                ", password='" + "[******]" + '\'' +
                ", roles=" + roles +
                '}';
    }
}