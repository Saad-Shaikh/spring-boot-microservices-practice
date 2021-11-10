package com.saad.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saad.moviecatalogservice.models.CatalogItem;
import com.saad.moviecatalogservice.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		List<Rating> ratings = Arrays.asList(
					new Rating("123", 5), 
					new Rating("456", 3)
				);
		
		return ratings
				.stream()
				.map(rating -> new CatalogItem("John Wick Chapter 1", "Test Desc", 5))
				.collect(Collectors.toList());
	}
}
