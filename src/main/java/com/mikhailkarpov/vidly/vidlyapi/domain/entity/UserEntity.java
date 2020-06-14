package com.mikhailkarpov.vidly.vidlyapi.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // for JPA
@AllArgsConstructor
@Getter
@Setter
public class UserEntity extends BaseEntity {

    private String email;

    private String password;

    @ElementCollection(targetClass = UserRole.class)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    public boolean addRole(UserRole role) {
        return roles.add(role);
    }

    public boolean deleteRole(UserRole role) {
        return roles.remove(role);
    }
}
