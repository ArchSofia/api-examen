package com.dh.catalog.client;

import com.dh.catalog.model.serie.Season;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/*,url = "http://localhost:8085"*/
@FeignClient(name="api-serie")
public interface SerieServiceClient {

	@GetMapping("/api/v1/series/{genre}")
	List<SerieDto> getSerieByGenre(@PathVariable (value = "genre") String genre);


	@Getter
	@Setter
	class SerieDto{
		@Id
		private String id;
		private String name;
		private String genre;
		private List<Season> seasons = new ArrayList<>();
	}
// TODO: Que pasa con chapter y season?
}
