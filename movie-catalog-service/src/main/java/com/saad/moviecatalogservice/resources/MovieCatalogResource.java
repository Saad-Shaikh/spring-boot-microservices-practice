package com.saad.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.saad.moviecatalogservice.models.CatalogItem;
import com.saad.moviecatalogservice.models.Movie;
import com.saad.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
					new Rating("123", 5), 
					new Rating("456", 3)
				);
		
		return ratings
				.stream()
				.map(rating -> {
					Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
					return new CatalogItem(movie.getName(), "Test Desc", rating.getRating());
				})
				.collect(Collectors.toList());
	}
}
