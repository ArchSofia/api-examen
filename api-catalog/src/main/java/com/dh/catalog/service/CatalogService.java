package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

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

    //TODO: PARA PROBAR PRIMERO HACER UN FALLBACK CON UN MENSAJE PARA VER QUE FUNCIONE
    public List<Movie> findMovieFallBack(String genre) {
        return movieRepository.findAllByGenre(genre);
    }

    public MovieServiceClient.MovieDto createMovie(MovieServiceClient.MovieDto movieDto) {
        return movieServiceClient.saveMovie(movieDto);
    }


    /*SERIES */
    @Retry(name = "retryFindByGenre")
    @CircuitBreaker(name = "catalogByGenre", fallbackMethod = "findSeriesFallBack")
    public List<SerieServiceClient.SerieDto> getSerieByGenre(String genre) {
        return serieServiceClient.getSerieByGenre(genre);
    }

    public List<Serie> findSeriesFallBack(String genre) {
        return serieRepository.findAllByGenre(genre);
    }

    public SerieServiceClient.SerieDto createSeries(SerieServiceClient.SerieDto serieDto) {
        return serieServiceClient.saveSerie(serieDto);
    }

}
