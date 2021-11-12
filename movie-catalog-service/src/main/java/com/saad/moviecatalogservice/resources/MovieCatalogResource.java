package com.saad.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.saad.moviecatalogservice.models.CatalogItem;
import com.saad.moviecatalogservice.models.Movie;
import com.saad.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		// get all movie ratings for given userId
		UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/users/" + userId, UserRating.class);
		
		return ratings
				.getUserRatings()
				.stream()
				.map(rating -> {
					// for each movieId, get movie info
					Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
					
					// put it all together
					return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
				})
				.collect(Collectors.toList());
	}
}
