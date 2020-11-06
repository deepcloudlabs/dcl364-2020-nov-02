package com.example.stockmarket.dto;

import javax.json.bind.annotation.JsonbProperty;

public class ConstraintViolationPair {
	@JsonbProperty("property")
	private String field;
	@JsonbProperty("errorMessage")
	private String error;

	public ConstraintViolationPair() {
	}

	public ConstraintViolationPair(String field, String error) {
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ConstraintViolationPair [field=" + field + ", error=" + error + "]";
	}

}
