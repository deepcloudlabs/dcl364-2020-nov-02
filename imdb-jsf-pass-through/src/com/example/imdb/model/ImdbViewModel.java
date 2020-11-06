package com.example.imdb.model;

import java.util.Collection;
import java.util.Collections;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;

import com.example.imdb.domain.Movie;
import com.example.imdb.service.MovieService;

@Named
@RequestScoped
public class ImdbViewModel {
	@Inject
	private MovieService movieService;
	@NotNull(message="You must enter a year")
	private Integer fromYear;
	@NotNull(message="You must enter a year")
	private Integer toYear;
	private Collection<String> genres;
	private String genre;
	private Collection<Movie> movies= Collections.emptyList();

	public ImdbViewModel() {
	}

	public Integer getFromYear() {
		return fromYear;
	}

	public void setFromYear(Integer fromYear) {
		this.fromYear = fromYear;
	}

	public Integer getToYear() {
		return toYear;
	}

	public void setToYear(Integer toYear) {
		this.toYear = toYear;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Collection<String> getGenres() {
		return genres;
	}

	public Collection<Movie> getMovies() {
		return movies;
	}
	public void doSearch(){
		movies= movieService.findAllMoviesByYearRangeAndGenre(
				genre, fromYear, toYear);
	}

}
