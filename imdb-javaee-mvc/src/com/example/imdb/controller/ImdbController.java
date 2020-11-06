package com.example.imdb.controller;

import javax.inject.Inject;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.binding.BindingResult;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import com.example.imdb.model.CriteriaBean;
import com.example.imdb.service.MovieService;

@Path("search")
public class ImdbController {
	@Inject
	private MovieService movieService;
	@Inject
	private Models models;
	@Inject
	private BindingResult bindingResult;

	@GET
	@Controller
	public String open() {
		return "/WEB-INF/views/home.jsp";
	}

	@POST
	@Controller
	public String search(@Valid @BeanParam CriteriaBean criteria) {
		if (bindingResult.isFailed()) {
			System.err.println("Form has errors.");
		}
		System.err.println(criteria);
		var movies = movieService.findAllMoviesByCriteria(criteria);
		models.put("movies", movies); // JSP: ${movies}
		return "/WEB-INF/views/home.jsp";
	}
}
