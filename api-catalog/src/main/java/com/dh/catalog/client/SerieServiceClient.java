package com.dh.catalog.client;

import com.dh.catalog.model.serie.Season;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/*,url = "http://localhost:8085"*/
@FeignClient(name="api-serie")
public interface SerieServiceClient {

	@GetMapping("/api/v1/series/{genre}")
	List<Serie> getSerieByGenre(@PathVariable (value = "genre") String genre);

	@PostMapping("/api/v1/series/save")
	Serie saveSerie(@RequestBody Serie serieDto);

	@Getter
	@Setter
	@AllArgsConstructor
	class Serie{

		private String id;
		private String name;
		private String genre;

	}
/*
 Que pasa con chapter y season? private List<Season> seasons = new ArrayList<>(); le saque esto dsps se lo agrego si funciona bien
*/
}
