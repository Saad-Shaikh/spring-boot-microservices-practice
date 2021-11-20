package com.saad.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.saad.moviecatalogservice.models.CatalogItem;
import com.saad.moviecatalogservice.models.Movie;
import com.saad.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod="getFallbackMovieInfo",
			commandProperties = {
					@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000"),
					@HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="5"),
					@HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="50"),
					@HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="5000"),
			}
	)
	public CatalogItem getMovieInfo(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
	
	public CatalogItem getFallbackMovieInfo(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}
}
