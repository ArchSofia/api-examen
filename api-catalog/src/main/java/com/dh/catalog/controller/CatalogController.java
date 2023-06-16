package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;

import com.dh.catalog.client.SerieServiceClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieServiceClient;

	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {

		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
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

		genreItems.addAll(movieServiceClient.getMovieByGenre(genre));
		genreItems.addAll(serieServiceClient.getSerieByGenre(genre));

		return ResponseEntity.ok(genreItems);
	}

	//TODO: ACA VAS A HACER DOS POST, UNO DE PELICULAS YU OPTRO DE SERIES! usando el feign que armaste
}