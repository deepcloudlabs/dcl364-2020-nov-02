package com.example.stockmarket.controller.exceptionmapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CheckedExceptionMapper implements ExceptionMapper<Exception>{

	@Override
	public Response toResponse(Exception e) {
		return Response.status(Response.Status.BAD_REQUEST)
				   .entity(this.exceptionToJson(e))
				   .type(MediaType.APPLICATION_JSON)
				   .build();
	}

	private String exceptionToJson(Exception e) {
		return "{\"message\": \""+e.getMessage() +"\"}";
	}

}
