package com.example.imdb.viewmodel;

import java.io.Serializable;
import java.util.Collection;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@SuppressWarnings("serial")
@Named("searchView")
@SessionScoped
public class SearchViewModel implements Serializable {
	private Integer fromYear; // input: #{searchView.fromYear}
	private Integer toYear; // input
	private String genre; // input
	private Collection<Movie> movies; // output
	@Inject 
	transient private MovieService movieService;
	
	public SearchViewModel() {
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

	public Collection<Movie> getMovies() {
		return movies;
	}

	// action: #{searchView.fromYear}
	public void doSearch(AjaxBehaviorEvent event) {
		movies = movieService.findAllMoviesByYearRangeAndGenre(genre, fromYear, toYear);
	}
}
