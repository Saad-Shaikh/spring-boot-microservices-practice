package com.saad.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saad.moviecatalogservice.models.CatalogItem;
import com.saad.moviecatalogservice.models.UserRating;
import com.saad.moviecatalogservice.services.MovieInfo;
import com.saad.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
	@Autowired
	UserRatingInfo userRatingInfo;
	
	@Autowired
	MovieInfo movieInfo;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		
		// get all movie ratings for given userId
		UserRating ratings = userRatingInfo.getUserRating(userId);
		
		return ratings
				.getUserRatings()
				.stream()
				.map(rating -> movieInfo.getMovieInfo(rating))
				.collect(Collectors.toList());
	}
}
