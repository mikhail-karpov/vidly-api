package com.mikhailkarpov.vidly.vidlyapi.config;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.GenreEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.MovieEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.UserRole;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.GenreRepository;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.MovieRepository;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.UserRepository;
import com.mikhailkarpov.vidly.vidlyapi.service.UserService;
import com.mikhailkarpov.vidly.vidlyapi.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class AppInitializer implements CommandLineRunner {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        GenreEntity comedy = new GenreEntity("Comedy");
        GenreEntity thriller = new GenreEntity("Thriller");
        GenreEntity romantic = new GenreEntity("Romantic");
        GenreEntity action = new GenreEntity("Action");

        genreRepository.save(comedy);
        genreRepository.save(thriller);
        genreRepository.save(romantic);
        genreRepository.save(action);

        MovieEntity dieHard = new MovieEntity("Die Hard", action, 10, 2.5);
        movieRepository.save(dieHard);

        String email = "mikhailkarpov@hotmail.com";
        String password = "$2a$10$zj/QDtV2oUVPzWNyk80Hnek8w3/EMwZpBAgdNP0nxly8moU3fx10q";
        Set<UserRole> roles = new HashSet<>(Arrays.asList(UserRole.ADMIN, UserRole.USER));

        userRepository.save(new UserEntity(email, password, roles));
    }
}
