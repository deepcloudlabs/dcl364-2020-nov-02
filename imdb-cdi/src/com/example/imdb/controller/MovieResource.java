package com.example.imdb.controller;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

// http://localhost:5100/imdb/api/v1/movies
@Path("/movies")
public class MovieResource {
	@Inject private MovieService movieSrv; // loosely-coupled
	
    // http://localhost:5100/imdb/api/v1/movies?from=1970&to=1979
	@GET
	@Produces("application/json")
	public Collection<Movie> getMovies(
			@QueryParam("from") int from, 
			@QueryParam("to") int to){
		System.err.println(movieSrv.getClass());
		return movieSrv.findAllMoviesByYearRange(from, to);
	}
}
