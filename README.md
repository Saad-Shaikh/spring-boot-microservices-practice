## What the project is -

This is supposed to be a demo backend for a movie rating website where a user can add movies they have watched and rate them.

## How it works -

There are 4 Spring Boot projects in this repository.

The **_discovery-server_** project acts as a Eureka Server which automatically discovers any Eureka clients and helps with service discovery.

The **_ratings-data-service_** project returns movieIDs and their respective ratings for a given userID.

The **_movie-info-service_** project returns the detailed movie info for a given movieID. _(This product uses the TMDB API but is not endorsed or certified by TMDB.)_

The **_movie-catalog-service_** project calls the **_ratings-data-service_** and **_movie-info-service_** and returns the entire catalog information for a given userID.

## How to run -

First start the **_discovery-server_** and then run the other 3 projects. You can look at the registered Eureka clients in the [Spring Eureka dashboard](http://localhost:8761/).

The **_movie-catalog-service_** endpoint is at http://localhost:8081/catalog/foo.

## Technologies -

* Spring Boot
* Spring Cloud
* Eureka Discovery Server
