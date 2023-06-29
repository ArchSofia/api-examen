package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;
	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

/*	@GetMapping("/{genre}")
	ResponseEntity<List<MovieServiceClient.MovieDto>> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(
				movieServiceClient.getMovieByGenre(genre)
				serieServiceClient.getSerieByGenre(genre));
	}*/
	@GetMapping("/{genre}")
	ResponseEntity<List<Object>> getGenre(@PathVariable String genre) {
		List<Object> genreItems = new ArrayList<>();
		genreItems.addAll(catalogService.getMovieByGenre(genre));
		genreItems.addAll(catalogService.getSerieByGenre(genre));

		return ResponseEntity.ok(genreItems);
	}

	@PostMapping("/saveMovie")
	@ResponseStatus(code = HttpStatus.CREATED)
	public MovieServiceClient.MovieDto saveMovie(@RequestBody MovieServiceClient.MovieDto movieDto) {
		return catalogService.createMovie(movieDto);
	}

	@PostMapping("/saveSeries")
	@ResponseStatus(code = HttpStatus.CREATED)
	public SerieServiceClient.Serie saveSeries(@RequestBody SerieServiceClient.Serie serie) {
		return catalogService.createSeries(serie);
	}
}