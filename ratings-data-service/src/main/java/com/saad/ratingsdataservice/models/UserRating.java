package com.saad.ratingsdataservice.models;

import java.util.List;

public class UserRating {

	private List<Rating> userRatings;

	public UserRating() {
		super();
	}

	public List<Rating> getUserRatings() {
		return userRatings;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRatings = userRatings;
	}
	
	
}
