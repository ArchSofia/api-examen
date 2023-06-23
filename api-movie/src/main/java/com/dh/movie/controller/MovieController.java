package com.dh.movie.controller;

import com.dh.movie.event.FinalizarCursoEventProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {


    private final MovieService movieService;

    private final FinalizarCursoEventProducer finalizarCursoEventProducer;

    public MovieController(MovieService movieService, FinalizarCursoEventProducer finalizarCursoEventProducer) {
        this.movieService = movieService;
        this.finalizarCursoEventProducer = finalizarCursoEventProducer;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(
                movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.save(movie));
    }

/*    // TODO: agregar un service para poder enviar el mensaje: lo hago en el mismo método que crea la película?
    @PatchMapping("/finalizarCurso")
    @ResponseStatus(code = HttpStatus.OK)
    public void finalizarCurso(){
        finalizarCursoEventProducer.publishFinalizarCursoEvent(new FinalizarCursoEventProducer.Data(2L, "Pelicula","Terror", "urlstream" ));
    }*/
}
