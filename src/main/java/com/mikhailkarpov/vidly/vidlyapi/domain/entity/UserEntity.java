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
public class UserEntity extends BaseEntity {

    @Setter
    private String email;

    @Setter
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRoleEntity> roles = new HashSet<>();

    public void addRole(UserRoleEntity role) {
        if (this.roles.add(role)) {
            role.getUsers().add(this);
        }
    }

    public void deleteRole(UserRoleEntity role) {
        this.roles.remove(role);
        role.getUsers().remove(this);
    }
}
