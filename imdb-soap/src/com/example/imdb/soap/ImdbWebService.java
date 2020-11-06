package com.example.imdb.soap;

import java.util.Collection;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.example.imdb.entity.Genre;
import com.example.imdb.entity.Movie;
import com.example.imdb.service.MovieService;

@WebService
public class ImdbWebService {
	@Inject private MovieService movieSrv;
	
	@WebMethod(operationName = "filmTurleriniGetir")
	@WebResult(name = "turler")
	public Collection<Genre> getAllGenres(){
		return movieSrv.findAllGenres();
	}
	
	@WebMethod(operationName = "filmleriBul")
	@WebResult(name = "filmler")
	public Collection<Movie> getMovies(
	       @WebParam(name="from") 
	       @XmlElement(required = true) Integer from,
	       @WebParam(name="to") 
	       @XmlElement(required = true) Integer to,
	       @WebParam(name="genre") 
	       @XmlElement(required = true) String genre
	){
		return movieSrv.findAllMoviesByYearRangeAndGenre(genre, from, to);
	}
}
