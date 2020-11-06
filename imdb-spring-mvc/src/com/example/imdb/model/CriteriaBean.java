package com.example.imdb.model;

import java.util.Collection;

import com.example.imdb.entity.Movie;

/**
 * 
 * @author Binnur Kurt
 *
 */
public class CriteriaBean {
	private Boolean yearRangeSelected=true;
	private Integer fromYear;
	private Integer toYear;
	private Boolean genreSelected=true;
	private Integer genre;
	private Boolean directorSelected=false;
	private Integer director;
	private Collection<Movie> movies;
	
	public CriteriaBean() {
	}

	public Collection<Movie> getMovies() {
		return movies;
	}

	public void setMovies(Collection<Movie> movies) {
		this.movies = movies;
	}


	public Boolean getYearRangeSelected() {
		return yearRangeSelected;
	}

	public void setYearRangeSelected(Boolean yearRangeSelected) {
		this.yearRangeSelected = yearRangeSelected;
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

	public Boolean getGenreSelected() {
		return genreSelected;
	}

	public void setGenreSelected(Boolean genreSelected) {
		this.genreSelected = genreSelected;
	}

	public Integer getGenre() {
		return genre;
	}

	public void setGenre(Integer genre) {
		this.genre = genre;
	}

	public Boolean getDirectorSelected() {
		return directorSelected;
	}

	public void setDirectorSelected(Boolean directorSelected) {
		this.directorSelected = directorSelected;
	}

	public Integer getDirector() {
		return director;
	}

	public void setDirector(Integer director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "CriteriaBean [yearRangeSelected=" + yearRangeSelected
				+ ", fromYear=" + fromYear + ", toYear=" + toYear
				+ ", genreSelected=" + genreSelected + ", genre=" + genre
				+ ", directorSelected=" + directorSelected + ", director="
				+ director + "]";
	}

}
