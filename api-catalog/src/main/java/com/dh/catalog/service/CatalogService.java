package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CatalogService {
    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;

    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;


    public CatalogService(MovieRepository movieRepository, SerieRepository serieRepository, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }

    /*MOVIE */
    @Retry(name = "retryFindByGenre")
    @CircuitBreaker(name = "catalogByGenre", fallbackMethod = "findMovieFallBack")
    public List<MovieServiceClient.MovieDto> getMovieByGenre(String genre) {
        return movieServiceClient.getMovieByGenre(genre);
    }

    public List<Movie> findMovieFallBack(String genre, Throwable throwable) {
        // Handle the fallback logic here
        // You can return a default list or perform any other fallback action
        return movieRepository.findAllByGenre(genre);
    }
    public MovieServiceClient.MovieDto createMovie(MovieServiceClient.MovieDto movie) {
        return movieServiceClient.saveMovie(movie);
    }



    /*SERIES */
    @Retry(name = "retryFindByGenre")
    @CircuitBreaker(name = "catalogByGenre", fallbackMethod = "findSeriesFallBack")
    public List<SerieServiceClient.Serie> getSerieByGenre(String genre) {
        return serieServiceClient.getSerieByGenre(genre);
    }

    // Fallback method for getSerieByGenre
    public List<SerieServiceClient.Serie> findSeriesFallBack(String genre, Throwable throwable)  {
        return serieRepository.findAllByGenre(genre);
    }


    public SerieServiceClient.Serie createSeries(SerieServiceClient.Serie serie) {
        return serieServiceClient.saveSerie(serie);
    }

}
