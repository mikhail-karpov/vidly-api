package com.mikhailkarpov.vidly.vidlyapi.config;

import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Genre;
import com.mikhailkarpov.vidly.vidlyapi.domain.entity.Movie;
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
        Genre comedy = new Genre("Comedy");
        Genre thriller = new Genre("Thriller");
        Genre romantic = new Genre("Romantic");

        genreRepository.save(comedy);
        genreRepository.save(thriller);
        genreRepository.save(romantic);

        Movie dieHard = new Movie("Die Hard", new Genre("Action"), 10, 2.5);
        movieRepository.save(dieHard);
    }
}
