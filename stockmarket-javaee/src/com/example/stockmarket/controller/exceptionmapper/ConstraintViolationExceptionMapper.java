package com.example.stockmarket.controller.exceptionmapper;

import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.bind.JsonbBuilder;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.example.stockmarket.dto.ConstraintViolationBean;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
	// Java XML Api: SAX, DOM, JAXP (Java API for Xml Processing)
	// JAXB (Java API for Xml Binding)
	// StAX, JAXR
	// JSON-P(rocessing) API -> Java EE 7
	// JSON-B(inding) API -> Java EE 8
	@Override
	public Response toResponse(ConstraintViolationException e) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(this.constraintViolationExceptionToJsonUsingJsonb(e))
				.type(MediaType.APPLICATION_JSON).build();
	}

	@SuppressWarnings("unused")
	private String constraintViolationExceptionToJson(ConstraintViolationException e) {
		return e.getConstraintViolations().stream()
				.map(cv -> "\"" + cv.getPropertyPath() + "\": \"" + cv.getMessage() + "\"")
				.collect(Collectors.joining(", ", "{", "}"));
	}

	// JSON-P
	@SuppressWarnings("unused")
	private String constraintViolationExceptionToJsonUsingJsonp(ConstraintViolationException e) {
		var jsonBuilder = Json.createObjectBuilder();

		for (var cv : e.getConstraintViolations())
			jsonBuilder.add(cv.getPropertyPath().toString(), cv.getMessage());
		return jsonBuilder.build().toString();
	}

	// JSON-B: POJO (field) <--> JSON (field)
	// We are using Yasson as JSON-B implementation
	private String constraintViolationExceptionToJsonUsingJsonb(ConstraintViolationException e) {
		var cvb = new ConstraintViolationBean();
		for (var cv : e.getConstraintViolations())
			cvb.addConstraintViolation(cv);
		var jsonb = JsonbBuilder.create();
		return jsonb.toJson(cvb);
	}
}

