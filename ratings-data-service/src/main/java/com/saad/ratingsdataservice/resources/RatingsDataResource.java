package com.saad.ratingsdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saad.ratingsdataservice.models.Rating;
import com.saad.ratingsdataservice.models.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRatingData(@PathVariable("movieId") String movieId) {
		return new Rating(movieId, 5);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
				new Rating("100", 5), 
				new Rating("150", 3)
			);
		UserRating userRating = new UserRating();
		userRating.setUserRatings(ratings);
		return userRating;
	}
}
