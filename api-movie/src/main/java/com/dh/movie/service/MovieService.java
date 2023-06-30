package com.dh.movie.service;


import com.dh.movie.event.PeliculasEventProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final PeliculasEventProducer peliculasEventProducer;

    public MovieService(MovieRepository movieRepository, PeliculasEventProducer peliculasEventProducer) {
        this.movieRepository = movieRepository;
        this.peliculasEventProducer = peliculasEventProducer;
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {

        peliculasEventProducer.publishFinalizarCursoEvent(new PeliculasEventProducer.Data(movie.getId(), movie.getName(), movie.getGenre()));

        return movieRepository.save(movie);
    }
}
