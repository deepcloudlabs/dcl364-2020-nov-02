package com.example.stockmarket.controller.exceptionmapper;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

	@Override
	public Response toResponse(ConstraintViolationException e) {
		return Response.status(Response.Status.BAD_REQUEST)
				   .entity(this.constraintViolationExceptionToJson(e))
				   .type(MediaType.APPLICATION_JSON)
				   .build();
	}

	private String constraintViolationExceptionToJson(ConstraintViolationException e) {
		return e.getConstraintViolations()
		 .stream()
		 .map( cv -> "\""+cv.getPropertyPath() + "\": \""+cv.getMessage() +"\"" )
		 .collect(Collectors.joining(", ", "{", "}"));
	}

}
