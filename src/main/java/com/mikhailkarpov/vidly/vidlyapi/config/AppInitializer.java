package com.mikhailkarpov.vidly.vidlyapi.config;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.GenreEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.MovieEntity;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.GenreRepository;
import com.mikhailkarpov.vidly.vidlyapi.domain.repo.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppInitializer implements CommandLineRunner {

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private MovieRepository movieRepository;

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
    }
}
