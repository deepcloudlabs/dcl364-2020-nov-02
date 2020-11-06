package com.example.imdb.controller;

import static com.example.imdb.config.UrlConfig.REQUEST;
import static com.example.imdb.config.UrlConfig.SEARCH;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Movie;
import com.example.imdb.model.CriteriaBean;
import com.example.imdb.service.MovieService;

@Controller
@Scope(REQUEST)
@RequestMapping(SEARCH)
public class ImdbController {
	@Autowired private MovieService movieService;
	
	@ModelAttribute("genres")
	public Collection<Genre> getGenres(){
		return movieService.findAllGenres();
	}
	
	@GetMapping
	public String open() {
		return "search";
	}
	@PostMapping
	public String search(
			@ModelAttribute("imdb") CriteriaBean criteria) {
		Collection<Movie> movies = 
			movieService.findAllMoviesByCriteria(criteria);
		criteria.setMovies(movies);
		return "search";
	}
}

