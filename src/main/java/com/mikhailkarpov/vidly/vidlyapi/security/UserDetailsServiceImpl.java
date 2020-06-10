package com.mikhailkarpov.vidly.vidlyapi.security;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByEmail(username)
                .orElseThrow(() -> {
                    String errMsg = String.format("User with email %s not found", username);
                    return new UsernameNotFoundException(errMsg);
        });

        return new UserDetailsImpl(user.getEmail(), user.getPassword());
    }

    private static class UserDetailsImpl implements UserDetails {

        private String username;
        private String password;

        public UserDetailsImpl(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            //todo add authorities
            return new ArrayList<>();
        }

        @Override
        public String getPassword() {
            return password;
        }

        @Override
        public String getUsername() {
            return username;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
