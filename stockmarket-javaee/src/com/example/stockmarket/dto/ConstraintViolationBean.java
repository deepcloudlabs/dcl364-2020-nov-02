package com.example.stockmarket.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;

public class ConstraintViolationBean {
	private List<ConstraintViolationPair> violations;

	public ConstraintViolationBean() {
		violations = new ArrayList<>();
	}

	public List<ConstraintViolationPair> getViolations() {
		return violations;
	}

	public void setViolations(List<ConstraintViolationPair> violations) {
		this.violations = violations;
	}

	public void addConstraintViolation(ConstraintViolation<?> cv) {
		this.violations.add(new ConstraintViolationPair(cv.getPropertyPath().toString(), cv.getMessage()));
	}
}
