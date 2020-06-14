package com.mikhailkarpov.vidly.vidlyapi.security;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository
                .findByEmail(username)
                .orElseThrow(() -> {
                    String errMsg = String.format("User with email %s not found", username);
                    return new UsernameNotFoundException(errMsg);
        });

        String password = user.getPassword();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

        return User.builder()
                .username(username)
                .password(password)
                .authorities(authorities).build();
    }

}
