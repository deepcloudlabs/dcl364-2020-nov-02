package com.example.imdb.model;

import javax.mvc.binding.MvcBinding;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.ws.rs.FormParam;

/**
 * 
 * @author Binnur Kurt
 *
 */
@Valid
public class CriteriaBean {
	private boolean yearRangeSelected = true;
	@MvcBinding
	@FormParam("fromYear")
	private int fromYear;
	@MvcBinding
	@FormParam("toYear")
	private int toYear;
	private boolean genreSelected = true;
	@MvcBinding
	@FormParam("genre")
	@Min(0)
	private int genre;
	private boolean directorSelected = false;
	private int director;

	public CriteriaBean() {
	}

	public boolean isYearRangeSelected() {
		return yearRangeSelected;
	}

	public void setYearRangeSelected(boolean yearRangeSelected) {
		this.yearRangeSelected = yearRangeSelected;
	}

	public int getFromYear() {
		return fromYear;
	}

	public void setFromYear(int fromYear) {
		this.fromYear = fromYear;
	}

	public int getToYear() {
		return toYear;
	}

	public void setToYear(int toYear) {
		this.toYear = toYear;
	}

	public boolean isGenreSelected() {
		return genreSelected;
	}

	public void setGenreSelected(boolean genreSelected) {
		this.genreSelected = genreSelected;
	}

	public int getGenre() {
		return genre;
	}

	public void setGenre(int genre) {
		this.genre = genre;
	}

	public boolean isDirectorSelected() {
		return directorSelected;
	}

	public void setDirectorSelected(boolean directorSelected) {
		this.directorSelected = directorSelected;
	}

	public int getDirector() {
		return director;
	}

	public void setDirector(int director) {
		this.director = director;
	}

	@Override
	public String toString() {
		return "CriteriaBean [yearRangeSelected=" + yearRangeSelected + ", fromYear=" + fromYear + ", toYear=" + toYear
				+ ", genreSelected=" + genreSelected + ", genre=" + genre + ", directorSelected=" + directorSelected
				+ ", director=" + director + "]";
	}

}
